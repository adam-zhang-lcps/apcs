import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class MyPointPanel extends JPanel implements MouseListener {
    // constant
    private static final Color BACKGROUND = new Color(242, 43, 43);
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 800;

    // fields
    private ArrayList<RotatingPoint> myPoints = new ArrayList<>();
    private Color myColor;
    private Timer t;

    public MyPointPanel(JFrame frame, int maxX, int maxY) {
        myColor = BACKGROUND;
        t = new Timer(50, new Listener());
        t.start();
        addMouseListener(this);
    }

    public void paintComponent(Graphics g) {
        g.setColor(myColor);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Coordinate grid linee
        g.setColor(Color.WHITE);
        g.fillRect(0, HEIGHT / 2 - 2, WIDTH, 4);
        g.fillRect(WIDTH / 2 - 2, 0, 4, HEIGHT);

        // Intentionally using == to compare references,
        // since I know the memory addresses would be the same
        double largestDistance = myPoints.stream()
                .flatMapToDouble(p -> myPoints.stream().mapToDouble(p2 -> p.distance(p2))).max().orElse(0);
        // Draw lines first, so the points are on top
        for (Point p : myPoints) {
            for (Point p2 : myPoints) {
                // Using == again
                if (p != p2) {
                    int brightness = (int) (255 * (p.distance(p2) / largestDistance));
                    g.setColor(new Color(brightness, brightness, brightness));
                    g.drawLine(p.x, p.y, p2.x, p2.y);
                }
            }
        }

        for (Point p : myPoints) {
            g.setColor(Color.BLUE);
            g.fillOval(p.x - 5, p.y - 5, 10, 10);
        }
    }

    public void mouseClicked(MouseEvent e) {
        saySomething("Mouse pressed; # of clicks: " + e.getClickCount(), e);
    }

    public void mouseReleased(MouseEvent e) {
        saySomething("Mouse released; # of clicks: " + e.getClickCount(), e);
    }

    public void mouseEntered(MouseEvent e) {
        saySomething("Mouse entered", e);
        myColor = BACKGROUND;

        repaint();
    }

    public void mouseExited(MouseEvent e) {
        saySomething("Mouse exited", e);
        myColor = Color.WHITE;
        myPoints.clear();

        repaint();
    }

    public void mousePressed(MouseEvent e) {
        saySomething("Mouse pressed (# of clicks: " + e.getClickCount() + ")", e);

        // retrieves x and y location of the click
        int x = e.getX();
        int y = e.getY();

        myPoints.add(new RotatingPoint(x - HEIGHT / 2, y - WIDTH / 2, e.getButton() == MouseEvent.BUTTON1));

        repaint();

        System.out.println("x = " + x + "  y = " + y);
    }

    public void saySomething(String eventDescription, MouseEvent e) {
        System.out.println(eventDescription + " detected on " + e.getComponent().getClass().getName() + "." + "\n");
    }

    private class Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            myPoints.forEach(p -> p.move());
            repaint();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("What is the Point");
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocation(50, 50);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new MyPointPanel(frame, 800, 400));
        frame.setVisible(true);
    }
}
