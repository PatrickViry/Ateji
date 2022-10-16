/****************************
 * Copyright (c) 2009 ATEJI *
 * All rights reserved.     *
 ****************************/
package com.ateji.px.channels;

import java.util.Arrays;

import apx.lang.Chan;
import apx.lang.IChan;
import apx.lang.serial.SerializationCloner;

/**
 * This example shows some differences between two types of channels: 
 *  - the channels that share references at an exchange, and
 *  - the channels that copies values at an exchange.
 *  
 * Running the main method will display the result of the comparison.
 */
public class ShareOrCopy {

	public static void main(String[] args) {
		IChan<int[]> sharingChannel = new Chan<int[]>();
		IChan<int[]> copyingChannel = new Chan<int[]>(new SerializationCloner<int[]>());

		System.out.println("Sending an array over a channel that SHARES REFERENCES");
		int[] value1 = {0, 1, 2};
		{
final apx . lang . IChan < int [ ] > sharingChannel0 = sharingChannel ;
final int [ ] value10 = value1 ;
{
			final java . util . List < apx . lang . gen . Branch > branches15 =new java . util . ArrayList < apx . lang . gen . Branch > ();
final java . util . List < apx . lang . gen . Bang < ? > > bangs15 =new java . util . ArrayList < apx . lang . gen . Bang < ? > > ();
final apx . lang . gen . Parallel parallelBlock15 =apx . lang . gen . Parallel .getParallelBlock (null);
{
apx . lang . gen . Branch branch21 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
sharingChannel0 . send (value10);

			}
}
;
branches15 .add (branch21 );
}
{
apx . lang . gen . Branch branch22 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
int[] sharedValue=sharingChannel0 . receive ();
			com . ateji . px . channels . ShareOrCopy .compare(value10, sharedValue);
		}
}
;
branches15 .add (branch22 );
}
final apx . lang . gen . ExitStatus exitStatus18 =parallelBlock15 . run (branches15 , bangs15 );
if(exitStatus18 .hasReturned ()) {
return ;
} {
final java . lang . Throwable throwable18 =exitStatus18 .thrownException ();
if(throwable18 !=null) {
if(throwable18 instanceof java . lang . RuntimeException ) throw (java . lang . RuntimeException )throwable18 ;
if(throwable18 instanceof java . lang . Error ) throw (java . lang . Error )throwable18 ;
}
}
}
		}
System.out.println("In short, the sent array and the received array are the SAME OBJECT.");
		
		System.out.println();
		
		System.out.println("Sending an array over a channel that COPIES VALUES");
		int[] value2 = {0, 1, 2};
		{
final apx . lang . IChan < int [ ] > copyingChannel0 = copyingChannel ;
final int [ ] value20 = value2 ;
{
			final java . util . List < apx . lang . gen . Branch > branches16 =new java . util . ArrayList < apx . lang . gen . Branch > ();
final java . util . List < apx . lang . gen . Bang < ? > > bangs16 =new java . util . ArrayList < apx . lang . gen . Bang < ? > > ();
final apx . lang . gen . Parallel parallelBlock16 =apx . lang . gen . Parallel .getParallelBlock (null);
{
apx . lang . gen . Branch branch23 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
copyingChannel0 . send (value20);
			
			}
}
;
branches16 .add (branch23 );
}
{
apx . lang . gen . Branch branch24 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
int[] copiedValue=copyingChannel0 . receive ();
			com . ateji . px . channels . ShareOrCopy .compare(value20, copiedValue);
		}
}
;
branches16 .add (branch24 );
}
final apx . lang . gen . ExitStatus exitStatus19 =parallelBlock16 . run (branches16 , bangs16 );
if(exitStatus19 .hasReturned ()) {
return ;
} {
final java . lang . Throwable throwable19 =exitStatus19 .thrownException ();
if(throwable19 !=null) {
if(throwable19 instanceof java . lang . RuntimeException ) throw (java . lang . RuntimeException )throwable19 ;
if(throwable19 instanceof java . lang . Error ) throw (java . lang . Error )throwable19 ;
}
}
}
		}
System.out.println("In short, the received array is A COPY of the sent array.");
	}

	private static void compare(int[] sentArray, int[] receivedArray) {
		System.out.println("Comparing the sent array and the received array:");
		
		// compare the elements of the arrays
		System.out.println("Do the two arrays contain the same elements?  "
				+ Arrays.equals(sentArray, receivedArray));
		System.out.println("\tSent array elements:      " + Arrays.toString(sentArray));
		System.out.println("\tReceived array elements:  " + Arrays.toString(receivedArray));
		
		// compare the references of the arrays
		System.out.println("Are the two arrays the same object?  " + (sentArray == receivedArray));
		System.out.println("\tReference of the sent array:      " + sentArray.toString());
		System.out.println("\tReference of the received array:  " + receivedArray.toString());
		
		// propagation of changes in one array to the other
		sentArray[0]=7;
		System.out.println("After setting the first element of the sent array to 7," +
				" has the received array changed?  " + (sentArray[0] == receivedArray[0]));
		System.out.println("\tSent array elements:      " + Arrays.toString(sentArray));
		System.out.println("\tReceived array elements:  " + Arrays.toString(receivedArray));
	}
	
}
