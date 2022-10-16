package com.ateji.px.selection;


import apx.lang.ApxClassNotFoundException;
import apx.lang.ApxIOException;
import apx.lang.ApxInterruptedException;
import apx.lang.Serializer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * Most channel constructors can take an argument of type Serializer or Cloner 
 * that specifies the channel behaviour regarding copying and sharing (see the ShareOrCopy sample).
 * 
 * This instance of Serializer reads a string from the keyboard. The enter key terminate a word.
 */
public class StringSerializer extends Serializer<String, InputStream, OutputStream>
{

	BufferedReader r;
	
	@Override
	public void initialize(InputStream is, OutputStream os) throws ApxIOException
	{
		this.r = new BufferedReader(new InputStreamReader(is));
	}

	@Override
	@SuppressWarnings(value = "unchecked")
	public String readObject() throws ApxIOException, ApxClassNotFoundException
	{
		try {
			return r.readLine();
		} catch (IOException e) {
			throw new ApxIOException(e);
		}
	}

	@Override
	public void writeObject(String object) throws ApxIOException, ApxInterruptedException
	{
		throw new UnsupportedOperationException();
	}
}