import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;

public class GameSquare {
    private final String[] images = { null, "num1.jpg", "num2.jpg", "num3.jpg", "num4.jpg", "num5.jpg", "num6.jpg",
            "num7.jpg", "num8.jpg", "bomb.jpg" };
    private boolean covered = true;
    private int row;
    private int col;
    private int num;
    private boolean flagged = false;

    public GameSquare(int row, int col, int num) {
        this.row = row;
        this.col = col;
        this.num = num;
    }

    public void uncover() {
        covered = false;
    }

    public void toggleFlag() {
        flagged = !flagged;
    }

    public boolean isCovered() {
        return covered;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public int getNum() {
        return num;
    }

    public void draw(Graphics g, int tileSize, int offset, boolean offsetIsX) {
        int trueX = row * tileSize + (offsetIsX ? offset : 0);
        int trueY = col * tileSize + (!offsetIsX ? offset : 0);

        if (flagged) {
            if (!covered) {
                if (num == Minesweeper.MINE) {
                    g.drawImage(new ImageIcon("assets/minesweeper/bomb.png").getImage(), trueX, trueY, tileSize,
                            tileSize, null);
                } else {
                    g.drawImage(new ImageIcon("assets/minesweeper/x.png").getImage(), trueX, trueY, tileSize, tileSize,
                            null);
                }
            } else {
                g.drawImage(new ImageIcon("assets/minesweeper/flag.png").getImage(), trueX, trueY, tileSize, tileSize,
                        null);
            }
            return;
        }

        if (covered) {
            g.setColor(Color.GRAY);
            g.fillRect(trueX, trueY, tileSize, tileSize);
            return;
        }

        if (num == 0) {
            g.setColor(new Color(192, 192, 192));
            g.fillRect(trueX, trueY, tileSize, tileSize);
            return;
        }

        g.drawImage(new ImageIcon("assets/minesweeper/" + images[num]).getImage(), trueX, trueY, tileSize, tileSize,
                null);
    }

    @Override
    public String toString() {
        return String.format("%d, %d, %s", row, col, covered ? "covered" : "not covered");
    }
}
