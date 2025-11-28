import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Controller implements KeyListener {
    private Player player;
    private Game game;
    public Controller(Player player, Game game) {
        this.player = player;
        this.game = game;
    }
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("OOOO");
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                System.out.println("WW");
                this.player.moveUp();
                break;
            case KeyEvent.VK_S:
                System.out.println("sss");
                this.player.moveDown();
                break;
        }
        this.game.repaint();
    }

    public void keyReleased(KeyEvent e) {
    }
}
