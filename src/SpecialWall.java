/**
 * Speciális fal
 */
public class SpecialWall extends Wall {


    @Override
    public void collideWith(Bullet bullet) {
        bullet.transformToTeleporter();
    }

    @Override
    public Character print() {
        return '+';
    }
}
