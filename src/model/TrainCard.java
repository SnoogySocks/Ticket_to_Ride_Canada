package model;

import javax.swing.*;


/**
 * @author Cerena
 */
public class TrainCard extends JLabel {

    private RouteColour colour;
    
    public TrainCard(RouteColour colour){
        this.colour = colour;
    }
    
    public RouteColour getColour () {
        return colour;
    }
    
    public void setColour (RouteColour colour) {
        this.colour = colour;
    }
    
}
