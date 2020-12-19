package gameClient;

import api.*;
import Server.Game_Server_Ex2;
import gameClient.util.Range;
import gameClient.util.Range2D;
import gameClient.util.Range2Range;
import java.util.*;


public class Arena implements arenaGame{

    public static final double EPS1 = 0.001, EPS2 = EPS1 * EPS1, EPS = EPS2;
    private final dw_graph_algorithms graphAlgo;
    private List<CL_Agent> agents;
    private List<CL_Pokemon> pokemons;
    private final game_service game;
    private final int level;
    private final int numAgents;

    public Arena(int level) {
        this.level = level;
        graphAlgo = new DWGraph_Algo();
        game = Game_Server_Ex2.getServer(level);
        numAgents = jsonToObject.numOfAgentsByLevel(game.toString());
        jsonToObject.loadGraph(game.getGraph(), graphAlgo.getGraph());
        this.pokemons = jsonToObject.loadPokemon(game.getPokemons(), graphAlgo.getGraph());
        startPositionOfAgents();
    }

    public int getLevel(){return level;}

    public void setAgents(List<CL_Agent> a){
        this.agents = a;
    }

    @Override
    public dw_graph_algorithms getGraphAlgo() {
        return graphAlgo;
    }

    @Override
    public game_service gatGame() {
        return game;
    }

    @Override
    public List<CL_Agent> getAgents() {
        return agents;
    }

    @Override
    public List<CL_Pokemon> getPokemons() {
        return pokemons;
    }

    @Override
    public void startPositionOfAgents() {
//        int numOfAgents = jsonToObject.numOfAgentsByLevel(jsonGame);
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

    @Override
    public void firstChooseNext(){
        int pokemonWithHigherValue = pokemons.size() - 1;
        for (int i = 0; i < numAgents; i++) {
            if(pokemonWithHigherValue < 0){
                agent a = agents.get(i);
                Collection<edge_data> e = graphAlgo.getGraph().getE(a.getSrc());
                if(e != null) {
                    Iterator<edge_data> itE = e.iterator();
                    edge_data e1 = itE.next();
                    this.game.chooseNextEdge(a.getId(), e1.getDest());
                }
            } else {
                CL_Pokemon p = pokemons.get(pokemonWithHigherValue);
                if ((agents.get(i).getSrc()) == p.getEdge().getSrc()) {
                    agent a = agents.get(i);
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

    @Override
    public void movementStrategy() {
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

//    @Override
//    public void movementStrategy() {
//        String jsonPokemons = this.game.getPokemons();
//        this.pokemons = jsonToObject.loadPokemon(jsonPokemons, this.graphAlgo.getGraph());
////         int numOfAgents = this.agents.size();
//        int numOfPokemons = this.pokemons.size();
////         double shortestWayToPokemon = Double.MAX_VALUE;
//        List<node_data> shortestWayToPokemon = new LinkedList<>();
//        List<node_data> pathToPokemon = new LinkedList<>();
////         List<node_data> destOfPokemon = null;
////         int destOfPokemon = -1;
//        for (int i = 0; i < numAgents; i++) {
//            if (this.agents.get(i).getDest() == -1) {
//                for (int j = 0; j < numOfPokemons; j++) {
//                    if(agents.get(i).getSrc() == pokemons.get(j).getEdge().getSrc()){
////                         System.out.println("["+agents.get(i).getSrc()+", "+pokemons.get(j).getEdge().getDest()+"]");
//                        this.game.chooseNextEdge(agents.get(i).getId(), pokemons.get(j).getEdge().getDest());
//                        break;
//                    }
//                    pathToPokemon = graphAlgo.shortestPath(agents.get(i).getSrc(), pokemons.get(j).getEdge().getSrc());
//                    if ((shortestWayToPokemon.size() == 0) || (pathToPokemon.size() < shortestWayToPokemon.size())) {
//                        shortestWayToPokemon = pathToPokemon;
////                         destOfPokemon = pokemons.get(i).getEdge().getDest();
//                    }
//                }
////                 System.out.println(shortestWayToPokemon.toString());
//                if(shortestWayToPokemon.size() != 0)
//                    this.game.chooseNextEdge(agents.get(i).getId(), shortestWayToPokemon.get(1).getKey());
//            }
//            shortestWayToPokemon.clear();
//        }
//    }

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



