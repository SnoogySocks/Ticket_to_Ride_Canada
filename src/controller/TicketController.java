package controller;

import model.Player;
import model.Route;
import model.Ticket;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

/**
 * @author Nathan
 */
public class TicketController {



    public void showTicketSelectionDialogue() {
        int size = Math.min(TTRController.tickets.size(), 3);

        if (size <= 0) {
            JOptionPane.showMessageDialog(null, "No tickets left in the ticket stack!");
            return;
        }

        Ticket[] choices = new Ticket[size];

        for (int i = 0; i < size; i++) {
            choices[i] = TTRController.tickets.pop();
        }

        new TicketDialogue(choices, null); //TODO require player to be passed as a param
    }

    public void checkTicketComplete(Ticket ticket, Player owner) {


        Queue<Route> queue = new LinkedList<>();

        while(!queue.isEmpty()){
            Route next = queue.poll();


        }


        //run BFS or DFS to see if the ticket is complete then set its complete field to true
    }

    public void scoreTickets(Player[] players) {
        //run isTicketComplete() for each ticket that the player owns?
    }

    /**
     * @author Nathan Wong, Cerena
     */
    protected class TicketDialogue extends JFrame implements ActionListener {

        private JLabel selectingTicketsInstructions;
        private JButton okButton;
        private Player player;
        private JCheckBox[] checkBoxes = new JCheckBox[3];
        private Ticket[] choices;


        public TicketDialogue(Ticket[] choices, Player player) {

            this.player = player;
            this.choices = choices;

            selectingTicketsInstructions = new JLabel("Select at least 1 ticket.");
            selectingTicketsInstructions.setBounds(20, 0, 400, 60);
            add(selectingTicketsInstructions);

            okButton = new JButton("OK");
            okButton.addActionListener(this);
            okButton.setBounds(250, 200, 100, 50);
            add(okButton);

            for (int i = 0; i < 3; i++) {
                checkBoxes[i] = new JCheckBox(choices[i].toString());
                checkBoxes[i].setBounds(30, 40 + 40 * i, 500, 20);
                add(checkBoxes[i]);
            }

            setTitle("Select your train cards");
            setSize(600, 300);
            setLayout(null);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setVisible(true);
        }


        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == okButton) {
                int numSelected = 0;
                for (JCheckBox cb : checkBoxes) {
                    if (cb.isSelected()) {
                        numSelected++;
                    }
                }

                if (numSelected < 1) {
                    JOptionPane.showMessageDialog(null, "You must choose at least 1 ticket!");
                } else {

                    for (int i = 0; i < 3; i++) {
                        if (checkBoxes[i].isSelected()) {
                            System.out.println(choices[i]);
                            //player.addTicket(choices[i]); TODO uncomment when players are implemented
                        } else {
                            //place unselected tickets at the bottom of the stack
                            TTRController.tickets.insertElementAt(choices[i], TTRController.tickets.size() - 1);
                        }
                    }

                    setVisible(false);
                    dispose();
                }
            }
        }
    }
}
