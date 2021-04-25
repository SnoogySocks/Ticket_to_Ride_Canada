package view;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

/**
 * @author Cerena
 */
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class CardPanel extends JPanel implements ActionListener {

    // Buttons
    JButton ticketDeckButton, cardDeckButton, train1Button, train2Button, train3Button, train4Button, train5Button;

    // Titles
    private JLabel ticketDeckTitle, cardDeckTitle, cardPanelTitle;

    public CardPanel(int x, int y, int width, int height) {

        Border border = BorderFactory.createLineBorder(Color.black, 2);

        setBounds(x, y, width, height);
        setLayout(null);
        setBackground(new Color(245, 239, 245));

        // Adding tiles
        ticketDeckTitle = new JLabel("TICKET DECK");
        ticketDeckTitle.setBounds(30, 0, 100, 30);
        // ticketDeckTitle.setBorder(border);
        add(ticketDeckTitle);

        cardDeckTitle = new JLabel("CARD DECK");
        cardDeckTitle.setBounds(160, 0, 100, 30);
        // cardDeckTitle.setBorder(border);
        add(cardDeckTitle);

        cardPanelTitle = new JLabel("CARD PANEL");
        cardPanelTitle.setBounds(865, 0, 100, 30);
        // cardPanelTitle.setBorder(border);
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

        train1Button = new JButton();
        train1Button.setText("Placeholder :)");
        train1Button.setBounds(340, 10, 125, 90);
        train1Button.setFocusable(false);
        add(train1Button);

        train2Button = new JButton();
        train2Button.setText("Placeholder :)");
        train2Button.setBounds(540, 10, 125, 90);
        train2Button.setFocusable(false);
        add(train2Button);

        train3Button = new JButton();
        train3Button.setText("Placeholder :)");
        train3Button.setBounds(740, 10, 125, 90);
        train3Button.setFocusable(false);
        add(train3Button);

        train4Button = new JButton();
        train4Button.setText("Placeholder :)");
        train4Button.setBounds(940, 10, 125, 90);
        train4Button.setFocusable(false);
        add(train4Button);

        train5Button = new JButton();
        train5Button.setText("Placeholder :)");
        train5Button.setBounds(1140, 10, 125, 90);
        train5Button.setFocusable(false);
        add(train5Button);

    }

    // Train deck function
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ticketDeckButton) {
            System.out.println("your mom");
            new selectTickets();
        }

    }

    public class selectTickets extends JFrame implements ActionListener {

        JLabel selectingTicketsInstructions;
        JButton okButton;
        JCheckBox cb1, cb2, cb3;

        selectTickets() {

            selectingTicketsInstructions = new JLabel("Select at least 1 ticket.");
            selectingTicketsInstructions.setBounds(20,0,400, 60);

            okButton = new JButton("OK");
            okButton.addActionListener(this);
            okButton.setBounds(250,200,50,50);

            cb1 = new JCheckBox("sahhk");
            cb1.setBounds(30,40,400,20);

            cb2 = new JCheckBox("AHHH");
            cb2.setBounds(30,80,400,20);

            cb3 = new JCheckBox("REEEEE");
            cb3.setBounds(30,120,400,20);


            add(okButton);
            add(cb1);
            add(cb2);
            add(cb3);
            add(selectingTicketsInstructions);
            setTitle("Select your train cards");
            setSize(500, 300);
            setLayout(null);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == okButton ) {

            }

        }

    }

}

