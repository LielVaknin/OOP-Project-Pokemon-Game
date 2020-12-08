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

    public NodeData(int id){
        this.key= -1;
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
        this.nodeWeight= node.getWeight();
        // this.nodeGeoLocation = node.getLocation();
    }


    void setKey(int k){
        if(this.key == -1)
            this.key= k;
    }
    /**
     * Returns the key (id) associated with this node.
     * @return
     */
    @Override
    public int getKey(){
        return this.key;
    }

    /** Returns the location of this node, if
     * none return null.
     *
     * @return
     */
    @Override
    public geo_location getLocation(){
        return this.nodeGeoLocation;
    }

    /**
     * Allows changing this node's location.
     * @param p - new new location (position) of this node.
     */
    @Override
    public void setLocation(geo_location p){
        this.nodeGeoLocation = p;
    }

    /**
     * Returns the weight associated with this node.
     * @return
     */
    @Override
    public double getWeight(){
        return this.nodeWeight;
    }

    /**
     * Allows changing this node's weight.
     * @param w - the new weight
     */
    @Override
    public void setWeight(double w){
        this.nodeWeight = w;
    }

    /**
     * Returns the remark (meta data) associated with this node.
     * @return
     */
    @Override
    public String getInfo(){
        return this.nodeInfo;
    }

    /**
     * Allows changing the remark (meta data) associated with this node.
     * @param s
     */
    @Override
    public void setInfo(String s){
        this.nodeInfo = s;
    }

    /**
     * Temporal data (aka color: e,g, white, gray, black)
     * which can be used be algorithms
     * @return
     */
    @Override
    public int getTag(){
        return this.nodeTag;
    }

    /**
     * Allows setting the "tag" value for temporal marking an node - common
     * practice for marking by algorithms.
     * @param t - the new value of the tag
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
}


