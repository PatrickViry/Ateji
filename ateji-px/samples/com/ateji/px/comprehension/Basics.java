/****************************
 * Copyright (c) 2009 ATEJI *
 * All rights reserved.     *
 ****************************/
package com.ateji.px.comprehension;

import apx.util.monoid.Monoids;
import java.util.Set;

/**
 * These example demonstrates the use of comprehensions. Launch the main
 * method and read the explanation. 
 */
public class Basics
{
	@SuppressWarnings("unchecked")
	public static void main(String[] args)
	{		
		final long n = 10000;	
				
		System.out.println("Comprehension syntax");
		System.out.println("--------------------");
		System.out.println("Comprehension expressions can express algebraic calculations in a simple way.\n"+
				           "For example, a sum can be written in this form. In Ateji PX, you would write:\n"+
                           "\tlong sum = `+ for (long i: 1 .. n, long j: 1 .. n) (i*j);\n"+				          
				           "To parallelize the calculation, simply add the symbol '||' after the keyword 'for':\n"+
				           "\t// Calculate the sum of i*j in parallel\n"+
				           "\tlong sum = `+ for || (long i: 1 .. n, long j: 1 .. n) (i*j);");
		System.out.println();
		System.out.println("For example, for n = " + n + ", we obtain the following results:");

		// Computes the sum of i*j from i=1 to i=n and from j=1 to j=n.
		{ // Sequential computation
			
			long toBeReturned = 0 ; {
final apx . util . RangeLong range16 = new apx . util . RangeLong (1 , n);
final long last7 = (range16 ). max ;
for(long block5 = (range16 ). min ;block5 <= last7 ;block5 ++ )
{
long i = block5 ;
{
{
final apx . util . RangeLong range17 = new apx . util . RangeLong (1 , n);
final long last8 = (range17 ). max ;
for(long block6 = (range17 ). min ;block6 <= last8 ;block6 ++ )
{
long j = block6 ;
{
toBeReturned += (i*j); }
}
}
}
}
}
long sum = toBeReturned ;
			System.out.println("\tComputes the sum with a sequential comprehension: sum = " + sum );
		}
		{ // Parallel computation
			final java . lang . Object lock = new java . lang . Object ();
long global = 0 ;
final apx . util . RangeLong range0= new apx . util . RangeLong (1 , n); final int blockcount = java . lang . Runtime .getRuntime ().availableProcessors (); {
final apx . lang . gen . MutableReferenceLong global7 = new apx . lang . gen . MutableReferenceLong (global );
{
final java . util . List < apx . lang . gen . Branch > branches3 =new java . util . ArrayList < apx . lang . gen . Branch > ();
final java . util . List < apx . lang . gen . Bang < ? > > bangs3 =new java . util . ArrayList < apx . lang . gen . Bang < ? > > ();
final apx . lang . gen . Parallel parallelBlock3 =apx . lang . gen . Parallel .getParallelBlock (null);
{
final apx . util . RangeInteger range18 = new apx . util . RangeInteger (0 , ((blockcount )- 1 ));
final int last9 = (range18 ). max ;
for(int block7 = (range18 ). min ;block7 <= last9 ;block7 ++ )
{
final int nbBlock = block7 ;
{
{
apx . lang . gen . Branch branch4 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
long toBeReturned0 = 0 ; {
final apx . util . RangeLong range19 = new apx . util . RangeLong ((((range0 ). min )+ ((((range0 ). max )- ((range0 ). min )+ 1 )/ (java . lang . Runtime .getRuntime ().availableProcessors ())+ ((((range0 ). max )- ((range0 ). min )+ 1 )% (java . lang . Runtime .getRuntime ().availableProcessors ())== 0 ? 0 : 1 ))* (nbBlock )), (java . lang . Math . min (((range0 ). max ), ((range0 ). min )+ ((((range0 ). max )- ((range0 ). min )+ 1 )/ (java . lang . Runtime .getRuntime ().availableProcessors ())+ ((((range0 ). max )- ((range0 ). min )+ 1 )% (java . lang . Runtime .getRuntime ().availableProcessors ())== 0 ? 0 : 1 ))* ((nbBlock )+ 1 )- 1 )));
final long last10 = (range19 ). max ;
for(long block8 = (range19 ). min ;block8 <= last10 ;block8 ++ )
{
final long i = block8 ;
{
{
final apx . util . RangeLong range20 = new apx . util . RangeLong (1 , n);
final long last11 = (range20 ). max ;
for(long block9 = (range20 ). min ;block9 <= last11 ;block9 ++ )
{
long j = block9 ;
{
toBeReturned0 += ((i*j)); }
}
}
}
}
}
final long local = toBeReturned0 ;
synchronized ( lock ) { global7 .ref += local ; } }
}
;
branches3 .add (branch4 );
}
}
}
}
final apx . lang . gen . ExitStatus exitStatus4 =parallelBlock3 . run (branches3 , bangs3 );
global = global7 .ref ;
if(exitStatus4 .hasReturned ()) {
return ;
} {
final java . lang . Throwable throwable4 =exitStatus4 .thrownException ();
if(throwable4 !=null) {
if(throwable4 instanceof java . lang . RuntimeException ) throw (java . lang . RuntimeException )throwable4 ;
if(throwable4 instanceof java . lang . Error ) throw (java . lang . Error )throwable4 ;
}
}
}
}
long sum = global ;
			System.out.println("\tComputes the sum with a parallel comprehension: sum = " + sum );
		}
		System.out.println("Please refer to the SpeedUp sample to compare performance of those two comprehensions");
		
		System.out.println();
		
		System.out.println("Filter syntax");
		System.out.println("-------------");
		System.out.println("A filter can be introduced to prune the values of the generators that will be iterated.");
		System.out.println("To do this, use the 'if' syntax:");
		System.out.println("\t// Computes the sum of only even numbers");
		System.out.println("\tlong sum = `+ for (long i: 1 .. n, long j: 1 .. n, if (i*j%2 == 0)) (i*j);");
		System.out.println();
		{
			System.out.println("For example, for n = " + n + ", we obtain the following result:");
			long toBeReturned1 = 0 ; {
final apx . util . RangeLong range21 = new apx . util . RangeLong (1 , n);
final long last12 = (range21 ). max ;
for(long block10 = (range21 ). min ;block10 <= last12 ;block10 ++ )
{
long i = block10 ;
{
{
final apx . util . RangeLong range22 = new apx . util . RangeLong (1 , n);
final long last13 = (range22 ). max ;
for(long block11 = (range22 ). min ;block11 <= last13 ;block11 ++ )
{
long j = block11 ;
{
if ( (i*j%2 == 0)) { toBeReturned1 += (i*j) ; } }
}
}
}
}
}
long sum = toBeReturned1 ;			
			System.out.println("\tsum = " + sum);
		}
		System.out.println();
		
		System.out.println("Local variable");
		System.out.println("--------------");
		System.out.println("Finally a local variable may be introduced to simplify the writing of a comprehension.");
		System.out.println("It can be very handy to share the result of a computation between the filter and the");
		System.out.println("target expression, as in our exemple:");
		System.out.println("\t// Compute once i*j in a local variable ij");
		System.out.println("\tlong sum = `+ for (long i: 1 .. n, long j: 1 .. n, long ij = i*j, if (ij%2 == 0)) ij;");
		System.out.println();
		{
			System.out.println("For example, for n = " + n + ", we obtain the following result:");
			long toBeReturned2 = 0 ; {
final apx . util . RangeLong range23 = new apx . util . RangeLong (1 , n);
final long last14 = (range23 ). max ;
for(long block12 = (range23 ). min ;block12 <= last14 ;block12 ++ )
{
long i = block12 ;
{
{
final apx . util . RangeLong range24 = new apx . util . RangeLong (1 , n);
final long last15 = (range24 ). max ;
for(long block13 = (range24 ). min ;block13 <= last15 ;block13 ++ )
{
long j = block13 ;
{
{ long ij = i*j; if ( (ij%2 == 0)) { toBeReturned2 += ij; } } }
}
}
}
}
}
long sum = toBeReturned2 ;			
			System.out.println("\tsum = " + sum);
		}
		System.out.println();

		System.out.println("Monoids");
		System.out.println("-------");		
		System.out.println("Regarding the operator (called monoid) used to aggregate values, all associative\n" +
		                   "Java binary operators can be used.");
		System.out.println("For example, a multiplication can be written by using the '`*' monoid;\n" +
				           "a boolean sum can be written by using the '`|' monoid:");
		System.out.println();
		{
			System.out.println("\tlong prod = `* for (long i : 1 .. 10) i;");
			long toBeReturned3 = 1 ; {
final apx . util . RangeInteger range25 = new apx . util . RangeInteger (1 , 10);
final int last16 = (range25 ). max ;
for(int block14 = (range25 ). min ;block14 <= last16 ;block14 ++ )
{
long i = block14 ;
{
toBeReturned3 *= i; }
}
}
long prod = toBeReturned3 ;
			System.out.println("\tprod = " + prod);
		}
		System.out.println();
		{
			System.out.println("\tboolean some = `| for (long i : 1 .. n) (i%129 == 0);");
			boolean toBeReturned4 = false ; {
final apx . util . RangeLong range26 = new apx . util . RangeLong (1 , n);
final long last17 = (range26 ). max ;
for(long block15 = (range26 ). min ;block15 <= last17 ;block15 ++ )
{
long i = block15 ;
{
toBeReturned4 |= (i%129 == 0); }
}
}
boolean some = toBeReturned4 ;
			System.out.println("\tCheck whether an element satisfies the following property: (i%129 == 0)? " + some);
		}
		System.out.println();
		System.out.println("More over, other monoids are provided by Ateji. They are in the library.");
		System.out.println("The monoids calculating min and max are included as well as monoids building\n" +
		                   "collections such as hashSet and arrayList.");
		System.out.println();
		{
			apx . util . monoid . MinMonoid monoid13 = Monoids.min() ; long toBeReturned5 = monoid13 . longZero ( ) ; {
final apx . util . RangeLong range27 = new apx . util . RangeLong (1 , n);
final long last18 = (range27 ). max ;
for(long block16 = (range27 ). min ;block16 <= last18 ;block16 ++ )
{
long i = block16 ;
{
toBeReturned5 = monoid13 . longMerge ( toBeReturned5 , i) ; }
}
}
long min = toBeReturned5 ;
			System.out.println("\tMonoids.min() for (long i : 1 .. n) i = " + min);			
		}
		System.out.println();
		{
			apx . util . monoid . Monoids . HashSetMonoid < java . lang . Long > monoid14 = Monoids.hashSet() ; java . util . HashSet < java . lang . Long > toBeReturned6 = monoid14 . zero ( ) ; {
final apx . util . RangeInteger range28 = new apx . util . RangeInteger (1 , 10);
final int last19 = (range28 ). max ;
for(int block17 = (range28 ). min ;block17 <= last19 ;block17 ++ )
{
long i = block17 ;
{
monoid14 . mutableAdd ( toBeReturned6 , i) ; }
}
}
Set<Long> set = toBeReturned6 ;
			System.out.println("\tMonoids.hashSet() for (long i : 1 .. 10) i = " + set);			
		}		
		System.out.println();
		System.out.println("The last type of monoids are those developed by users; they are presented\n" +
		                   "in other examples of this package.");
	}
	
}

