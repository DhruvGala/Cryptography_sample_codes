/*
 * filename: CommonConvertingLogic.java
 * 
 * Version: 1.0
 * 
 * Revisions: $Log initial version$
 */



/**
 * This is just the common logic of conversion used in the other java files 
 * for faster computation using integer ring values.
 * 
 * @author DhruvGala
 *
 */
public class CommonConvertingLogic {

	/**
	 * The following method convert the integer array back to String
	 * 
	 * @param intArray
	 * @return
	 */
	public static String convertToString(int[] intArray){
		char charArray[] = new char[intArray.length];
		
		for(int i=0;i<intArray.length;i++){
			charArray[i] =(char) (intArray[i]+65);
		}
		String plainText = new String(charArray);
	
		return plainText;
	}
	
	
	
	/**
	 * This method converts the plain text to integer ring values for faster computation.
	 * 
	 * @param thisString
	 * @return
	 */
	public static int[] convertThis(String thisString){
		int[] decodeThis = new int[thisString.length()];
		
		for(int i=0;i<thisString.length();i++){
			decodeThis[i]=thisString.charAt(i)-65;
		}
		
		return decodeThis;
	}
}
