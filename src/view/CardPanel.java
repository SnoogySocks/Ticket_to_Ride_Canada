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
        add(cardDeckButton);
        
        for (int i = 1; i<=5; i++) {
            trainButtons[i-1] = new JButton();
            JButton trainButton = trainButtons[i-1];
            //  trainButton.setText("Placeholder :)");
            trainButton.setBounds(buttonStartX+buttonStartX/2+buttonWidth+buttonSpacing*i, buttonY, buttonWidth, buttonHeight);
            trainButton.setFocusable(false);
            add(trainButton);
        }
        
    }
    
    // Train deck function
    public void actionPerformed (ActionEvent e) {
        if (e.getSource()==ticketDeckButton) {
            TicketController controller = new TicketController(); // TODO remove after testing
            controller.showTicketSelectionDialogue();
            
        }
    }
    
    @Override
    public void update (Observable obj, EventType event) {
        
        if (event==EventType.UPDATE_SHOWN_CARDS) {
            
            //here lies an hour of painful debugging... we forgot to pass something
            for (int i = 0; i<5; i++) {
                trainButtons[i].setIcon(new ImageIcon(TTRController.shownCards.get(i).getColour().getImagePath()));
            }
            
        }
        
    }
    
    //    public class SelectTickets extends JFrame implements ActionListener {
    //
    //        private JLabel selectingTicketsInstructions;
    //        private JButton okButton;
    //        private JCheckBox cb1, cb2, cb3;
    //
    //        public SelectTickets () {
    //
    //            selectingTicketsInstructions = new JLabel("Select at least 1 ticket.");
    //            selectingTicketsInstructions.setBounds(20,0,400, 60);
    //
    //            okButton = new JButton("OK");
    //            okButton.addActionListener(this);
    //            okButton.setBounds(250,200,50,50);
    //
    //            cb1 = new JCheckBox("sahhk");
    //            cb1.setBounds(30,40,400,20);
    //
    //            cb2 = new JCheckBox("AHHH");
    //            cb2.setBounds(30,80,400,20);
    //
    //            cb3 = new JCheckBox("REEEEE");
    //            cb3.setBounds(30,120,400,20);
    //
    //            add(okButton);
    //            add(cb1);
    //            add(cb2);
    //            add(cb3);
    //            add(selectingTicketsInstructions);
    //            setTitle("Select your train cards");
    //            setSize(500, 300);
    //            setLayout(null);
    //            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    //            setVisible(true);
    //        }
    //
    //        @Override
    //        public void actionPerformed(ActionEvent e) {
    //            if (e.getSource() == okButton) {
    //
    //            }
    //        }
    //
    //    }
    
}

