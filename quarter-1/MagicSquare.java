public class MagicSquare {

    private int[][] mySquare;

    /**
     * Makes mySquare a copy of input matrix
     */
    public MagicSquare(int[][] matrix) {
        mySquare = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                mySquare[i][j] = matrix[i][j];
            }
        }
    }

    /**
     * Display mySquare on screen
     */
    public void writeMatrix() {
        for (int i = 0; i < mySquare.length; i++) {
            for (int j = 0; j < mySquare[0].length; j++) {
                System.out.print(mySquare[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * @param row a row in in the matrix Precondition: 0 <= row < SIZE
     * @return the sum of the elements in row
     */
    public int sumRow(int row) {
        int sum = 0;
        for (int i = 0; i < mySquare[0].length; i++) {
            sum += mySquare[row][i];
        }
        return sum;
    }

    /**
     * @param col a column in the matrix Precondition: 0 <= col < SIZE
     * @return the sum of elements in col
     */
    public int sumCol(int col) {
        int sum = 0;
        for (int i = 0; i < mySquare.length; i++) {
            sum += mySquare[i][col];
        }
        return sum;
    }

    /**
     * @return the sum of elements in the major diagonal
     */
    public int sumMajorDiag() {
        int sum = 0;
        for (int i = 0; i < mySquare.length; i++) {
            sum += mySquare[i][i];
        }
        return sum;
    }

    /**
     * @return the sum of the elements in the minor diagonal
     */
    public int sumMinorDiag() {
        int sum = 0;
        for (int i = 0; i < mySquare.length; i++) {
            sum += mySquare[i][mySquare.length - 1 - i];
        }
        return sum;
    }

    /**
     * Precondition: mySquare is a square matrix of integers, which may or may
     * not be a magic square.
     *
     * @return true if mySquare is a magic square, false otherwise
     */
    public boolean isMagic() {
        int sum = sumRow(0);
        for (int i = 1; i < mySquare.length; i++) {
            if (sum != sumRow(i)) {
                return false;
            }
        }
        for (int i = 0; i < mySquare[0].length; i++) {
            if (sum != sumCol(i)) {
                return false;
            }
        }
        if (sum != sumMajorDiag()) {
            return false;
        }
        if (sum != sumMinorDiag()) {
            return false;
        }
        return true;
    }
}
