package Tests;

import api.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.*;

class DWGraph_DSTest {

    directed_weighted_graph g_Test = new DWGraph_DS();

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
        assertEquals(5, g_Test.nodeSize());
        assertEquals(9, g_Test.edgeSize());
        assertEquals(14, g_Test.getMC());
    }

    @Test
    public void nodes() {
        assertNull(g_Test.getNode(12));		//A node that isn't in the graph.
        assertNotNull(g_Test.getNode(4));	//A node that is in the graph.
        node_data n5 = new NodeData();
        g_Test.addNode(n5);							//Add node which there is in the graph.
        assertEquals(6, g_Test.nodeSize());
        assertEquals(15, g_Test.getMC());
    }

    @Test
    public void edges() {
        assertNotNull( g_Test.getEdge(1, 4));
        g_Test.connect(1, 4, 10);	    //Connect 2 nodes that connected.
        g_Test.connect(2, 4, 0);		//Connect 2 nodes that didn't connected.
        assertNotNull( g_Test.getEdge(2, 4));
        g_Test.connect(4, 12, 3);	    //Connect 2 nodes- the first is in the graph and the second not.
        assertNull( g_Test.getEdge(4, 12));
        g_Test.connect(9, 3, 5);		//Connect 2 nodes- the first isn't in the graph and the second their.
        assertNull( g_Test.getEdge(9, 3));
        g_Test.connect(12, 13, 3);	    //Connect 2 nodes that aren't in the graph.
        assertNull(g_Test.getEdge(12, 13));
        g_Test.connect(3, 3, 7);		//Connect node that is in the graph to himself.
        assertNull(g_Test.getEdge(3, 3));
        g_Test.connect(17, 17, 1);	    //Connect node that isn't in the graph to himself.
        assertNull(g_Test.getEdge(17, 17));

        assertEquals(10, g_Test.edgeSize());
        assertEquals(15, g_Test.getMC());
    }

    @Test
    public void getV() {
        int length= g_Test.getV().size();
        assertEquals(5, length);
        directed_weighted_graph g_Test1 = new DWGraph_DS();
        int length1= g_Test1.getV().size();
        assertEquals(0, length1);
    }

    @Test
    void getE() {
        int length1= g_Test.getE(1).size();
        assertEquals(3, length1);
        node_data n5 = new NodeData();
        g_Test.addNode(n5);
        assertNull(g_Test.getE(5));
        assertNull(g_Test.getE(10));
    }


    @Test
    void removeNode() {
        node_data n= g_Test.removeNode(1);	//Remove a node that is in the graph.
        assertEquals(1, n.getKey());
        assertEquals(4, g_Test.edgeSize());
        assertEquals(4, g_Test.nodeSize());
        n= g_Test.removeNode(8);			//Remove a node that isn't in the graph.
        assertNull(n);
        assertEquals(4, g_Test.edgeSize());
        assertEquals(4, g_Test.nodeSize());
        assertEquals(17, g_Test.getMC());
    }

    @Test
    public void removeEdge() {
        g_Test.removeEdge(0, 1);		//Remove a edge that is in the graph.
        g_Test.removeEdge(1, 0);		//Remove a edge that isn't in the graph.
        g_Test.removeEdge(4, 11);	    //Remove a edge between node that is in the graph to node that not.
        g_Test.removeEdge(7, 2);		//Remove a edge between node that isn't in the graph to node that their.
        g_Test.removeEdge(17, 13);	    //Remove a edge between 2 nodes that aren't in the graph.
        g_Test.removeEdge(2, 2);		//Remove a edge between node that is in the graph to himself.
        g_Test.removeEdge(8, 8);		//Remove a edge between node that isn't in the graph to himself.
        assertEquals(5, g_Test.nodeSize());
        assertEquals(8, g_Test.edgeSize());
        assertEquals(15, g_Test.getMC());
    }
}