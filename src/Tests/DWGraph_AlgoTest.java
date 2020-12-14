package Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import api.*;
import java.util.LinkedList;

class DWGraph_AlgoTest {

    directed_weighted_graph g_Test = new DWGraph_DS();
    dw_graph_algorithms ga_Test = new DWGraph_Algo();

    node_data n0 = new NodeData();
    node_data n1 = new NodeData();
    node_data n2 = new NodeData();
    node_data n3 = new NodeData();
    node_data n4 = new NodeData();

    @BeforeEach
    public void  buildingGraph(){
        g_Test.addNode(n0);
        g_Test.addNode(n1);
        g_Test.addNode(n2);
        g_Test.addNode(n3);
        g_Test.addNode(n4);
        g_Test.connect(0, 1, 6);
        g_Test.connect(0, 2, 9);
        g_Test.connect(1, 2, 2);
        g_Test.connect(1, 3, 7);
        g_Test.connect(1, 4, 5);
        g_Test.connect(2, 0, 3);
        g_Test.connect(2, 3, 1);
        g_Test.connect(3, 4, 1);
        g_Test.connect(4, 1, 3);
        ga_Test.init(g_Test);
    }

    @Test
    void copy() {
        directed_weighted_graph ga_copy = ga_Test.copy();
        System.out.println(g_Test.toString());
        System.out.println(ga_copy.toString());
        assertEquals(g_Test, ga_copy);
        g_Test.removeNode(1);
        System.out.println(g_Test.toString());
        System.out.println(ga_copy.toString());
        assertNotEquals(g_Test, ga_copy);
    }

    @Test
    void isConnected() {
        assertTrue(ga_Test.isConnected());	//The graph is connected.
        g_Test.removeEdge(2, 0);
        System.out.println(g_Test);
        assertFalse(ga_Test.isConnected());	//The graph isn't connected.

        node_data n2 = new NodeData(2);
        g_Test.addNode(n2);
        g_Test.connect(2, 0, 3);
        g_Test.removeEdge(4 ,1);
        g_Test.removeEdge(0 ,1);
        assertFalse(ga_Test.isConnected());	//The graph isn't connected.

        ga_Test.init(null);
        assertTrue(ga_Test.isConnected());	//The graph is null.

        g_Test= new DWGraph_DS();
        ga_Test.init(g_Test);
        assertTrue(ga_Test.isConnected());	//The graph is empty.

        node_data n0 = new NodeData(0);
        g_Test.addNode(n0);
        assertTrue(ga_Test.isConnected());	//A graph with 1 node.

        node_data n1 = new NodeData(0);
        g_Test.addNode(n1);
        assertFalse(ga_Test.isConnected());	//A graph with 2 nodes that not connected.

        g_Test.connect(1, 0, 5.62);
        assertFalse(ga_Test.isConnected());	//A graph with 2 nodes that connected at one direction.

        g_Test.connect(0, 1, 5.62);
        assertTrue(ga_Test.isConnected());	//A graph with 2 nodes that connected.
    }

    @Test
    void shortestPathDist() {
//        assertEquals(7, ga_Test.shortestPathDist(0, 4));
//        assertEquals(-1, ga_Test.shortestPathDist(6, 10)); // Tries to find the length of shortest path between 2 non-existent nodes.
//        assertEquals(-1, ga_Test.shortestPathDist(6, 4)); // Tries to find the length of shortest path between 2 nodes which the first is not exists in the graph.
//        assertEquals(-1, ga_Test.shortestPathDist(4, 10)); // Tries to find the length of shortest path between 2 nodes which the second is not exists in the graph.
//        assertEquals(0, ga_Test.shortestPathDist(1, 1)); // The length of shortest path from a node to itself is 0.
//
//        g_Test.removeNode(1);
//        g_Test.removeNode(3);
//        assertEquals(Double.POSITIVE_INFINITY, ga_Test.shortestPathDist(0, 4)); // The length is infinity because 4 is not connected to the graph.
        assertEquals(5, ga_Test.shortestPathDist(1, 0));	    //Path between 2 node that in the graph and connected.
        assertEquals(3, ga_Test.shortestPath(1, 0).size());
        assertEquals(10, ga_Test.shortestPathDist(0, 4));	//Path between 2 node that in the graph and connected.
        assertEquals(5, ga_Test.shortestPath(0, 4).size());
        assertEquals(3, ga_Test.shortestPathDist(1, 3));	//Path between 2 node that in the graph and connected.
        assertEquals(3, ga_Test.shortestPath(1, 3).size());
        g_Test.removeEdge(2, 0);
        assertEquals(-1, ga_Test.shortestPathDist(3, 0));	    //Path between 2 node that in the graph and not connected.
        assertNull(ga_Test.shortestPath(3, 0));
        assertEquals(-1, ga_Test.shortestPathDist(4, 7));	    //Path between node in the graph to node that not in the graph.
        assertNull(ga_Test.shortestPath(4, 7));
        assertEquals(-1, ga_Test.shortestPathDist(8, 1));	    //Path between node that not in the graph to node in the graph.
        assertNull(ga_Test.shortestPath(8, 1));
        assertEquals(-1, ga_Test.shortestPathDist(13, 11));	//Path between 2 node that not in the graph.
        assertNull(ga_Test.shortestPath(13, 11));
        assertEquals(0, ga_Test.shortestPathDist(3, 3));	    //Path between node in the graph to himself.
        assertEquals(1, ga_Test.shortestPath(3, 3).size());
        assertEquals(-1, ga_Test.shortestPathDist(10, 10));	//Path between node that not in the graph to himself.
        assertNull(ga_Test.shortestPath(10, 10));
    }

//    @Test
//    void shortestPath() {
//        LinkedList<node_data> path_Test = (LinkedList<node_data>) ga_Test.shortestPath(0, 4);
//        assertEquals(0, path_Test.get(0).getKey());
//        assertEquals(2, path_Test.get(1).getKey());
//        assertEquals(3, path_Test.get(2).getKey());
//        assertEquals(4, path_Test.get(3).getKey());
//        System.out.println(ga_Test.shortestPath(0, 4));
//
//        assertNull(ga_Test.shortestPath(6, 10)); // Tries to find the shortest path between 2 non-existent nodes.
//        assertNull(ga_Test.shortestPath(6, 4)); // Tries to find the shortest path between 2 nodes which the first is not exists in the graph.
//        assertNull(ga_Test.shortestPath(4, 10)); // Tries to find the shortest path between 2 nodes which the second is not exists in the graph.
//        path_Test = (LinkedList<node_data>) ga_Test.shortestPath(1, 1); // src == node so might return a List with one node.
//        assertEquals(1, path_Test.get(0).getKey());
//        assertEquals(1, path_Test.size());
//        System.out.println(ga_Test.shortestPath(1, 1));
//
//        g_Test.removeNode(1);
//        g_Test.removeNode(3);
//        path_Test = (LinkedList<node_data>) ga_Test.shortestPath(0, 4);
//        assertNull(path_Test); // 4 is not connected to the graph so there is no such path.
//        System.out.println(ga_Test.shortestPath(0, 4));
//    }

    @Test
    void file() {
        assertTrue(ga_Test.save("test1.txt"));	//Save graph to file.
        assertTrue(ga_Test.load("test1.txt"));	//Loading graph from a file found.
        assertFalse(ga_Test.load("test2.txt"));	//Loading graph from a file that not found.
        assertFalse(ga_Test.load(""));			//Loading graph from nothing file.
        assertFalse(ga_Test.save(""));			//Save graph to nothing file.
    }
}