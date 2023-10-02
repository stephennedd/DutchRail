package utils;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexFilter {

    public static int filter(Reader input, Pattern regex) throws IOException {
        BufferedReader reader = new BufferedReader(input);
        String line;
        int count = 0;

        // Read the file line by line
        while ((line = reader.readLine()) != null) {

            // Check if the line matches the regex
            Matcher matcher = regex.matcher(line);
            if (matcher.find()) {
                if (matcher.groupCount()>0) {
                    // Print the group if the line matches the regex and has a group
                    System.out.println("<group>" + matcher.group(1));
                }
                else {
                    // Print the line if the line matches the regex and has no group
                    System.out.println("<line> " + line);
                }
                // Increase the count by 1 if the line matches the regex
                count++;
            }
        }
        reader.close();
        return count;
    }
}
