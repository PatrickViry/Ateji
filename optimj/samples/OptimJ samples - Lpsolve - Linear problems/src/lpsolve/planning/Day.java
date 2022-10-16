package lpsolve.planning;

import java.util.ArrayList;
import java.util.List;

public class Day {
	
	private final int position; // monday = 0; sunday = 6;
	
	
	
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + position;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Day other = (Day) obj;
		if (position != other.position)
			return false;
		return true;
	}
	
	public Day(final int position) {
		super();
		this.position = position;
	}
	// cuurent day and three days before.
	List<Day> fourPrecedingDays()
	{
		List<Day> res = new ArrayList<Day>();
		for (int d = 0; d <=3; d++) res.add(new Day((position-d)>=0?position-d:7+position-d));
		return res;
	}
	
	public static Day monday = new Day(0);
	public static Day tuesday = new Day(1);
	public static Day wednesday = new Day(2);
	public static Day thursday = new Day(3);
	public static Day friday = new Day(4);
	public static Day saturday = new Day(5);
	public static Day sunday = new Day(6);
	
	public static List<Day> week = new ArrayList<Day>();
	static{
		week.add(monday);
		week.add(tuesday);
		week.add(wednesday);
		week.add(thursday);
		week.add(friday);
		week.add(saturday);
		week.add(sunday);
	}
}
