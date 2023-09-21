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
        return data == null;
    }

    public void add(T value) {
        if (isEmpty()) {
            setData(value);
        } // compare the data to the value and add it to the list if it is larger
//        else if(data.compareTo(value) < 0)
//        {
//
//        }
        else {
            this.next = new RecursiveList<>(value, this.next, this.comparator);
            this.data = value;
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


    @Override
    public String toString() {
        if(isEmpty()) {
            return "[]";
        } else {
            return "RecursiveList{" +
                    "data=" + data +
                    ", next=" + next +
                    '}';
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
    public int getCount() {
        if(isEmpty()) {
            assert next == null : "rest is not null";
            return 0;
        } else {
            assert next!= null : "rest is null";
            return 1 + next.getCount();
        }
    }

    @Override
    public boolean contains(T value) {
        if(isEmpty()) {
            return false;
        } else {
            if(data.equals(value)) {
                return true;
            } else {
                return next.contains(value);
            }
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
}
