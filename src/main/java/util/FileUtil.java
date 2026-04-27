package util;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    private static final String FILE = "attendance.csv";

    // Save attendance
    public static void save(String date, String roll, String status) {
        try (FileWriter fw = new FileWriter(FILE, true)) {
            fw.write(date + "," + roll + "," + status + "\n");
        } catch (IOException e) {
            System.out.println("File write error: " + e.getMessage());
        }
    }

    // Read all
    public static List<String[]> readAll() {
        List<String[]> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line.split(","));
            }
        } catch (IOException e) {
            System.out.println("File read error: " + e.getMessage());
        }

        return data;
    }
}
