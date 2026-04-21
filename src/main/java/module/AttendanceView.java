package module;

import java.util.Scanner;

public class AttendanceView {

    // Sample attendance data
    static int[] studentIds = {101, 102, 103};
    static String[][] attendanceRecords = {
        {"2026-01-29", "Present"},
        {"2026-01-29", "Absent"},
        {"2026-01-29", "Present"}
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Student Attendance Viewing Module ===");
        System.out.println("1. View attendance of one student");
        System.out.println("2. View attendance of all students for a date");
        System.out.print("Enter choice (1 or 2): ");
        int choice = sc.nextInt();
        sc.nextLine(); // consume newline

        if (choice == 1) {
            System.out.print("Enter student ID: ");
            int id = sc.nextInt();
            viewSingleStudent(id);
        } else if (choice == 2) {
            System.out.print("Enter date (YYYY-MM-DD): ");
            String date = sc.nextLine();
            viewAllStudents(date);
        } else {
            System.out.println("Invalid choice!");
        }

        sc.close();
    }

    // View one student attendance
    static void viewSingleStudent(int studentId) {
        boolean found = false;
        for (int i = 0; i < studentIds.length; i++) {
            if (studentIds[i] == studentId) {
                System.out.println("Student ID: " + studentIds[i] + " | Attendance: " + attendanceRecords[i][1]);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Student not found!");
        }
    }

    // View all students for a specific date
    static void viewAllStudents(String date) {
        System.out.println("Attendance for date: " + date);
        boolean found = false;
        for (int i = 0; i < studentIds.length; i++) {
            if (attendanceRecords[i][0].equals(date)) {
                System.out.println("Student ID: " + studentIds[i] + " | Attendance: " + attendanceRecords[i][1]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No attendance records found for this date.");
        }
    }
}
