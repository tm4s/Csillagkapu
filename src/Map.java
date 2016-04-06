import com.sun.org.apache.xml.internal.serializer.utils.StringToIntTable;
import com.sun.xml.internal.fastinfoset.util.CharArray;
import javafx.geometry.Pos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Pálya beovasasara, kapcsolatok letrehozasara, kiirasra valo inicializalas
 * utan nem kell belole peldany
 */

public class Map {
	private int width;
	private int height;
	private Field colonelStartingField;
	private Field jaffaStartingField;
	private int colonelWeight;
	private int jaffaWeight;

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

	public int getAllZpms() {
		return allZpms;
	}

	public Field getFieldAt(Coordinate position) {
		return mapDatas[position.getX()][position.getY()];
	}

	public void setFieldAt(Coordinate position, Field field) {
		mapDatas[position.getX()][position.getY()] = field;
	}

	public Field getColonelStartingField() {
		return colonelStartingField;
	}
	public Field getJaffaStartingField() { return jaffaStartingField; }
	public int getColonelWeight() {
		return colonelWeight;
	}
	public int getJaffaWeight() {
		return jaffaWeight;
	}

	private void readMapData(String fileName) throws IOException {
		BufferedReader br = null;
		br = new BufferedReader(new FileReader(new File(fileName)));
		String line = "";

		// A palya meretenek beolvasasa
		line = br.readLine();
		String mapSize[] = line.split(";");
		width = Integer.parseInt(mapSize[0]);
		height = Integer.parseInt(mapSize[1]);

		// Uj palya letrehozasa
		mapDatas = new Field[height][width];

		// merlegek es ajtok osszekapcsolasahoz szukseges ideiglenes adatok
		// tarolasa
		class PosAndIdDataAndWeight {
			public String id;
			private Coordinate position;
			private int weight;

			public PosAndIdDataAndWeight(String id, Coordinate pos, int w) {
				this.id = id;
				position = new Coordinate(pos);
				weight = w;
			}

			public Coordinate getPosition() {
				return position;
			}
			public int getWeight() { return weight; }

		}

		ArrayList<PosAndIdDataAndWeight> scaleDatas = new ArrayList<PosAndIdDataAndWeight>();
		ArrayList<PosAndIdDataAndWeight> doorDatas = new ArrayList<PosAndIdDataAndWeight>();

		// Egyes elemek beolvasasa
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
					doorDatas.add(new PosAndIdDataAndWeight(doorData[1], new Coordinate(j, i), 0));
					break;
				case 'B':
					mapDatas[j][i] = new Box();
					break;
				case 'C':
					colonelWeight = Integer.parseInt(array[i].split("_")[1]);
					mapDatas[j][i] = new EmptyField();
					colonelStartingField = mapDatas[j][i];
					break;
				case 'J':
					jaffaWeight = Integer.parseInt(array[i].split("_")[1]);
					mapDatas[j][i] = new EmptyField();
					jaffaStartingField = mapDatas[j][i];
					break;
				case 'S':
					String scaleData[] = array[i].split("_");
					scaleDatas.add(new PosAndIdDataAndWeight(scaleData[1], new Coordinate(j, i), Integer.parseInt(scaleData[2])));
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

		// merlegek letrehozasa

		for (PosAndIdDataAndWeight d : scaleDatas) {
			int i = 0;
			while (!doorDatas.get(i).id.equals(d.id)) {
				++i;
			}
			Coordinate doorPosition = new Coordinate(doorDatas.get(i).getPosition());
			doorDatas.remove(i);
			setFieldAt(d.getPosition(), new Scale((Door, d.getWeight(), getFieldAt(doorPosition));
		}

		setDatas();
	}

	private void setDatas() {
		for (int j = 0; j < height; ++j) {
			for (int i = 1; i < width - 1; ++i) {
				mapDatas[j][i].setNextField(Orientation.Type.WEST, mapDatas[j][i - 1]);
				mapDatas[j][i].setNextField(Orientation.Type.EAST, mapDatas[j][i + 1]);
			}
			mapDatas[j][0].setNextField(Orientation.Type.EAST, mapDatas[j][1]);
			mapDatas[j][width - 1].setNextField(Orientation.Type.WEST, mapDatas[j][width - 2]);
		}
		for (int i = 0; i < width; ++i) {
			for (int j = 1; j < height - 1; ++j) {
				mapDatas[j][i].setNextField(Orientation.Type.NORTH, mapDatas[j - 1][i]);
				mapDatas[j][i].setNextField(Orientation.Type.SOUTH, mapDatas[j + 1][i]);
			}
			mapDatas[0][i].setNextField(Orientation.Type.SOUTH, mapDatas[1][i]);
			mapDatas[height - 1][i].setNextField(Orientation.Type.NORTH, mapDatas[height - 2][i]);
		}
		for (int j = 0; j < height; ++j) {
			for (int i = 1; i < width; ++i) {
				mapDatas[j][i].setPosition(new Coordinate(j, i));
				mapDatas[j][i].setMap(this);
			}
		}
	}

}
