import java.awt.Point;

public class RotatingPoint extends Point {
    private final double PANEL_WIDTH = 1000;
    private final double PANEL_HEIGHT = 800;

    private double radius;
    private double angle;
    private boolean direction;

    public RotatingPoint(int x, int y, boolean direction) {
        super(x, y);
        radius = Math.sqrt(x * x + y * y);
        angle = Math.atan((double)y / x);
        this.direction = direction;
    }

    public void move() {
        angle += 0.1 * (direction ? 1 : -1);
        angle %= 2 * Math.PI;
        double x = radius * Math.cos(angle) + PANEL_WIDTH / 2;
        double y = radius * Math.sin(angle) + PANEL_HEIGHT / 2;
        setLocation((int)x, (int)y);
    }
}
