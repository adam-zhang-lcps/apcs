import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Minesweeper {

    public static final int MINE = 9;

    public static void main(String[] args) {

        // Enter size grid N x N
        // place N bombs randomly in the grid - check for repeats
        // In each remaining space, count the number of bombs
        // print grid

        int gridSize = getGridSize();
        int[][] grid = createInitGrid(gridSize);
        printGrid(grid);
    }

    /**
     * Initialize the grid with a given grid size. Note, the grid will always be
     * a square.
     *
     * @param size the number of rows and columns in the 2D array
     * @return a square 2D array of <code>size</code> x <code>size</code>
     */

    public static int[][] createInitGrid(int gridSize) {
        int[][] grid = makeGrid(gridSize);
        placeMines(gridSize, grid);
        countAllSurroundingMines(grid);
        return grid;
    }

    /**
     * Prompts to enter the size of the grid. Ensures the grid size > 3.
     *
     * @return the size of the grid
     *
     *         NOTE: Start off returning 10 until you get all the other methods
     *         working. Then change to prompt user.
     */
    public static int getGridSize() {
        Scanner s = new Scanner(System.in);
        int choice = 0;
        do {
            System.out.printf("Enter a number for the size grid: ");
            try {
                choice = s.nextInt();
            } catch (InputMismatchException e) {
                System.out.printf("Invalid input!%n");
                // Move past the current invalid token
                s.nextLine();
            }
        } while (choice < 3);

        s.close();
        return choice;
    }

    /**
     * Creates and returns a 2D array of <code>size</code> x <code>size</code>
     *
     * @param size the number of rows and columns in the 2D array
     * @return a square 2D array of <code>size</code> x <code>size</code>
     */
    private static int[][] makeGrid(int size) { return new int[size][size]; }

    /**
     * Randomly places n mines in the 2D array, <code>grid</code>, where n is
     * equal to <code>size</code>
     *
     * @param size the number of mines to place in the <code>grid</code>
     * @param grid the 2D array
     */
    private static void placeMines(int size, int[][] grid) {
        int mines = 0;
        Random random = new Random();
        while (mines < size) {
            int x = random.nextInt(grid.length);
            int y = random.nextInt(grid.length);
            if (grid[x][y] != MINE) {
                grid[x][y] = MINE;
                mines++;
            }
        }
    }

    /**
     * After the MINEs have been set, this method is called to set the rest of
     * the elements in the grid. Each element is set to the count of the number
     * of surrounding mines.
     *
     * This method should traverse the grid and call countSurroundingMines() for
     * each (r,c).
     *
     * @param grid the 2D array
     */
    private static void countAllSurroundingMines(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] != MINE) {
                    countSurroundingMines(i, j, grid);
                }
            }
        }
    }

    /**
     * Counts and then sets the element to the number of mines surrounding the
     * element at the given <code>row</code> and <code>col</code>
     *
     * @param row  the row location
     * @param col  the column location
     * @param grid the 2D array
     */
    private static void countSurroundingMines(int row, int col, int[][] grid) {
        boolean left = row > 0;
        boolean right = row < grid.length - 1;
        boolean up = col > 0;
        boolean down = col < grid.length - 1;

        int mines = 0;

        if (left && up && grid[row - 1][col - 1] == MINE) {
            mines++;
        }
        if (up && grid[row][col - 1] == MINE) {
            mines++;
        }
        if (right && up && grid[row + 1][col - 1] == MINE) {
            mines++;
        }
        if (left && grid[row - 1][col] == MINE) {
            mines++;
        }
        if (right && grid[row + 1][col] == MINE) {
            mines++;
        }
        if (left && down && grid[row - 1][col + 1] == MINE) {
            mines++;
        }
        if (down && grid[row][col + 1] == MINE) {
            mines++;
        }
        if (down && right && grid[row + 1][col + 1] == MINE) {
            mines++;
        }

        grid[row][col] = mines;
    }

    /**
     * Prints the 2D array of integers you can use the print format method,
     * printf, display an integer with 2 spaces: System.out.printf("%2d ",
     * grid[r][c]);
     *
     * @param grid the 2D array
     */
    public static void printGrid(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                System.out.printf("%d ", grid[i][j]);
            }
            System.out.printf("%n");
        }
    }
}
