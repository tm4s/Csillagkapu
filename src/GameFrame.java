import javax.swing.*;

/**
 * Megjelenitett ablak, egesz kerete
 */

public class GameFrame extends JFrame {

    public void setUp(String fileName) {
        Controller controller = Controller.getInstance();
        GameView gameView = GameView.getInstance();
        controller.setGameView(gameView);
        gameView.setController(controller);
        controller.loadMap(fileName);
        gameView.setSize(controller.getWidth(), controller.getHeight());
        add(gameView);
        setSize(gameView.getWidth(),gameView.getHeight());
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
