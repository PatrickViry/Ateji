/****************************
 * Copyright (c) 2009 ATEJI *
 * All rights reserved.     *
 ****************************/
package com.ateji.px.channels;

import java.util.Timer;
import java.util.TimerTask;

import apx.lang.AsyncChan;
import apx.lang.Chan;
import apx.lang.IChan;
import apx.lang.Signal;

/**
 * This example shows some differences between synchronous channels and
 * asynchronous channels.
 * 
 * Running the main method will display the result of the comparison.
 */
public class SynchronousOrAsynchronous {

	/**
	 * Two parallel branches sends and then receives a message on the same
	 * channel.
	 * Using a synchronous channel, both branches will block on the sending
	 * because there will be nobody to receive the message.
	 * Using an asynchronous channel, both branches can send messages without
	 * blocking (the messages will be buffered) and then proceed to the
	 * receiving.
	 *
	 * The last branch is used to get out of a deadlock situation; after three
	 * seconds a signal is sent suggesting that the situation will no longer
	 * advance: there is a deadlock.
	 * 
	 * Both synchronous and asynchronous channels implements the same interface
	 * IChan: as a result this method can work on any channel 'c', be
	 * synchronous or asynchronous.
	 */
	@SuppressWarnings("unused")
	private static void showDeadLock(IChan<Integer> c) throws DeadlockException
	{
		{
final apx . lang . IChan < java . lang . Integer > c0 = c ;
{
			final java . util . List < apx . lang . gen . Branch > branches7 =new java . util . ArrayList < apx . lang . gen . Branch > ();
final java . util . List < apx . lang . gen . Bang < ? > > bangs7 =new java . util . ArrayList < apx . lang . gen . Bang < ? > > ();
final apx . lang . gen . Parallel parallelBlock7 =apx . lang . gen . Parallel .getParallelBlock (null);
{
apx . lang . gen . Branch branch8 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
c0 . send (0); int v=c0 . receive ();
			throw new apx . lang . gen . ReturnException (); 
			
			}
}
;
branches7 .add (branch8 );
}
{
apx . lang . gen . Branch branch9 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
c0 . send (1); int v=c0 . receive ();
			throw new apx . lang . gen . ReturnException ();
			
			}
}
;
branches7 .add (branch9 );
}
{
apx . lang . gen . Branch branch10 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
com . ateji . px . channels . SynchronousOrAsynchronous .deadlockSignal .receive ();
			throw new DeadlockException();
		}
}
;
branches7 .add (branch10 );
}
final apx . lang . gen . ExitStatus exitStatus9 =parallelBlock7 . run (branches7 , bangs7 );
if(exitStatus9 .hasReturned ()) {
return ;
} {
final java . lang . Throwable throwable9 =exitStatus9 .thrownException ();
if(throwable9 !=null) {
if(throwable9 instanceof java . lang . RuntimeException ) throw (java . lang . RuntimeException )throwable9 ;
if(throwable9 instanceof java . lang . Error ) throw (java . lang . Error )throwable9 ;
if(throwable9 instanceof com . ateji . px . channels . SynchronousOrAsynchronous . DeadlockException ) throw (com . ateji . px . channels . SynchronousOrAsynchronous . DeadlockException )throwable9 ;
}
}
throw new apx . lang . gen . ApxError ();
}	
	}
}


	public static void main(String[] args) {
		IChan<Integer> synchronous = new Chan<Integer>();
		IChan<Integer> asynchronous = new AsyncChan<Integer>();

		System.out.println(
				"DEFINITION\n" +
				"\n" +
				"Synchronous channels have the property that a sender putting a message in the\n" +
				"channel MUST WAIT for a receiver to get the message out of the channel before\n" +
				"the sender can proceed.\n" +
				"\n" +
				"Asynchronous channels have the property that a sender putting a message in the\n" +
				"channel NEED NOT WAIT for a receiver to get the message out of the channel.\n" +
				"\n");				
		
		System.out.println(
				"DEAD LOCK\n" +
				"\n" +
				"In this example, some problematic message exchange is run: two parallel branches\n" +
				"sends and then receives a message on the same channel.\n" +
				"Using a synchronous channel, both branches will block on the sending because\n" +
				"there will be nobody ready to receive the message.\n" +
				"Using an asynchronous channel, both branches can send messages without blocking\n" +
				"(the messages will be buffered) and then proceed to the receiving.\n" +
				"The example considers that there is a deadlock if the message exchange is still\n" +
				"not done after three seconds.\n" +
				"\n" +
				"Does a synchronous chanel cause a dead lock? ");
		System.out.println(causeDeadLock(synchronous));
		System.out.println(
				"Does a asynchronous chanel cause a dead lock? ");
		System.out.println(causeDeadLock(asynchronous));
	}
	
	private static final Signal deadlockSignal = new Signal();
	
	private static String causeDeadLock(IChan<Integer> c) 
	{
		try {
			{
final apx . lang . IChan < java . lang . Integer > c4 = c ;
{
			 	final java . util . List < apx . lang . gen . Branch > branches8 =new java . util . ArrayList < apx . lang . gen . Branch > ();
final java . util . List < apx . lang . gen . Bang < ? > > bangs8 =new java . util . ArrayList < apx . lang . gen . Bang < ? > > ();
final apx . lang . gen . Parallel parallelBlock8 =apx . lang . gen . Parallel .getParallelBlock (null);
{
apx . lang . gen . Branch branch11 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
com . ateji . px . channels . SynchronousOrAsynchronous .showDeadLock(c4);
			 	throw new apx . lang . gen . ReturnException ("exit on message exchange");
			 	
			 	}
}
;
branches8 .add (branch11 );
}
{
apx . lang . gen . Branch branch12 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
com . ateji . px . channels . SynchronousOrAsynchronous .deadlockSignal(com . ateji . px . channels . SynchronousOrAsynchronous .deadlockSignal);
			}
}
;
branches8 .add (branch12 );
}
final apx . lang . gen . ExitStatus exitStatus10 =parallelBlock8 . run (branches8 , bangs8 );
if(exitStatus10 .hasReturned ()) {
return exitStatus10 .<java . lang . String >returnedValue ();
} {
final java . lang . Throwable throwable10 =exitStatus10 .thrownException ();
if(throwable10 !=null) {
if(throwable10 instanceof java . lang . RuntimeException ) throw (java . lang . RuntimeException )throwable10 ;
if(throwable10 instanceof java . lang . Error ) throw (java . lang . Error )throwable10 ;
if(throwable10 instanceof com . ateji . px . channels . SynchronousOrAsynchronous . DeadlockException ) throw (com . ateji . px . channels . SynchronousOrAsynchronous . DeadlockException )throwable10 ;
}
}
throw new apx . lang . gen . ApxError ();
}
		}
} catch(DeadlockException e) {
			return "exit on dead lock";
		}
	}
	
	private static class DeadlockException extends Exception
	{
		private static final long serialVersionUID = -1875415094015286001L;
	}
	
	/**
	 *  After 3000 ms, notify on 'deadlockSignal' that a dead lock probably
	 *  occurred.
	 *  Every 100 ms before this notification, a dot is printed on the console
	 *  to notify the reader that the timer is waiting for deadlock.
	 */
	private static void deadlockSignal(final Signal deadlockSignal) 
	{
		final int end = 3000;
		final int interval = 100;
		Timer timer = new Timer();
		{
final apx . util . RangeInteger range40 = new apx . util . RangeInteger (0 , ((end/interval)- 1 ));
final int last31 = (range40 ). max ;
for(int block29 = (range40 ). min ;block29 <= last31 ;block29 ++ )
{
final int i = block29 ;
{
{
			timer.schedule(new TimerTask(){
				@Override
				public void run() {
					System.out.print(".");
				}
			}, i*interval);
		}
		}
}
}
timer.schedule(new TimerTask(){
			@Override
			public void run() {				
				deadlockSignal .send ();
			}
		}, end);
	}
	
}
