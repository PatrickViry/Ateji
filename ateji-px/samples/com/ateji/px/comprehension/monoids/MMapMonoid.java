/****************************
 * Copyright (c) 2009 ATEJI *
 * All rights reserved.     *
 ****************************/
package com.ateji.px.comprehension.monoids;

import apx.util.monoid.Monoid;

import com.ateji.px.comprehension.util.MultiMap;
import com.ateji.px.comprehension.util.Tuple;

/**
 * MMapMonoid is the monoid used to compose MultiMap's. It takes as argument another monoid
 * used to compose values inside multimaps.
 * K is the type of the keys in the multimap
 * E is the type of value "elements" in the multimap
 * R is the type of value "results" (combinations) in the multimap
 */
public class MMapMonoid<K, E, R> extends Monoid<MultiMap<K, E, R>, Tuple<K, E>>
{
	private final Monoid<R, E> m;

	public MMapMonoid(Monoid<R, E> m)
	{
		this.m = m;
	}
	
	/**
	 * Merge returns a binary function used to compose elements. The function *must* be associative.
	 */
	@Override
	public MultiMap<K, E, R> merge(MultiMap<K, E, R> arg0, MultiMap<K, E, R> arg1)
	{
		MultiMap<K, E, R> result = new MultiMap<K, E, R>();
		result.addAll(arg0, m);
		result.addAll(arg1, m);
		return result;
	}


	/**
	 * Unit returns the unit element of merge.
	 */
	@Override
	public MultiMap<K, E, R> zero()
	{
		return new MultiMap<K, E, R>();
	}

	/**
	 * Singleton ...
	 * In the case of a primitive monoid, singleton is the identity.
	 */
	@Override
	public MultiMap<K, E, R> unit(Tuple<K, E> element)
	{
		MultiMap<K, E, R> result = new MultiMap<K, E, R>();
		result.add(element.getE1(), element.getE2(), m);
		return result;
	}
	
//	/**
//	 * Add is the imperative incremental version of merge :
//	 *   add(r, e);
//	 * is the same as
//	 *   r = merge(r, singleton(e));
//	 * Add is meant to be subclassed for performance purposes.
//	 */
//	@Override
//	public void add(MultiMap<K, E, R> result, (:K,E:) element)
//	{
//		result = (merge(result, singleton(element)));
//	}
}
