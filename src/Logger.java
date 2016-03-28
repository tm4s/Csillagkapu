/**
 * Logger osztaly. A fuggvenyhivasok kovetesere hasznalhato.
 */
public class Logger {
	private static int numberOfTabs = -1;
	private static boolean isOn = true;

	/**
	 * Logger ki- es bekapcsolasa
	 *
	 * @param value
	 */
	public static void setIsOn(boolean value) {
		isOn = value;
	}

	/**
	 * A log kiirasa
	 *
	 * @param parameter kiirando szoveg
	 */
	public static void log(String parameter) {
		if (isOn) {
			if (parameter.charAt(0) == '>') {
				++numberOfTabs;
			}

			for (int i = 0; i < numberOfTabs; ++i) {
				System.out.print("\t");
			}
			System.out.println(parameter);

			if (parameter.charAt(0) == '<') {
				--numberOfTabs;
			}
		}
	}
}
