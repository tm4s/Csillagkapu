/**
 * Szakadék
 */
public class Ravine extends Field {
    @Override
    public void collideWith(Colonel colonel) {
    	Logger.log(">Ravine.collideWith(Colonel colonel)");
        colonel.moveTo(this);
        Logger.log("<Ravine.collideWith(Colonel colonel)");
    }

    @Override
    public void collideWith(Bullet bullet) {
    	Logger.log(">Ravine.collideWith(Bullet bullet)");
        bullet.moveForward();
        Logger.log("<Ravine.collideWith(Bullet bullet)");
    }

    @Override
    public void collideWith(ColonelsHand hand) {
    	Logger.log(">Ravine.collideWith(ColonelsHand hand)");
    	if (hand.hasBox())
            hand.getColonel().boxPutDownToRavine(this);
    	Logger.log("<Ravine.collideWith(ColonelsHand hand)");
    }
    
    public Ravine(){
    	Logger.log(">Ravine.Ravine()");
    	Logger.log("<Ravine.Ravine()");
    }
}
