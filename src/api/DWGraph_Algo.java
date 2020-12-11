package api;

import com.google.gson.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This class represents a Directed (positive) Weighted Graph Theory Algorithms including:
 * 0. clone(); (copy)
 * 1. init(graph);
 * 2. isConnected(); // strongly (all ordered pais connected)
 * 3. double shortestPathDist(int src, int dest);
 * 4. List<node_data> shortestPath(int src, int dest);
 * 5. Save(file); // JSON file
 * 6. Load(file); // JSON file
 *
 */
public class DWGraph_Algo implements dw_graph_algorithms {

   private directed_weighted_graph g = new DWGraph_DS();

   /**
    * This method initializes the graph with the set of algorithms operates on.
    *
    * @param g represents the given graph.
    */
   @Override
   public void init(directed_weighted_graph g) {
      this.g = g;
   }

   /**
    * Returns the underlying graph of which this class works.
    *
    * @return g.
    */
   @Override
   public directed_weighted_graph getGraph() {
      return g;
   }

   /**
    * Computes a deep copy of this weighted graph.
    *
    * @return copied graph.
    */
   @Override
   public directed_weighted_graph copy() {
      if (this.g == null)
         return null;
      return new DWGraph_DS(g);
   }

   /**
    *
    * @param graph
    * @param src
    */
   private void dfsVisit(directed_weighted_graph graph, node_data src) {
      if (src == null) {
         return;
      }
      src.setInfo("grey");
      for (edge_data e : graph.getE(src.getKey())) {
         if (graph.getNode(e.getDest()).getInfo().equals("white")) {
            dfsVisit(graph, graph.getNode(e.getDest()));
         }
      }
      src.setInfo("black");
   }

   /**
    * Returns true if and only if (iff) there is a valid path from every node to each
    * other node.
    *
    * @return true if this graph is connected, false if not.
    */
   @Override
   public boolean isConnected() {
      for (node_data n : this.g.getV()) {
         n.setInfo("white");
      }
      int c = 0;
      while ((g.getNode(c)) == null){
         c++;
      }
      dfsVisit(this.g, (g.getNode(c)));
      for (node_data n : this.g.getV()) {
         if (n.getInfo().equals("white"))
            return false;
      }

      directed_weighted_graph gr = this.copy();
      for (node_data n : gr.getV()){
         for (edge_data e : gr.getE(n.getKey())){
            gr.removeEdge(e.getSrc(), e.getDest());
            gr.connect(e.getDest(), e.getSrc(), e.getWeight());
         }
      }

      for (node_data n : gr.getV()) {
         n.setInfo("white");
      }

      dfsVisit(gr, (gr.getNode(c)));
      for (node_data n : gr.getV()) {
         if (n.getInfo().equals("white"))
            return false;
      }
      return true;
   }

   private void DFS(node_data src) {
      for (node_data n : this.g.getV()) {
         n.setInfo("white");
         ((NodeData) n).dist = Double.MAX_VALUE;
      }
      ((NodeData) src).dist = 0;
      dfsVisit(src);
   }

   private void dfsVisit(node_data src) {
      if (src == null) {
         return;
      }
      src.setInfo("grey");
      for (edge_data e : g.getE(src.getKey())) {
         if (((NodeData) g.getNode(e.getDest())).dist > ((NodeData) src).dist + e.getWeight()) {
            ((NodeData) g.getNode(e.getDest())).dist = ((NodeData) src).dist + e.getWeight();
            ((NodeData) g.getNode(e.getDest())).prev = (src);
         }
         if (g.getNode(e.getDest()).getInfo().equals("white")) {
            dfsVisit(g.getNode(e.getDest()));
         }
      }
      src.setInfo("black");
   }

   /**
    * Returns the length of the shortest path between src to dest,
    * if there is no such path --> returns -1, using Dijkstra's algorithm.
    *
    * @param src represents the starting point.
    * @param dest represents the ending point.
    * @return length of shortest path.
    */
   @Override
   public double shortestPathDist(int src, int dest) {
      if (g.getNode(src) == null || g.getNode(dest) == null || src == dest) {
         return -1;
      }
      DFS(g.getNode(src));
      if (g.getNode(dest).getInfo().equals("white")) {
         return -1;
      }
      return ((NodeData) g.getNode(dest)).dist;
   }

   /**
    * Returns the shortest path between src to dest - as an ordered List of nodes:
    * src --> n1 --> n2 -->...dest,
    * if no such path --> returns null.
    * Using Dijkstra's algorithm (shortestPathDist).
    *
    * @param src represents the starting point.
    * @param dest represents the ending point.
    * @return the shortest path as an ordered list of nodes.
    */
   @Override
   public List<node_data> shortestPath(int src, int dest) {
      List<node_data> path = new LinkedList<>();
      double dist = shortestPathDist(src, dest);
      if (dist == -1 || dist == Double.MAX_VALUE) {
         if (src == dest && g.getNode(dest) != null) {
            path.add(g.getNode(dest));
            return path;
         }
         return null;
      } else {
         path.add(g.getNode(dest));
         node_data pr = ((NodeData) g.getNode(dest)).prev;
         int node0 = dest;
         while (node0 != src) {
            path.add(0, pr);
            pr = ((NodeData) g.getNode(node0)).prev;
            node0 = pr.getKey();
         }
         return path;
      }
   }

   /**
    * Saves this weighted (directed) graph to the given
    * file name - in JSON format.
    * @param file - the file name (may include a relative path).
    * @return true - iff the file was successfully saved.
    */
   @Override
   public boolean save(String file) {
      Gson gson = new Gson();
      String jsonG = gson.toJson(g);
      System.out.println(jsonG);
      try {
         FileWriter saveGraph = new FileWriter(file);
         saveGraph.write(jsonG);
         saveGraph.close();
         return true;
      } catch (Exception e) {
         return false;
      }
   }

   /**
    * Loads a graph to this graph algorithm.
    * If the file was successfully loaded - the underlying graph
    * of this class will be changed (to the loaded one), in case the
    * graph was not loaded the original graph should remain "as is".
    * @param file - file name of JSON file.
    * @return true - iff the graph was successfully loaded.
    */
   @Override
   public boolean load(String file) {
      try {
         Gson gson = new Gson();
         FileReader lodeGraph = new FileReader(file);
         JsonObject jsonOb = gson.fromJson(lodeGraph, JsonObject.class);
         initFromJson(jsonOb);
         return true;
      } catch (FileNotFoundException e) {
         e.printStackTrace();
         return false;
      }
   }

   private void initFromJson(JsonObject json){
      directed_weighted_graph newGraph = new DWGraph_DS();
      loadNodes(json, newGraph);
      loadEdges(json, newGraph);
      this.g = newGraph;
   }

   private void loadNodes(JsonObject json, directed_weighted_graph newGraph){
      JsonObject jsonNodes = json.get("nodes").getAsJsonObject();
      for (Map.Entry<String, JsonElement> node : jsonNodes.entrySet()){
         int hashKey = Integer.parseInt(node.getKey()); //the key of the hashmap
         JsonObject jsonN = node.getValue().getAsJsonObject();
         double nodeWeight = jsonN.get("nodeWeight").getAsDouble();
         JsonObject jsonLocation = jsonN.get("nodeGeoLocation").getAsJsonObject();
         int x = jsonLocation.get("x").getAsInt();
         int y = jsonLocation.get("y").getAsInt();
         int z = jsonLocation.get("z").getAsInt();
         geo_location pos  = new GeoLocation(x, y, z);
         node_data n = new NodeData(hashKey);
         n.setWeight(nodeWeight);
         n.setLocation(pos);
         newGraph.addNode(n);
      }
   }

   private void loadEdges(JsonObject json, directed_weighted_graph newGraph){
      JsonObject jsonEdges = json.get("edges").getAsJsonObject();
      for (Map.Entry<String, JsonElement> setHashEdges : jsonEdges.entrySet()) {
         int src = Integer.parseInt(setHashEdges.getKey()); //the key of the hashmap
         JsonObject jsonEdgeByNode = setHashEdges.getValue().getAsJsonObject();
         for (Map.Entry<String, JsonElement> edge : jsonEdgeByNode.entrySet()) {
            JsonObject jsonE = edge.getValue().getAsJsonObject();
            int dest = jsonE.get("dest").getAsInt();
            double w = jsonE.get("edgeWeight").getAsDouble();
            newGraph.connect(src, dest, w);
         }
      }
   }

}