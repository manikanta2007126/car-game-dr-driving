import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements Runnable, KeyListener {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int FPS = 60;

    private Player player;
    private List<Enemy> enemies;
    private List<Obstacle> obstacles;
    private GameState gameState;
    private int score;
    private int level;
    private Thread gameThread;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(new Color(34, 139, 34)); // Green road
        setFocusable(true);
        addKeyListener(this);

        initializeGame();
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void initializeGame() {
        player = new Player(WIDTH / 2, HEIGHT - 100);
        enemies = new ArrayList<>();
        obstacles = new ArrayList<>();
        gameState = GameState.RUNNING;
        score = 0;
        level = 1;

        spawnEnemies(3);
        spawnObstacles(5);
    }

    private void spawnEnemies(int count) {
        for (int i = 0; i < count; i++) {
            int x = (int) (Math.random() * (WIDTH - 50));
            int y = (int) (Math.random() * (HEIGHT / 2));
            enemies.add(new Enemy(x, y));
        }
    }

    private void spawnObstacles(int count) {
        for (int i = 0; i < count; i++) {
            int x = (int) (Math.random() * (WIDTH - 50));
            int y = (int) (Math.random() * (HEIGHT - 100));
            obstacles.add(new Obstacle(x, y));
        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountTicks = FPS;
        double ns = 1000000000 / amountTicks;
        double delta = 0;

        while (gameState == GameState.RUNNING) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    private void update() {
        if (gameState != GameState.RUNNING) return;

        player.update(WIDTH, HEIGHT);

        // Update enemies
        for (Enemy enemy : enemies) {
            enemy.update(WIDTH, HEIGHT);

            // Check collision with player
            if (player.intersects(enemy)) {
                gameState = GameState.GAME_OVER;
            }
        }

        // Update obstacles
        for (Obstacle obstacle : obstacles) {
            obstacle.update(HEIGHT);

            // Check collision with player
            if (player.intersects(obstacle)) {
                gameState = GameState.GAME_OVER;
            }
        }

        // Remove off-screen obstacles and add new ones
        obstacles.removeIf(o -> o.getY() > HEIGHT);
        if (obstacles.size() < 5) {
            spawnObstacles(1);
        }

        // Increase score
        score += 10;

        // Level up
        if (score % 1000 == 0) {
            level++;
            spawnEnemies(1);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw road markings
        g2d.setColor(Color.YELLOW);
        g2d.setStroke(new BasicStroke(2));
        for (int i = 0; i < HEIGHT; i += 40) {
            g2d.drawLine(WIDTH / 2, i, WIDTH / 2, i + 20);
        }

        // Draw game objects
        player.draw(g2d);
        for (Enemy enemy : enemies) {
            enemy.draw(g2d);
        }
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(g2d);
        }

        // Draw HUD
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.drawString("Score: " + score, 20, 30);
        g2d.drawString("Level: " + level, 20, 60);

        // Draw game over message
        if (gameState == GameState.GAME_OVER) {
            g2d.setColor(new Color(0, 0, 0, 150));
            g2d.fillRect(0, 0, WIDTH, HEIGHT);
            g2d.setColor(Color.RED);
            g2d.setFont(new Font("Arial", Font.BOLD, 48));
            String gameOverText = "GAME OVER";
            FontMetrics fm = g2d.getFontMetrics();
            int x = (WIDTH - fm.stringWidth(gameOverText)) / 2;
            g2d.drawString(gameOverText, x, HEIGHT / 2);

            g2d.setFont(new Font("Arial", Font.PLAIN, 24));
            String scoreText = "Final Score: " + score;
            g2d.drawString(scoreText, (WIDTH - fm.stringWidth(scoreText)) / 2, HEIGHT / 2 + 50);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.moveLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_R && gameState == GameState.GAME_OVER) {
            initializeGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.stop();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
