package Application;

import controller.FileImportController;
import controller.RouteController;
import controller.TTRController;
import controller.TicketController;
import model.*;
import view.CardPanel;
import view.GameFrame;
import view.PlayerPanel;;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import static controller.TTRController.players;

public class Main {
    
    public static void main (String[] args) {
        
        new TTRController();
        //        FileImportController.init();
        //        TicketController controller = new TicketController();
        //        Player player = new Player("Hello", PlayerColour.BLUE);
        //
        //        System.out.println(controller.checkTicketComplete(TTRController.tickets.pop(), player));
        
    }
    
}
