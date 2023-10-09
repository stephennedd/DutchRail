package lists;

public interface BasicList<T> {

    public void append(T value);

    boolean isEmpty();

    public int size();

    public boolean contains(T value);

}
