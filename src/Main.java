import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Main {


	public static void main(String args[]) {
		case1();
		case2();

		case3();
		case4();
		case5();
		case6();
		case7();
		for (int i = 1; i <= 3; ++i) {
			System.out.println();
			case8(i);
		}
		case9();
		case10();
		case11();

		case12();

		case15();
		case16();

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
	
	private static void case3(){
		System.out.println("\n3. Merlegre fel- es lelepes [DCS]\n");
		Logger.setIsOn(false);
		Field door = new Door();
		Field field = new EmptyField();
		Field scale = new Scale((Door)door);
		field.setNextField(Orientation.Type.EAST, door);
		field.setNextField(Orientation.Type.WEST, scale);
		scale.setNextField(Orientation.Type.EAST, field);
		door.setNextField(Orientation.Type.WEST, field);
		Colonel colonel = new Colonel(field);
		Logger.setIsOn(true);
		colonel.tryMoveTo(Orientation.Type.WEST);
		colonel.tryMoveTo(Orientation.Type.EAST);
	}

	private static void case4(){
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
	
	private static void case5(){
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
	
	private static void case6(){
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
		Field field2 = new EmptyField();
		field1.setNextField(Orientation.Type.EAST, field2);
		field2.setNextField(Orientation.Type.WEST, field1);
		Colonel colonel = new Colonel(field1);
		colonel.rotateTo(Orientation.Type.EAST);
		Logger.setIsOn(true);
		colonel.shootTeleporter(Teleporter.Type.BLUE);
	}
	
	private static void case8(int n) {
		System.out.println("\n8. Tolteny utkozese");
		Logger.setIsOn(false);
		Field field1 = new EmptyField();
		Field field2 = null;
		switch(n) {
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
	
	private static void case9() {
		System.out.println("\n9. Tolteny utkozese specialis fallal[C0]\n");
		Logger.setIsOn(false);
		Field field1 = new EmptyField();
		Field field2 = new SpecialWall();
		field1.setNextField(Orientation.Type.EAST, field2);
		field2.setNextField(Orientation.Type.WEST, field1);
		Colonel colonel = new Colonel(field1);
		colonel.rotateTo(Orientation.Type.EAST);
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
		colonel.boxPickUp((Box)box);
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
		colonel.boxPickUp((Box)box);
		Field scale = new Scale(new Door());
		field.setNextField(Orientation.Type.EAST, scale);
		Logger.setIsOn(true);
		colonel.tryBoxPutDown();
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
		System.out.println("\n16. Doboz felvétele mérlegről [CBD]\n");
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

	String initText = "Az objektumokat a karakteres kepernyon a kovetkezok jelolik:\n" +
			"\tC: Colonel\n" +
			"\t_: EmptyField\n"+
			"\tB: Box\n"+
			"\tD: Door\n"+
			"\tS: Scale\n"+
			"\tR: Ravine\n"+
			"\t#: Wall\n"+
			"\t+: SpecialWall\n" +
			"\tZ: Zpm\n"+
			"\t0/O: Teleporter\n\n";

	String useCases =  "Adja meg a kert use-case szamat: \n" +
			"1. Ures mezore lepes [C_]\n" +
			"2. Ezredes utkozese\n" +
			"\t2.1.fallal [C#]\n" +
			"\t2.2 specialis fallal[C+]\n" +
			"\t2.3 dobozzal[CB]\n" +
			"\t2.4 ajtoval [CD]\n" +
			"3. Merlegre fel- es lelepes [DCS]\n" +
			"4. Szakadekba lepes [CR]\n" +
			"5. Csillagkapuba lepes [0CO]\n" +
			"6. ZPM begyujtese [CZ]\n" +
			"7. Tolteny kilovese [C]\n" +
			"8. Tolteny utkozese\n" +
			"\t8.1. fallal [C#]\n"+
			"\t8.2. ajtoval [CD]\n"+
			"\t8.3. csillagkapuval [C0]\n"+
			"9. Tolteny utkozese specialis fallal [C+]\n" +
			"10. Tolteny utkozese ures mezovel, dobozzal, szakadekkal, merleggel, ZPM modullal\n" +
			"11. Doboz lerakasa ures mezore [C_]\n" +
			"12. Doboz lerakasa merlegre [CS]\n" +
			"13. Doboz lerakasa szakadekba [CR]\n" +
			"14. Sikertelen doboz lerakasa\n" +
			"\t14.1 ajtora [CD]\n" +
			"\t14.2 dobozra [CB]\n" +
			"\t14.3 falra [C#]\n" +
			"\t14.4 specialis falra [C+]\n" +
			"\t14.5 ZPM modulra\n" +
			"15. Doboz felvetele ures mezorol [CB]\n" +
			"16. Doboz felvetele merlegrol [CBD]\n" +
			"17. Sikertelen doboz felvetel\n" +
			"\t17.1. ajtorol [CD]\n" +
			"\t17.2. dobozrol [CB]\n" +
			"\t17.3. falrol [C#]\n" +
			"\t17.4. specialis falrol [C+]\n" +
			"\t17.5. ures mezorol [C_]\n" +
			"\t17.6. szakadekrol [CR]\n" +
			"\t17.7. ZPM modulrol [CZ]\n";

/*
	public static void main	(String args[]) {
		string Output = { " Adja meg a kért use-case számát: \n" +
				"Üres mezőre lépés [C_]\n" +
				"Ezredes ütközése \n" +
				"fallal [C#]\n" +
				"speciális fallal[C+]\n" +
				"dobozzal[CB]\n" +
				"ajtóval [CD]\n" +
				"Mérlegre fel- és lelépés [DCS]\n" +
				"Szakadékba lépés [CR]\n" +
				"Csillagkapuba lépés [0CO]\n" +
				"ZPM begyűjtése [CZ]\n" +
				"Töltény kilövése [C]\n" +
				"Töltény ütközése fallal, ajtóval, csillagkapuval [C#]\n" +
				"Töltény ütközése speciális fallal [C0]\n" +
				"Töltény ütközése\n" +
				"üres mezővel [C_]\n" +
				"dobozzal [CB]\n" +
				"szakadékkal [CR]\n" +
				"mérleggel [CS]\n" +
				"ZPM modullal [CZ]\n" +
				"Doboz lerakása üres mezőre [C_]\n" +
				"Doboz lerakása mérlegre [CS]\n" +
				"Doboz lerakása szakadékba [CR]\n" +
				"Sikertelen doboz lerakás\n" +
				"ajtóra [CD]\n" +
				"dobozra [CB]\n" +
				"falra [C#]\n" +
				"speciális falra [C+]\n" +
				"ZPM modulra [CZ]\n" +
				"Doboz felvétele üres mezőről [CB]\n" +
				"Doboz felvétele mérlegről[CBD]\n" +
				"Sikertelen doboz felvétel\n" +
				"ajtóról [CD]\n" +
				"dobozról [CB]\n" +
				"falról [C#]\n" +
				"speciális falról [C+]\n" +
				"üres mezőről [C_]\n" +
				"szakadékról [CR]\n" +
				"ZPM modulról [CZ]\n"
		}

		BufferedReader br = null;
		br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";

		while((line = br.readLine()) != null) {
			switch(line) {
				case: "1.":
				System.out.println("Ures mezore lepes [C_]");
				Log("C_");
				break;
				case: "2.":
				System.out.println("Ezredes utkozese: ");
				System.out.println("fallal[C#]");
				Log("C#");
				System.out.println("Specialis fallal[C+]");
				Log("C+");
				System.out.println("dobozzal[CB]");
				Log("CB")
				System.out.println("ajtoval[CD]");
				Log("CD");
				break;
				case: "3.":

				break;
				case: "4.":
				break;
				case: "5.":
				case: "6.":
				case: "7.":
				case: "8.":
				case: "9.":
				case: "10.":
				case: "11.":
				case: "12.":
				case: "13.":
				case: "14.":
				case: "15.":
				case: "16.":
				case: "17.":

			}


		}

	}
*/


}