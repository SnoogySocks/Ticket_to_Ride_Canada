package controller;

import model.City;
import model.Player;
import model.Route;
import model.Ticket;
import view.GameFrame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class TTRController {
    
    GameFrame frame;
    
    public static Player[] players;

    public static ArrayList<Route> routes = new ArrayList<>();
    public static ArrayList<City> cities = new ArrayList<>();
    public static Stack<Ticket> tickets = new Stack<>();
    
    public TTRController () {

        FileImportController.init();
        players = new Player[4];

        routes = (ArrayList<Route>) FileImportController.routes.clone();
        cities = (ArrayList<City>) FileImportController.cities.clone();
        tickets = (Stack<Ticket>) FileImportController.tickets.clone();
        Collections.shuffle(tickets);

        frame = new GameFrame();

    }
    
}
