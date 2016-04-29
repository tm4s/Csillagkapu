/**
 * Jatek elinditasaert felelos osztaly.
 */

public class Main {

	public static void main(String args[]) {
		String fileName = "map01.csv";
		if (args.length == 1)
			fileName = args[0];
		GameFrame game = new GameFrame();
		game.setUp(fileName);
	}

}
