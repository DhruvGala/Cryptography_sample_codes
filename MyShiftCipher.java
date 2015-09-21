/*
 * filename: MyShiftCipher.java
 * 
 * Version: 1.0
 * 
 * Revisions: $Log initial version$
 */

import java.util.Scanner;


/**
 * 
 * @author DhruvGala
 *
 * The following code uses the algorithm of shift/caesar cipher to encrypt the plain text
 * when provided with a key value. 
 *
 */

public class MyShiftCipher extends CommonConvertingLogic{

	private static String plainText;
	private static int key;
	
	
	
	/**
	 * The following method conducts the actual logic of encrypting the plain text as follows:
	 * 					y(i) = x(i) + key mod 26
	 * 
	 * @return
	 */
	public static String encrypt(){
		int[] plainTextRingValue = convertThis(plainText);
		int[] cipherTextRingValue = new int[plainText.length()];
		
		for(int i=0;i<plainText.length();i++){
			cipherTextRingValue[i] = (key + plainTextRingValue[i])%26;
		}
		
		return convertToString(cipherTextRingValue);
	}
	
	
	
	/**
	 * 
	 */
	public static void feedInput(){
		Scanner takeInput = new Scanner(System.in);

		System.out.print("Enter the key value: ");
		key = Integer.parseInt(takeInput.nextLine());
		
		System.out.print("Enter cipher text: ");
		plainText = takeInput.nextLine().toUpperCase();
		
		takeInput.close();
	}
	
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		feedInput();
		System.out.println("cipher text: "+encrypt());
	}
}
