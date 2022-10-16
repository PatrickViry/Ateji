/****************************
 * Copyright (c) 2009 ATEJI *
 * All rights reserved.     *
 ****************************/
package com.ateji.px.dataparallelism.mandelbrot;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Graphic code. Does not show any Ateji PX feature.
*/ 
public class MandelbrotSlider extends JSlider implements ChangeListener
{
	private static final long serialVersionUID = -1154061587291889386L;

	private final static int minimum = 100;
	private final static int maximum = 1000000;
	private static int value = maximum - minimum - 100000;
	
	public MandelbrotSlider(MandelbrotSet mandelbrotSet)
	{
		this.mandelbrotSet = mandelbrotSet;
		this.mandelbrotSet.convergenceThreshold = maxIterations();
		
		setMinimum(minimum);
		setMaximum(maximum);
		setValue(value);
		addChangeListener(this);
	}

	private final MandelbrotSet mandelbrotSet;
	
	public void stateChanged(ChangeEvent e)
	{
        JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting()) {
        	value = source.getValue();
        	mandelbrotSet.convergenceThreshold = maxIterations();
        }
	}
	
	private int maxIterations() {
		return minimum + maximum - value;
	}
}
