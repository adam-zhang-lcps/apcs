public class Rational {
    private int numerator;
    private int denominator;

    public Rational(int x) {
        numerator = x;
        denominator = 1;
    }

    /**
     * Constructs an object representing a rational number in the form of numer
     * / denom. The rational number will be simplified.
     *
     * @param numer the numerator of a rational number
     * @param denom the denominator of a rational number
     */
    public Rational(int numer, int denom) {
        numerator = numer * (denom < 0 ? -1 : 1);
        denominator = Math.abs(denom);

        reduce(); // simplify
    }

    /**
     * Find the sum of the two Rational numbers
     *
     * @param num a Rational number
     * @return a new Rational that is the sum of two Rational numbers
     */
    public Rational add(Rational num) {
        int commonDenominator = num.denominator * denominator;
        int newNumerator =
            num.numerator * denominator + numerator * num.denominator;

        return new Rational(newNumerator, commonDenominator);
    }

    public Rational subtract(Rational num) {
        int commonDenominator = num.denominator * denominator;
        int newNumerator =
            numerator * num.denominator - num.numerator * denominator;

        return new Rational(newNumerator, commonDenominator);
    }

    public Rational multiply(Rational num) {
        int newNumerator = num.numerator * numerator;
        int newDenominator = num.denominator * denominator;

        return new Rational(newNumerator, newDenominator);
    }

    public Rational divide(Rational num) { return multiply(num.reciprocal()); }

    public Rational reciprocal() {
        return new Rational(denominator, numerator);
    }

    /*
     *
     * ---------------- Private Helper Methods -----------------------
     *
     *
     */

    /**
     * Reduces the fraction
     */
    private void reduce() {
        int gcd = gcd(numerator, denominator);
        numerator /= gcd;
        denominator /= gcd;
    }

    /**
     * Find the greatest common divisor between integers a and b
     *
     * @param a an integer
     * @param b an integer
     * @return the GCD
     */
    private int gcd(int a, int b) {
        int n = Math.min(Math.abs(a), Math.abs(b));
        while (!(a % n == 0 && b % n == 0))
            n = n - 1;
        return n;
    }

    public String toString() {
        return numerator + (denominator == 1 ? "" : "/" + denominator);
    }

    public boolean equals(Object obj) {
        if (obj instanceof Rational) {
            Rational r = (Rational)obj;
            return numerator == r.numerator && denominator == r.denominator;
        }
        return false;
    }
}
