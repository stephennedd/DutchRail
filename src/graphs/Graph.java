package graphs;

import lists.HashTable;
import lists.SinglyLinkedList;
import model.Station;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Graph<T> {
    private HashTable<T, SinglyLinkedList<T>> adjacencyList;

    public Graph() {
        adjacencyList = new HashTable<>();
    }

    public void addVertex(T vertex) {
        adjacencyList.put(vertex, new SinglyLinkedList<T>());
    }

    public void addEdgeUndirectedGraph(T source, T destination) {
        if (!adjacencyList.containsKey(source)) {
            addVertex(source);
        }
        if (!adjacencyList.containsKey(destination)) {
            addVertex(destination);
        }
        adjacencyList.get(source).append(destination);
        adjacencyList.get(destination).append(source);
    }

    public void addEdgeDirectedGraph(T source, T destination) {
        if (!adjacencyList.containsKey(source)) {
            addVertex(source);
        }
        if (!adjacencyList.containsKey(destination)) {
            addVertex(destination);
        }
        adjacencyList.get(source).append(destination);
    }

    public void bfs(T startVertex) {
        Set<T> visited = new HashSet<>();
        Queue<T> queue = new LinkedList<>();

        visited.add(startVertex);
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            T vertex = queue.poll();
            System.out.print(vertex + " ");

            for (T neighbor : getNeighbors(vertex)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

    public void dfs(T startVertex) {
        Set<T> visited = new HashSet<>();
        dfsRecursive(startVertex, visited);
    }

    private void dfsRecursive(T startVertex, Set<T> visited) {
        visited.add(startVertex);
        System.out.print(startVertex + " ");

        for (T neighbor : getNeighbors(startVertex)) {
            if (!visited.contains(neighbor)) {
                dfsRecursive(neighbor, visited);
            }
        }
    }


    private T[] getNeighbors(T vertex) {
        return (T[]) adjacencyList.get(vertex).toArray();
    }

    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>();
        graph.addVertex(0);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);

        graph.addEdgeUndirectedGraph(0, 1);
        graph.addEdgeUndirectedGraph(0, 2);
        graph.addEdgeUndirectedGraph(1, 3);
        graph.addEdgeUndirectedGraph(2, 3);
        graph.addEdgeUndirectedGraph(3, 4);
        graph.addEdgeUndirectedGraph(4, 5);

        graph.bfs(2);
        System.out.println();
        graph.dfs(2);

        Graph<String> graph2 = new Graph<>();
        graph2.addVertex("A");
        graph2.addVertex("B");
        graph2.addVertex("C");
        graph2.addVertex("D");
        graph2.addVertex("E");
        graph2.addVertex("F");
        graph2.addVertex("G");

        graph2.addEdgeUndirectedGraph("A", "B");
        graph2.addEdgeUndirectedGraph("A", "C");
        graph2.addEdgeUndirectedGraph("A", "E");
        graph2.addEdgeUndirectedGraph("B", "D");
        graph2.addEdgeUndirectedGraph("B", "F");
        graph2.addEdgeUndirectedGraph("C", "G");
        graph2.addEdgeUndirectedGraph("F", "E");

        System.out.println();

        graph2.bfs("A");
        System.out.println();
        graph2.dfs("A");

    }
}
