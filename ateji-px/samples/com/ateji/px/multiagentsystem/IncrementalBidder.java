/****************************
 * Copyright (c) 2009 ATEJI *
 * All rights reserved.     *
 ****************************/
package com.ateji.px.multiagentsystem;

import apx.lang.IChan;
import apx.lang.Signal;

/**
 * The strategy of IncrementalBidder is to increment each bid by one
 * until a maximum offer is reached.
 */
public class IncrementalBidder extends Bidder
{

	@Override
	public void run()
	{
		int nextBid = 0;
		boolean exit = false;
		while(true) {
			// The agent will react to every interaction it has with the environment:
			//   - it bids until it has reached its maximum,
			//   - it quits when a higher bid was made, and
			//   - it returns when it wins or when it quits.
			{
final apx . lang . gen . MutableReferenceBoolean exit0 = new apx . lang . gen . MutableReferenceBoolean (exit );
final apx . lang . gen . MutableReferenceInt nextBid0 = new apx . lang . gen . MutableReferenceInt (nextBid );
{
			final java . util . List < apx . lang . gen . Choice < ? > > choices =new java . util . ArrayList < apx . lang . gen . Choice < ? > > ();
if((nextBid0 .ref != 0) ) { {
choices .add (new apx . lang . gen . Choice < java . lang . Integer > (null, IncrementalBidder .this.bidActuator , nextBid0.ref ){
public @java .lang .Override void run () throws java . lang . Throwable {
nextBid0 .ref = 0;
			}
}
);
}
} {
choices .add (new apx . lang . gen . Choice < java . lang . Integer > (null, IncrementalBidder .this.bidSensor ){
public @java .lang .Override void run () throws java . lang . Throwable {
int bid=this.value ();
{
				if(bid + 1 <= IncrementalBidder .this.maxOffer) 
				{
					nextBid0 .ref = bid + 1;
				}
				else
				{
					exit0 .ref = true;
				}
			}
			}
}
);
}
if((exit0.ref ) ) { {
choices .add (new apx . lang . gen . Choice < java . lang . Void > (null, IncrementalBidder .this.quitAuctionActuator , apx . lang . Signal .DUMMY ){
public @java .lang .Override void run () throws java . lang . Throwable {
throw new apx . lang . gen . ReturnException ();
			}
}
);
}
} {
choices .add (new apx . lang . gen . Choice < java . lang . Void > (null, IncrementalBidder .this.winAuctionSensor){
public @java .lang .Override void run () throws java . lang . Throwable {
{
				System.out.println(IncrementalBidder .this.name+" wins");
				throw new apx . lang . gen . ReturnException ();
			}
			}
}
);
}
final apx . lang . gen . ExitStatus exitStatus3 =apx . lang . gen . Select .select (null, choices );
nextBid = nextBid0 .ref ;
exit = exit0 .ref ;
if(exitStatus3 .hasReturned ()) {
return ;
} {
final java . lang . Throwable throwable3 =exitStatus3 .thrownException ();
if(throwable3 !=null) {
if(throwable3 instanceof java . lang . RuntimeException ) throw (java . lang . RuntimeException )throwable3 ;
if(throwable3 instanceof java . lang . Error ) throw (java . lang . Error )throwable3 ;
}
}
}
		}
}
	}

	private final int maxOffer;

	public IncrementalBidder(String name, int maxOffer,
			IChan<Integer> bidSensor, Signal winAuctionSensor,
			IChan<Integer> bidActuator, Signal quitAuctionActuator)
	{
		super (name, bidSensor, winAuctionSensor, bidActuator, quitAuctionActuator); this.maxOffer = maxOffer;
	}

}
