package view;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

/**
 * @author Cerena
 */
public class CardPanel extends JPanel{
    
    JLabel cardLabel = new JLabel();
    
    public CardPanel(int x, int y, int width, int height) {
        cardLabel.setBounds(x,y,width,height);
        cardLabel.setBackground(ColorUIResource.lightGray);
        
        cardLabel.setText("CARD LABEL");
        cardLabel.setText("TICKET DECK");
        cardLabel.setText("CARD DECK");
        
        
    }


}
