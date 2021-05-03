package controller;

import model.*;
import serialization.GameState;
import serialization.Serializer;
import util.Observable;
import view.GameFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import util.*;

import javax.swing.*;

public class TTRController extends Observable implements ActionListener {
    
    // Frame
    public static GameFrame frame;
    
    // Controllers
    public static TrainCardController tCController;
    public static TicketController ticketController;
    public static RouteController routeController;
    
    // Players
    public static Player[] players;
    public static int playerTurn;
    
    // Models
    public static ArrayList<Route> routes;
    public static HashSet<Route> availableRoutes;
    public static ArrayList<City> cities;
    public static Stack<Ticket> tickets;
    
    public static Stack<TrainCard> trainCardDeck = new Stack<>();
    public static Stack<TrainCard> trainCardDiscards = new Stack<>();
    public static ArrayList<TrainCard> shownCards = new ArrayList<>();
    
    public static int endGameTurn = 1;
    public static boolean isEndGame = false;
    
    public TTRController () {
        
        FileImportController.init();
        createPlayers();
        
        System.out.println(Arrays.toString(players));
        
        routes = (ArrayList<Route>) FileImportController.routes.clone();
        availableRoutes = new HashSet<>(routes);
        
        cities = (ArrayList<City>) FileImportController.cities.clone();
        tickets = (Stack<Ticket>) FileImportController.tickets.clone();
        Collections.shuffle(tickets);
        
        tCController = new TrainCardController();
        ticketController = new TicketController();
        routeController = new RouteController();
        
        trainCardDeck = tCController.generateTrainCardDeck();
        frame = new GameFrame();
        
        tCController.addObserver(frame.getCardPanel());
        frame.getPlayerPanel().updateCurrentPlayer(getCurrentPlayer());
        
        setupActionListeners();
        
        newGame();
        
    }
    
    public void newGame () {
        
        playerTurn = 0;
        endGameTurn = 1;
        createPlayers();
        tCController.dealTrainCards();
        tCController.flipFiveCards();
        //ticketController.dealStartingTickets();
        // TODO add below methods
        //         showNextPlayer();
        //         enableGUIElements(true);
        
        setupObservers();
        
        notifyStaticObservers(EventType.NEXT_TURN); // necessary to initialize GUI to player 1
        JOptionPane.showMessageDialog(frame, "GAME STARTING - Player 1 Begins");
        
    }
    
    public static void createPlayers () {
        
        players = new Player[4];
        PlayerColour[] clrValues = PlayerColour.values();
        for (int i = 1; i<=players.length; ++i) {
            players[i-1] = new Player("Player "+i, clrValues[i]);
        }
        
    }
    
    public static void endGame () {
        
        ticketController.scoreTicketsEndGame();
        
        ArrayList<Player> highest = new ArrayList<>();
        highest.add(players[0]);
    
        // Score the longest path
        ArrayList<Object> parameter = new ArrayList<>();
        parameter.add("Owner(s) of the longest route: ");
        parameter.add("John Cena");
        
        for (Player player : TTRController.routeController.getLongestContinuousPathOwners()) {
            player.setScore(player.getScore()+10);
            parameter.add(player.getName());
        }
        
        // Display the owner(s) of the longest route
        JOptionPane.showMessageDialog(frame, parameter.toArray(), "Bonus", JOptionPane.INFORMATION_MESSAGE);
        
        for (int i = 1 ; i < players.length; i++) {
            Player p = players[i];
            if (p.getScore()==highest.get(0).getScore()) {
                highest.add(p);
            } else if (p.getScore()>highest.get(0).getScore()) {
                highest.clear();
                highest.add(p);
            }
        }
        
        if (highest.size()>1) {
            StringBuilder msg = new StringBuilder("Tie: ");
            for (Player p : highest) {
                msg.append(p.getName()).append(" ");
            }
            
            JOptionPane.showMessageDialog(frame, msg.toString());
        } else {
            JOptionPane.showMessageDialog(frame, "Winner: "+highest.get(0).getName());
        }
        
    }
    
    private void setupActionListeners () {
        
        frame.getPlayerPanel().getNextTurnButton().addActionListener(this);
        frame.getPlayerPanel().getClaimRouteButton().addActionListener(this);
        frame.getNewMI().addActionListener(this);
        frame.getExitMI().addActionListener(this);
        frame.getLoadMI().addActionListener(this);
        frame.getSaveMI().addActionListener(this);
        frame.getHelpContentsMI().addActionListener(this);
        frame.getAboutMI().addActionListener(this);
        
    }
    
    /**
     *
     */
    private static void setupObservers () {
        // For any GUI elements that need to be updated when something changes for a player
        for (Player p : players) {
            p.addObserver(frame.getPlayerPanel());
            p.addObserver(frame.getScorePanel());
        }
        
        // For any GUI elements that need to be updated when the next turn is called
        TTRController.addStaticObserver(frame.getCardPanel());
        TTRController.addStaticObserver(frame.getPlayerPanel());
        TTRController.addStaticObserver(frame.getScorePanel());
        
        tCController.addObserver(frame.getCardPanel());
    }
    
    /**
     *
     */
    public static void saveGame () {
        
        JTextField name = new JTextField();
        Object[] params = { name };
        JOptionPane.showMessageDialog(frame, params, "Save Game", JOptionPane.QUESTION_MESSAGE);
        
        //Copy our game data into an object then serialize it
        GameState state = new GameState(false);
        Serializer.serialize("./savedGames/"+name.getText()+".ser", state);
    }
    
    /**
     *
     */
    public static void loadGame () {
        JTextField name = new JTextField();
        Object[] params = { name };
        JOptionPane.showMessageDialog(frame, params, "Load Game", JOptionPane.QUESTION_MESSAGE);
        
        //deserialize saved game
        GameState state = Serializer.deserialize("./savedGames/"+name.getText()+".ser");
        
        if (state==null) {
            JOptionPane.showMessageDialog(frame, "No Saved Games Found!");
            return;
        }
        
        //set the static variables to the new loaded data
        routes = state.routes;
        availableRoutes = state.availableRoutes;
        cities = state.cities;
        tickets = state.tickets;
        trainCardDeck = state.trainCardDeck;
        trainCardDiscards = state.trainCardDiscards;
        shownCards = state.shownCards;
        players = state.players;
        playerTurn = state.playerTurn;
        endGameTurn = state.endGameTurn;
        isEndGame = state.isEndGame;
        
        setupObservers();
        notifyStaticObservers(EventType.NEXT_TURN); //initializes frames to current player
        JOptionPane.showMessageDialog(frame, "GAME LOADED");
    }
    
    /**
     *
     */
    public static void nextTurn () {
        
        playerTurn = playerTurn==3 ? 0 : playerTurn+1;
        if (isEndGame) {
            if (endGameTurn==4) {
                endGame();
                return;
            } else {
                endGameTurn++;
            }

        } else {
            endGameConditions();
        }

        notifyStaticObservers(EventType.NEXT_TURN);
        JOptionPane.showMessageDialog(frame,
                getCurrentPlayer().getName()+"'s turn",
                "Alert", JOptionPane.INFORMATION_MESSAGE);


    }
    
    /**
     *
     */
    public static void endGameConditions () {
        for (Player p : players) {
            if (p.getNumTrains()<=2) {
                isEndGame = true;
                JOptionPane.showMessageDialog(frame ,"One More Round");
                return;
            }
        }
        isEndGame = false;
    }
    
    /**
     *
     */
    public static Player getCurrentPlayer () {
        return players[playerTurn];
    }
    
    /**
     *
     */
    public void helpMenuMIMessage () {
        JOptionPane.showMessageDialog(frame,
                "\n\n " +
                        "How to Score Points:\n"+
                        "   - Claiming a route between two adjacent cities on the map\n"+
                        "   - Completing a continuous path of routes between two cities listed on your Destination Tickets\n"+
                        "   - Completing the Longest Continuous Path of routes.\n\n"+
                        
                        "Game Setup:\n"+
                        "   - Each player is give 4 random train cards\n"+
                        "   - 45 coloured trains\n"+
                        "   - Each player must select at least 2 tickets at the start of the game and at least 1 (up to 3) if they wish to pick the ticket deck for their round\n\n"+
                        
                        "Gameplay:\n"+
                        "   Player can do the following actions only once during their turn:\n"+
                        "       - Draw Train Car Cards\n"+
                        "       - Claim a route\n"+
                        "       - Draw Destination Tickets\n" +
                        "   *The game ends when one of the players reach 2 or less trains*\n\n"+
                        
                        "Drawing Train Car Cards:\n"+
                        "   - Players are allowed to draw 2 train cards in total, two from the 5 face up cards or 2 from the card deck or one of each\n"+
                        "   - If a rainbow card is chosen from one of the 5 face up cards, the player is only able to pick that one rainbow card and not take another train card\n\n"+
                        
                        "Claiming Routes:\n"+
                        "   - Available routes that a players can claim is highlighted in pink\n\n");
    }
    
    public void aboutMessage () {
        JOptionPane.showMessageDialog(frame, ""+
                "       \n    Ticket to Ride - Canada Edition       \n"+
                "                    Created by\n " +
                "            Cerena, Felix, Nathan\n"+
                "                        2021");
        
    }
    
    @Override
    public void actionPerformed (ActionEvent e) {
        
        // A switch would be much nicer but these aren't constant values :( - Nathan
        if (e.getSource()==frame.getCardPanel().getTicketDeckButton()) {
            ticketController.showTicketSelectionDialogue(false);
            notifyStaticObservers(EventType.LOCK_CONTROLS);
        } else if (e.getSource()==frame.getPlayerPanel().getClaimRouteButton()) {
            routeController.getPlayerRouteChoice(getCurrentPlayer());
        } else if (e.getSource()==frame.getPlayerPanel().getNextTurnButton()) {
            nextTurn();
        } else if (e.getSource()==frame.getSaveMI()) {
            saveGame();
        } else if (e.getSource()==frame.getLoadMI()) {
            loadGame();
            
        //Cerena
        } else if (e.getSource()==frame.getNewMI()) {
            newGame();
        } else if (e.getSource()==frame.getExitMI()) {
            System.exit(0);
        } else if (e.getSource()==frame.getHelpContentsMI()) {
            helpMenuMIMessage();
        } else if (e.getSource()==frame.getAboutMI()) {
            aboutMessage();
        }
        
    }
    
}
