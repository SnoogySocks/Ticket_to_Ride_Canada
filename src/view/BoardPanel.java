package view;

import model.City;
import model.Route;

import static controller.FileImportController.*;

import javax.swing.*;

/**
 * @author Felix
 */
public class BoardPanel extends JPanel {
    
    private final JLabel gameBoardImage;
    
    public BoardPanel (int x, int y, int width, int height) {
       
        setBounds(x, y, width, height);
        gameBoardImage = new JLabel(new ImageIcon("./images/board.png"));
        gameBoardImage.setBounds(0,0, this.getWidth(), this.getHeight());
        add(gameBoardImage);

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
    
}
