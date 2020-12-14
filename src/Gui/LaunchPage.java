package Gui;

import gameClient.Arena;
import gameClient.GamePlay;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaunchPage implements ActionListener {

    private Arena arena;
    private panelLogin loginPanel;
    private JButton loginButton;

    public LaunchPage(){
        JFrame loginFrame = new JFrame();
        loginFrame.setTitle("Catch Them All");
        ImageIcon image = new ImageIcon("./resources/Pokemon.png");
        loginFrame.setIconImage(image.getImage());
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        loginPanel = new panelLogin(loginFrame);
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
            int gameLevel = Integer.parseInt(loginPanel.getLevelText().getText());
            arena = new Arena(gameLevel);
        }
    }

    public Arena getArena(){
        return arena;
    }

    public long id(){
        return Long.parseLong(loginPanel.getIDText().getText());
    }
}
