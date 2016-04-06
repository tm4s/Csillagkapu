/**
 * Ilyen mező típusokból áll a pálya
 */

public abstract class Field {
    private static int nextId = 0;

    // csak teszteleshez kell
    private Coordinate position = new Coordinate(-1, -1);
    private Map map = null;
    /**
     * mezo szomszedai az iranyokat tarolo enumnak megfelelo az indexelese a tombnek
     */
    private Field[] nextFields = new Field[4];

    private Replicator replicator;
    protected boolean isThereAColonel;
    private int id;

    public Field() {
        replicator = null;
        isThereAColonel = false;
        id = nextId++;
    }

    public boolean equals(Field otherField) {
        return id == otherField.id;
    }

    public void setReplicator(Replicator replicator) {
        this.replicator = replicator;
    }

    protected void bulletMoveForward(Bullet bullet) {
        if (replicator == null)
            bullet.moveForward();
        else
            replicator.die();
    }

    public void setThereAColonel(boolean isThereAColonel) {
        this.isThereAColonel = isThereAColonel;
    }


    /**
     * mezo adott iranyu szomszedjanak beallitasa
     * @param direction ilyen iranyban helyezkedik el a szomszed
     * @param field a szomszed mezo referenciaja
     */
    public void setNextField(Orientation.Type direction, Field field) {
        nextFields[direction.ordinal()] = field;
    }

    /**
     * masik mezot helyez a sajat helyere
     * eleg a masik mezo szomszedait atallitania ra
     * @param field ez a mezo lep a helyebe
     */
    public void setField(Field field) {
        field.setNextFields(nextFields);

        // csak teszteleshez kell
        field.position = new Coordinate(position);
        field.map = map;
        field.setOnMap();
    }

    /**
     * megadott iranyu szomszed mezojet adja vissza
     * @param direction ilyen iranyu szomszedjat
     * @return adott iranyu szomszed mezo
     */
    public Field getNextField(Orientation.Type direction) {
        return nextFields[direction.ordinal()];
    }

    /**
     * veletlenszeruen kivalasztja az egyik szomszedjat es visszaadja azt
     * @returne random szomszed mezo
     */
    public Field getNextRandomField() {
        Field nextField = getNextField(RandomGenerator.generateOrientation());
        while (nextField == null)
            nextField = getNextField(RandomGenerator.generateOrientation());
        return nextField;
    }

    /**
     * szomszed mezok ertesitese magarol es sajat szomszed mezoinek inicializalasa
     * szomszed mezok megfelleo iranyu szomszed mezo referenciajat magara allitja
     * @param nextFields szomszed mezok tombje
     */
    public void setNextFields(Field[] nextFields) {
        for (int i = 0; i < nextFields.length; ++i) {
            this.nextFields[i] = nextFields[i];
            if (this.nextFields[i] != null) {
                this.nextFields[i].setNextField(Orientation.getOpposite(i), this);
            }
        }
    }

    /**
     * visitor pattern fuggvenyei
     */
    public void collideWith(Colonel colonel) {}
    public void collideWith(Bullet bullet) {}
    public void collideWith(ColonelsHand hand) {}
    public void collideWith(Zpm zpm) {
        getNextRandomField().collideWith(zpm);
    }

    //teszteleshez kell
    public abstract Character print();

    //csak teszteleshez kell
    public Coordinate getPosition() {
        return position;
    }
    public void setPosition(Coordinate position) {
        this.position = new Coordinate(position);
    }
    public void setOnMap(){
        map.setFieldAt(position, this);

    }

    public void setMap(Map map) {
        this.map = map;
    }
	// csak teszteleshez kell

}
