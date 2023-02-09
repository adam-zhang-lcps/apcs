import java.util.Scanner;

public class SumReverse {
    private static int sumDigit(int n) {
        int sum = 0;

        while (n != 0) {
            sum += n % 10;
            n /= 10;
        }

        return sum;
    }

    private static int reverseDigit(int n) {
        int rev = 0;

        while (n != 0) {
            rev *= 10;
            rev += n % 10;
            n /= 10;
        }

        return rev;
    }

    private static String computeISBN10(int n) {
        int sum = 0;
        int nn = n;

        for (int i = 9; i > 0; i--) {
            sum += nn % 10 * i;
            nn /= 10;
        }

        int last = sum % 11;

        return n + "-" + (last == 10 ? "X" : last);
    }

    private static boolean verifyISBN(String n) {
        return computeISBN10(Integer.parseInt(n.substring(0, 9))).equals(n);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        for (int i = 0; i < 5; i++) {
            System.out.println("Enter a number:");
            int number = scan.nextInt();
            int sum = sumDigit(number);
            int reverse = reverseDigit(number);

            System.out.printf("integer: %d%nsum: %d%nreverse: %d%n", number,
                              sum, reverse);
        }

        int isbn = 158939758;
        System.out.println("isbn: " + computeISBN10(isbn));

        System.out.println("valid: " + verifyISBN("158939758-4"));

        scan.close();
    }
}
