import java.awt.Color;
import java.awt.Graphics;

public class Diamond extends Shape {
    private int width;
    private int height;

    public Diamond(int x, int y, int width, int height, Color color) {
        super(x, y, color);
        this.width = width;
        this.height = height;
    }

    public Diamond(int x, int y, int width, int height, Color color, int deltaX,
                   int deltaY) {
        super(x, y, color, deltaX, deltaY);
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        int[] xPoints = {getX(), getX() + width / 2, getX() + width,
                         getX() + width / 2};
        int[] yPoints = {getY() + height / 2, getY(), getY() + height / 2,
                         getY() + height};
        g.fillPolygon(xPoints, yPoints, 4);
    }

    @Override
    public void move(int width, int height) {
        setX(getX() + getDeltaX());
        setY(getY() + getDeltaY());
        if (getX() <= 0) {
            setDeltaX(-getDeltaX());
            setX(0);
        } else if (getX() + this.width >= width) {
            setDeltaX(-getDeltaX());
            setX(width - this.width);
        }
        if (getY() <= 0) {
            setDeltaY(-getDeltaY());
            setY(0);
        } else if (getY() + this.height >= height) {
            setDeltaY(-getDeltaY());
            setY(height - this.height);
        }
    }

    public int getWidth() { return width; }

    public int getHeight() { return height; }

    public void setWidth(int width) { this.width = width; }

    public void setHeight(int height) { this.height = height; }
}
