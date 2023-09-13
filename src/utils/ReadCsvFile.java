package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadCsvFile {
    private String path;

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
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("Something went wrong.");
        }

        // post conditions: data is not empty
        assert !data.isEmpty();

        return data;
    }
}
