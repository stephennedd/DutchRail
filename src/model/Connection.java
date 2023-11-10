package model;

public class Connection implements Comparable<Connection> {

    public final String fromStation;
    public final String toStation;
    public final int distanceInKm;
    public final int distance2;
    public final int number3;

    public Connection(String fromStation, String toStation, int distanceInKm, int distance2, int number3) {
        this.fromStation = fromStation;
        this.toStation = toStation;
        this.distanceInKm = distanceInKm;
        this.distance2 = distance2;
        this.number3 = number3;
    }

    public String toString() {
        return "FROM: " + fromStation + "  -  TO: " + toStation + "    DISTANCE: " + distanceInKm + "KM    " + distance2 + "     " + number3;
    }

    @Override
    public int compareTo(Connection o) {
        return this.fromStation.compareTo(o.fromStation);
    }

    public int compareByDistance(Connection o) {
        return this.distanceInKm - o.distanceInKm;
    }
}
