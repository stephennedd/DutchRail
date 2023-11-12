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
}
