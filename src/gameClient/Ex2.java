package gameClient;

import Gui.Frame;
import Server.Game_Server_Ex2;
import api.game_service;

import java.awt.*;

public class Ex2 {

    public static void main(String[] args){
//        System.out.println(g);
//        System.out.println(g.getAgents());
        Arena catchThePokemon = new Arena(23);
//        System.out.println(catchThePokemon.getGraphAlgo().getGraph().toString());
        Frame f = new Frame(catchThePokemon);
        catchThePokemon.gatGame().startGame();
        catchThePokemon.firstChooseNext();
//        catchThePokemons.gatGame().login(Long.parseLong(args[1]));
        GamePlay game = new GamePlay(catchThePokemon, f);
        Thread gamePlay = new Thread(game);
        gamePlay.start();
    }
}
