package module;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AttendanceManagerTest {

    private StudentsManager sm;
    private ByteArrayOutputStream out;

    @BeforeEach
    void setUp() {
        sm = new StudentsManager();

        // capture console output
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        // clean file before each test
        File file = new File("attendance.csv");
        if (file.exists())
            file.delete();
    }

    // ---------- BASIC ----------
    @Test
    void testObjectCreation() {
        AttendanceManager am = new AttendanceManager(sm, new Scanner(""));
        assertNotNull(am);
    }

    // ---------- NO STUDENTS ----------
    @Test
    void testMarkAttendance_NoStudents() {

        AttendanceManager am = new AttendanceManager(sm, new Scanner(""));

        am.markAttendance();

        assertTrue(out.toString().contains("No students available"));
    }

    // ---------- SUCCESS ----------
    @Test
    void testMarkAttendance_Success() {

        sm.addStudent("101", "A", "CS");

        Scanner input = new Scanner("P\n");
        AttendanceManager am = new AttendanceManager(sm, input);

        am.markAttendance();

        String output = out.toString();

        assertTrue(output.contains("Marking attendance"));
        assertTrue(output.contains("Attendance saved successfully"));
    }

    // ---------- INVALID STATUS ----------
    @Test
    void testInvalidStatusThenValid() {

        sm.addStudent("101", "A", "CS");

        Scanner input = new Scanner("X\nP\n");
        AttendanceManager am = new AttendanceManager(sm, input);

        am.markAttendance();

        String output = out.toString();

        assertTrue(output.contains("Invalid status"));
        assertTrue(output.contains("Attendance saved successfully"));
    }

    // ---------- MULTIPLE STUDENTS ----------
    @Test
    void testMultipleStudentsAttendance() {

        sm.addStudent("101", "A", "CS");
        sm.addStudent("102", "B", "IT");

        Scanner input = new Scanner("P\nA\n");
        AttendanceManager am = new AttendanceManager(sm, input);

        am.markAttendance();

        assertTrue(out.toString().contains("Attendance saved successfully"));
    }

    // ---------- VIEW NO DATA ----------
    @Test
    void testViewAttendance_NoData() {

        sm.addStudent("101", "A", "CS");

        Scanner input = new Scanner("101\n");
        AttendanceManager am = new AttendanceManager(sm, input);

        am.viewAttendance();

        assertTrue(out.toString().contains("No attendance data found"));
    }

    // ---------- VIEW SUCCESS ----------
    @Test
    void testViewAttendance_Success() {

        sm.addStudent("101", "A", "CS");

        // first mark attendance
        AttendanceManager am1 = new AttendanceManager(sm, new Scanner("P\n"));
        am1.markAttendance();

        out.reset();

        // now view
        AttendanceManager am2 = new AttendanceManager(sm, new Scanner("101\n"));
        am2.viewAttendance();

        String output = out.toString();

        assertTrue(output.contains("-> P"));
    }

    // ---------- INVALID ROLL ----------
    @Test
    void testInvalidRollInput() {

        sm.addStudent("101", "A", "CS");

        Scanner input = new Scanner("ABC\n101\n");
        AttendanceManager am = new AttendanceManager(sm, input);

        am.viewAttendance();

        String output = out.toString();

        assertTrue(output.contains("Invalid roll number"));
    }

    // ---------- STUDENT NOT FOUND ----------
    @Test
    void testStudentNotFound() {

        sm.addStudent("101", "A", "CS");

        Scanner input = new Scanner("999\n101\n");
        AttendanceManager am = new AttendanceManager(sm, input);

        am.viewAttendance();

        String output = out.toString();

        assertTrue(output.contains("Student not found"));
    }

    // ---------- NO RECORD FOUND ----------
    @Test
    void testNoRecordFound() {

        sm.addStudent("101", "A", "CS");

        // no attendance marked yet
        Scanner input = new Scanner("101\n");
        AttendanceManager am = new AttendanceManager(sm, input);

        am.viewAttendance();

        assertTrue(out.toString().contains("No attendance data found"));
    }

    // ---------- FILE CREATED ----------
    @Test
    void testFileCreatedAfterMarking() {

        sm.addStudent("101", "A", "CS");

        AttendanceManager am = new AttendanceManager(sm, new Scanner("P\n"));
        am.markAttendance();

        File file = new File("attendance.csv");

        assertTrue(file.exists());
    }

    // ---------- EDGE ----------
    @Test
    void testMultipleInvalidStatusAttempts() {

        sm.addStudent("101", "A", "CS");

        Scanner input = new Scanner("X\nY\nZ\nP\n");
        AttendanceManager am = new AttendanceManager(sm, input);

        am.markAttendance();

        String output = out.toString();

        assertTrue(output.contains("Invalid status"));
        assertTrue(output.contains("Attendance saved successfully"));
    }
}