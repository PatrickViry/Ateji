package com.ateji.px.comprehension.exercises;

import java.util.HashMap;
import java.util.Map;

/**
 * Try to complete the main method. The goal is to write comprehensions 
 * whose result is given in the comments.
 * 
 * Data are defined as static fields (see below).
 */
public class Questions {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args)
	{		
		// Computes the sum of all elements in the given data.	   
		
		// Filter the element in the given data that are more than 10
		
		// Count of elements in the given data that are equal to 100.
		
		// some is true iff *some* element of the booleanData data is true
		
		// Computes the minimum of all elements in the given data.

		// Filter the value (not the key) in the given map that are more than 10.

	}

	public static int[] data;
	static {
final int [ ] freshVariable1 =new int [1000];{int freshVariable2 =0;for(int i : new apx . util . RangeInteger (0 , ((1000)- 1 ))){freshVariable1 [freshVariable2 ]=(int)Math.round(100*Math.sin(i));freshVariable2 ++;}}data =freshVariable1 ; }
public static int[] keys = data;
	public static Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	static {
		for ( int key : keys) {
map.put(key, Math.abs(key));
	}
}
	public static boolean[] booleanData;

static {
final boolean [ ] freshVariable3 =new boolean [1000];{int freshVariable4 =0;for(int i : new apx . util . RangeInteger (0 , ((1000)- 1 ))){freshVariable3 [freshVariable4 ]=(i%19 == 1);freshVariable4 ++;}}booleanData =freshVariable3 ; }
}
