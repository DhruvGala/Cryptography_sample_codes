/*
 * filename: MyVigenereCipher.java
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
 * The following java file uses the plain text as well as the key to encrypt the data
 * using the algorithm of vigenere cipher encryption. 
 * 
 */

public class MyVigenereCipher extends CommonConvertingLogic{

	
	private static String plainText,key;
	
	
	/**
	 * The key is wrap around the plain text for encrypting it to cipher text.
	 * 
	 * @return
	 */
	public static String encryptThis(){
		int[] plainTextRingValue = convertThis(plainText);
		int[] cipherTextRingValue = new int[plainText.length()];
		
		int[] wrapKey = convertThis(key);
		int j = 0;
		for(int i=0;i<plainText.length();i++){
			if(j >= wrapKey.length){
				j = 0;
			}
			cipherTextRingValue[i] = (wrapKey[j++]+ plainTextRingValue[i])%26;
			
		}
		
		return convertToString(cipherTextRingValue);
	}
	
	
	
	/**
	 * 
	 */
	public static void feedInput(){
		Scanner takeInput = new Scanner(System.in);

		System.out.print("Enter the key value: ");
		key = takeInput.nextLine().toUpperCase();
		
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
		System.out.println("The cipher text; "+encryptThis());
	}
}
