import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape {
    // fields
    private int x;
    private int y;
    private Color color;
    private int deltaX;
    private int deltaY;

    // constructor(s)
    public Shape(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.deltaX = 2;
        this.deltaY = 1;
    }

    public Shape(int x, int y, Color color, int deltaX, int deltaY) {
        this(x, y, color);
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    // methods

    public abstract void draw(Graphics g);

    public abstract void move(int width, int height);

    public int getX() { return x; }

    public void setX(int x) { this.x = x; }

    public int getY() { return y; }

    public void setY(int y) { this.y = y; }

    public Color getColor() { return color; }

    public void setColor(Color color) { this.color = color; }

    public int getDeltaX() { return deltaX; }

    public void setDeltaX(int deltaX) { this.deltaX = deltaX; }

    public int getDeltaY() { return deltaY; }

    public void setDeltaY(int deltaY) { this.deltaY = deltaY; }
}
