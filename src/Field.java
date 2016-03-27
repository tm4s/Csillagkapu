/**
 * Ilyen mező típusokból áll a pálya
 */

public abstract class Field {
	// csak teszteleshez kell
	private Coordinate position = new Coordinate(-1, -1);

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
		Logger.log(">Field.setNextField(Orientation.Type direction, Field field");
		nextFields[direction.ordinal()] = field;
		Logger.log("<Field.setNextField(Orientation.Type direction, Field field");
	}

	/**
	 * masik mezot helyez a sajat helyere
	 * eleg a masik mezo szomszedait atallitania ra
	 *
	 * @param field ez a mezo lep a helyebe
	 */
	public void setField(Field field) {
		Logger.log(">Field.setField(Field Field");
		field.setNextFields(nextFields);

		// csak teszteleshez kell
		field.position = new Coordinate(position);
		Logger.log("<Field.setField(Field Field");
	}

	/**
	 * megadott iranyu szomszed mezojet adja vissza
	 *
	 * @param direction ilyen iranyu szomszedjat
	 * @return adott iranyu szomszed mezo
	 */
	public Field getNextField(Orientation.Type direction) {
		return nextFields[direction.ordinal()];
	}

	/**
	 * szomszed mezok ertesitese magarol es sajat szomszed mezoinek inicializalasa
	 * szomszed mezok megfelleo iranyu szomszed mezo referenciajat magara allitja
	 *
	 * @param nextFields szomszed mezok tombje
	 */
	public void setNextFields(Field[] nextFields) {
		Logger.log(">Field.setNextFields(Field[] nextFields)");
		for (int i = 0; i < nextFields.length; ++i) {
			this.nextFields[i] = nextFields[i];
			this.nextFields[i].setNextField(Orientation.getOpposite(i), this);
		}
		Logger.log("<Field.setNextFields(Field[] nextFields)");
	}

	/**
	 * visitor pattern fuggvenyei
	 */
	public void collideWith(Colonel colonel) {
		Logger.log(">Field.collideWith(Colonel colonel)");
		Logger.log("<Field.collideWith(Colonel colonel)");
	}

	public void collideWith(Bullet bullet) {
		Logger.log(">Field.collideWith(Bullet bullet)");
		Logger.log("<Field.collideWith(Bullet bullet)");
	}

	public void collideWith(ColonelsHand hand) {
		Logger.log(">Field.collideWith(ColonelsHand hand)");
		Logger.log("<Field.collideWith(ColonelsHand hand)");
	}




}
