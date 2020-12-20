package gameClient;

import Gui.Frame;
import Gui.panelLogin;

public class Ex2 {

    public static void main(String[] args){
        if(args.length == 0){
            panelLogin p = new panelLogin();
        }
        else {
            Arena catchThemAll = new Arena(Integer.parseInt(args[0]));
            Frame f = new Frame(catchThemAll);
//            catchThemAll.gatGame().login(Integer.parseInt(args[0]));
            GamePlay game = new GamePlay(catchThemAll, f);
            Thread gamePlay = new Thread(game);
            gamePlay.start();
        }
    }
}
