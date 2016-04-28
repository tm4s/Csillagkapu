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

	public Scale(Door door, int requiredWeight) {
		this.door = door;
		this.requiredWeight = requiredWeight;
		this.actualWeight = 0;
		boxes = new ArrayList<>();
	}

	private void updateWeight(int weight) {
		actualWeight += weight;
		if (actualWeight >= requiredWeight)
			door.open();
		else
			door.close();
	}
	

	public void addWeight(int plusWeight) {
		updateWeight(plusWeight);
	}

	public void removeWeight(int minusWeight) {
		updateWeight(-minusWeight);
	}

	public int getNumberOfBoxes() {
		return boxes.size();
	} 

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
			updateWeight((-1)*boxes.get(boxes.size()-1).getWeight());
			hand.getColonel().boxPickUp(boxes.remove(boxes.size()-1));
		}
	}

	@Override
	public void view(Controller controller) {
		controller.showView(this);
	}

}
