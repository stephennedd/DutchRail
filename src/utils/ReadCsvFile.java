package utils;

import model.Connection;
import model.Station;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


public class ReadCsvFile {
    private final String path;

    public ReadCsvFile(String path) {
        this.path = path;
    }

    public ArrayList<String> read() {
        // preconditions: path is not null
        assert this.path != null;

        ArrayList<String> data = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.path));
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found.");
        } catch (IOException e) {
            throw new RuntimeException("IO Exception.");
        }

        // post conditions: data is not empty
        assert !data.isEmpty();

        return data;
    }

    // Read the stations from the file. complexity is 0(n)
    public static List<Station> readStations(String filename) {
        // preconditions: filename is not null
        assert filename != null : "filename is null";

        ArrayList<Station> stations = new ArrayList<>();
        ReadCsvFile reader = new ReadCsvFile(filename);
        ArrayList<String> data = reader.read();

        assert !data.isEmpty() : "data is empty";

        int expectedLength = 11;
        for (String line : data) {
            String[] parts = line.split(",");
            assert parts.length == expectedLength : "parts length is not " + expectedLength;
        }

        for (String line : data) {
            String[] parts = line.split(",");
            // strip the quotes from the parts
            for (int i = 0; i < parts.length; i++) {
                Pattern pattern = Pattern.compile("^\"(.*)\"$");
                Matcher matcher = pattern.matcher(parts[i]);
                if (matcher.find()) {
                    parts[i] = matcher.group(1);
                }
            }
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
    }

    public static List<Station> readStationsWithValidation(String filename) {
        // preconditions: filename is not null
        assert filename != null : "filename is null";
        List<Station> stationList = new ArrayList<>();

        try (FileReader fileReader = new FileReader(filename);
             CSVParser csvParser = CSVFormat.DEFAULT
                     .withFirstRecordAsHeader() // Assuming the first row is the header
                     .parse(fileReader)) {

            for (CSVRecord record : csvParser) {
                String id = record.get("id");
                String code = record.get("code");
                String uic = record.get("uic");
                String nameShort = record.get("name_short");
                String nameMedium = record.get("name_medium");
                String nameLong = record.get("name_long");
                String slug = record.get("slug");
                String country = record.get("country");
                String type = record.get("type");
                String geoLat = record.get("geo_lat");
                String geoLng = record.get("geo_lng");

                // Create an instance of YourDataClass and populate it with the parsed data
                Station station = new Station(id, code, uic, nameShort, nameMedium, nameLong, slug, country, type, geoLat, geoLng);

                stationList.add(station);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return stationList;
    }



    // sort the station names alphabetically. complexity is 0(n^2)
    public static List<Station> sortListByNameLong(List<Station> stations) {
        // preconditions: stations is not null and not empty
        assert stations != null : "stations is null";
        assert !stations.isEmpty() : "stations is empty";

        for (int i = 0; i < stations.size(); i++) {
            for (int j = i + 1; j < stations.size(); j++) {
                if (stations.get(i).getNameLong().compareToIgnoreCase(stations.get(j).getNameLong()) > 0) {
                    Station temp = stations.get(i);
                    stations.set(i, stations.get(j));
                    stations.set(j, temp);
                }
            }
        }
        return stations;
    }

    public static ArrayList<Connection> readConnections(String filename) {
        // preconditions: filename is not null
        assert filename != null : "filename is null";

        ArrayList<Connection> connections = new ArrayList<>();
        ReadCsvFile reader = new ReadCsvFile(filename);
        ArrayList<String> data = reader.read();

        assert !data.isEmpty() : "data is empty";

        int expectedLength = 5;
        for (String line : data) {
            String[] parts = line.split(",");
            assert parts.length == expectedLength : "parts length is not " + expectedLength;
        }

        for (String line : data) {
            String[] parts = line.split(",");

            Connection connection = new Connection(
                    parts[0],
                    parts[1],
                    Integer.parseInt(parts[2]),
                    Integer.parseInt(parts[3]),
                    Integer.parseInt(parts[4])
            );
            connections.add(connection);
        }
        return connections;
    }
}
