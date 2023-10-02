package lists;

import model.Station;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class StationHashMap {
    private static final int DEFAULT_SIZE = 16;
    private static final double LOAD_FACTOR = 0.75;

    private ArrayList<StationEntry>[] map;
    private int count;

    public StationHashMap() {
        this.map = new ArrayList[DEFAULT_SIZE];
        for (int i = 0; i < map.length; i++)
            map[i] = new ArrayList<>(); // initialize each index of the map to an empty list [
        this.count = 0;
    }

    // return true if the map is empty
    public boolean isEmpty() {
        return getCount() == 0;
    }

    // return the number of entries in the map
    public int getCount() {
        return this.count;
    }

    // return true if the map contains the key
    public boolean containsKey(String key) {
        // preconditions: key is not null
        if (key == null) {
            throw new AssertionError("key is null");
        }
        int index = getIndex(key);



        if (map == null) {
            return false;
        }
        // check if the list contains the key and return true if it does
        if (map[index] != null) {
            for (StationEntry entry : map[index]) {
                if (entry.areEquals(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    // public boolean containsValue(Station value) {
    //     return false;
    // }

    public void put(Station value) {
        if (value == null) {
            throw new AssertionError("key is null");
        }

        int index = getIndex(value.getCode()); // calculate the index based on the hashcode

        // get the list at the index
        ArrayList<StationEntry> bucket = map[index];

        // check if list is null and create a new list if it is
        if (bucket == null) {
            bucket = new ArrayList<>();
            map[index] = bucket;
        }

        // check if the list contains the key and update the value if it does
        if(containsKey(value.getCode())) {
            for (StationEntry entry : bucket) {
                if (entry.areEquals(value.getCode())) {
                    entry.setValue(value);
                }
            }
        }

        // otherwise add the entry to the list and return null
        bucket.add(new StationEntry(value.getCode() ,value));
        count++;

        if ((double) count/map.length > LOAD_FACTOR) {
            remap();
        }
    }

    private int getIndex(String key) {
        // calculate the index based on the hashcode using math.abs to avoid negative numbers
        int index = Math.abs(key.hashCode()) % map.length;
        return index;
    }

    // return the value for the key or null if the key is not found
    public Station get(String key) {
        // preconditions: key is not null,
        if (key == null) {
            throw new AssertionError("key is null");
        }

        int index = getIndex(key);

        // check if list is null and return null if it is
        if (map == null) {
            return null;
        } else {
            // check if list contains the key and return the value if it does
            if (containsKey(key)) {
                for (StationEntry entry : map[index]) {
                    if (entry.areEquals(key)) {
                        return (Station) entry.getValue();
                    }
                }
            }
        }

        return null;
    }

    // remove the entry for the key and return the value if it exists
    public Station remove(String key) {
        if (key == null) {
            throw new AssertionError("key is null");
        }

        int index = getIndex(key);

        ArrayList<StationEntry> bucket = map[index];
        // check if list is null and return null if it is
        if (bucket == null) {
            return null;
        } else {
            // check if list contains the key and return the value if it does
            if (containsKey(key)) {
                for (StationEntry entry : bucket) {
                    if (entry.getKey().equals(key)) {
                        entry.setDeleted(true);
                        count--;
                        return (Station) entry.getValue();
                    }
                }
            }
        }
        return null;
    }

    // remap the entries to a new map with a larger size when the load factor is reached
    public void remap() {
        // create a new map with a larger size
        ArrayList<StationEntry>[] newMap = new ArrayList[map.length * 2];

        // loop through the map and add the entries to the new map
        for (ArrayList<StationEntry> list : map) {
            if (list != null) {
                for (StationEntry entry : list) {
                    int index = entry.hashCode() % newMap.length;
                    ArrayList<StationEntry> newList = newMap[index];
                    if (newList == null) {
                        newList = new ArrayList<>();
                        newMap[index] = newList;
                    }
                    newList.add(entry);
                }
            }
        }

        // set the map to the new map
        map = newMap;
    }
}