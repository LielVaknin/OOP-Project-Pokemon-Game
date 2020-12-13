package gameClient;

import Gui.Frame;
import Gui.panelLogin;

import java.awt.*;

public class Ex2_login {

    public static void main(String[] args){
        Frame f = new Frame();
        panelLogin p = new panelLogin(f);
        f.add(p);
        f.setVisible(true);
//        Arena catchThePokemon = new Arena(p.getNumLevel());
//        Frame f2 = new Frame(catchThePokemon);
//        GamePlay game = new GamePlay(catchThePokemon, f2);
//        Thread gamePlay = new Thread(game);
//        gamePlay.start();
    }
}
