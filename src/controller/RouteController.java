package controller;

import model.City;
import model.Player;
import model.Route;
import model.TrainCard;
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
     */
    public void getPlayerRouteChoice (Player player) {
    
        // Find the routes that the player can go to
        ArrayList<Route> validRoutes = new ArrayList<>();
        
        // Routes are valid if
        // 1. The player has enough of trains
        // 2. The player has enough train cards of that colour
        for (Route route: availableRoutes) {
            if (player.getNumTrains()>=route.getLength()
                    && player.getNumCardsOfColour(route.getColour().getValue())>=route.getLength()) {
                validRoutes.add(route);
            }
        }
        
        // If there are no choices available, cancel the transaction
        if (validRoutes.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "NO ROUTES AVAILABLE - CANCEL TRANSAcTION");
            return;
        }
    
        Route route = (Route) JOptionPane.showInputDialog(null, "Choose route to claim...",
                "Claim Route", JOptionPane.QUESTION_MESSAGE, null,
                validRoutes.toArray(),
                validRoutes.get(0));
        
        // Use the player's trains
        player.setNumTrains(player.getNumTrains()-route.getLength());
        
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
