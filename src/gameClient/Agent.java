package gameClient;

import api.*;

/**
 * This interface represents
 */
public interface agent {

    /**
     * Returns the id of the agent.
     * @return id.
     */
    public int getId();

    /**
     * Allows to set the id of the agent.
     * @param id represents the given id.
     */
    public void setId(int id);

    /**
     * Returns the value of the agent.
     * @return value.
     */
    public double getValue();

    /**
     * Allows to set the value of the agent.
     * @param value represents the given value.
     */
    public void setValue(double value);

    /**
     * Returns the id of the source node which is where the agent is currently stands on.
     * @return src.
     */
    public int getSrc();

    /**
     * Allows to set the id of the source node which is where the agent is currently stands on.
     * @param src represents the given id of this source node.
     */
    public void setSrc(int src);

    /**
     * Returns the id of the destination node which is where the agent is about to reach.
     * @return dest.
     */
    public int getDest();

    /**
     * Allows to set the id of the destination node which is where the agent is about to reach.
     * @param dest represents the given destination node.
     */
    public void setDest(int dest);

    /**
     * Returns the speed of the agent.
     * @return speed.
     */
    public double getSpeed();

    /**
     * Allows to set the speed of the agent.
     * @param speed represents the speed of the agent.
     */
    public void setSpeed(double speed);

    /**
     * Returns geo location <x,y,z>, aka Point3D of the agent.
     * @return pos.
     */
    public geo_location getPos();

    /**
     * Allows to set the geo location <x,y,z>, aka Point3D of the agent.
     * @param pos represents the given geo location.
     */
    public void setPos(geo_location pos);
}
