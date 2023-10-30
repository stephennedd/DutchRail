package model;

import lists.SinglyLinkedList;

import java.util.ArrayList;
import java.util.List;


public class Station implements Comparable<Station> {
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

    public String getSlug() {
        return slug;
    }

    @Override
    public String toString() {
        return id + ", " + code + ", " + shortCode + ", " + nameShort + ", " + nameMedium + ", " + nameLong + ", " + slug + ", " + countryCode + ", " + type + ", " + latitude + ", " + longitude;
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

    public static int linearSearchByNameShortSinglyLinkedList(SinglyLinkedList<Station> stations, String searchedStation, boolean searchBy) {
        System.out.println("Linear search for: " + searchedStation);
        // preconditions: stations is not null and not empty
        assert stations != null : "stations is null";
        assert !stations.isEmpty() : "stations is empty";
        assert searchedStation != null : "searchedStation is null";

        long startTime = System.nanoTime();

        for (int i = 0; i < stations.size(); i++) {
            if (searchBy) {
                if (stations.get(i).getNameShort().equals(searchedStation)) {
                    long endTime = System.nanoTime();
                    System.out.println("Execution time linear search in nanoseconds: " + (endTime - startTime));
                    System.out.println("Found station at index " + i + ".");
                    return i; // station found
                }
            } else {
                if (stations.get(i).getCode().equals(searchedStation)) {
                    long endTime = System.nanoTime();
                    System.out.println("Execution time linear search in nanoseconds: " + (endTime - startTime));
                    System.out.println("Found station at index " + i + ".");
                    return i; // station found
                }
            }
        }

        long endTime = System.nanoTime();
        System.out.println("Execution time linear search in nanoseconds: " + (endTime - startTime));
        System.out.println("Station not found");
        return -1; // station not found
    }

    // binary search stations by name. this is an 0(log n) algorithm
    public static Station binarySearchByNameShort(List<Station> stations, String searchedName) {
        System.out.println("Binary search for: " + searchedName);
        // preconditions: searchName is not null and stationNames is sorted alphabetically
        assert searchedName != null : "searchName is null";
        assert stations != null : "stationNames is null";
        assert !stations.isEmpty() : "stationNames is empty";
        assert stations.get(0).getSlug().compareToIgnoreCase(stations.get(stations.size() - 1).getSlug()) < 0 : "stationNames is not sorted alphabetically";

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

    // binary search stations by name. this is an 0(log n) algorithm
    public static int binarySearchByNameShortSinglyLinkedList(SinglyLinkedList<Station> stations, String searchedName, boolean b) {
        System.out.println("Binary search for: " + searchedName);
        // preconditions: searchName is not null and stationNames is sorted alphabetically
        assert searchedName != null : "searchedName cannot null";
        assert stations != null : "stations singly-linked-list is null";
        assert !stations.isEmpty() : "stations singly-linked-list is empty";
        assert stations.get(0).getNameShort().compareToIgnoreCase(stations.get(stations.size() - 1).getNameShort()) < 0 : "stations singly-linked-list is not sorted alphabetically";

        if (!b) {
            // sort stations by code
            sortByCode(stations);
        }

        long startTime = System.nanoTime();

        int left = 0;
        int right = stations.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            Station midStation = stations.get(mid);

            int comparison;
            if (b) {
                comparison = searchedName.compareTo(midStation.getNameShort());
            } else {
                comparison = searchedName.compareTo(midStation.getCode());
            }

            if (comparison == 0) {
                long endTime = System.nanoTime();
                System.out.println("Execution time binary search in nanoseconds: " + (endTime - startTime));
                System.out.println("Found station at index " + mid + ".");
                return mid; // Found the station
            } else if (comparison < 0) {
                right = mid - 1; // Search in the left half
            } else {
                left = mid + 1; // Search in the right half
            }
        }

        long endTime = System.nanoTime();
        System.out.println("Execution time binary search in nanoseconds: " + (endTime - startTime));
        System.out.println("Station not found");
        return -1; // Station not found
    }

    // sort stations by name. this is an 0(n log n) algorithm
    public static void sortByNameShort(SinglyLinkedList<Station> stations) {
        System.out.println("Sorting stations by name");
        stations.sort(Station::compareTo);
    }

    // sort stations by code. this is an 0(n log n) algorithm
    public static void sortByCode(SinglyLinkedList<Station> stations) {
        System.out.println("Sorting stations by code");
        stations.sort(Station::compareByCode);
    }



    @Override
    public int compareTo(Station o) {
        return this.getNameShort().compareTo(o.getNameShort());
    }

    public int compareByCode(Station o) {
        return this.getCode().compareTo(o.getCode());
    }
}
