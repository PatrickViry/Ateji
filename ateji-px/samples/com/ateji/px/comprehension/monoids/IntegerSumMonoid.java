/****************************
 * Copyright (c) 2009 ATEJI *
 * All rights reserved.     *
 ****************************/
package com.ateji.px.comprehension.monoids;

import apx.util.monoid.Monoid;

/**
 * This monoid combines integer values by addition.
 */
public class IntegerSumMonoid extends Monoid<Integer, Integer>
{
	@Override
	public Integer merge(Integer arg0, Integer arg1)
	{
		return arg0 + arg1;
	}

	@Override
	public Integer zero()
	{
		return 0;
	}

	@Override
	public Integer unit(Integer element)
	{
		return element;
	}
	
	@Override
	public Integer add(Integer result, Integer element)
	{
		return result + element;
	}
	
	public Integer addAll(Integer result, Integer other)
	{
		return result + other;
	}

}
