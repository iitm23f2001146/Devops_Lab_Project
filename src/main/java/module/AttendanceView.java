package module;

import java.util.List;

public class AttendanceView {

    // ---------- CORE LOGIC (TESTABLE) ----------

    public static String viewByRoll(List<String[]> data, String roll) {

        if (roll == null || !roll.matches("\\d+")) {
            throw new IllegalArgumentException("Invalid roll number");
        }

        StringBuilder result = new StringBuilder();

        for (String[] row : data) {
            if (row.length >= 3 && row[1].equals(roll)) {
                result.append(row[0]).append(" -> ").append(row[2]).append("\n");
            }
        }

        if (result.length() == 0) {
            return "No record found";
        }

        return result.toString().trim();
    }

    public static String viewByDate(List<String[]> data, String date) {

        if (date == null || !date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new IllegalArgumentException("Invalid date format");
        }

        StringBuilder result = new StringBuilder();

        for (String[] row : data) {
            if (row.length >= 3 && row[0].equals(date)) {
                result.append(row[1]).append(" -> ").append(row[2]).append("\n");
            }
        }

        if (result.length() == 0) {
            return "No record found";
        }

        return result.toString().trim();
    }
}




// package module;

// import java.util.List;
// import java.util.Scanner;

// import util.FileUtil;

// public class AttendanceView {

//     public static void main(String[] args) {

//         Scanner sc = new Scanner(System.in);

//         while (true) {
//             System.out.println("\n1. View by Roll");
//             System.out.println("2. View by Date");
//             System.out.println("3. Exit");

//             int ch = Integer.parseInt(sc.nextLine());

//             if (ch == 3) break;

//             List<String[]> data = FileUtil.readAll();

//             switch (ch) {

//                 case 1:
//                     System.out.print("Roll: ");
//                     String r = sc.nextLine();

//                     data.stream()
//                             .filter(x -> x[1].equals(r))
//                             .forEach(x -> System.out.println(x[0] + " -> " + x[2]));
//                     break;

//                 case 2:
//                     System.out.print("Date: ");
//                     String d = sc.nextLine();

//                     data.stream()
//                             .filter(x -> x[0].equals(d))
//                             .forEach(x -> System.out.println(x[1] + " -> " + x[2]));
//                     break;
//             }
//         }
//     }
// }

