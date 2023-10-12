package utils;

import java.util.Queue;
import java.util.Set;


//breadthFirst(start_node)
// create Set visited // visited nodes
// create Queue queue // nodes to visit
// enqueue start_node in queue
// while queue is not empty
// next = dequeue from queue
// if next not in visited
// process next // print or whatever you want to do with it
// add next to visited
// for every neighbor of next as neighbor
// if neighbor not in visited
// enqueue neighbor in queue
public class GraphTraversal<V> {
    
    private Set<V> visited;
    private Queue<V> toVisit;
    
    public GraphTraversal(Set<V> visited, Queue<V> toVisit) {
        this.visited = visited;
        this.toVisit = toVisit;
    }
    
    public void search(V startNode) {
        toVisit.add(startNode);
        while (!toVisit.isEmpty()) {
            V next = toVisit.remove();
            if (!visited.contains(next)) {
                process(next);
                visited.add(next);
                for (V neighbor : getNeighbors(next)) {
                    if (!visited.contains(neighbor)) {
                        toVisit.add(neighbor);
                    }
                }
            }
        }
    }

    private void process(V next) {

    }

    private V[] getNeighbors(V next) {
        return null;
    }

}
