package Tests;

import api.*;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.*;

class DWGraph_DSTest {

    @Test
    void WGraph_DS() {
        directed_weighted_graph g_Test1 = new DWGraph_DS();
        assertEquals(0, g_Test1.nodeSize());
        assertEquals(0, g_Test1.edgeSize());
        assertEquals(0, g_Test1.getMC());
    }

    @Test
    void getNode() {
        directed_weighted_graph g_Test2 = new DWGraph_DS();
        node_data n0 = new NodeData();
        g_Test2.addNode(n0);
        assertEquals(1, g_Test2.nodeSize());
        assertEquals(0, g_Test2.edgeSize());
        assertEquals(1, g_Test2.getMC());
        assertNotNull(g_Test2.getNode(0));
        assertNull(g_Test2.getNode(1));
    }

    @Test
    void getEdge() {
        directed_weighted_graph g_Test3 = new DWGraph_DS();
        node_data n0 = new NodeData();
        node_data n1 = new NodeData();
        node_data n2 = new NodeData();
        node_data n3 = new NodeData();
        node_data n4 = new NodeData();
        g_Test3.addNode(n0);
        g_Test3.addNode(n1);
        g_Test3.addNode(n2);
        g_Test3.addNode(n3);
        g_Test3.addNode(n4);
        g_Test3.connect(0, 1, 6);
        g_Test3.connect(0, 2, 1);
        g_Test3.connect(1, 2, 2);
        g_Test3.connect(1, 3, 2);
        g_Test3.connect(1, 4, 5);
        g_Test3.connect(2, 3, 1);
        g_Test3.connect(3, 4, 5);
        assertNotNull(g_Test3.getEdge(1, 4));
        assertNull(g_Test3.getEdge(2, 0));
        assertNull(g_Test3.getEdge(3, 0));
        assertNull(g_Test3.getEdge(6, 7));
        assertNull(g_Test3.getEdge(6, 4));
        assertNull(g_Test3.getEdge(4, 7));
        assertNull(g_Test3.getEdge(1, 1));
    }

    @Test
    void addNode() {
        directed_weighted_graph g_Test4 = new DWGraph_DS();
        assertEquals(0, g_Test4.nodeSize());
        assertEquals(0, g_Test4.getMC());
        node_data n0 = new NodeData();
        node_data n1 = new NodeData();
        g_Test4.addNode(n0);
        g_Test4.addNode(n1);
        assertEquals(2, g_Test4.nodeSize());
        assertEquals(2, g_Test4.getMC());
        g_Test4.addNode(n0);
        assertEquals(2, g_Test4.nodeSize());
        assertEquals(2, g_Test4.getMC());
    }

    @Test
    void connect() {
        directed_weighted_graph g_Test5 = new DWGraph_DS();
        assertEquals(0, g_Test5.edgeSize());
        assertEquals(0, g_Test5.getMC());
        node_data n0 = new NodeData();
        node_data n1 = new NodeData();
        node_data n2 = new NodeData();
        node_data n3 = new NodeData();
        node_data n4 = new NodeData();
        g_Test5.addNode(n0);
        g_Test5.addNode(n1);
        g_Test5.addNode(n2);
        g_Test5.addNode(n3);
        g_Test5.addNode(n4);
        g_Test5.connect(0, 1, 6);
        g_Test5.connect(0, 2, 1);
        g_Test5.connect(1, 2, 2);
        g_Test5.connect(1, 3, 2);
        g_Test5.connect(1, 4, 5);
        g_Test5.connect(2, 3, 1);
        g_Test5.connect(3, 4, 5);
        assertEquals(7, g_Test5.edgeSize());
        assertEquals(12, g_Test5.getMC());
        assertNotNull(g_Test5.getEdge(0, 1));
        g_Test5.connect(0, 4, 3); // Connects 2 nodes which there is no at least one directed edge between them.
        assertEquals(8, g_Test5.edgeSize());
        assertEquals(13, g_Test5.getMC());
        assertNotNull(g_Test5.getEdge(0, 4));
        g_Test5.connect(6, 7, 6); // Tries to connect 2 nodes which don't exists in the graph.
        g_Test5.connect(6, 4, 5); // Tries to connect 2 nodes which only the first exists in the graph.
        g_Test5.connect(4, 7, 4); // Tries to connect 2 nodes which only the second exists in the graph.
        g_Test5.connect(2, 4, -5); // Tries to connect 2 nodes with weight < 0.
        g_Test5.connect(1, 1, 5); // Tries to connect a node to itself.
        assertEquals(8, g_Test5.edgeSize()); // The connections failed as expected.
        assertEquals(13, g_Test5.getMC());
      /*  g_Test5.connect(0, 1, 5); // Only updates the weight because the edge is already exists.
        assertEquals(8, g_Test5.edgeSize());
        assertEquals(14, g_Test5.getMC());
        assertEquals(5, g_Test5.getEdge(0,1)); // Updated.*/
    }

    @Test
    void getV() {
        directed_weighted_graph g_Test6 = new DWGraph_DS();
        node_data n0 = new NodeData();
        node_data n1 = new NodeData();
        node_data n2 = new NodeData();
        node_data n3 = new NodeData();
        node_data n4 = new NodeData();
        g_Test6.addNode(n0);
        g_Test6.addNode(n1);
        g_Test6.addNode(n2);
        g_Test6.addNode(n3);
        g_Test6.addNode(n4);
        int colNodeSize = 0;
        Iterator<node_data> it = g_Test6.getV().iterator();
        while (it.hasNext()) {
            node_data n = it.next();
            assertNotNull(n);
            colNodeSize++;
        }
        assertEquals(colNodeSize, g_Test6.nodeSize());
    }

    @Test
    void getE() {
        directed_weighted_graph g_Test7 = new DWGraph_DS();
        node_data n0 = new NodeData();
        node_data n1 = new NodeData();
        node_data n2 = new NodeData();
        node_data n3 = new NodeData();
        node_data n4 = new NodeData();
        g_Test7.addNode(n0);
        g_Test7.addNode(n1);
        g_Test7.addNode(n2);
        g_Test7.addNode(n3);
        g_Test7.addNode(n4);
        g_Test7.connect(0, 1, 6);
        g_Test7.connect(0, 2, 1);
        g_Test7.connect(0, 3, 2);
        g_Test7.connect(0, 4, 5);
        int colESize = 0;
        Iterator<edge_data> it = g_Test7.getE(0).iterator();
        while (it.hasNext()) {
            edge_data e = it.next();
            assertNotNull(e);
            colESize++;
        }
        assertEquals(4, colESize);
    }


    @Test
    void removeNode() {
        directed_weighted_graph g_Test8 = new DWGraph_DS();
        node_data n0 = new NodeData();
        node_data n1 = new NodeData();
        node_data n2 = new NodeData();
        node_data n3 = new NodeData();
        node_data n4 = new NodeData();
        g_Test8.addNode(n0);
        g_Test8.addNode(n1);
        g_Test8.addNode(n2);
        g_Test8.addNode(n3);
        g_Test8.addNode(n4);
        g_Test8.connect(0, 1, 6);
        g_Test8.connect(0, 2, 1);
        g_Test8.connect(1, 2, 2);
        g_Test8.connect(1, 3, 2);
        g_Test8.connect(1, 4, 5);
        g_Test8.connect(2, 3, 1);
        g_Test8.connect(3, 4, 5);
        assertEquals(5, g_Test8.nodeSize());
        assertEquals(7, g_Test8.edgeSize());
        assertEquals(12, g_Test8.getMC());
        node_data n = g_Test8.removeNode(3);
        assertEquals(3, n.getKey());
        assertEquals(4, g_Test8.nodeSize());
        assertEquals(5, g_Test8.edgeSize());
        assertEquals(15, g_Test8.getMC()); // The increase of MC also caused because of edges deletion.
        assertNull(g_Test8.removeNode(3)); // Tries to remove node which no longer exists in the graph.
        assertEquals(4, g_Test8.nodeSize());
        assertEquals(5, g_Test8.edgeSize());
        assertEquals(15, g_Test8.getMC());
    }

    @Test
    void removeEdge() {
        directed_weighted_graph g_Test9 = new DWGraph_DS();
        node_data n0 = new NodeData();
        node_data n1 = new NodeData();
        node_data n2 = new NodeData();
        node_data n3 = new NodeData();
        node_data n4 = new NodeData();
        g_Test9.addNode(n0);
        g_Test9.addNode(n1);
        g_Test9.addNode(n2);
        g_Test9.addNode(n3);
        g_Test9.addNode(n4);
        g_Test9.connect(0, 1, 6);
        g_Test9.connect(0, 2, 1);
        g_Test9.connect(1, 2, 2);
        g_Test9.connect(1, 3, 2);
        g_Test9.connect(1, 4, 5);
        g_Test9.connect(2, 3, 1);
        g_Test9.connect(3, 4, 5);
        assertEquals(7, g_Test9.edgeSize());
        assertEquals(12, g_Test9.getMC());
        g_Test9.removeEdge(1, 4); // Removes the edge between this 2 nodes which has one directed edge between them.
        assertEquals(6, g_Test9.edgeSize());
        assertEquals(13, g_Test9.getMC());
        g_Test9.removeEdge(1, 4); // Tries to remove a non-existent edge.
        g_Test9.removeEdge(6, 7); // Tries to remove an edge between non-existent nodes.
        g_Test9.removeEdge(6, 4); // Tries to remove an edge between 2 nodes which the first is not exists in the graph.
        g_Test9.removeEdge(4, 7); // Tries to remove an edge between 2 nodes which the second is not exists in the graph.
        assertEquals(6, g_Test9.edgeSize()); // The removals failed as expected.
        assertEquals(13, g_Test9.getMC());
    }
}