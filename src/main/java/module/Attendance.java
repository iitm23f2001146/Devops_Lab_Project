// package module;

// import java.util.HashMap;

// public class Attendance {
//     private String date;
//     private HashMap<String, String> studentAttendance; // RollNo -> P/A

//     // Constructor
//     public Attendance(String date) {
//         this.date = date;
//         studentAttendance = new HashMap<>();
//     }

//     // Mark attendance for a student
//     public void markAttendance(String rollNo, String status) {
//         studentAttendance.put(rollNo, status);
//     }

//     // Get all attendance for the date
//     public HashMap<String, String> getStudentAttendance() {
//         return studentAttendance;
//     }

//     // Get the date
//     public String getDate() {
//         return date;
//     }
// }


package module;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Attendance {

    private final LocalDate date;
    private final Map<String, String> studentAttendance;

    public Attendance(String date) {
        this.date = validateDate(date);
        this.studentAttendance = new HashMap<>();
    }

    private LocalDate validateDate(String d) {
        if (d == null || d.trim().isEmpty()) {
            throw new IllegalArgumentException("Date cannot be empty");
        }
        try {
            return LocalDate.parse(d.trim());
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format (YYYY-MM-DD)");
        }
    }

    public void markAttendance(String rollNo, String status) {
        String r = validateRoll(rollNo);
        String s = validateStatus(status);

        if (studentAttendance.containsKey(r)) {
            throw new IllegalStateException("Already marked: " + r);
        }

        studentAttendance.put(r, s);
    }

    private String validateRoll(String r) {
        if (r == null || !r.matches("\\d+")) {
            throw new IllegalArgumentException("Invalid roll number");
        }
        return r;
    }

    private String validateStatus(String s) {
        if (s == null) throw new IllegalArgumentException("Status null");

        s = s.trim().toUpperCase();
        if (!s.equals("P") && !s.equals("A")) {
            throw new IllegalArgumentException("Use P or A only");
        }
        return s;
    }

    public Map<String, String> getStudentAttendance() {
        return Collections.unmodifiableMap(studentAttendance);
    }

    public String getDate() {
        return date.toString();
    }
}