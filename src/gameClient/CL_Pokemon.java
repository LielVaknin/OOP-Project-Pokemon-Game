package gameClient;

import api.*;
import java.util.Comparator;
import java.util.Iterator;

/**
 * This class represents a pokemon in the game.
 *
 * @authors Liel.Vaknin & Renana.Levy.
 */
public class CL_Pokemon {

    public static final double EPS1 = 0.001, EPS2 = EPS1 * EPS1, EPS = EPS2;
    private int id;
    private double value;
    private int type;
    private geo_location pos;
    private edge_data edge;

    /**
     * Constructor.
     *
     * @param value
     * @param type
     * @param pos
     * @param g
     */
    public CL_Pokemon(double value, int type, geo_location pos, directed_weighted_graph g){
        this.value = value;
        this.type = type;
        this.pos = pos;
        updateEdge(g);
    }

    /**
     * Returns the edge which this pokemon is stands on.
     *
     * @return edge.
     */
    public edge_data getEdge() {
        return edge;
    }

    /**
     * Returns the value of this pokemon.
     *
     * @return value.
     */
    public double getValue() {
        return value;
    }

    /**
     * Returns the type of this pokemon.
     * if type == -1 the pokemon is on falling edge,
     * if type == 1 the pokemon is on rising edge.
     *
     * @return type.
     */
    public int getType() {
        return type;
    }

    /**
     * Returns geo location <x,y,z>, aka Point3D, of this pokemon.
     *
     * @return pos.
     */
    public geo_location getPos() {
        return pos;
    }

    /**
     * This method finds on which edge the given pokemon is stands on
     * and sets this edge to be the pokemon's edge.
     *
     * @param g represents the given graph.
     */
    private void updateEdge(directed_weighted_graph g) {
        //	oop_edge_data ans = null;
        Iterator<node_data> it1 = g.getV().iterator();
        while(it1.hasNext()) {
            node_data v = it1.next();
            Iterator<edge_data> it2 = g.getE(v.getKey()).iterator();
            while(it2.hasNext()) {
                edge_data e = it2.next();
                boolean f = isOnEdge(e, g);
                if(f) {
                    this.setEdge(e);
                }
            }
        }
    }

    /**
     * Uses for updating the pokemon's edge.
     *
     * @param e represents the given edge.
     */
    private void setEdge(edge_data e) {
        this.edge = e;
    }

    /**
     * Checks if this pokemon is on edge src-->dest.
     *
     * @param src represents the geo location of source node.
     * @param dest represents the geo location of destination node.
     * @return true if the pokemon is on edge src-->dest, else returns false.
     */
    private boolean isOnEdge(geo_location src, geo_location dest) {
        boolean ans = false;
        double dist = src.distance(dest);
        double d1 = src.distance(this.pos) + this.pos.distance(dest);
        if(dist > d1 - EPS2) {
            ans = true;
        }
        return ans;
    }

    /**
     * Calculates the geo locations of two neighbors nodes in graph g
     * and sends them to method that checks if this pokemon is on the edge between them.
     *
     * @param s represents the key of the source node.
     * @param d represents the key of the destination node.
     * @param g represents a given graph.
     * @return what the method that checks if the pokemon is on the edge between s-->d returns.
     */
    private boolean isOnEdge(int s, int d, directed_weighted_graph g) {
        geo_location src = g.getNode(s).getLocation();
        geo_location dest = g.getNode(d).getLocation();
        return isOnEdge(src, dest);
    }

    /**
     * Checks if this pokemon can be on the given edge.
     *
     * @param e represents the given edge.
     * @param g represents the given graph.
     * @return true if this pokemon is on e, false if not.
     */
    private boolean isOnEdge(edge_data e, directed_weighted_graph g) {
        int src = g.getNode(e.getSrc()).getKey();
        int dest = g.getNode(e.getDest()).getKey();
        if(type < 0 && dest > src) {
            return false;
        }
        if(type > 0 && src > dest) {
            return false;
        }
        return isOnEdge(src, dest, g);
    }

    /**
     * ToString method.
     *
     * @return String which represents the value and type of this pokemon.
     */
    public String toString() {return "F:{v="+value+", t="+type+"}";}

    /**
     * Class which implements the Comparator<T> interface,
     * used for startPositionOfAgents() method in Arena class.
     */
    static class pokemonsComparator implements Comparator<CL_Pokemon> {

        /**
         * Overriding compare() method of Comparator.
         *
         * @return
         */
        @Override
        public int compare(CL_Pokemon p1, CL_Pokemon p2) {
            return Double.compare(p1.getValue(), p2.getValue());
        }
    }
}
