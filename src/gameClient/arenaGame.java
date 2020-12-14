package gameClient;

import api.dw_graph_algorithms;
import api.game_service;
import java.util.List;

public interface arenaGame {

    /**
     * Returns the graph of this level of the game.
     * @return
     */
    public dw_graph_algorithms getGraphAlgo();

    /**
     * Returns the game in specific level.
     * @return
     */
    public game_service gatGame();

    /**
     * Returns a List of agents.
     * @return
     */
    public List<CL_Agent> getAgents();

    /**
     * Returns a List of pokemons.
     * @return
     */
    public List<CL_Pokemon> getPokemons();

    /**
     * This method places the agents before the game begins.
     *
     */
    public void startPositionOfAgents();

    /**
     * This method implements an algorithm which chooses
     * for each agent with dest == -1 (agent who has no destination at a given moment)
     * his next destination during the game depending the new pokemons list.
     */
    public void movementStrategy();
}
