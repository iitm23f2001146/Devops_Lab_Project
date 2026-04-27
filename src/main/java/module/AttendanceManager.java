package module;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import model.Student;
import util.FileUtil;

public class AttendanceManager {

    private final Scanner sc;   // ✅ changed
    private final StudentsManager sm;

    // ✅ Constructor for testing (inject Scanner)
    public AttendanceManager(StudentsManager sm, Scanner sc) {
        this.sm = sm;
        this.sc = sc;
    }

    // ✅ Default constructor (normal usage)
    public AttendanceManager(StudentsManager sm) {
        this(sm, new Scanner(System.in));
    }

    // ---------- ATTENDANCE ----------
    public void markAttendance() {

        String date = LocalDate.now().toString();

        if (sm.getStudents().isEmpty()) {
            System.out.println("No students available. Add students first.");
            return;
        }

        System.out.println("Marking attendance for date: " + date);

        for (Student s : sm.getStudents()) {

            String roll = s.getRollNo();
            System.out.println("Roll: " + roll + " | Name: " + s.getName());

            String status = readStatus();

            // ✅ SAVE TO FILE
            FileUtil.save(date, roll, status);
        }

        System.out.println("Attendance saved successfully.");
    }

    // ---------- VIEW ----------
    public void viewAttendance() {

        String roll = readRoll();

        List<String[]> data = FileUtil.readAll();

        if (data.isEmpty()) {
            System.out.println("No attendance data found.");
            return;
        }

        boolean found = false;

        for (String[] row : data) {
            // row[0] = date, row[1] = roll, row[2] = status
            if (row.length >= 3 && row[1].equals(roll)) {
                System.out.println(row[0] + " -> " + row[2]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No record found for roll: " + roll);
        }
    }

    // ---------- INPUT ----------
    private String readRoll() {
        while (true) {
            System.out.print("Enter Roll: ");
            String r = sc.nextLine().trim();

            if (!r.matches("\\d+")) {
                System.out.println("Invalid roll number (digits only).");
                continue;
            }

            boolean exists = sm.getStudents()
                    .stream()
                    .anyMatch(s -> s.getRollNo().equals(r));

            if (!exists) {
                System.out.println("Student not found.");
                continue;
            }

            return r;
        }
    }

    private String readStatus() {
        while (true) {
            System.out.print("P/A: ");
            String s = sc.nextLine().trim().toUpperCase();

            if (s.equals("P") || s.equals("A")) {
                return s;
            }

            System.out.println("Invalid status. Enter P or A.");
        }
    }
}








// package module;

// import java.time.LocalDate;
// import java.util.List;
// import java.util.Scanner;

// import model.Student;
// import util.FileUtil;

// public class AttendanceManager {

//     private final Scanner sc = new Scanner(System.in);
//     private final StudentsManager sm;

//     public AttendanceManager(StudentsManager sm) {
//         this.sm = sm;
//     }

//     // ---------- ATTENDANCE ----------
//     public void markAttendance() {

//         String date = LocalDate.now().toString();

//         if (sm.getStudents().isEmpty()) {
//             System.out.println("No students available. Add students first.");
//             return;
//         }

//         System.out.println("Marking attendance for date: " + date);

//         for (Student s : sm.getStudents()) {

//             String roll = s.getRollNo();
//             System.out.println("Roll: " + roll + " | Name: " + s.getName());

//             String status = readStatus();

//             // ✅ SAVE TO FILE
//             FileUtil.save(date, roll, status);
//         }

//         System.out.println("Attendance saved successfully.");
//     }

//     // ---------- VIEW ----------
//     public void viewAttendance() {

//         String roll = readRoll();

//         List<String[]> data = FileUtil.readAll();

//         if (data.isEmpty()) {
//             System.out.println("No attendance data found.");
//             return;
//         }

//         boolean found = false;

//         for (String[] row : data) {
//             // row[0] = date, row[1] = roll, row[2] = status
//             if (row.length >= 3 && row[1].equals(roll)) {
//                 System.out.println(row[0] + " -> " + row[2]);
//                 found = true;
//             }
//         }

//         if (!found) {
//             System.out.println("No record found for roll: " + roll);
//         }
//     }

//     // ---------- INPUT ----------
//     private String readRoll() {
//         while (true) {
//             System.out.print("Enter Roll: ");
//             String r = sc.nextLine().trim();

//             if (!r.matches("\\d+")) {
//                 System.out.println("Invalid roll number (digits only).");
//                 continue;
//             }

//             boolean exists = sm.getStudents()
//                     .stream()
//                     .anyMatch(s -> s.getRollNo().equals(r));

//             if (!exists) {
//                 System.out.println("Student not found.");
//                 continue;
//             }

//             return r;
//         }
//     }

//     private String readStatus() {
//         while (true) {
//             System.out.print("P/A: ");
//             String s = sc.nextLine().trim().toUpperCase();

//             if (s.equals("P") || s.equals("A")) {
//                 return s;
//             }

//             System.out.println("Invalid status. Enter P or A.");
//         }
//     }
// }