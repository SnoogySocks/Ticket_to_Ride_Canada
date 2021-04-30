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

    /**
     * Auto-initialization constructor takes all data from TTRController
     */
    public GameState(boolean empty){
        if(!empty) {
            this.routes = TTRController.routes;
            this.availableRoutes = TTRController.availableRoutes;
            this.cities = TTRController.cities;
            this.tickets = TTRController.tickets;
            this.trainCardDeck = TTRController.trainCardDeck;
            this.trainCardDiscards = TTRController.trainCardDiscards;
            this.shownCards = TTRController.shownCards;
            this.players = TTRController.players;
            this.playerTurn = TTRController.playerTurn;
        }
    }

    // Standard constructor
    public GameState(ArrayList<Route> routes, HashSet<Route> availableRoutes, ArrayList<City> cities, Stack<Ticket> tickets,
                     Stack<TrainCard> trainCardDeck, Stack<TrainCard> trainCardDiscards, ArrayList<TrainCard> shownCards, Player[] players, int playerTurn) {
        this.routes = routes;
        this.availableRoutes = availableRoutes;
        this.cities = cities;
        this.tickets = tickets;
        this.trainCardDeck = trainCardDeck;
        this.trainCardDiscards = trainCardDiscards;
        this.shownCards = shownCards;
        this.players = players;
        this.playerTurn = playerTurn;
    }

    @Override
    public String toString() {
        return "GameState{" +
                "routes=" + routes +
                ", availableRoutes=" + availableRoutes +
                ", cities=" + cities +
                ", tickets=" + tickets +
                ", trainCardDeck=" + trainCardDeck +
                ", trainCardDiscards=" + trainCardDiscards +
                ", shownCards=" + shownCards +
                ", players=" + Arrays.toString(players) +
                ", playerTurn=" + playerTurn +
                '}';
    }
}
