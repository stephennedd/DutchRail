package trees;

public class MinHeap<T extends Comparable<T>> {

    private T[] heap;
    private int size;
    private int capacity;


    @SuppressWarnings("unchecked") // This will stop the compiler from complaining about the cast
    public MinHeap(int capacity) {
        //this.heap = (T[]) java.lang.reflect.Array.newInstance(clazz, initialSize);
        this.size = 0;
        this.capacity = capacity;
        this.heap = (T[]) new Comparable[capacity];
    }
    
    public void push(T data) {
        if (size >= capacity) {
            remap();
            //throw new RuntimeException("Heap is full");
        }

        heap[size] = data;
        size++;

        // maintain heap property by moving the new element to the correct position
        int current = size - 1;
        while (current > 0 && data.compareTo(heap[parent(current)]) < 0) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }

        if (size == 1) {
            size--;
            return heap[0];
        }

        T data = heap[0];
        heap[0] = heap[size - 1];
        size--;
        percolateDown(0);

        return data;
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return heap[0];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return capacity;
    }

    @SuppressWarnings("unchecked")
    public void remap() {
        // create a new heap with double the capacity
        capacity *= 2;
        T[] newHeap = (T[]) new Comparable[capacity];
        System.arraycopy(heap, 0, newHeap, 0, heap.length);

        this.heap = newHeap;
    }

    public void buildHeap(T[] array) {
        this.heap = array;
        this.size = array.length;

        for (int i = size / 2 - 1; i >= 0; i--) {
            percolateDown(i);
        }
    }

    public String graphViz(T[] array) {
        StringBuilder dotTree = new StringBuilder();
        dotTree.append("digraph MinHeap {\n");

        for (int i = 0; i < array.length; i++) {
            dotTree.append("  ").append(i).append(" [label=\"").append(array[i]).append("\"];\n");

            if (i > 0) {
                int parentIndex = (i - 1) / 2;
                dotTree.append("  ").append(parentIndex).append(" -> ").append(i).append(";\n");
            }
        }

        dotTree.append("}\n");
        return dotTree.toString();
    }

    private void percolateDown(int index) {
        int left = leftChild(index);
        int right = rightChild(index);
        int smallest = index;

        if (left < size && heap[left].compareTo(heap[smallest]) < 0) { // if left child is smaller than parent
            smallest = left;
        }
        if (right < size && heap[right].compareTo(heap[smallest]) < 0) { // if right child is smaller than parent
            smallest = right;
        }

        if (smallest != index) {
            swap(index, smallest);
            percolateDown(smallest);
        }
    }

    private void swap(int index, int smallest) {
        T temp = heap[index];
        heap[index] = heap[smallest];
        heap[smallest] = temp;
        percolateDown(smallest);
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

}
