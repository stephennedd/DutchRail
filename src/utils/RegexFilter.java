package utils;

import java.io.*;
import java.util.regex.Pattern;

public class RegexFilter {

    public static String[] parseCSVLine(String line, Pattern regex) {
        // split input line with regex
        String [] fields = regex.split(line);
        for (int i = 0; i < fields.length; i++) {
            // get rid of quotes
            fields[i] = fields[i].replace("\"", "");
        }
        return fields;
    }

    public static void main(String[] args) throws IOException {
        // example line from stations.csv:
        // 266,HT,8400319,"Den Bosch",'s-Hertogenbosch,'s-Hertogenbosch,s-hertogenbosch,NL,knooppuntIntercitystation,51.69048,5.29362
        Pattern regex = Pattern.compile(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

        try {
            BufferedReader reader = new BufferedReader(new FileReader("data/tracks.csv"));
            String line;
            int count = 1;

            // read the file line by line
            while ((line = reader.readLine()) != null) {
                // parse the line
                String[] fields = parseCSVLine(line, regex);

                // print the fields
                for (String field : fields) {
                    System.out.print(field + "\t\t");
                }
                System.out.println(count++);
            }
            reader.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
