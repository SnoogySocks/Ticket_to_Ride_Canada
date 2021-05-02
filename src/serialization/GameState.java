package serialization;

import controller.TTRController;
import model.*;

import java.util.*;
import java.io.Serializable;

/**
 * Data object to store the state of a game which can be serialized and deserialized
 * @author Nathan
 */
public class GameState implements Serializable {
    public ArrayList<Route> routes;
    public HashSet<Route> availableRoutes;
    public ArrayList<City> cities;
    public Stack<Ticket> tickets;
    
    public Stack<TrainCard> trainCardDeck = new Stack<>();
    public Stack<TrainCard> trainCardDiscards = new Stack<>();
    public ArrayList<TrainCard> shownCards = new ArrayList<>();
    
    public Player[] players;
    public int playerTurn;
    
    public int endGameTurn = 1;
    public boolean isEndGame = false;
    
    /**
     * Auto-initialization constructor takes all data from TTRController
     */
    public GameState (boolean isEmpty) {
        this(isEmpty, TTRController.routes, TTRController.availableRoutes, TTRController.cities,
                TTRController.tickets, TTRController.trainCardDeck, TTRController.trainCardDiscards,
                TTRController.shownCards, TTRController.players, TTRController.playerTurn, TTRController.endGameTurn, TTRController.isEndGame);
    }
    
    public GameState (boolean isEmpty, ArrayList<Route> routes, HashSet<Route> availableRoutes, ArrayList<City> cities, Stack<Ticket> tickets, Stack<TrainCard> trainCardDeck, Stack<TrainCard> trainCardDiscards, ArrayList<TrainCard> shownCards, Player[] players, int playerTurn, int endGameTurn, boolean isEndGame) {
        
        if (isEmpty) return;
        this.routes = routes;
        this.availableRoutes = availableRoutes;
        this.cities = cities;
        this.tickets = tickets;
        this.trainCardDeck = trainCardDeck;
        this.trainCardDiscards = trainCardDiscards;
        this.shownCards = shownCards;
        this.players = players;
        this.playerTurn = playerTurn;
        this.endGameTurn = endGameTurn;
        this.isEndGame = isEndGame;
    }
}
