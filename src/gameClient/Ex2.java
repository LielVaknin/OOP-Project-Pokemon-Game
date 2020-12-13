package gameClient;

import Gui.Frame;
import java.awt.*;

public class Ex2 {

    public static void main(String[] args){
        Arena catchThePokemon = new Arena(0);
//        catchThePokemons.gatGame().login(Long.parseLong(args[1]));
        Frame f = new Frame(catchThePokemon);
        GamePlay game = new GamePlay(catchThePokemon, f);
        Thread gamePlay = new Thread(game);
        gamePlay.start();
    }
}
