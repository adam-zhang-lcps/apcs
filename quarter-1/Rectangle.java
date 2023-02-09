import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends Square {
    private int height;

    public Rectangle(int x, int y, int width, int height, Color color) {
        super(x, y, width, color);
        this.height = height;
    }

    public Rectangle(int x, int y, int width, int height, Color color,
                     int deltaX, int deltaY) {
        super(x, y, width, color, deltaX, deltaY);
        this.height = height;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.fillRect(getX() - getWidth() / 2, getY() - height / 2, getWidth(),
                   height);
    }

    @Override
    public void move(int panelWidth, int panelHeight) {
        setX(getX() + getDeltaX());
        setY(getY() + getDeltaY());
        if (getX() - getWidth() / 2 < 0) {
            setDeltaX(-getDeltaX());
            setX(getWidth() / 2);
        } else if (getX() + getWidth() / 2 > panelWidth) {
            setDeltaX(-getDeltaX());
            setX(panelWidth - getWidth() / 2);
        }
        if (getY() - height / 2 < 0) {
            setDeltaY(-getDeltaY());
            setY(height / 2);
        } else if (getY() + height / 2 > panelHeight) {
            setDeltaY(-getDeltaY());
            setY(panelHeight - height / 2);
        }
    }

    public int getHeight() { return height; }

    public void setHeight(int height) { this.height = height; }
}
