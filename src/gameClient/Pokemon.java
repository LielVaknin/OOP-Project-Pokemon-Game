package gameClient;

import com.google.gson.*;
import gameClient.util.Point3D;
import java.lang.reflect.Type;

public class Pokemon implements JsonDeserializer<Pokemon> {

    private double value;
    private int type;
    private Point3D pos;

    @Override
    public Pokemon deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject agentJ = jsonElement.getAsJsonObject();
        this.value = agentJ.get("value").getAsDouble();
        this.type = agentJ.get("type").getAsInt();
        String st = agentJ.get("pos").getAsString();
        this.pos =  new Point3D(st);
        return this;
    }
}
