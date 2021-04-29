package model;

import util.Coordinate;

import javax.swing.*;

/**
 * @author Felix
 */
public class Route extends JLabel {
    
    private Player owner;
    private City city1, city2;
    private int length;
    private RouteColour colour;
    private Coordinate completionPoint;
    private boolean isDualRoute;
    
    public Route (City city1, City city2, int length, RouteColour colour, Coordinate completionPoint, boolean isDualRoute) {
        
        this.owner = null;
        this.city1 = city1;
        this.city2 = city2;
        this.length = length;
        this.colour = colour;
        this.completionPoint = completionPoint;
        this.isDualRoute = isDualRoute;
        
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
    
    public void setCity1 (City city1) {
        this.city1 = city1;
    }
    
    public City getCity2 () {
        return city2;
    }
    
    public void setCity2 (City city2) {
        this.city2 = city2;
    }
    
    public int getLength () {
        return length;
    }
    
    public void setLength (int length) {
        this.length = length;
    }
    
    public RouteColour getColour () {
        return colour;
    }
    
    public void setColour (RouteColour colour) {
        this.colour = colour;
    }
    
    public Coordinate getCompletionPoint () {
        return completionPoint;
    }
    
    public void setCompletionPoint (Coordinate completionPoint) {
        this.completionPoint = completionPoint;
    }
    
    public boolean isDualRoute () {
        return isDualRoute;
    }
    
    public void setDualRoute (boolean dualRoute) {
        isDualRoute = dualRoute;
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
    
}
