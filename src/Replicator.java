/**
 * Created by danielkrausz on 03/04/16.
 */
public class Replicator extends Colonel {
    void Move() {
        tryMoveTo(RandomGenerator.generateOrientation());
    }
}
