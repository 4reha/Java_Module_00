import java.util.Scanner;

public class Program {

    private static int getTestMinGrade(Scanner scanner) {
        int minGrade = 9;
        for (int i = 0; i < 5; i++) {
            int grade = scanner.nextInt();
            if (grade < 1 || grade > 9) {
                System.err.println("IllegalArgument");
                System.exit(-1);
            }
            minGrade = grade <= minGrade ? grade : minGrade;
        }
        scanner.nextLine();
        return minGrade;
    }

    private static long collectWeekGrades(int currentWeek[]) {
        long sumOfGrades = 0;
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        try{
            while (currentWeek[0] < 18 && !input.equals("42")) {
                if (input.equals("week " + (currentWeek[0] + 1))) {
                    sumOfGrades = sumOfGrades * 10 + getTestMinGrade(scanner);
                    currentWeek[0]++;
                    input = scanner.nextLine();
                } else {
                    System.err.println("IllegalArgument");
                    System.exit(-1);
                }
            }
            scanner.close();
        } catch (Exception e){
            System.err.println(e.toString());
            System.exit(-1);
        }
        return sumOfGrades;
    }

    public static boolean isValidInput(String input) {
        return true;
    }

    private static void printGradesGraph(long grades, int weeks) {
        int week = 1;
        while (weeks > 0) {
            System.out.print("Week " + week++ + " ");
            int grade = (int) (grades / Math.pow(10, weeks-1));
            for (int i = 0; i < grade; i++) {
                System.out.print("=");
            }
            System.out.println(">");
            grades %= Math.pow(10, weeks-1);
            weeks--;
        }
    }

    public static void main(String[] args) {
        int weeks[] = {0};
        long grades = collectWeekGrades(weeks);
        printGradesGraph(grades, weeks[0]);
    }
}