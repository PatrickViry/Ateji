/****************************
 * Copyright (c) 2009 ATEJI *
 * All rights reserved.     *
 ****************************/
package com.ateji.px.dataparallelism.mandelbrot;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * Graphic code. Does not show any Ateji PX feature.
*/ 
public class MandelbrotPanel extends JPanel
{
	private static final long serialVersionUID = -1772877467964008567L;
	
	private BufferedImage bufferedImage;

	MandelbrotPanel(BufferedImage bufferedImage)
	{
		this.bufferedImage = bufferedImage;
		this.setPreferredSize(new Dimension(bufferedImage.getWidth(), bufferedImage.getHeight()));
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(bufferedImage, 0, 0, null);
	}
}
