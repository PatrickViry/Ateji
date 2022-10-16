/****************************
 * Copyright (c) 2010 ATEJI *
 * All rights reserved.     *
 ****************************/
package com.ateji.px.dataflow;

import apx.lang.*;

/**
 * This example shows how to implement a data flow algorithm with Ateji PX.
 * Dataflow programming models a program as a directed graph of the data
 * flowing between operations. In Ateji PX code, nodes of the graph will be
 * methods. The arcs are modeled by channels.
 */
public class Sine
{
	
	/**
	 * The add method is a node in the graph:
	 *   - The operator involved is the addition of the inputs.
	 *   - The ingoing arcs are the channels ins[0], ..., ins[n].
	 *   - The outgoing arc is the channel out.
	 *       
	 *  _________________
	 *       ins[0]       \
	 *  _________________ add _________
	 *       ...           /     out
	 *  _________________ /
	 *       ins[n]
	 */
	private static void add(Chan<Integer> out, Chan<Integer> ... ins)
	{
		while(true) {
			int result = 0;	
			for ( Chan<Integer> in : ins) {
{
				// receive values on the ingoing arcs
				int value=in . receive ();
				// add the inputs
				result += value;				
			}
			// send the result on the outgoing arc
			}
out . send (result);
		}
	}
	
	
	
	/**
	 * 	The main method composes five elements according to the following pattern:
	 * 
	 *  sine ___________  
	 *           c1     \
	 *  sine __________ add ________ display
	 *           c2     /       c
	 *  sine __________/
	 *           c3
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args)
	{
		// How to create an arc c1 between a sine method and an add method:
		// Create a channel c1 and connect it to the output of sine and to the input of add.
		Chan<Integer> c1 = new Chan<Integer>();
		Chan<Integer> c2 = new Chan<Integer>();
		Chan<Integer> c3 = new Chan<Integer>();
		Chan<Integer> c  = new Chan<Integer>();
		{
final apx . lang . Chan < java . lang . Integer > c20 = c2 ;
final apx . lang . Chan < java . lang . Integer > c10 = c1 ;
final apx . lang . Chan < java . lang . Integer > c5 = c ;
final apx . lang . Chan < java . lang . Integer > c30 = c3 ;
{ 
		  final java . util . List < apx . lang . gen . Branch > branches19 =new java . util . ArrayList < apx . lang . gen . Branch > ();
final java . util . List < apx . lang . gen . Bang < ? > > bangs19 =new java . util . ArrayList < apx . lang . gen . Bang < ? > > ();
final apx . lang . gen . Parallel parallelBlock19 =apx . lang . gen . Parallel .getParallelBlock (null);
{
apx . lang . gen . Branch branch27 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
com . ateji . px . dataflow . Sine .sine(20, 100, c10);  // connect c1 to the output of sine
		  }
}
;
branches19 .add (branch27 );
}
{
apx . lang . gen . Branch branch28 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
com . ateji . px . dataflow . Sine .sine(15, 50, c20);
		  }
}
;
branches19 .add (branch28 );
}
{
apx . lang . gen . Branch branch29 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
com . ateji . px . dataflow . Sine .sine(18, 80, c30);
		  }
}
;
branches19 .add (branch29 );
}
{
apx . lang . gen . Branch branch30 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
com . ateji . px . dataflow . Sine .add(c5, c10, c20, c30); // connect c1 to the input of add
		  }
}
;
branches19 .add (branch30 );
}
{
apx . lang . gen . Branch branch31 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
com . ateji . px . dataflow . Sine .display(230, c5);
		}
}
;
branches19 .add (branch31 );
}
final apx . lang . gen . ExitStatus exitStatus22 =parallelBlock19 . run (branches19 , bangs19 );
if(exitStatus22 .hasReturned ()) {
return ;
} {
final java . lang . Throwable throwable22 =exitStatus22 .thrownException ();
if(throwable22 !=null) {
if(throwable22 instanceof java . lang . RuntimeException ) throw (java . lang . RuntimeException )throwable22 ;
if(throwable22 instanceof java . lang . Error ) throw (java . lang . Error )throwable22 ;
}
}
}
	}
}
	
	/**
	 * The sine method is a node in the graph:
	 *   - The operator involved is the computation of the values of the sine function.
	 *   - There is no ingoing arc.
	 *   - The outgoing arc is the channel out.
	 *   
	 *   sine _________
	 *           out
	 */
	static void sine(int length, int amplitude, Chan<Integer> out)
	{
		final double m = (2*Math.PI)/length;

		// precompute sine values
		final int [ ] freshVariable18 =new int [length];{int freshVariable19 =0;for(int i : new apx . util . RangeInteger (0 , ((length)- 1 ))){freshVariable18 [freshVariable19 ]=(int)(amplitude * Math.sin(i*m));freshVariable19 ++;}}int[] sineValues=freshVariable18 ; 
		
		for(int i=0; true; i++) {		
			i %= length;
			out . send (sineValues[i]);
		}
	}
		
	/**
	 * The display method is a node in the graph:
	 *   - The operator displays values.
	 *   - The ingoing arc is the channel in.
	 *   - There is no outgoing arc.
	 *   
	 *   _______ display
	 *     in
	 */
	static void display(int maxAmplitude, Chan<Integer> in)
	{
		final int range = 40;
		while(true) {
			Integer value=in . receive ();

				if(value > maxAmplitude) value = maxAmplitude;
				if(value < -maxAmplitude) value = -maxAmplitude;
				int pos = (value * range) / maxAmplitude;

				for(int i=-range; i<0; i++) {
					if(i == pos) {
						System.out.print("*");
					} else {
						System.out.print(" ");
					}
				}
				if(0 == pos) {
					System.out.print("*");
				} else {
					System.out.print("|");
				}
				for(int i=1; i<=range; i++) {
					if(i == pos) {
						System.out.print("*");
					} else {
						System.out.print(" ");
					}
				}
				System.out.println();			
		}
	}
	
}
