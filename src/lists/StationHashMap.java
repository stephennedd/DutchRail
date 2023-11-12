package lists;

import model.Station;

public class StationHashMap {
    private static final int DEFAULT_CAPACITY = 16; // Default capacity of the map
    private static final double LOAD_FACTOR = 0.75; // Factor to determine when to resize the map

    SinglyLinkedList<StationEntry>[] buckets;
    private int size;

    public StationHashMap() {
        this(DEFAULT_CAPACITY);
    }

    public StationHashMap(int capacity) {
        buckets = new SinglyLinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new SinglyLinkedList<>();
        }
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void put(Station station) {
        if (station == null) {
            NullKey();
        }

        if ((double) size / buckets.length >= LOAD_FACTOR) {
            resize();
        }
        String key = station.getCode();

        int index = getIndex(key);

        SinglyLinkedList<StationEntry> bucket = buckets[index];
        SinglyLinkedListNode<StationEntry> current = bucket.head;

        while (current != null) {
            if (current.data.areEquals(key)) { // If the key already exists
                current.data.station = station; // Update the value
                return;
            }
            current = current.next; // Move to the next node
        }

        bucket.append(new StationEntry(key, station));
        size++;
    }

    public Station get(String key) {
        if (key == null) {
            NullKey();
        }

        int index = getIndex(key);
        SinglyLinkedList<StationEntry> bucket = buckets[index];
        SinglyLinkedListNode<StationEntry> current = bucket.head;

        while (current != null) {
            if (current.data.areEquals(key)) {
                return current.data.station;
            }
            current = current.next;
        }

        return null;
    }

    public boolean remove(String key) {
        if (key == null) {
            NullKey();
        }

        int index = getIndex(key);
        SinglyLinkedList<StationEntry> bucket = buckets[index];
        SinglyLinkedListNode<StationEntry> current = bucket.head;
        SinglyLinkedListNode<StationEntry> previous = null;

        while (current != null) {
            if (current.data.areEquals(key)) {
                if (previous == null) { // If the node to remove is the head
                    bucket.head = current.next;
                } else { // If the node to remove is not the head
                    previous.next = current.next; // Remove the node
                }
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }

        return false;
    }

    public boolean containsKey(String key) {
        return get(key) != null;
    }

    public int size() {
        return size;
    }

    private void NullKey() {
        throw new AssertionError("Key cannot be null");
    }

    public int getIndex(String key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    private void resize() {
        int newCapacity = buckets.length * 2;
        SinglyLinkedList<StationEntry>[] newBuckets = new SinglyLinkedList[newCapacity];
        for (int i = 0; i < newCapacity; i++) {
            newBuckets[i] = new SinglyLinkedList<>();
        }

        for (SinglyLinkedList<StationEntry> bucket : buckets) {
            SinglyLinkedListNode<StationEntry> current = bucket.head;
            while (current != null) {
                int index = getIndex(current.data.key);
                newBuckets[index].append(current.data);
                current = current.next;
            }
        }

        buckets = newBuckets;
    }
}

//
//public class StationHashMap {
//    private static final int DEFAULT_SIZE = 8;
//    private static final double LOAD_FACTOR = 0.75;
//
//    private SinglyLinkedList<StationEntry>[] map;
//    private int count = 0;
//
//    public StationHashMap() {
//        this.map = new SinglyLinkedList[DEFAULT_SIZE];
//        for (int i = 0; i < map.length; i++)
//            map[i] = new SinglyLinkedList<>(); // initialize each index of the map to an empty list
//    }
//
//    // return true if the map is empty
//    public boolean isEmpty() {
//        return getCount() == 0;
//    }
//
//    // return the number of entries in the map
//    public int getCount() {
//        return this.count;
//    }
//
//    // return true if the map contains the key
//    public boolean containsKey(String key) {
//        // preconditions: key is not null
//        if (key == null) {
//            throw new AssertionError("key is null");
//        }
//        int index = getIndex(key) % map.length;
//
//        SinglyLinkedList<StationEntry> bucket = map[index];
//
//        // check if the list contains the key and return true if it does
//        if (bucket == null) {
//            return false;
//        } else {
//            for (int i = 0; i < bucket.size(); i++) {
//                if (bucket.get(i).areEquals(key)) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    public void put(Station value) {
//        if (value == null) {
//            throw new AssertionError("key is null");
//        }
//
//        if(count >= map.length) {
//            remap();
//        }
//
//        int index = getIndex(value.getCode()) % map.length; // calculate the index based on the hashcode
//
//        // get the list at the index
//        SinglyLinkedList<StationEntry> bucket = map[index];
//
//        // check if list is null and create a new list if it is
//        if (bucket == null) {
//            bucket = new SinglyLinkedList<>();
//            map[index] = bucket;
//            bucket.append(new StationEntry(value.getCode(), value));
//            count++;
//        } else {
//            // check if list contains the key and update the value if it does
//            for (int i = 0; i < bucket.size(); i++) {
//                StationEntry entry = bucket.get(i);
//                if (entry.areEquals(value.getCode())) {
//                    entry.station = value;
//                    count++;
//                    return;
//                }
//            }
//
//            // add the entry to the list
//            bucket.append(new StationEntry(value.getCode(), value));
//            count++;
//            return;
//        }
//
////        // check if the list contains the key and update the value if it does
////        if(containsKey(value.getCode())) {
////            for (StationEntry entry : bucket) {
////                if (entry.areEquals(value.getCode())) {
////                    entry.station = value;
////                }
////            }
////        }
//    }
//
//    private int getIndex(String key) {
//        return Math.abs(key.hashCode());
//    }
//
//    // return the value for the key or null if the key is not found
//    public StationEntry get(String key) {
//        // preconditions: key is not null,
//        if (key == null) {
//            throw new AssertionError("key is null");
//        }
//
//        int index = getIndex(key) % map.length;
//
//        // get the list at the index
//        SinglyLinkedList<StationEntry> bucket = map[index];
//
//        // check if list is null and return null if it is
//        if (bucket == null) {
//            return null;
//        } else {
//            // check if list contains the key and return the value if it does
//            if (containsKey(key)) {
//                for (int i = 0; i < bucket.size(); i++) {
//                    if (map[index].get(i).areEquals(key)) {
//                        return map[index].get(i);
//                    }
//                }
//
//            }
//        }
//
//        return null;
//    }
//
//    // remove the entry for the key and return the value if it exists
//    public Station remove(String key) {
//        if (key == null) {
//            throw new AssertionError("key is null");
//        }
//
//        int index = getIndex(key) % map.length;
//
//        SinglyLinkedList<StationEntry> bucket = map[index];
//        // check if list is null and return null if it is
//        if (bucket == null) {
//            return null;
//        } else {
//            // check if list contains the key and return the value if it does
//            if (containsKey(key)) {
//                for (int i = 0; i < bucket.size(); i++) {
//                    if (bucket.get(i).areEquals(key)) {
//                        bucket.remove(bucket.get(i));
//                        bucket.get(i).setDeleted(true);
//                        count--;
//                        return bucket.get(i).station;
//                    }
//                }
//            }
//        }
//        return null;
//    }
//
//    // remap the entries to a new map with a larger size when the load factor is reached
//    public void remap() {
//        // create a new map with a larger size
//        SinglyLinkedList<StationEntry>[] newMap = new SinglyLinkedList[map.length * 2];
//        count = 0;
//        // loop through the map and add the entries to the new map
//        for (SinglyLinkedList<StationEntry> list : map) {
//            if(list == null) { continue; }
//            for (int i = 0; i < list.size(); i++) {
//                StationEntry entry = list.get(i);
//                if (!entry.isDeleted()) {
//                    int index = getIndex(entry.key) % newMap.length;
//                    if (newMap[index] == null) {
//                        newMap[index] = new SinglyLinkedList<>();
//                    }
//                    newMap[index].append(entry);
//                    count++;
//                }
//            }
//        }
//
//        // set the map to the new map
//        map = newMap;
//    }
//
//    public static void main(String[] args) {
//        StationHashMap map = new StationHashMap();
//        Station station1 = new Station("1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1");
//        Station station2 = new Station("2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2");
//        Station station3 = new Station("3", "3", "3", "3", "3", "3", "3", "3", "3", "3", "3");
//        Station station4 = new Station("4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4");
//        Station station5 = new Station("5", "5", "5", "5", "5", "5", "5", "5", "5", "5", "5");
//        Station station6 = new Station("6", "6", "6", "6", "6", "6", "6", "6", "6", "6", "6");
//        Station station7 = new Station("7", "7", "7", "7", "7", "7", "7", "7", "7", "7", "7");
//        Station station8 = new Station("8", "8", "8", "8", "8", "8", "8", "8", "8", "8", "8");
//        Station station9 = new Station("9", "9", "9", "9", "9", "9", "9", "9", "9", "9", "9");
//        Station station10 = new Station("10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10");
//        Station station11 = new Station("11", "11", "11", "11", "11", "11", "11", "11", "11", "11", "11");
//        Station station12 = new Station("12", "12", "12", "12", "12", "12", "12", "12", "12", "12", "12");
//        Station station13 = new Station("13", "13", "13", "13", "13", "13", "13", "13", "13", "13", "13");
//        Station station14 = new Station("14", "14", "14", "14", "14", "14", "14", "14", "14", "14", "14");
//        Station station15 = new Station("15", "15", "15", "15", "15", "15", "15", "15", "15", "15", "15");
//        Station station16 = new Station("16", "16", "16", "16", "16", "16", "16", "16", "16", "16", "16");
//        Station station17 = new Station("17", "17", "17", "17", "17", "17", "17", "17", "17", "17", "17");
//        Station station18 = new Station("18", "18", "18", "18", "18", "18", "18", "18", "18", "18", "18");
//        Station station19 = new Station("19", "19", "19", "19", "19", "19", "19", "19", "19", "19", "19");
//        Station station20 = new Station("20", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20");
//
//
//        map.put(station1);
//        map.put(station2);
//        map.put(station3);
//        map.put(station4);
//        map.put(station5);
//        map.put(station6);
//        map.put(station7);
//        map.put(station8);
//        map.put(station9);
//        map.put(station10);
//        map.put(station11);
//        map.put(station12);
//        map.put(station13);
//        map.put(station14);
//        map.put(station15);
//        map.put(station16);
//        map.put(station17);
//        map.put(station18);
//        map.put(station19);
//        map.put(station20);
//
//        System.out.println(map.containsKey("1"));
//    }
//}