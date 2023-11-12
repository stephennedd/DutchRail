package graphs;

import lists.SinglyLinkedList;
import model.Connection;
import model.Station;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.ReadCsvFile;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrainStationGraphTest {

    private TrainStationGraph graph;
    List<Station> stations;
    List<Connection> connections;

    @BeforeEach
    void setUp() {
        graph = new TrainStationGraph();
        stations = ReadCsvFile.readStationsWithValidation("data/stations.csv");
        connections = ReadCsvFile.readConnections("data/tracks.csv");
    }

    @Test
    void addVertex() {
        for (Station station: stations) {
            graph.addVertex(station);
        }

    }

    @Test
    void addEdge() {
        graph.addAllEdges(connections);
    }

    @Test
    public void testAddEdge() {
        TrainStationGraph graph = new TrainStationGraph();
        Station station1 = stations.get(0);
        Station station2 = stations.get(1);
        graph.addVertex(station1);
        graph.addVertex(station2);
        graph.addEdge(station1.getCode(), station2.getCode(), 10);

        assertTrue(graph.areConnected(station1, station2));
        assertFalse(graph.areConnected(station2, station1)); // Assuming the graph is directed
    }

    @Test
    public void testFilterStationsInRectangle() {
        TrainStationGraph graph = new TrainStationGraph();
        // Assume you have stations in a rectangular area
        Station station1 = stations.get(0);
        Station station2 = stations.get(1);
        Station station3 = stations.get(2);


        graph.addVertex(station1);
        graph.addVertex(station2);
        graph.addVertex(station3);

        // Assuming the rectangle covers stations 1 and 2
        TrainStationGraph subgraph = TrainStationGraph.filterStationsInRectangle(graph, 51.5, 5.0, 52.0, 6.0);

        assertTrue(subgraph.containsVertex(station1));
        assertTrue(subgraph.containsVertex(station2));
        assertFalse(subgraph.containsVertex(station3));
    }

    @Test
    public void testFilterStationsInRectangle_AllStationsInsideRectangle() {
        TrainStationGraph graph = new TrainStationGraph();
        Station station1 = stations.get(0);
        Station station2 = stations.get(1);
        Station station3 = stations.get(2);


        graph.addVertex(station1);
        graph.addVertex(station2);
        graph.addVertex(station3);

        TrainStationGraph subgraph = TrainStationGraph.filterStationsInRectangle(graph, 51.0, 4.0, 53.0, 7.0);

        assertTrue(subgraph.containsVertex(station1));
        assertTrue(subgraph.containsVertex(station2));
        assertTrue(subgraph.containsVertex(station3));
    }

    @Test
    public void testFilterStationsInRectangle_SomeStationsOutsideRectangle() {
        TrainStationGraph graph = new TrainStationGraph();
        Station station1 = stations.get(0);
        Station station2 = stations.get(1);
        Station station3 = stations.get(2);

        graph.addVertex(station1);
        graph.addVertex(station2);
        graph.addVertex(station3);

        TrainStationGraph subgraph = TrainStationGraph.filterStationsInRectangle(graph, 51.5, 5.0, 52.0, 6.0);

        assertTrue(subgraph.containsVertex(station1));
        assertTrue(subgraph.containsVertex(station2));
        assertFalse(subgraph.containsVertex(station3));
    }

}