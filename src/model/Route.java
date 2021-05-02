package model;

import util.Coordinate;

import javax.swing.*;
import java.io.Serializable;
import java.util.Arrays;

/**
 * @author Felix
 */
public class Route extends JLabel implements Serializable, Comparable<Route> {
    
    private Player owner;
    private final City[] cities;
    private final int length;
    private final  CardColour colour;
    private final Coordinate completionPoint;
    private final boolean dualRoute;
    
    public Route (City city1, City city2, int length, CardColour colour, Coordinate completionPoint, boolean dualRoute) {
        
        setBounds(completionPoint.getX(), completionPoint.getY(), 15, 15);
        this.owner = null;
        cities = new City[] { city1, city2 };
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
    
    public City getCity (int index) {
        return cities[index];
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
                "cities="+Arrays.toString(cities)+
                ", length="+length+
                ", colour="+colour+
                '}';
    }
    
    // Compare the things in order to sort the routes by cities as a pair
    @Override
    public int compareTo (Route o) {
        int firstCompare = cities[0].getName().compareTo(o.cities[0].getName());
        return firstCompare!=0?firstCompare:cities[1].getName().compareTo(o.cities[1].getName());
    }
    
}
