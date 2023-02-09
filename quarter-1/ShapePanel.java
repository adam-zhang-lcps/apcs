import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Random;
import javax.swing.*;

public class ShapePanel extends JPanel {
    private static final Color BACKGROUND = new Color(120, 120, 120);

    // Create two fields
    // One for the Shapes Array (just declared, not intialized)
    // One integer for the size of the array (10 is a good default)
    private Shape[] shapes;
    private int numShapes = 10;

    private Color myColor;
    private Timer t;

    public ShapePanel() {
        myColor = BACKGROUND;
        initShapes();

        // timer to repaint every 20 microseconds
        // The listener for this is at the bottom
        t = new Timer(20, new Listener());
        t.start();
    }

    // Complete the method
    public void initShapes() {
        shapes = new Shape[numShapes];
        Random r = new Random();
        Color[] colors = {Color.RED,    Color.BLUE,   Color.GREEN,
                          Color.YELLOW, Color.ORANGE, Color.MAGENTA,
                          Color.CYAN};

        for (int i = 0; i < shapes.length; i++) {
            int x = r.nextInt(701);
            int y = r.nextInt(401);
            shapes[i] = switch (r.nextInt(5)) {
            case 0 -> new Circle(x, y, 100, colors[r.nextInt(colors.length)], 2, 1);
            case 1 -> new Square(x, y, 100, colors[r.nextInt(colors.length)], 1, 2);
            case 2 -> new Triangle(x, y, 100, colors[r.nextInt(colors.length)], 2, 2);
            case 3 -> new Rectangle(x, y, 100, 50, colors[r.nextInt(colors.length)], 1, 3);
            case 4 -> new Diamond(x, y, 100, 50, colors[r.nextInt(colors.length)], 3, 1);
            default -> null;
            };
        }
    }

    public void paintComponent(Graphics g) {
        // Overwrite what was on the screen before
        // with a blank background
        g.setColor(myColor);
        g.fillRect(0, 0, getWidth(), getHeight());

        Arrays.stream(shapes).forEach(shape -> shape.move(getWidth(), getHeight()));

        Arrays.stream(shapes).forEach(shape -> shape.draw(g));
    }

    private class Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            repaint();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Shapes");
        frame.setSize(1000, 700);
        frame.setLocation(300, 50);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new ShapePanel());
        frame.setVisible(true);
    }
}
