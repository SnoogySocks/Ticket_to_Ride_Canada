package controller;

import model.*;
import view.GameFrame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import util.*;

public class TTRController extends Observable{
    
    GameFrame frame;
    
    public static TrainCardController tCController;
    public static TicketController ticketController;
    public static RouteController routeController;
    
    public static Player[] players;
    public static int playerTurn = 0;
    
    public static ArrayList<Route> routes;
    public static ArrayList<Route> availableRoutes;
    public static ArrayList<Route> ownedRoutes;
    public static ArrayList<City> cities;
    public static Stack<Ticket> tickets;
    
    public static Stack<TrainCard> trainCardDeck = new Stack<>(); //TODO these should be initialized with something
    public static Stack<TrainCard> trainCardDiscards = new Stack<>();
    public static ArrayList<TrainCard> shownCards = new ArrayList<>();
    
    public static void nextTurn() {
        playerTurn = playerTurn == 3 ? 0 : playerTurn + 1;
        notifyStaticObservers(EventType.NEXT_TURN);
    }
    
    public TTRController () {
        
        FileImportController.init();
        players = new Player[4];
        
        final PlayerColour[] clrValues = PlayerColour.values();
        for (int i = 0; i<players.length; ++i) {
            players[i] = new Player("Player "+(i+1), clrValues[i]);
        }
        
        routes = (ArrayList<Route>) FileImportController.routes.clone();
        availableRoutes = new ArrayList<>(routes);  // TODO Check if elements pass by reference
        ownedRoutes = new ArrayList<>();
        
        cities = (ArrayList<City>) FileImportController.cities.clone();
        tickets = (Stack<Ticket>) FileImportController.tickets.clone();
        Collections.shuffle(tickets);
        
        tCController = new TrainCardController();
        ticketController = new TicketController();
        routeController = new RouteController();
        
        trainCardDeck = tCController.generateTrainCardDeck();
        frame = new GameFrame();
        
        tCController.addObserver(frame.getCardPanel());
        
        tCController.flipFiveCards();
        
    }
    
}
