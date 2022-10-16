/****************************
 * Copyright (c) 2009 ATEJI *
 * All rights reserved.     *
 ****************************/
package com.ateji.px.dataparallelism.matrixmult;

import java.text.DecimalFormat;

/**
 * This class embeds the logic to make a proper comparative performance
 * measurement between different algorithms for matrix multiplication.
 */
abstract class Measure
{
	
	// C = A * B
	
	private final double[][] C;
	
	void measure()
	{
		long start; long end;
		final int count = 20;
		final int freshVariable0 =count;final long[] time=new long [freshVariable0 ];

		for(int c=0; c<count; c++) {
			{
final apx . util . RangeInteger range32 = new apx . util . RangeInteger (0 , ((C.length)- 1 ));
final int last23 = (range32 ). max ;
for(int block21 = (range32 ). min ;block21 <= last23 ;block21 ++ )
{
int i = block21 ;
{
{
final apx . util . RangeInteger range33 = new apx . util . RangeInteger (0 , ((C[i].length)- 1 ));
final int last24 = (range33 ). max ;
for(int block22 = (range33 ). min ;block22 <= last24 ;block22 ++ )
{
int j = block22 ;
{
{
				C[i][j] = 0.0;
			}
			
			}
}
}
}
}
}
start = System.nanoTime();
			run();
			end = System.nanoTime();
			time[c] = (end - start) / 1000000; // convert to ms
		}
		
		// average value after removing the first 3 entries
		long meanTime = 0;
		for(int c=3; c<count; c++) {
			meanTime += time[c];
		}
		meanTime = meanTime / (count - 3);
		
		System.out.print(name() + ": mean time = " + meanTime + " ms; ");
		
		// standard_deviation after removing the first 3 entries
		long sum = 0;
		for (int c=3; c<count; c++){
			sum+= (time[c] - meanTime) * (time[c] - meanTime);
		}
		double sigma = Math.sqrt(sum / (count - 3));
		
		System.out.print("standard deviation = "+df.format(sigma)+" ms; ( ");
		
		for(int c=0; c<count; c++) {
			System.out.print(time[c] + " ");
		}
		System.out.println(")");
		
		// check if the result was correct
		{
final apx . util . RangeInteger range34 = new apx . util . RangeInteger (0 , ((C.length)- 1 ));
final int last25 = (range34 ). max ;
for(int block23 = (range34 ). min ;block23 <= last25 ;block23 ++ )
{
int i = block23 ;
{
{
final apx . util . RangeInteger range35 = new apx . util . RangeInteger (0 , ((C[i].length)- 1 ));
final int last26 = (range35 ). max ;
for(int block24 = (range35 ). min ;block24 <= last26 ;block24 ++ )
{
int j = block24 ;
{
{
			if(C[i][j] != MatrixMult.check[i][j]) {
				throw new Error("Incorrect result");
			}
		}
	}
}
}
}
}
}
}
	
	abstract String name();
	
	abstract void run();
	
	Measure(double [][] C)
	{
		this.C = C;
	}
	
	private static DecimalFormat df = new DecimalFormat();
	static {
		df.setMaximumFractionDigits(3);
	}

}
