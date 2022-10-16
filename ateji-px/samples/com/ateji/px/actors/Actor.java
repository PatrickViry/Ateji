/****************************
 * Copyright (c) 2009 ATEJI *
 * All rights reserved.     *
 ****************************/
package com.ateji.px.actors;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import apx.lang.IChan;

/**
 * In computer science, the Actor model is a mathematical model of concurrent computation.
 * See wikipedia entry for more details: http://en.wikipedia.org/wiki/Actor_model
 * 
 * 
 * How to model an actor in Ateji PX
 * --------------------------------
 * An actor has a unique mailbox (channel) on which it receives messages. In response to a message 
 * that it receives an actor performs an local action that is described by a private method.
 * 
 * It operates as follows : it awaits a message, then chooses the action to do based on the message.
 * When action is complete, it expects a new message. A particular message, the stopMessage, stops 
 * this infinite loop.
 * 
 * 
 * How to model an action
 * ----------------------
 * An action is a private method whose name is act_X where X is the name of the action. The arguments of the 
 * methods are sent with the message.
 * 
 * 
 * The key to the design of an actor is : "All its fields and its methods must be private."
 * 
 */
public class Actor {

	private final IChan<Message> mailbox;

	public final void run(){
		while (true) {
			Message message=mailbox . receive ();
			if (message.equals(Message.stopMessage)) {
				terminate();
				return;
			}
			run_act(message);
		}
	}


	
	/**
	 * Run the action according to the received message.
	 * 
	 * In this implementation of actors, the choice of the action is performed 
	 * by an algorithm that uses the reflect API.
	 */
	private void run_act(Message m){
		String actionName = m.getActionName();
		Object[] arguments = m.getArguments();
		try {
			actions.get(actionName)[arguments.length].invoke(this, arguments);
		} catch (IllegalArgumentException e) {			
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

		/**
		 * Work to do when the actor terminate this run.
		 */
		public void terminate() {
			mailbox.close();
		}

		/**
		 * Send a message into the mailbox to the actor.
		 */
		public void send(Actor actor, Message message){
			actor.mailbox . send (message);
		}

		/**
		 * Creates an actor with a given mailbox.
		 * 
		 */
		public Actor(IChan<Message> mailbox){
			this.mailbox = mailbox;
		}
		
		private Map<String, Method[]> actions = new HashMap<String, Method[]>();
		// Search and stores all methods of an actor who are action.
		{
			Class<?> clazz = this.getClass();
			Method[] methods = clazz.getDeclaredMethods();
			// An action method is identified by its name and its of arguments.
			for ( Method method : methods) {
{
				if(method.getName().startsWith("act_")) {
					Class<?>[] argsClazz = method.getParameterTypes();					
					method.setAccessible(true); // otherwise IllegalAccessException will be thrown because the method is private
					String actionName = method.getName().substring("act_".length());
					int argCount = argsClazz.length;
					Method[] otherActions = this.actions.get(actionName);
					if(otherActions==null) {
						otherActions = new Method[argCount+1];
						this.actions.put(actionName, otherActions);
					} else if(otherActions.length<argCount) {
						Method[] newOtherActions = new Method[argCount+1];
						for(int i=0 ; i < otherActions.length ; i++) {
							newOtherActions[i] = otherActions[i]; 
						}
						otherActions = newOtherActions;
						this.actions.put(actionName, otherActions);
					}
					otherActions[argCount] = method;
				}
			}
		}
}

	}
