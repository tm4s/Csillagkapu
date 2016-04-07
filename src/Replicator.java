/**
 * Replikator
 */
public class Replicator extends Colonel {
    public Replicator(Field field) {
        super(field, 0);
        field.setReplicator(this);
        field.setThereAColonel(false);
    }

    public void move() {
        tryMoveTo(RandomGenerator.generateOrientation());
    }

    private void replicatorMoveTo(Field field) {
        ownedField.setReplicator(null);
        ownedField = field;
        ownedField.setReplicator(this);
    }

    @Override
    public void die() {
        ownedField.setReplicator(null);
        isDead = true;
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
        ravine.setField(new EmptyField());
        die();
    }

    @Override
    public void teleportTo(Teleporter teleporter) {
        replicatorMoveTo(teleporter);
        tryMoveTo(teleporter.getOrientation());
    }
}