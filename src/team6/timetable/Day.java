package team6.timetable;

public enum Day {
	MON(1),
	THU(2),
	WED(3),
	THR(4),
	FRI(5),
	SAT(6),
	SUN(7);
	
	int num;
	
	Day(int num){
		this.num = num;
	}
	
	public int getDay() {
		return num;
	}
	
	public String getName() {
		switch(this) {
		case MON: return "월";
		case THU: return "화";
		case WED: return "수";
		case THR: return "목";
		case FRI: return "금";
		case SAT: return "토";
		case SUN: return "일";
		}
		return null;
	}
	
	public static Day getDay(String name) {
		switch(name) {
		case "월": return MON;
		case "화": return THU;
		case "수": return WED;
		case "목": return THR;
		case "금": return FRI;
		case "토": return SAT;
		case "일": return SUN;
		}
		return null;
	}
	
	public static Day getDayfromCalendarDay(int day) {
		switch(day) {
		case 1:
			return SUN;
		case 2:
			return MON;
		case 3:
			return THU;
		case 4:
			return WED;
		case 5:
			return THR;
		case 6:
			return FRI;
		case 7:
			return SAT;
		default:
			return null;
		}
	}
	
	public static Day getDay(int num) {
		for(Day day : Day.values()) {
			if(day.getDay() == num)
				return day;
		}
		return null;
	}
}
