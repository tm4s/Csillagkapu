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

public class Controller extends JPanel implements ActionListener {
    private Colonel colonel;
    private Colonel jaffa;
    private Replicator replicator;
    private Field firstField; //top left corner on map
    boolean colonelAlreadyDead;
    boolean jaffaAlreadyDead;

    private BufferedImage[] colonelImgs = new BufferedImage[4];
    private BufferedImage[] replicatorImgs = new BufferedImage[4];
    private BufferedImage[] jaffaImgs = new BufferedImage[4];

    private int actualX, actualY;
    private int width, height;
    private int pixelPerField;


    public int getWidth() {
        return width*pixelPerField;
    }

    public int getHeight() {
        return height*pixelPerField;
    }

    private Graphics2D graphics;

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
            specialWallImg = ImageIO.read(new File("specialWall.png"));
            scaleImg = ImageIO.read(new File("scale.png"));
            boxImg = ImageIO.read(new File("box.png"));
            emptyFieldImg = ImageIO.read(new File("emptyField.png"));
            zpmImg = ImageIO.read(new File("zpm.png"));
            ravineImg = ImageIO.read(new File("ravine.png"));
            orangePortalImg = ImageIO.read(new File("orangePortal.png"));
            bluePortalImg = ImageIO.read(new File("bluePortal.png"));
            redPortalImg = ImageIO.read(new File("redPortal.png"));
            greenPortalImg = ImageIO.read(new File("greenPortal.png"));
            doorImg = ImageIO.read(new File("door.png"));
            colonelImgs[0] = ImageIO.read(new File("colonelNorth.png"));
            colonelImgs[1] = ImageIO.read(new File("colonelWest.png"));
            colonelImgs[2] = ImageIO.read(new File("colonelSouth.png"));
            colonelImgs[3] = ImageIO.read(new File("colonelEast.png"));

            replicatorImgs[0] = ImageIO.read(new File("replicatorNorth.png"));
            replicatorImgs[1] = ImageIO.read(new File("replicatorWest.png"));
            replicatorImgs[2] = ImageIO.read(new File("replicatorSouth.png"));
            replicatorImgs[3] = ImageIO.read(new File("replicatorEast.png"));

            jaffaImgs[0] = ImageIO.read(new File("jaffaNorth.png"));
            jaffaImgs[1] = ImageIO.read(new File("jaffaWest.png"));
            jaffaImgs[2] = ImageIO.read(new File("jaffaSouth.png"));
            jaffaImgs[3] = ImageIO.read(new File("jaffaEast.png"));
        }
        catch (IOException e) {
        }


        resetEverything();
        addKeyListener(new Controller.KeyListener());
        setFocusable(true);
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

    private void drawPerson(BufferedImage[] person, Orientation.Type orientation) {
        BufferedImage image = person[0];
        switch (orientation) {
            case NORTH:
                image = person[0];
                break;
            case WEST:
                image = person[1];
                break;
            case SOUTH:
                image = person[2];
                break;
            case EAST:
                image = person[3];
                break;

        }
		drawObject(image);
    }

    private void printMap(Graphics g) {
        graphics = (Graphics2D) g;
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
                    drawPerson(colonelImgs, colonel.getOrientation());
                if (nextField.equals(jaffa.getOwnedField()))
                    drawPerson(jaffaImgs, jaffa.getOrientation());
            } else if (nextField.isThereAReplicator())
                drawPerson(replicatorImgs, replicator.getOrientation());
            if (nextField.getNextField(Orientation.Type.EAST) != null)
                nextField = nextField.getNextField(Orientation.Type.EAST);
            else {
                System.out.println();
                nextField = nextRowFirstField;
                if (nextField != null)
                    nextRowFirstField = nextField.getNextField(Orientation.Type.SOUTH);
            }
        }

        graphics.setFont(new Font("Helvetica Neue Ultra Light", Font.PLAIN, 20));
        graphics.setColor(Color.CYAN);

        if (Zpm.getAllZpms() == (colonel.getCollectedZpms() + jaffa.getCollectedZpms())) {
            graphics.drawString("NO MORE ZPMS", getWidth()/2-50, getHeight()/2);
        }

        if (colonel.isDead() && jaffa.isDead()) {
            graphics.drawString("Game Over", getWidth()/2-50, getHeight()/2);
        }


    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        printMap(g);
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
            if (!colonel.isDead()) {
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
            if (!replicator.isDead())
                replicator.move();
            repaint();
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

    }

    private void drawObject(BufferedImage image){
    	graphics.drawImage(image, actualX, actualY, null);
    }
    
    public void showView(Box box) {
    	drawObject(boxImg);
    }

    public void showView(Door door) {
        if (door.isOpened())
            drawObject(emptyFieldImg);
        else
    	    drawObject(doorImg);
    }

    public void showView(EmptyField emptyField) {
    	drawObject(emptyFieldImg);
    }

    public void showView(Ravine ravine) {
    	drawObject(ravineImg);
    }

    public void showView(Scale scale) {
        int fontSize = 30;
        graphics.setFont(new Font("Arial", Font.PLAIN, fontSize));
        graphics.setColor(Color.WHITE);

        drawObject(scaleImg);

        if (scale.getNumberOfBoxes() > 0) {
            drawObject(boxImg);
            graphics.drawString(Integer.toString(scale.getNumberOfBoxes()), actualX+15, actualY+fontSize+5);
        }
        else if (scale.getNumberOfBoxes() > 9) {
            drawObject(boxImg);
            graphics.drawString("*", actualX+15, actualY+fontSize+5);
        }
    }

    public void showView(SpecialWall specialWall) {
    	drawObject(specialWallImg);
    }

    public void showView(Teleporter teleporter) {
    	switch(teleporter.getType()){
    		case ORANGE:
                drawObject(emptyFieldImg);
    			drawObject(orangePortalImg);
    			break;
    		case BLUE:
                drawObject(emptyFieldImg);
    			drawObject(bluePortalImg);
    			break;
    		case RED:
                drawObject(emptyFieldImg);
    			drawObject(redPortalImg);
    			break;
    		case GREEN:
                drawObject(emptyFieldImg);
    			drawObject(greenPortalImg);
    			break;
    		default:
    			break;
    	}
    }

    public void showView(Wall wall) {
    	drawObject(wallImg);
    }

    public void showView(Zpm zpm) {
    	drawObject(zpmImg);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
