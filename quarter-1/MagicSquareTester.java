public class MagicSquareTester {

    public static void main(String[] args) {

        // true
        int[][] square1 = { { 8, 1, 6 }, { 3, 5, 7 }, { 4, 9, 2 } };

        MagicSquare magicSquare1 = new MagicSquare(square1);
        magicSquare1.writeMatrix();

        System.out.println(magicSquare1.isMagic());
        System.out.println();

        // true
        int[][] square2 = { { 16, 2, 3, 13 }, { 5, 11, 10, 8 }, { 9, 7, 6, 12 }, { 4, 14, 15, 1 } };

        MagicSquare magicSquare2 = new MagicSquare(square2);
        magicSquare2.writeMatrix();

        System.out.println(magicSquare2.isMagic());
        System.out.println();

        // false
        int[][] square3 = { { 14, 2, 3, 13 }, { 8, 6, 10, 5 }, { 1, 7, 11, 12 }, { 4, 16, 15, 9 } };

        MagicSquare magicSquare3 = new MagicSquare(square3);
        magicSquare3.writeMatrix();

        System.out.println(magicSquare3.isMagic());
        System.out.println();

        // false - 2 columns are the same
        int[][] square4 = { { 1, 3, 8 }, { 1, 3, 9 }, { 1, 3, 10 } };

        MagicSquare magicSquare4 = new MagicSquare(square4);
        magicSquare4.writeMatrix();

        System.out.println(magicSquare4.isMagic());
        System.out.println();

        // false - nth row and column are the same
        int[][] square5 = { { 1, 2, 3 }, { 2, 5, 7 }, { 3, 7, 5 } };

        MagicSquare magicSquare5 = new MagicSquare(square5);
        magicSquare5.writeMatrix();

        System.out.println(magicSquare5.isMagic());
        System.out.println();

    }

}
