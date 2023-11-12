package graphs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    private Graph<Integer> integerGraph;
    private Graph<String> stringGraph;

    @BeforeEach
    public void setUp() {
        // Initialize your graph instances before each test
        integerGraph = new Graph<>();
        stringGraph = new Graph<>();
    }

    @Test
    public void testAddVertex() {
        integerGraph.addVertex(1);
        assertTrue(integerGraph.containsVertex(1));
    }

    @Test
    public void testAddEdgeUndirectedGraph() {
        integerGraph.addEdgeUndirectedGraph(1, 2);
        assertTrue(integerGraph.areConnected(1, 2));
        assertTrue(integerGraph.areConnected(2, 1));
    }

    @Test
    public void testAddEdgeDirectedGraph() {
        integerGraph.addEdgeDirectedGraph(1, 2);
        assertTrue(integerGraph.areConnected(1, 2));
        assertFalse(integerGraph.areConnected(2, 1));
    }

    @Test
    public void testBFS() {
        integerGraph.addEdgeUndirectedGraph(1, 2);
        integerGraph.addEdgeUndirectedGraph(1, 3);
        integerGraph.addEdgeUndirectedGraph(2, 4);
        integerGraph.addEdgeUndirectedGraph(2, 5);
        integerGraph.addEdgeUndirectedGraph(3, 6);
        integerGraph.addEdgeUndirectedGraph(3, 7);

        // Call BFS and assert the expected order of traversal
        //System.out.println(integerGraph.bfs(1));
        assertEquals("1 2 3 4 5 6 7 ", integerGraph.bfs(1).toString());
    }

    @Test
    public void testDFS() {
        integerGraph.addEdgeUndirectedGraph(1, 2);
        integerGraph.addEdgeUndirectedGraph(1, 3);
        integerGraph.addEdgeUndirectedGraph(2, 4);
        integerGraph.addEdgeUndirectedGraph(2, 5);
        integerGraph.addEdgeUndirectedGraph(3, 6);
        integerGraph.addEdgeUndirectedGraph(3, 7);

        // Call DFS and assert the expected order of traversal
        integerGraph.dfs(1);
    }

    @Test
    public void testContainsVertex() {
        integerGraph.addVertex(1);
        assertTrue(integerGraph.containsVertex(1));
    }

    @Test
    public void testAreConnected() {
        integerGraph.addEdgeUndirectedGraph(1, 2);
        assertTrue(integerGraph.areConnected(1, 2));
        assertTrue(integerGraph.areConnected(2, 1));
    }

    @Test
    public void testAreNotConnected() {
        integerGraph.addEdgeUndirectedGraph(1, 2);
        assertFalse(integerGraph.areConnected(1, 3));
        assertFalse(integerGraph.areConnected(3, 1));
    }

}