/****************************
 * Copyright (c) 2009 ATEJI *
 * All rights reserved.     *
 ****************************/
package com.ateji.px.multiagentsystem;

import apx.lang.IChan;
import apx.lang.Signal;

/**
 * The strategy of ConstantBidder is to offer a fixed amount and
 * wait until the end of auction.
 */
public class ConstantBidder extends Bidder
{

	@Override
	public void run()
	{
		boolean exit = false;
		while(true) {			
			// The agent will react to every interaction it has with the environment:
			//   - it bids as soon as possible,
			//   - it quits when a higher bid has been made, and
			//   - it returns when it wins or when it quits.
			{
final apx . lang . gen . MutableReferenceBoolean exit1 = new apx . lang . gen . MutableReferenceBoolean (exit );
{
			final java . util . List < apx . lang . gen . Choice < ? > > choices1 =new java . util . ArrayList < apx . lang . gen . Choice < ? > > ();
if((!exit1.ref )) { {
choices1 .add (new apx . lang . gen . Choice < java . lang . Integer > (null, ConstantBidder .this.bidActuator , ConstantBidder .this.myBid){
public @java .lang .Override void run () throws java . lang . Throwable {
;
			}
}
);
}
} {
choices1 .add (new apx . lang . gen . Choice < java . lang . Integer > (null, ConstantBidder .this.bidSensor ){
public @java .lang .Override void run () throws java . lang . Throwable {
int bid=this.value ();
if (bid > ConstantBidder .this.myBid) exit1 .ref = true;
			}
}
);
}
if((exit1.ref )) { {
choices1 .add (new apx . lang . gen . Choice < java . lang . Void > (null, ConstantBidder .this.quitAuctionActuator , apx . lang . Signal .DUMMY ){
public @java .lang .Override void run () throws java . lang . Throwable {
throw new apx . lang . gen . ReturnException ();
			}
}
);
}
} {
choices1 .add (new apx . lang . gen . Choice < java . lang . Void > (null, ConstantBidder .this.winAuctionSensor){
public @java .lang .Override void run () throws java . lang . Throwable {
{
				System.out.println(ConstantBidder .this.name+" wins");
				throw new apx . lang . gen . ReturnException ();
			}
			}
}
);
}
final apx . lang . gen . ExitStatus exitStatus11 =apx . lang . gen . Select .select (null, choices1 );
exit = exit1 .ref ;
if(exitStatus11 .hasReturned ()) {
return ;
} {
final java . lang . Throwable throwable11 =exitStatus11 .thrownException ();
if(throwable11 !=null) {
if(throwable11 instanceof java . lang . RuntimeException ) throw (java . lang . RuntimeException )throwable11 ;
if(throwable11 instanceof java . lang . Error ) throw (java . lang . Error )throwable11 ;
}
}
}
		}
}
	}

	private final int myBid;

	public ConstantBidder(String name, int myBid,
			IChan<Integer> bidSensor, Signal winAuctionSensor,
			IChan<Integer> bidActuator, Signal quitAuctionActuator)
	{
		super (name, bidSensor, winAuctionSensor, bidActuator, quitAuctionActuator); this.myBid = myBid;
	}

}
