package lists;

public class HashTable<K, V> {
    private final int INITIAL_SIZE = 16; // Initial size of the hash table
    private final double LOAD_FACTOR = 0.75; // Factor to resize the hash table
    private int size;
    private SinglyLinkedList<GenericEntry<K, V>> [] buckets; // SinglyLinkedList of buckets

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
        int index = getIndex(key); // Get the index of the bucket
        SinglyLinkedList<GenericEntry<K, V>> bucket = buckets[index]; // Get the bucket
        GenericEntry<K, V> entry = new GenericEntry<>(key, value); // Create a new entry

        if (bucket.isEmpty()) { // Check if the bucket is empty
            bucket.append(entry); // Add the entry to the bucket
            size++; // Increase the size
        } else {
            for (int i = 0; i < bucket.size(); i++) { // Loop through the bucket
                GenericEntry<K, V> currentEntry = bucket.get(i); // Get the current entry
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

        for (int i = 0; i < bucket.size(); i++) { // Loop through the bucket
            GenericEntry<K, V> entry = bucket.get(i); // Get the current entry
            if (entry.key.equals(key)) { // Check if the current entry has the same key as the new entry
                return entry.value; // Return the value of the current entry
            }
        }
        return null; // Return null if the key is not found
    }

    public boolean remove(K key) {
        int index = getIndex(key); // Get the index of the bucket
        SinglyLinkedList<GenericEntry<K, V>> bucket = buckets[index]; // Get the bucket

        for (int i = 0; i < bucket.size(); i++) { // Loop through the bucket
            GenericEntry<K, V> entry = bucket.get(i); // Get the current entry
            if (entry.key.equals(key)) { // Check if the current entry has the same key as the new entry
                bucket.remove(entry); // Remove the entry from the bucket
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

    private void resizeTable() {
        int newCapacity = buckets.length * 2;
        SinglyLinkedList<GenericEntry<K, V>>[] newTable = new SinglyLinkedList[newCapacity];

        for (int i = 0; i < newCapacity; i++) {
            newTable[i] = new SinglyLinkedList<>();
        }

        for (SinglyLinkedList<GenericEntry<K, V>> bucket : buckets) {
            for (int i = 0; i < bucket.size(); i++) {
                GenericEntry<K, V> entry = bucket.get(i);
                int index = Math.abs(entry.key.hashCode()) % newCapacity;
                newTable[index].append(entry);
            }

        }

        buckets = newTable;
    }


}
