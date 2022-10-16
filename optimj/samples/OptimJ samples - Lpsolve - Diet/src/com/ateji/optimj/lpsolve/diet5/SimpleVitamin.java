/****************************
 * Copyright (c) 2007 ATEJI *
 * All rights reserved.     *
 ****************************/
package com.ateji.optimj.lpsolve.diet5;

class SimpleVitamin implements Vitamin {
	public SimpleVitamin(String name, float minAmount, float maxAmount) {
		this.name = name;
		assert minAmount>=0;
		this.minAmount = minAmount;
		assert maxAmount>=minAmount;
		this.maxAmount = maxAmount;
	}
	
	private final String name;
	public String name() { return name; }
	
	private final float minAmount;
	public float minAmount() { return minAmount; }
	
	private final float maxAmount;
	public float maxAmount() { return maxAmount; }
	
	@Override public String toString() {
		return name;
	}

	@Override public boolean equals(Object o) {
		return o instanceof SimpleVitamin
		&& ((SimpleVitamin)o).name.equals(name);
	}
	
	@Override public int hashCode() {
		return name.hashCode();
	}
}
