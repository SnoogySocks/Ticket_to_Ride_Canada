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

        player.getClaimedRoutes().add(r1);
        player.getClaimedRoutes().add(r2);
        player.addTicket(t);


        return player;
    }
}
