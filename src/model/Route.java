package model;

import util.Coordinate;

import javax.swing.*;
import java.io.Serializable;

/**
 * @author Felix
 */
public class Route extends JLabel implements Serializable, Comparable<Route> {
    
    private Player owner;
    private final City city1, city2;
    private final int length;
    private final  CardColour colour;
    private final Coordinate completionPoint;
    private final boolean dualRoute;
    
    public Route (City city1, City city2, int length, CardColour colour, Coordinate completionPoint, boolean dualRoute) {
        
        setBounds(completionPoint.getX(), completionPoint.getY(), 15, 15);
        this.owner = null;
        this.city1 = city1;
        this.city2 = city2;
        this.length = length;
        this.colour = colour;
        this.completionPoint = completionPoint;
        this.dualRoute = dualRoute;
        
    }
    
    public Player getOwner () {
        return owner;
    }
    
    public void setOwner (Player owner) {
        this.owner = owner;
        owner.getClaimedRoutes().add(this);
    }
    
    public City getCity1 () {
        return city1;
    }
    
    public City getCity2 () {
        return city2;
    }
    
    public int getLength () {
        return length;
    }
    
    public CardColour getColour () {
        return colour;
    }
    
    public Coordinate getCompletionPoint () {
        return completionPoint;
    }
    
    public boolean isDualRoute () {
        return dualRoute;
    }
    
    @Override
    public String toString () {
        return "Route{"+
                "city1="+city1+
                ", city2="+city2+
                ", length="+length+
                ", colour="+colour+
                '}';
    }
    
    @Override
    public int compareTo (Route o) {
        return city1.getName().compareTo(o.city1.getName());
    }
    
}
