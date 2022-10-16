/****************************
 * Copyright (c) 2009 ATEJI *
 * All rights reserved.     *
 ****************************/
package com.ateji.px.dataparallelism.mandelbrot;

/**
 * This example shows how to share the computation with all processors of the
 * machine by splitting the parallel block columns iterator.
 * 
 * Visually, several columns of the Mandelbrot set are displayed in parallel.
 */
public class GrainSizeColumnsStrategy extends MandelbrotSet
{

	@Override
	public void runStrategy()
	{
		
		 // The indication '#BlockSize(grainSize)' splits the iterator 'ix'
		 // (the columns) in blocks of size 'grainSize'.
		 {
{
final apx . util . RangeInteger range3 = new apx . util . RangeInteger (0 , (nx)- 1 ); final int blockcount8 = (((range3 ). max )- ((range3 ). min )+ 1 )/ (10)+ ((((range3 ). max )- ((range3 ). min )+ 1 )% (10)== 0 ? 0 : 1 ); { final java . util . List < apx . lang . gen . Branch > branches2 =new java . util . ArrayList < apx . lang . gen . Branch > ();
final java . util . List < apx . lang . gen . Bang < ? > > bangs2 =new java . util . ArrayList < apx . lang . gen . Bang < ? > > ();
final apx . lang . gen . Parallel parallelBlock2 =apx . lang . gen . Parallel .getParallelBlock (null);
{
final apx . util . RangeInteger range13 = new apx . util . RangeInteger (0 , ((blockcount8 )- 1 ));
final int last4 = (range13 ). max ;
for(int block2 = (range13 ). min ;block2 <= last4 ;block2 ++ )
{
final int nbBlock8 = block2 ;
{
{
apx . lang . gen . Branch branch3 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
{
final apx . util . RangeInteger range14 = new apx . util . RangeInteger ((java . lang . Math . min (((range3 ). max ), ((range3 ). min )+ (10)* (nbBlock8 ))), (java . lang . Math . min (((range3 ). max ), ((range3 ). min )+ (10)* ((nbBlock8 )+ 1 )- 1 )));
final int last5 = (range14 ). max ;
for(int block3 = (range14 ). min ;block3 <= last5 ;block3 ++ )
{
final int ix = block3 ;
{
{
final apx . util . RangeInteger range15 = new apx . util . RangeInteger (0 , ((GrainSizeColumnsStrategy .this.ny)- 1 ));
final int last6 = (range15 ). max ;
for(int block4 = (range15 ). min ;block4 <= last6 ;block4 ++ )
{
int iy = block4 ;
{
{
{
		 	GrainSizeColumnsStrategy .this.computeAndDraw(ix, iy);
		 }
		
	}
}
}
}
}
}
}
}
}
;
branches2 .add (branch3 );
}
}
}
}
final apx . lang . gen . ExitStatus exitStatus2 =parallelBlock2 . run (branches2 , bangs2 );
if(exitStatus2 .hasReturned ()) {
return ;
} {
final java . lang . Throwable throwable2 =exitStatus2 .thrownException ();
if(throwable2 !=null) {
if(throwable2 instanceof java . lang . RuntimeException ) throw (java . lang . RuntimeException )throwable2 ;
if(throwable2 instanceof java . lang . Error ) throw (java . lang . Error )throwable2 ;
}
}
} }
}
}

	@Override
	public String title()
	{
		return "Columns split in blocks of size " + grainSize;
	}
	
	final int grainSize = 10;

	public GrainSizeColumnsStrategy(int nx, int ny)
	{
		super (nx, ny); }

}
