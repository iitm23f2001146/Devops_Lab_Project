package module;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IntegrationTest {

    private StudentsManager sm;
    private AttendanceManager am;

    private ByteArrayOutputStream out;

    @BeforeEach
    void setUp() {
        sm = new StudentsManager();

        // capture console
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        // clean file before test
        File file = new File("attendance.csv");
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testFullFlow_AddStudent_MarkAttendance_View() {

        // ---------- ADD STUDENT ----------
        sm.addStudent("101", "Sejal", "CS");

        assertEquals(1, sm.getStudents().size());

        // ---------- MARK ATTENDANCE ----------
        // input: P
        Scanner input = new Scanner("P\n");
        am = new AttendanceManager(sm, input);

        am.markAttendance();

        String output1 = out.toString();
        assertTrue(output1.contains("Attendance saved successfully"));

        // ---------- VIEW ATTENDANCE ----------
        out.reset();

        // input roll
        Scanner viewInput = new Scanner("101\n");
        am = new AttendanceManager(sm, viewInput);

        am.viewAttendance();

        String output2 = out.toString();

        assertTrue(output2.contains("-> P")); // attendance retrieved
    }

    @Test
    void testMultipleStudentsIntegration() {

        sm.addStudent("101", "A", "CS");
        sm.addStudent("102", "B", "IT");

        // P for first, A for second
        Scanner input = new Scanner("P\nA\n");
        am = new AttendanceManager(sm, input);

        am.markAttendance();

        assertTrue(out.toString().contains("Attendance saved successfully"));

        // view one student
        out.reset();

        Scanner viewInput = new Scanner("102\n");
        am = new AttendanceManager(sm, viewInput);

        am.viewAttendance();

        assertTrue(out.toString().contains("-> A"));
    }

    @Test
    void testIntegration_NoStudents() {

        am = new AttendanceManager(sm, new Scanner(""));

        am.markAttendance();

        assertTrue(out.toString().contains("No students available"));
    }

    @Test
    void testIntegration_InvalidInputRecovery() {

        sm.addStudent("101", "A", "CS");

        // invalid then valid
        Scanner input = new Scanner("X\nP\n");
        am = new AttendanceManager(sm, input);

        am.markAttendance();

        String output = out.toString();

        assertTrue(output.contains("Invalid status"));
        assertTrue(output.contains("Attendance saved successfully"));
    }

    @Test
    void testIntegration_FilePersistence() throws IOException {

        sm.addStudent("101", "A", "CS");

        Scanner input = new Scanner("P\n");
        am = new AttendanceManager(sm, input);

        am.markAttendance();

        // verify file exists
        File file = new File("attendance.csv");
        assertTrue(file.exists());

        // verify file content
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();

        assertNotNull(line);
        assertTrue(line.contains("101"));
        assertTrue(line.contains("P"));

        br.close();
    }
}

// not much usefull
// package module;

// import java.util.Map;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertTrue;
// import org.junit.jupiter.api.Test;

// class IntegrationTest {

// @Test
// void testStudentAndAttendanceIntegration() {

// AttendanceManager am = new AttendanceManager();

// // Step 1: Add students
// am.addStudent(101, "Sejal");
// am.addStudent(102, "Amit");

// // Step 2: Mark attendance
// String date = "2026-04-21";
// am.markAttendanceRecord(date, 101, "P");
// am.markAttendanceRecord(date, 102, "A");

// // Step 3: Validate student data
// assertEquals(2, am.getStudentsMap().size());

// // Step 4: Validate attendance data
// Map<String, Map<Integer, String>> attendance = am.getAttendanceMap();

// assertTrue(attendance.containsKey(date));
// assertEquals("P", attendance.get(date).get(101));
// assertEquals("A", attendance.get(date).get(102));
// }

// @Test
// void testAttendanceStorageIntegration() {

// AttendanceManager am = new AttendanceManager();

// String date = "2026-04-22";

// // Mark attendance
// am.markAttendanceRecord(date, 201, "P");

// // Validate internal attendance storage
// assertTrue(AttendanceManager.getAttendanceData().containsKey(date));

// Attendance attendance = AttendanceManager.getAttendanceData().get(date);

// assertEquals("P", attendance.getStudentAttendance().get("201"));
// }

// @Test
// void testMultipleDaysIntegration() {

// AttendanceManager am = new AttendanceManager();

// // Day 1
// am.markAttendanceRecord("2026-04-20", 1, "P");

// // Day 2
// am.markAttendanceRecord("2026-04-21", 1, "A");

// Map<String, Map<Integer, String>> map = am.getAttendanceMap();

// assertEquals("P", map.get("2026-04-20").get(1));
// assertEquals("A", map.get("2026-04-21").get(1));
// }
// }