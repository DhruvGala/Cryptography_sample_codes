/*
 * filename: EulerPhiFunction.java
 * 
 * Version: 1.1
 * 
 * Revisions: 1.0 Basic implementation with in built function to compute the GCD.
 * 			  1.1 Implemented Eulid'd Algorithm to compute the GCD amongst two large numbers.
 */

import java.util.Scanner;

/**
 * 
 * @author DhruvGala
 *
 */
public class EulerPhiFunction {
	
	
	/**
	 * The logic here is check if GCD of all integer between 0 to m with their
	 * is equal to 1, if yes then that integer is relatively prime to m.
	 * 
	 * @param m
	 */
	public static void findRelativelyPrime(int m){
		
		for(int iteration = 0;iteration < m; iteration++){
			if(EuclidAlgoGCD.calculateGCD(m, iteration) == 1){
				System.out.print(iteration+" ");
			}
		}
	}
	
	
	/**
	 * this method takes input from the user.
	 * 
	 * @return
	 */
	public static int feedInput(){
		Scanner takeInput = new Scanner(System.in);
		
		System.out.print("Enter the value of m: ");
		int m = Integer.parseInt(takeInput.nextLine());
		
		takeInput.close();
		
		return m;
	}
	
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int m = feedInput();
		System.out.print("Euler's Phi function of "+m+" : ");
		findRelativelyPrime(m);
	}
}
