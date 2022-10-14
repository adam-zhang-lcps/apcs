import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HeatMap extends JPanel implements MouseListener {

    private static final Color BACKGROUND = new Color(204, 204, 204);

    private double[][] tempGrid = new double[50][50];
    private double maxTemp = 150;
    private double minTemp = -150;

    // for part 3, leave them alone
    private int clickedRow = -1;
    private int clickedCol = -1;
    private int clickTemp = 0;

    public HeatMap() {
        // initialize the heat map, half cold, half hot
        for (int i = 0; i < tempGrid.length; i++) {
            for (int j = 0; j < tempGrid[i].length; j++) {
                if (j < tempGrid.length / 2) {
                    tempGrid[i][j] = -150;
                } else {
                    tempGrid[i][j] = 150;
                }
            }
        }

        Timer t = new Timer(40, new Listener());
        t.start();

        addMouseListener(this);

        // Tests get colors methods. Prints results to console.
        for (int temp = (int) minTemp; temp < maxTemp; temp += 10) {
            System.out.printf("%5d: %4d, %4d, %4d\n", temp, getRed(temp), getGreen(temp), getBlue(temp));
        }
    }

    // Given a temp value, return proper R, G or B value.
    public int getRed(double temp) {
        if (temp > 0) {
            return 255;
        }
        if (temp <= -75) {
            return 0;
        }
        return (int) (255 * (temp + 75) / 75);
    }

    public int getGreen(double temp) {
        if (temp > maxTemp) {
            return 0;
        }
        if (temp < minTemp) {
            return 0;
        }
        if (temp <= 75 && temp > -75) {
            return 255;
        }
        if (temp > 75) {
            return 255 - (int) (255 * (temp - 75) / 75);
        }
        return (int) (255 * (temp + 150) / 75);
    }

    public int getBlue(double temp) {
        if (temp > 75) {
            return 0;
        }
        if (temp <= 0) {
            return 255;
        }
        return 255 - (int) (255 * temp / 75);
    }

    // draws squares representing the temp in each cell
    // method completed for you, nothing to do here
    public void paintComponent(Graphics g) {
        g.setColor(BACKGROUND);
        g.fillRect(0, 0, getWidth(), getHeight());

        int blockHeight = getHeight() / tempGrid.length + 1;
        int blockWidth = getWidth() / tempGrid[0].length + 1;
        for (int r = 0; r < tempGrid.length; r++) {
            for (int c = 0; c < tempGrid[r].length; c++) {

                double tVal = tempGrid[r][c];
                g.setColor(new Color(getRed(tVal), getGreen(tVal), getBlue(tVal)));

                int x = c * blockWidth; // (x,y) is the upper left hand corner of the rectangle
                int y = r * blockHeight;
                g.fillRect(x, y, blockWidth, blockHeight);
            }
        }

        // Display temperatures of both sides
        String avgLeftTempStr = String.format("%7.2f", tempGrid[tempGrid.length / 2][0]);
        String avgRightTempStr = String.format("%7.2f", tempGrid[tempGrid.length / 2][tempGrid[0].length - 1]);

        g.setColor(Color.black);
        g.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 60));
        g.drawString("Left Side Temp", 10, 60);
        g.drawString(avgLeftTempStr, 100, 120);
        g.drawString("Right Side Temp", getWidth() - 600, 60);
        g.drawString(avgRightTempStr, getWidth() - 350, 120);
    }

    @Override
    public void mousePressed(MouseEvent event) {
        // Convert x/y click to row/col and set clickedTemp
        clickedRow = event.getY() / (getHeight() / tempGrid.length + 1);
        clickedCol = event.getX() / (getWidth() / tempGrid[0].length + 1);
        clickTemp = event.getButton() == MouseEvent.BUTTON1 ? 1 : -1;

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // reset clickedRow and clickedCol
        clickedRow = -1;
        clickedCol = -1;
    }

    // these mouse methods are unneeded for this lab
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    private class Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // recalculate every element of the 2D array
            // update the temperature values in tempGrid
            for (int i = 0; i < tempGrid.length; i++) {
                for (int j = 0; j < tempGrid[i].length; j++) {
                    double total = tempGrid[i][j];
                    if (i > 0) {
                        total += tempGrid[i - 1][j];
                    }
                    if (i < tempGrid.length - 1) {
                        total += tempGrid[i + 1][j];
                    }
                    if (j > 0) {
                        total += tempGrid[i][j - 1];
                    }
                    if (j < tempGrid[i].length - 1) {
                        total += tempGrid[i][j + 1];
                    }
                    tempGrid[i][j] = total / 5;
                }
            }

            if (clickedRow != -1 && clickedCol != -1) {
                tempGrid[clickedRow][clickedCol] = (clickTemp > 0 ? maxTemp : minTemp) * 10;
            }

            repaint(); // leave this as the last line of code in actionPerformed
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Heat Map");
        frame.setSize(1400, 1005);
        frame.setLocation(0, 0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new HeatMap());
        frame.setVisible(true);
    }
}
