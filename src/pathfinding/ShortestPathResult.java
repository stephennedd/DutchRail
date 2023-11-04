package pathfinding;

import model.Station;

import java.util.List;

public class ShortestPathResult {

    private List<Station> route;
    private int totalDistance;

    public ShortestPathResult(List<Station> route, int totalDistance) {
        this.route = route;
        this.totalDistance = totalDistance;
    }

    public List<Station> getRoute() {
        return route;
    }

    public int getTotalDistance() {
        return totalDistance;
    }
}
