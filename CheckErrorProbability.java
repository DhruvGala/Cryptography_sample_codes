/*
 * filename: CheckErrorProbability.java
 * 
 * log: 1.0 11/22/2015
 * 			Completed the basic functionality of checking the error probability.
 * 		
 * 		1.1 11/23/2015
 * 			Updated the record error method for possible errors.
 * 
 */

/**
 * The following java code computes the error Probability of Miller-Rabin Primality test
 * to give an incorrect answer for the integer between 90,000 to 100,000 range.
 * 
 * @author DhruvGala
 *
 * The output computes k for each n and hence computes the error probability for each n
 * given by error probability = k / (n-2) it then prints it out.
 * Next compute the max error and hence print the numbers with max error
 */
public class CheckErrorProbability {

	static float[] errorP = new float [10000];
	
	
	/**
	 * The following method determines if the given n is prime or not
	 * 
	 * @param n
	 * @return
	 */
	public static boolean isPrime(int n){
		for(int i=2;i<n;i++){
			if(n % i == 0){
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * the following method checks if an incorrect answer is generated from MR test
	 * comparing it with isPrime method and then for every incorrect answer increase
	 * the counter k.
	 * 
	 */
	public static void check(){
		
		int k=0;
		
		for(int n=90001;n<100000;n+=2){
			//for each n check if its prime
			boolean prime = isPrime(n);
			
			for(int a=2;a<=n-2;a++){
				//check if prime by using MR test
				boolean MR = MillerRabinPrimalityTest.checkIfPrime(n, a);
				
				//compare the results and increment the counter if incorrect
				if((MR && !prime) || (!MR && prime)){
					k++;
					//System.out.println("MR yes! prime no! a:"+a+",k:"+k);
				}
				/*if(!MR && prime){
					k++;
					System.out.println("MR no! prime yes! a:"+a+",k:"+k);
				}*/
			}
			
			recordError(k,n);	//record this error
			k=0;				//reinitialize k for next iteration
		}
	}
	
	
	
	/**
	 * This method records the error of each n in an array.
	 * 
	 * @param k
	 * @param n
	 */
	public static void recordError(float k, float n){
		
		float error = k / (n-2);
		int index = (int) n-90000;
		errorP[index] = error;
		System.out.println("n:"+n+" --> k:"+k+" --> error:"+error);
	}
	
	
	
	/**
	 * This method gets the maximum error probability and hence prints all the
	 * integers n between 90,000 to 100,000 that have the max error probability.
	 * 
	 */
	public static void getMaxTen(){
		float max = errorP[0];
		for(int i=1;i<errorP.length;i++){
			if(errorP[i] > max){
				max = errorP[i]; 
			}
		}
		
		System.out.println("================================");
		System.out.println("Max Error Probability:"+max);
		System.out.println("================================");
		System.out.println("The numbers that have max error probability are: ");
		
		//print the numbers that have the max error probability
		for(int i=1;i<errorP.length;i++){
			if(errorP[i] == max){
				int number = i+90000;
				System.out.print(number+" ");
			}
		}
	}
	
	
	/**
	 * The main method 
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		check();
		getMaxTen();
	}
}
