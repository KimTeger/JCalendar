package team6.calculator;

import java.util.List;

import team6.timetable.Subject;
import team6.timetable.Timetable;
public class Calculator2 {

	    public int getTotalCredit (List<Subject> Timetable) {
		    int totalCredit = 0;
		    for(Subject subject : Timetable) {
	            totalCredit += subject.getCredit();
		    }
	        return totalCredit;
	    }
    
	    
	    public double getAverageGrade(List<Subject> Timetable) {
	    	double averageGrade=0;
	    	for(Subject subject : Timetable) {
	    		averageGrade += subject.getCredit() * getGrade(subject.getGrade());
	    	}
	    	averageGrade = averageGrade / getTotalCredit(Timetable);
	        return averageGrade;
	    }
	    
	    
	    public double getTotalAverageGrade(List<Timetable> timetables) {
	    	double averageGrade=0;
	    	double gradesum = 0;
	    	double creditsum = 0;
	    	for(Timetable table : timetables) {
	    		for(Subject subject: table.getSubjects()) {
	    			gradesum += subject.getCredit() * getGrade(subject.getGrade());
	    			creditsum += subject.getCredit();
	    		}
	    	}
	    	averageGrade = gradesum / creditsum;
	    	return averageGrade;
	    }
	    
	    public int getAllCredit(List<Timetable> timetables) {
	    	int sum = 0;
	    	for(Timetable table : timetables) {
	    		for(Subject subject : table.getSubjects()) {
	    			sum += subject.getCredit();
	    		}
	    	}
	    	return sum;
	    }
	    
	   
	    public double getGrade(String grade) {
	    	if(grade == null) return 0.0;
	        double gradeInt = 0;
	        if (grade.equals("A+")){
	            gradeInt = 4.5;
	        }else if (grade.equals("A")){
	            gradeInt = 4.0;
	        }
	        else if (grade.equals("B+")){
	            gradeInt = 3.5;
	        }
	        else if (grade.equals("B")){
	            gradeInt = 3.0;
	        }
	        else if (grade.equals("C+")){
	            gradeInt = 2.5;
	        }
	        else if (grade.equals("C")){
	            gradeInt = 2.0;
	        }
	        else if (grade.equals("D+")){
	            gradeInt = 1.5;
	        }
	        else if (grade.equals("D")){
	            gradeInt = 1.0;
	        }
	        else if (grade.equals("F")){
	            gradeInt = 0.0;
	        }
	        
	        return gradeInt;
	    }
	  

	}