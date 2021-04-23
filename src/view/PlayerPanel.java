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
        setLayout(null);
        final int PANEL_PADDING = width/20;
        final int LABEL_HEIGHT = height*3/160;
        final int PREFERRED_WIDTH = width-PANEL_PADDING*2;
        final int HALF_PREFERRED_WIDTH = width/2-PANEL_PADDING;
        int curY = 0;
    
        panelTitle = new JLabel("PLAYER PANEL");
        curY += PANEL_PADDING;
        panelTitle.setBounds(0, curY, width, LABEL_HEIGHT);
        panelTitle.setHorizontalAlignment(JLabel.CENTER);
        add(panelTitle);
    
        playerColour = PlayerColour.RED;
        playerNameTitle = new JLabel("NAME:    Player "+playerColour);
        curY += LABEL_HEIGHT*2;
        playerNameTitle.setBounds(PANEL_PADDING, curY, HALF_PREFERRED_WIDTH, LABEL_HEIGHT);
        playerNameTitle.setHorizontalAlignment(JLabel.CENTER);
        add(playerNameTitle);
    
        playerColourTitle = new JLabel("COLOUR:    "+playerColour.toString());
        playerColourTitle.setBounds(width/2, curY, HALF_PREFERRED_WIDTH, LABEL_HEIGHT);
        playerColourTitle.setHorizontalAlignment(JLabel.CENTER);
        add(playerColourTitle);
        
        ticketTitle = new JLabel("TICKETS");
        curY += LABEL_HEIGHT*2;
        ticketTitle.setBounds(PANEL_PADDING, curY, PREFERRED_WIDTH, LABEL_HEIGHT);
        add(ticketTitle);
        
        // Create the scroll pane
        ticketPaneText = new JTextArea();
        ticketPaneText.setPreferredSize(new Dimension(PREFERRED_WIDTH-18*2, LABEL_HEIGHT*20));
        ticketPane = new JScrollPane(ticketPaneText);
        curY += LABEL_HEIGHT*2;
        ticketPane.setBounds(PANEL_PADDING, curY, PREFERRED_WIDTH-18, LABEL_HEIGHT*15);
        add(ticketPane);
        
        tickets = new ArrayList<>();
        
        // Train Card title
        trainCardTitle = new JLabel("TRAIN CARDS");
        curY += LABEL_HEIGHT*17;
        trainCardTitle.setBounds(PANEL_PADDING, curY, HALF_PREFERRED_WIDTH, LABEL_HEIGHT);
        add(trainCardTitle);
        
        // Create the numTrainCard list
        CardColour[] values = CardColour.values();
        numTrainCards = new TrainCard[values.length];
        for (int i = 0; i<values.length; ++i) {
    
            numTrainCards[i] = new TrainCard(values[i]);
    
            // numTrainCards will contain the number of each card in the label
            numTrainCards[i].setBounds(PANEL_PADDING*3, curY+LABEL_HEIGHT*2*(i+1), PANEL_PADDING*4, LABEL_HEIGHT);
            numTrainCards[i].setHorizontalAlignment(JLabel.RIGHT);
            numTrainCards[i].setText("0");
            add(numTrainCards[i]);
            
            // Create a new JLabel to display the card colour
            JLabel cardColour = new JLabel(values[i].toString());
            cardColour.setBounds(PANEL_PADDING*3, curY+LABEL_HEIGHT*2*(i+1), PANEL_PADDING*2, LABEL_HEIGHT);
            cardColour.setHorizontalAlignment(JLabel.RIGHT);
            add(cardColour);
            
        }
        
        numTrains = 0;
        numTrainsTitle = new JLabel("NUMBER OF TRAINS:    "+numTrains);
        numTrainsTitle.setBounds(width/2, curY, HALF_PREFERRED_WIDTH, LABEL_HEIGHT);
        add(numTrainsTitle);
    
        claimRouteButton = new JButton("CLAIM ROUTE");
        curY += LABEL_HEIGHT*10;
        claimRouteButton.setBounds(width/2, curY, HALF_PREFERRED_WIDTH-18, LABEL_HEIGHT*4);
        add(claimRouteButton);
        
        nextTurnButton = new JButton("NEXT TURN");
        curY += LABEL_HEIGHT*7;
        nextTurnButton.setBounds(width/2, curY, HALF_PREFERRED_WIDTH-18, LABEL_HEIGHT*6);
        add(nextTurnButton);
        
    }
    
}