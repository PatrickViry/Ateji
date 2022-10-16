/****************************
 * Copyright (c) 2009 ATEJI *
 * All rights reserved.     *
 ****************************/
package com.ateji.px.recursiveparallelism;

/**
 * This example shows how to parallelize the recursive version of the
 * quicksort algorithm in Ateji PX.
 */
public class Quicksort 
{

	/**
	 * The following code shows the recursive part of the quicksort algorithm.
	 */
	private void quicksort(int first, int last, int depth)
	{
		if (last > first)
		{
			int pivotIndex = first;
			int pivotNewIndex = partition(first, last, pivotIndex);
			 // The test '(depth < 4))' chooses at run time the implementation
			 // to be used by the recursive calls. The goal is to execute code in parallel if the depth is
			 // low and sequential otherwise.
			if (depth < 4)
			{
final int first0 = first ;
final int last0 = last ;
final int depth0 = depth ;
final int pivotNewIndex0 = pivotNewIndex ;
{
			 // This block puts in parallel the two recursive calls of the 'quicksort' method.
    		 final java . util . List < apx . lang . gen . Branch > branches21 =new java . util . ArrayList < apx . lang . gen . Branch > ();
final java . util . List < apx . lang . gen . Bang < ? > > bangs21 =new java . util . ArrayList < apx . lang . gen . Bang < ? > > ();
final apx . lang . gen . Parallel parallelBlock21 =apx . lang . gen . Parallel .getParallelBlock (null);
{
apx . lang . gen . Branch branch33 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
Quicksort .this.quicksort(first0, pivotNewIndex0-1, depth0+1);
    		 }
}
;
branches21 .add (branch33 );
}
{
apx . lang . gen . Branch branch34 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
Quicksort .this.quicksort(pivotNewIndex0+1, last0, depth0+1);
    		}
}
;
branches21 .add (branch34 );
}
final apx . lang . gen . ExitStatus exitStatus24 =parallelBlock21 . run (branches21 , bangs21 );
if(exitStatus24 .hasReturned ()) {
return ;
} {
final java . lang . Throwable throwable24 =exitStatus24 .thrownException ();
if(throwable24 !=null) {
if(throwable24 instanceof java . lang . RuntimeException ) throw (java . lang . RuntimeException )throwable24 ;
if(throwable24 instanceof java . lang . Error ) throw (java . lang . Error )throwable24 ;
}
}
}	
    		}
else
    		{
    			// This block executes in sequentially the two recursive calls of the 'quicksort' method.
    			// All recursive calls are also executes in sequentially.
    			// The test on the depth is never revalued.
    			sequentialQuicksort(first, pivotNewIndex-1, depth+1);
    			sequentialQuicksort(pivotNewIndex+1, last, depth+1);
    		}
		}
	}

	
	/**
	 * The following code shows the recursive part of the sequential quicksort algorithm.
	 */
	private void sequentialQuicksort(int first, int last, int depth)
	{
		if (last > first)
		{
			int pivotIndex = first;
			int pivotNewIndex = partition(first, last, pivotIndex);
			sequentialQuicksort(first, pivotNewIndex-1, depth+1);
			sequentialQuicksort(pivotNewIndex+1, last, depth+1);
		}
	}

	
	// Run the quicksort algorithm.
	public void sort()
	{
		quicksort(0, array.length-1, 0);
	}
	
	/**
	 * The 'partition' method splits the portion of the array between indexes
	 * 'first' and 'last'.
	 * It moves all the elements that are less than or equal to 'array[pivotIndex]'
	 * to the beginning of the subarray.
	 */
	private int partition(int first, int last, int pivotIndex)
	{
		int pivotValue = array[pivotIndex];
		exchange(pivotIndex, last);
		int storeIndex = first;
		{
final apx . util . RangeInteger range56 = new apx . util . RangeInteger (first , last - 1);
final int last47 = (range56 ). max ;
for(int block45 = (range56 ). min ;block45 <= last47 ;block45 ++ )
{
int i = block45 ;
{
if( (array[i] <= pivotValue) ) { {
			exchange(i, storeIndex);
			storeIndex ++;
		}
		} }
}
}
exchange(storeIndex, last);
		return storeIndex;
	}
	
	public static void main(String[] args) 
	{
		int[] array = sampleArray();
		
		Quicksort q = new Quicksort(array);
		q.sort();
		
		display(array);
	}

	private final int[] array;
	
	Quicksort(int[] array)
	{
		this.array = array;
	}
	
	private void exchange(int i, int j) 
	{
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	static int[] sampleArray() {
		int N = 100;
		int[] array = new int[N];
		for(int i=0; i<N; i++) {
			array[i] = (i*i+N*i+1) % N;
		}
		return array;
	}
	
	static void display(int[] array)
	{
		for(int i=0; i<array.length; i++) {
			System.out.print(array[i]);
			System.out.print(" ");
		}
		System.out.println();
	}

}
