import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ShapeTester extends JPanel {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final Color BACKGROUND = Color.GRAY;

    private BufferedImage image;
    private Graphics buffer;

    public ShapeTester() {
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        buffer = image.getGraphics();

        drawBackground();

        Circle topLeftCircle = new Circle(0, 0, 50, Color.BLUE);
        Circle middleCircle = new Circle(200, 400, 120, Color.GREEN.brighter());
        Square leftSquare = new Square(0, 100, 100, Color.YELLOW);
        Square square = new Square(500, 200, 100, Color.RED);

        topLeftCircle.draw(
            buffer); // ensure only 25% of the blue circle appears on screen
        middleCircle.draw(buffer);
        leftSquare.draw(buffer); // ensure only 50% of the yellow square appears
                                 // on the screen
        square.draw(buffer);
        // Draw two rectangles
        new Rectangle(200, 200, 50, 60, Color.BLUE).draw(buffer);
        new Rectangle(400, 100, 10, 60, Color.MAGENTA).draw(buffer);
        // Draw two circles
        new Triangle(500, 400, 50, Color.WHITE).draw(buffer);
        new Triangle(200, 200, 80, Color.BLACK).draw(buffer);
    }

    /**
     * Draws the background
     */
    public void drawBackground() {
        buffer.setColor(BACKGROUND);
        buffer.fillRect(0, 0, WIDTH, HEIGHT);
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Shape Tester");
        frame.setSize(800, 600);
        frame.setLocation(50, 100);
        frame.setContentPane(new ShapeTester());
        frame.setVisible(true);
    }
}
