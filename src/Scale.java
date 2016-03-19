/**
 * Mérleg
 */

public class Scale extends Field {
    private Door door = new Door();

    public Scale(Door door) {
        System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "(Door)");
        this.door = door;
    }

    @Override
    public void collideWith(Colonel colonel) {
        System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "(Colonel): void");
        colonel.moveTo(this);
    }

    @Override
    public void collideWith(Bullet bullet) {
        System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "(Bullet): void");
        bullet.moveForward();
    }

    @Override
    public void collideWith(ColonelsHand hand) {
        System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "(ColonelsHand): void");
        if (hand.hasBox())
            hand.getColonel().boxPutDownToScale(this);
    }

    @Override
    public Character print() {
        System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "(): Character");
        return 'S';
    }

    public void addWeight() {
        System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "(): void");
        door.open();
    }

    public void removeWeight() {
        System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "(): void");
        door.close();
    }

}
