package view;

import javax.swing.*;
import util.*;
import java.awt.*;

/**
 * @author Cerena
 */

public class HighlightLine extends JComponent {
    
    private static final int OFFSET_X = 5, OFFSET_Y = 15;
    
    public static void drawLine (Graphics g, Coordinate c1, Coordinate c2) {
        
        int x1, y1, x2, y2;
        x1 = c1.getX()+OFFSET_X;
        y1 = c1.getY()+OFFSET_Y;
        x2 = c2.getX()+OFFSET_X;
        y2 = c2.getY()+OFFSET_Y;
        
        Graphics2D g2D = (Graphics2D) g;
        
        g2D.setColor(new Color(255, 61, 252, 139)); //hot pink best pink
        g2D.setStroke(new BasicStroke(10));
        
        g.drawLine(x1, y1, x2, y2);
        
    }
    
}
