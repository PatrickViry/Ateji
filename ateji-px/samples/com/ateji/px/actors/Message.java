/****************************
 * Copyright (c) 2009 ATEJI *
 * All rights reserved.     *
 ****************************/
package com.ateji.px.actors;

import java.io.Serializable;

/**
 * The actor model is about the semantics of message.
 * 
 * In our implementation, a message is composed of two elements:
 *   - The name of the action an agent must perform when it receives this message,
 *   - The arguments with which the action must be run.
 */
public class Message implements Serializable {

	private final String actionName;
	private final Object[] arguments;
	
	public Object[] getArguments(){ return arguments; }
	public String getActionName(){ return actionName; }
	
	public static final Message stopMessage = StopMessage.stopMessage;

	public Message(String actionName, Object ... arguments) {
		super();
		this.actionName = actionName;
		this.arguments = arguments;
	}
	
	private static final long serialVersionUID = -5543462627348205436L;
}

class StopMessage extends Message {
		

	private StopMessage() {
		super("__STOP__");
	}
	
	@Override
	public boolean equals(Object o){
		if (o instanceof StopMessage) return true;
		return false;
	}
	
	public static StopMessage stopMessage = new StopMessage();
	
	private static final long serialVersionUID = 6195345023324701892L;
}