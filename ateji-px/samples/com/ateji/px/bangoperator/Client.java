/****************************
 * Copyright (c) 2009 ATEJI *
 * All rights reserved.     *
 ****************************/
package com.ateji.px.bangoperator;

import apx.lang.Chan;

/**
 * Clients are used to query the web service.
 */
public class Client
{
	
	private final double euros;
	
	public void run()
	{
		Chan<Double> reply = new Chan<Double>();
		// Client sends its query to the web service and then waits for 
		// the answer on its private channel 'reply'.
		MoneyChanger.queryChannel . send (new Query(euros, reply));
		double dollars=reply . receive ();
		System.out.println(euros+" euro(s) = "+dollars+" U.S. dollar(s)");
	}
	
	Client(double euros)
	{
		this.euros = euros;
	}
		
}
