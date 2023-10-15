package model;

public class Connection implements Comparable<Connection> {

    private final String fromStation;
    private final String toStation;
    private final int distance;
    private final int number2;
    private final int number3;

    public Connection(String fromStation, String toStation, int distance, int number2, int number3) {
        this.fromStation = fromStation;
        this.toStation = toStation;
        this.distance = distance;
        this.number2 = number2;
        this.number3 = number3;
    }

    public String getFromStation() {
        return fromStation;
    }

    public String getToStation() {
        return toStation;
    }

    public int getDistance() {
        return distance;
    }

    public int getNumber2() {
        return number2;
    }

    public int getNumber3() {
        return number3;
    }

    public String toString() {
        return "Connection{" + fromStation + " " + toStation + " " + distance + " " + number2 + " " + number3 + "}";
    }

    @Override
    public int compareTo(Connection o) {
        return this.getFromStation().compareTo(o.getFromStation());
    }

    public int compareByDistance(Connection o) {
        return this.getDistance() - o.getDistance();
    }
}
