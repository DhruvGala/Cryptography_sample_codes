/**
 * 
 * @author DhruvGala
 *
 * Version: <1.0> basic algorithm to implement the Eulid's Algorithm when computing
 * 			GCD of two large numbers.
 */
public class EuclidAlgoGCD {

	/**
	 * This method computes the GCD of 2 numbers using the 
	 * Euclid's Algorithm.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int calculateGCD(int a, int b){
		if(b == 0){
			return a;
		}
		else{
			return calculateGCD(b, a%b);
		}
	}
	
}
