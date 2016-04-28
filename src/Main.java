import javax.swing.*;

public class Main extends JFrame {

	public static void main(String args[]) {
		Game game = new Game();
		add(game);
		game.start();
		setSize(ScreenWidth, ScreenHeight);
		setTitle("BREAKOUT");
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		String fileName = "test\test01.csv";
		if (args.length == 1)
			fileName = args[0];
		Controller controller = new Controller();
        controller.loadMap(fileName);
        controller.run();
	}

}
