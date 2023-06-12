import java.util.Scanner;

public class Program {
    private static final int MAX_CHARS = 65535;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        short[] charCount = getCharCount(input);
        getTopTen(charCount);


    }

    private static void getTopTen(short[] charCount) {
        short[] topTenCount = new short[10];
        char[] topTenChars = new char[10];
        for (int i = 0; i < MAX_CHARS; i++) {
            if (charCount[i] > topTenCount[9]) {
                topTenCount[9] = charCount[i];
                topTenChars[9] = (char) i;
                for (int j = 8; j >= 0; j--) {
                    if (topTenCount[j] < topTenCount[j + 1]) {
                        short tempCount = topTenCount[j];
                        topTenCount[j] = topTenCount[j + 1];
                        topTenCount[j + 1] = tempCount;
                        char tempChar = topTenChars[j];
                        topTenChars[j] = topTenChars[j + 1];
                        topTenChars[j + 1] = tempChar;
                    }
                }
            }
        }
        printTopTen(topTenChars, topTenCount);
    }

    private static void printTopTen(char[] topTenChars, short[] topTenCount) {
        for (int i = 10; i >= 0; i--) {
            putChart(i, topTenCount);
        }
        for (int j = 0; j < 10; j++) {
            System.out.printf("%3c ", topTenChars[j]);
        }
    }

    private static void putChart(int i, short[] topTenCount) {
        for (int j = 0; j < 10; j++) {
            if (topTenCount[j] * 10 / topTenCount[0] == i) {
                System.out.printf("%3d ", topTenCount[j]);
            } else if (topTenCount[j] * 10 / topTenCount[0] > i) {
                System.out.print("  # ");
            } else {
                System.out.print("    ");
            }
        }
        System.out.println();

    }

    private static short[] getCharCount(String input) {
        char[] chars = input.toCharArray();
        short[] charsCount = new short[MAX_CHARS];
        for (char c : chars) {
            if (charsCount[c] < 999) charsCount[c]++;
        }
        return charsCount;
    }
}