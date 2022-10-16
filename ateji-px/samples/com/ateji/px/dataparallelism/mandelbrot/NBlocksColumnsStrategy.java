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
 * 
 * NOTE: if only one processor is available, the lines are split in two blocks,
 * as if two processors were available. Otherwise, the example would not be
 * interesting to see.
 */
public class NBlocksColumnsStrategy extends MandelbrotSet
{

	@Override
	public void runStrategy()
	{
		 // The indication '#BlockCount(nBlocks)' splits the iterator 'ix'
		 // (the columns) in 'nBlocks' blocks.
		 {
{
final apx . util . RangeInteger range5 = new apx . util . RangeInteger (0 , (nx)- 1 ); final int blockcount10 = nBlocks; { final java . util . List < apx . lang . gen . Branch > branches20 =new java . util . ArrayList < apx . lang . gen . Branch > ();
final java . util . List < apx . lang . gen . Bang < ? > > bangs20 =new java . util . ArrayList < apx . lang . gen . Bang < ? > > ();
final apx . lang . gen . Parallel parallelBlock20 =apx . lang . gen . Parallel .getParallelBlock (null);
{
final apx . util . RangeInteger range53 = new apx . util . RangeInteger (0 , ((blockcount10 )- 1 ));
final int last44 = (range53 ). max ;
for(int block42 = (range53 ). min ;block42 <= last44 ;block42 ++ )
{
final int nbBlock10 = block42 ;
{
{
apx . lang . gen . Branch branch32 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
{
final apx . util . RangeInteger range54 = new apx . util . RangeInteger ((((range5 ). min )+ ((((range5 ). max )- ((range5 ). min )+ 1 )/ (NBlocksColumnsStrategy .this.nBlocks)+ ((((range5 ). max )- ((range5 ). min )+ 1 )% (NBlocksColumnsStrategy .this.nBlocks)== 0 ? 0 : 1 ))* (nbBlock10 )), (java . lang . Math . min (((range5 ). max ), ((range5 ). min )+ ((((range5 ). max )- ((range5 ). min )+ 1 )/ (NBlocksColumnsStrategy .this.nBlocks)+ ((((range5 ). max )- ((range5 ). min )+ 1 )% (NBlocksColumnsStrategy .this.nBlocks)== 0 ? 0 : 1 ))* ((nbBlock10 )+ 1 )- 1 )));
final int last45 = (range54 ). max ;
for(int block43 = (range54 ). min ;block43 <= last45 ;block43 ++ )
{
final int ix = block43 ;
{
{
final apx . util . RangeInteger range55 = new apx . util . RangeInteger (0 , ((NBlocksColumnsStrategy .this.ny)- 1 ));
final int last46 = (range55 ). max ;
for(int block44 = (range55 ). min ;block44 <= last46 ;block44 ++ )
{
int iy = block44 ;
{
{
{
		 	NBlocksColumnsStrategy .this.computeAndDraw(ix, iy);
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
branches20 .add (branch32 );
}
}
}
}
final apx . lang . gen . ExitStatus exitStatus23 =parallelBlock20 . run (branches20 , bangs20 );
if(exitStatus23 .hasReturned ()) {
return ;
} {
final java . lang . Throwable throwable23 =exitStatus23 .thrownException ();
if(throwable23 !=null) {
if(throwable23 instanceof java . lang . RuntimeException ) throw (java . lang . RuntimeException )throwable23 ;
if(throwable23 instanceof java . lang . Error ) throw (java . lang . Error )throwable23 ;
}
}
} }
}
}

	@Override
	public String title()
	{
		return "Columns split in " + nBlocks + " blocks";
	}
	
	final int nBlocks;

	public NBlocksColumnsStrategy(int nx, int ny)
	{
		super (nx, ny); int nBlocks = Runtime.getRuntime().availableProcessors();
		// if there is only one processor, do as if there were two
		if(nBlocks == 1) {
			nBlocks = 2;
		}
		this.nBlocks = nBlocks;
	}

}
