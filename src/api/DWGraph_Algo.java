package api;

import com.google.gson.Gson;
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
      this.g= g;
   }

   @Override
   public directed_weighted_graph getGraph() {
      return g;
   }

   @Override
   public directed_weighted_graph copy() {
      if(this.g == null)
         return null;
      return new DWGraph_DS(g);
   }

   @Override
   public boolean isConnected() {
      for (node_data n: this.g.getV()){
         for (node_data niN: this.g.getV()){
            if(niN != n){
               if(this.g.getEdge(n.getKey(), niN.getKey()) == null)
                  return false;
            }
         }
      }
      return true;
   }

   private void DFS(node_data src){
      for (node_data n: this.g.getV()){
         n.setInfo("white");
         ((NodeData)n).dist = Double.MAX_VALUE;
      }
      ((NodeData)src).dist = 0;
      dfsVisit(src);
   }

   private void dfsVisit(node_data src){
      if(src == null){
         return;
      }
      src.setInfo("grey");
      for (edge_data e: g.getE(src.getKey())){
         if(((NodeData)g.getNode(e.getDest())).dist > ((NodeData)src).dist + e.getWeight()){
            ((NodeData)g.getNode(e.getDest())).dist = ((NodeData)src).dist + e.getWeight();
            ((NodeData)g.getNode(e.getDest())).prev = (src);
         }
         if(g.getNode(e.getDest()).getInfo().equals("white")){
            dfsVisit(g.getNode(e.getDest()));
         }
      }
      src.setInfo("black");
   }

   @Override
   public double shortestPathDist(int src, int dest) {
      if(g.getNode(src) == null || g.getNode(dest) == null || src==dest){
         return -1;
      }
      DFS(g.getNode(src));
      if(g.getNode(dest).getInfo().equals("white")){
         return -1;
      }
      return ((NodeData)g.getNode(dest)).dist;
   }

   @Override
   public List<node_data> shortestPath(int src, int dest) {
      List<node_data> path= new LinkedList<>();
      double dist= shortestPathDist(src,dest);
      if(dist==-1 || dist==Double.MAX_VALUE) {
         if(src==dest && g.getNode(dest)!=null) {
            path.add(g.getNode(dest));
            return path;
         }
         return null;
      }
      else {
         path.add(g.getNode(dest));
         node_data pr = ((NodeData)g.getNode(dest)).prev;
         int node0= dest;
         while(node0 != src) {
            path.add(0, pr);
            pr = ((NodeData)g.getNode(node0)).prev;
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
      } catch (Exception e){
         return false;
      }
   }

   @Override
   public boolean load(String file) {
      Gson gson = new Gson();
      try {
         FileReader lodeGraph = new FileReader(file);
         directed_weighted_graph graph = gson.fromJson(lodeGraph, directed_weighted_graph.class);
         this.init(graph);
         lodeGraph.close();
         return true;
      } catch (Exception e) {
         return false;
      }
   }
}


