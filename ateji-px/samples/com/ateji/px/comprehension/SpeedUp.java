/****************************
 * Copyright (c) 2009 ATEJI *
 * All rights reserved.     *
 ****************************/
package com.ateji.px.comprehension;

import com.ateji.px.comprehension.util.Measure;

/**
 * If you are not familiar with Ateji PX comprehensions, please refer to the Basics sample.
 * 
 * The purpose of this example is to compare the performance between sequential and 
 * parallel comprehensions. The monoid chosen to compare those two comprehensions is 
 * the sum.
 * 
 * Run this sample to get the result.
 */
public class SpeedUp {

	/**
	 * sequentialSum measures the algorithm when the comprehension
	 * is sequential.
	 */
	private static Measure sequentialSum = new Measure(){

		@Override
		public String name(){
			return "sequential sum";
		}

		@Override
		public void run(){
			{ int toBeReturned28 = 0 ; {
final apx . util . RangeInteger range92 = new apx . util . RangeInteger (0 , ((I)- 1 ));
final int last83 = (range92 ). max ;
for(int block81 = (range92 ). min ;block81 <= last83 ;block81 ++ )
{
int i = block81 ;
{
{
final apx . util . RangeInteger range93 = new apx . util . RangeInteger (0 , ((J)- 1 ));
final int last84 = (range93 ). max ;
for(int block82 = (range93 ). min ;block82 <= last84 ;block82 ++ )
{
int j = block82 ;
{
toBeReturned28 += (i*j); }
}
}
}
}
}
} }
		
	};

	/**
	 * parallelSum measures the algorithm when the comprehension
	 * is parallel.
	 */
	private static Measure parallelSum = new Measure(){

		@Override
		public String name(){
			return "parallel sum";
		}

		@Override
		public void run(){
			{ final java . lang . Object lock6 = new java . lang . Object ();
int global6 = 0 ;
final apx . util . RangeInteger range1 = new apx . util . RangeInteger (0 , (I)- 1 ); final int blockcount6 = java . lang . Runtime .getRuntime ().availableProcessors (); {
final apx . lang . gen . MutableReferenceInt global60 = new apx . lang . gen . MutableReferenceInt (global6 );
{
final java . util . List < apx . lang . gen . Branch > branches31 =new java . util . ArrayList < apx . lang . gen . Branch > ();
final java . util . List < apx . lang . gen . Bang < ? > > bangs31 =new java . util . ArrayList < apx . lang . gen . Bang < ? > > ();
final apx . lang . gen . Parallel parallelBlock31 =apx . lang . gen . Parallel .getParallelBlock (null);
{
final apx . util . RangeInteger range94 = new apx . util . RangeInteger (0 , ((blockcount6 )- 1 ));
final int last85 = (range94 ). max ;
for(int block83 = (range94 ). min ;block83 <= last85 ;block83 ++ )
{
final int nbBlock6 = block83 ;
{
{
apx . lang . gen . Branch branch44 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
int toBeReturned29 = 0 ; {
final apx . util . RangeInteger range95 = new apx . util . RangeInteger ((((range1 ). min )+ ((((range1 ). max )- ((range1 ). min )+ 1 )/ (java . lang . Runtime .getRuntime ().availableProcessors ())+ ((((range1 ). max )- ((range1 ). min )+ 1 )% (java . lang . Runtime .getRuntime ().availableProcessors ())== 0 ? 0 : 1 ))* (nbBlock6 )), (java . lang . Math . min (((range1 ). max ), ((range1 ). min )+ ((((range1 ). max )- ((range1 ). min )+ 1 )/ (java . lang . Runtime .getRuntime ().availableProcessors ())+ ((((range1 ). max )- ((range1 ). min )+ 1 )% (java . lang . Runtime .getRuntime ().availableProcessors ())== 0 ? 0 : 1 ))* ((nbBlock6 )+ 1 )- 1 )));
final int last86 = (range95 ). max ;
for(int block84 = (range95 ). min ;block84 <= last86 ;block84 ++ )
{
final int i = block84 ;
{
{
final apx . util . RangeInteger range96 = new apx . util . RangeInteger (0 , ((com . ateji . px . comprehension . SpeedUp .J)- 1 ));
final int last87 = (range96 ). max ;
for(int block85 = (range96 ). min ;block85 <= last87 ;block85 ++ )
{
int j = block85 ;
{
toBeReturned29 += ((i*j)); }
}
}
}
}
}
final int local6 = toBeReturned29 ;
synchronized ( lock6 ) { global60 .ref += local6 ; } }
}
;
branches31 .add (branch44 );
}
}
}
}
final apx . lang . gen . ExitStatus exitStatus35 =parallelBlock31 . run (branches31 , bangs31 );
global6 = global60 .ref ;
if(exitStatus35 .hasReturned ()) {
return ;
} {
final java . lang . Throwable throwable35 =exitStatus35 .thrownException ();
if(throwable35 !=null) {
if(throwable35 instanceof java . lang . RuntimeException ) throw (java . lang . RuntimeException )throwable35 ;
if(throwable35 instanceof java . lang . Error ) throw (java . lang . Error )throwable35 ;
}
}
}
}
} }
		
	};
	
	public static void main(String[] args){
		System.out.println("PERFORMANCE COMPARISON BETWEEN SEQUENTIAL AND PARALLEL COMPREHENSIONS");
		System.out.println();
		System.out.println("sequential sum:");
		System.out.println("\t`+ for (int i : I, int j : J) (i*j);");
		System.out.println("parallel sum:");
		System.out.println("\t`+ for || (int i : I, int j : J) (i*j);");
		System.out.println("data size : I = "+I+"; J = "+J);
		System.out.println();
		System.out.println("Wait for the result...");
		long seqTime = sequentialSum.measure();
		long parTime = parallelSum.measure();
		System.out.println();
		System.out.println("Speed up = "+1.0*seqTime/parTime);
		System.out.println("Available processors = "+Runtime.getRuntime().availableProcessors());				
	}

	// data size
	final static int I = 10000;
	final static int J = 10000;
	
}
