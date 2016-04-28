import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Controller {
    public enum PersonType {
        COLONEL,
        JAFFA,
        REPLICATOR
    }

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

    private static Controller instance = null;

    public static Controller getInstance() {
        if (instance == null)
            instance = new Controller();
        return instance;
    }

    private GameView gameView;

    private Colonel colonel;
    private Colonel jaffa;
    private Replicator replicator;
    private Field firstField; //top left corner on map
    boolean colonelAlreadyDead;
    boolean jaffaAlreadyDead;

    private int width, height;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private void resetEverything() {
        colonel = new Colonel(new EmptyField(), 0);
        colonel.die();
        jaffa = new Colonel(new EmptyField(), 0);
        jaffa.die();
        replicator = new Replicator(new EmptyField());
        replicator.die();
        firstField = null;
        colonelAlreadyDead = false;
        jaffaAlreadyDead = false;
        width = 0;
        height = 0;
    }

    private Controller() {
        resetEverything();
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
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
            width = Integer.parseInt(mapSize[0]);
            height = Integer.parseInt(mapSize[1]);

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

    public void printMap() {
        Field nextField = firstField;
        Field nextRowFirstField = firstField.getNextField(Orientation.Type.SOUTH);
        int actualFieldNumber = 0;
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
    }

    private Colonel choosePerson(PersonType personType) {
        if (personType == PersonType.JAFFA)
            return jaffa;
        else
            return colonel;

    }

    public boolean personIsDead(PersonType personType) {
        Colonel person = choosePerson(personType);
        return person.isDead();
    }

    public int personGetCollectedZpms(PersonType personType) {
        Colonel person = choosePerson(personType);
        return person.getCollectedZpms();
    }

    public void personMove(PersonType personType, Orientation.Type direction) {
        Colonel person = choosePerson(personType);

        if (!person.isDead()) {
            if (person.getOrientation() != direction) {
                person.rotateTo(direction);
            } else person.tryMoveTo(direction);
        }
    }

    public void personTryBoxPickUp(PersonType personType) {
        Colonel person = choosePerson(personType);

        if (!person.isDead()) {
            person.tryBoxPickUp();
        }
    }

    public void personTryBoxPutDown(PersonType personType) {
        Colonel person = choosePerson(personType);

        if (!person.isDead()) {
            person.tryBoxPutDown();
        }
    }

    public void personShootTeleporter(PersonType personType, Teleporter.Type teleporterType) {
        Colonel person = choosePerson(personType);

        if (!person.isDead()) {
            person.shootTeleporter(teleporterType);
        }
    }

    public void replicatorMove() {
        if (!replicator.isDead())
            replicator.move();
    }

    public int getAllZpms() {
        return Zpm.getAllZpms();
    }
    
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
