package gameClient;

import api.*;

/**
 * This class represents an agent in the game.
 *
 * @authors Liel.Vaknin & Renana.Levy.
 */
public class CL_Agent {
    private int id;
    private double value;
    private int src;
    private int dest;
    private double speed;
    private geo_location pos;

    /**
     * Constructor.
     *
     * @param id
     * @param value
     * @param src
     * @param dest
     * @param speed
     * @param pos
     */
    public CL_Agent(int id, double value, int src, int dest, double speed, geo_location pos){
        this.id = id;
        this.value = value;
        this.src = src;
        this.dest = dest;
        this.speed = speed;
        this.pos = pos;
    }

    /**
     * Returns the id of this agent.
     *
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the value of this agent.
     *
     * @return value.
     */
    public double getValue() {
        return value;
    }

    /**
     * Returns the key of the source node which this agent is currently stands on.
     *
     * @return src.
     */
    public int getSrc() {
        return src;
    }

    /**
     * Allows to set the key of the source node which this agent is currently stands on.
     *
     * @param src represents the given key of the source node.
     */
    public void setSrc(int src) {
        this.src = src;
    }

    /**
     * Returns the key of the destination node which this agent is about to reach.
     *
     * @return dest.
     */
    public int getDest() {
        return dest;
    }

    /**
     * Allows to set the key of the destination node which this agent is about to reach.
     *
     * @param dest represents the given key of the destination node.
     */
    public void setDest(int dest) {
        this.dest = dest;
    }

    /**
     * Returns the speed of this agent.
     *
     * @return speed.
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Returns geo location <x,y,z>, aka Point3D, of this agent.
     *
     * @return pos.
     */
    public geo_location getPos() {
        return pos;
    }

    /**
     * Allows to set the geo location <x,y,z>, aka Point3D, of this agent.
     *
     * @param pos represents the given geo location.
     */
    public void setPos(geo_location pos) {
        this.pos = pos;
    }
}
