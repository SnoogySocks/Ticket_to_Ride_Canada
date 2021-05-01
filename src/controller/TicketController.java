package controller;

import model.City;
import model.Player;
import model.Route;
import model.Ticket;
import util.EventType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

/**
 * @author Nathan
 */
public class TicketController {
    
    /**
     * At the start of the game - deals out three ticket choices for each player
     */
    public void dealStartingTickets () {
        for (Player player : TTRController.players) {
            showTicketSelectionDialogue();
            TTRController.nextTurn();
        }
    }
    
    private void renderDialogue (Ticket[] choices) {
        
        boolean hasSelected = false;
        int size = choices.length;
        
        //While the user has not given valid input (chosen at least 1 ticket)
        while (!hasSelected) {
            
            //Initialize GUI elements for the JOptionPane
            JCheckBox[] checkBoxes = new JCheckBox[size];
            Object[] parameters = { TTRController.getCurrentPlayer().getName()+" select at least 1 ticket! ", checkBoxes };
            
            for (int i = 0; i<size; i++) {
                checkBoxes[i] = new JCheckBox(choices[i].toString());
            }
            
            //Show options
            JOptionPane.showMessageDialog(TTRController.frame, parameters);
            
            //Count the number of selected tickets
            int numSelected = 0;
            for (JCheckBox cb : checkBoxes) {
                if (cb.isSelected()) {
                    numSelected++;
                }
            }
            
            //Restart the process if a ticket hasn't been selected
            if (numSelected<1) {
                int i = JOptionPane.showConfirmDialog(TTRController.frame, "You must choose at least 1 ticket.", "Continue?", JOptionPane.OK_CANCEL_OPTION);
                
                if (i == 2){
                    return;
                }
            } else {
                //Add the selected tickets to the player
                for (int i = 0; i<size; i++) {
                    if (checkBoxes[i].isSelected()) {
                        TTRController.getCurrentPlayer().addTicket(choices[i]);
                    } else {
                        //place unselected tickets at the bottom of the stack
                        TTRController.tickets.insertElementAt(choices[i], TTRController.tickets.size()-1);
                    }
                }
                hasSelected = true;
            }
        }
        
        TTRController.notifyStaticObservers(EventType.LOCK_CONTROLS);
        
    }
    
    /**
     * Shows the player 3 tickets that they must pick from in a JOptionPane
     */
    public void showTicketSelectionDialogue () {
        
        int size = Math.min(TTRController.tickets.size(), 3);
        
        if (size<=0) {
            JOptionPane.showMessageDialog(TTRController.frame, "No tickets left in the ticket stack!");
            return;
        }
        
        Ticket[] choices = new Ticket[size];
        
        //Initialize the tickets by popping them off the stack
        for (int i = 0; i<size; i++) {
            choices[i] = TTRController.tickets.pop();
        }
        
        //Call renderDialogue to show the JOptionPane
        renderDialogue(choices);
        
    }
    
    /**
     * @param ticket ticket to be checked for completion
     * @param owner  owner of the ticket
     * @return whether there is a route connecting the two cities in the ticket
     */
    public boolean checkTicketComplete (Ticket ticket, Player owner) {
        
        //nodes - cities, edges - routes
        //start - city1, destination - city2
        
        HashMap<City, Boolean> explored = new HashMap<>();
        City destination = ticket.getCity2();
        
        for (City c : FileImportController.cities) {
            explored.put(c, false);
        }
        
        Queue<City> queue = new LinkedList<>();
        queue.add(ticket.getCity1());
        
        //Basic implementation of BFS
        while (!queue.isEmpty()) {
            
            //Get the next city in the queue and mark it explored
            City current = queue.poll();
            explored.replace(current, true);
            
            for (Route r : current.generateOwnedRoutes(owner)) {
                //switch depending on which city is the destination
                City c1 = r.getCity1();
                City c2 = r.getCity2();
                
                //If any of the cities are the destination then the ticket is complete
                if (c1.equals(destination) || c2.equals(destination)) {
                    ticket.setCompleted(true);
                    return true;
                }
                
                //Add the connecting city to the queue
                if (!c1.equals(current) && !explored.get(c1)) {
                    queue.add(c1);
                } else if (!explored.get(c2)) {
                    queue.add(c2);
                }
            }
        }
        
        return false;
    }
    
    /**
     * Scores the tickets every time a player adds a route
     */
    public void scoreTicketsOnRouteAdded (Player player) {
        for (Ticket ticket : player.getTickets()) {
            if (!ticket.isCompleted()) {
                checkTicketComplete(ticket, player);
            }
        }
    }
    
    /**
     * Applies ticket points to the players at the end of each game
     */
    public void scoreTicketsEndGame () {
        for (Player player : TTRController.players) {
            for (Ticket t : player.getTickets()) {
                if (t.isCompleted()) {
                    player.setScore(player.getScore()+t.getVal());
                }
            }
        }
    }
}
