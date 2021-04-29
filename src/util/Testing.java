package util;

import model.*;
import controller.*;

import java.awt.*;

public class Testing {
    public static Player playerWithCompleteRoute() {
        Player player = new Player("Hi", PlayerColour.BLUE);

        Ticket t = FileImportController.tickets.get(49);
        Route r1 = FileImportController.routes.get(56);
        Route r2 = FileImportController.routes.get(62);

        System.out.println(t);
        System.out.println(r1);
        System.out.println(r2);

        r1.setOwner(player);
        r2.setOwner(player);

        player.addTicket(t);


        return player;
    }
}
