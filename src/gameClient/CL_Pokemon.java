package gameClient;

import api.*;
import java.util.Comparator;
import java.util.Iterator;

public class CL_Pokemon implements pokemon {

    public static final double EPS1 = 0.001, EPS2=EPS1*EPS1, EPS=EPS2;
    private int id;
    private double value;
    private int type;
    private geo_location pos;
    private edge_data edge;

    public CL_Pokemon(double value, int type, geo_location pos, directed_weighted_graph g){
        this.value = value;
        this.type = type;
        this.pos = pos;
        updateEdge(this, g);
    }

    @Override
    public edge_data getEdge() {
        return edge;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public geo_location getPos() {
        return pos;
    }

    /**
     * This method finds on which edge the given pokemon is on and sets this edge to be the pokemon's edge.
     *
     * @param pok represents a given pokemon.
     * @param g represents a given graph.
     */
    public static void updateEdge(CL_Pokemon pok, directed_weighted_graph g) {
        //	oop_edge_data ans = null;
        Iterator<node_data> it1 = g.getV().iterator();
        while(it1.hasNext()) {
            node_data v = it1.next();
            Iterator<edge_data> it2 = g.getE(v.getKey()).iterator();
            while(it2.hasNext()) {
                edge_data e = it2.next();
                boolean f = isOnEdge(pok.getPos(), e, pok.getType(), g);
                if(f) {
                    pok.setEdge(e);
                }
            }
        }
    }

    private void setEdge(edge_data e) {
        this.edge = e;
    }

    private static boolean isOnEdge(geo_location p, geo_location src, geo_location dest) {
        boolean ans = false;
        double dist = src.distance(dest);
        double d1 = src.distance(p) + p.distance(dest);
        if(dist > d1 - EPS2) {
            ans = true;
        }
        return ans;
    }

    private static boolean isOnEdge(geo_location p, int s, int d, directed_weighted_graph g) {
        geo_location src = g.getNode(s).getLocation();
        geo_location dest = g.getNode(d).getLocation();
        return isOnEdge(p, src, dest);
    }

    private static boolean isOnEdge(geo_location p, edge_data e, int type, directed_weighted_graph g) {
        int src = g.getNode(e.getSrc()).getKey();
        int dest = g.getNode(e.getDest()).getKey();
        if(type < 0 && dest > src) {
            return false;
        }
        if(type > 0 && src > dest) {
            return false;
        }
        return isOnEdge(p, src, dest, g);
    }

//    public edge_data pokemonEdge(directed_weighted_graph graph) {
//        Boolean pokemonOnRisingEdge = true;
//        Boolean pokemonOnFallingEdge = true;
//        if (this.type == 1) {
//            pokemonOnFallingEdge = false;
//        } else if (this.type == -1) {
//            pokemonOnRisingEdge = false;
//        }
//        Iterator<node_data> it1 = graph.getV().iterator();
//        while (it1.hasNext()) {
//            node_data node = it1.next();
//            Iterator<edge_data> it2 = graph.getE(node.getKey()).iterator();
//            while (it2.hasNext()) {
//                edge_data edge = it2.next();
//                if (pokemonOnRisingEdge && (edge.getSrc() < edge.getDest())) {
//                    if(this.pos.y() == calculatePokemonEdge(edge, graph)){
//                        return edge;
//                    }
//                } else if (pokemonOnFallingEdge && (edge.getSrc() > edge.getDest())) {
//                    if(this.pos.y() == calculatePokemonEdge(edge, graph)){
//                        return edge;
//                    }
//                }
//            }
//        }
//        return null;
//    }
//
//    private double calculatePokemonEdge(edge_data edge, directed_weighted_graph graph){
//        double subtruction_y1_y2 = graph.getNode(edge.getSrc()).getLocation().y() - graph.getNode(edge.getDest()).getLocation().y();
//        double subtruction_x1_x2 = graph.getNode(edge.getSrc()).getLocation().x() - graph.getNode(edge.getDest()).getLocation().x();
//        double m = subtruction_y1_y2 / subtruction_x1_x2;
//        double n = graph.getNode(edge.getSrc()).getLocation().y() - (m * graph.getNode(edge.getSrc()).getLocation().x());
//        return m * this.pos.x() + n;
//    }

    /**
     * ToString method.
     *
     * @return
     */
    public String toString() {return "F:{v="+value+", t="+type+"}";}

    /**
     * Class which implements the Comparator<T> interface,
     * used for startPositionOfAgents method in Arena class.
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
