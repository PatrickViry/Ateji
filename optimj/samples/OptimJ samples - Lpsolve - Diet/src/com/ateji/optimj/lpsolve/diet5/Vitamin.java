/****************************
 * Copyright (c) 2007 ATEJI *
 * All rights reserved.     *
 ****************************/
package com.ateji.optimj.lpsolve.diet5;

interface Vitamin {

	String name();
	
	/**
	 * The returned value is never negative.
	 */
	float minAmount();

	/**
	 * The returned value is greater than the value returned by the
	 * <code>minAmount</code> method.
	 */
	float maxAmount();	

}
