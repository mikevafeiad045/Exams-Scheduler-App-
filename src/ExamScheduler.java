
import java.time.LocalDate;
import java.time.Month;
import java.time.DayOfWeek;

public class ExamScheduler extends Secretary{
	
	private Course[][]availability;
	static final int EXAM_ZONES = 6;

	
	 public ExamScheduler(int period, int capacityAud, int capacityAmph, int numberOfAud, int numberOfAmph,
			Course[][] availability) {
		super(period, capacityAud, capacityAmph, numberOfAud, numberOfAmph);
		this.availability = availability;
	}
	//Calculation of the working days between the first and the last day of the exams
     public int calculateWorkingDays() {
    	 
            LocalDate startDate = LocalDate.of(2022, Month.DECEMBER, 23); //java.time.LocalDate
            LocalDate endDate = LocalDate.of(2023, Month.JANUARY, 3); //java.time.LocalDate

            
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

    private boolean isWeekday(LocalDate date) {
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            return dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY;
    }


	public void calcRemainingStudents(Course c) {
		
		
		
		 
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
		
		String[] parts = input.split("/");
		String temp1 = parts[0];
		String temp2 = parts[1];
		
		int day = Integer.parseInt(temp1);
		int month = Integer.parseInt(temp2);
		
		
		
	}
	
	public void addToAvailabilityBoard(Course c,int row, int col) {
		
		availability[row][col] = c;
	}
	
	
	
}
