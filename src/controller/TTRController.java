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
        
        newGame();
        
    }
    
    public void newGame () {
        
        playerTurn = 0;
        createPlayers();
        tCController.dealTrainCards();
        tCController.flipFiveCards();
        //ticketController.dealStartingTickets(); //TODO uncomment
        // TODO add below methods
        // showNextPlayer();
        // enableGUIElements(true);
        
        setupObservers();
        setupActionListeners();
        
        notifyStaticObservers(EventType.NEXT_TURN); // necessary to initialize GUI to player 1
        JOptionPane.showMessageDialog(frame, "GAME STARTING - Player 1 Begins");
        // TODO good
        Testing.longestPathIsWorking(routeController);
        
    }
    
    public static void createPlayers () {
        
        players = new Player[4];
        PlayerColour[] clrValues = PlayerColour.values();
        for (int i = 0; i<players.length; ++i) {
            players[i] = new Player("Player "+(i+1), clrValues[i+1]);
        }
        
    }
    
    public static void endGame () {
        ticketController.scoreTicketsEndGame();
        
        ArrayList<Player> highest = new ArrayList<Player>();
        highest.add(players[0]);
        
        for(Player p : players){
            if(p.getScore() == highest.get(0).getScore()){
                highest.add(p);
            }
            else if (p.getScore() > highest.get(0).getScore()){
                highest.clear();
                highest.add(p);
            }
        }
        
        if(highest.size() > 1){
            String msg = "Tie: ";
            for(Player p : highest){
                msg += p.getName() + " ";
            }
            
            JOptionPane.showMessageDialog(frame, msg);
        } else {
            JOptionPane.showMessageDialog(frame, "Winner: " + highest.get(0).getName());
        }
        
    }
    
    private void setupActionListeners () {
        frame.getPlayerPanel().getNextTurnButton().addActionListener(this);
        frame.getPlayerPanel().getClaimRouteButton().addActionListener(this);
        frame.getNewMI().addActionListener(this);
        frame.getExitMI().addActionListener(this);
        frame.getLoadMI().addActionListener(this);
        frame.getSaveMI().addActionListener(this);
        frame.getHelpMenu().addActionListener(this);
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
     * @author Nathan
     */
    public static void saveGame () {
        
        JTextField name = new JTextField();
        Object[] params = { name };
        JOptionPane.showMessageDialog(frame, params, "Save Game Name", JOptionPane.QUESTION_MESSAGE);
        
        //Copy our game data into an object then serialize it
        GameState state = new GameState(false);
        Serializer.serialize("./saveGames/"+name.getText()+".ser", state);
    }
    
    /**
     * @author Nathan
     */
    public static void loadGame () {
        JTextField name = new JTextField();
        Object[] params = { name };
        JOptionPane.showMessageDialog(frame, params, "Save Game Name", JOptionPane.QUESTION_MESSAGE);
        
        //deserialize saved game
        GameState state = Serializer.deserialize("./saveGames/"+name.getText()+".ser");
        
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
        notifyStaticObservers(EventType.NEXT_TURN);
        JOptionPane.showMessageDialog(frame,
                getCurrentPlayer().getName()+"'s turn",
                "Alert", JOptionPane.INFORMATION_MESSAGE);
        
        if (isEndGame) {
            if (endGameTurn==4) {
                endGame();
            } else {
                endGameTurn++;
            }
            
        } else {
            endGameConditions();
        }
        
    }
    
    /**
     *
     */
    public static void endGameConditions () {
        for (Player p : players) {
            if (p.getNumTrains()<=2) {
                isEndGame = true;
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
    public void helpMenuMessage () {
        JOptionPane.showMessageDialog(frame,
                " The object of the game is to score points by:\n"+
                        "\n\t"+
                        "- claiming a route between two adjacent cities on the map\n\t"+
                        "- completing a continuous path of routes between two cities listed on your Destination Tickets.\n\t"+
                        "- Completing the Longest Continuous Path of routes.\n\t"+
                        
                        "Game Setup:\n\t"+
                        "-Each player is give 4 random train cards\n\t"+
                        "-45 coloured trains"+
                        "-Each player must select at least 2 tickets at the start of the game and at least 1 (up to 3) if they wish to pick the ticket deck for their round\n"+
                        
                        "Gameplay:\n\t"+
                        "Player can do the following actions only once during their turn:\n\t\t:"+
                        "-draw Train Car Cards\n\t\t"+
                        "-claim a route\n\t\t"+
                        "-draw Destination Tickets\n"+
                        
                        "Drawing Train Car Cards:\n\t" +
                        "-Players are allowed to draw 2 train cards in total, two from the 5 face up cards or 2 from the card deck or one of each\n\t" +
                        "-If a rainbow card is chosen from one of the 5 face up cards, the player is only able to pick that one rainbow card and not take another train card" +
                        
                        "Claiming Routes:\n\t" +
                        "-Available routes that a players can claim is highlighted in pink");
    }
    
    public void aboutMessage(){
        JOptionPane.showMessageDialog(frame,"" +
                "\t\tTicket to Ride - Canada Edition\n\n" +
                "\t\t Created by Cerena, Felix, Nathan\n" +
                "\t\t 2021");
        
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
        } else if (e.getSource()==frame.getHelpMenu()) {
            helpMenuMessage();
        }
        else if(e.getSource()==frame.getAboutMI()){
            aboutMessage();
        }
        
    }
    
}
