/**
 * Ezredes keze a doboz felvetelhez
 */

public class ColonelsHand {
	/**
	 * hozza tatozo ezredesre referencia
	 * van-e nala box parameter
	 */
	private Colonel colonel;
	private boolean hasBox;

	/**
	 * konstruktor
	 *
	 * @param colonel ennek az ezredesnek a keze
	 */
	public ColonelsHand(Colonel colonel) {
		System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "(Colonel)");
		this.colonel = colonel;
	}

	/**
	 * @return hozza tartozo ezredes
	 */
	public Colonel getColonel() {
		System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "(): Colonel");
		return colonel;
	}

	/**
	 * @return van-e nala doboz (true van)
	 */
	public boolean hasBox() {
		System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "(): boolean");
		return hasBox;
	}

	/**
	 * beallitja megfelelore hogy van-e nala doboz
	 *
	 * @param hasBox true van false nincs
	 */
	public void setHasBox(boolean hasBox) {
		System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "(boolean): void");
		this.hasBox = hasBox;
	}
}
