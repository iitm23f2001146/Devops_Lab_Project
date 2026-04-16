import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class AttendanceManager {
    private Map<Integer, String> students = new LinkedHashMap<>();
    // date -> (roll -> status)
    private Map<String, Map<Integer, String>> attendance = new HashMap<>();

    public void addStudent(int roll, String name) {
        students.put(roll, name);
        System.out.println("Student added: " + roll + " - " + name);
    }

    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        System.out.println("Roll No | Name");
        for (Map.Entry<Integer, String> e : students.entrySet()) {
            System.out.println(e.getKey() + " | " + e.getValue());
        }
    }

    public void markAttendance(String date) {
        if (students.isEmpty()) {
            System.out.println("No students to mark attendance for.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        Map<Integer, String> daily = attendance.getOrDefault(date, new HashMap<>());

        for (Map.Entry<Integer, String> e : students.entrySet()) {
            System.out.print("Roll " + e.getKey() + " (" + e.getValue() + ") - Present or Absent (P/A): ");
            String status = sc.next().trim().toUpperCase();
            if (!status.equals("P") && !status.equals("A")) {
                System.out.println("Invalid input, marking Absent.");
                status = "A";
            }
            daily.put(e.getKey(), status);
        }

        attendance.put(date, daily);
        System.out.println("Attendance recorded for " + date);
    }

    public void viewAttendance(int roll) {
        if (!students.containsKey(roll)) {
            System.out.println("Student with roll " + roll + " not found.");
            return;
        }

        System.out.println("Attendance for " + roll + " - " + students.get(roll));
        for (Map.Entry<String, Map<Integer, String>> e : attendance.entrySet()) {
            String date = e.getKey();
            Map<Integer, String> daily = e.getValue();
            String status = daily.getOrDefault(roll, "N/A");
            System.out.println(date + " : " + status);
        }
    }

    public Map<Integer, String> getStudentsMap() {
        return students;
    }

    public Map<String, Map<Integer, String>> getAttendanceMap() {
        return attendance;
    }
}
