/**
 * Játékmotor
 */

public class GameEngine {
    private Map map;
    private Colonel colonel;

    public  GameEngine() {
        map = new Map("mapName");
        colonel = map.getColonelStart();
    }

    public void moveColonelTo(Coordinate vecDst) {
        Coordinate posDst = vecDst.add(colonel.getPosition());
        colonel.collideWith(map.getFieldAt(posDst));
    }

}
