package model;

import javax.swing.*;
import java.awt.*;

/**
 * @author Felix
 */
public class Route extends JLabel {
    
    private PlayerColour owner;
    private City city1, city2;
    private int length;
    private Color colour;
    private Coordinate completionPoint;
    private boolean isDualRoute;
    
    public Route (PlayerColour owner, City city1, City city2, int length, Color colour, Coordinate completionPoint, boolean isDualRoute) {
        
        this.owner = owner;
        this.city1 = city1;
        this.city2 = city2;
        this.length = length;
        this.colour = colour;
        this.completionPoint = completionPoint;
        this.isDualRoute = isDualRoute;
        
    }
    
    public PlayerColour getOwner () {
        return owner;
    }
    
    public void setOwner (PlayerColour owner) {
        this.owner = owner;
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
    
    public Color getColour () {
        return colour;
    }
    
    public void setColour (Color colour) {
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
    
}
