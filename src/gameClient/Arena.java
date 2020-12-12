package gameClient;

import api.*;
import Server.Game_Server_Ex2;
import gameClient.util.Range;
import gameClient.util.Range2D;
import gameClient.util.Range2Range;
import java.util.*;

public class Arena {

    public static final double EPS1 = 0.001, EPS2 = EPS1 * EPS1, EPS = EPS2;
    private dw_graph_algorithms graphAlgo;
    private List<CL_Agent> agents;
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

    public List<CL_Agent> getAgents() {
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

        pokemonWithHigherValue = pokemons.size() - 1;
        for (int i = 1; i <= numOfAgents; i++) {
            CL_Pokemon p = pokemons.get(pokemonWithHigherValue);
            agent a = agents.get(p.getEdge().getSrc());
            a.setDest(p.getEdge().getDest());
            pokemonWithHigherValue --;
        }
    }

     public void movementStrategy() {
        int numAgents= agents.size();
//        List<node_data> shortestWayToPokemon = new LinkedList<>();
//        for (int i = 0; i < (pokemons.size()) && (numAgents > 0); i++) {
////             int agentSrc = agents.get(0).getSrc();
//            int agentSrc = -1;
//            int pokemonDest = pokemons.get(i).getEdge().getDest();
////             List<node_data> shortestWayToPokemon = new LinkedList<>();/*graphAlgo.shortestPath(agentSrc, pokemonDest)*/;
//            for (int j = 0; j < agents.size(); j++) {
////                 int pokemonDest = pokemons.get(i).getEdge().getDest();
//                agentSrc = agents.get(j).getSrc();
//                List<node_data> pathToPokemon = graphAlgo.shortestPath(agentSrc, pokemonDest);
//                if ((shortestWayToPokemon.size() == 0) || (pathToPokemon.size() < shortestWayToPokemon.size())) {
//                    shortestWayToPokemon = pathToPokemon;
//                    agentSrc = agents.get(j).getSrc();
//                    pokemonDest =  pokemons.get(i).getEdge().getDest();
//                }
//            }
//            int agentDest = shortestWayToPokemon.get(1).getKey();
//            game.chooseNextEdge(agentSrc, agentDest);
//            numAgents--;
//            shortestWayToPokemon.clear();
//        }
     }

    private static Range2D GraphRange(directed_weighted_graph g) {
        Iterator<node_data> itr = g.getV().iterator();
        double x0=0,x1=0,y0=0,y1=0;
        boolean first = true;
        while(itr.hasNext()) {
            geo_location p = itr.next().getLocation();
            if(first) {
                x0=p.x(); x1=x0;
                y0=p.y(); y1=y0;
                first = false;
            }
            else {
                if(p.x()<x0) {x0=p.x();}
                if(p.x()>x1) {x1=p.x();}
                if(p.y()<y0) {y0=p.y();}
                if(p.y()>y1) {y1=p.y();}
            }
        }
        Range xr = new Range(x0,x1);
        Range yr = new Range(y0,y1);
        return new Range2D(xr,yr);
    }

    public static Range2Range w2f(directed_weighted_graph g, Range2D frame) {
        Range2D world = GraphRange(g);
        Range2Range ans = new Range2Range(world, frame);
        return ans;
    }
}

