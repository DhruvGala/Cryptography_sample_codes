/*
 * filename: MultiplicativeInverse.java
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
 * The following code computes the multiplicative inverse of a number if it exists. 
 * The multiplicative inverse can be given as "a * a^-1 = 1 mod m"
 *
 */

public class MultiplicativeInverse {

	private static int a,m;
	
	
	/**
	 * The following method computes the multiplicative inverse by trying-and-error method
	 * 
	 */
	public static int computeMI(){
		boolean found = false;
		int iteration = 1,temp;
		
		while(!found && (iteration <= m)){
			temp = a * ++iteration;
			if(temp % m == 1){
				found = true;
			}
		}
		return iteration;
	}
	
	
	
	/**
	 * If the value of a is completely divided by m ,
	 * then multiplicative inverse of a doesn't exist.
	 * 
	 * @return
	 */
	public static boolean doesMIExist(){
		return (!(a % m == 0));
	}
	
	
	
	/**
	 * The following method takes the input from the user for the values of
	 * a and m.
	 * 
	 */
	public static void feedInput(){
		
		Scanner takeInput = new Scanner(System.in);
		
		System.out.print("Enter a: ");
		a = Integer.parseInt(takeInput.nextLine());
		
		System.out.print("Enter m: ");
		m = Integer.parseInt(takeInput.nextLine());
		
		takeInput.close();
	}
	
	
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		feedInput();
		if(doesMIExist()){
			System.out.println("The multiplicative inverse: "+computeMI());
		}
		else{
			System.out.println("Multiplicative inverse doesn't exist!");
		}
		
	}
}
