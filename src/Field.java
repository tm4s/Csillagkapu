/**
 * Ilyen mező típusokból áll a pálya
 */

public abstract class Field {
    public void accept(FieldVisitor visitor){
        visitor.visit(this);
    }
}
