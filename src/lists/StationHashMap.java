package lists;

import model.Station;

import java.util.ArrayList;

public class StationHashMap {
    private static final int DEFAULT_SIZE = 16;
    private static final double LOAD_FACTOR = 0.75;

    private ArrayList<StationEntry>[] map;
    private int count;

    public StationHashMap() {
        this.map = new ArrayList[DEFAULT_SIZE];
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
        int index = key.hashCode() % map.length;
        // check if the list is null and return false if it is
        ArrayList<StationEntry> list = map[index];
        if (list == null) {
            return false;
        }
        // check if the list contains the key and return true if it does
        for (StationEntry entry : list) {
            if (entry.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    // public boolean containsValue(Station value) {
    //     return false;
    // }

    public void put(String code, Station value) {
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

        for (StationEntry stationEntry : bucket) {
            if (stationEntry.getKey().equals(value.getCode())) { // check if the key already exists
                stationEntry.setValue(value);
                return;
            }
        }

        // otherwise add the entry to the list and return null
        bucket.add(new StationEntry(value));
        count++;

        if ((double) count/map.length > LOAD_FACTOR) {
            remap();
        }
    }

    private int getIndex(String key) {
        return key.hashCode() % map.length;
    }

    // return the value for the key or null if the key is not found
    public Station get(String key) {
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
            for (StationEntry entry : bucket) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
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
            for (StationEntry entry : bucket) {
                if (entry.getKey().equals(key)) {
                    Station value = entry.getValue();
                    entry.setDeleted(true);
                    bucket.remove(entry);
                    // update the count
                    count--;

                    return value;
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