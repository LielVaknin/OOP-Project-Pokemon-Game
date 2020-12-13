package Gui;

import api.game_service;
import gameClient.Arena;
import gameClient.GamePlay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class panelLogin extends JPanel implements ActionListener {

    private static JLabel ID;
    private static JLabel level;
    private static JTextField IDText;
    private static JTextField levelText;
    private static JButton login;
    private int numLevel;
    private Frame frame;
//    private Arena a;

    public panelLogin(Frame f){
//        this.setLayout(new BorderLayout(50, 50));
//        this.setSize(50, 50);
        this.setLayout(null);
        setID();
        setLevel();
        login();
        this.frame = f;
    }

    private void setID(){
        ID = new JLabel("ID");
        ID.setBounds(580, 300, 90, 35);
        this.add(ID);

        IDText = new JTextField(10);
        IDText.setBounds(620, 305, 165, 25);
        this.add(IDText);
    }

    private void setLevel() {
        level = new JLabel("level");
        level.setBounds(580, 350, 90, 35);
        this.add(level);

        levelText = new JTextField(10);
        levelText.setBounds(620, 355, 165, 25);
        this.add(levelText);
    }

    private void login(){
        JButton login = new JButton("Login");
        login.setBounds(650, 400 , 80, 25);
        login.addActionListener(this);
        this.add(login);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == login) {
            numLevel = Integer.parseInt(levelText.getText());
            Arena a = new Arena(numLevel);
            Panel p = new Panel(a);
            frame.add(p);
            frame.setVisible(true);
            GamePlay game = new GamePlay(a, frame);
            Thread gamePlay = new Thread(game);
            gamePlay.start();
        }
    }

}
