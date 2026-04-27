// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import module.AttendanceManager;

// import org.junit.jupiter.api.RepeatedTest;
// import org.junit.jupiter.api.RepetitionInfo;
// import static org.junit.jupiter.api.Assertions.*;
// import java.util.Map;

// public class MainTest {

//     private AttendanceManager attendanceManager;

//     @BeforeEach
//     public void setUp() {
//         // Initialize a new AttendanceManager before each test
//         attendanceManager = new AttendanceManager();
//     }

//     @Test
//     public void testAddStudent() {
//         // Test adding a student
//         attendanceManager.addStudent(101, "John Doe");
        
//         Map<Integer, String> students = attendanceManager.getStudentsMap();
        
//         // Using @assertEquals
//         assertEquals("John Doe", students.get(101), "Student name should match");
//         assertEquals(1, students.size(), "Should have exactly one student");
//     }

//     @Test
//     public void testAddMultipleStudents() {
//         // Test adding multiple students
//         attendanceManager.addStudent(101, "John Doe");
//         attendanceManager.addStudent(102, "Jane Smith");
//         attendanceManager.addStudent(103, "Bob Johnson");
        
//         Map<Integer, String> students = attendanceManager.getStudentsMap();
        
//         assertEquals(3, students.size(), "Should have three students");
//         assertEquals("John Doe", students.get(101));
//         assertEquals("Jane Smith", students.get(102));
//         assertEquals("Bob Johnson", students.get(103));
//     }

//     @Test
//     public void testStudentNotNull() {
//         // Test that student data is not null
//         attendanceManager.addStudent(101, "John Doe");
        
//         Map<Integer, String> students = attendanceManager.getStudentsMap();
//         String studentName = students.get(101);
        
//         // Using @assertNotNull
//         assertNotNull(studentName, "Student name should not be null");
//         assertNotNull(students, "Students map should not be null");
//     }

//     @Test
//     public void testStudentNotInList() {
//         // Test that a student is not in the list
//         attendanceManager.addStudent(101, "John Doe");
        
//         Map<Integer, String> students = attendanceManager.getStudentsMap();
        
//         // Using @assertNotEquals
//         assertNotEquals("Jane Smith", students.get(101), "Student name should not be Jane Smith");
//         assertNotEquals(5, students.size(), "Should not have 5 students");
//         assertFalse(students.containsKey(999), "Roll number 999 should not exist");
//     }

//     @Test
//     public void testStudentExists() {
//         // Test that a student exists in the list
//         attendanceManager.addStudent(101, "John Doe");
        
//         Map<Integer, String> students = attendanceManager.getStudentsMap();
        
//         // Using @assertTrue
//         assertTrue(students.containsKey(101), "Student with roll 101 should exist");
//         assertTrue(students.size() > 0, "Students map should not be empty");
//     }

//     @Test
//     public void testStudentDoesNotExist() {
//         // Test that a student does not exist in the list
//         attendanceManager.addStudent(101, "John Doe");
        
//         Map<Integer, String> students = attendanceManager.getStudentsMap();
        
//         // Using @assertFalse
//         assertFalse(students.containsKey(999), "Student with roll 999 should not exist");
//         assertFalse(students.isEmpty(), "Students map should not be empty after adding");
//     }

//     @RepeatedTest(3)
//     public void testAddStudentRepeated(RepetitionInfo repetitionInfo) {
//         // Test adding students repeatedly (runs 3 times)
//         int repetition = repetitionInfo.getCurrentRepetition();
//         int rollNo = 100 + repetition;
        
//         attendanceManager.addStudent(rollNo, "Student " + repetition);
        
//         Map<Integer, String> students = attendanceManager.getStudentsMap();
//         assertEquals(1, students.size(), "Should have one student in this repetition");
//         assertTrue(students.containsKey(rollNo), "Student should be added in repetition " + repetition);
//     }

//     @Test
//     public void testEmptyAttendanceMap() {
//         // Test that attendance map is initially empty
//         Map<String, Map<Integer, String>> attendance = attendanceManager.getAttendanceMap();
        
//         assertNotNull(attendance, "Attendance map should not be null");
//         assertTrue(attendance.isEmpty(), "Attendance map should be empty initially");
//     }

//     @Test
//     public void testStudentNameEquality() {
//         // Test student name equality
//         attendanceManager.addStudent(101, "John Doe");
        
//         String expectedName = "John Doe";
//         String actualName = attendanceManager.getStudentsMap().get(101);
        
//         assertEquals(expectedName, actualName, "Names should be equal");
//         assertNotEquals("john doe", actualName, "Names should be case-sensitive");
//     }

//     @Test
//     public void testStudentRollNumberComparison() {
//         // Test student roll number comparisons
//         attendanceManager.addStudent(101, "John Doe");
//         attendanceManager.addStudent(102, "Jane Smith");
        
//         Map<Integer, String> students = attendanceManager.getStudentsMap();
        
//         assertEquals(101, 101, "Roll number 101 should equal 101");
//         assertNotEquals(101, 102, "Roll number 101 should not equal 102");
//         assertTrue(students.containsKey(101));
//         assertFalse(students.containsKey(103));
//     }
// }
