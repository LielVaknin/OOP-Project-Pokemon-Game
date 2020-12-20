package Gui;

import gameClient.Arena;
import gameClient.GamePlay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class displays the login window of the game.
 *
 * @authors Liel.Vaknin & Renana.Levy.
 */
public class loginPanel extends JPanel implements ActionListener {

    private JFrame loginFrame;

    private JLabel ID, level;
    private JTextField IDText, levelText;
    private JButton loginButton;

    private Arena arena;
    private Image background;

    private int w;
    private int h;

    /**
     * Constructor.
     */
    public loginPanel(){
        loginFrame = new JFrame();
        loginFrame.setTitle("Catch Them All");
        ImageIcon image = new ImageIcon("./resources/Pokemon.png");
        loginFrame.setIconImage(image.getImage());
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setExtendedState(Frame.MAXIMIZED_BOTH);

        background = new ImageIcon("./resources/LoginBackground.jpg").getImage();

        this.setLayout(null);

        ID = new JLabel("ID");
        this.add(ID);
        IDText = new JTextField(10);
        this.add(IDText);
        level = new JLabel("level");
        this.add(level);
        levelText = new JTextField(10);
        this.add(levelText);
        loginButton = new JButton("Login");
        this.add(loginButton);

        loginFrame.add(this);
        loginFrame.setVisible(true);
        loginButton.addActionListener(this);
    }

    /**
     * Displays on the window the TextFields of the Login.
     * Overrides paintComponent method.
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        w = this.getWidth();
        h = this.getHeight();

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(background, 0,0, w, h, null);

        setID();
        setLevel();
        setLoginButton();
    }

    /**
     * Displays on the window TextFields for userID.
     */
    private void setID(){
        ID.setBounds((w/2)-155, (h/2)-35, 90, 35);
        ID.setForeground((new Color(255, 255, 255, 255)));
        ID.setFont(new Font("Verdana", Font.ITALIC, 20));

        IDText.setBounds((w/2)-100, (h/2)-35, 260, 34);
    }

    /**
     * Displays on the window TextFields for choosing level.
     */
    private void setLevel() {
        level.setBounds((w/2)-170, (h/2)+20, 90, 35);
        level.setForeground((new Color(255, 255, 255, 255)));
        level.setFont(new Font("Verdana", Font.ITALIC, 20));

        levelText.setBounds((w/2)-100, (h/2)+20, 260, 34);

    }

    /**
     * Displays the Login button on the window.
     */
    private void setLoginButton(){
        loginButton.setBounds((w/2)-20, (h/2)+80, 100, 30);
        loginButton.setForeground((new Color(0, 0, 0, 255)));
        loginButton.setFont(new Font("Verdana", Font.ITALIC, 18));
    }

    /**
     * Launches the game after pressing the Login button.
     * Overrides actionPerformed method from actionListener.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginButton){
            int gameLevel = Integer.parseInt(levelText.getText());
            arena = new Arena(gameLevel);
            long user = Integer.parseInt(IDText.getText());
            arena.gatGame().login(user);
            loginFrame.dispose();
            Frame frame = new Frame(arena);
            GamePlay game = new GamePlay(arena, frame);
            Thread gamePlay = new Thread(game);
            gamePlay.start();
        }
    }
}
