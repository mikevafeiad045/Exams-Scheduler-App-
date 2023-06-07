import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExamScheduler extends Secretary{
	
	static final int EXAM_ZONES = 6;
	ArrayList<examDate> dates = new ArrayList<>();

	
	 public ExamScheduler(int period, int capacityAud, int capacityAmph, int numberOfAud, int numberOfAmph,
			Course[][] availability) {
		super(period, capacityAud, capacityAmph, numberOfAud, numberOfAmph);
	}
	 
	 
	 public Course[] findDate(String day) { //Searching the date and if it isn't in the list dates adding it.
	    	for(examDate d: dates) {
	    		if (d.day.equals(day)) {//Date found
	    			return d.getZone();
	    		}
	    	}
	    	//Not found, Adding it to dates
	    	examDate selectedDate = new examDate(day);
	    	dates.add(selectedDate);
	    	return selectedDate.getZone();
	    }

	public int calcRemainingStudents(int students, int capacity) {
		
		int remainingStudents = students-capacity;
		if(remainingStudents <=0) return 0;
		return remainingStudents;
		 
	}
	
	public int [] ConvertAndSplitDate(String inputDate) {
		//Converts input date from date chooser to simple date format
		//Returns array of size 3, containing the day, month and year int values
		
		SimpleDateFormat inputFormat = new SimpleDateFormat ("EEE MMMM dd HH:mm:ss zzzz yyyy");
		SimpleDateFormat outputFormat = new SimpleDateFormat ("dd-MM-yy");
		
		int parts[] = new int[3];
		
		 try {
	            Date date = inputFormat.parse(inputDate);
	            String outputDate = outputFormat.format(date);
	            
	            String[] temp = outputDate.split("-");
	            //Convert parts (string) to int
	            parts[0] = Integer.parseInt(temp[0]);
	            parts[1] = Integer.parseInt(temp[1]);
	            parts[2] = Integer.parseInt(temp[2]);
	            
	            
	     } catch (Exception e) {
	            e.printStackTrace();
	            //parts[0] = -1;
	            System.exit(1);
	     }
		 
		 return parts;
		 
	}

}
