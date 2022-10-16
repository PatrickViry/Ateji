package com.ateji.px.comprehension.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * This class encapsulates a Scanner inside an Iterator in order to
 * use it as the generator of a comprehension.
 */

public class IterableScanner implements Iterable<String>
{
	final Scanner scanner;
	
	public IterableScanner(Scanner scanner)
	{
		this.scanner = scanner;
		scanner.useDelimiter("[^a-zA-Z]+");
	}
	
	public IterableScanner(String s)
	{
		this.scanner = new Scanner(s);
		scanner.useDelimiter("[^a-zA-Z]+");
	}
	
	public IterableScanner(File f) throws FileNotFoundException
	{
		this.scanner = new Scanner(f);
		scanner.useDelimiter("[^a-zA-Z]+");
	}
	
	public Iterator<String> iterator()
	{
		return scanner;
	}
}
