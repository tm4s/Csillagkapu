import java.util.Random;

/**
 * Random generator
 */
public class RandomGenerator {
    private static boolean isTest = false;
    private static int testCases[] = {0, 1, 2, 3};
    private static int index = 0;


    public static void setTest(boolean isTest) {
        this.isTest = isTest;
    }


    /**
     * Visszaadja, hogy milyen tavolsagban tortenjen a veletlenszeru generalas
     * @return
     */
    public static int generateDistance() {
        if (isTest) {
            if (index>testCases.length)
                index = 0;
            return testCases[index++]*10;
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
            if (index>testCases.length)
                index = 0;
            return Orientation.Type.values()[testCases[index++]];
        }
        return Orientation.Type.values()[value];
    }
}
