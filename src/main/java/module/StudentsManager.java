
package module;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.Student;

public class StudentsManager {

    private final Set<Student> students = new HashSet<>();

    public void addStudent(String rollNo, String name, String className) {

        // validation handled inside Student class
        Student s = new Student(rollNo, name, className);

        // duplicate check
        if (!students.add(s)) {
            throw new IllegalArgumentException(
                    "Student with roll number already exists: " + rollNo.trim());
        }
    }

    public List<Student> getStudents() {
        return Collections.unmodifiableList(new ArrayList<>(students));
    }

    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        System.out.println("Roll No | Name | Class");
        students.stream()
                .sorted(Comparator.comparing(Student::getRollNo))
                .forEach(System.out::println);
    }
}




// package module;

// import java.util.ArrayList;
// import java.util.Collections;
// import java.util.Comparator;
// import java.util.HashSet;
// import java.util.List;
// import java.util.Set;

// import model.Student;

// public class StudentsManager {

//     private final Set<Student> students = new HashSet<>();

//     public void addStudent(String rollNo, String name, String className) {
//         try {
//             Student s = new Student(rollNo, name, className);

//             if (!students.add(s)) {
//                 throw new IllegalArgumentException(
//                         "Student with roll number already exists: " + rollNo);
//             }

//             System.out.println("Student added successfully.");

//         } catch (IllegalArgumentException e) {
//             System.out.println("Error: " + e.getMessage());
//         } catch (Exception e) {
//             System.out.println("Unexpected error occurred.");
//         }
//     }

//     public List<Student> getStudents() {
//         return Collections.unmodifiableList(new ArrayList<>(students));
//     }

//     public void displayStudents() {
//         if (students.isEmpty()) {
//             System.out.println("No students available.");
//             return;
//         }

//         System.out.println("Roll No | Name | Class");
//         students.stream()
//                 .sorted(Comparator.comparing(Student::getRollNo))
//                 .forEach(System.out::println);
//     }
// }


