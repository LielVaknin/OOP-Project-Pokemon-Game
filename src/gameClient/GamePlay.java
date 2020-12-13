package gameClient;

import Gui.Frame;
import Server.Game_Server_Ex2;
import api.game_service;

import java.util.List;

public class GamePlay implements Runnable{

    private Arena arena;
    private Frame frame;
    private game_service game;

    public GamePlay(Arena a, Frame f){
        this.arena = a;
        this.frame = f;
        this.game = a.gatGame();
    }

    @Override
    public void run() {
        game.startGame();
        while (game.isRunning()) {
            this.game.move();
            move();
        }
        game.stopGame();
    }

    public void move(){
//        this.frame.update();
        System.out.println(game.getAgents());
        List<CL_Agent> l = jsonToObject.loadAgents(game.getAgents());
        this.arena.setAgents(l);
        this.arena.movementStrategy();
//        System.out.println(game.getAgents());
        this.frame.update();
    }
}
