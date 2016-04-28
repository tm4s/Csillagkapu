import javax.imageio.ImageIO;
import javax.swing.*;
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

public class Controller extends JPanel implements ActionListener {
    private Colonel colonel;
    private Colonel jaffa;
    private Replicator replicator;
    private Field firstField; //top left corner on map
    boolean colonelAlreadyDead;
    boolean jaffaAlreadyDead;

    private String[] colonelChars = {"A", "<", ">", "V"};
    private String[] replicatorChars = {"T", "F", "H", "G"};
    private String[] jaffaChars = {"I", "J", "L", "K"};

    private int actualX, actualY;
    private int width, height;
    private int pixelPerField;

    private BufferedImage emptyFieldImg = null;
    private BufferedImage zpmImg = null;
    private BufferedImage scaleImg = null;
    private BufferedImage boxImg = null;
    private BufferedImage doorImg = null;
    private BufferedImage wallImg = null;
    private BufferedImage specialWallImg = null;
    private BufferedImage ravineImg = null;
    private BufferedImage orangePortalImg = null;
    private BufferedImage bluePortalImg = null;
    private BufferedImage greenPortalImg = null;
    private BufferedImage redPortalImg = null;
    private BufferedImage colonelImg = null;
    private BufferedImage jaffaImg = null;
    private BufferedImage replicatorImg = null;




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
        actualX = 0;
        actualY = 0;
        width = 0;
        height = 0;
        pixelPerField = 50;
    }

    public Controller() {
        try {
            wallImg = ImageIO.read(new File("wall.png"));
            specialWallImg = ImageIO.read(new File("wall.png"));
            scaleImg = ImageIO.read(new File("scale.png"));
            boxImg = ImageIO.read(new File("box.png"));
            replicatorImg = ImageIO.read(new File("replicator.png"));
            emptyFieldImg = ImageIO.read(new File("emptyField.png"));
            zpmImg = ImageIO.read(new File("zpm.png"));
            colonelImg = ImageIO.read(new File("colonel.png"));
            jaffaImg = ImageIO.read(new File("jaffa.png"));
            ravineImg = ImageIO.read(new File("ravine.png"));
            orangePortalImg = ImageIO.read(new File("orangePortal.png"));
            bluePortalImg = ImageIO.read(new File("bluePortal.png"));
            redPortalImg = ImageIO.read(new File("redPortal.png"));
            greenPortalImg = ImageIO.read(new File("greenPortal.png"));
            doorImg = ImageIO.read(new File("door.png"));

        } catch (IOException e) {
        };
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

    private void printPerson(String[] person, Orientation.Type orientation) {
        String str = person[0];
        switch (orientation) {
            case NORTH:
                str = person[0];
                break;
            case WEST:
                str = person[1];
                break;
            case EAST:
                str = person[2];
                break;
            case SOUTH:
                str = person[3];
                break;

        }
        System.out.print(str);
    }

    private void printMap() {
        System.out.println("---------------------------------------");
        Field nextField = firstField;
        Field nextRowFirstField = firstField.getNextField(Orientation.Type.SOUTH);
        int actualFieldNumber = 0;
        while (nextField != null) {
            actualX = (actualFieldNumber % width) * pixelPerField;
            actualY = (actualFieldNumber / width) * pixelPerField;
            ++actualFieldNumber;
            nextField.view(this);
            if (nextField.isThereAColonel) {
                if (nextField.equals(colonel.getOwnedField()))
                    printPerson(colonelChars, colonel.getOrientation());
                if (nextField.equals(jaffa.getOwnedField()))
                    printPerson(jaffaChars, jaffa.getOrientation());
            } else if (nextField.isThereAReplicator())
                printPerson(replicatorChars, replicator.getOrientation());
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

    public class KeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
                if (!jaffa.isDead()) {
                    switch(keyCode) {
                        case KeyEvent.VK_I:
                            if (jaffa.getOrientation() != Orientation.Type.NORTH) {
                                jaffa.rotateTo(Orientation.Type.NORTH);
                            } else jaffa.tryMoveTo(Orientation.Type.NORTH);
                            break;
                        case KeyEvent.VK_K:
                            if (jaffa.getOrientation() != Orientation.Type.SOUTH) {
                                jaffa.rotateTo(Orientation.Type.SOUTH);
                            } else jaffa.tryMoveTo(Orientation.Type.SOUTH);
                            break;
                        case KeyEvent.VK_J:
                            if (jaffa.getOrientation() != Orientation.Type.WEST) {
                                jaffa.rotateTo(Orientation.Type.WEST);
                            } else jaffa.tryMoveTo(Orientation.Type.WEST);
                            break;
                        case KeyEvent.VK_L:
                            if (jaffa.getOrientation() != Orientation.Type.EAST) {
                                jaffa.rotateTo(Orientation.Type.EAST);
                            } else jaffa.tryMoveTo(Orientation.Type.EAST);
                            break;
                        case KeyEvent.VK_8:
                            jaffa.tryBoxPickUp();
                            break;
                        case KeyEvent.VK_9:
                            jaffa.tryBoxPutDown();
                            break;
                        case KeyEvent.VK_U:
                            jaffa.shootTeleporter(Teleporter.Type.GREEN);
                            break;
                        case KeyEvent.VK_O:
                            jaffa.shootTeleporter(Teleporter.Type.RED);
                            break;
                        default:
                            break;
                }
            }
            if (colonel.isDead()) {
                switch(keyCode) {
                    case KeyEvent.VK_W:
                        if (colonel.getOrientation() != Orientation.Type.NORTH) {
                            colonel.rotateTo(Orientation.Type.NORTH);
                        } else colonel.tryMoveTo(Orientation.Type.NORTH);
                        break;
                    case KeyEvent.VK_S:
                        if (colonel.getOrientation() != Orientation.Type.SOUTH) {
                            colonel.rotateTo(Orientation.Type.SOUTH);
                        } else colonel.tryMoveTo(Orientation.Type.SOUTH);
                        break;
                    case KeyEvent.VK_A:
                        if (colonel.getOrientation() != Orientation.Type.WEST) {
                            colonel.rotateTo(Orientation.Type.WEST);
                        } else colonel.tryMoveTo(Orientation.Type.WEST);
                        break;
                    case KeyEvent.VK_D:
                        if (colonel.getOrientation() != Orientation.Type.EAST) {
                            colonel.rotateTo(Orientation.Type.EAST);
                        } else colonel.tryMoveTo(Orientation.Type.EAST);
                        break;
                    case KeyEvent.VK_2:
                        colonel.tryBoxPickUp();
                        break;
                    case KeyEvent.VK_3:
                        colonel.tryBoxPutDown();
                        break;
                    case KeyEvent.VK_Q:
                        colonel.shootTeleporter(Teleporter.Type.BLUE);
                        break;
                    case KeyEvent.VK_E:
                        colonel.shootTeleporter(Teleporter.Type.ORANGE);
                        break;
                    default:
                        break;
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

    }

    public void run() {

    }

    public void showView(Box box) {
        System.out.print('B');
    }

    public void showView(Door door) {
        char c = door.isOpened() ? ' ' : 'D';
        System.out.print(c);
    }

    public void showView(EmptyField emptyField) {
        System.out.print(' ');
    }

    public void showView(Ravine ravine) {
        System.out.print('R');
    }

    public void showView(Scale scale) {
        char c;
        if (scale.getNumberOfBoxes() == 0)
            c = 'S';
        else if (scale.getNumberOfBoxes() <= 9)
            c = Integer.toString(scale.getNumberOfBoxes()).charAt(0);
        else
            c = '*';
        System.out.print(c);
    }

    public void showView(SpecialWall specialWall) {
        System.out.print('+');
    }

    public void showView(Teleporter teleporter) {
        char c = '0';
        if (teleporter.getType() == Teleporter.Type.ORANGE)
            c = 'O';
        else if (teleporter.getType() == Teleporter.Type.GREEN)
            c = 'X';
        else if (teleporter.getType() == Teleporter.Type.RED)
            c = 'Y';
        System.out.print(c);
    }

    public void showView(Wall wall) {
        System.out.print('#');

    }

    public void showView(Zpm zpm) {
        System.out.print('Z');
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        if (Zpm.getAllZpms() == (colonel.getCollectedZpms() + jaffa.getCollectedZpms())) {
            // TODO
        }
    }
}
