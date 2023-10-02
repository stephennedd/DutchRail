package lists;

import model.Station;

public class StationEntry {
    private final String key;
    private Station station;
    private boolean deleted;

    public StationEntry(String key, Station station) {
        this.key = key;
        this.station = station;
        this.deleted = false;
    }

    public String getKey() {
        return key;
    }

    public Station getValue() {
        return station;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void setValue(Station station) {
        this.station = station;
    }

    public boolean areEquals(String other) {
        return this.key.equals(other);
    }

}
