/*
 * filename: MyAffineCipher.java
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
 * The following java file is used to compute the plaintext from the
 * cipher text ciphered using the Affine cipher.
 * 
 */

public class MyAffineCipher extends CommonConvertingLogic{

	static String cipherText;
	static int a,aInv,b;
	
	
	/**
	 * The logic of deciphering the cipher text is carried out here.
	 * 
	 */
	public static void decipher(){
		int[] cipherTextRingValue = convertThis(cipherText);
		int[] plainTextRingValue = new int[cipherText.length()];
		int y,temp;
		
		for(int i=0;i<cipherText.length();i++){
			y = cipherTextRingValue[i];
			temp = (aInv * (y - b)) % 26;
			
			if(temp >= 0){
				plainTextRingValue[i] = temp;
			}
			else{
				plainTextRingValue[i] = temp + 26;
			}
		}
		
		System.out.println("plain text: "+convertToString(plainTextRingValue));
	}
	
	
	/**
	 * This method computes the a^-1 for the given a, which will be used 
	 * to decipher the ciphertext.
	 * 
	 */
	public static void computeAInverse(){

		int temp;
		int[] arrayA = {1,3,5,7,9,11,15,17,19,21,23,25};
		
		for(int i=0;i<12;i++){
			temp = a * arrayA[i];
			
			if(temp % 26 == 1){
				aInv = arrayA[i];
			}
		}
		
		//System.out.println("\nValue of A inverse: " +aInv);
	}
	
	
	/**
	 * This method is used to take input from the user.
	 * 
	 */
	public static void feedInput(){
		Scanner takeInput = new Scanner(System.in);
		
		System.out.print("Enter the value of a: ");
		a = Integer.parseInt(takeInput.nextLine());
		
		System.out.print("Enter the value of b: ");
		b = Integer.parseInt(takeInput.nextLine());
		
		System.out.print("Enter cipher text: ");
		cipherText = takeInput.nextLine();
		
		takeInput.close();
	}
	
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		feedInput();
		cipherText = cipherText.toUpperCase();
		
		computeAInverse();
		
		System.out.println("------------------------------------------------");
		decipher();
		System.out.println("------------------------------------------------");
	}
}
