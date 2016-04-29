import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Thomas on 29/04/16.
 */
public class GameView extends JPanel {
    private static GameView instance = null;

    private Controller controller;

    private Graphics2D graphics;

    private int actualX, actualY;
    private int width, height;
    private int pixelPerField;
    private int actualFieldNumber;

    private BufferedImage[] colonelImgs = new BufferedImage[4];
    private BufferedImage[] replicatorImgs = new BufferedImage[4];
    private BufferedImage[] jaffaImgs = new BufferedImage[4];

    private BufferedImage emptyFieldImg;
    private BufferedImage zpmImg;
    private BufferedImage scaleImg;
    private BufferedImage boxImg;
    private BufferedImage doorImg;
    private BufferedImage wallImg;
    private BufferedImage specialWallImg;
    private BufferedImage ravineImg;
    private BufferedImage orangePortalImg;
    private BufferedImage bluePortalImg;
    private BufferedImage greenPortalImg;
    private BufferedImage redPortalImg;

    public static GameView getInstance() {
        if (instance == null)
            instance = new GameView();
        return instance;
    }

    private GameView() {
        width = 0;
        height = 0;
        pixelPerField = 50;
        try {
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
        }
        catch (IOException e) {
        }
        addKeyListener(new GameView.KeyListener());
        setFocusable(true);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setSize(int x, int y) {
        width = x;
        height = y;
    }

    public int getWidth() {
        return pixelPerField*width;
    }

    public int getHeight() {
        return pixelPerField*height;
    }

    public void drawPerson(Controller.PersonType personType, Orientation.Type orientation) {
        BufferedImage[] person;
        if (personType == Controller.PersonType.COLONEL)
            person = colonelImgs;
        else if (personType == Controller.PersonType.JAFFA)
            person = jaffaImgs;
        else
            person = replicatorImgs;
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

    private void drawObject(BufferedImage image){
        graphics.drawImage(image, actualX, actualY, null);
    }

    public void drawField(Controller.FieldType fieldType) {
        actualX = (actualFieldNumber % width) * pixelPerField;
        actualY = (actualFieldNumber / width) * pixelPerField;
        ++actualFieldNumber;
        switch (fieldType) {
            case EMPTY_FIELD:
                drawObject(emptyFieldImg);
                break;
            case ZPM:
                drawObject(zpmImg);
                break;
            case SCALE:
                drawObject(scaleImg);
                break;
            case BOX:
                drawObject(boxImg);
                break;
            case DOOR:
                drawObject(doorImg);
                break;
            case WALL:
                drawObject(wallImg);
                break;
            case SPECIAL_WALL:
                drawObject(specialWallImg);
                break;
            case RAVINE:
                drawObject(ravineImg);
                break;
            case PORTAL_ORANGE:
                drawObject(emptyFieldImg);
                drawObject(orangePortalImg);
                break;
            case PORTAL_BLUE:
                drawObject(emptyFieldImg);
                drawObject(bluePortalImg);
                break;
            case PORTAL_GREEN:
                drawObject(emptyFieldImg);
                drawObject(greenPortalImg);
                break;
            case PORTAL_RED:
                drawObject(emptyFieldImg);
                drawObject(redPortalImg);
                break;
            default:
                break;
        }
    }

    public void drawScale(int numberOfBoxes) {
        actualX = (actualFieldNumber % width) * pixelPerField;
        actualY = (actualFieldNumber / width) * pixelPerField;
        ++actualFieldNumber;

        int fontSize = 30;
        graphics.setFont(new Font("Arial", Font.PLAIN, fontSize));
        graphics.setColor(Color.WHITE);

        drawObject(scaleImg);

        if (numberOfBoxes > 0) {
            drawObject(boxImg);
            graphics.drawString(Integer.toString(numberOfBoxes), actualX+15, actualY+fontSize+5);
        }
        else if (numberOfBoxes > 9) {
            drawObject(boxImg);
            graphics.drawString("*", actualX+15, actualY+fontSize+5);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        graphics = (Graphics2D) g;
        actualFieldNumber = 0;
        controller.printMap();

        Color transparentBg = new Color(87, 87, 87, 240);

        graphics.setFont(new Font("Arial", Font.BOLD, 15));
        graphics.setColor(Color.WHITE);

        graphics.drawString("Colonel's ZPMs:", getWidth() - 200, getHeight() - 60);
        if (controller.personIsDead(Controller.PersonType.COLONEL)) {
            graphics.drawString("✝", getWidth() - 215, getHeight() - 60);

        }
        graphics.drawString(Integer.toString(controller.personGetCollectedZpms(Controller.PersonType.COLONEL)), getWidth() - 30, getHeight() - 60);
        graphics.drawString("Jaffa's ZPMs:", getWidth() - 200, getHeight() - 40);
        if (controller.personIsDead(Controller.PersonType.JAFFA)) {
            graphics.drawString("✝", getWidth() - 215, getHeight() - 40);

        }
        graphics.drawString(Integer.toString(controller.personGetCollectedZpms(Controller.PersonType.JAFFA)), getWidth() - 30, getHeight() - 40);

        if (controller.getGameState() == Controller.GameState.MENU) {
            graphics.setColor(transparentBg);
            graphics.fillRect(0, 0, getWidth(), getHeight());
            graphics.setColor(Color.WHITE);
            graphics.drawString("HIT SPACE TO START", getWidth() / 2 - 50, getHeight() / 2);
        }
    }

    public class KeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch(keyCode) {
                case KeyEvent.VK_I:
                    controller.personMove(Controller.PersonType.JAFFA, Orientation.Type.NORTH);
                    break;
                case KeyEvent.VK_K:
                    controller.personMove(Controller.PersonType.JAFFA, Orientation.Type.SOUTH);
                    break;
                case KeyEvent.VK_J:
                    controller.personMove(Controller.PersonType.JAFFA, Orientation.Type.WEST);
                    break;
                case KeyEvent.VK_L:
                    controller.personMove(Controller.PersonType.JAFFA, Orientation.Type.EAST);
                    break;
                case KeyEvent.VK_8:
                    controller.personTryBoxPickUp(Controller.PersonType.JAFFA);
                    break;
                case KeyEvent.VK_9:
                    controller.personTryBoxPutDown(Controller.PersonType.JAFFA);
                    break;
                case KeyEvent.VK_U:
                    controller.personShootTeleporter(Controller.PersonType.JAFFA, Teleporter.Type.GREEN);
                    break;
                case KeyEvent.VK_O:
                    controller.personShootTeleporter(Controller.PersonType.JAFFA, Teleporter.Type.RED);
                    break;
                case KeyEvent.VK_W:
                    controller.personMove(Controller.PersonType.COLONEL, Orientation.Type.NORTH);
                    break;
                case KeyEvent.VK_S:
                    controller.personMove(Controller.PersonType.COLONEL, Orientation.Type.SOUTH);
                    break;
                case KeyEvent.VK_A:
                    controller.personMove(Controller.PersonType.COLONEL, Orientation.Type.WEST);
                    break;
                case KeyEvent.VK_D:
                    controller.personMove(Controller.PersonType.COLONEL, Orientation.Type.EAST);
                    break;
                case KeyEvent.VK_2:
                    controller.personTryBoxPickUp(Controller.PersonType.COLONEL);
                    break;
                case KeyEvent.VK_3:
                    controller.personTryBoxPutDown(Controller.PersonType.COLONEL);
                    break;
                case KeyEvent.VK_Q:
                    controller.personShootTeleporter(Controller.PersonType.COLONEL, Teleporter.Type.BLUE);
                    break;
                case KeyEvent.VK_E:
                    controller.personShootTeleporter(Controller.PersonType.COLONEL, Teleporter.Type.ORANGE);
                    break;
                case KeyEvent.VK_SPACE:
                    controller.relodMap();
                    break;
                default:
                    break;
            }
            controller.replicatorMove();
            repaint();
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

    }
}
