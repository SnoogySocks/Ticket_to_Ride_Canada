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
        
        gameBoardImage = new JLabel("images/board.png");
        add(gameBoardImage);
    
        this.cities = cities;
        this.routes = routes;
        
        // Add the cities to the game board
        for (int i = 0; i<this.cities.length; ++i) {
            gameBoardImage.add(cities[i]);
        }
        for (int i = 0; i<this.routes.length; ++i) {
            gameBoardImage.add(routes[i]);
        }
        
    }
    
    public JLabel getGameBoardImage () {
        return gameBoardImage;
    }
    
    public void setGameBoardImage (JLabel gameBoardImage) {
        this.gameBoardImage = gameBoardImage;
    }
    
    public City getCity (int index) {
        return cities[index];
    }
    
    public Route getRoute (int index) {
        return routes[index];
    }
    
}
