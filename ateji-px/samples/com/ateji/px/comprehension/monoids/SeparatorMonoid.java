/****************************
 * Copyright (c) 2009 ATEJI *
 * All rights reserved.     *
 ****************************/
package com.ateji.px.comprehension.monoids;

import apx.util.monoid.*;

/**
 * The purpose of this example to show how to define new monoids.
 * 
 * In the Basics example, the predefined monoids and those from the library have been 
 * introduced. This example shows how to define an arbitrary monoid.
 * 
 * The new monoid is used to display a comma separated list of strings using a comprehension.
 * The same technique can be used wherever we have to do some treatment for all iterations in
 * a loop except the first or the last one.
 */
public class SeparatorMonoid  extends Monoid<String, String> {

	// The separator is user-defined.
	private final String separator;
	public SeparatorMonoid(String separator) {
		this.separator = separator;
	}

	// We carefully avoid to use "" as the zero element, because
	// we want to be able to differentiate the empty String as the
	// zero element and the empty String as one of the values to
	// merge.
	private final String zero = new String();
	@Override
	public String zero() { return zero; }

	// For this monoid, the unit method is the identity.
	@Override
	public String unit(String s) { return s; }

	// The merge method insert a separator except when one of the 
	// arguments is the zero element.
	@Override
	public String merge(String x, String y) {
		if (x == zero) return y;
		if (y == zero) return x;
		return x + separator + y;
	}


	public static void main(String[] args)
	{
		// 	You can then use your own monoid in a comprehension.
		SeparatorMonoid contact = new SeparatorMonoid(",");
		com . ateji . px . comprehension . monoids . SeparatorMonoid monoid35 = contact ; java . lang . String toBeReturned27 = monoid35 . zero ( ) ; for ( String s : data ) {
toBeReturned27 = monoid35 . add ( toBeReturned27 , s ) ; }
System.out.println(toBeReturned27 );
	}

	public static String[] data;

static {
final java . lang . String [ ] freshVariable9 =new java . lang . String [1000];{int freshVariable10 =0;for(int i : new apx . util . RangeInteger (0 , ((1000)- 1 ))){freshVariable9 [freshVariable10 ]=""+i;freshVariable10 ++;}}data =freshVariable9 ; }
}
