package team6.timetable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Timetable {

	private int year;
	private int semester;
	private List<Subject> list = new ArrayList<>();
	private Subject[][] table = new Subject[5][10];
	
	public Timetable(int year, int semester) {
		this.year = year;
		this.semester = semester;
	}
	
	public Subject[][] getTable() {
		return table;
	}

	public void setTable(Subject[][] table) {
		this.table = table;
	}

	public int getYear() {
		return year;
	}
	
	public int getSemester() {
		return semester;
	}
	
	public List<Subject> getSubjects(){
		return list;
	}
	
	public boolean addSubject(Subject subject) {
		list.add(subject);
		for(Time time : subject.getTime()) {
			int srow = getStartRow(time);
			int erow = getEndRow(time);
			int day = time.getDay().getDay()-1;
			for(int i = srow; i <= erow; i ++){
				if(table[day][i] != null) {
					return false;
				}
			}
			for(int i = srow; i <= erow; i ++)
				table[day][i] = subject;
		}
		return true;
	}
	
	public void removeSubject(Subject subject) {
		for(int i = 0; i < 5; i ++) {
			for(int j = 0; j < 10; j ++) {
				if(table[i][j] == null) continue;
				if(table[i][j].getName().equals(subject.getName())
						&& table[i][j].getProf().equals(subject.getProf()))
					table[i][j] = null;
			}
		}
		list.remove(subject);
	}
	
	public Subject getSubject(int i, int j) {
		return table[i][j];
	}
	
	
	public Subject getSubject(String name) {
		for(Subject sub : list) {
			if(sub.getName().equalsIgnoreCase(name))
				return sub;
		}
		return null;
	}
	
	private int getStartRow(Time time) {
		int start = time.getStart_hour();
		return start - 9;
	}
	
	private int getEndRow(Time time) {
		int end = time.getEnd_hour();
		return end - 9;
	}
	
	public boolean canAddable(Subject subject) {
		for(Subject sub : list) {
			for(Time time : sub.getTime()) {
				for(Time t : subject.getTime()) {
					if(time.isStacked(t)) {
						System.out.println(sub.getName() + "과 중복입니다.");
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public List<Subject> getSubjectByDay(Day day){
		return list.stream()
				.filter(subject -> subject.isinDay(day))
				.collect(Collectors.toList());
	}
}
