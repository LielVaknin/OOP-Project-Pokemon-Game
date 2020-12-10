package gameClient;

import api.*;
import Server.Game_Server_Ex2;

import java.util.*;

public class Arena {
    public static final double EPS1 = 0.001, EPS2 = EPS1 * EPS1, EPS = EPS2;
    private dw_graph_algorithms graphAlgo;
    private List<Agent> agents;
    private List<CL_Pokemon> pokemons;
    private game_service game;

    public Arena(int level) {
        graphAlgo = new DWGraph_Algo();
        game = Game_Server_Ex2.getServer(level);
        jsonToObject.loadGraph(game.getGraph(), graphAlgo.getGraph());
        this.pokemons = jsonToObject.loadPokemon(game.getPokemons(), graphAlgo);
        startPositionOfAgents(game.toString());
    }

    public dw_graph_algorithms getGraphAlgo() {
        return graphAlgo;
    }

    public game_service gatGame() {
        return game;
    }

    public List<Agent> getAgents() {
        return agents;
    }

    public List<CL_Pokemon> getPokemons() {
        return pokemons;
    }

    public void startPositionOfAgents(String jsonGame) {
        int numOfAgents = jsonToObject.numOfAgentsByLevel(jsonGame);
        Collections.sort(pokemons, new CL_Pokemon.pokemonsComparator());
        int pokemonWithHigherValue = pokemons.size() - 1;
        for (int i = 1; i <= numOfAgents; i++) {
            CL_Pokemon p = pokemons.get(pokemonWithHigherValue);
//            Agent ag = new Agent();
//            ag.setSrc(p.getEdge().getSrc());
//            ag.setDest(p.getEdge().getDest());
            game.addAgent(p.getEdge().getSrc());
//            agents.add(ag);
            pokemonWithHigherValue --;
        }
        String jsonAgents = this.game.getAgents();
        this.agents = jsonToObject.loadAgents(jsonAgents);
    }

     public void movementStrategy() {
        int numAgents= agents.size();
        List<node_data> shortestWayToPokemon = new LinkedList<>();
        for (int i = 0; i < (pokemons.size()) && (numAgents > 0); i++) {
//             int agentSrc = agents.get(0).getSrc();
            int agentSrc = -1;
            int pokemonDest = pokemons.get(i).getEdge().getDest();
//             List<node_data> shortestWayToPokemon = new LinkedList<>();/*graphAlgo.shortestPath(agentSrc, pokemonDest)*/;
            for (int j = 1; j < agents.size(); j++) {
//                 int pokemonDest = pokemons.get(i).getEdge().getDest();
                if(agents.get(j).getDest() != -1){
                    continue;
                }
                agentSrc = agents.get(j).getSrc();
                List<node_data> pathToPokemon = graphAlgo.shortestPath(agentSrc, pokemonDest);
                if ((shortestWayToPokemon == null) || (pathToPokemon.size() < shortestWayToPokemon.size())) {
                    shortestWayToPokemon = pathToPokemon;
                    agentSrc = agents.get(j).getSrc();
                    pokemonDest =  pokemons.get(i).getEdge().getDest();
                }
            }
            int agentDest = shortestWayToPokemon.get(1).getKey();
            game.chooseNextEdge(agentSrc, agentDest);
            numAgents--;
            shortestWayToPokemon.clear();
        }
     }
}

