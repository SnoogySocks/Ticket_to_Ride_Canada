package model;

/**
 * @author Cerena
 */
public enum PlayerColour {
    
    RED(0), GREEN(1), BLUE(2), YELLOW(3);
    
    private int val;
    PlayerColour (int val) {
        this.val = val;
    }
    
}
