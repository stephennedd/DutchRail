package model;

public class Connection implements Comparable<Connection> {

    public final String fromStation;
    public final String toStation;
    final int distance;
    final int number2;
    final int number3;

    public Connection(String fromStation, String toStation, int distance, int number2, int number3) {
        this.fromStation = fromStation;
        this.toStation = toStation;
        this.distance = distance;
        this.number2 = number2;
        this.number3 = number3;
    }

//    public String getFromStation() {
//        return fromStation;
//    }
//
//    public String getToStation() {
//        return toStation;
//    }
//
//    public int getDistance() {
//        return distance;
//    }
//
//    public int getNumber2() {
//        return number2;
//    }
//
//    public int getNumber3() {
//        return number3;
//    }

    public String toString() {
        return "Connection{" + fromStation + " " + toStation + " " + distance + " " + number2 + " " + number3 + "}";
    }

    @Override
    public int compareTo(Connection o) {
        return this.fromStation.compareTo(o.fromStation);
    }

    public int compareByDistance(Connection o) {
        return this.distance - o.distance;
    }
}
