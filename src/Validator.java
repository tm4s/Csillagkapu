import java.io.File;
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
        if (!scanner.hasNextLine())
            return data;
        String res = scanner.nextLine();
        while (scanner.hasNextLine() && res.toCharArray()[0] != c) {
            data += "\n";
            data += res;
            res = scanner.nextLine();
            while (scanner.hasNextLine() && res.equals("")) {
                data += "\n";
                data+=res;
                res = scanner.nextLine();
            }
        }
        return data;
    }

    private static void printTitle(String title){
        System.out.println(title.substring(2));
    }

    private static void printMap(String map){
        System.out.println(map.substring(1));
    }

    public static void main(String args[]) {
        if (args.length != 2) {
            System.out.println("Error expected 2 arguments");
            return;
        }
        String expectedFileName = args[0];
        String realFileName = args[1];

        try {
            Scanner expectedFile = new Scanner(new File(expectedFileName));
            Scanner realFile = new Scanner(new File(realFileName));

            String lastTitle = goToLine(expectedFile, '/');
            System.out.println();
            printTitle(lastTitle);
            System.out.println();
            lastTitle = goToLine(expectedFile, '/');
            goToLine(realFile, '-');

            while (realFile.hasNextLine() && expectedFile.hasNextLine()) {
                String actualTitle = lastTitle;
                String expectedMap= readUntilChar(expectedFile, '/');
                lastTitle = expectedFile.nextLine();
                String realMap = readUntilChar(realFile, '-');
                readUntilChar(realFile, '-');
                printTitle(actualTitle);
                if (!realMap.equals(expectedMap)){
                    System.out.println("Failed :(");
                    System.out.println();
                    System.out.println("Output:");
                    System.out.println("-------------------------------------");
                    printMap(realMap);
                    System.out.println("-------------------------------------\n");
                    System.out.println("Expected output:");
                    System.out.println("-------------------------------------");
                    printMap(expectedMap);
                    System.out.println("-------------------------------------");
                } else {
                    System.out.println("Passed :)");
                }
                System.out.println("=====================================");
                System.out.println();
            }
        } catch (IOException ex) {
            System.out.println("Error while reading files.. :(");
        }
    }
}
