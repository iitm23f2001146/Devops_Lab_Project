package module;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.RepeatedTest;

import java.time.LocalDate;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class AttendanceManagerTest {

    @BeforeEach
    void clearData() {
        AttendanceManager.getAttendanceData().clear();
    }

    @Test
    void testAttendanceStorage() {
        String today = LocalDate.now().toString();

        Attendance attendance = new Attendance(today);
        attendance.markAttendance("201", "P");

        AttendanceManager.getAttendanceData().put(today, attendance);

        HashMap<String, Attendance> data =
                AttendanceManager.getAttendanceData();

        assertNotNull(data);
        assertTrue(data.containsKey(today));
        assertEquals("P",
                data.get(today).getStudentAttendance().get("201"));
        assertNotEquals("A",
                data.get(today).getStudentAttendance().get("201"));
    }

    @RepeatedTest(2)
    void testAttendanceMapNotEmpty() {
        String today = LocalDate.now().toString();

        Attendance attendance = new Attendance(today);
        AttendanceManager.getAttendanceData().put(today, attendance);

        assertFalse(AttendanceManager.getAttendanceData().isEmpty());
    }
}
