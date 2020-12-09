package gameClient;

import java.util.List;

public class Ex2 {

    public static void main(String[] args){
        Arena arena = new Arena(23);
//        Frame frame = new Frame(catchThePokemons);
        arena.startPositionOfAgents();
        List<Agent> agents = arena.getAgents();
        for(int i=0; i<agents.size(); i++){
            Thread t = new Thread(agents.get(i));
        }
        arena.gatGame().startGame();

    }
}
