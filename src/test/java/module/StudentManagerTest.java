package module;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Student;

public class StudentManagerTest {

    private StudentsManager sm;

    @BeforeEach
    void setUp() {
        sm = new StudentsManager();
    }

    //  1. Add valid student
    @Test
    void testAddStudent_Valid() {
        sm.addStudent("101", "Sejal", "TY");

        List<Student> list = sm.getStudents();
        assertEquals(1, list.size());
        assertEquals("101", list.get(0).getRollNo());
    }

    //  2. Duplicate roll number
    @Test
    void testAddStudent_Duplicate() {
        sm.addStudent("101", "Sejal", "TY");

        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            sm.addStudent("101", "Other", "SY");
        });

        assertTrue(ex.getMessage().contains("already exists"));
    }

    //  3. Null roll number
    @Test
    void testAddStudent_NullRollNo() {
        assertThrows(Exception.class, () -> {
            sm.addStudent(null, "Sejal", "TY");
        });
    }

    //  4. Empty roll number
    @Test
    void testAddStudent_EmptyRollNo() {
        assertThrows(Exception.class, () -> {
            sm.addStudent("", "Sejal", "TY");
        });
    }

    //  5. Null name
    @Test
    void testAddStudent_NullName() {
        assertThrows(Exception.class, () -> {
            sm.addStudent("101", null, "TY");
        });
    }

    //  6. Empty name
    @Test
    void testAddStudent_EmptyName() {
        assertThrows(Exception.class, () -> {
            sm.addStudent("101", "", "TY");
        });
    }

    

    //  9. Multiple students
    @Test
    void testAddMultipleStudents() {
        sm.addStudent("101", "A", "TY");
        sm.addStudent("102", "B", "SY");

        assertEquals(2, sm.getStudents().size());
    }

    //  10. getStudents returns unmodifiable list
    @Test
    void testGetStudents_Unmodifiable() {
        sm.addStudent("101", "Sejal", "TY");

        List<Student> list = sm.getStudents();

        assertThrows(UnsupportedOperationException.class, () -> {
            list.add(new Student("102", "X", "SY"));
        });
    }

    //  11. getStudents returns empty list initially
    @Test
    void testGetStudents_Empty() {
        List<Student> list = sm.getStudents();
        assertTrue(list.isEmpty());
    }

    //  12. displayStudents when empty (no crash)
    @Test
    void testDisplayStudents_Empty() {
        assertDoesNotThrow(() -> sm.displayStudents());
    }

    //  13. displayStudents with data (no crash)
    @Test
    void testDisplayStudents_WithData() {
        sm.addStudent("101", "Sejal", "TY");
        sm.addStudent("102", "A", "SY");

        assertDoesNotThrow(() -> sm.displayStudents());
    }

    //  14. Trim handling in duplicate (if Student trims internally)
    @Test
    void testAddStudent_TrimDuplicate() {
        sm.addStudent("101", "Sejal", "TY");

        assertThrows(IllegalArgumentException.class, () -> {
            sm.addStudent(" 101 ", "Other", "SY");
        });
    }
}
