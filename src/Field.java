/**
 * Ilyen mező típusokból áll a pálya
 */

public abstract class Field {
	// csak teszteleshez kell
	private Coordinate position = new Coordinate(-1, -1);
	private Map map = null;

	/**
	 * mezo szomszedai az iranyokat tarolo enumnak megfelelo az indexelese a tombnek
	 */
	private Field[] nextFields = new Field[4];

	/**
	 * mezo adott iranyu szomszedjanak beallitasa
	 *
	 * @param direction ilyen iranyban helyezkedik el a szomszed
	 * @param field     a szomszed mezo referenciaja
	 */
	public void setNextField(Orientation.Type direction, Field field) {
		System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "(Type, Field): void");
		nextFields[direction.ordinal()] = field;
	}

	/**
	 * masik mezot helyez a sajat helyere
	 * eleg a masik mezo szomszedait atallitania ra
	 *
	 * @param field ez a mezo lep a helyebe
	 */
	public void setField(Field field) {
		System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "(Field): void");
		field.setNextFields(nextFields);

		// csak teszteleshez kell
		field.position = new Coordinate(position);
		field.map = map;
		field.setOnMap();
	}

	/**
	 * megadott iranyu szomszed mezojet adja vissza
	 *
	 * @param direction ilyen iranyu szomszedjat
	 * @return adott iranyu szomszed mezo
	 */
	public Field getNextField(Orientation.Type direction) {
		System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "(Type): Field");
		return nextFields[direction.ordinal()];
	}

	/**
	 * szomszed mezok ertesitese magarol es sajat szomszed mezoinek inicializalasa
	 * szomszed mezok megfelleo iranyu szomszed mezo referenciajat magara allitja
	 *
	 * @param nextFields szomszed mezok tombje
	 */
	public void setNextFields(Field[] nextFields) {
		System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "(Field[]): void");
		for (int i = 0; i < nextFields.length; ++i) {
			this.nextFields[i] = nextFields[i];
			this.nextFields[i].setNextField(Orientation.getOpposite(i), this);
		}
	}

	/**
	 * visitor pattern fuggvenyei
	 */
	public void collideWith(Colonel colonel) {
		System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "(Colonel): void");
	}

	public void collideWith(Bullet bullet) {
		System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "(Bullet): void");
	}

	public void collideWith(ColonelsHand hand) {
		System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "(ColonelsHand): void");
	}

	//teszteleshez kell
	public abstract Character print();

	//csak teszteleshez kell
	public Coordinate getPosition() {
		System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "(): Coordinate");
		return position;
	}

	public void setPosition(Coordinate position) {
		System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "(Coordinate): void");
		this.position = new Coordinate(position);
	}

	public void setOnMap() {
		System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "(): void");
		map.setFieldAt(position, this);
	}

	public void setMap(Map map) {
		System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "(Map): void");
		this.map = map;
	}
	// csak teszteleshez kell

}
