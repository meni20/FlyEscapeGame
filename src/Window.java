import javax.swing.*;
import java.awt.*;
public class Window extends JFrame {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int WIDTH = screenSize.width;
    private final int HEIGHT = screenSize.height;
    private final Menu menu = new Menu(0,0, WIDTH/8, HEIGHT);
    Game game;
    JButton startButton;
    public Window (){
        game = new Game(WIDTH,HEIGHT, this);
        JButton menuButton = new JButton("-");
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
