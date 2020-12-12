package api;

import java.util.Objects;

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
     * Copy constructor
     *
     * @param e
     */
    public EdgeData(edge_data e){
        this.src =  e.getSrc();
        this.dest = e.getDest();
        this.edgeWeight = e.getWeight();
        this.edgeInfo = e.getInfo();
        this.edgeTag = e.getTag();
    }

    /**
     * The id of the source node of this edge.
     * @return
     */
    @Override
    public int getSrc(){
        return this.src;
    }

    /**
     * The id of the destination node of this edge
     * @return
     */
    @Override
    public int getDest(){
        return this.dest;
    }

    /**
     * @return the weight of this edge (positive value).
     */
    @Override
    public double getWeight(){
        return this.edgeWeight;
    }

    /**
     * Returns the remark (meta data) associated with this edge.
     * @return
     */
    @Override
    public String getInfo(){
        return this.edgeInfo;
    }

    /**
     * Allows changing the remark (meta data) associated with this edge.
     * @param s
     */
    @Override
    public void setInfo(String s){
        this.edgeInfo = s;
    }

    /**
     * Temporal data (aka color: e,g, white, gray, black)
     * which can be used be algorithms
     * @return
     */
    @Override
    public int getTag(){
        return this.edgeTag;
    }

    /**
     * This method allows setting the "tag" value for temporal marking an edge - common
     * practice for marking by algorithms.
     * @param t - the new value of the tag
     */
    @Override
    public void setTag(int t){
        this.edgeTag = t;
    }

    /**
     * Equals method.
     *
     * @param o represents a given object.
     * @return true if this object and a given object are equals, false if not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EdgeData edgeData = (EdgeData) o;
        return src == edgeData.src && dest == edgeData.dest && Double.compare(edgeData.edgeWeight, edgeWeight) == 0 && edgeTag == edgeData.edgeTag && Objects.equals(edgeInfo, edgeData.edgeInfo);
    }

    /**
     * HashCode method.
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(src, dest, edgeWeight, edgeInfo, edgeTag);
    }
}

