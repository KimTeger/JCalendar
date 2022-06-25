package team6.schedule;

import team6.timetable.Time;

public class Schedule {

	private Time time;
	private int type;
	private String name;
	private String description;
	
	public Schedule(String name, String description, Time time, int type) {
		this.name = name;
		this.description = description;
		this.time = time;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public void setTime(Time time) {
		this.time = time;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	public Time getTime() {
		return time;
	}
	
	public int getType() {
		return type;
	}
	
	
}
