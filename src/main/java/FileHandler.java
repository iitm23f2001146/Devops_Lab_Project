import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileHandler {
    private static final String STUDENTS_FILE = "students.txt";
    private static final String ATTENDANCE_FILE = "attendance.txt";

    public void loadStudents(AttendanceManager mgr) {
        File f = new File(STUDENTS_FILE);
        if (!f.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length == 2) {
                    try {
                        int roll = Integer.parseInt(parts[0].trim());
                        String name = parts[1].trim();
                        mgr.addStudent(roll, name);
                    } catch (NumberFormatException ex) {
                        // skip invalid lines
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to load students: " + e.getMessage());
        }
    }

    public void saveStudents(AttendanceManager mgr) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(STUDENTS_FILE))) {
            for (Map.Entry<Integer, String> e : mgr.getStudentsMap().entrySet()) {
                bw.write(e.getKey() + "," + e.getValue());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Failed to save students: " + e.getMessage());
        }
    }

    public void loadAttendance(AttendanceManager mgr) {
        File f = new File(ATTENDANCE_FILE);
        if (!f.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                // date,roll,status
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String date = parts[0].trim();
                    try {
                        int roll = Integer.parseInt(parts[1].trim());
                        String status = parts[2].trim();
                        mgr.getAttendanceMap().computeIfAbsent(date, k -> new HashMap<>()).put(roll, status);
                    } catch (NumberFormatException ex) {
                        // skip
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to load attendance: " + e.getMessage());
        }
    }

    public void saveAttendance(AttendanceManager mgr) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ATTENDANCE_FILE))) {
            for (Map.Entry<String, Map<Integer, String>> day : mgr.getAttendanceMap().entrySet()) {
                String date = day.getKey();
                for (Map.Entry<Integer, String> e : day.getValue().entrySet()) {
                    bw.write(date + "," + e.getKey() + "," + e.getValue());
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to save attendance: " + e.getMessage());
        }
    }
}
