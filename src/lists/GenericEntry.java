package lists;

public class GenericEntry<K, V> {
    public final K key;
    public V value;

    public GenericEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public boolean areEquals(K other) {
        return this.key.equals(other);
    }
}
