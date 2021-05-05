package view;

import controller.TTRController;
import model.*;
import util.Observer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import util.*;

/**
 * @author Felix
 */
public class PlayerPanel extends JPanel implements Observer {
    
    // Titles and player colour
    private final JLabel panelTitle, playerNameTitle, playerColourTitle, ticketTitle, trainCardTitle, numTrainsTitle;
    
    // Ticket Scroll pane
    private final JTextArea ticketPaneText;
    private final JScrollPane ticketPane;
    
    // Buttons
    private final JButton claimRouteButton, nextTurnButton;
    
    // Lists
    private final ArrayList<Ticket> tickets;
    private final TrainCard[] numTrainCards;
    
    public PlayerPanel (int x, int y, int width, int height) {
        
        setBounds(x, y, width, height);
        setLayout(null);
        
        final int PANEL_PADDING = width/20;
        final int LABEL_HEIGHT = height*3/160;
        final int PREFERRED_WIDTH = width-PANEL_PADDING*2;
        final int HALF_PREFERRED_WIDTH = PREFERRED_WIDTH/2;
        int curY = 0;
        
        panelTitle = new JLabel("PLAYER PANEL");
        curY += PANEL_PADDING;
        panelTitle.setBounds(0, curY, width, LABEL_HEIGHT);
        panelTitle.setHorizontalAlignment(JLabel.CENTER);
        add(panelTitle);
        
        playerNameTitle = new JLabel();
        curY += LABEL_HEIGHT*2;
        playerNameTitle.setBounds(PANEL_PADDING, curY, HALF_PREFERRED_WIDTH, LABEL_HEIGHT);
        playerNameTitle.setHorizontalAlignment(JLabel.CENTER);
        add(playerNameTitle);
        
        playerColourTitle = new JLabel();
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
        ticketPaneText.setEditable(false);
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
        numTrainCards = new TrainCard[values.length-1];
        for (int i = 0; i<numTrainCards.length; ++i) {
            
            numTrainCards[i] = new TrainCard(values[i]);
            
            // numTrainCards will contain the number of each card in the label
            numTrainCards[i].setBounds(PANEL_PADDING*3, curY+LABEL_HEIGHT*2*(i+1), PANEL_PADDING*4, LABEL_HEIGHT);
            numTrainCards[i].setHorizontalAlignment(JLabel.RIGHT);
            add(numTrainCards[i]);
            
            // Create a new JLabel to display the card colour
            JLabel cardColour = new JLabel(values[i].toString());
            cardColour.setBounds(PANEL_PADDING*2, curY+LABEL_HEIGHT*2*(i+1), PANEL_PADDING*4, LABEL_HEIGHT);
            cardColour.setHorizontalAlignment(JLabel.RIGHT);
            add(cardColour);
            
        }
        
        numTrainsTitle = new JLabel();
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
    
    public void updateCurrentPlayer (Player currentPlayer) {
        
        playerNameTitle.setText("NAME:    Player "+currentPlayer.getPlayerColour().getValue());
        playerColourTitle.setText("COLOUR:    "+currentPlayer.getPlayerColour());
        updateTrains();
        
    }
    
    public void updateTrains () {
        
        numTrainsTitle.setText("NUMBER OF TRAINS:    "+TTRController.getCurrentPlayer().getNumTrains());
        for (int i = 0; i<numTrainCards.length; ++i) {
            numTrainCards[i].setText(Integer.toString(TTRController.getCurrentPlayer().getNumCardsOfColour(i)));
        }
        
    }
    
    public JButton getClaimRouteButton () {
        return claimRouteButton;
    }
    
    public JTextArea getTicketPaneText () {
        return ticketPaneText;
    }
    
    public JScrollPane getTicketPane () {
        return ticketPane;
    }
    
    public JButton getNextTurnButton () {
        return nextTurnButton;
    }
    
    public ArrayList<Ticket> getTickets () {
        return tickets;
    }
    
    public TrainCard[] getNumTrainCards () {
        return numTrainCards;
    }
    
    private void updateTickets () {
        ticketPaneText.setText("");
        for (Ticket t : TTRController.getCurrentPlayer().getTickets()) {
            ticketPaneText.append(t.toString()+"\n");
        }
    }
    
    @Override
    public void update (Observable obj, EventType event) {
        
        switch (event) {
            case UPDATE_TRAINS: updateTrains(); break;
            case UPDATE_TICKETS: updateTickets(); break;
            case LOCK_CONTROLS: case CARD_TAKEN:
                claimRouteButton.setEnabled(false); break;
            case NEXT_TURN:
                updateCurrentPlayer(TTRController.getCurrentPlayer());
                updateTickets();
                claimRouteButton.setEnabled(true);
                break;
        }
        
    }
    
}