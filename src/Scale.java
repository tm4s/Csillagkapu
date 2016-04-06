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

	public void addBox(Box box) {
		boxes.add(box);
		updateWeight(box.getWeight());
	}

	@Override
	public void collideWith(Colonel colonel) {
		if (!isThereAColonel && boxes.isEmpty())
			colonel.moveTo(this);
	}

	@Override
	public void collideWith(Bullet bullet) {
		bulletMoveForward(bullet);
	}

	@Override
	public void collideWith(ColonelsHand hand) {
		if (hand.hasBox())
			hand.getColonel().boxPutDownToScale(this);
		else if (!boxes.isEmpty()){
			updateWeight(-boxes.get(boxes.size()-1).getWeight());
			hand.getColonel().boxPickUpFromScale(boxes.remove(boxes.size()-1));
		}
	}

	@Override
	public Character print() {
		if (boxes.isEmpty())
			return 'S';
		else if (boxes.size()<=9)
			return Integer.toString(boxes.size()).charAt(0);
		else
			return '*';
	}

}
