package Gui;

import gameClient.Arena;
import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    private Arena arena;
    private Panel panel;


    public Frame(){
        super();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        this.setTitle("Catch Them All"); //sets title of frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit from application
//        this.setResizable(false); //prevent frame from being resized
        this.setSize(screenWidth, screenHeight); //sets the x-dimension, and y-dimension of frame
//        this.setVisible(true); //make frame visible
        ImageIcon image = new ImageIcon("./resources/Pokemon.png"); //create an ImageIcon
        this.setIconImage(image.getImage()); //change icon of frame
    }

    public Frame(Arena arena) {
        super();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        this.setTitle("Catch Them All"); //sets title of frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit from application
//        this.setResizable(true); //prevent frame from being resized
        this.setSize(screenWidth, screenHeight); //sets the x-dimension, and y-dimension of frame
        this.setResizable(true);
        ImageIcon image = new ImageIcon("./resources/Pokemon.png"); //create an ImageIcon
        this.setIconImage(image.getImage()); //change icon of frame
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
//        this.getContentPane().setBackground(new Color(0,122 ,232)); //change color of background
//        this.setLayout(null);

        this.arena = arena;
        initPanel();
        this.setVisible(true); //make frame visible
    }

    private void initPanel(){
//        this.getSize();

        this.panel = new Panel(arena);
        this.add(panel);
    }

    public void update(int score){
        panel.update(score);
    }

}

