/****************************
 * Copyright (c) 2009 ATEJI *
 * All rights reserved.     *
 ****************************/
package com.ateji.px.actors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import apx.lang.IChan;
/**
 * DownloadActor downloads pages from the world wide web
 *
 */
public class DownloadActor extends Actor {

	private StringBuilder builder;
	private final IndexActor indexActor;

	/**
	 * The download action takes a URL and produces a String with the contents of web page.
	 * This String is send to an IndexActor.
	 * 
	 * @param url
	 */
	@SuppressWarnings("unused")
	private void act_download(String url) {		

		URL myUrl;
		try {
			myUrl = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return;
		}
		BufferedReader in = null;
		try {
			try {
				in = new BufferedReader(
						new InputStreamReader(
								myUrl.openStream()));
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}

			String inputLine;
			builder = new StringBuilder();

			try {
				while ((inputLine = in.readLine()) != null)
					builder.append(inputLine);
			} catch (IOException e) {
				e.printStackTrace();
				return ;
			}
		}
		finally{
			send(indexActor, new Message("index", url, builder.toString()));
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {					
					e.printStackTrace();
				}		
		}


	}

	@Override
	public void terminate(){
		send(indexActor, Message.stopMessage);
		super.terminate();
	}
	
	
	public DownloadActor(IChan<Message> inbox, IndexActor indexActor) {
		super(inbox);	
		this.indexActor = indexActor;		
	}

}
