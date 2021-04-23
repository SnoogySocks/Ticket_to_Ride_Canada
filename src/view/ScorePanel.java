package view;

import util.*;
import javax.swing.*;

/**
 * @author Nathan
 */
public class ScorePanel extends JPanel implements Observer {
    
    public ScorePanel(int x, int y, int width, int height){
        setBounds(x, y, width, height);
    }
    
    public void update(Observable obj, EventType event){
        //once set up this method should be called every time the players' scores are changed
    }
    
}
