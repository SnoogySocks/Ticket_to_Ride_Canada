package view;

/**
 * @author Cerena
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

    public CardPanel(int x, int y, int width, int height) {
        
        setBounds(x, y, width, height);
        setLayout(null);
        setBackground(new Color(245, 239, 245));

        // Adding tiles
        ticketDeckTitle = new JLabel("TICKET DECK");
        ticketDeckTitle.setBounds(30, 0, 100, 30);
        add(ticketDeckTitle);

        cardDeckTitle = new JLabel("CARD DECK");
        cardDeckTitle.setBounds(160, 0, 100, 30);
        add(cardDeckTitle);

        cardPanelTitle = new JLabel("CARD PANEL");
        cardPanelTitle.setBounds(865, 0, 100, 30);
        add(cardPanelTitle);

        // Add Images
        ImageIcon ticketDeckImage = new ImageIcon("./images/ticketDeck.png");
        ImageIcon cardDeckImage = new ImageIcon("./images/cardBack.png");

        // Buttons
        ticketDeckButton = new JButton(ticketDeckImage);
        ticketDeckButton.setBounds(10, 10, 125, 100);
        ticketDeckButton.setFocusable(false);
        ticketDeckButton.addActionListener(this);
        add(ticketDeckButton);

        cardDeckButton = new JButton(cardDeckImage);
        cardDeckButton.setBounds(140, 10, 125, 100);
        cardDeckButton.setFocusable(false);
        add(cardDeckButton);
        
        for(int i = 1; i <= 5; i++){
            trainButtons[i - 1]  = new JButton();
            JButton trainButton = trainButtons[i-1];
          //  trainButton.setText("Placeholder :)");
            trainButton.setBounds(140 + 200 * i, 10, 125, 90);
            trainButton.setFocusable(false);
            add(trainButton);
        }

//        train1Button = new JButton();
//        train1Button.setText("Placeholder :)");
//        train1Button.setBounds(340, 10, 125, 90);
//        train1Button.setFocusable(false);
//        add(train1Button);
//
//        train2Button = new JButton();
//        train2Button.setText("Placeholder :)");
//        train2Button.setBounds(540, 10, 125, 90);
//        train2Button.setFocusable(false);
//        add(train2Button);
//
//        train3Button = new JButton();
//        train3Button.setText("Placeholder :)");
//        train3Button.setBounds(740, 10, 125, 90);
//        train3Button.setFocusable(false);
//        add(train3Button);
//
//        train4Button = new JButton();
//        train4Button.setText("Placeholder :)");
//        train4Button.setBounds(940, 10, 125, 90);
//        train4Button.setFocusable(false);
//        add(train4Button);
//
//        train5Button = new JButton();
//        train5Button.setText("Placeholder :)");
//        train5Button.setBounds(1140, 10, 125, 90);
//        train5Button.setFocusable(false);
//        add(train5Button);

    }

    // Train deck function
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ticketDeckButton) {
            TicketController controller = new TicketController(); // TODO remove after testing
            controller.showTicketSelectionDialogue();
            
        }
    }
    
    @Override
    public void update (Observable obj, EventType event) {
        
        if(event == EventType.UPDATE_SHOWN_CARDS){
            
            //here lies an hour of painful debugging... we forgot to pass something
            for(int i = 0; i < 5; i++){
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

