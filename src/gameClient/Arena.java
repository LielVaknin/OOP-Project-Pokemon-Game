package gameClient;

import api.*;
import com.google.gson.*;
import Server.Game_Server_Ex2;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.PrintWriter;

import java.util.*;

public class Arena {

    public static final double EPS1 = 0.001, EPS2 = EPS1 * EPS1, EPS = EPS2;
    private dw_graph_algorithms graphAlgo;
    private List<Agent> agents;
    private List<Pokemon> pokemons;
    private game_service game;

    public Arena(int level) {
        game = Game_Server_Ex2.getServer(level);
        loadAgents(game.getAgents());
        loadPokemon(game.getPokemons());
        loadGraph(game.getGraph());
    }

    void loadGraph(String json) {
        graphAlgo = new DWGraph_Algo();
        try {
            PrintWriter pw = new PrintWriter(new File("graph.json"));
            pw.write(json);
            pw.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            return;
        }
       graphAlgo.load("graph.json");
    }

    private void loadAgents(String json) {
        List<Agent> l = new ArrayList<>();
        GsonBuilder b = new GsonBuilder();
        Gson gson = b.create();
        JsonObject r = gson.fromJson(json, JsonObject.class);
        JsonArray ags = r.getAsJsonObject().get("Agents").getAsJsonArray();
        for (int i = 0; i < ags.size(); i++) {
            JsonObject ag= ags.get(i).getAsJsonObject().get("Agent").getAsJsonObject();
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Agent.class, new Agent());
            Gson gson1 = builder.create();
            Agent a = gson1.fromJson(ag, Agent.class);
            l.add(a);
        }
        this.agents = l;
    }

    private void loadPokemon(String json) {
        List<Pokemon> l = new ArrayList<>();
        try {
            JSONObject jp = new JSONObject(json);
            JSONArray pok = jp.getJSONArray("Pokemons");
            for (int i = 0; i < pok.length(); i++) {
                GsonBuilder builder = new GsonBuilder();
                builder.registerTypeAdapter(Agent.class, new Pokemon());
                Gson gson = builder.create();
                //FileReader newPokemon = new FileReader(json);
                Pokemon p = gson.fromJson(json, Pokemon.class);
                l.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.pokemons = l;
    }

    public dw_graph_algorithms getGraphAlgo() {
        return graphAlgo;
    }

    public game_service gatGame(){
        return game;
    }

    public List<Agent> getAgents() {
        return agents;
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    private Object[][] pokemonsAndEdges() {
        Object [][] pokemonsEdges = new Object [pokemons.size()][2];
        int i = 0;
        Iterator<Pokemon> it = pokemons.iterator();
        while (it.hasNext()) {
           Pokemon p =  it.next();
           pokemonsEdges [i][0] = p;
           pokemonsEdges [i][1] = p.pokemonEdge(this.graphAlgo.getGraph());
           i++;
        }
        return pokemonsEdges;
    }

    public void startPositionOfAgents(){
        PriorityQueue<List> q = new PriorityQueue<>();
        Object [][] pokemonsEdges = pokemonsAndEdges();
        int rowlength = pokemonsEdges.length;
        int colLength = pokemonsEdges[0].length;

        for (int i = 0; i < rowlength; i++){
            for (int j = 0; j < rowlength; j++){
                List<node_data> path = this.graphAlgo.shortestPath(((edge_data)pokemonsEdges[i][1]).getSrc(), ((edge_data)pokemonsEdges[j][1]).getDest());
                for (int k = 1; k <= path.size(); i++){
                    for (int a = 0; a < rowlength; a++){
                        for (int b = 0; b < colLength; b++){
                            //path.get(k-1), path.get(k);
                        }
                    }
                }
                q.add(path);
        }


        }
    }

//    public void chooseNextEdge(){
//        Object[][] edgesPokemon = pokemonsAndEdges();
//        int dest;
//        for (Agent a: agents){
//            if (a.getDest()!=-1){
//               // Collection<edge_data> neighborsAgent =  graphAlgo.getGraph().getE(a.getSrc());
//                double dis = Double.MAX_VALUE;
//                for(int i=0; i<edgesPokemon.length; i++){
//                   Double disBetweenTheEdges = this.graphAlgo.shortestPathDist(((edge_data)edgesPokemon[i][1]).getSrc(), ((edge_data)edgesPokemon[i][1]).getDest());
//                   if((disBetweenTheEdges != -1) && (disBetweenTheEdges < dis)){
//                       dis = disBetweenTheEdges;
//                       dest = ((edge_data)edgesPokemon[i][1]).getDest();
//                   }
//                }
//            }
//        }
//    }
}
