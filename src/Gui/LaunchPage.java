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
        loginButton.setBounds(742, 560, 100, 30);
//        loginButton.setFocusable(false);
        loginFrame.add(loginButton);
        loginButton.setForeground((new Color(0, 0, 0, 255)));
        loginButton.setFont(new Font("Verdana", Font.ITALIC, 18));
        ImageIcon img = new ImageIcon("./resources/LoginBackground.jpg");
        JLabel background = new JLabel("", img, JLabel.CENTER);
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        int screenWidth = screenSize.width;
//        int screenHeight = screenSize.height;
        background.setSize(loginFrame.getWidth(), loginFrame.getWidth());
        loginFrame.add(loginPanel);
        loginFrame.add(background);
//        background.setSize(screenSize);
//        background.setBounds(0, 0, loginFrame.getWidth(), loginFrame.getHeight());
        loginFrame.setVisible(true);
        loginButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginButton){
            int gameLevel = Integer.parseInt(loginPanel.getLevelText().getText());
            arena = new Arena(gameLevel);
            Frame frame = new Frame(arena);
            arena.gatGame().startGame();
            arena.firstChooseNext();
            GamePlay game = new GamePlay(arena, frame);
            Thread gamePlay = new Thread(game);
            gamePlay.start();
        }
    }

    public Arena getArena(){
        return arena;
    }

    public long id(){
        return Long.parseLong(loginPanel.getIDText().getText());
    }
}
