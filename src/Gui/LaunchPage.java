package Gui;

import gameClient.Arena;
import gameClient.GamePlay;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaunchPage implements ActionListener {

  private JButton loginButton;

    public LaunchPage(){
        JFrame loginFrame = new JFrame();
        loginFrame.setTitle("Catch Them All");
        ImageIcon image = new ImageIcon("./resources/Pokemon.png");
        loginFrame.setIconImage(image.getImage());
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        panelLogin loginPanel = new panelLogin(loginFrame);
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(650, 400 , 80, 25);
        loginButton.setFocusable(false);
        loginFrame.add(loginButton);
        ImageIcon img = new ImageIcon("./resources/LoginBackground.jpg");
        JLabel background = new JLabel("", img, JLabel.CENTER);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        background.setBounds(0, 0, screenWidth, screenHeight);
        loginFrame.add(loginPanel);
        loginFrame.add(background);
        loginFrame.setVisible(true);
        loginButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginButton){
            Arena arena = new Arena();

//            Frame frame = new Frame(arena);
//            GamePlay game = new GamePlay(arena, frame);
//            Thread gamePlay = new Thread(game);
//            gamePlay.start();
        }
    }
}
