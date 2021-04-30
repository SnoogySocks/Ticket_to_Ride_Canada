package model;

import javax.swing.*;
import java.io.Serializable;


/**
 * @author Cerena
 */
public class TrainCard extends JLabel implements Serializable {

    private CardColour colour;
    
    public TrainCard(CardColour colour){
        this.colour = colour;
    }
    
    public CardColour getColour () {
        return colour;
    }
    
    public void setColour (CardColour colour) {
        this.colour = colour;
    }
    
}
