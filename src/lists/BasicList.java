package lists;

public interface BasicList<T> {

    void append(T value);

    boolean isEmpty();

    int size();

    boolean contains(T value);

}
