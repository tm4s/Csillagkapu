import java.io.IOException;
import java.util.Scanner;

/**
 * eddigi dolgok gyors tesztelesere
 */
public class Main {


    public static void main(String args[]) throws IOException {
        Map map = new Map("map01.csv");
        Colonel colonel = new Colonel(map);
        MapBasicView mapView = new MapBasicView(map, colonel);

        mapView.printMap();

        boolean run = true;

        while (run) {
            Scanner scan = new Scanner(System.in);
            switch (scan.nextLine()) {
                case "w":
                    colonel.goTo(Orientation.Type.NORTH);
                    break;
                case "s":
                    colonel.goTo(Orientation.Type.SOUTH);
                    break;
                case "a":
                    colonel.goTo(Orientation.Type.WEST);
                    break;
                case "d":
                    colonel.goTo(Orientation.Type.EAST);
                    break;
                case "t":
                    colonel.rotateTo(Orientation.Type.NORTH);
                    break;
                case "g":
                    colonel.rotateTo(Orientation.Type.SOUTH);
                    break;
                case "f":
                    colonel.rotateTo(Orientation.Type.WEST);
                    break;
                case "h":
                    colonel.rotateTo(Orientation.Type.EAST);
                    break;
                case "k":
                    colonel.tryBoxPickUp();
                    break;
                case "m":
                    colonel.tryBoxPutDown();
                    break;
                case "q":
                    colonel.shootTeleporter(Teleporter.Type.BLUE);
                    break;
                case "e":
                    colonel.shootTeleporter(Teleporter.Type.ORANGE);
                    break;
                case "quit":
                    run = false;
                    break;
                default:
                    break;

            }
            mapView.printMap();
        }




        /*
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
        */
    }


}
