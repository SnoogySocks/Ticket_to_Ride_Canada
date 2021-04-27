package controller;

import model.City;
import model.Route;
import model.Ticket;
import view.GameFrame;

import java.util.ArrayList;
import java.util.Stack;

public class TTRController {
    
    private GameFrame frame;
    
    public final ArrayList<Route> routes;
    public final Stack<Ticket> tickets;
    
    public TTRController () {
        
        FileImportController.init();
        
        frame = new GameFrame();
        
    }
    
}
