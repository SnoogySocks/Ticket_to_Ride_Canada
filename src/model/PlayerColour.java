package model;

/**
 * @author Cerena
 */
public enum PlayerColour {
    
    WHITE(0), RED(1), GREEN(2), BLUE(3), YELLOW(4);
    
    private int val;
    PlayerColour (int val) {
        this.val = val;
    }
    
}
