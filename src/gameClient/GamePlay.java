package gameClient;

import Gui.Frame;
import api.*;

/**
 * This class manages the processes of the game.
 *
 * @authors Liel.Vaknin & Renana.Levy.
 */
public class GamePlay implements Runnable{
    private final Arena arena;
    private Frame frame;
    private final game_service game;
    private int dt;
    private int counter; // Counts the number of times that the score didn't update.
    private int score;
    private int lastScore;

    public GamePlay(Arena a, Frame f){
        this.arena = a;
        this.frame = f;
        this.game = a.gatGame();
        this.dt = 150;
        this.counter = 0;
        this.lastScore = 0;
    }

    @Override
    public void run() {
        arena.gatGame().startGame();
        arena.firstChooseNext();
        while (game.isRunning()) {
            score = jsonToObject.score(arena.gatGame().toString());
            if (score != lastScore) {
                lastScore = score;
                counter = 0;
            }
            counter++;
            if (counter >= 15 && counter < 25) {
                try {
                    game.move();
                    Thread.sleep(dt - ((counter/2)+30));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (counter >= 25){
                try {
                    game.move();
                    Thread.sleep(dt - ((counter/2)+40));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    for (int i = 0; i < 1; i++) {
                        game.move();
                    }
                    Thread.sleep(dt);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            move();
        }
        this.frame.update(score);
        game.stopGame();
        System.out.println(game);
    }

    public void move(){
//        System.out.println(game.getAgents());
//        System.out.println(this.arena.getAgents().get(0).getDest());
        this.arena.movementStrategy();
//        System.out.println(this.arena.getAgents().get(0).getDest());
//        System.out.println(game);
        this.frame.update(score);
    }
}