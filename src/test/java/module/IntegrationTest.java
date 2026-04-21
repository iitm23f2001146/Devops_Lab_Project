package module;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class IntegrationTest {

    @Test
    void testStudentAndAttendanceIntegration() {

        AttendanceManager am = new AttendanceManager();

        // Step 1: Add students
        am.addStudent(101, "Sejal");
        am.addStudent(102, "Amit");

        // Step 2: Mark attendance
        String date = "2026-04-21";
        am.markAttendanceRecord(date, 101, "P");
        am.markAttendanceRecord(date, 102, "A");

        // Step 3: Validate student data
        assertEquals(2, am.getStudentsMap().size());

        // Step 4: Validate attendance data
        Map<String, Map<Integer, String>> attendance = am.getAttendanceMap();

        assertTrue(attendance.containsKey(date));
        assertEquals("P", attendance.get(date).get(101));
        assertEquals("A", attendance.get(date).get(102));
    }


    @Test
    void testAttendanceStorageIntegration() {

        AttendanceManager am = new AttendanceManager();

        String date = "2026-04-22";

        // Mark attendance
        am.markAttendanceRecord(date, 201, "P");

        // Validate internal attendance storage
        assertTrue(AttendanceManager.getAttendanceData().containsKey(date));

        Attendance attendance = AttendanceManager.getAttendanceData().get(date);

        assertEquals("P", attendance.getStudentAttendance().get("201"));
    }


    @Test
    void testMultipleDaysIntegration() {

        AttendanceManager am = new AttendanceManager();

        // Day 1
        am.markAttendanceRecord("2026-04-20", 1, "P");

        // Day 2
        am.markAttendanceRecord("2026-04-21", 1, "A");

        Map<String, Map<Integer, String>> map = am.getAttendanceMap();

        assertEquals("P", map.get("2026-04-20").get(1));
        assertEquals("A", map.get("2026-04-21").get(1));
    }
}