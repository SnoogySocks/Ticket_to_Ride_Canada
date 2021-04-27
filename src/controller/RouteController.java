package controller;

import model.Player;
import model.Route;

import javax.swing.*;
import java.util.ArrayList;

import static controller.FileImportController.routes;

/**
 * @author Felix
 */
public class RouteController {
    
    private ArrayList<Route> routes;
    
    public RouteController () {
        routes = new ArrayList<>(FileImportController.routes);
    }
    
    public static void generateRoutes (Player player) {
        
        JOptionPane availableRoutes = new JOptionPane();
        
        
    }
    
    public Route getRoute (int index) {
        return routes.get(index);
    }
    
}
