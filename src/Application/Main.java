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
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;

import static controller.TTRController.players;

public class Main {
    
    public static void main (String[] args) {
        
//        new TTRController();
        NumberFormatter formatter = new NumberFormatter(NumberFormat.getInstance());
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
//        // If you want the value to be committed on each keystroke instead of focus lost
//        formatter.setCommitsOnValidEdit(true);
        
        JFormattedTextField username = new JFormattedTextField(formatter);
        JFormattedTextField password = new JFormattedTextField(formatter);
        Object[] message = {
                "Username:", username,
                "Password:", password
        };
    
        int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            if (username.getText().equals("h") && password.getText().equals("h")) {
                System.out.println("Login successful");
            } else {
                System.out.println("login failed");
            }
        } else {
            System.out.println("Login canceled");
        }
        
    }
    
}
