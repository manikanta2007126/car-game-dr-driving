import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Obstacle {
    private int x;
    private int y;
    private int width = 60;
    private int height = 30;
    private int speed = 3;

    public Obstacle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update(int panelHeight) {
        y += speed;
    }

    public void draw(Graphics2D g) {
        // Obstacle (cone/barrier)
        g.setColor(Color.ORANGE);
        g.fillRect(x, y, width, height);

        // Obstacle pattern
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
        g.drawLine(x, y, x + width, y + height);
        g.drawLine(x + width, y, x, y + height);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
