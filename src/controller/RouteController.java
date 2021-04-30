package controller;

import model.*;
import util.EventType;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;

import static controller.TTRController.*;

/**
 * @author Felix
 */
public class RouteController {
    
    public static final HashMap<Integer, Integer> routeScoringTable = new HashMap<>() {{
        put(1, 1);
        put(2, 2);
        put(3, 4);
        put(4, 7);
        put(5, 10);
        put(6, 15);
        put(7, 18);
    }};
    
    /**
     * Called from the claim route button
     * Provide player with list of available routes
     * @param player = the current player
     */
    public void getPlayerRouteChoice (Player player) {
        
        // Find the routes that the player can go to
        ArrayList<Route> validRoutes = new ArrayList<>();
        
        for (Route route : availableRoutes) {
            
            // Routes are valid if
            // 1. The player has enough trains and (
            //      1.1. The player has enough cards when the route is rainbow
            //      1.2. or the player has enough cards cards of that colour including rainbow
            // )
            if (route.getLength()<=player.getNumTrains() && (
                    route.getLength()<=player.getTotalCards() && route.getColour()==CardColour.RAINBOW
                            || route.getLength()<=
                            player.getNumCardsOfColour(route.getColour().getValue())
                                    +player.getNumCardsOfColour(CardColour.RAINBOW.getValue())
            )) {
                validRoutes.add(route);
            }
            
        }
        
        // If there are no choices available, cancel the transaction
        if (validRoutes.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "NO ROUTES AVAILABLE - CANCEL TRANSACTION");
            return;
        }
        
        Route route = (Route) JOptionPane.showInputDialog(frame, "Choose route to claim...",
                "Claim Route", JOptionPane.QUESTION_MESSAGE, null,
                validRoutes.toArray(),
                validRoutes.get(0));
        
        int[] numTrainCardsUsed = route.getColour()==CardColour.RAINBOW
                ? getPlayerTrainChoice(player, route) : null;
        // TODO consider asking for the number of rainbow cards they will use
        
        updateGame(player, route, numTrainCardsUsed);
        nextTurn();
        
    }
    
    /**
     * Called when the route is gray (any train card can be used)
     * @param player = current player
     * @param route  = chosen route
     * @return an array indicating the train cards to use
     */
    public int[] getPlayerTrainChoice (Player player, Route route) {
        
        CardColour[] values = CardColour.values();
        int[] numTrainCardsUsed = new int[values.length];
        
        // Make a JOption pane with checkboxes displaying
        // the CardColours the player can choose from
        JCheckBox[] checkBoxes = new JCheckBox[values.length];
        for (int i = 0; i<values.length; ++i) {
            checkBoxes[i] = new JCheckBox(values[i].toString());
        }
        
        // Loop until the player has chosen card colours
        // with enough cards to fill the route with
        int totalChosenCards = 0;
        while (totalChosenCards<route.getLength()) {
            
            JOptionPane.showMessageDialog(frame, checkBoxes);
            
            for (int i = 0; i<checkBoxes.length; ++i) {
                if (checkBoxes[i].isSelected()) {
                    totalChosenCards += player.getNumCardsOfColour(i);
                }
            }
            
        }
        
        ArrayList<Object> parameters = new ArrayList<>();
        // Create the message
        parameters.add("How many train cards of each type are there?");
        
        // Create the formatter for the text fields
        NumberFormatter formatter = new NumberFormatter(NumberFormat.getInstance());
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setAllowsInvalid(false);
        ArrayList<JFormattedTextField> input = new ArrayList<>();
        
        // Create the JOptionPane
        for (int i = 0; i<checkBoxes.length; ++i) {
            
            if (!checkBoxes[i].isSelected()) continue;
            parameters.add(values[i].toString()+" train cards:");
            
            formatter.setMaximum(player.getNumCardsOfColour(i));
            input.add(new JFormattedTextField(formatter));
            input.get(i).setText("0");
            parameters.add(input.get(i));
            
        }
    
        // Prompt the user for the number of trains to use
        // from their selected train cards until the
        // totalChosenCards==route.getLength()
        totalChosenCards = 0;
        while (totalChosenCards!=route.getLength()) {
            
            JOptionPane.showMessageDialog(frame, parameters, "Train Cards", JOptionPane.QUESTION_MESSAGE);
            
        }
        
        //        Route route = (Route) JOptionPane.showInputDialog(frame, "Choose route to claim...",
        //                "Claim Route", JOptionPane.QUESTION_MESSAGE, null,
        //                validRoutes.toArray(),
        //                validRoutes.get(0));
        
        return numTrainCardsUsed;
        
    }
    
    public void updateGame (Player player, Route route, int[] numTrainCardsUsed) {
        
        // Remove the player's trains
        player.setNumTrains(player.getNumTrains()-route.getLength());
        
        if (numTrainCardsUsed==null) {
            player.removeTrainCards(route.getColour().getValue(), route.getLength());
        } else {
            for (int i = 0; i<numTrainCardsUsed.length; ++i) {
                player.removeTrainCards(i, numTrainCardsUsed[i]);
            }
        }
        
        // Claim the route
        player.getClaimedRoutes().add(route);
        
        // Title case the colour to set a checkmark for the route
        String colour = player.getPlayerColour().toString().toLowerCase();
        colour = Character.toUpperCase(colour.charAt(0))+colour.substring(1);
        route.setIcon(new ImageIcon("./images/checkmark"+colour+".png"));
        
        // Update the player panel
        player.notifyObservers(EventType.UPDATE_TRAINS);
        
        // Put the used player trainCards in the discard pile
        for (int i = 0; i<player.getNumCardsOfColour(route.getColour().getValue()); ++i) {
            trainCardDiscards.push(new TrainCard(route.getColour()));
        }
        
        // Make the route unavailable
        availableRoutes.remove(route);
        
    }
    
    public int scoreRoutes (Player player) {
        
        int score = 0;
        for (Route route : player.getClaimedRoutes()) {
            if (route.getOwner()==player) {
                score += routeScoringTable.get(route.getLength());
            }
        }
        
        return score;
        
    }
    
}
