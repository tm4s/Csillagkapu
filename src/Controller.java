import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    private Colonel colonel;
    private Colonel jaffa;
    private Replicator replicator;
    private Field firstField; //top left corner on map

    private void resetEverything() {
        colonel = new Colonel(new EmptyField(), 0);
        colonel.die();
        jaffa = new Colonel(new EmptyField(), 0);
        jaffa.die();
        replicator = new Replicator(new EmptyField());
        replicator.die();
        firstField = null;
    }

    public Controller() {
        resetEverything();
    }

    public void loadMap(String mapName) {
        resetEverything();
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(new File(mapName)));
            String line = "";

            // A palya meretenek beolvasasa
            line = br.readLine();
            String mapSize[] = line.split(";");
            int width = Integer.parseInt(mapSize[0]);
            int height = Integer.parseInt(mapSize[1]);

            // Uj ideiglenes palya letrehozasa
            Field[][] mapDatas = new Field[height][width];

            // merlegek es ajtok osszekapcsolasahoz szukseges ideiglenes adatok tarolasa
            class scaleAndDoorData {
                public String id;
                private int x;
                private int y;
                private int weight;

                public scaleAndDoorData(String id, int x, int y, int w) {
                    this.id = id;
                    this.x = x;
                    this.y = y;
                    weight = w;
                }

                public int getX() {
                    return x;
                }

                public int getY() {
                    return y;
                }

                public int getWeight() {
                    return weight;
                }

            }

            ArrayList<scaleAndDoorData> scaleDatas = new ArrayList<scaleAndDoorData>();
            ArrayList<scaleAndDoorData> doorDatas = new ArrayList<scaleAndDoorData>();

            // Mezok beolvasasa
            int j = 0;
            while ((line = br.readLine()) != null) {
                String array[] = line.split(";");
                for (int i = 0; i < width; i++) {
                    switch (array[i].charAt(0)) {
                        case ' ':
                            mapDatas[j][i] = new EmptyField();
                            break;
                        case '#':
                            mapDatas[j][i] = new Wall();
                            break;
                        case 'D':
                            mapDatas[j][i] = new Door();
                            String doorData[] = array[i].split("_");
                            doorDatas.add(new scaleAndDoorData(doorData[1], j, i, 0));
                            break;
                        case 'B':
                            mapDatas[j][i] = new Box(Integer.parseInt(array[i].split("_")[1]));
                            break;
                        case 'C':
                            int weight = Integer.parseInt(array[i].split("_")[1]);
                            mapDatas[j][i] = new EmptyField();
                            colonel = new Colonel(mapDatas[j][i], weight);
                            break;
                        case 'J':
                            weight = Integer.parseInt(array[i].split("_")[1]);
                            mapDatas[j][i] = new EmptyField();
                            jaffa = new Colonel(mapDatas[j][i], weight);
                            break;
                        case 'S':
                            String scaleData[] = array[i].split("_");
                            scaleDatas.add(new scaleAndDoorData(scaleData[1], j, i, Integer.parseInt(scaleData[2])));
                            break;
                        case '+':
                            mapDatas[j][i] = new SpecialWall();
                            break;
                        case 'Z':
                            mapDatas[j][i] = new Zpm();
                            break;
                        case 'R':
                            mapDatas[j][i] = new Ravine();
                            break;
                        default:
                            break;
                    }
                }
                j++;
            }
            br.close();

            // merlegek letrehozasa
            for (scaleAndDoorData scale : scaleDatas) {
                int i = 0;
                while (!doorDatas.get(i).id.equals(scale.id))
                    ++i;
                scaleAndDoorData door = doorDatas.remove(i);
                mapDatas[scale.getX()][scale.getY()] = new Scale((Door) mapDatas[door.getX()][door.getY()], scale.getWeight());
            }

            // mezok szomszedainak beallitasa
            for (int k = 0; k < height; ++k) {
                for (int i = 1; i < width - 1; ++i) {
                    mapDatas[k][i].setNextField(Orientation.Type.WEST, mapDatas[k][i - 1]);
                    mapDatas[k][i].setNextField(Orientation.Type.EAST, mapDatas[k][i + 1]);
                }
                mapDatas[k][0].setNextField(Orientation.Type.EAST, mapDatas[k][1]);
                mapDatas[k][width - 1].setNextField(Orientation.Type.WEST, mapDatas[k][width - 2]);
            }
            for (int i = 0; i < width; ++i) {
                for (int k = 1; k < height - 1; ++k) {
                    mapDatas[k][i].setNextField(Orientation.Type.NORTH, mapDatas[k - 1][i]);
                    mapDatas[k][i].setNextField(Orientation.Type.SOUTH, mapDatas[k + 1][i]);
                }
                mapDatas[0][i].setNextField(Orientation.Type.SOUTH, mapDatas[1][i]);
                mapDatas[height - 1][i].setNextField(Orientation.Type.NORTH, mapDatas[height - 2][i]);
            }

            firstField = mapDatas[0][0];


        } catch (IOException ex) {
            System.out.println("Error while loading the map.. :(");
        }
    }

    private void printColonel(Orientation.Type orientation) {
        Character c = 'A';
        switch (orientation) {
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
        System.out.print(c);
    }

    private void printJaffa(Orientation.Type orientation) {
        Character c = '↑';
        switch (orientation) {
            case NORTH:
                c = '↑';
                break;
            case WEST:
                c = '←';
                break;
            case SOUTH:
                c = '↓';
                break;
            case EAST:
                c = '→';
                break;
        }
        System.out.print(c);
    }

    private void printMap() {
        System.out.print("---------------------------------------\n");
        System.out.println();
        Field nextField = firstField;
        Field nextRowFirstField = firstField.getNextField(Orientation.Type.SOUTH);
        while (nextField != null) {
            if (nextField.isThereAColonel) {
                if (nextField.equals(colonel.getOwnedField()))
                    printColonel(colonel.getOrientation());
                if (nextField.equals(jaffa.getOwnedField()))
                    printJaffa(jaffa.getOrientation());
            }
            else
                System.out.print(nextField.print());
            if (nextField.getNextField(Orientation.Type.EAST) != null)
                nextField = nextField.getNextField(Orientation.Type.EAST);
            else {
                System.out.println();
                nextField = nextRowFirstField;
                if (nextField != null)
                    nextRowFirstField = nextField.getNextField(Orientation.Type.SOUTH);
            }
        }
        System.out.println();
        System.out.println("ZPMs left on the map: " + (Zpm.getAllZpms() - colonel.getCollectedZpms() - jaffa.getCollectedZpms()));
        System.out.println("ZPMs collected by the colonel: " + colonel.getCollectedZpms());
        System.out.println("ZPMs collected by Jaffa: " + jaffa.getCollectedZpms());
        System.out.println();
    }

    public void run() {
        System.out.println("Colonel controls: ");
        System.out.println("move/rotate: wasd");
        System.out.println("shoot: qe");
        System.out.println("boxPickUp: 2");
        System.out.println("boxPutDown: 3");
        System.out.println("quit: quit");
        System.out.println("after commands hit ENTER");
        System.out.println();
        System.out.println("Jaffa controls: ");
        System.out.println("move/rotate: ijkl");
        System.out.println("shoot: uo");
        System.out.println("boxPickUp: 8");
        System.out.println("boxPutDown: 9");
        System.out.println("quit: quit");
        System.out.println("after commands hit ENTER");
        System.out.println();
        System.out.println("Map: ");
        System.out.println("Wall: #");
        System.out.println("SpecialWall: +");
        System.out.println("Colonel: AV<>");
        System.out.println("Box: B");
        System.out.println("Scale: S");
        System.out.println("Door: D");
        System.out.println("Portal: 0O");
        System.out.println("Ravine: R");
        System.out.println("ZPM: Z");
        System.out.println();

        printMap();

        boolean run = true;
        boolean colonelAlreadyDead = false;
        boolean jaffaAlreadyDead = false;

        while (run) {
            if (Zpm.getAllZpms() == (colonel.getCollectedZpms() + jaffa.getCollectedZpms())) {
                System.out.println("NO MORE ZPMS!!!!!");
                break;
            }
            if (colonel.isDead() && !colonelAlreadyDead) {
                System.out.println("RIP COLONEL :( ");
                colonelAlreadyDead = true;
            }
            if (jaffa.isDead() && !jaffaAlreadyDead) {
                System.out.println("RIP JAFFA :( ");
                jaffaAlreadyDead = true;
            }
            Scanner scan = new Scanner(System.in);
            if (!scan.hasNextLine()) {
                run = false;
                break;
            }

            String line = scan.nextLine().toLowerCase();
            if  (line.contains("quit")) {
                run = false;
                break;
            }
            for (int i = 0; i < line.length(); i++) {
                if (!colonel.isDead()) {
                    switch (line.charAt(i)) {
                        case 'w':
                            if (colonel.getOrientation() != Orientation.Type.NORTH) {
                                colonel.rotateTo(Orientation.Type.NORTH);
                            } else colonel.tryMoveTo(Orientation.Type.NORTH);
                            break;
                        case 's':
                            if (colonel.getOrientation() != Orientation.Type.SOUTH) {
                                colonel.rotateTo(Orientation.Type.SOUTH);
                            } else colonel.tryMoveTo(Orientation.Type.SOUTH);
                            break;
                        case 'a':
                            if (colonel.getOrientation() != Orientation.Type.WEST) {
                                colonel.rotateTo(Orientation.Type.WEST);
                            } else colonel.tryMoveTo(Orientation.Type.WEST);
                            break;
                        case 'd':
                            if (colonel.getOrientation() != Orientation.Type.EAST) {
                                colonel.rotateTo(Orientation.Type.EAST);
                            } else colonel.tryMoveTo(Orientation.Type.EAST);
                            break;
                        case '2':
                            colonel.tryBoxPickUp();
                            break;
                        case '3':
                            colonel.tryBoxPutDown();
                            break;
                        case 'q':
                            colonel.shootTeleporter(Teleporter.Type.BLUE);
                            break;
                        case 'e':
                            colonel.shootTeleporter(Teleporter.Type.ORANGE);
                            break;
                        default:
                            break;
                    }
                }
                if (!jaffa.isDead()) {
                    switch (line.charAt(i)) {
                        case 'i':
                            if (jaffa.getOrientation() != Orientation.Type.NORTH) {
                                jaffa.rotateTo(Orientation.Type.NORTH);
                            } else jaffa.tryMoveTo(Orientation.Type.NORTH);
                            break;
                        case 'k':
                            if (jaffa.getOrientation() != Orientation.Type.SOUTH) {
                                jaffa.rotateTo(Orientation.Type.SOUTH);
                            } else jaffa.tryMoveTo(Orientation.Type.SOUTH);
                            break;
                        case 'j':
                            if (jaffa.getOrientation() != Orientation.Type.WEST) {
                                jaffa.rotateTo(Orientation.Type.WEST);
                            } else jaffa.tryMoveTo(Orientation.Type.WEST);
                            break;
                        case 'l':
                            if (jaffa.getOrientation() != Orientation.Type.EAST) {
                                jaffa.rotateTo(Orientation.Type.EAST);
                            } else jaffa.tryMoveTo(Orientation.Type.EAST);
                            break;
                        case '8':
                            jaffa.tryBoxPickUp();
                            break;
                        case '9':
                            jaffa.tryBoxPutDown();
                            break;
                        case 'u':
                            jaffa.shootTeleporter(Teleporter.Type.GREEN);
                            break;
                        case 'o':
                            jaffa.shootTeleporter(Teleporter.Type.RED);
                            break;
                        default:
                            break;
                    }
                }
            }
            printMap();
        }
    }
}
