// package model;

// public class Student {
//     private String rollNo;
//     private String name;
//     private String className;

//     public Student(String rollNo, String name, String className) {
//         this.rollNo = rollNo;
//         this.name = name;
//         this.className = className;
//     }

//     public String getRollNo() {
//         return rollNo;
//     }

//     public String getName() {
//         return name;
//     }

//     public String getClassName() {
//         return className;
//     }

//     @Override
//     public String toString() {
//         return rollNo + " | " + name + " | " + className;
//     }
// }


package model;

import java.util.Objects;

public class Student {

    private final String rollNo;
    private final String name;
    private final String className;

    public Student(String rollNo, String name, String className) {
        this.rollNo = validateRollNo(rollNo);
        this.name = validateName(name);
        this.className = validateClassName(className);
    }

    private String validateRollNo(String r) {
        if (r == null || r.trim().isEmpty()) {
            throw new IllegalArgumentException("Roll number cannot be null or empty");
        }

        r = r.trim();

        // Example rule: ONLY digits allowed
        if (!r.matches("\\d+")) {
            throw new IllegalArgumentException("Roll number must contain only digits");
        }

        return r;
    }

    private String validateName(String n) {
        if (n == null || n.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        n = n.trim();

        // No numbers allowed in name
        if (!n.matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException("Name must contain only alphabets and spaces");
        }

        return n;
    }

    private String validateClassName(String c) {
        if (c == null || c.trim().isEmpty()) {
            return "N/A";
        }

        c = c.trim();

        // Optional: allow alphanumeric class names
        if (!c.matches("[a-zA-Z0-9 ]+")) {
            throw new IllegalArgumentException("Class name must be alphanumeric");
        }

        return c;
    }

    public String getRollNo() {
        return rollNo;
    }

    public String getName() {
        return name;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public String toString() {
        return rollNo + " | " + name + " | " + className;
    }

    // Important for Set duplicate handling
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student s = (Student) o;
        return rollNo.equals(s.rollNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rollNo);
    }
}