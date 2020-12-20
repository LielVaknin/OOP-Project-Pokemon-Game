package Gui;

import gameClient.Arena;
import javax.swing.*;
import java.awt.*;

/**
 * This class represents the frame on which the loginPanel and the gamePanel drawing on.
 *
 * @authors Liel.Vaknin & Renana.Levy.
 */
public class Frame extends JFrame {

    private Arena arena;
    private gamePanel gamePanel;


    public Frame(){
        super();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        this.setTitle("Catch Them All"); // Sets the title of the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exits from application
        this.setSize(screenWidth, screenHeight); // Sets the x-dimension and y-dimension of frame
        ImageIcon image = new ImageIcon("./resources/Pokemon.png"); // Create an ImageIcon
        this.setIconImage(image.getImage()); // Change icon of frame
    }

    public Frame(Arena arena) {
        super();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        this.setTitle("Catch Them All"); // Sets the title of frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exits from application
        this.setSize(screenWidth, screenHeight); // Sets the x-dimension and y-dimension of frame
        this.setResizable(true);
        ImageIcon image = new ImageIcon("./resources/Pokemon.png"); // Creates an ImageIcon
        this.setIconImage(image.getImage()); // Changes icon of frame
        this.setExtendedState(Frame.MAXIMIZED_BOTH);

        this.arena = arena;
        initPanel();
        this.setVisible(true); // Makes frame visible
    }

    private void initPanel(){
        this.gamePanel = new gamePanel(arena);
        this.add(gamePanel);
    }

    public void update(int score){
        gamePanel.update(score);
    }

}

