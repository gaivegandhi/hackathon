package com.sparkling.minds.consultancy.solutions;

/**
 * This class <b>NeighbourNumbers</b> is a solution to the problem of 
 * finding a pair of numbers which are adjacent to each other in the 
 * numeric system.
 * 
 * For example,
 * In an array of numbers like 12, 11, 20, 24, 25, 26
 * 
 * The program finds the following pairs which are adjacent to each other:
 * 
 * 12,11
 * 24,25
 * 25,26
 * 
 * The input is an un-ordered list of numbers in a byte array.
 * 
 * The output is a list of number pairs from the array which are adjacent 
 * as per the numeric system. 
 * 
 * @author gaive
 *
 */
public class NeighbourNumbers {
	
	public static void findNeighbourNumbers(byte []arr) {

		byte start  = 0;
		byte end    = (byte) (arr.length-1);
		byte middle = (byte) (arr.length/2);
		
		for (byte i=middle, j=middle; i>=0 || j<=end; i--, j++) {

			byte backward = (byte) ((Math.abs(i)-1<=start)?start:i-1);
			byte forward  = (byte) (((j+1)>=end)?end:j+1);	

			if(i<start || j<end) {
			
				if (Math.abs(arr[Math.abs(i)] - arr[backward])==1) {

					System.out.println("Index Position: "+backward+" "+"Value: "+arr[backward]);
					System.out.println("Index Position: "+i+" "+"Value: "+arr[Math.abs(i)]);
					System.out.println();
					
				}
			
				if (Math.abs(arr[j] - arr[forward])==1) {

					System.out.println("Index Position: "+j+" "+"Value: "+arr[j]);
					System.out.println("Index Position: "+forward+" "+"Value: "+arr[forward]);
					System.out.println();
					
				}
				
			}

		}
		
	}
	
	public static void main (String... args)  {

		byte []arr = {24,25,33,34,35,36,37};
			
		findNeighbourNumbers(arr);	
		
	}

}
