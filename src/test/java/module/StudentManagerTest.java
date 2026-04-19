

package module;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentManagerTest {

    private StudentManagerLegacy sm;

    @BeforeEach
    void setUp() {
        sm = new StudentManagerLegacy();
    }

    @Test
    void testCreate() {
        assertNotNull(sm);
    }

    @Test
    void testAddStudentSuccess() {
        sm.addStudent("101", "Sejal", "CS");
        assertEquals(1, sm.getStudents().size());
    }

    @Test
    void testEmptyNameThrows() {
        assertThrows(IllegalArgumentException.class, () -> sm.addStudent("102", "", "CS"));
    }

    @Test
    void testEmptyRollThrows() {
        assertThrows(IllegalArgumentException.class, () -> sm.addStudent("", "Test", "CS"));
    }

    @Test
    void testNullNameThrows() {
        assertThrows(IllegalArgumentException.class, () -> sm.addStudent("103", null, "CS"));
    }

    @Test
    void testNullRollThrows() {
        assertThrows(IllegalArgumentException.class, () -> sm.addStudent(null, "Test", "CS"));
    }

    @Test
    void testDuplicateRollThrows() {
        sm.addStudent("200", "A", "IT");

        assertThrows(IllegalArgumentException.class, () -> sm.addStudent("200", "B", "IT"));
    }

    @Test
    void testGetStudentsUnmodifiable() {
        sm.addStudent("300", "Test", "CS");

        assertThrows(UnsupportedOperationException.class, () -> sm.getStudents().add(null));
    }

    @Test
    void testDuplicateRollWithSpacesShouldFail() {
        sm.addStudent("101", "A", "CS");

        // logically same roll but with spaces
        assertThrows(IllegalArgumentException.class, () -> sm.addStudent(" 101 ", "B", "CS"));
    }
}