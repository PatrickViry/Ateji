/****************************
 * Copyright (c) 2009 ATEJI *
 * All rights reserved.     *
 ****************************/
package com.ateji.px.actors;

import com.ateji.px.comprehension.monoids.IntegerSumMonoid;
import com.ateji.px.comprehension.monoids.MMapMonoid;
import com.ateji.px.comprehension.util.IterableScanner;
import com.ateji.px.comprehension.util.MultiMap;
import com.ateji.px.comprehension.util.Tuple;

import apx.lang.IChan;

/**
 * 
 * IndexActor indexes String into a multimap.
 * 
 */
public class IndexActor extends Actor {

	/**
	 * The index action takes an url and its content  and 
	 * send to the WriteAction a MultiMap of terms and counts.
	 */
	@SuppressWarnings("unused")
	private void act_index(String url, String content){
		
		// If you are not familiar with Ateji PX comprehensions, please refer to the 
		// com.ateji.px.comprehension.Basics sample.
		
		// use an IterableScanner to iterate over the words in the sample string
		final Iterable<String> words = new IterableScanner(content);
		
		// define a multimap monoid combining integer values by addition
		final MMapMonoid<String, Integer, Integer> mmap = 
			new MMapMonoid<String, Integer, Integer>(new IntegerSumMonoid());
		
		// use this monoid in a comprehension to sum the number of occurrences of words
		com . ateji . px . comprehension . monoids . MMapMonoid < java . lang . String , java . lang . Integer , java . lang . Integer > monoid38 = mmap ; com . ateji . px . comprehension . util . MultiMap < java . lang . String , java . lang . Integer , java . lang . Integer > toBeReturned30 = monoid38 . zero ( ) ; for ( String word : words ) {
toBeReturned30 = monoid38 . add ( toBeReturned30 , new Tuple<String, Integer>(word.toLowerCase(), 1) ) ; }
MultiMap<String, Integer, Integer> result =
			toBeReturned30 ;
				
		// send the results
		send(databaseActor, new Message("populateDataBase", url, result));
		
	}
	
	@Override
	public void terminate(){
		send(databaseActor, new Message("displayDataBase", 5));
		send(databaseActor, new Message("shutdown"));
		send(databaseActor, Message.stopMessage);
		super.terminate();
	}
	
	private final DatabaseActor databaseActor;
	public IndexActor(IChan<Message> mailbox, DatabaseActor databaseActor) {
		super (mailbox); this.databaseActor = databaseActor;
	}

}
