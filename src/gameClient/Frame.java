package gameClient;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    private Panel panel;

    public Frame(Arena arena) {
        super();
        this.panel = new Panel(arena);
        this.add(panel);
        this.pack();
        frameInit();
    }

       public void frameInit(){
        this.setTitle("Catch The Pokemon");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       /* Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int height = dimension.height;
        int width = dimension.width;
        this.setSize(width, height);*/
        ImageIcon image = new ImageIcon("./resources/Icon.png");
        this.setIconImage(image.getImage());
//        this.getContentPane().setBackground(Color.yellow);
        this.setVisible(true);
    }
}
