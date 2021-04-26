package view;

import model.City;
import model.Route;
import util.*;

import javax.swing.*;

/**
 * @author Nathan
 */
public class GameFrame extends JFrame {

    //Constants
    private static final int WIDTH = 1920, HEIGHT = 1080;
<<<<<<< HEAD
    
    //Panels
    private BoardPanel boardPanel;
    private ScorePanel scorePanel = new ScorePanel(1400,0,520,180);
    private CardPanel cardPanel = new CardPanel(0,900,1400,180);
    private PlayerPanel playerPanel = new PlayerPanel(1400,180,520,900);
    
    //Menubar components
    
    public GameFrame(City[] cities, Route[] routes){
        boardPanel = new BoardPanel(0, 0, 1400,900, cities, routes);
=======

    //Panels
    private BoardPanel boardPanel;
    private ScorePanel scorePanel = new ScorePanel(1400, 0, 520, 180);
    private CardPanel cardPanel = new CardPanel(0, 860, 1400, 180);
    private PlayerPanel playerPanel = new PlayerPanel(1400, 180, 520, 900);

    //Menubar components
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

    public GameFrame(City[] cities, Route[] routes) {
        for (JMenuItem item : fileMenuItems) {
            fileMenu.add(item);
        }

        for (JMenuItem item : helpMenuItems) {
            helpMenu.add(item);
        }

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);

        boardPanel = new BoardPanel(cities, routes);

>>>>>>> e739442adaf33f41fc4d8b545a2ce71f2cae0b0e
        add(boardPanel);
        setSize(WIDTH, HEIGHT);
        setLayout(null);
        add(scorePanel);
        add(cardPanel);
        add(playerPanel);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
