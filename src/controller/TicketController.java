package controller;

import model.Player;
import model.Ticket;
import java.util.*;

/**
 * @author Nathan
 */
public class TicketController {
    
    private Stack<Ticket> tickets;
    
    public TicketController(){
        Collections.copy(tickets, FileImportController.tickets);
    }
    
    public void checkTicketComplete(Ticket ticket, Player owner){
        
        //run BFS or DFS to see if the ticket is complete then set its complete field to true
    }
    
    public void scoreTickets(Player[] players) {
        //run isTicketComplete() for each ticket that the player owns?
    }
}
