package gameClient;

import api.edge_data;
import api.geo_location;

public interface pokemon {

    /**
     * Returns the edge where which the pokemon is stands on.
     * @return edge.
     */
    public edge_data getEdge();

    /**
     * Returns the value of the pokemon.
     * @return value.
     */
    public double getValue();

    /**
     * Returns the type of the pokemon.
     * if type == -1 the pokemon is on falling edge,
     * if type == 1 the pokemon is on rising edge.
     * @return type.
     */
    public int getType();

    /**
     * Returns geo location <x,y,z>, aka Point3D of the pokemon.
     * @return pos.
     */
    public geo_location getPos();
}
