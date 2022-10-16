/****************************
 * Copyright (c) 2009 ATEJI *
 * All rights reserved.     *
 ****************************/
package com.ateji.px.dataparallelism.mandelbrot;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JSlider;

/**
 * Graphic code. Does not show any Ateji PX feature.
*/ 
public class MandelbrotFrame extends JFrame
{
	private static final long serialVersionUID = -4307281355287491460L;

	private static int offset = 0;

	public MandelbrotFrame(String title, BufferedImage bufferedImage, JSlider slider)
	{
		setTitle(title);
		Container contentPane = getContentPane();
		contentPane.add(new MandelbrotPanel(bufferedImage), BorderLayout.CENTER);
	    
	    slider.setBorder(BorderFactory.createTitledBorder("Speed"));
	    contentPane.add(slider, BorderLayout.SOUTH);
	    
	    setLocation(offset, offset);
	    offset += 30;
	    
	    pack();
	}
}
