package gameClient;

import Gui.Frame;
import Gui.LaunchPage;

public class Ex2_login {

    public static void main(String[] args){
//        Arena catchThemAll = new Arena(0);
        LaunchPage p = new LaunchPage();
        Frame frame = new Frame(arena);
        GamePlay game = new GamePlay(arena, frame);
        Thread gamePlay = new Thread(game);
        gamePlay.start();

    }
}
