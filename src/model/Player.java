package model;

import java.util.*;

/**
 * @author Nathan Wong
 */
public class Player {
    
    private String name;
    private PlayerColour playerColour;
    private ArrayList<Ticket> tickets;
    private ArrayList<Route> claimedRoutes;
    private int[] numCardsOfColour;
    private int numTrains;
    private int score;
    
    public Player (String name, PlayerColour playerColour, ArrayList<Ticket> tickets, ArrayList<Route> claimedRoutes, int[] cards, int numTrains) {
        
        this.name = name;
        this.playerColour = playerColour;
        this.tickets = tickets;
        this.claimedRoutes = claimedRoutes;
        this.numCardsOfColour = cards;
        this.numTrains = numTrains;
        this.numTrains = score = 0;
        
    }
    
    public String getName () {
        return name;
    }
    
    public void setName (String name) {
        this.name = name;
    }
    
    public PlayerColour getPlayerColour () {
        return playerColour;
    }
    
    public void setPlayerColour (PlayerColour playerColour) {
        this.playerColour = playerColour;
    }
    
    public ArrayList<Ticket> getTickets () {
        return tickets;
    }
    
    public void setTickets (ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }
    
    public ArrayList<Route> getClaimedRoutes () {
        return claimedRoutes;
    }
    
    public void setClaimedRoutes (ArrayList<Route> claimedRoutes) {
        this.claimedRoutes = claimedRoutes;
    }
    
    public int[] getNumCardsOfColour () {
        return numCardsOfColour;
    }
    
    public void setNumCardsOfColour (int[] numCardsOfColour) {
        this.numCardsOfColour = numCardsOfColour;
    }
    
    public int getNumTrains () {
        return numTrains;
    }
    
    public void setNumTrains (int numTrains) {
        this.numTrains = numTrains;
    }
    
    public int getScore () {
        return score;
    }
    
    public void setScore (int score) {
        this.score = score;
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
