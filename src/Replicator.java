/**
 * Replikator
 */
public class Replicator extends Colonel {
    public Replicator(Field field) {
        super(field, 0);
        field.setReplicator(this);
        field.setThereAColonel(false);
    }

    /**
     * Replikator mozgatasa egy veletlenszruen generalt iranyba
     */
    public void move() {
        tryMoveTo(RandomGenerator.generateOrientation());
    }

    /**
     * A replikator a megadott mezore lep
     * @param field
     */
    private void replicatorMoveTo(Field field) {
        ownedField.setReplicator(null);
        ownedField = field;
        ownedField.setReplicator(this);
    }

    /**
     * A replikator
     */
    @Override
    public void die() {
        ownedField.setReplicator(null);
        isDead = true;
    }

    /**
     * Az ezredesbol orokolt mozgato fuggvenyek overrideolasa minden mezore
     * @param
     */
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

    /**
     * Az ezredesbol orokolt teleportalo fuggveny overrideolasa
     * @param teleporter erre a mezore teleportalunk
     */
    @Override
    public void teleportTo(Teleporter teleporter) {
        replicatorMoveTo(teleporter);
        tryMoveTo(teleporter.getOrientation());
    }
}