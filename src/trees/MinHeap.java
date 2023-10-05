package trees;

import java.util.Comparator;

public class MinHeap<T extends Comparable<T>> {

    private T[] heap;
    private int size;
    private int initialSize;


    public MinHeap(Class<T> clazz, int initialSize) {
        this.heap = (T[]) java.lang.reflect.Array.newInstance(clazz, initialSize);
        size = 0;
        this.initialSize = initialSize;
    }

    public static MinHeap<String> createStringHeap(int initialSize) {
        return new MinHeap<>(String.class, initialSize );
    }
    
    public void push(T data) {
        if (size==this.heap.length) {
            remap();
        }
        this.heap[size] = data;
        percolateUp(size);
        size++;
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }
        T data = heap[0];
        heap[0] = heap[size-1];
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
        return heap.length;
    }

    public T[] getHeap() {
        return heap;
    }

    public void remap() {

    }

    private void percolateUp(int size) {
        int parent = (size-1)/2;
        if (heap[parent].compareTo(heap[size]) > 0) {
            T temp = heap[parent];
            heap[parent] = heap[size];
            heap[size] = temp;
            percolateUp(parent);
        }
    }

    private void percolateDown(int i) {

    }

    public static void main(String[] args) {
        MinHeap<String> heap = new MinHeap<>(String.class, 0);
        heap.push("test");
        heap.push("test2");
        System.out.println(heap.peek());
    }


}
