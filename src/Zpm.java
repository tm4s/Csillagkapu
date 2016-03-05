/**
 * ZPM modul
 */
public class Zpm extends Field{
    @Override
    public void collideWith(Colonel colonel) {
        colonel.moveTo(this);
    }

    @Override
    public void collideWith(Bullet bullet) {
        bullet.moveForward();
    }

    @Override
    public void collideWith(Box box) {

    }

    @Override
    public Character print() {
        return 'Z';
    }
}
