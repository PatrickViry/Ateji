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
 */
public class GrainSizeLinesStrategy extends MandelbrotSet
{

	@Override
	public void runStrategy()
	{
		
		 // The indication '#BlockSize(grainSize)' splits the iterator 'iy'
		 // (the lines) in blocks of size 'grainSize'.
		 {
{
final apx . util . RangeInteger range2 = new apx . util . RangeInteger (0 , (ny)- 1 ); final int blockcount7 = (((range2 ). max )- ((range2 ). min )+ 1 )/ (blockSize)+ ((((range2 ). max )- ((range2 ). min )+ 1 )% (blockSize)== 0 ? 0 : 1 ); { final java . util . List < apx . lang . gen . Branch > branches =new java . util . ArrayList < apx . lang . gen . Branch > ();
final java . util . List < apx . lang . gen . Bang < ? > > bangs =new java . util . ArrayList < apx . lang . gen . Bang < ? > > ();
final apx . lang . gen . Parallel parallelBlock =apx . lang . gen . Parallel .getParallelBlock (null);
{
final apx . util . RangeInteger range10 = new apx . util . RangeInteger (0 , ((blockcount7 )- 1 ));
final int last1 = (range10 ). max ;
for(int block = (range10 ). min ;block <= last1 ;block ++ )
{
final int nbBlock7 = block ;
{
{
apx . lang . gen . Branch branch = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
{
final apx . util . RangeInteger range11 = new apx . util . RangeInteger ((java . lang . Math . min (((range2 ). max ), ((range2 ). min )+ (GrainSizeLinesStrategy .this.blockSize)* (nbBlock7 ))), (java . lang . Math . min (((range2 ). max ), ((range2 ). min )+ (GrainSizeLinesStrategy .this.blockSize)* ((nbBlock7 )+ 1 )- 1 )));
final int last2 = (range11 ). max ;
for(int block0 = (range11 ). min ;block0 <= last2 ;block0 ++ )
{
final int iy = block0 ;
{
{
final apx . util . RangeInteger range12 = new apx . util . RangeInteger (0 , ((GrainSizeLinesStrategy .this.nx)- 1 ));
final int last3 = (range12 ). max ;
for(int block1 = (range12 ). min ;block1 <= last3 ;block1 ++ )
{
int ix = block1 ;
{
{
{
		 	GrainSizeLinesStrategy .this.computeAndDraw(ix, iy);
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
branches .add (branch );
}
}
}
}
final apx . lang . gen . ExitStatus exitStatus =parallelBlock . run (branches , bangs );
if(exitStatus .hasReturned ()) {
return ;
} {
final java . lang . Throwable throwable =exitStatus .thrownException ();
if(throwable !=null) {
if(throwable instanceof java . lang . RuntimeException ) throw (java . lang . RuntimeException )throwable ;
if(throwable instanceof java . lang . Error ) throw (java . lang . Error )throwable ;
}
}
} }
}
}
	
	@Override
	public String title()
	{
		return "Lines split in blocks of size " + blockSize;
	}
	
	final int blockSize = 10;

	public GrainSizeLinesStrategy(int nx, int ny)
	{
		super (nx, ny); }

}
