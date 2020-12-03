package api;

/**
 * This class represents the set of operations applicable on a
 * directional edge(src,dest) in a (directional) weighted graph.
 *
 * @authors Liel.Vaknin & Renana.Levy.
 *
 */
public class EdgeData implements edge_data{

    private int src;
    private int dest;
    private double edgeWeight;
    private String edgeInfo;
    private int edgeTag;

    public EdgeData(int src,int dest, double edgeWeight){
        this.src = src;
        this.dest = dest;
        this.edgeWeight = edgeWeight;
    }

    /**
     * The id of the source node of this edge.
     * @return
     */
    public int getSrc(){
        return this.src;
    }

    /**
     * The id of the destination node of this edge
     * @return
     */
    public int getDest(){
        return this.dest;
    }

    /**
     * @return the weight of this edge (positive value).
     */
    public double getWeight(){
        return this.edgeWeight;
    }

    /**
     * Returns the remark (meta data) associated with this edge.
     * @return
     */
    public String getInfo(){
        return this.edgeInfo;
    }

    /**
     * Allows changing the remark (meta data) associated with this edge.
     * @param s
     */
    public void setInfo(String s){
        this.edgeInfo = s;
    }

    /**
     * Temporal data (aka color: e,g, white, gray, black)
     * which can be used be algorithms
     * @return
     */
    public int getTag(){
        return this.edgeTag;
    }

    /**
     * This method allows setting the "tag" value for temporal marking an edge - common
     * practice for marking by algorithms.
     * @param t - the new value of the tag
     */
    public void setTag(int t){
        this.edgeTag = t;
    }
}

