/****************************
 * Copyright (c) 2009 ATEJI *
 * All rights reserved.     *
 ****************************/
package com.ateji.px.bangoperator;

import apx.lang.Chan;
import apx.lang.IChan;

/**
 * The trick to implement the money changer is to use the Ateji PX replication
 * operator.
 */ 
public class MoneyChanger
{

	void run()
	{
		{
{
		 	// ||* is a replication operator (also called bang). It may be seen
		 	// as a process which always creates new branches. As a typical
		 	// example, bang models a network service.
			// 
			// In Ateji PX, to control the number of created branches, a bang is
		 	// bound to a message exchange. 
			// 
			// Here, MoneyChanger waits for new queries and replies to them in
		 	// parallel.
		 	//
		 	// MoneyChanger will terminate as soon as the channel queryChannel
		 	// is closed.
		 	final java . util . List < apx . lang . gen . Branch > branches18 =new java . util . ArrayList < apx . lang . gen . Branch > ();
final java . util . List < apx . lang . gen . Bang < ? > > bangs18 =new java . util . ArrayList < apx . lang . gen . Bang < ? > > ();
final apx . lang . gen . Parallel parallelBlock18 =apx . lang . gen . Parallel .getParallelBlock (null);
{
bangs18 .add (new apx . lang . gen . Bang < com . ateji . px . bangoperator . Query > (null, parallelBlock18 , apx . lang . gen . Choice . Sense . RECEIVE ){
public @java .lang .Override void run (com . ateji . px . bangoperator . Query query ) throws java . lang . Throwable {
MoneyChanger .this.work(query);
		}
public @java .lang .Override apx . lang . IChan < com . ateji . px . bangoperator . Query > getChan () {
return com . ateji . px . bangoperator . MoneyChanger .queryChannel ;}
public @java .lang .Override com . ateji . px . bangoperator . Query getValue () {
return null ;}
}
);
}
final apx . lang . gen . ExitStatus exitStatus21 =parallelBlock18 . run (branches18 , bangs18 );
if(exitStatus21 .hasReturned ()) {
return ;
} {
final java . lang . Throwable throwable21 =exitStatus21 .thrownException ();
if(throwable21 !=null) {
if(throwable21 instanceof java . lang . RuntimeException ) throw (java . lang . RuntimeException )throwable21 ;
if(throwable21 instanceof java . lang . Error ) throw (java . lang . Error )throwable21 ;
}
}
}
	}
}

	/**
	 * Convert euros into dollars and send the answer on a private channel.
	 */
	private void work(Query query)
	{
		Double euros = query.getEuros();
		// private channel between the client and the money changer
		Chan<Double> reply = query.getReplyChan(); 
		reply . send (eurosToDollars(euros));
	}

	// 1 Euro = 1.4008 U.S. Dollars
	private double eurosToDollars(double euros)
	{
		return 1.4008 * euros;
	}
	
	/**
	 * Publicly available channel on which queries of money to change are sent.
	 */
	public static IChan<Query> queryChannel =
		new Chan<Query>();

}
