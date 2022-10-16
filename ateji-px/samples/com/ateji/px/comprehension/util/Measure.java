/****************************
 * Copyright (c) 2009 ATEJI *
 * All rights reserved.     *
 ****************************/
package com.ateji.px.comprehension.util;

import java.text.DecimalFormat;

/**
 * This class embeds the logic to make a proper comparative performance
 * measurement between sequential and parallel comprehensions.
 */
public abstract class Measure
{
	
	
	public long measure()
	{
		long start; long end;
		final int count = 20;
		final int freshVariable17 =count;final long[] time=new long [freshVariable17 ];

		for(int c=0; c<count; c++) {			
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
		return meanTime;
		
	}
	
	public abstract String name();
	
	public abstract void run();
	
	
	private static DecimalFormat df = new DecimalFormat();
	static {
		df.setMaximumFractionDigits(3);
	}

}
