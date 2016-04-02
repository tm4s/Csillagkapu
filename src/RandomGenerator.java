import java.util.Random;

/**
 * Random generator
 */


public class RandomGenerator {
    static boolean isTest = false;
    static int alma[] = {0, 1, 2, 3};

    static int generateDistance() {
        if (!isTest) {
            
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
        orient.ordinal() = value;
        return orient;
    }
}
