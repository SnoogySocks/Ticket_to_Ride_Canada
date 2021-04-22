package model;

import javax.swing.*;

/**
 * @author Cerena
 */
public class TrainCard extends JLabel {

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
