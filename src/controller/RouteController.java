package controller;

import model.*;
import util.EventType;

import javax.swing.*;
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
        
        for (Route route: availableRoutes) {
            
            // Routes are valid if
            // 1. The player has enough trains and (
            //      1.1. The player has enough cards when the route is rainbow
            //      1.2. or the player has enough cards cards of that colour including rainbow
            // )
            if (route.getLength()<=player.getNumTrains() && (
                    route.getLength()<=player.getTotalCards() && route.getColour()==CardColour.RAINBOW
                    || route.getLength()<=
                            player.getNumCardsOfColour(route.getColour().getValue())+
                                    player.getNumCardsOfColour(CardColour.RAINBOW.getValue())
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
                ? getPlayerTrainChoice(player, route):null;
        
        updateGame(player, route, numTrainCardsUsed);
        nextTurn();
    
    }
    
    /**
     * Called when the route is gray (any train card can be used)
     * @param player = current player
     * @param route = chosen route
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
        
        // Loop until the player chooses enough card colours
        // to fill the route with
        JOptionPane.showMessageDialog(frame, checkBoxes);
        
        // From the selected checkBoxes, check if
        
        int totalUsedTrains = 0;
    
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
        for (Route route: player.getClaimedRoutes()) {
            if (route.getOwner()==player) {
                score += routeScoringTable.get(route.getLength());
            }
        }
        
        return score;
        
    }
    
}
