/**
 * Ilyen mező típusokból áll a pálya
 */

public abstract class Field {


    public abstract void collideWith(Colonel colonel);
    public abstract void collideWith(Bullet bullet);
    public abstract void collideWith(Box box);

    public abstract Character print();
}
