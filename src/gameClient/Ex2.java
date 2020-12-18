package gameClient;

import Gui.Frame;
import Gui.LaunchPage;
import Server.Game_Server_Ex2;
import api.game_service;

import java.awt.*;

public class Ex2 {

    public static void main(String[] args){
        if(args.length == 0){
            LaunchPage p = new LaunchPage();
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
