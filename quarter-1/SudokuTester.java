public class SudokuTester {
    public static void main(String[] args) {
        int[] game1 = { 3, 5, 7, 9, 6, 4, 2, 8, 1, 4, 6, 8, 1, 2, 3, 5, 7, 9, 9, 1, 2, 5, 8, 7, 4, 6, 3,

                6, 3, 1, 7, 9, 5, 8, 4, 2, 7, 2, 4, 3, 1, 8, 6, 9, 5, 8, 9, 5, 2, 4, 6, 1, 3, 7,

                1, 7, 6, 4, 5, 9, 3, 2, 8, 5, 8, 3, 6, 7, 2, 9, 1, 4, 2, 4, 9, 8, 3, 1, 7, 5, 6 }; // all good

        int[] game2 = { 3, 5, 7, 9, 6, 4, 2, 8, 1, 4, 6, 8, 1, 2, 3, 5, 7, 9, 9, 1, 2, 5, 8, 7, 4, 6, 3,

                6, 3, 1, 7, 9, 5, 8, 4, 2, 7, 2, 4, 3, 1, 8, 6, 9, 5, 8, 9, 5, 2, 7, 6, 1, 3, 7,

                1, 7, 6, 4, 5, 9, 3, 2, 8, 5, 8, 3, 6, 0, 2, 9, 1, 4, 2, 4, 9, 8, 3, 1, 7, 5, 6 }; // duplicate in row 5

        int[] game3 = { 3, 5, 7, 9, 6, 4, 2, 8, 1, 4, 6, 8, 1, 2, 3, 5, 7, 9, 9, 1, 2, 5, 8, 7, 4, 6, 3,

                6, 3, 1, 7, 9, 5, 8, 4, 2, 7, 2, 4, 3, 1, 8, 6, 9, 5, 8, 9, 5, 2, 4, 7, 1, 3, 0,

                1, 7, 6, 4, 5, 9, 3, 2, 8, 5, 8, 3, 6, 7, 2, 9, 1, 4, 2, 4, 9, 8, 3, 1, 7, 5, 6 }; // duplicate in
                                                                                                   // column 5

        int[] game4 = { 3, 5, 7, 0, 6, 4, 2, 8, 1, 4, 6, 8, 1, 2, 3, 5, 7, 9, 9, 1, 2, 5, 8, 7, 4, 6, 3,

                6, 3, 1, 7, 9, 5, 8, 4, 2, 7, 2, 4, 3, 1, 8, 6, 9, 5, 8, 9, 5, 2, 4, 6, 1, 3, 7,

                1, 7, 6, 4, 5, 9, 3, 2, 8, 5, 8, 3, 9, 7, 2, 0, 1, 4, 2, 4, 9, 8, 3, 1, 7, 5, 6 }; // duplicate in lower
                                                                                                   // middle grid

        Sudoku myGame = new Sudoku(game1);
        boolean result = myGame.verifyGame();
        System.out.println("game1 result = " + result);

        myGame = new Sudoku(game2);
        result = myGame.verifyGame();
        System.out.println("game2 result = " + result);

        myGame = new Sudoku(game3);
        result = myGame.verifyGame();
        System.out.println("game3 result = " + result);

        myGame = new Sudoku(game4);
        result = myGame.verifyGame();
        System.out.println("game3 result = " + result);
    }

}
