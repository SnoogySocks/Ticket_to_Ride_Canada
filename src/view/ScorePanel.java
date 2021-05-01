package view;

import controller.TTRController;
import util.*;
import javax.swing.*;
import java.awt.*;

/**
 * @author Nathan
 */
public class ScorePanel extends JPanel implements Observer {

    private JLabel title = new JLabel("SCORE PANEL");
    private JLabel[] playerScoreLabels = new JLabel[4];
    
    public ScorePanel(int x, int y, int width, int height){
        
        setLayout(null);
        setBounds(x, y, width, height);

        title.setSize(100,25);
        title.setLocation(width/2 - title.getWidth() / 2, 30);
        add(title);

        for(int i = 0; i < 4; i++){
            playerScoreLabels[i] = new JLabel(TTRController.players[i].getName()+": 0");
            playerScoreLabels[i].setBounds(100, 80 + 25 * i, 200,25);
            add(playerScoreLabels[i]);
        }
        
    }
    
    public void update(Observable obj, EventType event){

        //When a player's score is updated, update the GUI as well
        if(event == EventType.UPDATE_SCORES) {
            for (int i = 0; i < TTRController.players.length; i++) {
                playerScoreLabels[i].setText("Player " + (i + 1) + ": " + TTRController.players[i].getScore());
            }
        }
    }
    
}
