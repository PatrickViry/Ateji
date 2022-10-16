package com.ateji.px.comprehension.util;

public class Tuple<T1, T2> {

	private final T1 e1;
	private final T2 e2;
	
	public T1 getE1() {
		return e1;
	}
	public T2 getE2() {
		return e2;
	}
	public Tuple(T1 e1, T2 e2) {
		super();
		this.e1 = e1;
		this.e2 = e2;
	}
	
	
	
}
