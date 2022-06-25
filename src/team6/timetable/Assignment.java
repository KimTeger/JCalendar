package team6.timetable;

public class Assignment {
	private String name;
	private Time time;
	
	public Assignment() { }
	
	public Assignment(String name, Time time) {
		this.name = name;
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}
}
