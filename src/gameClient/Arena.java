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
        Gson gson = new Gson();
        JsonObject jGraph = gson.fromJson(json, JsonObject.class);
        ((DWGraph_Algo)graphAlgo).initFromJson(jGraph);
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

    private int numOfAgentsByLevel(String jsonGame){
        Gson gson = new Gson();
        JsonObject jGame = gson.fromJson(jsonGame, JsonObject.class);
        int numAgentsOnTheGame = jGame.get("GameServer").getAsJsonObject().get("agents").getAsInt();
        return numAgentsOnTheGame;
    }

    //
    public void startPositionOfAgents(String jsonGame) {
        int numOfAgents = numOfAgentsByLevel(jsonGame);
        pokemons.sort(value);
        /*
        לופ:
       1. עדכון src לכל סוכן
       2. עדכון dest לכל סוכן
       3. הןספת סוכן למשחק (game.addAgent)
       4ץ עדכון ברשימת סוכנים
         */
    }
}
