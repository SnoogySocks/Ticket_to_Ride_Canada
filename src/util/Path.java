package util;

import model.Player;
import model.Route;

import java.util.ArrayList;

/**
 * Class to pass an integer by reference
 * @author Felix
 */

public class Path {
    
    private int length;
    private ArrayList<Route> path;
    
    public Path () {
        length = 0;
        path = new ArrayList<>();
    }
    
    public int getLength () {
        return length;
    }
    
    public void addLength (int additional, Route route) {
        length += additional;
        path.add(route);
    }
    
    public void clear () {
        this.length = 0;
        path.clear();
    }
    
    public Player getOwner() {
        return path.get(0).getOwner();
    }
    
    public ArrayList<Route> getPath () {
        return path;
    }
    
    @Override
    public String toString () {
        return Integer.toString(length);
    }
    
}
