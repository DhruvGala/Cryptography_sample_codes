/*
 * filename: MyCaesarCipher.java
 * 
 * Version: 1.0
 * 
 * Revisions: $Log initial version$
 */

import java.util.Scanner;

/**
 * @author DhruvGala (dmg7937@rit.edu)
 * 
 * The following code deciphers the caesar cipher/shift cipher by applying all possible
 * keys and obtaining the plain text for the cipher text.
 * 
 */

public class MyCaesarCipher extends CommonConvertingLogic{

	static String cipherText;
	static int key;

	
	/**
	 * The logic of decipering the cipher text is carried out here
	 */
	public static void decipher(){
		int[] cipherTextRingValue = convertThis(cipherText);
		int[] plainTextRingValue = new int[cipherText.length()];
		
		for(int i=0;i<cipherText.length();i++){
			if((cipherTextRingValue[i] - key)>=0){
				plainTextRingValue[i] = cipherTextRingValue[i] - key;
			}
			else{
				plainTextRingValue[i] = cipherTextRingValue[i] - key + 26;
			}
		}
		System.out.println("plain text: "+convertToString(plainTextRingValue));
	}
	

	
	/**
	 * This method takes the input from the user
	 * 
	 */
	public static void feedInput(){
		Scanner takeInput = new Scanner(System.in);
		
		System.out.print("Enter cipher text: ");
		cipherText = takeInput.nextLine().toUpperCase();
		
		takeInput.close();
	}
	
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
	
		feedInput();
		
		System.out.println("--------------------------------");
		for(int i=1;i<26;i++){
			key = i;
			System.out.println("For key: "+key);
			decipher();
			System.out.println("-------------------------------");
		}
	}
}
