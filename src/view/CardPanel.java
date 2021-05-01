package view;

/**
 *
 */

import controller.TicketController;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import util.*;
import controller.*;

public class CardPanel extends JPanel implements ActionListener, Observer {
    
    // Buttons
    private JButton ticketDeckButton, cardDeckButton, train1Button, train2Button, train3Button, train4Button, train5Button;
    
    private JButton[] trainButtons = new JButton[5];
    
    // Titles
    private JLabel ticketDeckTitle, cardDeckTitle, cardPanelTitle;
    
    public CardPanel (int x, int y, int width, int height) {
        
        setBounds(x, y, width, height);
        setLayout(null);
        setBackground(new Color(245, 239, 245));
        
        final int titleY = 20;
        
        final int buttonWidth = 125;
        final int buttonHeight = 75;
        final int buttonStartX = 50;
        final int buttonY = height/2-buttonHeight/2;
        final int buttonSpacing = buttonWidth+80;
        
        // Adding tiles
        ticketDeckTitle = new JLabel("TICKET DECK");
        ticketDeckTitle.setBounds(buttonStartX+buttonStartX/2, titleY, 100, 30);
        add(ticketDeckTitle);
        
        cardDeckTitle = new JLabel("CARD DECK");
        cardDeckTitle.setBounds(buttonStartX*2+buttonWidth, titleY, 100, 30);
        add(cardDeckTitle);
        
        cardPanelTitle = new JLabel("CARD PANEL");
        cardPanelTitle.setBounds(buttonStartX*2+buttonWidth+buttonSpacing*3, titleY, 100, 30);
        add(cardPanelTitle);
        
        // Add Images
        ImageIcon ticketDeckImage = new ImageIcon("./images/ticketDeck.png");
        ImageIcon cardDeckImage = new ImageIcon("./images/cardBack.png");
        
        // Buttons
        ticketDeckButton = new JButton(ticketDeckImage);
        ticketDeckButton.setBounds(buttonStartX, buttonY, buttonWidth, buttonHeight);
        ticketDeckButton.setFocusable(false);
        ticketDeckButton.addActionListener(this);
        add(ticketDeckButton);
        
        cardDeckButton = new JButton(cardDeckImage);
        cardDeckButton.setBounds(buttonStartX+buttonStartX/2+buttonWidth, buttonY, buttonWidth, buttonHeight);
        cardDeckButton.setFocusable(false);
        cardDeckButton.addActionListener(this);
        add(cardDeckButton);
        
        for (int i = 1; i<=5; i++) {
            trainButtons[i-1] = new JButton();
            JButton trainButton = trainButtons[i-1];
            //  trainButton.setText("Placeholder :)");
            trainButton.setBounds(buttonStartX+buttonStartX/2+buttonWidth+buttonSpacing*i, buttonY, buttonWidth, buttonHeight);
            trainButton.setFocusable(false);
            trainButton.addActionListener(this);
            add(trainButton);
        }
        
    }
    
    public JButton getTicketDeckButton () {
        return ticketDeckButton;
    }
    
    public JButton getCardDeckButton () {
        return cardDeckButton;
    }
    
    public JButton[] getTrainButtons () {
        return trainButtons;
    }
    
    public JLabel getTicketDeckTitle () {
        return ticketDeckTitle;
    }
    
    public JLabel getCardDeckTitle () {
        return cardDeckTitle;
    }
    
    public JLabel getCardPanelTitle () {
        return cardPanelTitle;
    }
    
    @Override
    public void actionPerformed (ActionEvent e) {
        
        if (e.getSource()==ticketDeckButton) {
            TTRController.ticketController.showTicketSelectionDialogue();
            //        } else if (e.getSource()==cardDeckButton) {
            //            TTRController.ticketController
        }
        
        if (e.getSource()==cardDeckButton) {
            TTRController.tCController.giveDeckCard();
        }
        
        for (int i = 0; i<trainButtons.length; i++) {
            JButton btn = trainButtons[i];
            if (e.getSource()==btn) {
                TTRController.tCController.giveShownCard(i);
            }
        }
        
    }
    
    @Override
    public void update (Observable obj, EventType event) {
        
        switch (event) {
            case UPDATE_SHOWN_CARDS:
                //here lies an hour of painful debugging... we forgot to pass something into the constructor
                for (int i = 0; i<5; i++) {
                    trainButtons[i].setIcon(new ImageIcon(TTRController.shownCards.get(i).getColour().getImagePath()));
                }
                break;
            case CARD_TAKEN:
                ticketDeckButton.setEnabled(false);
                break;
            case LOCK_CONTROLS:
                setButtonsEnabled(false);
                break;
            case NEXT_TURN:
                setButtonsEnabled(true);
                break;
        }
        
    }
    
    private void setButtonsEnabled (boolean isEnabled) {
        for (int i = 0; i<5; i++) {
            trainButtons[i].setEnabled(isEnabled);
        }
        cardDeckButton.setEnabled(isEnabled);
        ticketDeckButton.setEnabled(isEnabled);
    }
    
}

