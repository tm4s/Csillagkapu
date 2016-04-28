import javax.swing.*;

/**
 * Megjelenitett ablak, egesz kerete
 */

public class GameFrame extends JFrame {
    public GameFrame(String fileName) {
        setUp(fileName);
    }

    private void setUp(String fileName) {
        Controller controller = new Controller();
        controller.loadMap(fileName);
        add(controller);
        setSize(controller.getWidth(),controller.getHeight());
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
