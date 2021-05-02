package util;

import model.*;
import controller.*;

import java.awt.*;
import java.util.ArrayList;

/**
 * Basic test utilities
 * @author Nathan, Felix
 */
public class Testing {
    
    public static void BFSIsWorking(TicketController controller) {
//        Player player = new Player("Hi", PlayerColour.BLUE);

        Player player = TTRController.players[0];
        Ticket t = FileImportController.tickets.get(49);
        Route r1 = FileImportController.routes.get(56);
        Route r2 = FileImportController.routes.get(62);

        System.out.println(t);
        System.out.println(r1);
        System.out.println(r2);

        r1.setOwner(player);
        r2.setOwner(player);

        player.addTicket(t);

//        assert(controller.checkTicketComplete(t, player) == true);
//        assert(controller.checkTicketComplete(FileImportController.tickets.get(0), player) == false);
    
        if (controller.checkTicketComplete(t, player)) {
            System.out.println("[TESTING] BFS for tickets test --> Passed");
        } else {
            System.out.println("You fool, you absolute buffoon");
        }
    }
    
    // Here lies hours of debugging
    public static void longestPathIsWorking (RouteController controller) {
        
        BFSIsWorking(TTRController.ticketController);
        ArrayList<Player> playersLongestPathLength = controller.getLongestContinuousPathOwners();
        System.out.println(playersLongestPathLength);
    
    }
    
}
