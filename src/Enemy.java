import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Enemy {
    private int x;
    private int y;
    private int width = 40;
    private int height = 60;
    private int speed;
    private int lane;

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
        this.speed = 2 + (int) (Math.random() * 4);
        this.lane = x / 100;
    }

    public void update(int panelWidth, int panelHeight) {
        y += speed;

        // Reset position when off-screen
        if (y > panelHeight) {
            y = -height;
            x = (int) (Math.random() * (panelWidth - width));
        }
    }

    public void draw(Graphics2D g) {
        // Car body
        g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height);

        // Car windows
        g.setColor(Color.CYAN);
        g.fillRect(x + 5, y + 10, width - 10, 15);
        g.fillRect(x + 5, y + 35, width - 10, 15);

        // Car outline
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
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
