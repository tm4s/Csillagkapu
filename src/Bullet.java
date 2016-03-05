/**
 * Lövedék
 */

public class Bullet {


    private Field ownedField;
    private Orientation.Type direction;
    private Teleporter.Type type;

    public Bullet(Teleporter.Type type, Field startField, Orientation.Type direction) {
        this.type = type;
        ownedField = startField;
        this.direction = direction;
    }

    public void moveForward(){
        ownedField.getNextField(direction).collideWith(this);
        ownedField = ownedField.getNextField(direction);
    }

    public void transformToTeleporter(){
        ownedField.getNextField(direction).setField(new Teleporter(type));
    }
}
