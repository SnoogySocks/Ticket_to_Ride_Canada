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
    public Route getPlayerRouteChoice () {
    
        return (Route) JOptionPane.showInputDialog(null, "Choose route to claim...",
                "Claim Route", JOptionPane.QUESTION_MESSAGE, null,
                availableRoutes.toArray(),
                availableRoutes.get(0));
    
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
