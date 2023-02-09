import java.awt.Color;
import java.awt.Graphics;

public class Triangle extends Shape {
    private int radius;

    public Triangle(int x, int y, int radius, Color color) {
        super(x, y, color);
        this.radius = radius;
    }

    public Triangle(int x, int y, int radius, Color color, int deltaX,
                    int deltaY) {
        super(x, y, color, deltaX, deltaY);
        this.radius = radius;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.fillPolygon(
            new int[] {getX(), getX() - radius, getX() + radius},
            new int[] {getY() - radius, getY() + radius, getY() + radius}, 3);
    }

    @Override
    public void move(int width, int height) {
        setX(getX() + getDeltaX());
        setY(getY() + getDeltaY());
        if (getX() - radius < 0) {
            setDeltaX(-getDeltaX());
            setX(radius);
        }
        if (getX() + radius > width) {
            setDeltaX(-getDeltaX());
            setX(width - radius);
        }
        if (getY() - radius < 0) {
            setDeltaY(-getDeltaY());
            setY(radius);
        }
        if (getY() + radius > height) {
            setDeltaY(-getDeltaY());
            setY(height - radius);
        }
    }

    public double getRadius() { return radius; }

    public void setRadius(int radius) { this.radius = radius; }
}
