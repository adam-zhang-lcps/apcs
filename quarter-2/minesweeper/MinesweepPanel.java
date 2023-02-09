import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.function.Consumer;
import javafx.scene.input.MouseButton;
import javax.swing.*;

public class MinesweepPanel extends JPanel implements MouseListener {
    // constants
    static final Color BACKGROUND = new Color(192, 192, 192);

    // define variables for the board of GameSquares
    private GameSquare[][] myGrid;

    private boolean offsetIsX = false;
    private int offsetAmount = 0;
    private int tileSize;
    private int gridSize;

    /**
     * Panel's constructor initialize all the grid and the board of GameSquares
     */
    public MinesweepPanel(int size) {
        gridSize = size;
        initializeGrid();
        addMouseListener(this);
    }

    private void doOnGrid(Consumer<GameSquare> f) {
        for (GameSquare[] c : myGrid) {
            for (GameSquare s : c) {
                f.accept(s);
            }
        }
    }

    /**
     * creates and populates the grid.
     */
    public void initializeGrid() {
        int[][] grid = Minesweeper.createInitGrid(gridSize);
        Minesweeper.printGrid(grid);

        myGrid = new GameSquare[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                int num = grid[i][j];
                GameSquare s = new GameSquare(i, j, num);
                myGrid[i][j] = s;
            }
        }
    }

    public void paintComponent(Graphics g) {
        g.setColor(BACKGROUND);
        g.fillRect(0, 0, getWidth(), getHeight());

        int rawTileWidth = getWidth() / myGrid.length;
        int rawTileHeight = getHeight() / myGrid.length;
        offsetAmount = Math.abs(rawTileWidth - rawTileHeight) * gridSize / 2;
        offsetIsX = rawTileWidth > rawTileHeight;
        tileSize = Math.min(rawTileWidth, rawTileHeight);

        doOnGrid(s -> s.draw(g, tileSize, offsetAmount, offsetIsX));

        g.setColor(Color.BLACK);
        int xOffset = offsetIsX ? offsetAmount : 0;
        int yOffset = !offsetIsX ? offsetAmount : 0;
        for (int i = 0; i <= myGrid.length; i++) {
            for (int j = 0; j <= myGrid.length; j++) {
                g.fillRect(i * tileSize - 1 + xOffset, yOffset, 2,
                           getHeight() - yOffset * 2);
                g.fillRect(xOffset, j * tileSize - 1 + yOffset,
                           getWidth() - xOffset * 2, 2);
            }
        }
    }

    private void uncoverRecursively(int row, int col) {
        if (row < 0 || col < 0 || row >= myGrid.length ||
            col >= myGrid.length) {
            return;
        }

        GameSquare s = myGrid[row][col];
        if (!s.isCovered() || s.isFlagged()) {
            return;
        }
        s.uncover();
        if (s.getNum() != 0) {
            return;
        }
        uncoverRecursively(row - 1, col - 1);
        uncoverRecursively(row, col - 1);
        uncoverRecursively(row + 1, col - 1);
        uncoverRecursively(row - 1, col);
        uncoverRecursively(row + 1, col);
        uncoverRecursively(row - 1, col + 1);
        uncoverRecursively(row, col + 1);
        uncoverRecursively(row + 1, col + 1);
    }

    // Add methods you need to implement the MouseListener interface
    /**
     * mousePressed call e.getX() and e.getY()to get the (x,y) coordinates of
     * where the mouse was pressed. use getWidth() and getHeight to determine
     * the dimensions of the panel
     *
     * Hint: use getHeight with the number of rows to determine which row was
     * selected.
     */
    public void mousePressed(MouseEvent e) {
        saySomething("Mouse pressed; # of clicks: " + e.getClickCount(), e);

        int xOffset = offsetIsX ? offsetAmount : 0;
        int yOffset = !offsetIsX ? offsetAmount : 0;
        int x = (e.getX() - xOffset) / tileSize;
        int y = (e.getY() - yOffset) / tileSize;
        if (x < 0 || y < 0 || x >= gridSize || y >= gridSize) {
            return;
        }

        GameSquare s = myGrid[x][y];

        if (!s.isCovered()) {
            return;
        }

        if (e.getButton() == MouseEvent.BUTTON1 && !s.isFlagged()) {
            uncoverRecursively(x, y);

            // if the square's number was 9 (GameSquare.MINE), uncover all the
            // board's Squares.
            if (s.getNum() == Minesweeper.MINE) {
                doOnGrid(sq -> sq.uncover());
            }
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            s.toggleFlag();
        }

        // repaint() will cause paintComponent() to get invoked, which will
        // redraw myGrid
        repaint();
    }

    void saySomething(String eventDescription, MouseEvent e) {
        System.out.println(eventDescription + " detected on " +
                           e.getComponent().getClass().getName() + "."
                           + "\n");
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    /*
     * ====================================================== Code to create the
     * Panel, you do not need to modify
     * ======================================================
     */
    static int myWidth = 1500;
    static int myHeight = 900;

    public static void main(String[] args) {
        JFrame frame = new JFrame("MineSweeper");
        frame.setSize(myWidth, myHeight);
        frame.setLocation(0, 0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new MinesweepPanel(10));
        frame.setVisible(true);
    }
}
