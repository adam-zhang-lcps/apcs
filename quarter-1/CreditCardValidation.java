import java.util.Scanner;

class Main {
    public static boolean verifyCardNum(long num) {
        int sum = 0;
        boolean shouldDouble = false;

        while (num > 0) {
            int curNum = (int)(num % 10);

            if (shouldDouble) {
                curNum *= 2;

                if (curNum >= 10) {
                    curNum = (curNum % 10) + (curNum / 10);
                }
            }

            sum += curNum;
            num /= 10;
            shouldDouble = !shouldDouble;
        }

        return sum % 10 == 0;
    }

    public static String getCardType(long num) {
        switch (String.valueOf(num).charAt(0)) {
        case '4':
            return "Visa";
        case '5':
            return "MasterCard";
        case '3':
            return "AMEX";
        case '6':
            return "Discover";
        }

        return null;
    }

    // do not change code below
    public static void testCard(long num, boolean expResult, String expType) {
        boolean result = verifyCardNum(num);
        String cardType = getCardType(num);
        String resStr = "fail";
        if (expResult == result && expType.compareTo(cardType) == 0)
            resStr = "pass";
        System.out.printf("%6s %10s %16d is %5b\n", resStr, cardType, num,
                          result);
    }

    public static void main(String[] args) {
        /*******************************************
         * Expected Results
         *******************************************
         * result type card number pass AMEX 379354508162306 is true pass Visa
         * 4388576018402626 is false pass Visa 4556737586899855 is true pass
         *Discover 6011530160038519 is true pass Discover 6011530170038519 is
         *false pass MasterCard 5172964491701916 is true pass AMEX
         *379965901638574 is true
         *********************************************/

        System.out.printf("%6s %10s %16s\n", "result", "type", "card number");
        testCard(379354508162306L, true, "AMEX");
        testCard(4388576018402626L, false, "Visa");
        testCard(4556737586899855L, true, "Visa");
        testCard(6011530160038519L, true, "Discover");
        testCard(6011530170038519L, false, "Discover");
        testCard(5172964491701916L, true, "MasterCard");
        testCard(379965901638574L, true, "AMEX");

        Scanner s = new Scanner(System.in);
        System.out.println("Enter a credit card number");
        long num = s.nextLong();
        System.out.printf("Type: %s, Valid: %b%n", getCardType(num),
                          verifyCardNum(num));
        s.close();
    }
}
