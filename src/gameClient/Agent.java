package gameClient;

import api.*;
import com.google.gson.*;
import gameClient.util.Point3D;
import java.lang.reflect.Type;

public class Agent implements JsonDeserializer<Agent> {
    private int id;
    private double value;
    private int src;
    private int dest;
    private double speed;
    private geo_location pos;

    @Override
    public Agent deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject agentJ = jsonElement.getAsJsonObject();
        this.id = agentJ.get("id").getAsInt();
        this.value = agentJ.get("value").getAsDouble();
        this.src = agentJ.get("src").getAsInt();
        this.dest = agentJ.get("dest").getAsInt();
        this.speed = agentJ.get("speed").getAsDouble();
        String point = agentJ.get("pos").getAsString();
        pos = new Point3D(point);
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getSrc() {
        return src;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    public int getDest() {
        return dest;
    }

    public void setDest(int dest) {
        this.dest = dest;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Point3D getPos() {
        return pos;
    }

    public void setPos(Point3D pos) {
        this.pos = pos;
    }
}
