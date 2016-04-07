import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Teszt kiertekelese
 */
public class Validator {
    private static String goToLine(Scanner scanner, char c) {
        String res = "";
        res = scanner.nextLine();
        while (scanner.hasNextLine() && res.toCharArray()[0] != c)
            res = scanner.nextLine();
        return res;
    }

    private static String readUntilChar(Scanner scanner, String data, char c) {
        String res = scanner.nextLine();
        while (scanner.hasNextLine() && res.toCharArray()[0] != c) {
            data += res;
            res = scanner.nextLine();
        }
        return res;
    }

    public static void main(String args[]) {
        if (args.length != 2) {
            System.out.print("Error expected 2 arguments");
            return;
        }
        String expectedFileName = args[0];
        String realFileName = args[1];

        try {
            Scanner expectedFile = new Scanner(new File(expectedFileName));
            Scanner realFile = new Scanner(new File(realFileName));

            String lastTitel = goToLine(expectedFile, '/');
            while (realFile.hasNextLine() && expectedFile.hasNextLine()) {
                String actualTitle = lastTitel;
                goToLine(realFile, '-');
                String expectedMap = "";
                lastTitel = readUntilChar(expectedFile, expectedMap, '/');
                String realMap = "";
                readUntilChar(realFile, realMap, '-');
                if (!realMap.equals(expectedMap)){
                    System.out.println();
                    System.out.println(actualTitle);
                    System.out.println(realMap);
                    System.out.println();
                    System.out.println(expectedMap);
                }
            }
        } catch (IOException ex) {
            System.out.println("Error while reading files.. :(");
        }
    }
}
