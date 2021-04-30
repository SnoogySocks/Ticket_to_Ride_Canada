package model;

import java.io.Serializable;

/**
 * @author Cerena
 */
public enum CardColour implements Serializable {
    
    RAINBOW(0, "./images/trainCardRainbow.png"),
    BLACK(1, "./images/trainCardBlack.png"),
    BLUE(2, "./images/trainCardBlue.png"),
    GREEN(3, "./images/trainCardGreen.png"),
    ORANGE(4, "./images/trainCardOrange.png"),
    PURPLE(5, "./images/trainCardPurple.png"),
    RED(6, "./images/trainCardRed.png"),
    WHITE(7, "./images/trainCardWhite.png"),
    YELLOW(8, "./images/trainCardYellow.png");
    
    private int value;
    private String imgPath;
    
    CardColour (int value, String imgPath) {
        this.value = value;
        this.imgPath = imgPath;
    }
    
    public int getValue () {
        return value;
    }
    
    public String getImagePath () {
        return imgPath;
    }
    
}
