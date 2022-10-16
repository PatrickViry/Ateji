/****************************
 * Copyright (c) 2009 ATEJI *
 * All rights reserved.     *
 ****************************/
package com.ateji.px.multiagentsystem;

import apx.lang.Chan;
import apx.lang.Signal;

import java.util.ArrayList;
import java.util.List;

/**
 * This example shows how to model a Multi-agent system with Ateji PX.
 * (http://en.wikipedia.org/wiki/Multi-agent_system)
 * 
 * An agent is a program that can:
 *  - perceive its environment through sensors, and
 *  - make action on this environment through actuators.
 *  
 * In Ateji PX, the sensors and actuators will be represented by channels. An
 * agent will have no other way to communicate. For example, there is no public
 * method that can change its internal state.
 * 
 * A typical example of multi agent system is auction. Each agent (bidder)
 * has a strategy to buy an item at the best price.
 */
public class Main
{
	
	public static void main(String[] args)
	{
		List<Bidder> bidders = sampleBidders();
		AuctionEnvironment  auctionEnv = new AuctionEnvironment(bidders);
		
		// Run the auction environment and all the bidders in parallel.
		// Their only way to communicate is to use sensors and actuators.
		{
final com . ateji . px . multiagentsystem . AuctionEnvironment auctionEnv0 = auctionEnv ;
{
		 	final java . util . List < apx . lang . gen . Branch > branches9 =new java . util . ArrayList < apx . lang . gen . Branch > ();
final java . util . List < apx . lang . gen . Bang < ? > > bangs9 =new java . util . ArrayList < apx . lang . gen . Bang < ? > > ();
final apx . lang . gen . Parallel parallelBlock9 =apx . lang . gen . Parallel .getParallelBlock (null);
{
apx . lang . gen . Branch branch13 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
auctionEnv0.run();
		 	
		 	}
}
;
branches9 .add (branch13 );
}
for ( final Bidder bidder : bidders) {
{
apx . lang . gen . Branch branch14 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
{
		 		bidder.run();
		 	}
		}
}
;
branches9 .add (branch14 );
}
}
final apx . lang . gen . ExitStatus exitStatus12 =parallelBlock9 . run (branches9 , bangs9 );
if(exitStatus12 .hasReturned ()) {
return ;
} {
final java . lang . Throwable throwable12 =exitStatus12 .thrownException ();
if(throwable12 !=null) {
if(throwable12 instanceof java . lang . RuntimeException ) throw (java . lang . RuntimeException )throwable12 ;
if(throwable12 instanceof java . lang . Error ) throw (java . lang . Error )throwable12 ;
}
}
}
	}
}

	private static List<Bidder> sampleBidders()
	{
		List<Bidder> bidders = new ArrayList<Bidder>();
		bidders.add(john);
		bidders.add(kate);
		bidders.add(more);
		bidders.add(anna);
		return bidders;
	}
	private static Bidder john = new ConstantBidder("John", 500, new Chan<Integer>(), new Signal(), new Chan<Integer>(), new Signal());
	private static Bidder kate = new ConstantBidder("Kate", 700, new Chan<Integer>(), new Signal(), new Chan<Integer>(), new Signal());
	private static Bidder more = new IncrementalBidder("More", 750, new Chan<Integer>(), new Signal(), new Chan<Integer>(), new Signal());
	private static Bidder anna = new IncrementalBidder("Anna", 730, new Chan<Integer>(), new Signal(), new Chan<Integer>(), new Signal());
	
}
