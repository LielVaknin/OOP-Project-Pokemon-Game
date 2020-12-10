package gameClient;

import api.*;
import com.google.gson.*;
import gameClient.util.Point3D;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.Iterator;

public class Pokemon {

    private int id;
    private double value;
    private int type;
    private geo_location pos;
    private edge_data edge;

    public Pokemon (String json, directed_weighted_graph g){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject p = jsonObject.getJSONObject("Pokemon");

            this.value = p.getInt("value");
            this.type = p.getInt("type");
            this.pos = new GeoLocation(p.getString("pos"));
            this.edge = pokemonEdge(g);

            System.out.println(this);

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public edge_data getEdge() {
        return edge;
    }

    public double getValue() {
        return value;
    }

    public int getType() {
        return type;
    }

    public geo_location getPos() {
        return pos;
    }

    public edge_data pokemonEdge(directed_weighted_graph graph) {
        Boolean pokemonOnRisingEdge = true;
        Boolean pokemonOnFallingEdge = true;
        if (this.type == 1) {
            pokemonOnFallingEdge = false;
        } else if (this.type == -1) {
            pokemonOnRisingEdge = false;
        }
        Iterator<node_data> it1 = graph.getV().iterator();
        while (it1.hasNext()) {
            node_data node = it1.next();
            Iterator<edge_data> it2 = graph.getE(node.getKey()).iterator();
            while (it2.hasNext()) {
                edge_data edge = it2.next();
                if (pokemonOnRisingEdge && (edge.getSrc() < edge.getDest())) {
                    if(this.pos.y() == calculatePokemonEdge(edge, graph)){
                        return edge;
                    }
                } else if (pokemonOnFallingEdge && (edge.getSrc() > edge.getDest())) {
                    if(this.pos.y() == calculatePokemonEdge(edge, graph)){
                        return edge;
                    }
                }
            }
        }
        return null;
    }
    public String toString() {return "F:{v="+value+", t="+type+"}";}

    private double calculatePokemonEdge(edge_data edge, directed_weighted_graph graph){
        double subtruction_y1_y2 = graph.getNode(edge.getSrc()).getLocation().y() - graph.getNode(edge.getDest()).getLocation().y();
        double subtruction_x1_x2 = graph.getNode(edge.getSrc()).getLocation().x() - graph.getNode(edge.getDest()).getLocation().x();
        double m = subtruction_y1_y2 / subtruction_x1_x2;
        double n = graph.getNode(edge.getSrc()).getLocation().y() - (m * graph.getNode(edge.getSrc()).getLocation().x());
        return m * this.pos.x() + n;
    }

    /**
     * Class which implements the Comparator<T> interface,
     * used for startPositionOfAgents method in Arena class.
     */
    static class pokemonsComparator implements Comparator<Pokemon> {

        /**
         * Overriding compare() method of Comparator.
         *
         * @return
         */
        @Override
        public int compare(Pokemon p1, Pokemon p2) {
            return Double.compare(p1.getValue(), p2.getValue());
        }
    }
}
