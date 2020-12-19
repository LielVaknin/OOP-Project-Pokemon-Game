package api;

import java.util.Objects;

/**
 * This class represents the set of operations applicable on a
 * node (vertex) in a directed weighted graph.
 *
 * @authors Liel.Vaknin & Renana.Levy.
 */
public class NodeData implements node_data, Comparable<node_data>{

    private int key;
    private String nodeInfo;
    private int nodeTag;
    private double nodeWeight;
    private geo_location nodeGeoLocation;
    node_data prev;
    double dist;

    /**
     * Default constructor.
     */
    public NodeData() {
        this(-1);
    }

    /**
     * Constructor
     *
     * @param id
     */
    public NodeData(int id){
        this.key= id;
        this.nodeInfo = "";
        this.nodeTag= 0;
        this.nodeWeight =0;
    }

    /**
     * Copy constructor - Performs a deep copy of a given node.
     *
     * @param node represents the given node.
     */
    public NodeData(node_data node) {
        this.key = node.getKey();
        this.nodeInfo = node.getInfo();
        this.nodeTag = node.getTag();
        this.nodeWeight= node.getWeight();
        if (node.getLocation() != null)
            this.nodeGeoLocation = new GeoLocation(node.getLocation());
    }


    void setKey(int k){
        if(this.key == -1)
            this.key= k;
    }

    /**
     * Returns the key (id) associated with this node.
     *
     * @return key.
     */
    @Override
    public int getKey(){
        return this.key;
    }

    /**
     * Returns the location of this node, if
     * none returns null.
     *
     * @return nodeGeoLocation.
     */
    @Override
    public geo_location getLocation(){
        return this.nodeGeoLocation;
    }

    /**
     * Allows changing this node's geo location.
     *
     * @param p represents a new geo location (position) for changing.
     */
    @Override
    public void setLocation(geo_location p){
        this.nodeGeoLocation = p;
    }

    /**
     * Returns the weight associated with this node.
     *
     * @return nodeWeight.
     */
    @Override
    public double getWeight(){
        return this.nodeWeight;
    }

    /**
     * Allows changing this node's weight.
     *
     * @param w represents the new weight.
     */
    @Override
    public void setWeight(double w){
        this.nodeWeight = w;
    }

    /**
     * Returns the remark (meta data) associated with this node.
     *
     * @return nodeInfo.
     */
    @Override
    public String getInfo(){
        return this.nodeInfo;
    }

    /**
     * Allows changing the remark (meta data) associated with this node.
     *
     * @param s represents the new nodeInfo.
     */
    @Override
    public void setInfo(String s){
        this.nodeInfo = s;
    }

    /**
     * Returns temporal data which can be used by algorithms.
     *
     * @return nodeTag.
     */
    @Override
    public int getTag(){
        return this.nodeTag;
    }

    /**
     * Allows setting the "tag" value for temporal marking an node - common
     * practice for marking by algorithms.
     *
     * @param t represents the new value of nodeTag.
     */
    @Override
    public void setTag(int t){
        this.nodeTag = t;
    }

    /**
     * ToString method.
     *
     * @return String which represents the key of this node.
     */
    @Override
    public String toString() {
        return "" + this.key;
    }

    /**
     * Equals method.
     *
     * @param o represents a given object.
     * @return true if this object and a given object are equals, false if not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || !(o instanceof node_data))
            return false;
        node_data nodeData = (node_data) o;
//        return key == nodeData.key && nodeTag == nodeData.nodeTag && Double.compare(nodeData.nodeWeight, nodeWeight) == 0 && Double.compare(nodeData.dist, dist) == 0 && Objects.equals(nodeInfo, nodeData.nodeInfo) && Objects.equals(nodeGeoLocation, nodeData.nodeGeoLocation) && Objects.equals(prev, nodeData.prev);
        if(this.key != nodeData.getKey() || this.nodeTag!=nodeData.getTag() || (!(this.nodeInfo.equals(nodeData.getInfo()))) || (this.nodeWeight != nodeData.getWeight()))
            return false;
        geo_location gl = nodeData.getLocation();
        if((gl == null) && this.getLocation() == null)
            return true;
        if((this.nodeGeoLocation.x() != gl.x()) || (this.nodeGeoLocation.y() != gl.y()) || (this.nodeGeoLocation.z() != gl.z()))
            return false;
        return true;
    }

    /**
     * HashCode method.
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(key, nodeInfo, nodeTag, nodeWeight, nodeGeoLocation, prev, dist);
    }

    /**
     * CompareTo method.
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(node_data o) {
        if(this.dist > ((NodeData)o).dist)
            return 1;
        else if(this.dist < ((NodeData)o).dist)
            return -1;
        else
            return 0;
    }
}
