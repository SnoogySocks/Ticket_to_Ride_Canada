package view;

import model.CardColour;
import model.PlayerColour;
import model.Ticket;
import model.TrainCard;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Felix
 */
public class PlayerPanel extends JPanel {
    
    public static final int PANEL_PADDING = 20;
    public static final int LABEL_HEIGHT = 20;
    
    // Titles and player colour
    private PlayerColour playerColour;
    private JLabel panelTitle, playerNameTitle, playerColourTitle, ticketTitle, trainCardTitle, numTrainsTitle;
    
    // Ticket Scroll pane
    private JTextArea ticketPaneText;
    private JScrollPane ticketPane;
    
    // Buttons
    private JButton claimRouteButton, nextTurnButton;
    
    // Lists
    private ArrayList<Ticket> tickets;
    private TrainCard[] numTrainCards;
    private int numTrains;
    
    public PlayerPanel (int x, int y, int width, int height) {
    
        setBounds(x, y, width, height);
        final int PREFERRED_WIDTH = width-PANEL_PADDING*2;
        final int HALF_PREFERRED_WIDTH = width/2-PANEL_PADDING;
        
        playerColour = PlayerColour.RED;
        
        panelTitle = new JLabel("PLAYER PANEL");
        panelTitle.setBounds(0, PANEL_PADDING, width, LABEL_HEIGHT);
        panelTitle.setHorizontalAlignment(JLabel.CENTER);
        add(panelTitle);
    
        playerNameTitle = new JLabel("NAME:    Player "+playerColour);
        playerNameTitle.setBounds(PANEL_PADDING, LABEL_HEIGHT*3, HALF_PREFERRED_WIDTH, LABEL_HEIGHT);
        playerNameTitle.setHorizontalAlignment(JLabel.CENTER);
        add(playerNameTitle);
    
        playerColourTitle = new JLabel("COLOUR:    ");
        playerColourTitle.setBounds(width/2, LABEL_HEIGHT*3, HALF_PREFERRED_WIDTH, LABEL_HEIGHT);
        playerColourTitle.setHorizontalAlignment(JLabel.CENTER);
        add(playerColourTitle);
        
        ticketTitle = new JLabel("TICKETS");
        ticketTitle.setBounds(PANEL_PADDING, LABEL_HEIGHT*5, PREFERRED_WIDTH, LABEL_HEIGHT);
        add(ticketTitle);
        
        // Create the scroll pane
        ticketPaneText = new JTextArea();
        ticketPaneText.setPreferredSize(new Dimension(PREFERRED_WIDTH, PREFERRED_WIDTH/2));
        ticketPane = new JScrollPane(ticketPaneText);
        ticketPane.setBounds(PANEL_PADDING, LABEL_HEIGHT*7, PREFERRED_WIDTH, LABEL_HEIGHT*11);
        add(ticketPane);
        
        tickets = new ArrayList<>();
        
        // Train Card title
        trainCardTitle = new JLabel("TRAIN CARDS");
        trainCardTitle.setBounds(PANEL_PADDING, LABEL_HEIGHT*18, HALF_PREFERRED_WIDTH, LABEL_HEIGHT);
        
        // Create the numTrainCard list
        CardColour[] values = CardColour.values();
        numTrainCards = new TrainCard[values.length];
        for (int i = 0; i<values.length; ++i) {
    
            numTrainCards[i] = new TrainCard(values[i]);
            numTrainCards[i].setBounds(PANEL_PADDING*4, LABEL_HEIGHT*20+LABEL_HEIGHT*i*2, width/2-PANEL_PADDING*4, LABEL_HEIGHT);
            numTrainCards[i].setText(String.format("%-11s0", values[i].toString()));
            
            
            add(numTrainCards[i]);
            
        }
        
        numTrainsTitle = new JLabel("NUMBER OF TRAINS");
        
        
        
    }
    
}