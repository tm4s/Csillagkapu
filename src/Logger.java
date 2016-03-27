/**
 * Created by danielkrausz on 23/03/16.
 */
public class Logger {
	public static int numberOfTabs = -1;
	
    public static void Log(String parameter) {
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
