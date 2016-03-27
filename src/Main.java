import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Main {


	public static void main(String args[]) {
		case1();

	}

	private static void case1() {
		Field field1 = new EmptyField();
		Field field2 = new EmptyField();
		field2.setNextField(Orientation.Type.NORTH, field1);
		Colonel colonel = new Colonel(field2);
		colonel.tryMoveTo(Orientation.Type.NORTH);
	}


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