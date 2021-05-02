package controller;

import model.*;
import util.ContinuousPath;
import util.EventType;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;
import java.util.*;

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
    
    public ArrayList<Route> getAvailableRoutes (Player player) {
        
        ArrayList<Route> validRoutes = new ArrayList<>();
        for (Route route : TTRController.availableRoutes) {
        
            // Routes are valid if
            // 1. The player has enough trains and (
            //      1.1. The player has enough cards when the route is rainbow
            //      1.2. or the player has enough cards cards of that colour including rainbow
            // )
            if (route.getLength()<=player.getNumTrains() && (
                    route.getLength()<=player.getTotalCards() && route.getColour()==CardColour.GRAY
                            || route.getLength()<=
                            player.getNumCardsOfColour(route.getColour().getValue())
                                    +player.getNumCardsOfColour(CardColour.RAINBOW.getValue())
            )) {
                validRoutes.add(route);
            }
        
        }
        Collections.sort(validRoutes);
        return validRoutes;
        
    }
    
    /**
     *
     * @author Cerena
     * @param routes
     */
    public void highlightRoutes (ArrayList<Route> routes) {
    
    
    
    }
    
    /**
     * Called from the claim route button
     * Provide player with list of available routes
     * @param player = the current player
     */
    public void getPlayerRouteChoice (Player player) {
        
        // Find the routes that the player can go to
        ArrayList<Route> validRoutes = getAvailableRoutes(player);
        highlightRoutes(validRoutes);
        
        // If there are no choices available, cancel the transaction
        if (validRoutes.isEmpty()) {
            JOptionPane.showMessageDialog(TTRController.frame, "NO ROUTES AVAILABLE - CANCELING TRANSACTION...");
            return;
        }
        
        Route route = (Route) JOptionPane.showInputDialog(TTRController.frame,
                "Choose route to claim...",
                "Claim Route", JOptionPane.QUESTION_MESSAGE, null,
                validRoutes.toArray(),
                validRoutes.get(0));
        
        //if route is null then the player has cancelled the selection
        if (route==null) {
            return;
        }
        
        int[] numTrainCardsUsed = route.getColour()==CardColour.GRAY
                ? getPlayerTrainChoice(player, route) : null;
        
        updateGame(player, route, numTrainCardsUsed);
        TTRController.ticketController.scoreTicketsOnRouteAdded(player);
        TTRController.notifyStaticObservers(EventType.LOCK_CONTROLS);
        // TODO check when ticket is complete
        
    }
    
    /**
     * Called when the route is gray (any train card can be used)
     * @param player = current player
     * @param route  = chosen route
     * @return an array indicating the train cards to use
     */
    public int[] getPlayerTrainChoice (Player player, Route route) {
        
        CardColour[] values = CardColour.values();
        
        ArrayList<Object> parameters = new ArrayList<>();
        // Create the message
        parameters.add("Required # of train cards: "+route.getLength());
        parameters.add("How many train cards of each type will you use?");
        
        // Create the formatter for the text fields
        NumberFormatter formatter = new NumberFormatter(NumberFormat.getInstance());
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);
        ArrayList<JFormattedTextField> input = new ArrayList<>();
        
        // Create the JOptionPane
        for (int i = 0; i<values.length; ++i) {
            
            if (values[i]==CardColour.GRAY) continue;
            parameters.add(values[i].toString()+" train cards:");
            
            input.add(new JFormattedTextField(formatter));
            input.get(i).setText("0");
            parameters.add(input.get(i));
            
        }
        
        // Prompt the user for the number of trains to use
        // from their selected train cards until the
        // totalChosenCards==route.getLength()
        int[] numTrainCardsUsed = new int[values.length];
        int totalChosenCards;
        do {
            
            // Reset fields
            totalChosenCards = 0;
            Arrays.fill(numTrainCardsUsed, 0);
            
            JOptionPane.showMessageDialog(TTRController.frame, parameters.toArray(), "Train Cards", JOptionPane.QUESTION_MESSAGE);
            
            // Check if the cards exactly match a total of route.getLength()
            for (int i = 0; i<input.size(); ++i) {
                
                numTrainCardsUsed[i] = Integer.parseInt(input.get(i).getText());
                totalChosenCards += numTrainCardsUsed[i];
                
                // Invalid input if the player does not have enough train cards
                if (player.getNumCardsOfColour(i)<numTrainCardsUsed[i]) {
                    totalChosenCards = route.getLength()+1;
                    break;
                }
                
            }
            if (totalChosenCards!=route.getLength()) {
                JOptionPane.showMessageDialog(TTRController.frame, "Too many or too little trains. Try again.", "Alert", JOptionPane.ERROR_MESSAGE);
            }
            
        } while (totalChosenCards!=route.getLength());
        
        return numTrainCardsUsed;
        
    }
    
    public void updateGame (Player player, Route route, int[] numTrainCardsUsed) {
        
        final int routeColourValue = route.getColour().getValue();
        
        // Remove the player's trains
        player.setNumTrains(player.getNumTrains()-route.getLength());
        
        // Set up the success pane
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add("Successfully obtained");
        parameters.add(route.toString());
        parameters.add("Used the following train cards:");
        
        // TODO rainbow is place holder colour
        if (route.getColour()!=CardColour.GRAY) {
            
            // If the player has enough cards of that colour, use them
            final int difference = player.getNumCardsOfColour(routeColourValue)-route.getLength();
            if (difference>=0) {
                
                player.removeCards(routeColourValue, route.getLength());
                parameters.add(route.getColour().toString()+": "+route.getLength());
                
                // Otherwise, accommodate by using rainbow cards
            } else {
                
                parameters.add(route.getColour().toString()+": "+player.getNumCardsOfColour(routeColourValue));
                player.removeCards(routeColourValue, player.getNumCardsOfColour(routeColourValue));
                
                parameters.add(CardColour.RAINBOW.toString()+": "+(-difference));
                player.removeCards(CardColour.RAINBOW.getValue(), -difference);
                
            }
            
        } else {
            
            // Update the player's cards according to their choices
            CardColour[] values = CardColour.values();
            for (int i = 0; i<values.length; ++i) {
                
                if (numTrainCardsUsed[i]==0) continue;
                player.removeCards(i, numTrainCardsUsed[i]);
                parameters.add(values[i].toString()+": "+numTrainCardsUsed[i]);
                
            }
            
        }
        
        // Display the success pane
        JOptionPane.showMessageDialog(TTRController.frame, parameters.toArray(), "Success", JOptionPane.INFORMATION_MESSAGE);
        
        // Claim the route
        player.getClaimedRoutes().add(route);
        player.setScore(player.getScore()+routeScoringTable.get(route.getLength()));
        
        // Title case the colour to set a checkmark for the route
        String colour = player.getPlayerColour().toString().toLowerCase();
        colour = Character.toUpperCase(colour.charAt(0))+colour.substring(1);
        route.setIcon(new ImageIcon("./images/checkmark"+colour+".png"));
        
        // Update the player panel
        player.notifyObservers(EventType.UPDATE_TRAINS);
        player.notifyObservers(EventType.UPDATE_SCORES);
        
        // Put the used player trainCards in the discard pile
        for (int i = 0; i<player.getNumCardsOfColour(routeColourValue); ++i) {
            TTRController.trainCardDiscards.push(new TrainCard(route.getColour()));
        }
        
        // Make the route unavailable
        TTRController.availableRoutes.remove(route);
        
    }
    
    //TODO for deletion - we might score routes every time they are added to the player instead
    public int scoreRoutes (Player player) {
        
        int score = 0;
        for (Route route : player.getClaimedRoutes()) {
            if (route.getOwner()==player) {
                score += routeScoringTable.get(route.getLength());
            }
        }
        
        return score;
        
    }
    
    /**
     * Method to determine who will obtain 10 points for the
     * longest continuous path.
     * @return Owner of hte longest continuous path.
     * If there are multiple owners, return them.
     */
    public ArrayList<Player> getLongestContinuousPathOwners () {
        
        // Initialize the the longest path array
        int[] playersLongestPathLength = new int[TTRController.players.length];
        HashSet<Route> visited = new HashSet<>();
        
        // Iterate through all the routes to check for their length
        for (Route route: TTRController.routes) {
            
            if (visited.contains(route)) continue;
            
            // Find the length of the current path
            ContinuousPath currentPath = new ContinuousPath();
            visited.add(route);
            dfsLengthOfPath(visited, currentPath, route);
            
            // Check if the the current path length is longer than the current path
            int player = route.getOwner().getPlayerColour().getValue();
            int longestLength = playersLongestPathLength[player];
            
            if (longestLength<=currentPath.getLength()) {
    
                // Reset array if there is a new longest length
                if (longestLength<currentPath.getLength()) {
                    Arrays.fill(playersLongestPathLength, 0);
                }
                playersLongestPathLength[player] = currentPath.getLength();
                
            }
            
        }
        
        // Return the player(s) with the longest route
        ArrayList<Player> longestContinuousPathOwners = new ArrayList<>();
        for (int i = 0; i<playersLongestPathLength.length; ++i) {
            if (playersLongestPathLength[i]!=0) {
                longestContinuousPathOwners.add(TTRController.players[i]);
            }
        }
        
        return longestContinuousPathOwners;
        
    }
    
    public void dfsLengthOfPath (HashSet<Route> visited, ContinuousPath currentPath, Route currentRoute) {
    
    
    
    }
    
}
