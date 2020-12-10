package gameClient;

import api.edge_data;
import api.geo_location;

public interface pokemon {

    public edge_data getEdge();

    public double getValue();

    public int getType();

    public geo_location getPos();
}
