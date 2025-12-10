import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private final int WIDTH;
    private final int HEIGHT;

    private final Menu menu;
    private final Game game;

    JButton startButton;
    JButton menuButton;

    public Window (){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.WIDTH = screenSize.width;
        this.HEIGHT = screenSize.height;

        menu = new Menu(0,0, WIDTH/8, HEIGHT);
        game = new Game(WIDTH,HEIGHT, this);
        game.setBounds(0, 0, WIDTH, HEIGHT);
        game.setVisible(false);

        menuButton = new JButton("-");
        menuButton.setBounds(0,0,50,25);
        menuButton.setFocusable(false);
        menuButton.addActionListener(e -> {
            menu.setVisible(!menu.isVisible());
        });

        startButton = new JButton("Start");
        startButton.setBounds(WIDTH/3,HEIGHT/4,WIDTH/3,HEIGHT/8);
        startButton.setFocusable(false);
        startButton.addActionListener(e -> {
            game.setVisible(true);
            SwingUtilities.invokeLater(() -> game.requestFocusInWindow());
            game.startmainGameLoop();
            startbuttonVisible();
        });

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.add(game);
        this.add(menu);
        this.add(startButton);
        this.add(menuButton);
        this.setVisible(true);
    }
    public void startbuttonVisible(){
        startButton.setVisible(!game.isVisible());
    }

    public static void main(String[] args) {
        new Window();
    }
}
