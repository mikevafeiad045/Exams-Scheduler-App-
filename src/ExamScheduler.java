import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExamScheduler extends Secretary{
	
	static final int EXAM_ZONES = 6;
	private ArrayList<ExamDate> dates = new ArrayList<>();

	
	 public ExamScheduler(int period, int capacityAud, int capacityAmph, int numberOfAud, 
			 int numberOfAmph, String startDate, String endDate) {
		 
		super(period, capacityAud, capacityAmph, numberOfAud, numberOfAmph,startDate,endDate);
	}
	 
	 
	 public Course[] findDate(String day) { //Searching the date and if it isn't in the list dates adding it.
	    	for(ExamDate d: dates) {
	    		if (d.day.equals(day)) {//Date found
	    			return d.getZone();
	    		}
	    	}
	    	//Not found, Adding it to dates
	    	ExamDate selectedDate = new ExamDate(day);
	    	dates.add(selectedDate);
	    	return selectedDate.getZone();
	    }

	public int calcRemainingStudents(int students, int capacity) {
		
		int remainingStudents = students-capacity;
		if(remainingStudents <=0) return 0;
		return remainingStudents;
		 
	}
	
	
	public int [] SplitDate(String outputDate) {
		//Converts input date from date chooser to simple date format
		//Returns array of size 3, containing the day, month and year int values
		
		//SimpleDateFormat inputFormat = new SimpleDateFormat ("EEE MMMM dd HH:mm:ss zzzz yyyy");
		//SimpleDateFormat inputFormat = new SimpleDateFormat ("MMM dd, yyyy");
		//SimpleDateFormat outputFormat = new SimpleDateFormat ("dd-MM-yy");
		
		int parts[] = new int[3];
		
		 try {
	            
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
	
	public String ConvertDate(String inputDate) {
		//Converts input date from date chooser to simple date format
		//Returns array of size 3, containing the day, month and year int values
		
		//SimpleDateFormat inputFormat = new SimpleDateFormat ("EEE MMMM dd HH:mm:ss zzzz yyyy");
		SimpleDateFormat inputFormat = new SimpleDateFormat ("MMM dd, yyyy");
		SimpleDateFormat outputFormat = new SimpleDateFormat ("dd-MM-yy");
		
		Date date=null;
		try {
			 date = inputFormat.parse(inputDate);
		}catch(Exception e) {
			e.printStackTrace();
		}
	    
	    String outputDate = outputFormat.format(date);
	    
	    return outputDate; 
    
	}


	public ArrayList<ExamDate> getDates() {
		return dates;
	}
	

}
