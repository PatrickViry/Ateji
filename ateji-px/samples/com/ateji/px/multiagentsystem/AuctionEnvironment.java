/****************************
 * Copyright (c) 2009 ATEJI *
 * All rights reserved.     *
 ****************************/
package com.ateji.px.multiagentsystem;

import java.util.List;

/**
 * The environment must react to the actions of agents (through actuators) and
 * warn them of the evolution of the system (through sensors).
 */
public class AuctionEnvironment
{

	public void run() 
	{		
		int bestBid = 0;
		while(true) {
				{
final apx . lang . gen . MutableReferenceInt bestBid0 = new apx . lang . gen . MutableReferenceInt (bestBid );
{
					// receive a bid from one of the bidders
					// this is equivalent to having a case for each bidder
					final java . util . List < apx . lang . gen . Choice < ? > > choices0 =new java . util . ArrayList < apx . lang . gen . Choice < ? > > ();
for ( final Bidder bidder : AuctionEnvironment .this.bidders) {
{
choices0 .add (new apx . lang . gen . Choice < java . lang . Integer > (null, bidder.bidActuator ){
public @java .lang .Override void run () throws java . lang . Throwable {
int bid=this.value ();
{
						if(bid > bestBid0.ref ) {
							bestBid0.ref =bid;
							System.out.println(bidder + " offers " + bestBid0.ref );
							for ( Bidder b : AuctionEnvironment .this.bidders) {
if((b!=bidder)) { {
								// inform all other bidders
								b.bidSensor . send (bestBid0.ref );
							}
						} }
}
					}
					// receive a signal when a bidder quits the auction;
					}
}
);
}
}
for ( final Bidder bidder : AuctionEnvironment .this.bidders) {
{
choices0 .add (new apx . lang . gen . Choice < java . lang . Void > (null, bidder.quitAuctionActuator ){
public @java .lang .Override void run () throws java . lang . Throwable {
{
						AuctionEnvironment .this.bidders.remove(bidder);
						System.out.println(bidder+" quits");
						if (AuctionEnvironment .this.bidders.size() == 1) {
							// inform the last bidder that he wins the auction
							AuctionEnvironment .this.bidders.get(0).winAuctionSensor . send ();
							// close the environment
							throw new apx . lang . gen . ReturnException ();
						}
					}
				}
}
);
}
}
final apx . lang . gen . ExitStatus exitStatus8 =apx . lang . gen . Select .select (null, choices0 );
bestBid = bestBid0 .ref ;
if(exitStatus8 .hasReturned ()) {
return ;
} {
final java . lang . Throwable throwable8 =exitStatus8 .thrownException ();
if(throwable8 !=null) {
if(throwable8 instanceof java . lang . RuntimeException ) throw (java . lang . RuntimeException )throwable8 ;
if(throwable8 instanceof java . lang . Error ) throw (java . lang . Error )throwable8 ;
}
}
}
		}
}
	}
	
	private final List<Bidder> bidders;

	public AuctionEnvironment(List<Bidder> bidders) 
	{
		this.bidders = bidders;
	}
	
}
