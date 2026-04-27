package module;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AttendanceManager {

    private final HashMap<Integer, String> studentsMap = new HashMap<>();
    private final HashMap<String, Map<Integer, String>> attendanceMap = new HashMap<>();
    private static final HashMap<String, Attendance> attendanceData = new HashMap<>();

    @SuppressWarnings("resource")
    public static void markAttendance() {
        Scanner sc = new Scanner(System.in);
        String date = LocalDate.now().toString();

        Attendance attendance = attendanceData.getOrDefault(
                date, new Attendance(date));

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter Roll No: ");
            String roll = sc.next();

            System.out.print("Present or Absent (P/A): ");
            String status = sc.next();

            attendance.markAttendance(roll, status.toUpperCase());
        }

        attendanceData.put(date, attendance);
        System.out.println("Attendance marked successfully for " + date);
    }

    public void addStudent(int rollNo, String name) {
        studentsMap.put(rollNo, name);
    }

    public void displayStudents() {
        if (studentsMap.isEmpty()) {
            System.out.println("No students available.");
            return;
        }
        for (Map.Entry<Integer, String> entry : studentsMap.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

    @SuppressWarnings("resource")
    public void markAttendance(String date) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of students: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter Roll No: ");
            int roll = sc.nextInt();
            System.out.print("Present or Absent (P/A): ");
            String status = sc.next();
            markAttendanceRecord(date, roll, status);
        }
        System.out.println("Attendance marked successfully for " + date);
    }

    public void markAttendanceRecord(String date, int rollNo, String status) {
        String normalizedStatus = status.toUpperCase();
        attendanceMap.computeIfAbsent(date, k -> new HashMap<>())
                .put(rollNo, normalizedStatus);

        Attendance attendance = attendanceData.computeIfAbsent(date, Attendance::new);
        attendance.markAttendance(String.valueOf(rollNo), normalizedStatus);
    }

    public void viewAttendance(int rollNo) {
        boolean found = false;
        for (Map.Entry<String, Map<Integer, String>> day : attendanceMap.entrySet()) {
            String status = day.getValue().get(rollNo);
            if (status != null) {
                System.out.println(day.getKey() + " -> " + status);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No attendance found for roll no: " + rollNo);
        }
    }

    public HashMap<Integer, String> getStudentsMap() {
        return studentsMap;
    }

    public HashMap<String, Map<Integer, String>> getAttendanceMap() {
        return attendanceMap;
    }

    public static HashMap<String, Attendance> getAttendanceData() {
        return attendanceData;
    }
}
