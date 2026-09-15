import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Player {
    private int x;
    private int y;
    private int width = 40;
    private int height = 60;
    private int speed = 5;
    private int velocityX = 0;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveLeft() {
        velocityX = -speed;
    }

    public void moveRight() {
        velocityX = speed;
    }

    public void stop() {
        velocityX = 0;
    }

    public void update(int panelWidth, int panelHeight) {
        x += velocityX;

        // Boundary checking
        if (x < 0) {
            x = 0;
        } else if (x + width > panelWidth) {
            x = panelWidth - width;
        }
    }

    public void draw(Graphics2D g) {
        // Car body
        g.setColor(Color.RED);
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

    public boolean intersects(Enemy enemy) {
        return getBounds().intersects(enemy.getBounds());
    }

    public boolean intersects(Obstacle obstacle) {
        return getBounds().intersects(obstacle.getBounds());
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
