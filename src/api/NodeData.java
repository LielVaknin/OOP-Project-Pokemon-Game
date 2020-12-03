package api;

/**
 * This class represents the set of operations applicable on a
 * node (vertex) in a (directional) weighted graph.
 *
 * @authors Liel.Vaknin & Renana.Levy.
 *
 */
public class NodeData implements node_data{

    private int key;
    private static int keyCounter = 0;
    private String nodeInfo;
    private int nodeTag;
    private double nodeWeight;
    private geo_location nodeGeoLocation;

    /**
     * Default constructor.
     */
    public NodeData() {
        this.key = keyCounter;
        keyCounter++;
        this.nodeInfo = "";
        this.nodeTag= 0;
        this.nodeWeight =0;
    }

    /**
     * Copy constructor - Builds a new node from a given node.
     *
     * @param node represents the given node.
     */
    public NodeData(node_data node) {
        this.key = node.getKey();
        this.nodeInfo = node.getInfo();
        this.nodeTag = node.getTag();
       // this.nodeGeoLocation = node.getLocation();
    }

    /**
     * Returns the key (id) associated with this node.
     * @return
     */
    public int getKey(){
        return this.key;
    }

    /** Returns the location of this node, if
     * none return null.
     *
     * @return
     */
    public geo_location getLocation(){
        return this.nodeGeoLocation;
    }

    /** Allows changing this node's location.
     * @param p - new new location (position) of this node.
     */
    public void setLocation(geo_location p){
        this.nodeGeoLocation = p;
    }

    /**
     * Returns the weight associated with this node.
     * @return
     */
    public double getWeight(){
        return this.nodeWeight;
    }

    /**
     * Allows changing this node's weight.
     * @param w - the new weight
     */
    public void setWeight(double w){
        this.nodeWeight = w;
    }

    /**
     * Returns the remark (meta data) associated with this node.
     * @return
     */
    public String getInfo(){
        return this.nodeInfo;
    }

    /**
     * Allows changing the remark (meta data) associated with this node.
     * @param s
     */
    public void setInfo(String s){
        this.nodeInfo = s;
    }

    /**
     * Temporal data (aka color: e,g, white, gray, black)
     * which can be used be algorithms
     * @return
     */
    public int getTag(){
        return this.nodeTag;
    }

    /**
     * Allows setting the "tag" value for temporal marking an node - common
     * practice for marking by algorithms.
     * @param t - the new value of the tag
     */
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
}


