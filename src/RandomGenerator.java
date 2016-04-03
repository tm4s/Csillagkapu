import java.util.Random;

/**
 * Random generator
 */


public class RandomGenerator {
    static boolean isTest = false;
    static int testCases[] = {0, 1, 2, 3};
    private static int index = 0;

    static int generateDistance() {
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

    static Orientation.Type generateOrientation() {
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
