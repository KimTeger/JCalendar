package team6.timetable;

import java.util.Calendar;
import java.util.Date;

public class Time {
	
	private int start_year, start_month, start_date, start_hour, start_minute;
	private int end_year, end_month, end_date, end_hour, end_minute;
	private Day day;
	
	public Time(int start_hour, int start_minute) {
		this(0, 0, 0, start_hour,start_minute,0,0,0,0,0,null);
	}
	
	public Time(int start_year, int start_month, int start_date) {
		this(start_year, start_month, start_date, 0,0,0,0,0,0,0,null);
	}
	
	public Time(int start_hour, int start_minute, int end_hour, int end_minute) {
		this(0, 0, 0, start_hour,start_minute,0,0,0,end_hour,end_minute,null);
	}
	
	public Time(int start_hour, int start_minute, int end_hour, int end_minute, Day day) {
		this(0, 0, 0, start_hour,start_minute,0,0,0,end_hour,end_minute,day);
	}
	
	public Time(int start_year, int start_month, int start_date, int end_year, int end_month, int end_date) {
		this(start_year, start_month, start_date, 0,0,end_year,end_month,end_date,0,0,null);
	}
	
	public Time(int start_year, int start_month, int start_date, int start_hour, int start_minute) {
		this(start_year, start_month, start_date, start_hour,start_minute,0,0,0,0,0,null);
	}
	
	public Time(int start_year, int start_month, int start_date, int start_hour, int start_minute,
			int end_year, int end_month, int end_date, int end_hour, int end_minute) {
		this(start_year, start_month, start_date, start_hour,start_minute,end_year,end_month,end_date,end_hour,end_minute,null);
	}
	
	public Time(int start_year, int start_month, int start_date, int start_hour, int start_minute,
			int end_year, int end_month, int end_date, int end_hour, int end_minute,
			Day day) {
		this.start_year = start_year;
		this.start_month = start_month;
		this.start_date = start_date;
		this.start_hour = start_hour;
		this.start_minute = start_minute;
		this.end_year = end_year;
		this.end_month = end_month;
		this.end_date = end_date;
		this.end_hour = end_hour;
		this.end_minute = end_minute;
		this.day = day;
	}

	public int getStart_year() {
		return start_year;
	}

	public int getStart_month() {
		return start_month;
	}

	public int getStart_date() {
		return start_date;
	}

	public int getStart_hour() {
		return start_hour;
	}

	public int getStart_minute() {
		return start_minute;
	}

	public int getEnd_year() {
		return end_year;
	}

	public int getEnd_month() {
		return end_month;
	}

	public int getEnd_date() {
		return end_date;
	}

	public int getEnd_hour() {
		return end_hour;
	}

	public int getEnd_minute() {
		return end_minute;
	}

	public Day getDay() {
		return day;
	}
	
	public double getStart() {
		double start = start_date;
		start += start_month * 30;
		start += start_year * 365;
		start += start_hour * (1.0/24.0);
		start += start_minute * (1.0/(24.0*60.0));
		return start;
	}
	
	public double getEnd() {
		double end = end_date;
		end += end_month * 30;
		end += end_year * 365;
		end += end_hour * (1.0/24.0);
		end += end_minute * (1.0/(24.0*60.0));
		return end;
	}
	
	public boolean isStacked(Time time) {
		double start = getStart(), end = getEnd(), t_start = time.getStart(), t_end = time.getEnd();
		if(t_start >= start && t_start <= end) {
			if(time.day != day) return false;
			else return true;
		}
		else if(t_end >= start && t_end <= end) {
			if(time.day != day) return false;
			else return true;
		}
		return false;
	}
	
	public boolean isPast() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		double now = c.get(Calendar.DATE);
		now += (c.get(Calendar.MONTH)+1)*30;
		now += (c.get(Calendar.YEAR)) * 365;
		now += c.get(Calendar.HOUR) * (1.0/24.0);
		now += c.get(Calendar.MINUTE) * (1.0/(24.0*60.0));
		if(getStart() < now && getEnd() < now) return true;
		return false;
	}
	
	@Override
	public String toString() {
		return start_year + ","+start_month+","+start_date+","+start_hour+","+start_minute+","+
				end_year + "," + end_month + "," + end_date + "," + end_hour + "," + end_minute + "," +
				(day == null ? null : day.getDay());
	}
}
