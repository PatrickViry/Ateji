/****************************
 * Copyright (c) 2009 ATEJI *
 * All rights reserved.     *
 ****************************/
package com.ateji.px.comprehension;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.ateji.px.comprehension.monoids.IntegerSumMonoid;
import com.ateji.px.comprehension.monoids.MMapMonoid;
import com.ateji.px.comprehension.util.IterableScanner;
import com.ateji.px.comprehension.util.MultiMap;
import com.ateji.px.comprehension.util.Tuple;

/**
 * If you are not familiar with Ateji PX comprehensions, please refer to the Basics sample.
 * If you are not familiar with monoids, please refer to the UserDefinedMonoid sample.
 * 
 * This example demonstrates the use of a comprehension for counting occurrences 
 * of words.
 * 
 * It uses a new data structure called multimap and a monoid which allows to build them.
 */
public class WordCount
{
	
	public static void main(String[] args)
	{
		final String sampleString = sampleString();
		
		// use an IterableScanner to iterate over the words in the sample string
		final Iterable<String> words = new IterableScanner(sampleString);
		
		// define a multimap monoid combining integer values by addition
		final MMapMonoid<String, Integer, Integer> mmap = 
			new MMapMonoid<String, Integer, Integer>(new IntegerSumMonoid());
		
		// use this monoid in a comprehension to sum the number of occurrences of words
		com . ateji . px . comprehension . monoids . MMapMonoid < java . lang . String , java . lang . Integer , java . lang . Integer > monoid34 = mmap ; com . ateji . px . comprehension . util . MultiMap < java . lang . String , java . lang . Integer , java . lang . Integer > toBeReturned26 = monoid34 . zero ( ) ; for ( String word : words ) {
toBeReturned26 = monoid34 . add ( toBeReturned26 , new Tuple<String, Integer>(word.toLowerCase(), 1) ) ; }
MultiMap<String, Integer, Integer> result =
			toBeReturned26 ;
		
		
		System.out.println("This example parses a text (here a part of the Java 5.0 Collections documentation)");
		System.out.println("and counts the number of words. The displayed result is the list of words that appear");
		System.out.println("at least five times in the text. For example, 'elements  8' indicates that the word 'elements' appears");
		System.out.println("eight times in the text.");
		System.out.println();
		
		// print the results
		for ( String word : result.keySet()) {
{int count = result.get(word); if((count >= 5)) { {
			System.out.println(word + "  " + count);
		}
	} }}
}
	
	// This methods reads the sample text file.
	private static String sampleString() {
		try {
			BufferedReader r = new BufferedReader(new FileReader("src/com/ateji/px/comprehension/sampleText.txt"));
			String result = "";
			String line;
			while ((line=r.readLine()) != null) result += line;
			r.close();
			return result;
		} catch(IOException e) {
			throw new Error(e); // this is just a sample text reader
		}
	}
	
}
