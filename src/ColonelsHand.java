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
		Logger.log(">ColonelsHand.ColonelsHand(Colonel colonel)");
		this.colonel = colonel;
		Logger.log("<ColonelsHand.ColonelsHand(Colonel colonel)");
	}

	/**
	 * @return hozza tartozo ezredes
	 */
	public Colonel getColonel() {
		Logger.log(">ColonelsHand.getColonel()");
		Logger.log("<ColonelsHand.getColonel()");
		return colonel;
	}

	/**
	 * @return van-e nala doboz (true van)
	 */
	public boolean hasBox() {
		Logger.log(">ColonelsHand.hasBox()");
		Logger.log("<ColonelsHand.hasBox()");
		return hasBox;
	}

	/**
	 * beallitja megfelelore hogy van-e nala doboz
	 *
	 * @param hasBox true van false nincs
	 */
	public void setHasBox(boolean hasBox) {
		Logger.log(">ColonelsHand.setHasBox(boolean hasBox)");
		this.hasBox = hasBox;
		Logger.log("<ColonelsHand.setHasBox(boolean hasBox)");
	}
}
