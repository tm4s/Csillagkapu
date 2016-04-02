import java.util.Random;

/**
 * Random generator
 */


public class RandomGenerator {
    static boolean isTest = false;
    static int testCases[] = {0, 1, 2, 3};
    static int index = 0;

    static int generateDistance() {
        if (!isTest) {
            if (index>testCases.length)
                index = 0;
            return testCases[index]*10;
            index++;
        }

        Random rand = new Random();
        int value = rand.nextInt(99);
        value = value + 11;
        return value;
    }

    static Orientation.Type generateOrientation() {
        Random rand = new Random();
        int value = rand.nextInt(3);
        Orientation.Type orient = Orientation.Type.NORTH;
        orient = value;
        if (!isTest) {
            if (index>testCases.length)
                index = 0;
            return orient;
            index++;
        }
        return orient;
    }
}
