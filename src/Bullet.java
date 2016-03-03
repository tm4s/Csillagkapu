/**
 * Lövedék
 */

public class Bullet {


    private Coordinate position;
    private Coordinate direction;
    private Map map;
    private Teleporter.Type type;

    public Bullet(Teleporter.Type type, Coordinate startPosition, Coordinate direction, Map map) {
        this.type = type;
        position = new Coordinate(startPosition);
        this.direction = direction;
        this.map = map;
    }

    public void moveForward(){
        position = new Coordinate(position.add(direction));
        map.getFieldAt(position).collideWith(this);
    }

    public void transformToTeleporter(){
        map.createTeleporter(type, position);
    }
}
