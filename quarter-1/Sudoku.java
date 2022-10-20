public class Sudoku {
    int[][] board;

    public Sudoku(int[] board) {
        this.board = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.board[i][j] = board[i * 9 + j];
            }
        }
    }

    public boolean verifyGame() {
        for (int i = 0; i < 9; i++) {
            if (!verifyRow(i)) {
                return false;
            }
        }
        for (int i = 0; i < 9; i++) {
            if (!verifyColumn(i)) {
                return false;
            }
        }
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                if (!verifySquare(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean verifyRow(int row) {
        boolean[] numbers = new boolean[9];
        for (int i = 0; i < 9; i++) {
            if (board[row][i] != 0) {
                if (numbers[board[row][i] - 1]) {
                    return false;
                }
                numbers[board[row][i] - 1] = true;
            }
        }
        return true;
    }

    private boolean verifyColumn(int column) {
        boolean[] numbers = new boolean[9];
        for (int i = 0; i < 9; i++) {
            if (board[i][column] != 0) {
                if (numbers[board[i][column] - 1]) {
                    return false;
                }
                numbers[board[i][column] - 1] = true;
            }
        }
        return true;
    }

    private boolean verifySquare(int row, int column) {
        boolean[] numbers = new boolean[9];
        for (int i = row; i < row + 3; i++) {
            for (int j = column; j < column + 3; j++) {
                if (board[i][j] != 0) {
                    if (numbers[board[i][j] - 1]) {
                        return false;
                    }
                    numbers[board[i][j] - 1] = true;
                }
            }
        }
        return true;
    }
}
