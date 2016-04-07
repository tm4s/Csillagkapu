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
        while (scanner.hasNextLine() && res.toCharArray()[0] != c) {
            res = scanner.nextLine();
            while (scanner.hasNextLine() && res.equals(""))
                res = scanner.nextLine();
        }
        return res;
    }

    private static String readUntilChar(Scanner scanner, char c) {
        String data = "";
        String res = scanner.nextLine();
        while (scanner.hasNextLine() && res.toCharArray()[0] != c) {
            data += res;
            data += "\n";
            res = scanner.nextLine();
            while (scanner.hasNextLine() && res.equals("")) {
                data+=res;
                data += "\n";
                res = scanner.nextLine();
            }
        }
        return data;
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
            goToLine(realFile, '-');
            while (realFile.hasNextLine() && expectedFile.hasNextLine()) {
                String actualTitle = lastTitel;
                String expectedMap= readUntilChar(expectedFile, '/');
                lastTitel = expectedFile.nextLine();
                String realMap = readUntilChar(realFile, '-');
                System.out.println();
                System.out.println(actualTitle);
                if (!realMap.equals(expectedMap)){
                    System.out.println("Output:");
                    System.out.println(realMap);
                    System.out.println();
                    System.out.println("Expected output:");
                    System.out.println(expectedMap);
                } else {
                    System.out.println("Passed :)");
                }
            }
        } catch (IOException ex) {
            System.out.println("Error while reading files.. :(");
        }
    }
}
