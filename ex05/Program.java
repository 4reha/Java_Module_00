import java.util.Scanner;

public class Program {

    private static String[][][] Schedule = new String[10][30][6];
    private static String[] students = new String[10];
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Creating a list of students
        createStudentsList(scanner);

        // Populating the timetable
        populateTimetable(scanner);

        // Recording attendance
//        String[][] attendance = recordAttendance(scanner, students, timetable);
//
//        // Displaying the timetable with attendance
//        displayTimetableWithAttendance(students, timetable, attendance);
    }

    private static void createStudentsList(Scanner scanner) {
        System.out.println("Creating a list of students (enter '.' to finish):");
        int count = -1;

        while (++count < 10) {
            String student = scanner.nextLine();
            if (student.equals(".")) {
                break;
            }
            if(student.length() > 10 && hasSpaces(student)) {
                System.err.println("Illegal Argument");
                System.exit(-1);
            }
            students[count] = student;
        }

    }

    private static void populateTimetable(Scanner scanner) {
        System.out.println("Populating the timetable (enter '.' to finish):");
        boolean[][] timetable = new boolean[6][7];

        while (true)    {
            if (!scanner.hasNextInt())  {
                System.err.println("Illegal Argument");
                System.exit(-1);
            }else {
                int time = scanner.nextInt();
                if (time > 1 && time < 6) {
                    System.err.println("Illegal Argument");
                    System.exit(-1);
                    break;
                }
                String day = scanner.next();
                if (timetable[time][getDayIndex(day)]) {
                    System.err.println("Illegal Argument");
                    System.exit(-1);
                }
                timetable[time][getDayIndex(day)] = true;
                scanner.nextLine();
            }
        }



//        return timetable;
    }

    private static String[][] recordAttendance(Scanner scanner, String[] students, String[][] timetable) {
        System.out.println("Recording attendance (enter '.' to finish):");
        String[][] attendance = new String[students.length][timetable.length * timetable[0].length];

        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equals(".")) {
                break;
            }

            String[] parts = input.split(" ");
            String studentName = parts[0];
            int time = Integer.parseInt(parts[1]);
            int day = Integer.parseInt(parts[2]);
            String attendanceStatus = parts[3];

            int studentIndex = findStudentIndex(students, studentName);
            int timetableIndex = time * timetable[0].length + day;

            attendance[studentIndex][timetableIndex] = attendanceStatus;
        }

        return attendance;
    }

    private static void displayTimetableWithAttendance(String[] students, String[][] timetable, String[][] attendance) {
        System.out.println("Timetable with attendance:");
        System.out.print("       ");
        for (int i = 1; i <= timetable[0].length; i++) {
            System.out.printf("%-9s", getDayName(i));
        }
        System.out.println();

        for (int time = 0; time < timetable.length; time++) {
            int hour = time + 1;
            System.out.printf("%-7s", hour + ":00");
            for (int day = 0; day < timetable[time].length; day++) {
                String status = "";
                if (timetable[time][day] != null) {
                    int timetableIndex = time * timetable[0].length + day;
                    for (int studentIndex = 0; studentIndex < students.length; studentIndex++) {
                        if (attendance[studentIndex][timetableIndex] != null) {
                            status += students[studentIndex] + " " + attendance[studentIndex][timetableIndex] + "|";
                        }
                    }
                }
                System.out.printf("%-9s", status);
            }
            System.out.println();
        }
    }

    private static int getDayIndex(String day) {
        return switch (day) {
            case "SU" -> 0;
            case "MO" -> 1;
            case "TU" -> 2;
            case "WE" -> 3;
            case "TH" -> 4;
            case "FR" -> 5;
            case "SA" -> 6;
            default -> -1;
        };
    }

    private static String getDayName(int index) {
        switch (index) {
            case 1:
                return "MO";
            case 2:
                return "TU";
            case 3:
                return "WE";
            case 4:
                return "TH";
            case 5:
                return "FR";
            default:
                return "";
        }
    }

    private static int findStudentIndex(String[] students, String studentName) {
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null && students[i].equals(studentName)) {
                return i;
            }
        }
        return -1;
    }

    private static boolean hasSpaces(String student) {
        char [] studentArray = student.toCharArray();
        for (char c : studentArray) {
            if (c == ' ') {
                return true;
            }
        }
        return false;
    }

}
