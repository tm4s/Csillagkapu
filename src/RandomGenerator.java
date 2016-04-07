import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Random generator
 */
public class RandomGenerator {
    private static boolean isTest = false;
    public static List<Integer> testCases = new ArrayList<Integer>();



    public static void setTest(boolean test) {
        isTest = test;
    }

    public static boolean getTest() {
        return isTest;
    }


    /**
     * Visszaadja, hogy milyen tavolsagban tortenjen a veletlenszeru generalas
     * @return
     */
    public static int generateDistance() {
        if (isTest) {
            return 1;
        }

        Random rand = new Random();
        int value = rand.nextInt(200);
        value = value + 11;
        return value;
    }

    /**
     * A 4 lehetseges irany kozul (eszak, del, kelet nyugat)  veletlenszeruen general egyet
     * es visszaadja azt
     * @return
     */
    public static Orientation.Type generateOrientation() {
        Random rand = new Random();
        int value = rand.nextInt(4);
        if (isTest) {
            Orientation.Type t;
            t = Orientation.Type.values()[testCases.get(0)];
            testCases.remove(0);
            return t;
        }
        return Orientation.Type.values()[value];
    }
}
