/**
 * Játékmotor
 */

public class GameEngine {
    private Map map;
    private Colonel colonel;

    public  GameEngine() {
        map = new Map("mapName");
        colonel = new Colonel(map.getColonelPosition());
    }

    public void moveColonelTo(Orientation.Type orientation) {
        Coordinate destination = Orientation.getCoordinate(orientation).add(colonel.getPosition());
        colonel.moveTo(map.getFieldAt(destination), destination);
    }

}
