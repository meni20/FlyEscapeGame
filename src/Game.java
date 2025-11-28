import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
//hello
public class Game extends JPanel {
    private Player player;
    private final ArrayList<Enemy> enemies;
    public static final int ENEMIES_COUNT = 20;
    private Window window;
     Image backgroundImage;
     Image movingPlain;
    private boolean running = false;
    private int enemiesPassed;
    public void startmainGameLoop() {
        running = true;
        new Thread(() -> {
            while (running) {
                for (Enemy enemy : this.enemies) {
                    enemy.moveLeft();
                }
                for (Enemy enemy : this.enemies) {
                    if (collision(this.player.getRectangleSize(), enemy.getRectangleSize()) || collision(this.player.getRectangleSize(), enemy.getRectangles2Size())) {
                        gameOver();
                        running = false;
                        break;
                    }
                }
                // Check if any enemy has moved off the screen
                for (int i = 0; i < enemies.size(); i++) {
                    if (enemies.get(i).getRectangleSize().getX() < -enemies.get(i).getRectangleSize().getWidth()) {
                        enemies.remove(i);
                        enemiesPassed++;
                        i--;
                    }
                }
                repaint();
                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    Game(int WIDTH, int HEIGHT, Window window) {
        this.enemiesPassed = 0;
        this.window = window;
        backgroundImage = new ImageIcon("Images/background.gif").getImage();
        movingPlain = new ImageIcon("C:\\Users\\User\\Downloads\\w s keyboard.jpg").getImage();
        this.setSize(WIDTH, HEIGHT);
        this.setLayout(null);
        this.enemies = new ArrayList<>();
        int num = 0;
        for (int i = 0; i < ENEMIES_COUNT; i++) {
            int Random = ((int) (Math.random() * 730));
            Enemy enemy = new Enemy((WIDTH - 100 + num), 0, 50, Random, HEIGHT);
            num += 500;
            this.enemies.add(enemy);
        }

        JButton exit = new JButton("back");
        exit.setBounds(0, 0, 60, 40);
        this.add(exit);
        this.player = new Player(200, 100, 20, 20);
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.requestFocus();

        this.addKeyListener(new Controller(this.player, this));
       // this.setVisible(false);         // פההההה פה
        exit.addActionListener(e -> {
            this.setVisible(false);
            running = false;

            resetGame();
        });
    }

    // hello my name is beni
    public boolean collision(Rectangle player, Rectangle enemy) {
        return player.intersects(enemy);
    }
    public void gameOver() {
        SwingUtilities.invokeLater(() -> {
            String[] responses = {"Back to the home page", "Continue for 200 coins"};
            ImageIcon icon = new ImageIcon("C:\\Users\\User\\Pictures\\Screenshots\\צילום מסך 2024-07-08 181115.png");
            Image newImg = icon.getImage().getScaledInstance(80,80, Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);

            JLabel messageLabel = new JLabel("<html><body><div style='text-align: center;'>Game over!<br/>You passed " + enemiesPassed + " enemies.</div></body></html>");
            messageLabel.setFont(new Font("Arial", Font.BOLD, 25));

            int anserDialog = JOptionPane.showOptionDialog(null,
                    messageLabel,
                    "Game over!",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    icon,
                    responses,
                    0);
            if (anserDialog == 0){
                resetGame();
            } else if (anserDialog == 1){
                running = true;
                startmainGameLoop();
            }
        });
    }
    public void resetGame(){
        this.enemies.clear();
        int num = 0;
        for (int i = 0; i < ENEMIES_COUNT; i++) {
            int random = ((int) (Math.random() * 730));
            Enemy enemy = new Enemy((getWidth() - 100 + num), 0, 50, random, getHeight());
            num += 500;
            this.enemies.add(enemy);
        }
        this.player = new Player(200, 100, 20, 20);
        this.enemiesPassed = 0;
        this.setVisible(false);
        window.startbuttonVisible();
    }
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        for (Enemy enemy : this.enemies) {
            enemy.paint(graphics);
        }
        graphics.drawImage(movingPlain, 17,680,200,100,this);
        this.player.paint(graphics);
        ///
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Arial", Font.BOLD, 20));
        graphics.drawString("Enemies Passed: " + enemiesPassed, 250, 30);
    }
}
