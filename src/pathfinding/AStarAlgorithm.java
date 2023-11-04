package pathfinding;

import lists.HashTable;
import model.Connection;
import model.Station;
import trees.MinHeap;

import java.util.*;

import static model.Station.getStationByCode;

public class AStarAlgorithm {
    public static ShortestPathResult findShortestPath(Station fromStation, Station toStation, HashTable<String, Station> stationHashTable) {
        return null;
    }

    private static class AStarNode implements Comparable<AStarNode> {
        final Station station;
        AStarNode cameFrom;
        int gScore;
        int fScore;

        AStarNode(Station station) {
            this.station = station;
            this.gScore = Integer.MAX_VALUE;
            this.fScore = Integer.MAX_VALUE;
        }

        @Override
        public int compareTo(AStarNode other) {
            return Integer.compare(fScore, other.fScore);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            AStarNode aStarNode = (AStarNode) obj;

            return station.equals(aStarNode.station);
        }
    }


}
