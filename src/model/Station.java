package model;

import java.util.ArrayList;
import java.util.List;


public class Station {
    private final String id;
    private final String code;
    private final String shortCode;
    private final String nameShort;
    private final String nameMedium;
    private final String nameLong;
    private final String slug;
    private final String countryCode;
    private final String type;
    private final String latitude;
    private final String longitude;


    public Station(String id, String code, String shortCode, String nameShort, String nameMedium, String nameLong, String slug, String countryCode, String type, String latitude, String longitude) {
        assert id != null;
        assert code != null;
        assert shortCode != null;
        assert nameShort != null;
        assert nameMedium != null;
        assert nameLong != null;
        assert slug != null;
        assert countryCode != null;
        assert type != null;
        assert latitude != null;
        assert longitude != null;

        this.id = id;
        this.code = code;
        this.shortCode = shortCode;
        this.nameShort = nameShort;
        this.nameMedium = nameMedium;
        this.nameLong = nameLong;
        this.slug = slug;
        this.countryCode = countryCode;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getNameLong() {
        return nameLong;
    }
    public String getNameShort() {
        return nameShort;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Station{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", shortCode='" + shortCode + '\'' +
                ", nameShort='" + nameShort + '\'' +
                ", nameMedium='" + nameMedium + '\'' +
                ", nameLong='" + nameLong + '\'' +
                ", slug='" + slug + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", type='" + type + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }


    // Get the station names from the stations. complexity is 0(n)
    public static List<String> getStationNames(List<Station> stations) {
        // preconditions: stations is not null and not empty
        assert stations != null : "stations is null";
        assert !stations.isEmpty() : "stations is empty";

        ArrayList<String> stationNames = new ArrayList<>();
        // Loop through the stations and add the station names to the stationNames ArrayList start at 1 to skip the header
        for (int i = 1; i < stations.size(); i++) {
            stationNames.add(stations.get(i).getNameShort());
        }

        // Sort the station names alphabetically
        return stationNames;
    }

    // linear search stations by name. this is an 0(n) algorithm
    public static Station linearSearchByNameShort(List<Station> stations, String searchedStation) {
        System.out.println("Linear search for: " + searchedStation);
        // preconditions: stations is not null and not empty
        assert stations != null : "stations is null";
        assert !stations.isEmpty() : "stations is empty";
        assert searchedStation != null : "searchedStation is null";

        long startTime = System.nanoTime();

        for(Station station: stations) {
            if(station.getNameShort().equals(searchedStation)) {
                long endTime = System.nanoTime();
                System.out.println("Execution time linear search in nanoseconds: " + (endTime - startTime));
                System.out.println("Found station at index " + stations.indexOf(station) + ".");
                return station; // station found
            }
        }

        long endTime = System.nanoTime();
        System.out.println("Execution time linear search in nanoseconds: " + (endTime - startTime));
        System.out.println("Station not found");
        return null; // station not found
    }

    // binary search stations by name. this is an 0(log n) algorithm
    public static Station binarySearchByNameShort(List<Station> stations, String searchedName) {
        System.out.println("Binary search for: " + searchedName);
        // preconditions: searchName is not null and stationNames is sorted alphabetically
        assert searchedName != null : "searchName is null";
        assert stations != null : "stationNames is null";
        assert !stations.isEmpty() : "stationNames is empty";
        assert stations.get(0).getNameLong().compareToIgnoreCase(stations.get(stations.size() - 1).getNameLong()) < 0 : "stationNames is not sorted alphabetically";

        int left = 0;
        int right = stations.size() - 1;

        long startTime = System.nanoTime();
        while (left <= right) {
            int mid = left + (right - left) / 2;
            Station midStation = stations.get(mid);

            int comparison = searchedName.compareTo(midStation.getNameShort());

            if (comparison == 0) {
                long endTime = System.nanoTime();
                System.out.println("Execution time binary search in nanoseconds: " + (endTime - startTime));
                System.out.println("Found station at index " + mid + ".");
                return midStation; // Found the station
            } else if (comparison < 0) {
                right = mid - 1; // Search in the left half
            } else {
                left = mid + 1; // Search in the right half
            }
        }
        long endTime = System.nanoTime();
        System.out.println("Execution time binary search in nanoseconds: " + (endTime - startTime));
        System.out.println("Station not found");
        return null; // Station not found
    }
}
