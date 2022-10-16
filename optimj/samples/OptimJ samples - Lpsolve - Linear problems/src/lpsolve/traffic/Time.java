/****************************
 * Copyright (c) 2007 ATEJI *
 * All rights reserved.     *
 ****************************/
package lpsolve.traffic;

/**
 * class Time represents the time of day
 */
public class Time {
	final int hour;
	final int minute;
	
	public Time(final int hour, final int minute) {		
		this.hour = hour;
		this.minute = minute;
	}
	
	public Time(final int date) {						
		this.hour = date / 60;
		this.minute = date - 60 * (date / 60);
	}
	
	public String toString()
	{
		return hour+":"+minute;
	}
	
	// number of minutes from 00:00 to now.
	public int toMinutes()
	{
		return 60 * hour + minute;
	}
	
	
	public static int TWENTY_FOUR_HOURS = 60*24;
}
