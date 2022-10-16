/****************************
 * Copyright (c) 2009 ATEJI *
 * All rights reserved.     *
 ****************************/
package com.ateji.px.multiagentsystem;

import apx.lang.IChan;
import apx.lang.Signal;

/**
 * An agent is a program that can:
 *  - perceive its environment through sensors, and
 *  - make action on this environment through actuators.
 *  
 * In our example, an agent can collect two types of information: 
 *  1. A new bid was made. In this case, the bid amount is provided.
 *  2. It has won the auction.
 *
 * In our example, an agent may act on its environment in two ways:
 *  1. by offering a new bid, and
 *  2. by quitting the auction.
 *  
 * NB: This part of the example does not use any Ateji PX-specific language
 * feature. It is therefore written in pure Java.
 */
public abstract class Bidder 
{
	
	// sensors: perceive the environment
	public final IChan<Integer> bidSensor;
	public final Signal winAuctionSensor;

	// actuators: act on the environment
	public final IChan<Integer> bidActuator;
	public final Signal quitAuctionActuator;

	public Bidder(String name,
			IChan<Integer> bidSensor, Signal winAuctionSensor,
			IChan<Integer> bidActuator, Signal quitAuctionActuator)
	{
		super();
		this.name = name;
		this.bidSensor = bidSensor;
		this.winAuctionSensor = winAuctionSensor;
		this.quitAuctionActuator = quitAuctionActuator;
		this.bidActuator = bidActuator;
	}

	public abstract void run();
	
	final String name;
	@Override
	public String toString()
	{
		return name;
	}

}
