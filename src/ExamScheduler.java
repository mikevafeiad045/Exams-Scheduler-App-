import java.util.ArrayList;


public class ExamScheduler extends Secretary{
	
	static final int EXAM_ZONES = 6;
	ArrayList<Date> dates = new ArrayList<>();

	
	 public ExamScheduler(int period, int capacityAud, int capacityAmph, int numberOfAud, int numberOfAmph,
			Course[][] availability) {
		super(period, capacityAud, capacityAmph, numberOfAud, numberOfAmph);
	}
	 
	 
	 public Course[] findDate(String day) { //Searching the date and if it isn't in the list dates adding it.
	    	for(Date d: dates) {
	    		if (d.day.equals(day)) {//Date found
	    			return d.getZone();
	    		}
	    	}
	    	//Not found, Adding it to dates
	    	Date selectedDate = new Date(day);
	    	dates.add(selectedDate);
	    	return selectedDate.getZone();
	    }

	public int calcRemainingStudents(int students, int capacity) {
		
		int remainingStudents = students-capacity;
		if(remainingStudents <=0) return 0;
		return remainingStudents;
		 
	}
	
}
