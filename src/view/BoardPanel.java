package view;

import model.City;
import model.Route;

import static controller.FileImportController.*;

import javax.swing.*;

/**
 * @author Felix
 */
public class BoardPanel extends JPanel {

    private JLabel gameBoardImage;
    private final City[] cities;
    private final Route[] routes;
    
    public BoardPanel (int x, int y, int width, int height, City[] cities, Route[] routes) {
       
        setBounds(x, y, width, height);
        gameBoardImage = new JLabel(new ImageIcon("./images/board.png"));
        gameBoardImage.setBounds(0,0, this.getWidth(), this.getHeight());
        add(gameBoardImage);
        
        this.cities = cities;
        this.routes = routes;

        // Add the cities to the game board
        for (City city : cities) {
            gameBoardImage.add(city);
        }
        for (Route route : routes) {
            gameBoardImage.add(route);
        }
    
        setVisible(true);
    
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
