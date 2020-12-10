package gameClient;

import api.*;
import com.google.gson.*;
import gameClient.util.Point3D;
import org.json.JSONObject;

import java.lang.reflect.Type;

public class Agent {
    private int id;
    private double value;
    private int src;
    private int dest;
    private double speed;
    private geo_location pos;

//    public Agent(int id, double value, int src, int dest, double speed, Point3D pos){
//        this.id = id;
//        this.value = value;
//        this.src = src;
//        this.dest = dest;
//        this.speed = speed;
//        this.pos = new Point3D(pos.x(), pos.y(), pos.z());
//    }
/*


    @Override
    public Agent deserialize(String Json) {
        JsonObject agentJ = jsonElement.getAsJsonObject();
        this.id = agentJ.get("id").getAsInt();
        this.value = agentJ.get("value").getAsDouble();
        this.src = agentJ.get("src").getAsInt();
        this.dest = agentJ.get("dest").getAsInt();
        this.speed = agentJ.get("speed").getAsDouble();
        String point = agentJ.get("pos").getAsString();
        pos = new Point3D(point);
        return this;
    }*/

    public Agent(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject a = jsonObject.getJSONObject("Agent");
            this.id = a.getInt("id");
            this.value = a.getInt("value");
            this.src = a.getInt("src");
            this.dest = a.getInt("dest");
            this.speed = a.getDouble("speed");
            this.pos = new GeoLocation(a.getString("pos"));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
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

    public geo_location getPos() {
        return pos;
    }

    public void setPos(Point3D pos) {
        this.pos = pos;
    }

}
