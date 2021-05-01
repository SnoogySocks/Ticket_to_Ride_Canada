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

    public TTRController() {

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

    public void newGame() {

        playerTurn = 0;
        createPlayers();
        tCController.dealTrainCards();
        tCController.flipFiveCards();
        //ticketController.dealStartingTickets(); //TODO uncomment for prod
        // TODO add below methods
        // showNextPlayer();
//        enableGUIElements(true);

        setupObservers();
        setupActionListeners();

        notifyStaticObservers(EventType.NEXT_TURN); // necessary to initialize GUI to player 1
        JOptionPane.showMessageDialog(frame, "GAME STARTING - Player 1 Begins");

    }

    public void createPlayers() {
        players = new Player[4];
        PlayerColour[] clrValues = PlayerColour.values();
        for (int i = 0; i < players.length; ++i) {

            players[i] = new Player("Player " + (i + 1), clrValues[i + 1]);

        }

    }

    public void endGame() {
        ticketController.scoreTicketsEndGame();
    }

    private void setupActionListeners() {
        frame.getPlayerPanel().getNextTurnButton().addActionListener(this);
        frame.getPlayerPanel().getClaimRouteButton().addActionListener(this);
        frame.getLoadMI().addActionListener(this);
        frame.getSaveMI().addActionListener(this);
    }

    /**
     * @author Nathan
     */
    private static void setupObservers() {
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
    public static void saveGame() {
        //Copy our game data into an object then serialize it
        GameState state = new GameState(false);
        Serializer.serialize("./saveGames/save.ser", state);
    }

    /**
     * @author Nathan
     */
    public static void loadGame() {
        //deserialize saved game
        GameState state = Serializer.deserialize("./saveGames/save.ser");

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

        setupObservers();
        notifyStaticObservers(EventType.NEXT_TURN); //initializes frames to current player
        JOptionPane.showMessageDialog(frame, "GAME LOADED");

    }

    public static void nextTurn () {

        playerTurn = playerTurn==3 ? 0 : playerTurn+1;
        notifyStaticObservers(EventType.NEXT_TURN);
        JOptionPane.showMessageDialog(frame,
                getCurrentPlayer().getName()+"'s turn",
                "Alert", JOptionPane.INFORMATION_MESSAGE);

    }

    public static Player getCurrentPlayer () {
        return players[playerTurn];
    }

    @Override
    public void actionPerformed (ActionEvent e) {

        //a switch would be much nicer but these aren't constant values :(
        if (e.getSource()==frame.getCardPanel().getTicketDeckButton()) {
            ticketController.showTicketSelectionDialogue();
        } else if (e.getSource()==frame.getPlayerPanel().getClaimRouteButton()) {
            routeController.getPlayerRouteChoice(getCurrentPlayer());
        } else if (e.getSource()==frame.getPlayerPanel().getNextTurnButton()) {
            nextTurn();
        } else if (e.getSource()==frame.getSaveMI()) {
            saveGame();
        } else if (e.getSource()==frame.getLoadMI()) {
            loadGame();
        }

    }

}
