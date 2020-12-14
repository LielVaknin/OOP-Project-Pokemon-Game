package Gui;

import javax.swing.*;

public class panelLogin extends JPanel {

    private static JLabel ID;
    private static JLabel level;
    private static JTextField IDText;
    private static JTextField levelText;
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
}
