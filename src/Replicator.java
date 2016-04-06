/**
 * Created by danielkrausz on 03/04/16.
 */
public class Replicator extends Colonel {
    Replicator(Field field) {
        super(field, 0);
    }

    public void move() {
        tryMoveTo(RandomGenerator.generateOrientation());
    }

    private void replicatorMoveTo(Field field) {
        ownedField.setReplicator(null);
        ownedField = field;
        ownedField.setReplicator(this);
    }

    public void die() {
        ownedField.setReplicator(null);
        dead = true;
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
        ownedField.setReplicator(null);
        ravine.setField(new EmptyField());
        dead = true;
    }

    @Override
    public void teleportTo(Teleporter teleporter) {
        replicatorMoveTo(teleporter);
        tryMoveTo(teleporter.getOrientation());
    }
}