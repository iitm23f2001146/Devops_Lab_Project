import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AttendanceManager attendanceManager = new AttendanceManager();
        FileHandler fileHandler = new FileHandler();

        int choice;

        // Load data at start
        fileHandler.loadStudents(attendanceManager);
        fileHandler.loadAttendance(attendanceManager);

        do {
            System.out.println("\n==== ATTENDANCE MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. Display Students");
            System.out.println("3. Mark Attendance");
            System.out.println("4. View Attendance of Student");
            System.out.println("5. Save Data");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {

                case 1:
                    System.out.print("Enter Roll No: ");
                    int roll = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    attendanceManager.addStudent(roll, name);
                    break;

                case 2:
                    attendanceManager.displayStudents();
                    break;

                case 3:
                    System.out.print("Enter Date (dd-mm-yyyy): ");
                    String date = sc.nextLine();
                    attendanceManager.markAttendance(date);
                    break;

                case 4:
                    System.out.print("Enter Roll No: ");
                    int r = sc.nextInt();
                    attendanceManager.viewAttendance(r);
                    break;

                case 5:
                    fileHandler.saveStudents(attendanceManager);
                    fileHandler.saveAttendance(attendanceManager);
                    System.out.println("Data saved successfully.");
                    break;

                case 6:
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 6);

        sc.close();
    }
}
