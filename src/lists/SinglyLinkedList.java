package lists;

public class SinglyLinkedList<T> implements BasicList<T> {
    private T data;
    private SinglyLinkedList<T> next;

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean contains(T value) {
        return false;
    }

    public void add(T value) {
        if(isEmpty()) {
            data = value;
            next = new SinglyLinkedList<>();
        } else {
            next.add(value);
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

}
