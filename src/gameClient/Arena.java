package gameClient;

import api.*;
import com.google.gson.*;
import Server.Game_Server_Ex2;
import org.json.JSONArray;
import org.json.JSONException;
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
        loadGraph(game.getGraph());
        loadPokemon(game.getPokemons());
        startPositionOfAgents(game.toString());
//        loadAgents(game.getAgents());

//        setEdges();

        placeAgents();


    }

    private void placeAgents() {
    }
/*
    private void setEdges() {

        for(Pokemon p : pokemons ){
            if (p.getEdge() == null){
                f.setEdge()
            }
        }
    }*/

    void loadGraph(String json) {
        graphAlgo = new DWGraph_Algo();
        try {
            PrintWriter pw = new PrintWriter(new File("graph.json"));
            pw.write(json);
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        graphAlgo.load("graph.json");
        System.out.println(graphAlgo.getGraph());
    }

    private void loadAgents(String json) {
/*        System.out.println(json);
        List<Pokemon> l = new ArrayList<>();
        try {
            JSONObject jp = new JSONObject(json);
            JSONArray pok = jp.getJSONArray("Agent");
            for (int i = 0; i < pok.length(); i++) {
                JSONObject j = pok.getJSONObject(i);
                System.out.println(j);
                Pokemon p = new Pokemon(j.toString());
                l.add(p);
                *//*        ArrayList<Agent> ans = new ArrayList<Agent>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray agents = jsonObject.getJSONArray("Agents");
            for(int i=0;i<agents.length();i++) {
                Agent c = new Agent(ggs,0);
                c.update(agents.get(i).toString());
                ans.add(c);
            }
            //= getJSONArray("Agents");
        } catch (JSONException | JSONException e) {
            e.printStackTrace();
        }
        return ans;*//*
//        List<Agent> l = new ArrayList<>();
//        GsonBuilder b = new GsonBuilder();
//        Gson gson = b.create();
//        JsonObject r = gson.fromJson(json, JsonObject.class);
//        JsonArray ags = r.getAsJsonObject().get("Agents").getAsJsonArray();
//        for (int i = 0; i < ags.size(); i++) {
//            JsonObject ag= ags.get(i).getAsJsonObject().get("Agent").getAsJsonObject();
//            GsonBuilder builder = new GsonBuilder();
//            builder.registerTypeAdapter(Agent.class, new Agent());
//            Gson gson1 = builder.create();
//            Agent a = gson1.fromJson(ag, Agent.class);
//            l.add(a);
//        }
//        this.agents = l;
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }*/
    }

    private void loadPokemon(String json) {
        System.out.println(json);
        List<Pokemon> l = new ArrayList<>();
        try {
            JSONObject jp = new JSONObject(json);
            JSONArray pok = jp.getJSONArray("Pokemons");
            for (int i = 0; i < pok.length(); i++) {
                JSONObject j = pok.getJSONObject(i);
                System.out.println(j);
                Pokemon p = new Pokemon(j.toString(), graphAlgo.getGraph());
                l.add(p);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(l);
        this.pokemons = l;
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

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    private int numOfAgentsByLevel(String jsonGame) {
        return 1;
    }

    //
    public void startPositionOfAgents(String jsonGame) {
        int numOfAgents = numOfAgentsByLevel(jsonGame);
        Collections.sort(pokemons, new Pokemon.pokemonsComparator());
        int pCounter = pokemons.size() - 1;
        for (int i = 1; i <= numOfAgents; i++) {
            Pokemon p = pokemons.get(pCounter);
            Agent ag = new Agent();
            ag.setSrc(p.getEdge().getSrc());
            ag.setDest(p.getEdge().getDest());
            game.addAgent(ag.getSrc());
            agents.add(ag);
            pCounter--;
        }
    }

     public void moveStrategy() {

        for (int i = 1; i < agents.size(); i++) {
            Agent ag = agents.get(i);
            List<node_data> ShortestPathToPokemon = graphAlgo.shortestPath(agents.get(0).getSrc(), pokemons.get(0).getEdge().getDest());
            srcOfAgentPath =
            for (int j = 1; j < pokemons.size(); j++){
            List<node_data> l =
               if(l.size())
//            int id = ag.getId();
//            int dest = ag.getNextNode();
//            int src = ag.getSrc();
//            double v = ag.getValue();
//            if (dest == -1) {
//                dest = nextNode(g, src);
//                game.chooseNextEdge(ag.getID(), dest);
//                System.out.println("Agent: " + id + ", val: " + v + "   turned to node: " + dest);
            }
        }

        private static int nextNode (int src){

        }
    }
}

