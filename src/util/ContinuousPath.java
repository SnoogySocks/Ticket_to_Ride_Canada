package util;

import model.City;
import model.Player;
import model.Route;

/**
 * @author Felix
 */

public class ContinuousPath {
    
    private City previousCity;
    private Route currentRoute;
    private int length;
    
    public ContinuousPath (City previousCity, Route currentRoute) {
        this.previousCity = previousCity;
        this.currentRoute = currentRoute;
        this.length = 0;
    }
    
    public int getLength () {
        return length;
    }
    
    public void setLength (int length) {
        this.length = length;
    }
    
    public void incrementLength () {
        ++length;
    }
    
}
