/**
 * Az osztaay felelossege egy adott palyan a jatek vezerlese.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Controller {
    /**
     * A jatek szereploinek tipusai.
     * A megjelenitest vegzo osztalynak ilyen formaban van atadva az informacio.
     */
    public enum PersonType {
        COLONEL,
        JAFFA,
        REPLICATOR
    }

    /**
     * A palya mezoinek tipusai.
     * A megjelenitest vegzo osztalynak ilyen formaban van atadva az informacio.
     */
    public enum FieldType {
        EMPTY_FIELD,
        ZPM,
        SCALE,
        BOX,
        DOOR,
        WALL,
        SPECIAL_WALL,
        RAVINE,
        PORTAL_ORANGE,
        PORTAL_BLUE,
        PORTAL_GREEN,
        PORTAL_RED,
    }

    /**
     * A jatek lehetseges allapotai.
     */
    public enum GameState {
        GAME,
        MENU
    }

    /**
     * Referencia az egyetlen letezo peldanyra.
     */
    private static Controller instance = null;

    /**
     * Ennek a fugveny segitsegevel hozhato letre referencia az osztaly egyetlen peldanyara.
     * @return refencia az egyetlen peldanyra
     */
    public static Controller getInstance() {
        if (instance == null)
            instance = new Controller();
        return instance;
    }

    /**
     * Referencia a megjelenitest vegzo osztalyra.
     * A tobbi class csak a controlleren keresztul ferhet hozza a megjelenitest vegzo osztalyhoz.
     */
    private GameView gameView;

    /**
     * Aktiv szereplok a palyan.
     */
    private Colonel colonel;
    private Colonel jaffa;
    private Replicator replicator;

    /**
     * A palya bal felso sarkaban levo mezo.
     */
    private Field firstField;

    /**
     * A jatek aktualis allapota.
     */
    private GameState gameState;

    /**
     * Betoltve levo palyat tartalamzo fajl neve.
     */
    private String actualMap;

    /**
     * Palya merete mezokben merve.
     */
    private int width, height;

    /**
     * @return palya szelessege mezokben merve
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return palya magassaga mezokben merve
     */
    public int getHeight() {
        return height;
    }

    /**
     * Kiindulo allapotba allitja a palya parametereit.
     */
    private void resetEverything() {
        colonel = new Colonel(new EmptyField(), 0);
        colonel.die();
        jaffa = new Colonel(new EmptyField(), 0);
        jaffa.die();
        replicator = new Replicator(new EmptyField());
        replicator.die();
        Zpm.allZPMs = 0;
        firstField = null;
        gameState = GameState.MENU;
        width = 0;
        height = 0;
    }

    /**
     * Konstruktor, kivulrol nem lathoato, hogy ne lehessen tobbszor peldanyositani.
     */
    private Controller() {
        resetEverything();
    }

    /**
     * @param gameView refencia a megvalositast vegzo osztaly peldanyara
     */
    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    /**
     * Palya betoltese.
     * @param mapName palyat tartalmazo fajl neve
     */
    public void loadMap(String mapName) {
        actualMap = mapName;
        resetEverything();
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(new File(mapName)));
            String line = "";

            // A palya meretenek beolvasasa
            line = br.readLine();
            String mapSize[] = line.split(";");
            width = Integer.parseInt(mapSize[0]);
            height = Integer.parseInt(mapSize[1]);

            // Uj ideiglenes palya letrehozasa
            Field[][] mapDatas = new Field[height][width];

            // osztaly a merlegek es ajtok osszekapcsolasahoz szukseges ideiglenes adatok tarolasara
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

            // Merlegek es ajtok tarolasa, csak a beolvasas soran
            ArrayList<scaleAndDoorData> scaleDatas = new ArrayList<>();
            ArrayList<scaleAndDoorData> doorDatas = new ArrayList<>();

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
                        case '?':
                            mapDatas[j][i] = new EmptyField();  
                            replicator = new Replicator(mapDatas[j][i]);
                            break;
                        default:
                            break;
                    }
                }
                j++;
            }
            br.close();

            // Merlegek letrehozasa
            for (scaleAndDoorData scale : scaleDatas) {
                int i = 0;
                while (!doorDatas.get(i).id.equals(scale.id))
                    ++i;
                scaleAndDoorData door = doorDatas.remove(i);
                mapDatas[scale.getX()][scale.getY()] = new Scale((Door) mapDatas[door.getX()][door.getY()], scale.getWeight());
            }

            // Mezok szomszedainak beallitasa
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

    /**
     * Aktualis palya ujratoltese.
     */
    public void reloadMap() {
        if (gameState == GameState.MENU) {
            loadMap(actualMap);
            gameState = GameState.GAME;
        }
    }

    /**
     * Palya aktualis allasanak megjelenitese.
     */
    public void printMap() {
        // Vegighaladas a mezokon es azok megjelenitese
        Field nextField = firstField;
        Field nextRowFirstField = firstField.getNextField(Orientation.Type.SOUTH);
        while (nextField != null) {
            nextField.view(this);
            if (nextField.isThereAColonel) {
                if (nextField.equals(colonel.getOwnedField()))
                    gameView.drawPerson(PersonType.COLONEL, colonel.getOrientation());
                if (nextField.equals(jaffa.getOwnedField()))
                    gameView.drawPerson(PersonType.JAFFA, jaffa.getOrientation());
            } else if (nextField.isThereAReplicator())
                gameView.drawPerson(PersonType.REPLICATOR, replicator.getOrientation());
            if (nextField.getNextField(Orientation.Type.EAST) != null)
                nextField = nextField.getNextField(Orientation.Type.EAST);
            else {
                System.out.println();
                nextField = nextRowFirstField;
                if (nextField != null)
                    nextRowFirstField = nextField.getNextField(Orientation.Type.SOUTH);
            }
        }
        // Jatek allapotanak beallitasa
        if ((colonel.isDead() && jaffa.isDead()) || (Zpm.getAllZpms() == colonel.getCollectedZpms() + jaffa.getCollectedZpms()))
            gameState = GameState.MENU;
    }

    /**
     * @return a jatek aktualis allapota
     */
    public GameState getGameState() {
        return gameState;
    }

    /**
     * Szereplo tipus konkret szereplohoz rendeleset vegzi el a fuggveny.
     * Helyben a a szereplore van szukseg,
     * de a megjelenitest vegzo osztaly csak a szereplo tipusat ismeri.
     * @param personType szereplo tipusa
     * @return referencia a szereplore
     */
    private Colonel choosePerson(PersonType personType) {
        if (personType == PersonType.JAFFA)
            return jaffa;
        else
            return colonel;

    }

    /**
     * @param personType szereplo tipusa
     * @return meghalt-e mar a szereplo (true igen)
     */
    public boolean personIsDead(PersonType personType) {
        Colonel person = choosePerson(personType);
        return person.isDead();
    }

    /**
     * @param personType szereplo tipsa
     * @return szereplo altal osszegyujtott ZPM modulok szama
     */
    public int personGetCollectedZpms(PersonType personType) {
        Colonel person = choosePerson(personType);
        return person.getCollectedZpms();
    }

    /**
     * Szereplo mozgatasa.
     * @param personType szereplo tipusa
     * @param direction mozgas iranya
     */
    public void personMove(PersonType personType, Orientation.Type direction) {
        Colonel person = choosePerson(personType);

        if (!person.isDead() && gameState == GameState.GAME) {
            if (person.getOrientation() != direction) {
                person.rotateTo(direction);
            } else person.tryMoveTo(direction);
        }
    }

    public void personTryBoxPickUp(PersonType personType) {
        Colonel person = choosePerson(personType);

        if (!person.isDead() && gameState == GameState.GAME) {
            person.tryBoxPickUp();
        }
    }

    public void personTryBoxPutDown(PersonType personType) {
        Colonel person = choosePerson(personType);

        if (!person.isDead() && gameState == GameState.GAME) {
            person.tryBoxPutDown();
        }
    }

    public void personShootTeleporter(PersonType personType, Teleporter.Type teleporterType) {
        Colonel person = choosePerson(personType);

        if (!person.isDead() && gameState == GameState.GAME) {
            person.shootTeleporter(teleporterType);
        }
    }

    public void replicatorMove() {
        if (!replicator.isDead() && gameState == GameState.GAME)
            replicator.move();
    }

    /**
     * Doboz kirajzolasa,
     * ezen az osztalyon keresztul tortenik a megjelenitest kezelo osztaly eleres.
     * Mar csak a mezo tipusa van tovabb adva mert ezt ismeri a megjelenitest vegzo osztaly.
     * @param box
     */
    public void showView(Box box) {
    	gameView.drawField(FieldType.BOX);
    }

    public void showView(Door door) {
        if (door.isOpened())
            gameView.drawField(FieldType.EMPTY_FIELD);
        else
            gameView.drawField(FieldType.DOOR);
    }

    public void showView(EmptyField emptyField) {
        gameView.drawField(FieldType.EMPTY_FIELD);
    }

    public void showView(Ravine ravine) {
        gameView.drawField(FieldType.RAVINE);
    }

    public void showView(Scale scale) {
        gameView.drawScale(scale.getNumberOfBoxes());
    }

    public void showView(SpecialWall specialWall) {
        gameView.drawField(FieldType.SPECIAL_WALL);
    }

    public void showView(Teleporter teleporter) {
    	switch(teleporter.getType()){
    		case ORANGE:
                gameView.drawField(FieldType.PORTAL_ORANGE);
    			break;
    		case BLUE:
                gameView.drawField(FieldType.PORTAL_BLUE);
    			break;
    		case RED:
                gameView.drawField(FieldType.PORTAL_RED);
    			break;
    		case GREEN:
                gameView.drawField(FieldType.PORTAL_GREEN);
    			break;
    		default:
    			break;
    	}
    }

    public void showView(Wall wall) {
        gameView.drawField(FieldType.WALL);
    }

    public void showView(Zpm zpm) {
        gameView.drawField(FieldType.ZPM);
    }
}
