import com.sun.org.apache.xml.internal.serializer.utils.StringToIntTable;
import com.sun.xml.internal.fastinfoset.util.CharArray;
import javafx.geometry.Pos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Pálya aktuális állásának tárolására való osztály
 */

public class Map {
    private int width;
    private int height;
    private Coordinate colonelStartingPosition;

    private int allZpms = 0;


    private Field[][] mapDatas;

    private Teleporter blueTeleporter = null;
    private Teleporter orangeTeleporter = null;

    public Map(String fileName) throws IOException {
        readMapData(fileName);
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    public int getAllZpms() {return allZpms; }

    public Field getFieldAt(Coordinate position) {
        return mapDatas[position.getX()][position.getY()];
    }
    public void setFieldAt(Coordinate position, Field field) {
        mapDatas[position.getX()][position.getY()] = field;
    }
    public Coordinate getColonelStartingPosition() {
        return colonelStartingPosition;
    }

    private void readMapData(String fileName) throws IOException {
        BufferedReader br = null;
        br = new BufferedReader(new FileReader(new File(fileName)));
        String line = "";

        //A palya meretenek beolvasasa
        line = br.readLine();
        String mapSize[] = line.split(";");
        width = Integer.parseInt(mapSize[0]);
        height = Integer.parseInt(mapSize[1]);


        //Uj palya letrehozasa
        mapDatas = new Field[height][width];

        //merlegek es ajtok osszekapcsolasahoz szukseges ideiglenes adatok tarolasa
        class PosAndIdData {
            public String id;
            private Coordinate position;

            public PosAndIdData(String id, Coordinate pos){
                this.id = id;
                position = new Coordinate(pos);
            }

            public Coordinate getPosition() {
                return position;
            }
        }

        ArrayList<PosAndIdData> scaleDatas = new ArrayList<PosAndIdData>();
        ArrayList<PosAndIdData> doorDatas = new ArrayList<PosAndIdData>();

        //Egyes elemek beolvasasa
        int j = 0;
        while ((line = br.readLine()) != null) {
            String array[] = line.split(";");
            for (int i = 0; i < width; i++) {
                switch (array[i].charAt(0)) {
                    case 'E':
                        mapDatas[j][i] = new EmptyField();
                        break;
                    case 'W':
                        mapDatas[j][i] = new Wall();
                        break;
                    case 'D':
                        mapDatas[j][i] = new Door();
                        String doorData[] = array[i].split("_");
                        doorDatas.add(new PosAndIdData(doorData[1], new Coordinate(j, i)));
                        break;
                    case 'B':
                        mapDatas[j][i] = new Box(null);
                        break;
                    case 'C':
                        mapDatas[j][i] = new EmptyField();
                        colonelStartingPosition = new Coordinate(j, i);
                        break;
                    case 'S':
                        String scaleData[] = array[i].split("_");
                        scaleDatas.add(new PosAndIdData(scaleData[1], new Coordinate(j, i)));
                        break;
                    case '+':
                        mapDatas[j][i] = new SpecialWall();
                        break;
                    case 'Z':
                        mapDatas[j][i] = new Zpm();
                        allZpms++;
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

        //merlegek letrehozasa

        for (PosAndIdData d: scaleDatas) {
            int i = 0;
            while (!doorDatas.get(i).id.equals(d.id)) {
                ++i;
            }
            Coordinate doorPosition = new Coordinate(doorDatas.get(i).getPosition());
            doorDatas.remove(i);
            setFieldAt(d.getPosition(), new Scale((Door) getFieldAt(doorPosition)));
        }


    }

    /**
     * Csillagkapu (teleporter) letrehozasa adott:
     * @param type tipusu (sarga vagy kek)
     * @param position ezen a helyen
     * azonos szinu regi teleportert meg kell szuntetni ha letezik
     * es a map-ben kulon eltarolt megfelelo tipusu teleportert frissiteni kell az ujra
     * frissiteni kell meg a masik szinu teleporterben is a hivatkozast hogy az ujra mutasson
     * ha letezik masik szinu teleporter
     */
    public void createTeleporter(Teleporter.Type type, Coordinate position) {
        // lehetne szepiteni
        if (type == Teleporter.Type.BLUE) {
            setFieldAt(position, new Teleporter(type, orangeTeleporter, position));
            if (blueTeleporter != null)
                setFieldAt(blueTeleporter.getPosition(), new SpecialWall());
            blueTeleporter = (Teleporter) getFieldAt(position);
            if (orangeTeleporter != null)
                orangeTeleporter.setOtherTeleporter(blueTeleporter);
        } else {
            setFieldAt(position, new Teleporter(type, blueTeleporter, position));
            if (orangeTeleporter != null)
                setFieldAt(orangeTeleporter.getPosition(), new SpecialWall());
            orangeTeleporter = (Teleporter) getFieldAt(position);
            if (blueTeleporter != null)
                blueTeleporter.setOtherTeleporter(orangeTeleporter);
        }
    }
}
