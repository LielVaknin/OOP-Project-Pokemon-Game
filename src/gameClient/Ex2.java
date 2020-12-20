package gameClient;

import Gui.Frame;
import Gui.loginPanel;

/**
 * This class contains the "main" method which runs the whole project.
 *
 * @authors Liel.Vaknin & Renana.Levy.
 */
public class Ex2 {

    public static void main(String[] args){
        if(args.length == 0){
            loginPanel p = new loginPanel();
        }
        else {
            Arena catchThemAll = new Arena(Integer.parseInt(args[1]));
            Frame f = new Frame(catchThemAll);
            catchThemAll.gatGame().login(Integer.parseInt(args[0]));
            GamePlay game = new GamePlay(catchThemAll, f);
            Thread gamePlay = new Thread(game);
            gamePlay.start();
        }
    }
}
