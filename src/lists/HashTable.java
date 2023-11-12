package lists;

public class HashTable<K, V> {
    private static final int INITIAL_SIZE = 16; // Initial size of the hash table
    private static final double LOAD_FACTOR = 0.75; // Factor to resize the hash table
    private int size;
    private SinglyLinkedList<GenericEntry<K, V>> [] buckets; // SinglyLinkedList of buckets

    @SuppressWarnings("unchecked")
    public HashTable() {
        buckets = new SinglyLinkedList[INITIAL_SIZE];
        for (int i = 0; i < INITIAL_SIZE; i++) {
            buckets[i] = new SinglyLinkedList<>();
        }
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int getIndex(K key) {
        return Math.abs(key.hashCode()) % buckets.length; // Get the index of the bucket
    }

    public void put(K key, V value) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null"); // Check if the key is null

        int index = getIndex(key); // Get the index of the bucket
        SinglyLinkedList<GenericEntry<K, V>> bucket = buckets[index]; // Get the bucket
        GenericEntry<K, V> entry = new GenericEntry<>(key, value); // Create a new entry

        if (bucket.isEmpty()) { // Check if the bucket is empty
            bucket.append(entry); // Add the entry to the bucket
            size++; // Increase the size
        } else { // If the bucket is not empty
            for (GenericEntry<K, V> currentEntry : bucket) { // Loop through the bucket
                if (currentEntry.key.equals(key)) { // Check if the current entry has the same key as the new entry
                    currentEntry.value = value; // Update the value of the current entry
                    return;
                }
            }
            bucket.append(entry); // Add the entry to the bucket
            size++; // Increase the size
        }

        if (size > buckets.length * LOAD_FACTOR) { // Check if the size is bigger than the load factor
            resizeTable(); // Resize the hash table
        }
    }

    public V get(K key) {
        int index = getIndex(key); // Get the index of the bucket
        SinglyLinkedList<GenericEntry<K, V>> bucket = buckets[index]; // Get the bucket

        for (GenericEntry<K, V> entry : bucket) { // Loop through the bucket
            if (entry.key.equals(key)) { // Check if the current entry has the same key as the new entry
                return entry.value; // Return the value of the current entry
            }
        }
        return null; // Return null if the key is not found
    }

    public boolean remove(K key) {
        int index = getIndex(key); // Get the index of the bucket
        SinglyLinkedList<GenericEntry<K, V>> bucket = buckets[index]; // Get the bucket

        for (GenericEntry<K, V> entry : bucket) { // Loop through the bucket
            if (entry.key.equals(key)) { // Check if the current entry has the same key as the new entry
                bucket.remove(entry); // Remove the current entry
                size--; // Decrease the size
                return true;
            }
        }
        return false; // Return false if the key is not found
    }

    public boolean containsKey(K key) {
        int index = getIndex(key); // Get the index of the bucket
        SinglyLinkedList<GenericEntry<K, V>> bucket = buckets[index]; // Get the bucket

        for (int i = 0; i < bucket.size(); i++) { // Loop through the bucket
            GenericEntry<K, V> entry = bucket.get(i); // Get the current entry
            if (entry.key.equals(key)) { // Check if the current entry has the same key as the new entry
                return true;
            }
        }
        return false; // Return false if the key is not found
    }

    @SuppressWarnings("unchecked")
    private void resizeTable() {
        int newCapacity = buckets.length * 2;
        SinglyLinkedList<GenericEntry<K, V>>[] newTable = new SinglyLinkedList[newCapacity];

        for (int i = 0; i < newCapacity; i++) {
            newTable[i] = new SinglyLinkedList<>();
        }

        for (SinglyLinkedList<GenericEntry<K, V>> bucket : buckets) { // Loop through the buckets
            for (GenericEntry<K, V> entry : bucket) { // Loop through the entries
                int index = Math.abs(entry.key.hashCode()) % newCapacity; // Get the index of the bucket
                newTable[index].append(entry); // Add the entry to the bucket
            }
        }

        buckets = newTable;
    }


}
