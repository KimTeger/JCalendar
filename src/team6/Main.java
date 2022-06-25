package team6;

import java.awt.Color;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import team6.gui.MainFrame;
import team6.schedule.Schedule;
import team6.timetable.Assignment;
import team6.timetable.Day;
import team6.timetable.Subject;
import team6.timetable.Time;
import team6.timetable.Timetable;

public class Main {
	
	public static List<Timetable> tables = new ArrayList<>();

	public static String[] YEARS = new String[6];
	private static final File timetables = new File("timetables.json");
	private static final File schedules = new File("schedules.json");
	
	public static void main(String[] args) throws IOException {
		loadTimetables();
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		
		for(int i = 0; i < 6; i ++)
			YEARS[i] = Integer.toString(c.get(Calendar.YEAR) - i);
		
		MainFrame frame = new MainFrame();
		frame.setVisible(true);
	}
	
	public static Timetable getTimetable(int year, int semester) {
		for(Timetable table : tables) 
			if(table.getYear() == year && table.getSemester() == semester) {
				return table;
			}
		Timetable table = new Timetable(year, semester);
		tables.add(table);
		return table;
	}
	
	public static void saveSchedules() throws IOException {
		JSONObject result = new JSONObject();
		for(team6.schedule.Calendar c : team6.schedule.Calendar.cals) {
			String name = c.getYear() + "-"+c.getMonth();
			JSONObject calendarobj = new JSONObject();
			for(int i = 0; i < 31; i ++) {
				JSONArray otherschedules = new JSONArray();
				for(Schedule schedule : c.getOthers()[i]) {
					JSONObject obj = new JSONObject();
					obj.put("name", schedule.getName());
					obj.put("description", schedule.getDescription());
					obj.put("type", schedule.getType());
					obj.put("time", schedule.getTime().toString());
					otherschedules.add(obj);
				}
				calendarobj.put(i, otherschedules);
			}
			result.put(name, calendarobj);
		}
		if(!schedules.exists()) schedules.createNewFile();
		FileWriter file = new FileWriter(schedules); 
		file.write(result.toJSONString()); 
		file.flush(); 
		file.close();
	}
	
	public static void loadSchedules() throws IOException {
		if(!schedules.exists()) schedules.createNewFile();
		try {
			JSONObject read = (JSONObject) new JSONParser().parse(new FileReader(schedules));
			for(Object keyo : read.keySet()) {
				String key = keyo.toString();
				int year = Integer.parseInt(key.split("-")[0]);
				int month = Integer.parseInt(key.split("-")[1]);
				team6.schedule.Calendar cal = team6.schedule.Calendar.getInstance(year, month);
				JSONObject calendarobj = (JSONObject) read.get(key);
				for(int i = 0; i < 31; i ++) {
					JSONArray otherschedules = (JSONArray) calendarobj.get(i);
					for(Object robj : otherschedules) {
						JSONObject obj = (JSONObject) robj;
						String name = obj.get("name").toString();
						String description = obj.get("description").toString();
						int type = Integer.parseInt(obj.get("type").toString());
						String[] timedata = obj.get("time").toString().split(",");
						Time time = new Time(
								Integer.parseInt(timedata[0]),
								Integer.parseInt(timedata[1]),
								Integer.parseInt(timedata[2]),
								Integer.parseInt(timedata[3]),
								Integer.parseInt(timedata[4]),
								Integer.parseInt(timedata[5]),
								Integer.parseInt(timedata[6]),
								Integer.parseInt(timedata[7]),
								Integer.parseInt(timedata[8]),
								Integer.parseInt(timedata[9]),
								timedata[10].equalsIgnoreCase("null") ? null : Day.getDay(Integer.parseInt(timedata[10])));
						Schedule sch = new Schedule(name, description, time, type);
						cal.addOther(i, sch);
					}
				}
			}
		} catch(Exception e) {
			
		}
	}
	
	public static void saveTimetables() throws IOException {
		JSONObject result = new JSONObject();
		for(Timetable table : Main.tables) {
			JSONArray subjects = new JSONArray();
			for(Subject subject : table.getSubjects()) {
				JSONObject subobj = new JSONObject();
				subobj.put("name", subject.getName());
				subobj.put("prof", subject.getProf());
				subobj.put("location", subject.getLocation());
				subobj.put("color", subject.getColor().getRed() + ","+subject.getColor().getGreen()+","+subject.getColor().getBlue());
				subobj.put("credit",subject.getCredit());
				subobj.put("grade", subject.getGrade());
				JSONArray timearr = new JSONArray();
				for(Time time : subject.getTime())
					timearr.add(time.toString());
				subobj.put("time", timearr);
				
				JSONArray assignarr = new JSONArray();
				for(Assignment assign : subject.getAssign()) {
					JSONObject assignobj = new JSONObject();
					assignobj.put("name", assign.getName());
					assignobj.put("time", assign.getTime().toString());
					assignarr.add(assignobj);
				}
				subobj.put("assigns", assignarr);
				
				JSONArray restarr = new JSONArray();
				for(Assignment assign : subject.getRest()) {
					JSONObject assignobj = new JSONObject();
					assignobj.put("name", assign.getName());
					assignobj.put("time", assign.getTime().toString());
					restarr.add(assignobj);
				}
				subobj.put("rests", restarr);
				
				JSONArray exams = new JSONArray();
				for(Assignment assign : subject.getExam()) {
					JSONObject assignobj = new JSONObject();
					assignobj.put("name", assign.getName());
					assignobj.put("time", assign.getTime().toString());
					exams.add(assignobj);
				}
				subobj.put("exams", exams);
				
				subjects.add(subobj);
			}
			result.put(table.getYear()+"-"+table.getSemester(), subjects);
		}
		if(!timetables.exists()) timetables.createNewFile();
		FileWriter file = new FileWriter(timetables); 
		file.write(result.toJSONString()); 
		file.flush(); 
		file.close();
	}
	
	public static void loadTimetables() throws IOException {
		if(!timetables.exists()) timetables.createNewFile();
		try {
			JSONObject read = (JSONObject) new JSONParser().parse(new FileReader(timetables));
			for(Object keyo : read.keySet()) {
				String key = keyo.toString();
				int year = Integer.parseInt(key.split("-")[0]);
				int semester = Integer.parseInt(key.split("-")[1]);
				JSONArray subjects = (JSONArray) read.get(keyo);
				Timetable table = getTimetable(year, semester);
				for(Object objs : subjects) {
					JSONObject subobj = (JSONObject) objs;
					Subject subject = new Subject(subobj.get("name").toString(),
							subobj.get("prof").toString(),
							subobj.get("location").toString(),
							Integer.parseInt(subobj.get("credit").toString()));
					String[] cdata = subobj.get("color").toString().split(",");
					Color color = new Color(Integer.parseInt(cdata[0]),
							Integer.parseInt(cdata[1]),
							Integer.parseInt(cdata[2]));
					subject.setColor(color);
					JSONArray timearr = (JSONArray) subobj.get("time");
					List<Time> times = new ArrayList<>();
					for(Object obj : timearr) {
						String[] timedata = obj.toString().split(",");
						Time time = new Time(
								Integer.parseInt(timedata[0]),
								Integer.parseInt(timedata[1]),
								Integer.parseInt(timedata[2]),
								Integer.parseInt(timedata[3]),
								Integer.parseInt(timedata[4]),
								Integer.parseInt(timedata[5]),
								Integer.parseInt(timedata[6]),
								Integer.parseInt(timedata[7]),
								Integer.parseInt(timedata[8]),
								Integer.parseInt(timedata[9]),
								timedata[10].equalsIgnoreCase("null") ? null : Day.getDay(Integer.parseInt(timedata[10])));
						times.add(time);
					}
					subject.setTime(times);
					
					JSONArray assignarr = (JSONArray) subobj.get("assigns");
					if(assignarr != null)
					for(Object obj : assignarr) {
						JSONObject assignobj = (JSONObject) obj;
						String name = assignobj.get("name").toString();
						String[] timedata = assignobj.get("time").toString().split(",");
						Time time = new Time(
								Integer.parseInt(timedata[0]),
								Integer.parseInt(timedata[1]),
								Integer.parseInt(timedata[2]),
								Integer.parseInt(timedata[3]),
								Integer.parseInt(timedata[4]),
								Integer.parseInt(timedata[5]),
								Integer.parseInt(timedata[6]),
								Integer.parseInt(timedata[7]),
								Integer.parseInt(timedata[8]),
								Integer.parseInt(timedata[9]),
								timedata[10].equalsIgnoreCase("null") ? null : Day.getDay(Integer.parseInt(timedata[10])));
						Assignment assign = new Assignment(name, time);
						subject.getAssign().add(assign);
					}
					
					JSONArray restarr = (JSONArray) subobj.get("rests");
					if(restarr != null)
					for(Object obj : restarr) {
						JSONObject assignobj = (JSONObject) obj;
						String name = assignobj.get("name").toString();
						String[] timedata = assignobj.get("time").toString().split(",");
						Time time = new Time(
								Integer.parseInt(timedata[0]),
								Integer.parseInt(timedata[1]),
								Integer.parseInt(timedata[2]),
								Integer.parseInt(timedata[3]),
								Integer.parseInt(timedata[4]),
								Integer.parseInt(timedata[5]),
								Integer.parseInt(timedata[6]),
								Integer.parseInt(timedata[7]),
								Integer.parseInt(timedata[8]),
								Integer.parseInt(timedata[9]),
								timedata[10].equalsIgnoreCase("null") ? null : Day.getDay(Integer.parseInt(timedata[10])));
						Assignment assign = new Assignment(name, time);
						subject.getRest().add(assign);
					}
					
					JSONArray examarr = (JSONArray) subobj.get("exams");
					if(examarr != null)
					for(Object obj : examarr) {
						JSONObject assignobj = (JSONObject) obj;
						String name = assignobj.get("name").toString();
						String[] timedata = assignobj.get("time").toString().split(",");
						Time time = new Time(
								Integer.parseInt(timedata[0]),
								Integer.parseInt(timedata[1]),
								Integer.parseInt(timedata[2]),
								Integer.parseInt(timedata[3]),
								Integer.parseInt(timedata[4]),
								Integer.parseInt(timedata[5]),
								Integer.parseInt(timedata[6]),
								Integer.parseInt(timedata[7]),
								Integer.parseInt(timedata[8]),
								Integer.parseInt(timedata[9]),
								timedata[10].equalsIgnoreCase("null") ? null : Day.getDay(Integer.parseInt(timedata[10])));
						Assignment assign = new Assignment(name, time);
						subject.getExam().add(assign);
					}
					
					subject.setGrade(subobj.get("grade").toString());
					table.addSubject(subject);
				}
			}
		} catch(Exception e) { }
	}
}
