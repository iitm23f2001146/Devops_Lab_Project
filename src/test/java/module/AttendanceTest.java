package module;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class AttendanceTest {

    private Attendance attendance;

    @BeforeEach
    void setUp() {
        attendance = new Attendance("2026-02-12");
    }

    @Test
    void testDateInitialization() {
        assertEquals("2026-02-12", attendance.getDate());
        assertNotEquals("2025-01-01", attendance.getDate());
    }

    @Test
    void testMarkAttendance() {
        attendance.markAttendance("101", "P");

        HashMap<String, String> data = attendance.getStudentAttendance();

        assertNotNull(data);
        assertTrue(data.containsKey("101"));
        assertEquals("P", data.get("101"));
        assertFalse(data.get("101").equals("A"));
    }

    @RepeatedTest(3)
    void testMultipleEntries() {
        attendance.markAttendance("102", "A");

        assertTrue(attendance.getStudentAttendance().containsKey("102"));
        assertEquals("A", attendance.getStudentAttendance().get("102"));
    }
}
