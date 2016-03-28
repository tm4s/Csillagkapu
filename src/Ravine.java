/**
 * Szakadék
 */
public class Ravine extends Field {

    /**
     * Konstruktor
     */
    public Ravine(){
        Logger.log(">Ravine.Ravine()");
        Logger.log("<Ravine.Ravine()");
    }

    /**
     * Az ezredes szakadekba lepesenek a kezelese
     * @param colonel
     */
    @Override
    public void collideWith(Colonel colonel) {
    	Logger.log(">Ravine.collideWith(Colonel colonel)");
        colonel.moveTo(this);
        Logger.log("<Ravine.collideWith(Colonel colonel)");
    }

    /**
     * Egy szakadek es egy lovedek talalkozasa.
     * A lovedek tovabbhalad a szakadek felett.
     * @param bullet
     */
    @Override
    public void collideWith(Bullet bullet) {
    	Logger.log(">Ravine.collideWith(Bullet bullet)");
        bullet.moveForward();
        Logger.log("<Ravine.collideWith(Bullet bullet)");
    }

    /**
     * Amennyiben az ezerdes kezeben doboz volt, az beleesik a szakadekba es megszunik
     * @param hand
     */
    @Override
    public void collideWith(ColonelsHand hand) {
    	Logger.log(">Ravine.collideWith(ColonelsHand hand)");
    	if (hand.hasBox())
            hand.getColonel().boxPutDownToRavine(this);
    	Logger.log("<Ravine.collideWith(ColonelsHand hand)");
    }
}
