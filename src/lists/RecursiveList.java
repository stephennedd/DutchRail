package lists;

import java.util.Iterator;

public class RecursiveList<T extends Comparable<T>>  implements BasicList<T>, Iterable<T>{
    private T data;
    private RecursiveList<T> next;
    private T comparator;

    public RecursiveList() {
        this.data = null;
        this.next = null;
        this.comparator = null;
    }

    public RecursiveList(T data, RecursiveList<T> next, T comparator) {
        assert (data == null ) == (next == null) : "data and next are not consistent";
        this.data = data;
        this.next = next;
        this.comparator = comparator;
    }

    public RecursiveList(T comparator) {
        this.comparator = comparator;
    }


    public void setData(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }
        assert isEmpty() : "the list is not empty";
        this.data = data;
        this.next = new RecursiveList<>(this.comparator);
    }

    private T getData() {
        assert !isEmpty() : "the list is empty";
        return data;
    }

    public boolean isEmpty() {
        return next == null;
    }

    public void append(T value) {
        if (isEmpty()) {
            setData(value); // if the list is empty, set the data to the value
        }
        else { // if the list is not empty, add the value to the next list
            next.append(value);
        }
    }

    public boolean remove(T value) {
        if (isEmpty()) {
            return false;
        } else {
            if (data.equals(value)) {
                if (next.isEmpty()) {
                    data = null;
                    next = null;
                } else {
                    data = next.data;
                    next = next.next;
                }
                return true;
            } else {
                return next.remove(value);
            }
        }
    }

    public T peekTail() {
        if (next == null) {
            return null;
        }
        if(next.isEmpty()) {
            return data;
        } else {
            return next.peekTail();
        }
    }

    public T peekHead() {
        return data;
    }


    @Override
    public int size() {
        if(isEmpty()) {
            assert next == null : "rest is not null";
            return 0;
        } else {
            assert next!= null : "rest is null";
            return 1 + next.size();
        }
    }

    @Override
    public boolean contains(T value) {
        if(isEmpty()) { // if the list is empty, return false
            return false;
        }
        if(data.compareTo(value) == 0) { // if the data is equal to the value, return true
            return true;
        } else {
            return next.contains(value);
        }
    }

    @Override
    public Iterator<T> iterator() {
        RecursiveIterator iterator = new RecursiveIterator(this);
        return null;
    }

    class RecursiveIterator<Q extends Comparable<Q>> implements Iterator<Q> {
        private RecursiveList<Q> current;
        public RecursiveIterator(RecursiveList<Q> list) {
            this.current = list;
        }

        @Override
        public boolean hasNext() {
            return !current.isEmpty();
        }

        @Override
        public Q next() {
            if (current==null || current.isEmpty()) {
                return null;
            } else {
                Q result = current.getData();
                current = current.next;

                return result;
            }
        }
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]"; // return an empty list
        }
        String result = (data == null) ? "NULL" : data.toString(); // add the data to the result
        if(!next.isEmpty()) { // if the next list is not empty, add a comma and the next list to the result
            result += ", " + next.toString();
        }
        return result;
    }
}
