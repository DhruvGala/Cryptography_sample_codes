import java.util.Scanner;

/*
 * filename: MyDES.java
 * 
 * version: 1.0 10/06/2015
 * 			1.1 10/07/2015
 * 
 * Log: <version 1.0>
 * 		implemented the S-box logic along with f function logic 
 * 		and IP and inverse IP logic involved in DES.
 * 		Now working on developing the entire runs that will call f-function
 * 		16 times throughout to run the DES algorithm.
 * 		
 * 	<version 1.1>
 * 		implemented the complete functionality of the DES algorithm
 * 		now testing and debugging code to update if any error found.
 * 		Also working now on making the code compact and more 
 * 		object oriented.
 */

/**
 * The following code is the basic implementation of the DES algorithm
 * to encrypt the data using plain text of 64bits and a key k of 64 bits.
 * 
 * @author DhruvGala
 *
 */
public class MyDES {

	static int[] keyDash;
	/**
	 * This method is a generalized method for implementing bitwise XOR operator.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int[] XOR(int[] a, int[] b){
		int len = a.length;
		int[] c = new int[len];
		for(int i = 0;i < len; i++){
			if(a[i] == b[i]){
				c[i] = 0;
			}
			else{
				c[i] = 1;
			}
		}
		//String result = new String(c); 
		
		return c;
	}
	
	
	
	/**
	 * This method implements the initial permutation logic involved in the
	 * DES algorithm.
	 * 
	 * @param x
	 * @return
	 */
	public static int[] IP(int[] x){
		int lookUpTableIP[] = {
				58,50,42,34,26,18,10,2,
				60,52,44,36,28,20,12,4,
				62,54,46,38,30,22,14,6,
				64,56,48,40,32,24,16,8,
				57,49,41,33,25,17,9,1,
				59,51,43,35,27,19,11,3,
				61,53,45,37,29,21,13,5,
				63,55,47,39,31,23,15,7};
		
		int IPed[] = new int[x.length];
		
		for(int i=0;i<x.length;i++){
			IPed[i] = x[lookUpTableIP[i]-1];
		}
		
		return IPed;
	}
	
	
	
	/**
	 * This method implements the inverse of initial permutation logic involved in the
	 * DES algorithm.
	 * 
	 * @param x
	 * @return
	 */
	public static int[] inverseIP(int[] x){
		int lookUpTableInverseIP[] = {
				40,8,48,16,56,24,64,32,
				39,7,47,15,55,23,63,31,
				38,6,46,14,54,22,62,30,
				37,5,45,13,53,21,61,29,
				36,4,44,12,52,20,60,28,
				35,3,43,11,51,19,59,27,
				34,2,42,10,50,18,58,26,
				33,1,41,9,49,17,57,25};
		
		int IPedInv[] = new int[x.length];
		
		for(int i=0;i<x.length;i++){
			IPedInv[i] = x[lookUpTableInverseIP[i]-1];
		}
		
		return IPedInv;
	}
	
	
	/**
	 * This method implements the Expansion logic involved in DES algorithm, 
	 * where the 32 bit R is expanded to 48 bit.
	 * 
	 * @param R
	 * @return
	 */
	public static int[] Expansion(int[] R){
		int ExpansionTable[] = {
				32,1,2,3,4,5,
				4,5,6,7,8,9,
				8,9,10,11,12,13,
				12,13,14,15,16,17,
				16,17,18,19,20,21,
				20,21,22,23,24,25,
				24,25,26,27,28,29,
				28,29,30,31,32,1};
		
		int ExR[] = new int[48];
		
		for(int j=0; j<ExpansionTable.length;j++){
			ExR[j] = R[ExpansionTable[j]-1];
		}
		
		return ExR;
	}
	
	/*public static int[] convertSixBitToFourBit(int whichBox, int[] result){
		
	}*/
	
	
	/**
	 * The heart of the S-box logic is implemented in here.
	 * 
	 * @param x
	 * @return
	 */
	public static int[] Sbox(int[] x){
		int[][] Sresult = new int[8][6];
		
		int[][] S1 ={
				{14,4,13,1,2,15,11,8,3,10,6,12,5,9,0,7},
				{0,15,7,4,14,2,13,1,10,6,12,11,9,5,3,8},
				{4,1,14,8,13,6,2,11,15,12,9,7,3,10,5,0},
				{15,12,8,2,4,9,1,7,5,11,3,14,10,0,6,13}
		};
		
		int[][] S2 ={
				{15,1,8,14,6,11,3,4,9,7,2,13,12,0,5,10},
				{3,13,4,7,15,2,8,14,12,0,1,10,6,9,11,5},
				{0,14,7,11,10,4,13,1,5,8,12,6,9,3,2,15},
				{13,8,10,1,3,15,4,2,11,6,7,12,0,5,14,9}	
		};
		
		int[][] S3 ={
				{10,0,9,14,6,3,15,5,1,13,12,7,11,4,2,8},
				{13,7,0,9,3,4,6,10,2,8,5,14,12,11,15,1},
				{13,6,4,9,8,15,3,0,11,1,2,12,5,10,14,7},
				{1,10,13,0,6,9,8,7,4,15,14,3,11,5,2,12}
		};
		
		int[][] S4 ={
				{7,13,14,3,0,6,9,10,1,2,8,5,11,12,4,15},
				{13,8,11,5,6,15,0,3,4,7,2,12,1,10,14,9},
				{10,6,9,0,12,11,7,13,15,1,3,14,5,2,8,4},
				{3,15,0,6,10,1,13,8,9,4,5,11,12,7,2,14}
		};
		
		int[][] S5 ={
				{2,12,4,1,7,10,11,6,8,5,3,15,13,0,14,9},
				{14,11,2,12,4,7,13,1,5,0,15,10,3,9,8,6},
				{4,2,1,11,10,13,7,8,15,9,12,5,6,3,0,14},
				{11,8,12,7,1,14,2,13,6,15,0,9,10,4,5,3}
		};
		
		int[][] S6 ={
				{12,1,10,15,9,2,6,8,0,13,3,4,14,7,5,11},
				{10,15,4,2,7,12,9,5,6,1,13,14,0,11,3,8},
				{9,14,15,5,2,8,12,3,7,0,4,10,1,13,11,6},
				{4,3,2,12,9,5,15,10,11,14,1,7,6,0,8,13}
		};
		
		int[][] S7 ={
				{4,11,2,14,15,0,8,13,3,12,9,7,5,10,6,1},
				{13,0,11,7,4,9,1,10,14,3,5,12,2,15,8,6},
				{1,4,11,13,12,3,7,14,10,15,6,8,0,5,9,2},
				{6,11,13,8,1,4,10,7,9,5,0,15,14,2,3,12}
		};
		
		int[][] S8 ={
				{13,2,8,4,6,15,11,1,10,9,3,14,5,0,12,7},
				{1,15,13,8,10,3,7,4,12,5,6,11,0,14,9,2},
				{7,11,4,1,9,12,14,2,0,6,10,13,15,3,5,8},
				{2,1,14,7,4,10,8,13,15,12,9,0,3,5,6,11}
		};
		
		for(int i=0;i<8;i++){
			for(int j=0;j<6;j++){
				Sresult[i][j] = x[j];
			}
		}
		
		int[] result = new int[32],binary;
		int position = 0,number;
		
		for(int i=0;i<8;i++){
			int row = (int) (Sresult[i][0]*2 + Sresult[i][5]);
			int column = (int) (Sresult[i][1]*Math.pow(2, 3) + Sresult[i][2]*Math.pow(2, 2)
					+ Sresult[i][3]*2 + Sresult[i][4]);
			
		//	System.out.println("\niteration:"+(i+1)+" row:"+row+" column:"+column);
			/**
			 * The switch logic implements the nonlinear part of the DES algorithm,
			 * i.e. the S-boxes.
			 * 
			 */
			switch(i){
			case 0:
				number = S1[row][column];
				binary = convertToBinary(number);
				for(int j=0;j<4;j++){
					result[position++] = binary[j];
					//System.out.print(binary[j]);
				}
				break;
			case 1:
				number = S2[row][column];
				binary = convertToBinary(number);
				for(int j=0;j<4;j++){
					result[position++] = binary[j];
				}
				break;
			case 2:
				number = S3[row][column];
				binary = convertToBinary(number);
				for(int j=0;j<4;j++){
					result[position++] = binary[j];
				}
				break;
			case 3:
				number = S4[row][column];
				binary = convertToBinary(number);
				for(int j=0;j<4;j++){
					result[position++] = binary[j];
				}
				break;
			case 4:
				number = S5[row][column];
				binary = convertToBinary(number);
				for(int j=0;j<4;j++){
					result[position++] = binary[j];
				}
				break;
			case 5:
				number = S6[row][column];
				binary = convertToBinary(number);
				for(int j=0;j<4;j++){
					result[position++] = binary[j];
				}
				break;
			case 6:
				number = S7[row][column];
				binary = convertToBinary(number);
				for(int j=0;j<4;j++){
					result[position++] = binary[j];
				}
				break;
			case 7:
				number = S8[row][column];
				binary = convertToBinary(number);
				for(int j=0;j<4;j++){
					result[position++] = binary[j];
				}
				break;
			}
		}
		
		return result;
	}
	
	
	/**
	 * This method converts the number to its binary equivalent in integer array format.
	 * 
	 * @param thisNumber
	 * @return
	 */
	public static int[] convertToBinary(int thisNumber){
		String binary = Integer.toString(thisNumber,2);
		
		
		if(binary.length() != 4){
			switch(binary.length()){
			case 3:
				binary = "0" + binary;
				break;
			case 2:
				binary = "00" + binary;
				break;
			case 1:
				binary = "000" + binary;
				break;
			case 0:
				binary = "0000";
				break;
			}
		}
		
		//System.out.println("In convert ->"+binary);
		
		//System.out.print("result->");
		int[] result = new int[4]; 
		
		if(binary.charAt(0) == 0){
			result[0] =0;
		}
		else
		{
			result[0] =1;
		}
		
		for(int i=1;i<binary.length();i++){
			result[i] = Integer.parseInt(binary.substring(i-1,i));
			//System.out.println(result[i]);
		}
		//System.out.println();
		
		return result;
	}
	
	
	/**
	 * This method performs the permutation function involved after the S-boxes logic
	 * in the DES algorithm.
	 * 
	 * @param thisData
	 * @return
	 */
	public static int[] Permutation(int[] thisData){
		int Permutation[] = {
				16,7,20,21,29,12,28,17,
				1,15,23,26,5,18,31,10,
				2,8,24,14,32,27,3,9,
				19,13,30,6,22,11,4,25
		};
		
		int result[] = new int[thisData.length];
		
		for(int i=0;i<thisData.length;i++){
			result[i] = thisData[Permutation[i]-1];
		}
		
		return result;
	}
	
	
	/**
	 * This method implements the f-function logic involved in the DES algorithm.
	 * 
	 * @param R
	 * @param k
	 * @return
	 */
	public static int[] functionF(int[] R,int[] k){
		int[] ExR = Expansion(R);
		
		/*System.out.println("----------After Expansion----------");
		for(int i=0;i<ExR.length;i++){
			System.out.print(ExR[i]);
		}
		System.out.println("\nlength->"+ExR.length);*/
		
		int[] result = XOR(ExR,k);
		/*System.out.println("\n----------After XOR----------");
		for(int i=0;i<result.length;i++){
			System.out.print(result[i]);
		}
		System.out.println("\nlength->"+result.length);*/
		
		result = Sbox(result);
		/*System.out.println("\n----------After S-boxes----------");
		for(int i=0;i<result.length;i++){
			System.out.print(result[i]);
		}
		System.out.println("\nlength->"+result.length);*/
		
		result = Permutation(result);
		/*System.out.println("\n----------After Permutation----------");
		for(int i=0;i<result.length;i++){
			System.out.print(result[i]);
		}
		System.out.println("\nlength->"+result.length);*/
		
		return result;
	}
	
	
	/**
	 * This method takes the input of 64 bit plaintext and key.
	 *  
	 * @return
	 */
	public static int[][] feedInput(){
		Scanner takeInput = new Scanner(System.in);
		
		int[][] sendThis = new int[2][64];
		//System.out.print("Enter the plaintext: ");
		String input = takeInput.nextLine();
		if(input.charAt(0) == 0){
			sendThis[0][0] =0;
		}
		else
		{
			sendThis[0][0] =1;
		}
		
		for(int i=1;i<input.length();i++){
			sendThis[0][i] = Integer.parseInt(input.substring(i-1,i));
		}
		
		//System.out.print("Enter the key:");
		input = takeInput.nextLine();
		if(input.charAt(0) == 0){
			sendThis[1][0] =0;
		}
		else
		{
			sendThis[1][0] =1;
		}
		
		for(int i=1;i<input.length();i++){
			sendThis[1][i] = Integer.parseInt(input.substring(i-1,i));
		}
		takeInput.close();
		return sendThis;
	}
	
	
	/**
	 * This method is the actual logic of defining the PC-1 to generate the keys
	 * 
	 * @param k
	 * @return
	 */
	public static int[] PC1(int[] k){
		int PC1table[] = {
				57,49,41,33,25,17,9,1,
				58,50,42,34,26,18,10,2,
				59,51,43,35,27,19,11,3,
				60,52,44,36,63,55,47,39,
				31,23,15,7,62,54,46,38,
				30,22,14,6,61,53,45,37,
				29,21,13,5,28,20,12,4
				};
		
		int key[] = new int[56];
		
		for(int i=0;i<PC1table.length;i++){
			key[i] = k[PC1table[i]-1];
		}
		
		return key;
	}
	
	
	/**
	 * This method is the logic to implement the PC2 mechanism
	 * for generating the keys.
	 * 
	 * @param k
	 * @return
	 */
	public static int[] PC2(int[] k){
		//System.out.println("i/p length:"+k.length);
		int PC2table[] = {
			14,17,11,24,1,5,3,28,
			15,6,21,10,23,19,12,4,
			26,8,16,7,27,20,13,2,
			41,52,31,37,47,55,30,40,
			51,45,33,48,44,49,39,56,
			34,53,46,42,50,36,29,32
		};
		
		int key[] = new int[48];
		
		for(int i=0;i<PC2table.length;i++){
			key[i] = k[PC2table[i]-1];
		}
		
		return key;
	}
	
	
	/**
	 * This method performs the left shift operation on the input bitstream
	 * 
	 * @param bitstream
	 * @return
	 */
	public static int[] LS1(int[] bitstream){
		int temp = bitstream[0];
		
		for(int i=1;i<bitstream.length;i++){
			bitstream[i-1] = bitstream[i];
		}
		
		bitstream[bitstream.length-1] = temp;
		
		return bitstream;
	}
	
	
	
	/**
	 * this method carries out the transformation on the key side logic.
	 * 
	 * @return
	 */
	public static int[] transform1(){
		int[] C = new int[28],D = new int[28];
		
		/**
		 * first we split the input key into two parts.
		 * 
		 */
		for(int i =0;i<keyDash.length/2;i++){
			C[i] = keyDash[i];
			D[i] = keyDash[i+28];
		}
		
		C = LS1(C);
		D = LS1(D);
		int[] returnThis = new int[56];
		
		for(int i=0;i<28;i++){
			returnThis[i] = C[i];
			returnThis[i+28] = D[i];
		}
		
		keyDash = returnThis;
		int[] result = PC2(returnThis);
		
		return result;
	}
	
	
	/**
	 * This method generates all the different keys needed for each rounds.
	 * 
	 * @return
	 */
	public static int[] geenerateKeys(){
		int[] key = transform1();
		//int[] keyD = PC2(key);
		
		return key;
	}
	
	
	/**
	 * This method carries out the actual DES process by calling other functional methods.
	 * 
	 * @param plaintext
	 * @param key
	 * @return
	 */
	public static int[] carryOutDES(int[] plaintext, int[] key){
		keyDash = PC1(key);
		
		int[] x = IP(plaintext);
		
		
		for(int iterations = 0 ; iterations<16; iterations++){
			key = geenerateKeys();
			x = Rounds(x, key);
			
		}
		
		x = inverseIP(x);
		
		return x;
		
	}
	
	
	/**
	 * The logic of this method is to manipulate the bits and proceed with the 
	 * encryption process of DES algorithm.
	 * 
	 * @param x
	 * @param k
	 * @return
	 */
	public static int[] Rounds(int[] x,int[] k ){
		int[] L = new int[32],R = new int[32];
		
		for(int i =0;i<x.length/2;i++){
			L[i] = x[i];
			R[i] = x[i+32];
		}
		
		//int[] Lnext = R;
		int[] temp = functionF(R, k);
		int[] result = XOR(temp, L);
		
		int[] returnThis = new int[64];
		//returnThis = R;
		
		for(int i=0;i<32;i++){
			returnThis[i] = R[i];
			returnThis[i+32] = result[i];
		}
		
		return returnThis;
	}
	
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int[][] data = feedInput();
		int[] x = data[0];
		int[] k = data[1];
		
		int[] ciphertext = carryOutDES(x, k);
		
		System.out.println("The encrypted cipher text: ");
		for(int i=0;i<ciphertext.length;i++){
			System.out.print(ciphertext[i]);
		}
	}
}
