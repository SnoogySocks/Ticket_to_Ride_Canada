package controller;

import model.Player;
import model.Ticket;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

/**
 * @author Nathan
 */
public class TicketController {
    
    private Stack<Ticket> tickets;
    
    public TicketController(){
        tickets = (Stack<Ticket>)FileImportController.tickets.clone();
    }

    public void showTicketSelectionDialogue(){
        Ticket[] choices = new Ticket[3];
        for(int i = 0 ; i < 3; i++){
            choices[i] = tickets.pop();
        }
        TicketDialogue td = new TicketDialogue(choices);


    }
    
    public void checkTicketComplete(Ticket ticket, Player owner){
        
        //run BFS or DFS to see if the ticket is complete then set its complete field to true
    }
    
    public void scoreTickets(Player[] players) {
        //run isTicketComplete() for each ticket that the player owns?
    }

    /**
     * @author Nathan Wong, Cerena
     */
    public class TicketDialogue extends JFrame implements ActionListener{

        private JLabel selectingTicketsInstructions;
        private JButton okButton;

        JCheckBox[] checkBoxes = new JCheckBox[3];
        Ticket[] choices;


        public TicketDialogue (Ticket[] choices) {

            this.choices = choices;
            selectingTicketsInstructions = new JLabel("Select at least 1 ticket.");
            selectingTicketsInstructions.setBounds(20,0,400, 60);
            add(selectingTicketsInstructions);

            okButton = new JButton("OK");
            okButton.addActionListener(this);
            okButton.setBounds(250,200,50,50);
            add(okButton);

            for(int i = 0 ; i < 3; i++){
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
            if(e.getSource() == okButton){
                int numSelected = 0;
                for(JCheckBox cb : checkBoxes){
                    if(cb.isSelected()){
                        numSelected++;
                    }
                }

                if(numSelected < 1){
                    JOptionPane.showMessageDialog(null, "You must choose at least 1 ticket!");
                } else {
                    setVisible(false);
                    dispose();
                }
            }
        }
    }
}
