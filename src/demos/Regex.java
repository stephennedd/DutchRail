package demos;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Regex {
    public static void main(String[] args) {

        String regex = "^[0-9]+,[A-Z]+,[0-9]+,";
        String regex2 = "266";

        try {
            Reader input = new FileReader("data/stations.csv");
            int count = utils.RegexFilter.filter(input, java.util.regex.Pattern.compile(regex));
            System.out.println("number of matcher: " + count);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("Something went wrong.");
        }
    }
}
