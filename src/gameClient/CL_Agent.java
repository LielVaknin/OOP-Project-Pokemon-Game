package gameClient;

import api.*;
import com.google.gson.*;
import org.json.JSONObject;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CL_Agent implements agent{
    private int id;
    private double value;
    private int src;
    private int dest;
    private double speed;
    private geo_location pos;
    private List<node_data> shortestPathToPokemon;

    public CL_Agent(int id, double value, int src, int dest, double speed, geo_location pos){
        this.id = id;
        this.value = value;
        this.src = src;
        this.dest = dest;
        this.speed = speed;
        this.pos = pos;
//        this.shortestPathToPokemon = new LinkedList<>();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public int getSrc() {
        return src;
    }

    @Override
    public void setSrc(int src) {
        this.src = src;
    }

    @Override
    public int getDest() {
        return dest;
    }

    @Override
    public void setDest(int dest) {
        this.dest = dest;
    }

    @Override
    public double getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public geo_location getPos() {
        return pos;
    }

    @Override
    public void setPos(geo_location pos) {
        this.pos = pos;
    }

    public List<node_data> getShortestPathToPokemon() {
        return shortestPathToPokemon;
    }

    public void setShortestPathToPokemon(List<node_data> shortestPathToPokemon) {
        this.shortestPathToPokemon = shortestPathToPokemon;
    }
}
