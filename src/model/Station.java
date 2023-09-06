package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Station {
    private String id;
    private String code;
    private String shortCode;
    private String nameShort;
    private String nameMedium;
    private String nameLong;
    private String slug;
    private String countryCode;
    private String type;
    private String latitude;
    private String longitude;

    public Station () {}

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

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getShortCode() {
        return shortCode;
    }

    public String getNameShort() {
        return nameShort;
    }

    public String getNameMedium() {
        return nameMedium;
    }

    public String getNameLong() {
        return nameLong;
    }

    public String getSlug() {
        return slug;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getType() {
        return type;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
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

    public static ArrayList<Station> readStations(String filename) throws IOException {
        ArrayList<Station> stations = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();

            // skip header line
            line = reader.readLine();

            while (line != null) {
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
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stations;
    }

    public static ArrayList<String> getStationNames(ArrayList<Station> stations) {
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
