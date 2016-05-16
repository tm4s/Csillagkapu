import java.util.ArrayList;
import java.util.List;

/**
 * Mérleg
 */

public class Scale extends Field {
	private Door door = new Door();
	private int requiredWeight;
	private int actualWeight;
	private List<Box> boxes;

	/**
	 * A merleg konstruktora
	 * @param door A merleghez tartozo ajto
	 * @param requiredWeight A kinyitashoz szukseges suly
     */
	public Scale(Door door, int requiredWeight) {
		this.door = door;
		this.requiredWeight = requiredWeight;
		this.actualWeight = 0;
		boxes = new ArrayList<>();
	}

	/**
	 * Hozzaadja a parameterben atadott sulyt a merleghez es vizsgalja,
	 * hogy az ajto ennek kovetkezteben milyen allapotba keruljon
	 * @param weight hozzadaott, illetve elvett suly
     */
	private void updateWeight(int weight) {
		actualWeight += weight;
		if (actualWeight >= requiredWeight)
			door.open();
		else
			door.close();
	}
	
	/**
	 * Suly hozzadasa
	 */
	public void addWeight(int plusWeight) {
		updateWeight(plusWeight);
	}


	/**
	 * Suly levetele
	 * @param minusWeight
     */
	public void removeWeight(int minusWeight) {
		updateWeight(-minusWeight);
	}

	public int getNumberOfBoxes() {
		return boxes.size();
	}

	/**
	 * Doboz rarakasa a merlegre
	 * @param box
     */
	public void addBox(Box box) {
		boxes.add(box);
		updateWeight(box.getWeight());
	}

	/**
	 * Az ezredes merleggel valo talalkozasakor az ezredes a merlegre lep
	 *
	 * @param colonel
	 */
	@Override
	public void collideWith(Colonel colonel) {
		if (!isThereAColonel && boxes.isEmpty())
			colonel.moveTo(this);
	}

	/**
	 * A lovedek merleggel valo talalkozasakor athalad a merleg felett
	 */
	@Override
	public void collideWith(Bullet bullet) {
		bulletMoveForward(bullet);
	}

	/**
	 * Amennyiben az ezredes kezeben doboz van az rakerul a merlegre
	 *
	 * @param hand
	 */
	@Override
	public void collideWith(ColonelsHand hand) {
		if (hand.hasBox())
			hand.getColonel().boxPutDownToScale(this);
		else if (!boxes.isEmpty()){
			removeWeight(boxes.get(boxes.size()-1).getWeight());
			hand.getColonel().boxPickUpFromScale(boxes.remove(boxes.size()-1));
		}
	}

	@Override
	public void view(Controller controller) {
		controller.showView(this);
	}

}
