package Gui;

import javax.swing.*;
import java.awt.*;

public class panelLogin extends JPanel {

    private static JLabel ID;
    private static JLabel level;
    private static JTextField IDText;
    private static JTextField levelText;
    private int numLevel;
    private JFrame frame;
    //private Font normalFont = new Font("Times New Roman", Font.PLAIN, 28);
    //private Font pixelMplus;

    public panelLogin(JFrame f){ ;
        f.setLayout(null);
        setID();
        setLevel();
        this.frame = f;
        f.add(ID);
        f.add(IDText);
        f.add(level);
        f.add(levelText);

//        try{
//            pixelMplus = Font.createFont(Font.TRUETYPE_FONT, new File("PixelMplus10-Regular.ttf")).deriveFont(30f);
//            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("PixelMplus10-Regular.ttf")));
//        }
//        catch(IOException | FontFormatException e){
//
//        }
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

    public Font getPixelMplus() {
        return pixelMplus;
    }
}
