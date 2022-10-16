/****************************
 * Copyright (c) 2009 ATEJI *
 * All rights reserved.     *
 ****************************/
package com.ateji.px.bangoperator;

import java.util.ArrayList;
import java.util.List;

/**
 * This sample demonstrates how to use the replication operator ||* (also called bang).
 * The two important operations are:
 *    - Bang replication and then creation of a new branches
 *    - Termination of a branch of type bang.
 *    
 * In this example, a money changer knows the conversion rate between the euro and the US dollar. 
 * It uses a bang to listen and respond to the requests that are sent.
 * 
 */
public class Main
{
	 
	public static void main(String[] args)
	{
		// At the topmost level,the money changer program is ran in parallel with
		// the clients. 
		{
{
			final java . util . List < apx . lang . gen . Branch > branches1 =new java . util . ArrayList < apx . lang . gen . Branch > ();
final java . util . List < apx . lang . gen . Bang < ? > > bangs1 =new java . util . ArrayList < apx . lang . gen . Bang < ? > > ();
final apx . lang . gen . Parallel parallelBlock1 =apx . lang . gen . Parallel .getParallelBlock (null);
{
apx . lang . gen . Branch branch1 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
MoneyChanger moneyChanger = new MoneyChanger();
			moneyChanger.run();
				
			}
}
;
branches1 .add (branch1 );
}
{
apx . lang . gen . Branch branch2 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
List<Client> clients = new ArrayList<Client>();
			{
				clients.add(new Client(1));
				clients.add(new Client(100));
				clients.add(new Client(175));
			}
			{
final java . util . List < com . ateji . px . bangoperator . Client > clients0 = clients ;
{
				 final java . util . List < apx . lang . gen . Branch > branches0 =new java . util . ArrayList < apx . lang . gen . Branch > ();
final java . util . List < apx . lang . gen . Bang < ? > > bangs0 =new java . util . ArrayList < apx . lang . gen . Bang < ? > > ();
final apx . lang . gen . Parallel parallelBlock0 =apx . lang . gen . Parallel .getParallelBlock (null);
for ( final Client client : clients0) {
{
apx . lang . gen . Branch branch0 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
{
					 client.run();
				 }
			}
}
;
branches0 .add (branch0 );
}
}
final apx . lang . gen . ExitStatus exitStatus0 =parallelBlock0 . run (branches0 , bangs0 );
if(exitStatus0 .hasReturned ()) {
throw new apx . lang . gen . ReturnException ();
} {
final java . lang . Throwable throwable0 =exitStatus0 .thrownException ();
if(throwable0 !=null) {
if(throwable0 instanceof java . lang . RuntimeException ) throw (java . lang . RuntimeException )throwable0 ;
if(throwable0 instanceof java . lang . Error ) throw (java . lang . Error )throwable0 ;
}
}
}
			// Termination of the money changer is handled by closing the channel.
			}
MoneyChanger.queryChannel.close();
		}
}
;
branches1 .add (branch2 );
}
final apx . lang . gen . ExitStatus exitStatus1 =parallelBlock1 . run (branches1 , bangs1 );
if(exitStatus1 .hasReturned ()) {
return ;
} {
final java . lang . Throwable throwable1 =exitStatus1 .thrownException ();
if(throwable1 !=null) {
if(throwable1 instanceof java . lang . RuntimeException ) throw (java . lang . RuntimeException )throwable1 ;
if(throwable1 instanceof java . lang . Error ) throw (java . lang . Error )throwable1 ;
}
}
}
	}
}
	
}
