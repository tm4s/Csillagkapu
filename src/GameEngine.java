/**
 * Játékmotor
 */

public class GameEngine {
    private Map map;
    private Colonel colonel;

    public  GameEngine() {
        map = new Map("mapName");
        colonel = new Colonel(map);
    }



}
