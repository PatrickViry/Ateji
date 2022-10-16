package com.ateji.px.bangoperator;

import apx.lang.Chan;

/**
 * Helper class to pass several values in the same object.
 */
public class Query {
	
	/**
	 * The amount to convert.
	 */
	private final double euros;

	/**
	 * The channel on which to send the answer.
	 */
	private final Chan<Double> replyChan;
	
	public Query(double euros, Chan<Double> replyChan) {
		super();
		this.euros = euros;
		this.replyChan = replyChan;
	}
	public double getEuros() {
		return euros;
	}
	public Chan<Double> getReplyChan() {
		return replyChan;
	}
	
}
