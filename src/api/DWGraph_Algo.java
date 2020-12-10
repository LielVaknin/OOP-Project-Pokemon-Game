package api;

import com.google.gson.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.List;

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

   @Override
   public void init(directed_weighted_graph g) {
      this.g = g;
   }

   @Override
   public directed_weighted_graph getGraph() {
      return g;
   }

   @Override
   public directed_weighted_graph copy() {
      if (this.g == null)
         return null;
      return new DWGraph_DS(g);
   }

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

   @Override
   public boolean save(String file) {
      Gson gson = new Gson();
      String jsonG = gson.toJson(g);
      try {
         FileWriter saveGraph = new FileWriter(file);
         saveGraph.write(jsonG);
         saveGraph.close();
         return true;
      } catch (Exception e) {
         return false;
      }
   }

   public void initFromJson(JsonObject json){
      JsonArray nodes = json.get("Nodes").getAsJsonArray();
      for (int i=0; i<nodes.size(); i++){
         int id = nodes.get(i).getAsJsonObject().get("id").getAsInt();
         String pos = nodes.get(i).getAsJsonObject().get("pos").getAsString();
         geo_location location = new GeoLocation(pos);
         node_data node = new NodeData(id);
         node.setLocation(location);
         this.g.addNode(node);
      }

      JsonArray edges = json.get("Edges").getAsJsonArray();
      for (int i=0; i<edges.size(); i++){
         int src = edges.get(i).getAsJsonObject().get("src").getAsInt();
         int dest = edges.get(i).getAsJsonObject().get("dest").getAsInt();
         double weight = edges.get(i).getAsJsonObject().get("w").getAsDouble();
         g.connect(src, dest, weight);
      }
   }

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

}