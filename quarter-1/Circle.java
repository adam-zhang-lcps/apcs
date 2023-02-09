import java.awt.Color;
import java.awt.Graphics;

public class Circle extends Shape {
    private int diameter;
    private int radius;

    public Circle(int x, int y, int diameter, Color color) {
        super(x, y, color);
        this.diameter = diameter;
        this.radius = diameter / 2;
    }

    public Circle(int x, int y, int diameter, Color color, int deltaX,
                  int deltaY) {
        super(x, y, color, deltaX, deltaY);
        this.diameter = diameter;
        this.radius = diameter / 2;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.fillOval(getX() - radius, getY() - radius, diameter, diameter);
    }

    @Override
    public void move(int width, int height) {
        this.setX(this.getX() + this.getDeltaX());
        this.setY(this.getY() + this.getDeltaY());
        if (getX() - radius < 0) {
            setX(radius);
            setDeltaX(-getDeltaX());
        } else if (getX() + radius >= width) {
            setX(width - radius - 1);
            setDeltaX(-getDeltaX());
        }
        if (getY() - radius < 0) {
            setY(radius);
            setDeltaY(-getDeltaY());
        } else if (getY() + radius >= height) {
            setY(height - radius - 1);
            setDeltaY(-getDeltaY());
        }
    }

    public int getDiameter() { return diameter; }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
        this.radius = diameter / 2;
    }

    public int getRadius() { return radius; }

    public void setRadius(int radius) {
        this.radius = radius;
        this.diameter = radius * 2;
    }
}
