package gameClient;

import api.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;

/**
 * This class
 */
public final class jsonToObject {

    /**
     *
     *
     * @param json
     * @param graph
     */
    public static void loadGraph(String json, directed_weighted_graph graph) {
        Gson gson = new Gson();
        JsonObject jGraph = gson.fromJson(json, JsonObject.class);
        JsonArray nodes = jGraph.get("Nodes").getAsJsonArray();
        for (int i = 0; i < nodes.size(); i++) {
            int id = nodes.get(i).getAsJsonObject().get("id").getAsInt();
            String pos = nodes.get(i).getAsJsonObject().get("pos").getAsString();
            geo_location location = new GeoLocation(pos);
            node_data node = new NodeData(id);
            node.setLocation(location);
            graph.addNode(node);
        }

        JsonArray edges = jGraph.get("Edges").getAsJsonArray();
        for (int i = 0; i < edges.size(); i++) {
            int src = edges.get(i).getAsJsonObject().get("src").getAsInt();
            int dest = edges.get(i).getAsJsonObject().get("dest").getAsInt();
            double weight = edges.get(i).getAsJsonObject().get("w").getAsDouble();
            graph.connect(src, dest, weight);
        }
    }

    /**
     *
     *
     * @param json
     * @return
     */
    public static List<CL_Agent> loadAgents(String json) {
        List<CL_Agent> l = new ArrayList<>();
        Gson gson = new Gson();
        JsonObject jsonAgents = gson.fromJson(json, JsonObject.class);
        JsonArray arrayAgents = jsonAgents.get("Agents").getAsJsonArray();
        for(int i=0;i<arrayAgents.size();i++) {
            JsonObject agent= arrayAgents.get(i).getAsJsonObject().get("Agent").getAsJsonObject();
            int id = agent.get("id").getAsInt();
            int value = agent.get("value").getAsInt();
            int src = agent.get("src").getAsInt();
            int dest = agent.get("dest").getAsInt();
            Double speed = agent.get("speed").getAsDouble();
            geo_location pos = new GeoLocation(agent.get("pos").getAsString());
            CL_Agent a = new CL_Agent(id, value, src, dest, speed, pos);
            l.add(a);
        }
        return l;
    }

    /**
     *
     *
     * @param json
     * @param graph
     * @return
     */
    public static List<CL_Pokemon> loadPokemon(String json, directed_weighted_graph graph) {
        List<CL_Pokemon> l = new ArrayList<>();
        Gson gson = new Gson();
        JsonObject jsonPokemons = gson.fromJson(json, JsonObject.class);
        JsonArray arrayPokemons = jsonPokemons.get("Pokemons").getAsJsonArray();
        for(int i=0;i<arrayPokemons.size();i++) {
            JsonObject agent= arrayPokemons.get(i).getAsJsonObject().get("Pokemon").getAsJsonObject();
            int value = agent.get("value").getAsInt();
            int type = agent.get("type").getAsInt();
            geo_location pos = new GeoLocation(agent.get("pos").getAsString());
            CL_Pokemon p = new CL_Pokemon(value, type, pos, graph);
            l.add(p);
        }
        return l;
    }

    /**
     *
     *
     * @param jsonGame
     * @return
     */
    public static int numOfAgentsByLevel(String jsonGame){
        Gson gson = new Gson();
        JsonObject jGame = gson.fromJson(jsonGame, JsonObject.class);
        int numOfAgentsInTheGame = jGame.get("GameServer").getAsJsonObject().get("agents").getAsInt();
        return numOfAgentsInTheGame;
    }

    /**
     *
     * @param jsonGame
     * @return
     */
    public static int score(String jsonGame){
        Gson gson = new Gson();
        JsonObject jGame = gson.fromJson(jsonGame, JsonObject.class);
        int grade = jGame.get("GameServer").getAsJsonObject().get("grade").getAsInt();
        return grade;
    }

    /**
     *
     *
     * @param jsonGame
     * @return
     */
    public static int moves(String jsonGame){
        Gson gson = new Gson();
        JsonObject jGame = gson.fromJson(jsonGame, JsonObject.class);
        int moves = jGame.get("GameServer").getAsJsonObject().get("moves").getAsInt();
        return moves;
    }
}
