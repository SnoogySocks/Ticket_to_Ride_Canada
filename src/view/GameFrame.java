package view;

import model.*;
import util.*;

import javax.swing.*;

/**
 * @author Nathan
 */
public class GameFrame extends JFrame {

    // Constants
    private static final int WIDTH = 1920, HEIGHT = 1080;
    
    // Panels
    private BoardPanel boardPanel;
    private ScorePanel scorePanel = new ScorePanel(1400,0,520,180);
    private CardPanel cardPanel = new CardPanel(0,900,1400,180);
    private PlayerPanel playerPanel = new PlayerPanel(1400,180,520,900);
    
    // Menubar components
    private JMenuBar menuBar = new JMenuBar();
    
    private JMenu fileMenu = new JMenu("File");
    private JMenuItem newMI = new JMenuItem("New");
    private JMenuItem saveMI = new JMenuItem("Save");
    private JMenuItem loadMI = new JMenuItem("Load");
    private JMenuItem exitMI = new JMenuItem("Exit");
    private JMenuItem[] fileMenuItems = {newMI, saveMI, loadMI, exitMI};
    
    private JMenu helpMenu = new JMenu("Help");
    private JMenuItem helpContentsMI = new JMenuItem("Help Contents");
    private JMenuItem aboutMI = new JMenuItem("About");
    private JMenuItem[] helpMenuItems = {helpContentsMI, aboutMI};
    
    public GameFrame () {
        
        for (JMenuItem item : fileMenuItems) {
            fileMenu.add(item);
        }

        for (JMenuItem item : helpMenuItems) {
            helpMenu.add(item);
        }

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);

        boardPanel = new BoardPanel(0, 0, 1400, 900);

        add(boardPanel);
        setSize(WIDTH, HEIGHT);
        setLayout(null);
        add(scorePanel);
        add(cardPanel);
        add(playerPanel);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }
    
    public static int getWIDTH () {
        return WIDTH;
    }
    
    public static int getHEIGHT () {
        return HEIGHT;
    }
    
    public BoardPanel getBoardPanel () {
        return boardPanel;
    }
    
    public ScorePanel getScorePanel () {
        return scorePanel;
    }
    
    public CardPanel getCardPanel () {
        return cardPanel;
    }
    
    public PlayerPanel getPlayerPanel () {
        return playerPanel;
    }
    
    public JMenu getFileMenu () {
        return fileMenu;
    }
    
    public JMenuItem getNewMI () {
        return newMI;
    }
    
    public JMenuItem getSaveMI () {
        return saveMI;
    }
    
    public JMenuItem getLoadMI () {
        return loadMI;
    }
    
    public JMenuItem getExitMI () {
        return exitMI;
    }
    
    public JMenuItem[] getFileMenuItems () {
        return fileMenuItems;
    }
    
    public JMenu getHelpMenu () {
        return helpMenu;
    }
    
    public JMenuItem getHelpContentsMI () {
        return helpContentsMI;
    }
    
    public JMenuItem getAboutMI () {
        return aboutMI;
    }
    
    public JMenuItem[] getHelpMenuItems () {
        return helpMenuItems;
    }
    
}
