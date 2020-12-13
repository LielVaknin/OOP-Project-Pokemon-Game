package gameClient;

import Gui.Frame;
import Server.Game_Server_Ex2;
import api.game_service;

import java.awt.*;

public class Ex2 {

    public static void main(String[] args){
//        game_service g = Game_Server_Ex2.getServer(0);
//        g.addAgent(9);
//        g.startGame();
//        while (g.isRunning()){
//            try {
//                g.chooseNextEdge(0, 8);
//                g.move();
//                Thread.sleep(100);
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
//        }
//        System.out.println(g);
//        g.chooseNextEdge(0, 8);
//        System.out.println(g.getAgents());
        Arena catchThePokemon = new Arena(0);
//        catchThePokemon.gatGame().startGame();
        catchThePokemon.gatGame().startGame();
//        catchThePokemon.firstChooseNext(catchThePokemon.gatGame().toString());
//        catchThePokemon.gatGame().move();
//        catchThePokemons.gatGame().login(Long.parseLong(args[1]));
        Frame f = new Frame(catchThePokemon);
        GamePlay game = new GamePlay(catchThePokemon, f);
        Thread gamePlay = new Thread(game);
        gamePlay.start();
    }
}
