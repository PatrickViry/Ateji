/****************************
 * Copyright (c) 2009 ATEJI *
 * All rights reserved.     *
 ****************************/
package com.ateji.px.dataparallelism.mandelbrot;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

import javax.swing.JFrame;
import javax.swing.JSlider;

/**
 * This class contains the common code of the strategies computing and
 * displaying the Mandelbrot set.
 */
public abstract class MandelbrotSet
{
	
	/**
	 * Compute and draw the whole Mandelbrot set.
	 * 
	 * Subclasses specifies their strategy by implementing this method.
	 * 
	 * Different strategies exploit parallelism in different ways to draw
	 * the Mandelbrot set.
	 */
	public abstract void runStrategy();
	
	/**
	 * Title of the window in which the set is drawn. 
	 * It is used to distinguish the different strategies.
	 */
	public abstract String title();
	
	// size of the Mandelbrot set
	final int nx; // number of pixels on x dim
	final int ny; // number of pixels on y dim

	public MandelbrotSet(int nx, int ny)
	{
		this.nx = nx;
		this.ny = ny;
	}

	/**
	 * Compute the color of the given dot at 'x' and 'y', and then display the
	 * result.
	 * 
	 * This method is called once for every dot of the image by the
	 * 'runStrategy' method.
	 */
	 public void computeAndDraw(int x, int y)
	 {
		 // If the module of z(n) is more than 2 then z(n) will diverge.
		 // So the Mandelbrot set is necessarily included in the circle of
		 // radius 2 and center (0,0).
		 // So only the [-2,2] * [-2, 2] subset of the plan is used, divided in
		 // nx * ny pixels.
		 // The following code associates a pixel (x,y) to its coordinates
		 // (x0,y0) in the rectangle [-2,2] * [-2, 2]
		 double x0 = 4.0*x/nx -2;
		 double y0 = 4.0*y/nx -2;

		 // Compute the number of iterations required to get the Mandelbrot
		 // value at these coordinates.
		 int mandelbrotIterations = mandelbrotIterations(x0, y0);
		 
		 // Call the graphic code that will display the result.
		 displayPixel(x, y, mandelbrotIterations);
	 }

	 // Mathematical definition of the Mandelbrot values:
	 //   c = x0 + i* y0 and
	 //   z(0) = c
	 //   z(n+1) = z(n)*z(n) + c
	 // How to compute Mandelbrot values from x0 and y0:
	 //   x(0) = x0
	 //   x(n+1) = x(n)*x(n) - y(n)*y(n) + x0
	 //   y(0) = y0
	 //   y(n+1) = 2*y(n)*x(n) + y0
	private int mandelbrotIterations(double x0, double y0)
	{
		int iterations = 1;
		double xn = x0;
		double yn = y0;
		while ((iterations <= convergenceThreshold) && (xn*xn + yn*yn < 4)) {
			// x(n+1) = x(n)*x(n) - y(n)*y(n) + x0
			double xnplus1 = xn*xn - yn*yn + x0;
			// y(n+1) = 2*y(n)*x(n) + y0
			double ynplus1 = 2*xn*yn + y0;
			
			xn = xnplus1;
			yn = ynplus1;
			iterations++;
		}
		
		if (iterations > convergenceThreshold) {
			return 0; // z(n) will converge at this pixel
		} else {
			return iterations;
		}
	}
	
	// Can be user-defined via the slider in the GUI.
	int convergenceThreshold;
	
	// Display the Mandelbrot set.
	public void display()
	{
		// Boring graphic code: create and display the window that will embed
		// the image of the Mandelbrot set.
		prepareWindow();
		
		// Run the interesting code that will compute and draw the image
		// following a given strategy.
		runStrategy();
	}
	
	// Graphic code.
	private void prepareWindow() {
		slider = new MandelbrotSlider(this);
		bufferedImage = new BufferedImage(nx, ny, BufferedImage.TYPE_INT_ARGB);
		frame = new MandelbrotFrame(title(), bufferedImage, slider);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.repaint();
	}
	
	// Graphic code.
	private void displayPixel(int px, int py, int mandelbrotIterations)
	{
		WritableRaster writableRaster = bufferedImage.getRaster();
		ColorModel colorModel = bufferedImage.getColorModel();
		Color color;
		if (mandelbrotIterations == 0) {
			color = Color.BLACK;
		} else {
			color = Color.getHSBColor(
					(float) (Math.log(mandelbrotIterations) / Math.log(convergenceThreshold)),
					1f, 1f);
		}
		int c = color.getRGB();
		Object object = colorModel.getDataElements(c, null);
		writableRaster.setDataElements(px, py, object);
		frame.repaint();
	}
	
	private JFrame frame;
	private BufferedImage bufferedImage;
	private JSlider slider;
	
}
