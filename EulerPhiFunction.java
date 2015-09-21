/*
 * filename: EulerPhiFunction.java
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
 */
public class EulerPhiFunction {

	
	/**
	 * 
	 * this method computes the GCD by recursion.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int findGCD(int a, int b){
		
		if(b == 0){
			return a;
		}
		
		return findGCD(b, a%b);
		
	}
	
	
	/**
	 * The logic here is check if GCD of all integer between 0 to m with their
	 * is equal to 1, if yes then that integer is relatively prime to m.
	 * 
	 * @param m
	 */
	public static void findRelativelyPrime(int m){
		
		for(int iteration = 0;iteration < m; iteration++){
			if(findGCD(m, iteration) == 1){
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
