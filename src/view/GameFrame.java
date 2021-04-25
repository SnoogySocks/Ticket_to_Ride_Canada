package view;

import model.City;
import model.Route;
import util.*;
import javax.swing.*;

/**
 * @author Nathan
 */
public class GameFrame extends JFrame {

    //Constants
    private static final int WIDTH=1920, HEIGHT=1080;
    
    //Panels
    private BoardPanel boardPanel;
    private ScorePanel scorePanel = new ScorePanel(1400,0,520,180);
    private CardPanel cardPanel = new CardPanel(0,860,1400,180);
    private PlayerPanel playerPanel = new PlayerPanel(1400,180,520,900);
    
    //Menubar components
    
    public GameFrame(City[] cities, Route[] routes){
        boardPanel = new BoardPanel(cities, routes);

        add(boardPanel);
        setSize(WIDTH, HEIGHT);
        setLayout(null);
        add(scorePanel);
        add(cardPanel);
        add(playerPanel);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
