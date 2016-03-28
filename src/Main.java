import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A kulonbozo use-case-ek kozotti valasztasi lehetoseget felkinalo
 * es ezeket futtato menu
 */
public class Main {

	public static void main(String args[]) throws IOException {

		boolean run = true;

		printMenu();

		BufferedReader br = null;
		br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";

		while (run && (line = br.readLine()) != null) {
			switch (line) {
				case "1":
					case1();
					break;
				case "2":
					case2();
					break;
				case "3":
					case3();
					break;
				case "4":
					case4();
					break;
				case "5":
					case5();
					break;
				case "6":
					case6();
					break;
				case "7":
					case7();
					break;
				case "8":
					case8();
					break;
				case "9":
					case9();
					break;
				case "10":
					case10();
					break;
				case "11":
					case11();
					break;
				case "12":
					case12();
					break;
				case "13":
					case13();
					break;
				case "14":
					case14();
					break;
				case "15":
					case15();
					break;
				case "16":
					case16();
					break;
				case "17":
					case17();
					break;
				case "h":
					printMenu();
					break;
				case "q":
					run = false;
					quit();
					break;
				default:
					System.out.println("Ervenyes esetet adjon meg!");
					break;
			}
		}

		br.close();
	}


	private static void case1() {
		System.out.println("\n1. Ures mezore lepes [C_]\n");
		Logger.setIsOn(false);
		Field field1 = new EmptyField();
		Field field2 = new EmptyField();
		field2.setNextField(Orientation.Type.EAST, field1);
		Colonel colonel = new Colonel(field2);
		Logger.setIsOn(true);
		colonel.tryMoveTo(Orientation.Type.EAST);
	}

	private static void case2() {
		System.out.println("\n2. Ezredes utkozese");
		for (int n = 1; n < 5; ++n) {
			System.out.println();
			Logger.setIsOn(false);
			Field field1 = null;
			switch (n) {
				case 1:
					System.out.println("\n2.1. Ezredes utkozese fallal [C#]\n");
					field1 = new Wall();
					break;
				case 2:
					System.out.println("\n2.2. Ezredes utkozese specialis fallal [C+]\n");
					field1 = new SpecialWall();
					break;
				case 3:
					System.out.println("\n2.3. Ezredes utkozese dobozzal [CB]\n");
					field1 = new Box();
					break;
				case 4:
					System.out.println("\n2.4. Ezredes utkozese ajtoval [CD]\n");
					field1 = new Door();
					break;
				default:
					break;
			}
			Field field2 = new EmptyField();
			field2.setNextField(Orientation.Type.EAST, field1);
			Colonel colonel = new Colonel(field2);
			Logger.setIsOn(true);
			colonel.tryMoveTo(Orientation.Type.EAST);
		}
	}

	private static void case3() {
		System.out.println("\n3. Merlegre fel- es lelepes [DCS]\n");
		Logger.setIsOn(false);
		Field door = new Door();
		Field field = new EmptyField();
		Field scale = new Scale((Door) door);
		field.setNextField(Orientation.Type.EAST, door);
		field.setNextField(Orientation.Type.WEST, scale);
		scale.setNextField(Orientation.Type.EAST, field);
		door.setNextField(Orientation.Type.WEST, field);
		Colonel colonel = new Colonel(field);
		Logger.setIsOn(true);
		colonel.tryMoveTo(Orientation.Type.WEST);
		colonel.tryMoveTo(Orientation.Type.EAST);
	}

	private static void case4() {
		System.out.println("\n4. Szakadekba lepes [CR]\n");
		Logger.setIsOn(false);
		Field field1 = new Ravine();
		Field field2 = new EmptyField();
		field2.setNextField(Orientation.Type.EAST, field1);
		Colonel colonel = new Colonel(field2);
		Logger.setIsOn(true);
		colonel.tryMoveTo(Orientation.Type.EAST);
		colonel.isDead();
	}

	private static void case5() {
		System.out.println("\n5. Csillagkapuba lepes [0C0]\n");
		Logger.setIsOn(false);
		Field field = new EmptyField();
		Teleporter teleporter2 = new Teleporter(Teleporter.Type.ORANGE, Orientation.Type.WEST);
		Teleporter teleporter1 = new Teleporter(Teleporter.Type.BLUE, Orientation.Type.EAST);
		field.setNextField(Orientation.Type.EAST, teleporter1);
		field.setNextField(Orientation.Type.WEST, teleporter2);
		Colonel colonel = new Colonel(field);
		teleporter2.setNextField(Orientation.Type.EAST, field);
		teleporter1.setNextField(Orientation.Type.WEST, field);
		Logger.setIsOn(true);
		colonel.tryMoveTo(Orientation.Type.EAST);
	}

	private static void case6() {
		System.out.println("\n6. ZPM begyujtese [CZ]\n");
		Logger.setIsOn(false);
		Field field = new EmptyField();
		Field zpm = new Zpm();
		field.setNextField(Orientation.Type.EAST, zpm);
		Colonel colonel = new Colonel(field);
		zpm.setNextField(Orientation.Type.WEST, field);
		Logger.setIsOn(true);
		colonel.tryMoveTo(Orientation.Type.EAST);
	}

	private static void case7() {
		System.out.println("\n7. Tolteny kilovese [C]\n");
		Logger.setIsOn(false);
		Field field1 = new EmptyField();
		Colonel colonel = new Colonel(field1);
		Logger.setIsOn(true);
		colonel.shootTeleporter(Teleporter.Type.BLUE);
	}

	private static void case8() {
		System.out.println("\n8. Tolteny utkozese");
		for (int i = 1; i <= 3; ++i) {
			Logger.setIsOn(false);
			Field field1 = new EmptyField();
			Field field2 = null;
			switch (i) {
				case 1:
					System.out.println("\n8.1. Tolteny utkozese fallal [CW]\n");
					field2 = new Wall();
					break;
				case 2:
					System.out.println("\n8.2. Tolteny utkozese ajtoval [CD]\n");
					field2 = new Door();
					break;
				case 3:
					System.out.println("\n8.3. Tolteny utkozese csillagkapuval [CO vagy C0]\n");
					field2 = new Teleporter(Teleporter.Type.BLUE, Orientation.Type.WEST);
					break;
				default:
					break;
			}

			field1.setNextField(Orientation.Type.EAST, field2);
			field2.setNextField(Orientation.Type.WEST, field1);
			Colonel colonel = new Colonel(field1);
			colonel.rotateTo(Orientation.Type.EAST);
			Logger.setIsOn(true);
			colonel.shootTeleporter(Teleporter.Type.BLUE);
		}
	}

	private static void case9() {
		System.out.println("\n9. Tolteny utkozese specialis fallal[C0]\n");
		Logger.setIsOn(false);
		Field field1 = new EmptyField();
		Field field2 = new SpecialWall();
		field1.setNextField(Orientation.Type.EAST, field2);
		field2.setNextField(Orientation.Type.WEST, field1);
		Colonel colonel = new Colonel(field1);
		colonel.rotateTo(Orientation.Type.EAST);
		Teleporter.teleporterReset();
		Logger.setIsOn(true);
		colonel.shootTeleporter(Teleporter.Type.BLUE);
	}

	private static void case10() {
		System.out.println("\n10. Tolteny utkozese ures mezovel, dobozzal, szakadekkal, merleggel, ZPM modullal [C_BRSZ]\n");
		Logger.setIsOn(false);
		Field field1 = new EmptyField();
		Field field2 = new EmptyField();
		Field field3 = new Box();
		Field field4 = new Ravine();
		Door door = new Door();
		Field field5 = new Scale(door);
		Field field6 = new Zpm();

		field1.setNextField(Orientation.Type.EAST, field2);
		field2.setNextField(Orientation.Type.WEST, field1);
		field2.setNextField(Orientation.Type.EAST, field3);
		field3.setNextField(Orientation.Type.WEST, field2);
		field3.setNextField(Orientation.Type.EAST, field4);
		field4.setNextField(Orientation.Type.WEST, field3);
		field4.setNextField(Orientation.Type.EAST, field5);
		field5.setNextField(Orientation.Type.WEST, field4);
		field5.setNextField(Orientation.Type.EAST, field6);
		field6.setNextField(Orientation.Type.WEST, field5);

		Colonel colonel = new Colonel(field1);
		colonel.rotateTo(Orientation.Type.EAST);
		Logger.setIsOn(true);
		colonel.shootTeleporter(Teleporter.Type.BLUE);
	}

	private static void case11() {
		System.out.println("\n11. Doboz lerakasa ures mezore [C_]\n");
		Logger.setIsOn(false);
		Field field = new EmptyField();
		Field box = new Box();
		box.setNextField(Orientation.Type.WEST, field);
		field.setNextField(Orientation.Type.EAST, box);
		Colonel colonel = new Colonel(field);
		colonel.rotateTo(Orientation.Type.EAST);
		colonel.boxPickUp((Box) box);
		Logger.setIsOn(true);
		colonel.tryBoxPutDown();
	}

	private static void case12() {
		System.out.println("\n12. Doboz lerakasa merlegre [CS]\n");
		Logger.setIsOn(false);
		Field field = new EmptyField();
		Field box = new Box();
		box.setNextField(Orientation.Type.WEST, field);
		field.setNextField(Orientation.Type.EAST, box);
		Colonel colonel = new Colonel(field);
		colonel.rotateTo(Orientation.Type.EAST);
		colonel.boxPickUp((Box) box);
		Field scale = new Scale(new Door());
		field.setNextField(Orientation.Type.EAST, scale);
		Logger.setIsOn(true);
		colonel.tryBoxPutDown();
	}

	private static void case13() {
		System.out.println("\n13. Doboz lerakasa szakadekba [CR]\n");
		Logger.setIsOn(false);
		Field field = new EmptyField();
		Field box = new Box();
		Field ravine = new Ravine();
		field.setNextField(Orientation.Type.EAST, box);
		box.setNextField(Orientation.Type.WEST, field);
		Colonel colonel = new Colonel(field);
		colonel.rotateTo(Orientation.Type.EAST);
		colonel.tryBoxPickUp();
		field.setNextField(Orientation.Type.EAST, ravine);
		ravine.setNextField(Orientation.Type.WEST, field);
		Logger.setIsOn(true);
		colonel.tryBoxPutDown();
	}

	private static void case14() {
		System.out.println("\n14. Sikertelen doboz lerakas");
		for (int i = 1; i < 6; ++i) {
			Logger.setIsOn(false);
			Field field = new EmptyField();
			Field box = new Box();
			box.setNextField(Orientation.Type.WEST, field);
			field.setNextField(Orientation.Type.EAST, box);
			Colonel colonel = new Colonel(field);
			colonel.rotateTo(Orientation.Type.EAST);
			colonel.boxPickUp((Box) box);
			switch (i) {
				case 1:
					System.out.println("\n14.1. Sikertelen doboz lerakas ajtora  [CD]\n");
					field.setNextField(Orientation.Type.EAST, new Door());
					break;
				case 2:
					System.out.println("\n14.2. Sikertelen doboz lerakas dobozra [CB]\n");
					field.setNextField(Orientation.Type.EAST, new Box());
					break;
				case 3:
					System.out.println("\n14.3. Sikertelen doboz lerakas falra [C#]\n");
					field.setNextField(Orientation.Type.EAST, new Wall());
					break;
				case 4:
					System.out.println("\n14.4. Sikertelen doboz lerakas specialis [C+]\n");
					field.setNextField(Orientation.Type.EAST, new SpecialWall());
					break;
				case 5:
					System.out.println("\n14.5. Sikertelen doboz lerakas ZPM modulra [CZ]\n");
					field.setNextField(Orientation.Type.EAST, new Zpm());
					break;
				default:
					break;
			}
			Logger.setIsOn(true);
			colonel.tryBoxPutDown();
		}
	}


	private static void case15() {
		System.out.println("\n15. Doboz felvetel ures mezorol [CB]\n");
		Logger.setIsOn(false);
		Field field = new EmptyField();
		Field box = new Box();

		field.setNextField(Orientation.Type.EAST, box);

		Colonel colonel = new Colonel(field);
		ColonelsHand hand = new ColonelsHand(colonel);
		colonel.rotateTo(Orientation.Type.EAST);
		Logger.setIsOn(true);
		colonel.tryBoxPickUp();

	}

	private static void case16() {
		System.out.println("\n16. Doboz felvetele merlegrol [CBD]\n");
		Logger.setIsOn(false);
		Field field = new EmptyField();
		Box box = new Box();
		Door door = new Door();
		Scale scale = new Scale(door);

		field.setNextField(Orientation.Type.EAST, box);
		scale.setNextField(Orientation.Type.EAST, door);
		scale.setNextField(Orientation.Type.WEST, field);
		door.setNextField(Orientation.Type.WEST, scale);
		box.setOwnedScale(scale);

		Colonel colonel = new Colonel(field);
		ColonelsHand hand = new ColonelsHand(colonel);
		colonel.rotateTo(Orientation.Type.EAST);
		Logger.setIsOn(true);
		colonel.tryBoxPickUp();
	}


	private static void case17() {
		System.out.println("\n17. Sikertelen doboz felvetel  [CDB#+_RZ]\n");
		for (int i = 1; i <= 7; ++i) {
			Logger.setIsOn(false);
			Field field1 = null;
			switch (i) {
				case 1:
					System.out.println("\n17.1. Sikertelen doboz felvetel ajtorol [CD]\n");
					field1 = new Door();
					break;
				case 2:
					System.out.println("\n17.2. Sikertelen doboz felvetel doobozrol [CB]\n");
					field1 = new Box();
					break;
				case 3:
					System.out.println("\n17.3. Sikertelen doboz felvetel falrol [C#]\n");
					field1 = new Wall();
					break;
				case 4:
					System.out.println("\n17.4. Sikertelen doboz felvetel specialis falrol [C+]\n");
					field1 = new SpecialWall();
					break;
				case 5:
					System.out.println("\n17.5. Sikertelen doboz felvetel ures mezorol [C_]\n");
					field1 = new EmptyField();
					break;
				case 6:
					System.out.println("\n17.6. Sikertelen doboz felvetel szakadekrol [CR]\n");
					field1 = new Ravine();
					break;
				case 7:
					System.out.println("\n17.7. Sikertelen doboz felvetel ZPM modulrol [CZ]\n");
					field1 = new Zpm();
					break;
				default:
					break;
			}
			Field field2 = new EmptyField();
			field2.setNextField(Orientation.Type.EAST, field1);
			field1.setNextField(Orientation.Type.WEST, field2);
			Colonel colonel = new Colonel(field2);
			ColonelsHand hand = new ColonelsHand(colonel);
			colonel.rotateTo(Orientation.Type.EAST);
			Logger.setIsOn(true);
			colonel.tryBoxPickUp();
		}

	}

	public static void printMenu() {

		System.out.println(Resources.initText());

		System.out.println(Resources.useCase());
	}

	public static void quit() {
		System.out.println();
		System.out.println(Resources.LZ());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}