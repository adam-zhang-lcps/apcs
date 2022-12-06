/**
 * This class controls a micro:bit via bluetooth. It inherits almost 
 * all its functionality from the abstract class Robot. The only methods it needs here are constructors, 
 * which call a function that checks whether or not it is a micro:bit.
 * 
 * Mike Yuan and Bambi Breewer, BirdBrain Technologies LLC
 * November 2018
 */
public class Microbit extends Robot {
	
	/** This function tries to read sensor 4 (the Hummingbird battery) to determine whether 
     * the device is a Hummingbird or a micro:bit. It returns true if the device is a micro:bit. */
    private boolean isMicrobit() {
		StringBuilder newURL = new StringBuilder(baseUrl);
		String testURL = (newURL.append("in/isMicrobit/static/")
				.append(deviceInstance)).toString();

		String stringResponse = sendHttpRequest(testURL);
		if (stringResponse.equals("false")) {
			System.out.println("Error: Device " + deviceInstance + " is not a Microbit");
			return false;
		} else if (stringResponse.equals("Not Connected")) {
			System.out.println("Error: Device " + deviceInstance + " is not connected.");
			return false;
		} else {
			return true;
		}
		/*
    	try { 
	    	StringBuilder newURL = new StringBuilder(baseUrl);
	        String testURL = (newURL.append("in/")
	                .append("sensor/4/")
	                .append(deviceInstance)).toString();
	   	
	       requestUrl = new URL(testURL);
	       connection = (HttpURLConnection) requestUrl.openConnection();
	       connection.setRequestMethod("GET");
	       connection.setDoOutput(true);
	
	       String stringResponse = verifyResponse();
	       if (stringResponse.equals("255")) return true;
	       else {
	    	   System.out.println("Error: Device "+deviceInstance+" is not a micro:bit");
	    	   return false;
	       }
		} catch (IOException e) {
	       System.out.println("Error: Device " + deviceInstance + " is not connected");
	       return false;
	   }
    	*/
    }
   

    /**
     * default constructor for the library. Construct the baseUrl and set the default device to be A
     */
    public Microbit() {
    	deviceInstance = "A";
        if (!isConnectionValid()) System.exit(0);
        if (!isMicrobit()) System.exit(0);
    }

    /**
     * constructor for the library. Construct the baseUrl and set the default device to be input.
     *
     * @param device the input device that will be specified by the user.
     */
    public Microbit(String device) {
    	if (!((device == "A")||(device == "B")||(device == "C"))) {
        	System.out.println("Error: Device must be A, B, or C.");
        	System.exit(0);
        } else {
        	deviceInstance = device;
        	if (!isConnectionValid()) System.exit(0);
        	if (!isMicrobit()) System.exit(0);       
        }
    }
}
    
    