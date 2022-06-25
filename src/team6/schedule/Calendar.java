package team6.schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import team6.Main;
import team6.timetable.Assignment;
import team6.timetable.Day;
import team6.timetable.Subject;
import team6.timetable.Time;
import team6.timetable.Timetable;

public class Calendar {
	
	public static List<Calendar> cals = new ArrayList<Calendar>();

	private int year, month;
	private List<Schedule>[] schedules;
	private List<Schedule>[] others;
	
	private Calendar(int year, int month) {
		this.year = year;
		this.month = month;
		schedules = new ArrayList[31];
		for(int i = 0; i < 31; i ++)
			schedules[i] = new ArrayList<Schedule>();
		others = new ArrayList[31];
		for(int i = 0; i < 31; i ++)
			others[i] = new ArrayList<Schedule>();
	}
	
	public static Calendar getInstance(int year, int month) {
		for(Calendar c : cals) 
			if(c.getYear() == year && c.getMonth() == month)
				return c;
		Calendar c = new Calendar(year, month);
		cals.add(c);
		return c;
	}
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public List<Schedule>[] getSchedules() {
		return schedules;
	}
	
	public List<Schedule>[] getOthers(){
		return others;
	}

	public void setSchedules(List<Schedule>[] schedules) {
		this.schedules = schedules;
	}
	
	public void setOhters(List<Schedule>[] others) {
		this.others = others;
	}
	
	public List<Schedule> getSchedules(int date){
		return schedules[date-1];
	}
	
	public List<Schedule> getOthers(int date){
		return others[date-1];
	}
	
	public void addSchedule(int date, Schedule schedule) {
		schedules[date-1].add(schedule);
	}
	
	public void addOther(int date, Schedule schedule) {
		others[date-1].add(schedule);
	}
	
	public void removeTableSchedule() {
		schedules = new ArrayList[31];
		for(int i = 0; i < 31; i ++)
			schedules[i] = new ArrayList<Schedule>();
	}
	
	public static void reschedulingTimetable(int year, int month) {
		int semester = month <= 6 ? 1 : 2;
		Timetable table = Main.getTimetable(year, semester);
		Calendar cal = getInstance(year, month);
		cal.removeTableSchedule();
		java.util.Calendar calendar = java.util.Calendar.getInstance();
		calendar.set(java.util.Calendar.YEAR, year);
		calendar.set(java.util.Calendar.MONTH, month-1);
		for(int i = 0; i < cal.schedules.length; i ++) {
			final int date = i+1;
			calendar.set(java.util.Calendar.DATE, i+1);
			Day day = Day.getDayfromCalendarDay(calendar.get(java.util.Calendar.DAY_OF_WEEK));
			List<Subject> today = table.getSubjectByDay(day);
			today = today.stream()
					.filter(subject -> !subject.isRest(year, month, date))
					.collect(Collectors.toList());
			for(Subject subject : today) {
				Time time = subject.getTodayTime(year, month, date);
				if(time == null) continue;
				Schedule schedule = new Schedule(subject.getName(), subject.getLocation(), time, 0);
				cal.schedules[i].add(schedule);
			}
		}
		for(Subject subject : table.getSubjects()) {
			for(Assignment assign : subject.getAssign()) {
				Schedule schedule = new Schedule(subject.getName() + " 과제", "", assign.getTime(), 1);
				cal.schedules[assign.getTime().getStart_date()-1].add(schedule);
			}
			for(Assignment assign : subject.getExam()) {
				Schedule schedule = new Schedule(subject.getName() + " " + assign.getName(), "", assign.getTime(), 2);
				cal.schedules[assign.getTime().getStart_date()-1].add(schedule);
			}
		}
	}

}
