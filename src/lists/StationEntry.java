package lists;

import model.Station;

public class StationEntry {
    public final String key;
    public Station station;

    public StationEntry(String key, Station station) {
        this.key = key;
        this.station = station;
    }

    public boolean areEquals(String other) {
        return this.key.equals(other);
    }
}
