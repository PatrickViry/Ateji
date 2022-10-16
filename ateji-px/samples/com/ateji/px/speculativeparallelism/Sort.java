/****************************
 * Copyright (c) 2009 ATEJI *
 * All rights reserved.     *
 ****************************/
package com.ateji.px.speculativeparallelism;

import java.util.Arrays;

/**
 * Speculative execution is the execution of code whose result may not be needed.
 * This sample shows how speculative execution can be parallelized with Ateji PX.
 * 
 * This example sorts an array in a speculative fashion: two sorting algorithms
 * are run in parallel; the first to complete returns the final result and stops
 * further computation that became redundant.
 */
public class Sort {

	/**
	 * Two sorting algorithms are run in parallel. The first to complete returns
	 * the final result.
	 * 
	 * It is impossible to know which algorithm will return the result.
	 * Nevertheless, the value returned by speculativeSort is deterministic.
	 */
	private static int[] speculativeSort(int[] array)
	{
		// Run in parallel both algorithms.
		// The first algorithm to finish will execute a 'return' statement,
		// which will stop the other algorithm and then return its result.
		{
final int [ ] array6 = array ;
{
		  // We do not want to use the default implementation for this parallel
		  // composition, because the default implementation is very unfair.
		  // For the speculative parallelism to work, some degree of fairness
		  // is needed.
		  // This is achieved by using the '#(Threads())' indication.		  
		 	final java . util . List < apx . lang . gen . Branch > branches17 =new java . util . ArrayList < apx . lang . gen . Branch > ();
final java . util . List < apx . lang . gen . Bang < ? > > bangs17 =new java . util . ArrayList < apx . lang . gen . Bang < ? > > ();
final apx . lang . gen . Parallel parallelBlock17 =apx . lang . gen . Parallel .getParallelBlock (null);
{
apx . lang . gen . Branch branch25 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
throw new apx . lang . gen . ReturnException (com . ateji . px . speculativeparallelism . Sort .bubbleSortAlgorithm(array6));
		 	}
}
;
branch25 .runInItsOwnThread ();
branches17 .add (branch25 );
}
{
apx . lang . gen . Branch branch26 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
throw new apx . lang . gen . ReturnException (com . ateji . px . speculativeparallelism . Sort .insertionSortAlgorithm(array6));		 	
		}
}
;
branch26 .runInItsOwnThread ();
branches17 .add (branch26 );
}
final apx . lang . gen . ExitStatus exitStatus20 =parallelBlock17 . run (branches17 , bangs17 );
if(exitStatus20 .hasReturned ()) {
return exitStatus20 .<int [ ] >returnedValue ();
} {
final java . lang . Throwable throwable20 =exitStatus20 .thrownException ();
if(throwable20 !=null) {
if(throwable20 instanceof java . lang . RuntimeException ) throw (java . lang . RuntimeException )throwable20 ;
if(throwable20 instanceof java . lang . Error ) throw (java . lang . Error )throwable20 ;
}
}
throw new apx . lang . gen . ApxError ();
}		
	}
}
	
	/**
	 * bubbleSortAlgorithm implements a bubble sort algorithm.
	 * It stops prematurely its computation if the other algorithm has been quicker.
	 */
	private static int[] bubbleSortAlgorithm(int[] array)
	{
		System.out.println("Bubble sort algorithm has started.");
		
		// copy the array
		int[] t = array.clone();
		
		boolean swapped;
		do {
			// This code relates to the interruption.
			//  - isInterrupted() returns true iff the computation has been interrupted 
			//    by another branch
			// - return left without completing the method calculation. The return value is never used.
			if (Thread.currentThread().isInterrupted())
			{
				System.out.println("Bubble sort algorithm stops prematurely.");
				return t;
			}
			
			swapped = false;
			{
final apx . util . RangeInteger range49 = new apx . util . RangeInteger (0 , t.length - 2);
final int last40 = (range49 ). max ;
for(int block38 = (range49 ). min ;block38 <= last40 ;block38 ++ )
{
int i = block38 ;
{
{
				if (t[i] > t[i+1])
				{
					swap(t, i, i+1);
					swapped = true;
				}
			}
		}
}
}
}
		while (swapped);
		
		System.out.println("Bubble sort algorithm has completed.");
		return t;
	}

	/**
	 * insertionSortAlgorithm implements an insertion sort algorithm.
	 * It stops prematurely its computation if the other algorithm has been quicker.
	 */
	private static int[] insertionSortAlgorithm(int[] array)
	{
		System.out.println("Insertion sort algorithm has started.");
		
		// copy the array
		int[] t = array.clone();
		
		{
final apx . util . RangeInteger range50 = new apx . util . RangeInteger (1 , t.length-1);
final int last41 = (range50 ). max ;
for(int block39 = (range50 ). min ;block39 <= last41 ;block39 ++ )
{
int i = block39 ;
{
{
			// This code relates to the interruption.
			//  - isInterrupted() returns true iff the computation has been interrupted 
			//    by another branch
			// - return left without completing the method calculation. The return value is never used.
			if (Thread.currentThread().isInterrupted())
			{
				System.out.println("Insertion sort algorithm stops prematurely.");
				return t;
			}
			
			for (int k = i; k > 0; k--)
			{
				if (t[k] < t[k-1])
				{
					swap(t, k, k-1);	
				}
			}
		}
		
		}
}
}
System.out.println("Insertion sort algorithm has completed.");
		return t;
	}	
	
	/**
	 * Launch speculative calculation on three examples:
	 *   - a sorted array,
	 *   - a nearly sorted array, and
	 *   - a randomized array.
	 */
	public static void main(String[] args)
	{
		int size = 5000;
		
		System.out.println("Sorting a sorted array:");
		int[] sorted = sorted(size);
		System.out.println("Sorted array: " + Arrays.toString(speculativeSort(sorted)));
		System.out.println();
		
		System.out.println("Sorting a nearly sorted array:");
		int[] nearlySorted = nearlySorted(size);
		System.out.println("Sorted array: " + Arrays.toString(speculativeSort(nearlySorted)));
		System.out.println();
		
		System.out.println("Sorting a random array:");
		int[] randomized = randomized(size);
		System.out.println("Sorted array: " + Arrays.toString(speculativeSort(randomized)));
		System.out.println();
	}
	
	/**
	 * Swaps t[i] and t[j]
	 */
	private static void swap(int[] t, int i, int j)
	{
		int tmp = t[i];
		t[i] = t[j];
		t[j] = tmp;
	}
	
	/**
	 * Returns a sorted array with 'size' elements.
	 */
	private static int[] sorted(int size){
		final int [ ] freshVariable11 =new int [size];{int freshVariable12 =0;for(int i: new apx . util . RangeInteger (0 , ((size)- 1 ))){freshVariable11 [freshVariable12 ]=i;freshVariable12 ++;}}int[] t=freshVariable11 ;
		return t;
	}
	
	/**
	 * Returns a nearly sorted array with 'size' elements.
	 */
	private static int[] nearlySorted(int size){
		final int [ ] freshVariable13 =new int [size];{int freshVariable14 =0;for(int i: new apx . util . RangeInteger (0 , ((size)- 1 ))){freshVariable13 [freshVariable14 ]=i;freshVariable14 ++;}}int[] t=freshVariable13 ;
		{
final apx . util . RangeInteger range51 = new apx . util . RangeInteger (0 , ((size/50)- 1 ));
final int last42 = (range51 ). max ;
for(int block40 = (range51 ). min ;block40 <= last42 ;block40 ++ )
{
int i = block40 ;
{
{
			int	i1 = random(i);
			int i2 = random(size-1);
			swap(t, i1, i2);
		}
		}
}
}
return t;
	}
	
	/**
	 * Returns a randomized array with 'size' elements.
	 */
	private static int[] randomized(int size){
		final int [ ] freshVariable15 =new int [size];{int freshVariable16 =0;for(int i: new apx . util . RangeInteger (0 , ((size)- 1 ))){freshVariable15 [freshVariable16 ]=i;freshVariable16 ++;}}int[] t=freshVariable15 ;
		{
final apx . util . RangeInteger range52 = new apx . util . RangeInteger (0 , ((size)- 1 ));
final int last43 = (range52 ). max ;
for(int block41 = (range52 ). min ;block41 <= last43 ;block41 ++ )
{
int i = block41 ;
{
{
			int i2 = random(size-1);
			swap(t, i, i2);
		}
		}
}
}
return t;
	}
	
	/**
	 * Returns a random integer i such that  0 <= i <= max
	 */
	private static int random(int max)
	{
		double r = Math.random();
		return (int) Math.round(max*r);
	}
	
}
