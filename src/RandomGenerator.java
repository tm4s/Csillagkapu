import java.util.Random;

/**
 * Random generator
 */


public class RandomGenerator {
    boolean isTest = false;
    int alma[] = {0, 1, 2, 3};

    static void generateDistance() {}

    static Orientation.Type generateOrientation() {
        Random rand = new Random();
        int value = rand.nextInt(3);
        Orientation.Type orient = Orientation.Type.NORTH;
        orient.ordinal() = value;
        return orient;
    }
}
