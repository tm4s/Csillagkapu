/**
 * Speciális fal
 */
public class SpecialWall extends Wall {

    public SpecialWall() {}

    @Override
    public void collideWith(Bullet bullet) {
        bullet.transformToTeleporter();
    }

}
