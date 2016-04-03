/**
 * Created by danielkrausz on 03/04/16.
 */
public class Replicator extends Colonel {
    Replicator(Field field) {
        super(field);
    }

    public void move() {
        tryMoveTo(RandomGenerator.generateOrientation());
    }
}
