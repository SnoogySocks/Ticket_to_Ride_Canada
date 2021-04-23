package view;

import model.City;
import model.Route;
import util.*;
import javax.swing.*;

/**
 * @author Nathan
 */
public class GameFrame extends JFrame {
    
    //Panels
    BoardPanel boardPanel;
    ScorePanel scorePanel = new ScorePanel(1400,0,520,180);
    CardPanel cardPanel = new CardPanel(0,900,1400,180);
    PlayerPanel playerPanel = new PlayerPanel(1400,180,520,900);
    
    //Menubar components
    
    public GameFrame(City[] cities, Route[] routes){
        boardPanel = new BoardPanel(cities, routes);
    }
}
