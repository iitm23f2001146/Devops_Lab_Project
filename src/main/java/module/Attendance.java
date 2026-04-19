package module;

import java.util.HashMap;

public class Attendance {
    private String date;
    private HashMap<String, String> studentAttendance; // RollNo -> P/A

    // Constructor
    public Attendance(String date) {
        this.date = date;
        studentAttendance = new HashMap<>();
    }

    // Mark attendance for a student
    public void markAttendance(String rollNo, String status) {
        studentAttendance.put(rollNo, status);
    }

    // Get all attendance for the date
    public HashMap<String, String> getStudentAttendance() {
        return studentAttendance;
    }

    // Get the date
    public String getDate() {
        return date;
    }
}
