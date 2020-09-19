package com.sparkling.minds.consultancy.solutions;

import java.util.Scanner;

public class BinarySearch {

	static char[] sortCharArray(char[] cArray, char order) {
		
		int length = cArray.length;
		
		char temp;
		
		if (order == 'a' || order == 'A') {
			
			for (int i = 0; i < length; i++) {
				
				for (int j = i + 1; j < length; j++) {
					
					if (cArray[i] > cArray[j]) {
						
						temp = cArray[i];
						cArray[i] = cArray[j];
						cArray[j] = temp;
					
					}
				
				}
			
			}
			
		}else if (order == 'd' || order == 'D') {
			
			for (int i = 0; i < length; i++) {
				
				for (int j = i + 1; j < length; j++) {
					
					if (cArray[i] < cArray[j]) {
						
						temp = cArray[i];
						cArray[i] = cArray[j];
						cArray[j] = temp;
					
					}
				}
				
			}
			
		}else {
			
			System.out.println("Value Of Parameter For Sorting Order Is Not Valid");
			System.out.println("Exiting From The Program");
			
			System.exit(0);
			
		}
		
		return cArray;
		
	}
	
	static String binarySearchCharArray(char[] cArray, char c) {
		
		int length = cArray.length;
		int first = 0;
		int last = length-1;
		
		int mid = (first + last)/2;
		
		while( first <= last ){  
		
			if ( cArray[mid] < c ){  
		        
				first = mid + 1;     
		      
			}else if ( cArray[mid] == c ){
				
				return "Present";  
		        		      
			}else{  
		         
				last = mid - 1;  
		    }  
		    
			mid = (first + last)/2;  
		
		}    
		
		return "Absent";
	
	}
	
	static void printCharArray(char[] c) {
		
		for (int i=0; i<c.length; i++) {
			
			System.out.println(c[i]);
		
		}
		
	}
	
	public static void main (String ...args) {
		
		 int length;
		 char c;
		 char[] cArray;
		 
		 Scanner s = new Scanner(System.in);
	     
		 System.out.println("Enter Number Of Characters You Want In An Array:");
		 
	     length = s.nextInt();
	     
	     cArray = new char[length];
	     
	     System.out.println("Enter Characters:");
	     
	     for (int i = 0; i < length; i++){
	    	 
	            cArray[i] = s.next().charAt(0);
	            
	     }
	     
	     System.out.println("Enter Character To Be Searched: ");
		 
	     c = s.next().charAt(0);

	     // a or A for ascending order, d or D for descending order.
	     sortCharArray(cArray,'a');
	     
	     System.out.println("The Character "+c+" Is "+binarySearchCharArray(cArray, c)+" In The Array");
		
	     printCharArray(cArray);
		
	}
}
