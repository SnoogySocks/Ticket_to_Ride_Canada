package model;

import util.EventType;
import util.Observable;

import java.util.*;

/**
 * @author Nathan Wong
 */
public class Player extends Observable {

    private String name;
    private PlayerColour playerColour;
    private ArrayList<Ticket> tickets;
    private ArrayList<Route> claimedRoutes;
    private int[] numCardsOfColour;
    private int numTrains;
    private int score;
    
    public Player (String name, PlayerColour playerColour) {
        
        this.name = name;
        this.playerColour = playerColour;
        this.tickets = new ArrayList<>();
        this.claimedRoutes = new ArrayList<>();
        this.numCardsOfColour = new int[RouteColour.values().length];
        this.numTrains = 45;
        this.score = 0;
        
    }
    
    public void addTicket (Ticket ticket) {
        tickets.add(ticket);
        notifyObservers(EventType.UPDATE_SCORES);
    }
    
    public ArrayList<Route> getClaimedRoutes () {
        return claimedRoutes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public int getScore () {
        return score;
    }
    
    public void addTrainCard (TrainCard card) {
        numCardsOfColour[card.getColour().getValue()]++;
    }
    
    public void removeTrainCard (TrainCard card) {
        numCardsOfColour[card.getColour().getValue()]--;
    }
    
    public ArrayList<Ticket> getTickets(){
        return tickets;
    }
    
    public void setScore (int score) {
        this.score = score;
        notifyObservers(EventType.UPDATE_SCORES);
    }
    
    public int getNumTrains () {
        return numTrains;
    }
    
    public void setNumTrains (int numTrains) {
        this.numTrains = numTrains;
    }
    
    public int getNumCardsOfColour (int index) {
        return numCardsOfColour[index];
    }
    
    public void setNumCardsOfColour (int index, int num) {
        this.numCardsOfColour[index] = num;
    }
    
    public PlayerColour getPlayerColour () {
        return playerColour;
    }
    
    @Override
    public String toString () {
        return "Player{"+
                "name='"+name+'\''+
                ", playerColour="+playerColour+
                ", tickets="+tickets+
                ", claimedRoutes="+claimedRoutes+
                ", cards="+Arrays.toString(numCardsOfColour)+
                ", numTrains="+numTrains+
                ", score="+score+
                '}';
    }
    
}
