package lists;

import model.Station;

import java.util.ArrayList;

public class StationHashMap {
    private ArrayList<StationEntry>[] map;
    private int count;

    public StationHashMap(int size) {
        this.map = new ArrayList[size];
        this.count = 0;
    }

    public StationHashMap() {
        this(11);
    }


    public boolean isEmpty() {
        return getCount() == 0;
    }


    public int getCount() {
        return this.count;
    }

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

    public Station put(Station value) {
        StationEntry entry = new StationEntry(value);
        int index = entry.hashCode() % map.length;

        ArrayList<StationEntry> list = map[index];
        // check if list is null and create a new list if it is
        if (list == null) {
            list = new ArrayList<>();
            map[index] = list;
        }

        // check if list already contains the key and return the value if it does
        // otherwise add the entry to the list and return null
        // map[index] = entry;
        return null;
    }


    public Station get(String key) {
        int index = key.hashCode() % map.length;

        ArrayList<StationEntry> list = map[index];
        // check if list is null and return null if it is
        if (list == null) {
            return null;
        } else {
            // check if list contains the key and return the value if it does
            for (StationEntry entry : list) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
        }

        return null;
    }

    public Station remove(String key) {
        int index = key.hashCode() % map.length;

        ArrayList<StationEntry> list = map[index];
        // check if list is null and return null if it is
        if (list == null) {
            return null;
        } else {
            // check if list contains the key and return the value if it does
            for (StationEntry entry : list) {
                if (entry.getKey().equals(key)) {
                    Station value = entry.getValue();
                    entry.setDeleted(true);

                    // update the count
                    count--;

                    return value;
                }
            }
        }
        return null;
    }

    // remap the entries to a new map with a larger size
    public void remap() {
        // create a new map with a larger size
        ArrayList<StationEntry>[] newMap = new ArrayList[map.length * 2];

        // loop through the map and add the entries to the new map
        for (int i = 0; i < map.length; i++) {
            ArrayList<StationEntry> list = map[i];
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