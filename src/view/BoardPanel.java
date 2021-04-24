package view;

import model.City;
import model.Route;

import javax.swing.*;

/**
 * @author Felix
 */
public class BoardPanel extends JPanel {

    private JLabel gameBoardImage;
    private final City[] cities = null;
    private final Route[] routes = null;
    
    public BoardPanel (City[] cities, Route[] routes) {
       // setBounds(0,0,1400,1000); TODO these aren't the correct dimensions... I just did this for testing
        gameBoardImage = new JLabel(new ImageIcon("./images/board.png"));
        gameBoardImage.setBounds(0,0, this.getWidth(), this.getHeight());
        add(gameBoardImage);
        setVisible(true);
    //TODO uncomment these once file loading works
//        this.cities = cities;
//        this.routes = routes;
//
//        // Add the cities to the game board
//        for (int i = 0; i<this.cities.length; ++i) {
//            gameBoardImage.add(cities[i]);
//        }
//        for (int i = 0; i<this.routes.length; ++i) {
//            gameBoardImage.add(routes[i]);
//        }
        
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
