import java.util.Scanner;

public class Program {
    private static final int MAX_CHARS = 65535;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        short charCount[] = getCharCount(input);
        short TopTen[] = getTopTen(charCount);


    }

    private static short[] getTopTen(short[] charCount) {
        short[] TopTenCount = new short[10];
        char[] TopTenChars = new char[10];
        for (int i = 0; i < MAX_CHARS; i++) {
            if (charCount[i] > TopTenCount[9]) {
                TopTenCount[9] = charCount[i];
                TopTenChars[9] = (char) i;
                for (int j = 8; j >= 0; j--) {
                    if (TopTenCount[j] < TopTenCount[j + 1]) {
                        short tempCount = TopTenCount[j];
                        TopTenCount[j] = TopTenCount[j + 1];
                        TopTenCount[j + 1] = tempCount;
                        char tempChar = TopTenChars[j];
                        TopTenChars[j] = TopTenChars[j + 1];
                        TopTenChars[j + 1] = tempChar;
                    }
                }
            }
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(TopTenChars[i] + ": " + TopTenCount[i]);
        }
        return TopTenCount;
    }

    private static short[] getCharCount(String input) {
        char[] chars = input.toCharArray();
        short[] charCount = new short[MAX_CHARS];
        for (char c : chars) {
            charCount[c]++;
        }
        return charCount;
    }
}