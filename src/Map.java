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
    private Coordinate bulletPosition = new Coordinate(-1,-1);

    private Field[][] mapDatas;

    public Map(String fileName) throws IOException {
        readMapData(fileName);
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }


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
            public int id;
            private Coordinate position;

            public PosAndIdData(int id, Coordinate pos){
                id = id;
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
                        doorDatas.add(new PosAndIdData(Integer.parseInt(doorData[1]), new Coordinate(j, i)));
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
                        scaleDatas.add(new PosAndIdData(Integer.parseInt(scaleData[1]), new Coordinate(j, i)));
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
            while (doorDatas.get(i).id != d.id) {
                ++i;
            }
            Coordinate doorPosition = new Coordinate(doorDatas.get(i).getPosition());
            doorDatas.remove(i);
            setFieldAt(d.getPosition(), new Scale((Door) getFieldAt(doorPosition)));
        }


    }


}
