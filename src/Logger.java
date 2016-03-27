/**
 * Logger osztály. A függvényhívások követésére használható.
 */
public class Logger {
	private static int numberOfTabs = -1;
	private static boolean isOn = true;
	
	public boolean getIsOn() {
		return isOn;
	}
	
	public void setIsOn(boolean value) {
		isOn = value;
	}
	
    public static void log(String parameter) {
    	if(isOn){
	    	if(parameter.charAt(0) == '>') {
	    		++numberOfTabs;
	    	}
	    		
	    	for(int i = 0; i < numberOfTabs; ++i) {
	    		System.out.print("\t");
	    	}
	    	System.out.println(parameter);
	    	
	    	if(parameter.charAt(0) == '<') {
	    		--numberOfTabs;
	    	}
    	}
    }
}
