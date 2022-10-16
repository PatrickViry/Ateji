/****************************
 * Copyright (c) 2009 ATEJI *
 * All rights reserved.     *
 ****************************/
package com.ateji.px.comprehension.util;

import apx.util.monoid.Monoid;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

/**
 * A MultiMap is a map associating keys to values, where a given Monoid is used
 * to *compose* the values (as opposed to a simple Map where new values replace
 * old ones). - K is the type of the keys - E is the type of the values - R is
 * the type of the composition of values If the monoid is primitive, say
 * addition over integers, E and R are the same. Otherwise, the monoid is
 * typically a collection, say a set, and R is Set<E>.
 */

public class MultiMap<K, E, R> implements Serializable {

	private static final long serialVersionUID = -2823983460177898585L;

	private final HashMap<K, R> map = new HashMap<K, R>();

	/**
	 * Add a new (key, value) pair, using monoid m to combine value with
	 * previous values.
	 */
	public void add(K key, E value, Monoid<R, E> m) {
		R values = map.get(key);
		if (values == null) {
			map.put(key, m.unit(value));
		} else {
			map.put(key, m.merge(values, m.unit(value)));
		}
	}

	/**
	 * Add all (key, value) pairs from other multimap, using monoid m to combine
	 * values with previous values.
	 */
	public void addAll(MultiMap<K, E, R> other, Monoid<R, E> m) {
		for (K key : other.map.keySet()) {
			{
				R otherValues = other.map.get(key);
				R values = map.get(key);
				if (values == null) {
					map.put(key, otherValues);
				} else {
					map.put(key, m.merge(values, otherValues));
				}
			}
		}
	}

	/**
	 * Return the set of keys in this multimap.
	 */
	public R get(K key) {
		return map.get(key);
	}

	/**
	 * Return the composition of values associated to a given key.
	 */
	public Set<K> keySet() {
		return map.keySet();
	}
}
