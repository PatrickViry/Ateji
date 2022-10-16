/****************************
 * Copyright (c) 2009 ATEJI *
 * All rights reserved.     *
 ****************************/
package com.ateji.px.dataparallelism.mandelbrot;

/**
 * The purpose of this example is to show the parallelism and the indications
 * of Ateji PX. It shows different algorithms building the Mandelbrot set.
 * 
 * Mandelbrot set is a 2D image where the color of each pixel is computed by
 * iterating the complex function:
 *   z(0) = c
 *   z(n+1) = z(n)*z(n) + c
 * where c is the complex number (a+bi) giving the position of the pixel in the
 * complex plane. Color corresponds to the number of iterations required to
 * compute the pixel.
 *
 * As computation of any given two pixels is completely independent, there
 * exists a lot of ways to parallelize the computation of the Mandelbrot set.
 * Here we express some of them with Ateji PX.
 */
public class Main
{

	public static void main(String[] args)
	{
		// size of the Mandelbrot set
		int nx = 500; // number of pixels on x dim
		int ny = 500; // number of pixels on y dim
		MandelbrotSet mandelbrot;
		
		// splitting the work by splitting lines in as many blocks as there are processors
		mandelbrot = new NBlocksLinesStrategy(nx, ny);
		mandelbrot.display();
		
		// splitting the work by splitting columns in as many blocks as there are processors
		mandelbrot = new NBlocksColumnsStrategy(nx, ny); 		
		mandelbrot.display();
		
		// splitting the work by splitting lines in many small blocks
		mandelbrot = new GrainSizeLinesStrategy(nx, ny); 		
		mandelbrot.display();
		
		// splitting the work by splitting columns in many small blocks
		mandelbrot = new GrainSizeColumnsStrategy(nx, ny); 		
		mandelbrot.display();
	}
	
}
