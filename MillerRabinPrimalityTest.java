/*
 * filename: MillerRabinPrimalityTest
 * 
 * log: 1.0 11/22/2015 
 * 			The main logic was implemented using the algorithm as described
 * 			in Chapter 7. RSA Cryptosystem pg no.191
 * 		1.1 11/23/2015
 * 			Updated the square-and-multiply method for errors while computing
 * 			the mod of base^exp
 */

/**
 * The following code test a given number n with given integer a for prime numbers.
 * 
 * @author DhruvGala
 *
 */
public class MillerRabinPrimalityTest {

	
	/**
	 * The Miller-Rabin-Algorithm logic is applied here. This is similar to the
	 * pseudocode provided in the book. pg no 191
	 * 
	 * @param n
	 * @param a
	 * @return
	 */
	public static boolean checkIfPrime(int n,int a){
		
		int _p = n - 1,u = 0;						// n-1 is assigend to _p
		
		
		
		//let's first determine the value of u
		//Since n-1 = 2^u . r
		while(true){
			int denominator = (int)(Math.pow(2, u));
			if( _p % denominator != 0){
				u--;
				break;
			}
			u++;
		}
		
		
		//then compute r
		int r = _p / ((int)(Math.pow(2, u)));
		
		// z = a^r mod n
		//compute using the square-and-multiply method
		int z = squareAndMultiply(a, r, n);
		
		if(z!=1 && z!= _p){
			for(int j = 1; j < u-1; j++){
				z = ((int)Math.pow(z, 2)) % n;
				
				if(z == 1){
					return false;
				}
			}
			if(z != _p){
				return false;
			}
		}
		return true;
	}
	
	
	
	/**
	 * The following method computes base^exp in modular arithmetic i.e.
	 * base^exp mod modular
	 * 
	 * @param base
	 * @param exp
	 * @param mod
	 * @return
	 */
	public static int squareAndMultiply(int base, int exp, int mod){
		int returnThis = 1;
		
		//get the binary bits for exp number
		String binary = Integer.toBinaryString(exp);
		
		//we compute the square-and-multiply as follows:
		//in the binary bit we go from left to right
		//for every 1 we square what we have and multiply to base mod modulus
		//for every 0 we square what we have and mod modulus
		for(int i=0;i<binary.length();i++){
			char bit = binary.charAt(i);
			if(bit == '1'){
				returnThis = ((int)Math.pow(returnThis, 2)) * base % mod;
			}
			else if(bit == '0'){
				returnThis = ((int)Math.pow(returnThis, 2)) % mod;
			}
		}
		return returnThis;
	}
}
