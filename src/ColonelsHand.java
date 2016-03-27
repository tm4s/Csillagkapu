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
		this.colonel = colonel;
	}

	/**
	 * @return hozza tartozo ezredes
	 */
	public Colonel getColonel() {
		return colonel;
	}

	/**
	 * @return van-e nala doboz (true van)
	 */
	public boolean hasBox() {
		return hasBox;
	}

	/**
	 * beallitja megfelelore hogy van-e nala doboz
	 *
	 * @param hasBox true van false nincs
	 */
	public void setHasBox(boolean hasBox) {
		this.hasBox = hasBox;
	}
}
