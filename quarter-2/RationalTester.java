public class RationalTester {
    public static void main(String[] args) {

        Rational r1 = new Rational(1, 3);
        Rational r2 = new Rational(1, 4);
        Rational r3 = new Rational(2);
        Rational r4 = new Rational(1, 3);
        Rational r5 = new Rational(3, 9);
        Rational r6 = new Rational(-4, -7);
        Rational r7 = new Rational(3, 5);

        System.out.println("r1: " + r1);
        System.out.println("r2: " + r2);
        System.out.println("r3: " + r3);
        System.out.println("r4: " + r4);
        System.out.println("r5: " + r5);
        System.out.println("r6: " + r6);
        System.out.println("r7: " + r7);
        System.out.println();

        System.out.println("Test Equals (same values) r1.equals(r4): " +
                           r1.equals(r4));
        System.out.println("Test Equals (equivalent values) r1.equals(r5): " +
                           r1.equals(r4));
        System.out.println("Test Equals (not equal) r1.equals(r2): " +
                           r1.equals(r2));
        System.out.println();

        System.out.println("Test Reciprocal: " + r7 + " --> " +
                           r7.reciprocal());
        System.out.println();

        printResult("add", r1, r2, new Rational(7, 12));
        printResult("subtract", r1, r2, new Rational(1, 12));
        printResult("multiply", r1, r2, new Rational(1, 12));
        printResult("divide", r1, r2, new Rational(4, 3));
    }

    /**
     * Prints the result of the operation performed upon r1 and r2 and compares
     * the result to the expected outcome
     *
     * @param operation the operation performed
     * @param r1        a Rational object
     * @param r2        a Rational object
     * @param expected  the expected result of Rational object
     */
    public static void printResult(String operation, Rational r1, Rational r2,
                                   Rational expected) {

        Rational result = null;
        String sign = "";

        if (operation.equals("add")) {
            result = r1.add(r2);
            sign = "+";
        }
        // Complete for other cases...
        if (operation.equals("subtract")) {
            result = r1.subtract(r2);
            sign = "-";
        }
        if (operation.equals("multiply")) {
            result = r1.multiply(r2);
            sign = "*";
        }
        if (operation.equals("divide")) {
            result = r1.divide(r2);
            sign = "/";
        }

        System.out.print(operation + "\t" + r1 + " " + sign + " " + r2 +
                         " --> " + result);
        System.out.print("\tExpected: " + expected);
        System.out.println("\t" + result.equals(expected));
    }
}
