package graphs;

import lists.HashTable;
import lists.SinglyLinkedList;


import java.util.*;

public class Graph<T> {
    private final HashTable<T, SinglyLinkedList<T>> adjacencyList;

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

    public StringBuilder bfs(T startVertex) {
        StringBuilder result = new StringBuilder();

        Set<T> visited = new HashSet<>();
        Queue<T> queue = new LinkedList<>();

        visited.add(startVertex);
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            T vertex = queue.poll();
            result.append(vertex).append(" ");

            for (T neighbor : getNeighbors(vertex)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        return result;
    }

    public void dfs(T startVertex) {
        Set<T> visited = new HashSet<>();
        StringBuilder result = new StringBuilder();
        dfsRecursive(startVertex, visited, result);
        System.out.println(result);
    }

    public boolean containsVertex(T vertex) {
        return adjacencyList.containsKey(vertex);
    }

    public boolean areConnected(T source, T destination) {
        if (!containsVertex(source) || !containsVertex(destination)) {
            return false;
        }
        return adjacencyList.get(source).contains(destination);
    }


    private void dfsRecursive(T startVertex, Set<T> visited, StringBuilder result) {
        visited.add(startVertex);
        result.append(startVertex).append(" ");

        for (T neighbor : getNeighbors(startVertex)) {
            if (!visited.contains(neighbor)) {
                dfsRecursive(neighbor, visited, result);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private T[] getNeighbors(T vertex) {
        return (T[]) adjacencyList.get(vertex).toArray();
    }
}
