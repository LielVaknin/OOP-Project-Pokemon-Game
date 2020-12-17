package gameClient;

import Gui.Frame;
import api.game_service;

import java.util.List;

public class GamePlay implements Runnable{

    private final Arena arena;
    private Frame frame;
    private final game_service game;
    private int dt;
    private int counter;    //Counts how many times the agents are stuck.
    private int score;
    private int lastScore;

    public GamePlay(Arena a, Frame f){
        this.arena = a;
        this.frame = f;
        this.game = a.gatGame();
        this.dt = 125;
        this.counter = 0;
        this.lastScore = 0;
    }

    @Override
    public void run() {
        while (game.isRunning()) {
            score = jsonToObject.score(arena.gatGame().toString());
            
            if (score != lastScore) {
                lastScore = score;
                counter = 0;
            }
            counter++;
            if (counter >= 20 && counter < 25) {
                try {
                    for (int i = 0; i < 1; i++) {
                        game.move();
                    }
                    Thread.sleep(dt - (counter/2)-5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (counter >= 25){
                try {
                    for (int i = 0; i < 3; i++) {
                        game.move();
                    }
                    Thread.sleep(dt - (counter/2)-10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    for (int i = 0; i < 1; i++) {
                        game.move();
                    }
                    Thread.sleep(dt+counter-10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            move();
        }
        game.stopGame();
        this.frame.update(score);
        System.out.println(game);
    }

    public void move(){
//        this.frame.update();
//        System.out.println(game.getAgents());
        List<CL_Agent> l = jsonToObject.loadAgents(game.getAgents());
        this.arena.setAgents(l);
//        System.out.println(this.arena.getAgents().get(0).getDest());
        this.arena.movementStrategy();
//        System.out.println(this.arena.getAgents().get(0).getDest());
//        System.out.println(game);
        this.frame.update(score);

//        return  -1; // מה שחוזר מפונקצית החישוב
    }

    /*
     חישוב הצלע הכי ארוכה לסוכן חלקי המהירות שלו (להפוך למילי שניות) לעשות את החישוב במוב סטראתג'י
     */
}
