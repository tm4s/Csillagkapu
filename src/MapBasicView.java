/**
 * teszteleshez kezeli a map megjeleniteset
 */

public class MapBasicView {
    private Map map;
    private Colonel colonel;

    public MapBasicView(Map map, Colonel colonel) {
        this.map = map;
        this.colonel = colonel;
    }

    public void printMap() {
        for (int y = 0; y < map.getHeight(); ++y){
            for (int x = 0; x < map.getWidth(); ++x){
                if (colonel.getPosition().equals(new Coordinate(y,x))){
                    System.out.print(printColonel());
                } else {
                    System.out.print(map.getFieldAt(new Coordinate(y, x)).print());
                }
                //System.out.print(' ');
            }
            System.out.println();
            //System.out.println();
        }
        System.out.println();
        System.out.println("ZPM = " +  colonel.getCollectedZpms() + " / " + map.getAllZpms());
        System.out.println();
    }

    private Character printColonel(){
        Character c = 'A';
        switch (Orientation.getOrientationType(colonel.getOrientation())) {
            case NORTH:
                c = 'A';
                break;
            case WEST:
                c = '<';
                break;
            case SOUTH:
                c = 'V';
                break;
            case EAST:
                c = '>';
                break;
        }
        return c;
    }
}
