/**
 * ZPM modul
 */
public class Zpm extends Field {
	@Override
	public void collideWith(Colonel colonel) {
		colonel.moveTo(this);
	}

	@Override
	public void collideWith(Bullet bullet) {
		bullet.moveForward();
	}

	public void setNewPosition(Field startField) {
		Field actualField = startField;
		for (int i = 0; i < RandomGenerator.generateDistance(); ++i) {
			actualField = actualField.getNextRandomField();
		}
		actualField.collideWith(this);
	}


    @Override
    public Character print() {
        return 'Z';
    }
}
