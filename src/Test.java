/**
 * eddigi dolgok gyors tesztelesere
 */
public class Test {

    public static void main(String args[]) {
        Map map = new Map("teszt");
        Colonel colonel = new Colonel(map);
        MapBasicView mapView = new MapBasicView(map, colonel);

        mapView.printMap();



        colonel.tryBoxPicUp();
        colonel.goTo(Orientation.Type.SOUTH);

        mapView.printMap();

        colonel.rotateTo(Orientation.Type.NORTH);
        colonel.tryBoxPutDown();

        mapView.printMap();
    }


}
