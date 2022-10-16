/****************************
 * Copyright (c) 2009 ATEJI *
 * All rights reserved.     *
 ****************************/
package com.ateji.px.dataparallelism.mandelbrot;

/**
 * This example shows how to share the computation with all processors of the
 * machine by splitting the parallel block lines iterator.
 * 
 * Visually, several lines of the Mandelbrot set are displayed in parallel.
 * 
 * NOTE: if only one processor is available, the lines are split in two blocks,
 * as if two processors were available. Otherwise, the example would not be
 * interesting to see.
 */
public class NBlocksLinesStrategy extends MandelbrotSet
{

	@Override
	public void runStrategy()
	{
		
		 // The indication '#BlockCount(nBlocks)' splits the iterator 'iy'
		 // (the lines) in 'nBlocks.
		 {
{
final apx . util . RangeInteger range4 = new apx . util . RangeInteger (0 , (ny)- 1 ); final int blockcount9 = nBlocks; { final java . util . List < apx . lang . gen . Branch > branches4 =new java . util . ArrayList < apx . lang . gen . Branch > ();
final java . util . List < apx . lang . gen . Bang < ? > > bangs4 =new java . util . ArrayList < apx . lang . gen . Bang < ? > > ();
final apx . lang . gen . Parallel parallelBlock4 =apx . lang . gen . Parallel .getParallelBlock (null);
{
final apx . util . RangeInteger range29 = new apx . util . RangeInteger (0 , ((blockcount9 )- 1 ));
final int last20 = (range29 ). max ;
for(int block18 = (range29 ). min ;block18 <= last20 ;block18 ++ )
{
final int nbBlock9 = block18 ;
{
{
apx . lang . gen . Branch branch5 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
{
final apx . util . RangeInteger range30 = new apx . util . RangeInteger ((((range4 ). min )+ ((((range4 ). max )- ((range4 ). min )+ 1 )/ (NBlocksLinesStrategy .this.nBlocks)+ ((((range4 ). max )- ((range4 ). min )+ 1 )% (NBlocksLinesStrategy .this.nBlocks)== 0 ? 0 : 1 ))* (nbBlock9 )), (java . lang . Math . min (((range4 ). max ), ((range4 ). min )+ ((((range4 ). max )- ((range4 ). min )+ 1 )/ (NBlocksLinesStrategy .this.nBlocks)+ ((((range4 ). max )- ((range4 ). min )+ 1 )% (NBlocksLinesStrategy .this.nBlocks)== 0 ? 0 : 1 ))* ((nbBlock9 )+ 1 )- 1 )));
final int last21 = (range30 ). max ;
for(int block19 = (range30 ). min ;block19 <= last21 ;block19 ++ )
{
final int iy = block19 ;
{
{
final apx . util . RangeInteger range31 = new apx . util . RangeInteger (0 , ((NBlocksLinesStrategy .this.nx)- 1 ));
final int last22 = (range31 ). max ;
for(int block20 = (range31 ). min ;block20 <= last22 ;block20 ++ )
{
int ix = block20 ;
{
{
{
		 	NBlocksLinesStrategy .this.computeAndDraw(ix, iy);
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
branches4 .add (branch5 );
}
}
}
}
final apx . lang . gen . ExitStatus exitStatus5 =parallelBlock4 . run (branches4 , bangs4 );
if(exitStatus5 .hasReturned ()) {
return ;
} {
final java . lang . Throwable throwable5 =exitStatus5 .thrownException ();
if(throwable5 !=null) {
if(throwable5 instanceof java . lang . RuntimeException ) throw (java . lang . RuntimeException )throwable5 ;
if(throwable5 instanceof java . lang . Error ) throw (java . lang . Error )throwable5 ;
}
}
} }
}
}

	@Override
	public String title()
	{
		return "Lines split in " + nBlocks + " blocks";
	}
	
	final int nBlocks;

	public NBlocksLinesStrategy(int nx, int ny)
	{
		super (nx, ny); int nBlocks = Runtime.getRuntime().availableProcessors();
		// if there is only one processor, do as if there were two
		if(nBlocks == 1) {
			nBlocks = 2;
		}
		this.nBlocks = nBlocks;
	}
	
}
