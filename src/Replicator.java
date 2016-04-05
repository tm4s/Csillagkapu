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

    private void replicatorMoveTo(Field field) {
        ownedField.setIsThereReplicator(false);
        ownedField = field;
        ownedField.setIsThereReplicator(true);
    }

    @Override
    public void moveTo(Field field) {
        replicatorMoveTo(field);
    }

    @Override
    public void moveTo(Scale scale) {
        replicatorMoveTo(scale);
    }

    @Override
    public void moveTo(Zpm zpm) {
        replicatorMoveTo(zpm);
    }

    @Override
    public void moveTo(Ravine ravine) {
        ownedField.setIsThereReplicator(false);
        ravine.setField(new EmptyField());
        dead = true;
    }
}