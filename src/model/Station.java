package model;

import utils.ReadCsvFile;


import java.util.ArrayList;


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

    public static ArrayList<Station> readStations(String filename) {
        ArrayList<Station> stations = new ArrayList<>();
        ReadCsvFile reader = new ReadCsvFile(filename);
        ArrayList<String> data = reader.read();

        if (!data.isEmpty()) {
            for (String line : data) {
                String[] parts = line.split(",");
                Station station = new Station(
                        parts[0],
                        parts[1],
                        parts[2],
                        parts[3],
                        parts[4],
                        parts[5],
                        parts[6],
                        parts[7],
                        parts[8],
                        parts[9],
                        parts[10]
                );
                stations.add(station);
            }

            return stations;
        } else {
            return null;
        }
    }

    public static ArrayList<String> getStationNames(ArrayList<Station> stations) {
        // preconditions: stations is not null and not empty
        assert stations != null : "stations is null";
        assert !stations.isEmpty() : "stations is empty";

        ArrayList<String> stationNames = new ArrayList<>();
        for (Station station : stations) {
            stationNames.add(station.getNameLong());
        }

        // Sort the station names alphabetically
        //stationNames.sort(String::compareToIgnoreCase);

        return stationNames;
    }

    // Binary search of station names
    public static int binarySearch(ArrayList<String> stationNames, String searchName) {
        // preconditions: searchName is not null and stationNames is sorted alphabetically
        assert searchName != null : "searchName is null";
        assert stationNames != null : "stationNames is null";
        assert !stationNames.isEmpty() : "stationNames is empty";

        int low = 0;
        int high = stationNames.size() - 1;
        int mid;

        // Loop until the searchName is found or the searchName is not found
        while (low <= high) {
            mid = (low + high) / 2;
            // Compare the searchName with the middle element
            if (stationNames.get(mid).compareToIgnoreCase(searchName) < 0) {
                // searchName is in the upper half
                low = mid + 1;
            } else if (stationNames.get(mid).compareToIgnoreCase(searchName) > 0) {
                // searchName is in the lower half
                high = mid - 1;
            } else {
                // searchName is found
                return mid;
            }
        }
        // searchName is not found
        return -1;
    }
}
