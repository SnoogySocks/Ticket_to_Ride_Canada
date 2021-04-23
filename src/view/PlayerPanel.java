package view;

import model.PlayerColour;

import javax.swing.*;
import java.awt.*;

/**
 * @author Felix
 */
public class PlayerPanel extends JPanel {
    
    private JLabel panelTitle, playerNameTitle, playerColourTitle, ticketTitle, trainCardTitle, numTrainsTitle;
    private PlayerColour playerColour;
    
    public PlayerPanel (int x, int y, int width, int height) {
    
        playerColour = PlayerColour.RED;
        panelTitle = new JLabel("PLAYER PANEL");
        playerNameTitle = new JLabel("NAME:       ");
        playerColourTitle = new JLabel("COLOUR:");
        ticketTitle = new JLabel("TICKETS");
        trainCardTitle = new JLabel("TRAIN CARDS");
        numTrainsTitle = new JLabel("NUMBER OF TRAINS");
        setBounds(x, y, width, height);
        
    }
    
    
    
}
