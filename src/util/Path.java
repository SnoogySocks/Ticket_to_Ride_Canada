package util;

import model.Player;

/**
 * Class to pass an integer by reference
 * @author Felix
 */

public class Path {
    
    private Player owner;
    private int length;
    
    public Path (Player owner) {
        this.owner = owner;
        this.length = 0;
    }
    
    public Player getOwner() {
        return owner;
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
