package controller;

import model.*;
import view.GameFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import util.*;

import javax.swing.*;
import java.awt.*;

public class TTRController extends Observable implements ActionListener {
    
    GameFrame frame;
    
    public static TrainCardController tCController;
    public static TicketController ticketController;
    public static RouteController routeController;
    
    public static Player[] players;
    public static int playerTurn;
    
    public static ArrayList<Route> routes;
    public static ArrayList<Route> availableRoutes;
    public static ArrayList<Route> ownedRoutes;
    public static ArrayList<City> cities;
    public static Stack<Ticket> tickets;
    
    public static Stack<TrainCard> trainCardDeck = new Stack<>(); //TODO these should be initialized with something
    public static Stack<TrainCard> trainCardDiscards = new Stack<>();
    public static ArrayList<TrainCard> shownCards = new ArrayList<>();
    
    public TTRController () {
        
        FileImportController.init();
        players = new Player[4];
        
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
        frame.getPlayerPanel().getClaimRouteButton().addActionListener(this);
        
        tCController.addObserver(frame.getCardPanel());
        
        tCController.flipFiveCards();
        
        Player p = Testing.playerWithCompleteRoute();
        System.out.println(ticketController.checkTicketComplete(p.getTickets().get(0), p));
        
        newGame();
        
    }
    
    public void newGame() {
        
        playerTurn = 0;
        createPlayers();
        tCController.dealTrainCards();
        tCController.flipFiveCards();
        ticketController.dealStartingTickets();
        // TODO add below methods
        // showNextPlayer();
//        enableGUIElements(true);
        
        JOptionPane.showMessageDialog(frame, "GAME STARTING - Player 1 Begins");
        
    }
    
    public void createPlayers() {
        PlayerColour[] clrValues = PlayerColour.values();
        for (int i = 0; i<players.length; ++i) {
            players[i] = new Player("Player ", clrValues[i]);
        }
    }
    
    public static void nextTurn () {
        playerTurn = playerTurn==3 ? 0 : playerTurn+1;
        notifyStaticObservers(EventType.NEXT_TURN);
    }
    
    public static Player getCurrentPlayer() {
        return players[playerTurn];
    }
    
    @Override
    public void actionPerformed (ActionEvent e) {
        
        if (e.getSource()==frame.getCardPanel().getTicketDeckButton()) {
            ticketController.showTicketSelectionDialogue();
        }
        
        if (e.getSource()==frame.getPlayerPanel().getClaimRouteButton()) {
            
            // TODO check if the player has enough trains later
            
            
        }
        
    }
    
}
