import java.util.Scanner;

public class Program {
    private static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static int sumOfDigits(int n) {
        int sum = 0;
        while (n != 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

    private static int countCoffeeQueries() {
        int count = 0;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (!scanner.hasNextInt())  {
                System.err.println("Illegal Argument");
                System.exit(1);
            }

            int query = scanner.nextInt();
            if (query == 42) {
                break;
            }
            if (isPrime(sumOfDigits(query))) {
                count++;
            }
        }
        scanner.close();
        return count;
    }

    public static void main(String[] args) {
        int coffeeCount = countCoffeeQueries();
        System.out.println("Count of coffee-request : " + coffeeCount);
    }
}
