package team6.timetable;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class Subject {

	private String name;
	private String prof;
	private String location;
	private List<Time> time = new ArrayList<>();
	private List<Assignment> assign = new ArrayList<>(), exam = new ArrayList<>(), rest = new ArrayList<>();
	private Color color;
	private int credit;
	private String grade = "A+";
	
	public Subject() { }
	
	public Subject(String name, String prof, String location, int credit, Time... times) {
		this.name = name;
		this.prof = prof;
		this.location = location;
		this.credit = credit;
		time = Arrays.stream(times).toList();
		Random r = new Random();
		int red = r.nextInt(256),
				green = r.nextInt(256),
				blue = r.nextInt(256);
		color = new Color(red, green, blue);
	}
	
	public void setColor(Color color) {
		this.color = color;
	}

	public String getGrade() {
		return grade;
	}
	
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public Color getColor() {
		return color;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProf() {
		return prof;
	}
	public void setProf(String prof) {
		this.prof = prof;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public List<Time> getTime() {
		return time;
	}
	public void setTime(List<Time> time) {
		this.time = time;
	}
	public void addTime(Time time) {
		this.time.add(time);
	}
	public List<Assignment> getAssign() {
		return assign;
	}
	public void setAssign(List<Assignment> assign) {
		this.assign = assign;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}

	public List<Assignment> getExam() {
		return exam;
	}

	public void setExam(List<Assignment> exam) {
		this.exam = exam;
	}

	public List<Assignment> getRest() {
		return rest;
	}

	public void setRest(List<Assignment> rest) {
		this.rest = rest;
	}
	
	public boolean isinDay(Day day) {
		for(Time t : time)
			 if(t.getDay().equals(day))
				 return true;
		return false;
	}
	
	public boolean isRest(int year, int month, int date) {
		for(Assignment r : rest)
			if(r.getTime().getStart_year() == year
					&& r.getTime().getStart_month() == month
					&& r.getTime().getStart_date() == date)
				return true;
		return false;
	}
	
	public Time getTodayTime(int year, int month, int date) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		cal.set(Calendar.DATE, date);
		Day day = Day.getDayfromCalendarDay(cal.get(Calendar.DAY_OF_WEEK));
		for(Time t : time)
			if(t.getDay().equals(day))
				return t;
		return null;
	}
	
	public Assignment getAssignment(String name) {
		for(Assignment as : assign)
			if(as.getName().equalsIgnoreCase(name))
				return as;
		return null;
	}
	
	public Assignment getExam(String name) {
		for(Assignment as : exam)
			if(as.getName().equalsIgnoreCase(name))
				return as;
		return null;
	}
	public Assignment getRest(String name) {
		for(Assignment as : rest)
			if(as.getName().equalsIgnoreCase(name))
				return as;
		return null;
	}
}
