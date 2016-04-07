public class Main {

	public static void main(String args[]) {
		String fileName = "test_replicator.csv";
		if (args.length == 1)
			fileName = args[0];
		Controller controller = new Controller();
        controller.loadMap(fileName);
        controller.run();
	}

}
