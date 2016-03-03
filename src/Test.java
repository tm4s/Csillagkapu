import java.io.IOException;

/**
 * eddigi dolgok gyors tesztelesere
 */
public class Test {

    public static void main(String args[]) throws IOException {
        Map map = new Map("map01.csv");
        Colonel colonel = new Colonel(map);
        MapBasicView mapView = new MapBasicView(map, colonel);

        mapView.printMap();

        colonel.shootTeleporter(Teleporter.Type.BLUE);
        colonel.rotateTo(Orientation.Type.SOUTH);
        colonel.shootTeleporter(Teleporter.Type.ORANGE);
        colonel.goTo(Orientation.Type.SOUTH);

        mapView.printMap();


        colonel.goTo(Orientation.Type.NORTH);
        System.out.println("Az ezredes eszak fele megy");
        mapView.printMap();

        colonel.goTo(Orientation.Type.NORTH);
        System.out.println("Az ezredes eszak fele megy");
        mapView.printMap();

        colonel.tryBoxPicUp();
        System.out.println("Az ezredes megprobalja felvenni a dobozt");
        mapView.printMap();

        colonel.goTo(Orientation.Type.EAST);
        System.out.println("Az ezredes kelet fele megy. ");
        mapView.printMap();

        colonel.goTo(Orientation.Type.EAST);
        System.out.println("Az ezredes kelet fele megy. ");
        mapView.printMap();

        colonel.goTo(Orientation.Type.EAST);
        System.out.println("Az ezredes kelet fele megy. ");
        mapView.printMap();

        System.out.println("Az ezredes letszi a dobozt.");
        colonel.tryBoxPutDown();
        mapView.printMap();
    }


}
