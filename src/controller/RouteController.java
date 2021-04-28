package controller;

import model.City;
import model.Player;
import model.Route;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

import static controller.TTRController.routes;

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
        // TODO add score for a length of 7
    }};
    
    public static ArrayList<Route> generateAllAvailableRoutes () {
        
        ArrayList<Route> availableRoutes = new ArrayList<>();
        
        for (int i = 0 ; i<routes.size(); ++i) {
            if (routes.get(i).getOwner()==null) {
                availableRoutes.add(routes.get(i));
            }
        }
        
        return availableRoutes;
        
    }
    
    public static ArrayList<Route> generateAllOwnedRoutes (Player player) {
    
        ArrayList<Route> ownedRoutes = new ArrayList<>();
        
        for (Route route: routes) {
            if (route.getOwner()==player) {
                ownedRoutes.add(route);
            }
        }
        
        return ownedRoutes;
        
    }
    
    public static void removeCompletedRouteCards () {
        // TODO
    }
    
    public static int scoreRoutes (Player player) {
        
        int score = 0;
        for (Route route: generateAllOwnedRoutes(player)) {
            score += routeScoringTable.get(route.getLength());
        }
        
        return score;
    
    }
    
}
