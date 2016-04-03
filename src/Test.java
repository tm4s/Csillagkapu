import java.io.IOException;
import java.util.Scanner;

/**
 * eddigi dolgok gyors tesztelesere
 */
public class Test {

	public static void main(String args[]) throws IOException {
		String fileName = "map01.csv";
		if (args.length == 1)
			fileName = args[0];
		Map map = new Map(fileName);
		Colonel colonel = new Colonel(map.getColonelStartingField());
		Replicator replicator = new Replicator(map.getFieldAt(new Coordinate(3, 10)));
		MapBasicView mapView = new MapBasicView(map, colonel, replicator);

		System.out.println("Controls: ");
		System.out.println("move: wasd");
		System.out.println("rotate: tfgh");
		System.out.println("shoot: qe");
		System.out.println("boxPickUp: k");
		System.out.println("boxPutDown: m");
		System.out.println("quit: quit");
		System.out.println("after one command hit ENTER");
		System.out.println();
		System.out.println("Map: ");
		System.out.println("Wall: #");
		System.out.println("SpecialWall: +");
		System.out.println("Colonel: AV<>");
		System.out.println("Box: B");
		System.out.println("Scale: S");
		System.out.println("Door: D");
		System.out.println("Portal: 0O");
		System.out.println("Ravine: R");
		System.out.println("ZPM: Z");
		System.out.println();

		mapView.printMap();

		boolean run = true;

		while (run) {
			// Ha az összegyűjtött zpmek száma megegyezik a pályán eredetileg
			// lévő zpmek számával a játék véget ér
			if (Zpm.getAllZpms() == colonel.getCollectedZpms()) {
				System.out.println("NO MORE ZPMS!!!!!");
				break;
			}
			if (colonel.isDead()) {
				System.out.println("RIP COLONEL :( ");
				break;
			}
			Scanner scan = new Scanner(System.in);
			String line = scan.nextLine().toLowerCase();
			if  (line.contains("quit")) {
				run = false;
				break;
			}
			for (int i = 0; i < line.length(); i++) {
				switch (line.charAt(i)) {
					case 'w':
						if (colonel.getOrientation() != Orientation.Type.NORTH) {
							colonel.rotateTo(Orientation.Type.NORTH);
						}
						else colonel.tryMoveTo(Orientation.Type.NORTH);
						break;
					case 's':
						if (colonel.getOrientation() != Orientation.Type.SOUTH) {
							colonel.rotateTo(Orientation.Type.SOUTH);
						}
						else colonel.tryMoveTo(Orientation.Type.SOUTH);
						break;
					case 'a':
						if (colonel.getOrientation() != Orientation.Type.WEST) {
							colonel.rotateTo(Orientation.Type.WEST);
						}
						else colonel.tryMoveTo(Orientation.Type.WEST);
						break;
					case 'd':
						if (colonel.getOrientation() != Orientation.Type.EAST) {
							colonel.rotateTo(Orientation.Type.EAST);
						}
						else colonel.tryMoveTo(Orientation.Type.EAST);
						break;
					case 't':
						colonel.rotateTo(Orientation.Type.NORTH);
						break;
					case 'k':
						colonel.tryBoxPickUp();
						break;
					case 'm':
						colonel.tryBoxPutDown();
						break;
					case 'q':
						colonel.shootTeleporter(Teleporter.Type.BLUE);
						break;
					case 'e':
						colonel.shootTeleporter(Teleporter.Type.ORANGE);
						break;
					default:
						break;
				}
				mapView.printMap();
			}

		}

		/*
		 * mapView.printMap();
		 * 
		 * colonel.shootTeleporter(Teleporter.Type.BLUE);
		 * colonel.rotateTo(Orientation.Type.SOUTH);
		 * colonel.shootTeleporter(Teleporter.Type.ORANGE);
		 * colonel.goTo(Orientation.Type.SOUTH);
		 * 
		 * mapView.printMap();
		 * 
		 * 
		 * colonel.goTo(Orientation.Type.NORTH); System.out.println(
		 * "Az ezredes eszak fele megy"); mapView.printMap();
		 * 
		 * colonel.goTo(Orientation.Type.NORTH); System.out.println(
		 * "Az ezredes eszak fele megy"); mapView.printMap();
		 * 
		 * colonel.tryBoxPicUp(); System.out.println(
		 * "Az ezredes megprobalja felvenni a dobozt"); mapView.printMap();
		 * 
		 * colonel.goTo(Orientation.Type.EAST); System.out.println(
		 * "Az ezredes kelet fele megy. "); mapView.printMap();
		 * 
		 * colonel.goTo(Orientation.Type.EAST); System.out.println(
		 * "Az ezredes kelet fele megy. "); mapView.printMap();
		 * 
		 * colonel.goTo(Orientation.Type.EAST); System.out.println(
		 * "Az ezredes kelet fele megy. "); mapView.printMap();
		 * 
		 * System.out.println("Az ezredes letszi a dobozt.");
		 * colonel.tryBoxPutDown(); mapView.printMap();
		 */
	}

}
