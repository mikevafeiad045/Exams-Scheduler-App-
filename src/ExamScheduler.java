
import java.time.LocalDate;
import java.util.Date;
import java.time.DayOfWeek;

import java.text.SimpleDateFormat;

public class ExamScheduler extends Secretary{
	
	private Course[][]availability;
	static final int EXAM_ZONES = 6;

	
	 public ExamScheduler(int period, int capacityAud, int capacityAmph, int numberOfAud, int numberOfAmph,
			Course[][] availability) {
		super(period, capacityAud, capacityAmph, numberOfAud, numberOfAmph);
		this.availability = availability;
	}
	 
	 
	//Calculation of the working days between the first and the last day of the exams
     public int calculateWorkingDays(String start,String end) {
    	 
    	this.setStartDate(start);
    	this.setEndDate(end);
    	 
    	String[] startParts = start.split("/");//Splitting the start date and getting month,year,day
  		String start1 = startParts[0];
  		String start2 = startParts[1];
  		String start3 = startParts[2];
  		
  		int startDay = Integer.parseInt(start1);
  		int startMonth = Integer.parseInt(start2);
  		int startYear = Integer.parseInt(start3);
  		
  		String[] endParts = end.split("/");//Splitting the end date and getting month,year,day
  		String end1 = endParts[0];
  		String end2 = endParts[1];
  		String end3 = endParts[2];
  		
  		int endDay = Integer.parseInt(end1);
  		int endMonth = Integer.parseInt(end2);
  		int endYear = Integer.parseInt(end3);

    	 
        LocalDate startDate = LocalDate.of(startYear, startMonth, startDay); //java.time.LocalDate
        LocalDate endDate = LocalDate.of(endYear, endMonth, endDay); //java.time.LocalDate

            
        int numberOfDays = 0; 

        //Checking and adding the working days
        LocalDate date = startDate;
        while (!date.isAfter(endDate)) {
            if (isWeekday(date)) {
                numberOfDays++;
             }
             date = date.plusDays(1);
        }

        return numberOfDays;
    }

     void createAvailibilityBoard(String start, String end) {
    	 int column = calculateWorkingDays(start, end);
    	  
    	 this.availability = new Course[EXAM_ZONES][column];
     
     }
     

    private boolean isWeekday(LocalDate date) {
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            return dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY;
    }


	public int calcRemainingStudents(int students, int capacity) {
		
		int remainingStudents = students-capacity;
		if(remainingStudents <=0) return 0;
		return remainingStudents;
		 
	}
	
	public boolean checkAvailability(int row, int col) {
		
		if(availability[row][col] instanceof Course) {
			return false;
		}else {
			return true;
		}
		
	}
	
	public int calcRowFromInput(String input) {
		//format: e.g. "09:00 - 11:00"
		//returns: row 0-5
		
		String temp=input.substring(0,2);//getting the first 2 chars of the string (exam start hour)
		int start = Integer.parseInt(temp);//converting String to int
		
		switch(start) {
		case 9:
			return 0;
			
		case 11: 
			return 1;
			
		case 13: 
			return 2;
			
		case 15:
			return 3;
			
		case 17: 
			return 4;
			
		case 19: 
			return 5;
		
		default: 
			return -1;//wrong input
			
		}
		
	}
	
	public int calcColFromInput(String input) {
		//Function that converts selected date from GUI_Prof to a column of the availability array
		//Input format: e.g. "13/01/23"
		
		int col = (this.calculateWorkingDays(this.startDate, input) - 1);//Working days from exam period start date to input date
																		 // (minus 1 because first column == 0))
		
		return col;
	}
	
	public void addToAvailabilityBoard(Course c,int row, int col) {
		
		availability[row][col] = c;
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
