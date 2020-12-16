package Gui;

import javax.swing.*;
import java.awt.*;

public class panelLogin extends JPanel {

    private JLabel ID;
    private JLabel level;
    private JTextField IDText;
    private JTextField levelText;
    private JFrame frame;

    public panelLogin(JFrame f){ ;
        this.setLayout(null);
        setID();
        setLevel();
        this.frame = f;
        f.add(ID);
        f.add(IDText);
        f.add(level);
        f.add(levelText);
    }

    private void setID(){
        ID = new JLabel("ID");
        ID.setBounds(624, 449, 90, 35);
        this.add(ID);
        ID.setForeground((new Color(255, 255, 255, 255)));
        ID.setFont(new Font("Verdana", Font.ITALIC, 20));

        IDText = new JTextField(10);
        IDText.setBounds(660, 450, 260, 34);
        this.add(IDText);
    }

    private void setLevel() {
        level = new JLabel("level");
        level.setBounds(601, 499, 90, 35);
        this.add(level);
        level.setForeground((new Color(255, 255, 255, 255)));
        level.setFont(new Font("Verdana", Font.ITALIC, 20));

        levelText = new JTextField(10);
        levelText.setBounds(660, 500, 260, 34);
        this.add(levelText);

    }

    public JTextField getIDText() {
        return IDText;
    }

    public JTextField getLevelText() {
        return levelText;
    }
}
