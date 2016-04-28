import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {

	public static void main(String args[]) {
		String fileName = "map01.csv";
		if (args.length == 1)
			fileName = args[0];
		final String finalFileName = fileName;
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				GameFrame game;
				try {
					game = new GameFrame(finalFileName);
					game.setVisible(true);
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

}
