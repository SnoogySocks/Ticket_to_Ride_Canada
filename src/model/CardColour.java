package model;

/**
 * @author Cerena
 */
public enum CardColour {
    RAINBOW(0), BLACK(1), BLUE(2),
    GREEN(3), ORANGE(4), PURPLE(5),
    RED(6), WHITE(7), YELLOW(8);
    
    private int value;
    CardColour(int value) {
        this.value = value;
    }


}
