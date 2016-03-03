/**
 * Ezredes
 */

public class Colonel{

    private Coordinate position;
    private Coordinate orientation;

    private Scale ownedScale = null;
    private Box ownedBox = null;
    private boolean tryBoxPicking = false;


    public Colonel(Coordinate position) {
        position = new Coordinate(position);
        orientation = Orientation.getCoordinate(Orientation.Type.NORTH);
    }

    public void collideWith(Field field) {
        field.collideWith(this);
    }

    public void moveTo(EmptyField emptyField) {
        position = new Coordinate(emptyField.getPosition());
        if (ownedScale != null) {
            ownedScale.removeWeight();
            ownedScale = null;
        }
    }

    public void moveTo(Scale scale) {
        position = new Coordinate((scale.getPosition()));
        scale.addWeight();
        this.ownedScale = scale;
    }

    public void pickUpBox(Box box) {
        if (tryBoxPicking) {
            this.ownedBox = box;
            tryBoxPicking = false;
        }
    }

    public boolean pickUpBox(Field field){
        if (this.ownedBox != null) {
            return false;
        }
        tryBoxPicking = true;
        field.collideWith(this);
        if (!tryBoxPicking) {
            return true;
        }
        tryBoxPicking = false;
        return false;

    }

    public Coordinate getPosition() {
        return position;
    }
    public  Coordinate getFrontFieldPosition() {
        return position.add(orientation);
    }
}
