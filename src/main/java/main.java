import java.util.Scanner;

import module.AttendanceManager;
import module.StudentsManager;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        StudentsManager sm = new StudentsManager();
        AttendanceManager am = new AttendanceManager(sm);

        while (true) {
            try {
                System.out.println("\n===== ATTENDANCE MANAGEMENT SYSTEM =====");
                System.out.println("1. Add Student");
                System.out.println("2. Display Students");
                System.out.println("3. Mark Attendance");
                System.out.println("4. View Attendance of Student");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {

                    case 1:
                        System.out.print("Enter Roll No: ");
                        String roll = sc.nextLine();

                        // ✅ Roll validation
                        if (!roll.matches("\\d+")) {
                            System.out.println("Invalid roll number (digits only).");
                            break;
                        }

                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();

                        if (name.trim().isEmpty()) {
                            System.out.println("Name cannot be empty.");
                            break;
                        }

                        System.out.print("Enter Class: ");
                        String cls = sc.nextLine();

                        if (cls.trim().isEmpty()) {
                            cls = "N/A";
                        }

                        sm.addStudent(roll, name, cls);
                        break;

                    case 2:
                        sm.displayStudents();
                        break;

                    case 3:
                        am.markAttendance();
                        break;

                    case 4:
                        am.viewAttendance();
                        break;

                    case 5:
                        System.out.println("Exiting system...");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice!");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter numbers only.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
