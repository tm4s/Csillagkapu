/**
 * Sima fal
 */
public class Wall extends Field {


	@Override
	public Character print() {
		System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "(): Character");
		return '#';
	}
}
