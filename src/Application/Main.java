package Application;

import controller.FileImportController;
import controller.TTRController;
import model.CardColour;
import view.CardPanel;
import view.GameFrame;
import view.PlayerPanel;

import javax.swing.*;
import java.awt.*;

public class Main {
    
    public static void main (String[] args) {
    
        // Testing
//        JFrame frame = new JFrame();
//        int width = 400, height = 800;
//        frame.setSize(width, height);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        PlayerPanel playerPanel = new PlayerPanel(0, 0, width, height);
//        frame.add(playerPanel);

//        frame.setVisible(true);
//        GameFrame frame = new GameFrame(null, null);
        
        TTRController controller = new TTRController();
        
        System.out.println(FileImportController.cities);
        System.out.println(FileImportController.routes);
        System.out.println(FileImportController.tickets);
    
    }
    
}
