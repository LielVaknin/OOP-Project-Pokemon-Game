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
        while (game.isRunning()) {
            try {
//                move();
                game.chooseNextEdge(0, 8);
//                for (int i = 0; i < 20; i++)
                game.move();
//                this.frame.update();
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
//            for (int i = 0; i<1000; i++) {
//                this.game.move();
//                this.frame.update();
//            }
            move();
        }
        game.stopGame();
        System.out.println(game);
    }

    public void move(){
//        this.frame.update();
//        System.out.println(game.getAgents());
        List<CL_Agent> l = jsonToObject.loadAgents(game.getAgents());
        this.arena.setAgents(l);
        this.arena.movementStrategy();
//        System.out.println(game);
        this.frame.update();
    }
}
