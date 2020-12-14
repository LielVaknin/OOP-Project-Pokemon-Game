package gameClient;

import Gui.Frame;
import Gui.LaunchPage;

public class Ex2_login {

    public static void main(String[] args){
//        Arena catchThemAll = new Arena(0);
        LaunchPage p = new LaunchPage();
        Frame frame = new Frame(p.getArena());
//        LaunchPage p = new LaunchPage();
//        p.getArena().gatGame().login(p.id());
//        Frame frame = new Frame(p.getArena());
        GamePlay game = new GamePlay(p.getArena(), frame);
        Thread gamePlay = new Thread(game);
        gamePlay.start();

    }
}
