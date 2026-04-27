package module;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AttendanceViewTest {

    private List<String[]> data;

    @BeforeEach
    void setUp() {
        data = new ArrayList<>();

        // date, roll, status
        data.add(new String[]{"2026-01-01", "101", "P"});
        data.add(new String[]{"2026-01-01", "102", "A"});
        data.add(new String[]{"2026-01-02", "101", "A"});
    }

    // ---------- BASIC ----------
    @Test
    void testDataNotNull() {
        assertNotNull(data);
    }

    // ---------- VIEW BY ROLL ----------
    @Test
    void testViewByRollSuccess() {
        String result = AttendanceView.viewByRoll(data, "101");

        assertTrue(result.contains("2026-01-01 -> P"));
        assertTrue(result.contains("2026-01-02 -> A"));
    }

    @Test
    void testViewByRollNoRecord() {
        String result = AttendanceView.viewByRoll(data, "999");

        assertEquals("No record found", result);
    }

    @Test
    void testViewByRollInvalidInput() {
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> AttendanceView.viewByRoll(data, "ABC"));

        assertEquals("Invalid roll number", e.getMessage());
    }

    @Test
    void testViewByRollNull() {
        assertThrows(IllegalArgumentException.class,
                () -> AttendanceView.viewByRoll(data, null));
    }

    // ---------- VIEW BY DATE ----------
    @Test
    void testViewByDateSuccess() {
        String result = AttendanceView.viewByDate(data, "2026-01-01");

        assertTrue(result.contains("101 -> P"));
        assertTrue(result.contains("102 -> A"));
    }

    @Test
    void testViewByDateNoRecord() {
        String result = AttendanceView.viewByDate(data, "2026-02-01");

        assertEquals("No record found", result);
    }

    @Test
    void testViewByDateInvalidFormat() {
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> AttendanceView.viewByDate(data, "01-01-2026"));

        assertEquals("Invalid date format", e.getMessage());
    }

    @Test
    void testViewByDateNull() {
        assertThrows(IllegalArgumentException.class,
                () -> AttendanceView.viewByDate(data, null));
    }

    // ---------- EDGE CASES ----------
    @Test
    void testEmptyData() {
        List<String[]> empty = new ArrayList<>();

        String result = AttendanceView.viewByRoll(empty, "101");

        assertEquals("No record found", result);
    }

    @Test
    void testMalformedRowIgnored() {
        data.add(new String[]{"bad", "row"}); // invalid row

        String result = AttendanceView.viewByRoll(data, "101");

        assertTrue(result.contains("2026-01-01"));
    }
}