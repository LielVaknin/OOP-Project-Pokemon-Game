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
     * Returns the id of the agent.
     *
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * Allows to set the id of the agent.
     *
     * @param id represents the given id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the value of the agent.
     *
     * @return value.
     */
    public double getValue() {
        return value;
    }

    /**
     * Allows to set the value of the agent.
     *
     * @param value represents the given value.
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Returns the id of the source node which the agent is currently stands on.
     *
     * @return src.
     */
    public int getSrc() {
        return src;
    }

    /**
     * Allows to set the id of the source node which the agent is currently stands on.
     *
     * @param src represents the given id of this source node.
     */
    public void setSrc(int src) {
        this.src = src;
    }

    /**
     * Returns the id of the destination node which the agent is about to reach.
     *
     * @return dest.
     */
    public int getDest() {
        return dest;
    }

    /**
     * Allows to set the id of the destination node which the agent is about to reach.
     *
     * @param dest represents the given destination node.
     */
    public void setDest(int dest) {
        this.dest = dest;
    }

    /**
     * Returns the speed of the agent.
     *
     * @return speed.
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Allows to set the speed of the agent.
     *
     * @param speed represents the speed of the agent.
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * Returns geo location <x,y,z>, aka Point3D of the agent.
     *
     * @return pos.
     */
    public geo_location getPos() {
        return pos;
    }

    /**
     * Allows to set the geo location <x,y,z>, aka Point3D of the agent.
     *
     * @param pos represents the given geo location.
     */
    public void setPos(geo_location pos) {
        this.pos = pos;
    }
}
