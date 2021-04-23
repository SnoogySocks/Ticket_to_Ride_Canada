package view;

import model.City;
import model.Route;

import javax.swing.*;
import java.awt.*;

/**
 * @author Felix
 */
public class BoardPanel extends JPanel {

    private JLabel gameBoardImage;
    private City[] cities;
    private Route[] routes;
    
    public BoardPanel (City[] cities, Route[] routes) {
        
        this.gameBoardImage = new JLabel("images/images/board.png");
        this.cities = cities;
        this.routes = routes;
        
        // Add the cities to the game board
        
        
    }
    
}
