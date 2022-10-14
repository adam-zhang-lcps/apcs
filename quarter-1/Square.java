import java.awt.Color;
import java.awt.Graphics;

public class Square extends Shape {
    private int width;

    public Square(int x, int y, int width, Color color) {
        super(x, y, color);
        this.width = width;
    }

    public Square(int x, int y, int width, Color color, int deltaX, int deltaY) {
        super(x, y, color, deltaX, deltaY);
        this.width = width;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        // Remember that x and y should be at the center
        g.fillRect(getX() - width / 2, getY() - width / 2, width, width);
    }

    @Override
    public void move(int panelWidth, int panelHeight) {
        setX(getX() + getDeltaX());
        setY(getY() + getDeltaY());
        if (getX() - width / 2 < 0) {
            setDeltaX(-getDeltaX());
            setX(width / 2);
        } else if (getX() + width / 2 > panelWidth) {
            setDeltaX(-getDeltaX());
            setX(panelWidth - width / 2);
        }
        if (getY() - width / 2 < 0) {
            setDeltaY(-getDeltaY());
            setY(width / 2);
        } else if (getY() + width / 2 > panelHeight) {
            setDeltaY(-getDeltaY());
            setY(panelHeight - width / 2);
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
