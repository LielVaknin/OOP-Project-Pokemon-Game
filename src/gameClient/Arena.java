package gameClient;

import api.*;
import Server.Game_Server_Ex2;
import gameClient.util.Range;
import gameClient.util.Range2D;
import gameClient.util.Range2Range;
import java.util.*;

/**
 * This class represents the arena of the game.
 *
 * @authors Liel.Vaknin & Renana.Levy.
 */
public class Arena {

    public static final double EPS1 = 0.001, EPS2 = EPS1 * EPS1, EPS = EPS2;
    private final dw_graph_algorithms graphAlgo;
    private List<CL_Agent> agents;
    private List<CL_Pokemon> pokemons;
    private final game_service game;
    private final int level;
    private final int numAgents;

    /**
     * Constructor
     *
     * @param level
     */
    public Arena(int level) {
        this.level = level;
        graphAlgo = new DWGraph_Algo();
        game = Game_Server_Ex2.getServer(level);
        numAgents = jsonToObject.numOfAgentsByLevel(game.toString());
        jsonToObject.loadGraph(game.getGraph(), graphAlgo.getGraph());
        this.pokemons = jsonToObject.loadPokemon(game.getPokemons(), graphAlgo.getGraph());
        startPositionOfAgents();
    }

    /**
     * Returns the current level of this game.
     *
     * @return level.
     */
    public int getLevel(){return level;}

    /**
     * Returns the graph of current level of this game.
     *
     * @return graphAlgo.
     */
    public dw_graph_algorithms getGraphAlgo() {
        return graphAlgo;
    }

    /**
     * Returns the game in specific level.
     *
     * @return game.
     */
    public game_service gatGame() {
        return game;
    }

    /**
     * Returns a List of agents of this game.
     *
     * @return List<CL_Agent>.
     */
    public List<CL_Agent> getAgents() {
        return agents;
    }

    /**
     * Returns a List of pokemons of this game.
     *
     * @return List<CL_Pokemon>.
     */
    public List<CL_Pokemon> getPokemons() {
        return pokemons;
    }

    /**
     * This method places the agents before the game begins.
     */
    public void startPositionOfAgents() {
        Collections.sort(pokemons, new CL_Pokemon.pokemonsComparator());
        int pokemonWithHigherValue = pokemons.size() - 1;
        for (int i = 1; i <= numAgents; i++) {
            if(pokemonWithHigherValue < 0){
                int index = (int)(Math.random()*graphAlgo.getGraph().getV().size());
                game.addAgent(index);
            }
            CL_Pokemon p = pokemons.get(pokemonWithHigherValue);
            game.addAgent(p.getEdge().getSrc());
            pokemonWithHigherValue --;
        }

        String jsonAgents = this.game.getAgents();
        this.agents = jsonToObject.loadAgents(jsonAgents);
    }

    /**
     * This method makes for each agent his first movement in this game.
     */
    public void firstChooseNext(){
        int pokemonWithHigherValue = pokemons.size() - 1;
        for (int i = 0; i < numAgents; i++) {
            if(pokemonWithHigherValue < 0){
                CL_Agent a = agents.get(i);
                Collection<edge_data> e = graphAlgo.getGraph().getE(a.getSrc());
                if(e != null) {
                    Iterator<edge_data> itE = e.iterator();
                    edge_data e1 = itE.next();
                    this.game.chooseNextEdge(a.getId(), e1.getDest());
                }
            } else {
                CL_Pokemon p = pokemons.get(pokemonWithHigherValue);
                if ((agents.get(i).getSrc()) == p.getEdge().getSrc()) {
                    CL_Agent a = agents.get(i);
                    //                System.out.println(p.getEdge().getDest());
                    this.game.chooseNextEdge(a.getId(), p.getEdge().getDest());
                    //                System.out.println(this.game.getAgents());
                    pokemonWithHigherValue--;
                    i = 0;
                }
            }
        }
        String jsonAgents = this.game.getAgents();
        this.agents = jsonToObject.loadAgents(jsonAgents);
    }

    /**
     * This method implements an algorithm which chooses for each agent with dest == -1
     * (agent who has no destination at a given moment) his next destination during the game
     * depending the new pokemons list.
     */
    public void movementStrategy() {
        this.agents = jsonToObject.loadAgents(game.getAgents());
        String jsonPokemons = this.game.getPokemons();
        this.pokemons = jsonToObject.loadPokemon(jsonPokemons, this.graphAlgo.getGraph());
        synchronized (this) {
            directed_weighted_graph g = graphAlgo.getGraph();
            List<node_data> shortestWayToPokemon = new LinkedList<>();
            List<node_data> pathToPokemon = new LinkedList<>();
            List<node_data> captured = new ArrayList<>();
            edge_data e = null;
            for (CL_Agent a : agents) {
                if (a.getDest() == -1) {
                    for (CL_Pokemon p : pokemons) {
                        e = p.getEdge();
                        if (captured.contains(g.getNode(e.getSrc())) || captured.contains(g.getNode(e.getDest()))) {
                            continue;
                        }
                        if (a.getSrc() == e.getSrc()) {
                            this.game.chooseNextEdge(a.getId(), e.getDest());
                            captured.add(g.getNode(e.getSrc()));
                            captured.add(g.getNode(e.getDest()));
                            break;
                        }
                        pathToPokemon = graphAlgo.shortestPath(a.getSrc(), e.getSrc());
                        if ((shortestWayToPokemon.size() == 0) || (pathToPokemon.size() < shortestWayToPokemon.size())) {
                            shortestWayToPokemon = pathToPokemon;
                        }
                    }
                    if (shortestWayToPokemon.size() != 0) {
                        this.game.chooseNextEdge(a.getId(), shortestWayToPokemon.get(1).getKey());
                        captured.add(shortestWayToPokemon.get(1));
                        captured.add(g.getNode(e.getSrc()));
                        captured.add(g.getNode(e.getDest()));
                    }
                    //                } else {


                }
                shortestWayToPokemon.clear();
            }
        }
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

    /**
     * This method uses for updateFrame() method in gamePanel class.
     *
     * @param g
     * @param frame
     * @return
     */
    public static Range2Range w2f(directed_weighted_graph g, Range2D frame) {
        Range2D world = GraphRange(g);
        Range2Range ans = new Range2Range(world, frame);
        return ans;
    }
}



