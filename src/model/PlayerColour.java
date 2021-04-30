package model;

import java.io.Serializable;

/**
 * @author Cerena
 */
public enum PlayerColour implements Serializable {
    
    WHITE(0), RED(1), GREEN(2), BLUE(3), YELLOW(4);
    
    private final int val;
    
    PlayerColour (int val) {
        this.val = val;
    }
    
    public int getValue () {
        return val;
    }
    
}
