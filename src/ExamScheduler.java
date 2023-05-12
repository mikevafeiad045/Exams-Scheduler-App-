
public class ExamScheduler extends Secretary{
	
	private Course[][]availability;

	
	 public ExamScheduler(int period, int capacityAud, int capacityAmph, int numberOfAud, int numberOfAmph,
			Course[][] availability) {
		super(period, capacityAud, capacityAmph, numberOfAud, numberOfAmph);
		this.availability = availability;
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
	
	
	
	
}
