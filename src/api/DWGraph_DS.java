package api;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/**
 * This class represents a directional weighted graph.
 * The interface has a road-system or communication network in mind -
 * and should support a large number of nodes (over 100,000).
 * The implementation should be based on an efficient compact representation
 * (should NOT be based on a n*n matrix).
 *
 */
public class DWGraph_DS implements directed_weighted_graph{

    private int MC;
    private int nodeSize;
    private int edgeSize;

    private HashMap<Integer, node_data> nodes;
    private HashMap<Integer, HashMap<Integer, edge_data>> edges;

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
     * returns the node_data by the node_id,
     * @param key - the node_id
     * @return the node_data by the node_id, null if none.
     */
    public node_data getNode(int key){ return this.nodes.get(key);}

    /**
     * returns the data of the edge (src,dest), null if none.
     * Note: this method should run in O(1) time.
     * @param src
     * @param dest
     * @return
     */
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
    public void addNode(node_data n){
        if (!this.nodes.containsKey(n.getKey())) {
            this.nodes.put(n.getKey(), n);
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
    public node_data removeNode(int key){
        if (!this.nodes.containsKey(key))
            return null;
        Iterator<edge_data> it = this.getE(key).iterator();
        while(it.hasNext()){
            edge_data edge = it.next();
            removeEdge(key, edge.getDest());
        }
        node_data n = nodes.get(key);
        this.nodes.remove(key);
        nodeSize--;
        MC++;
        return n;
    }

    /**
     * Deletes the edge from the graph,
     * Note: this method should run in O(1) time.
     * @param src
     * @param dest
     * @return the data of the removed edge (null if none).
     */
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
    public int nodeSize(){return this.nodeSize;}

    /**
     * Returns the number of edges (assume directional graph).
     * Note: this method should run in O(1) time.
     * @return
     */
    public int edgeSize(){return this.edgeSize;}

    /**
     * Returns the Mode Count - for testing changes in the graph.
     * @return
     */
    public int getMC(){return this.MC;}
}
