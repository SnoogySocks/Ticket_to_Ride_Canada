package util;

import model.Player;

/**
 * Class to pass an integer by reference
 * @author Felix
 */

public class Path {
    
    private int length;
    
    public Path () {
        this.length = 0;
    }
    
    public int getLength () {
        return length;
    }
    
    public void addLength (int additional) {
        length += additional;
    }
    
    @Override
    public String toString () {
        return Integer.toString(length);
    }
    
}
