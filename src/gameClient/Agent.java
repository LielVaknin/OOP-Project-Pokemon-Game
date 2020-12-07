package gameClient;

import com.google.gson.*;
import gameClient.util.Point3D;
import java.lang.reflect.Type;

public class Agent implements JsonDeserializer<Agent> {
    private int id;
    private double value;
    private int src;
    private int dest;
    private double speed;
    private Point3D pos;

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
}
