package lists;

public interface BasicList<T> {

    public void add(T value);

    boolean isEmpty();

    public int size();

    public boolean contains(T value);

}
