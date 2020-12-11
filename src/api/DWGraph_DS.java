package api;

import java.util.Collection;
import java.util.HashMap;

/**
 * This class represents a directional weighted graph.
 * @authors Liel.Vaknin & Renana.Levy.
 */
public class DWGraph_DS implements directed_weighted_graph{

    private int MC;
    private int nodeSize;
    private int edgeSize;

    public HashMap<Integer, node_data> nodes;
    private HashMap<Integer, HashMap<Integer, edge_data>> edges;

    private int ID;

    /**
     * Default constructor.
     */
    public DWGraph_DS() {
        this.MC = 0;
        this.nodeSize = 0;
        this.edgeSize = 0;
        this.nodes = new HashMap<>();
        this.edges = new HashMap<>();
    }

    /**
     * Copy constructor.
     */
    public DWGraph_DS(directed_weighted_graph graph) {
        for (node_data n: graph.getV()){
            node_data newN= new NodeData(n);
            this.nodes.put(newN.getKey(), newN);

            if(graph.getE(n.getKey()) != null) {
                HashMap<Integer, edge_data> newEdges = new HashMap<>();
                for (edge_data e: graph.getE(n.getKey())){
                    edge_data newE = new EdgeData(e);
                    newEdges.put(e.getDest(), newE);
                }
                this.edges.put(n.getKey(), newEdges);
            }
        }
        this.MC = graph.getMC();
        this.nodeSize = graph.nodeSize();
        this.edgeSize = graph.edgeSize();
    }

    /**
     * returns the node_data by the node_id,
     * @param key - the node_id
     * @return the node_data by the node_id, null if none.
     */
    @Override
    public node_data getNode(int key){ return this.nodes.get(key);}

    /**
     * returns the data of the edge (src,dest), null if none.
     * Note: this method should run in O(1) time.
     * @param src
     * @param dest
     * @return
     */
    @Override
    public edge_data getEdge(int src, int dest){
        if (!this.nodes.containsKey(src) || !this.nodes.containsKey(dest) || src == dest)
            return null;
        return this.edges.get(src).get(dest);
    }

    /**
     * adds a new node to the graph with the given node_data.
     * Note: this method should run in O(1) time.
     * @param n
     */
    @Override
    public void addNode(node_data n){
        ((NodeData)n).setKey(ID);
        if (!this.nodes.containsKey(n.getKey())) {
            this.nodes.put(n.getKey(), n);
            ID++;
            MC++;
            nodeSize++;
        }
    }

    /**
     * Connects an edge with weight w between node src to node dest.
     * * Note: this method should run in O(1) time.
     * @param src - the source of the edge.
     * @param dest - the destination of the edge.
     * @param w - positive weight representing the cost (aka time, price, etc) between src-->dest.
     */
    @Override
    public void connect(int src, int dest, double w){
        if (!this.nodes.containsKey(src) || !this.nodes.containsKey(dest) || src == dest || w < 0)
            return;
        if(this.edges.get(src) == null) {
            HashMap<Integer, edge_data> innerHashMap = new HashMap<>();
            this.edges.put(src, innerHashMap);
        }
        if (getEdge(src, dest) == null){
            edge_data newEdge = new EdgeData(src, dest, w);
            this.edges.get(src).put(dest, newEdge);
            edgeSize++;
            MC++;
        }
    }

    /**
     * This method returns a pointer (shallow copy) for the
     * collection representing all the nodes in the graph.
     * Note: this method should run in O(1) time.
     * @return Collection<node_data>
     */
    @Override
    public Collection<node_data> getV(){
        return this.nodes.values();
    }

    /**
     * This method returns a pointer (shallow copy) for the
     * collection representing all the edges getting out of
     * the given node (all the edges starting (source) at the given node).
     * Note: this method should run in O(k) time, k being the collection size.
     * @return Collection<edge_data>
     */
    @Override
    public Collection<edge_data> getE(int node_id){
        return this.edges.get(node_id).values();
    }

    /**
     * Deletes the node (with the given ID) from the graph -
     * and removes all edges which starts or ends at this node.
     * This method should run in O(k), V.degree=k, as all the edges should be removed.
     * @return the data of the removed node (null if none).
     * @param key
     */
    // TODO: To fix this method.
    @Override
    public node_data removeNode(int key) {
        if (!this.nodes.containsKey(key))
            return null;
        for (node_data n : this.getV()) {
            if (this.edges.get(n.getKey()).containsKey(key)) {
                this.removeEdge(n.getKey(), key);
            }
        }
        nodeSize--;
        MC++;
        return this.nodes.remove(key);

//        Iterator<edge_data> it = this.getE(key).iterator();
//        while(it.hasNext()){
//            edge_data edge = it.next();
//            removeEdge(edge.getDest(), key);
//        }
//        node_data n = nodes.get(key);
//        this.nodes.remove(key);
//        nodeSize--;
//        MC++;
//        return n;
    }

    /**
     * Deletes the edge from the graph,
     * Note: this method should run in O(1) time.
     * @param src
     * @param dest
     * @return the data of the removed edge (null if none).
     */
    @Override
    public edge_data removeEdge(int src, int dest){
        if (!this.nodes.containsKey(src) || !this.nodes.containsKey(dest) || src == dest)
            return null;
        edge_data edge = edges.get(src).get(dest);
        if (this.edges.get(src).containsKey(dest)) {
            this.edges.get(src).remove(dest);
            edgeSize--;
            MC++;
        }
        return edge;
    }

    /** Returns the number of vertices (nodes) in the graph.
     * Note: this method should run in O(1) time.
     * @return
     */
    @Override
    public int nodeSize(){return this.nodeSize;}

    /**
     * Returns the number of edges (assume directional graph).
     * Note: this method should run in O(1) time.
     * @return
     */
    @Override
    public int edgeSize(){return this.edgeSize;}

    /**
     * Returns the Mode Count - for testing changes in the graph.
     * @return
     */
    @Override
    public int getMC(){return this.MC;}

    @Override
    public String toString(){
        String g = "[";
        int siN = 0;
        for(int n: this.nodes.keySet()){
            g = g+"("+n+": {";
            int siE = 0;
            for (edge_data e: getE(n)){
                if(siE == getE(n).size()-1){
                    g = g+e.getDest()+"- weight = "+e.getWeight()+"}";
                }else {
                    g = g+e.getDest()+"- weight = "+e.getWeight()+", ";
                }
                siE++;
            }
            g = g+"}";
            if(siN == this.nodes.keySet().size()-1){
                g = g+"]";
            } else {
                g = g+" , ";
            }
            siN++;
        }
        return g;
    }
}
