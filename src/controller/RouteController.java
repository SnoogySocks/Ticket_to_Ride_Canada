package controller;

import model.City;
import model.Player;
import model.Route;

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
    public Route getPlayerRouteChoice (Player player) {
    
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
        
        return (Route) JOptionPane.showInputDialog(null, "Choose route to claim...",
                "Claim Route", JOptionPane.QUESTION_MESSAGE, null,
                validRoutes.toArray(),
                validRoutes.get(0));
    
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
