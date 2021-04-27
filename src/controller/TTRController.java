package controller;

import model.City;
import model.Player;
import model.Route;
import model.Ticket;
import view.GameFrame;

import java.util.ArrayList;
import java.util.Stack;

public class TTRController {
    
    GameFrame frame;
    
    Player[] players;
    
    public TTRController () {
        
        FileImportController.init();
        players = new Player[4];
        
        frame = new GameFrame();
        
    }
    
}
