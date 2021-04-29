package controller;

import model.City;
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
    public void dealStartingTickets() {
        for (Player player : TTRController.players) {
            showTicketSelectionDialogue();
            TTRController.nextTurn();
        }
    }

    public void renderDialogue(Ticket[] choices) {
        boolean hasSelected = false;
        int size = choices.length;

        while (!hasSelected) {
            JCheckBox[] checkBoxes = new JCheckBox[size];
            Object[] parameters = {TTRController.getCurrentPlayer().getName() + " select at least 1 ticket! ", checkBoxes};

            for (int i = 0; i < size; i++) {
                checkBoxes[i] = new JCheckBox(choices[i].toString());
            }

            JOptionPane.showMessageDialog(null, parameters);

            int numSelected = 0;
            for (JCheckBox cb : checkBoxes) {
                if (cb.isSelected()) {
                    numSelected++;
                }
            }

            if (numSelected < 1) {
                JOptionPane.showMessageDialog(null, "You must choose at least 1 ticket!");
            } else {
                for (int i = 0; i < size; i++) {
                    if (checkBoxes[i].isSelected()) {
                        System.out.println(choices[i]);
                        TTRController.getCurrentPlayer().addTicket(choices[i]);
                    } else {
                        //place unselected tickets at the bottom of the stack
                        TTRController.tickets.insertElementAt(choices[i], TTRController.tickets.size() - 1);
                    }
                }

                hasSelected = true;
            }

        }
    }

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

        renderDialogue(choices);
    }

    public boolean checkTicketComplete(Ticket ticket, Player owner) {

        //nodes - cities, edges - routes
        //start - city1, destination - city2

        System.out.println("Hello World");

        HashMap<City, Boolean> explored = new HashMap<>();
        City destination = ticket.getCity2();

        for (City c : FileImportController.cities) {
            explored.put(c, false);
        }

        Queue<City> queue = new LinkedList<>();
        queue.add(ticket.getCity1());

        //TODO this implementation of BFS hasn't been tested... idk if it works
        while (!queue.isEmpty()) {
            City current = queue.poll();
            explored.replace(current, true);

            for (Route r : current.generateOwnedRoutes(owner)) {
                //switch depending on which city is the destination
                City c1 = r.getCity1();
                City c2 = r.getCity2();

                if (c1.equals(destination) || c2.equals(destination)) {
                    ticket.setCompleted(true);
                    return true;
                }

                if (!c1.equals(current) && !explored.get(c1)) {
                    queue.add(c1);
                } else if (!explored.get(c2)) {
                    queue.add(c2);
                }
            }
        }

        return false;

        //run BFS or DFS to see if the ticket is complete then set its complete field to true
    }

    public void scoreTickets(Player[] players) {
        for (Player player : players) {
            for (Ticket ticket : player.getTickets()) {
                if (checkTicketComplete(ticket, player)) {
                    player.setScore(player.getScore() + ticket.getVal());
                } else {
                    player.setScore(player.getScore() - ticket.getVal());
                }
            }
        }
        //run isTicketComplete() for each ticket that the player owns?
    }
}
