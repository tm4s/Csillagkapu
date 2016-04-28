import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main extends JFrame {

	public Main() {
		setUp();
	}

	private void setUp() {
		String fileName = "map01.csv";
		Controller controller = new Controller();
		controller.loadMap(fileName);
		add(controller);
		setSize(controller.getWidth(),controller.getHeight());
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				Main game;
				try {
					game = new Main();
					game.setVisible(true);
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

}
