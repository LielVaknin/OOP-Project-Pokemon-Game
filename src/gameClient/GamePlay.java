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
        this.dt = 110;
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
            if (score == lastScore)
                counter++;
            if (counter >= 10) {
                try {
//                move();
                    for (int i = 0; i < 1; i++) {
                        game.move();
//                    if(i%200 == 0)
//                        this.frame.update();
                    }
                    Thread.sleep(dt - 25);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//            } if (counter >= 10 && counter < 12){
//                try {
////                move();
//                    for (int i = 0; i < 1; i++) {
//                        game.move();
////                    if(i%200 == 0)
////                        this.frame.update();
//                    }
//                    Thread.sleep(dt - 30);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            } else {
                try {
//                   move();
                    for (int i = 0; i < 1; i++) {
                        game.move();
//                     if(i%200 == 0)
//                        this.frame.update();
                    }
                    Thread.sleep(dt);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
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
