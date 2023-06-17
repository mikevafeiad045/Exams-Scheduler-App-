import java.io.Serializable;

/**
 * Class for the representation of exam dates. Each date, containing a string of dd-MM-yyyy format and an availability array of size 6.
 * Each object gets created dynamically.
 * 
 * @version 17 Jun 2023
 * @author Dimitra Papadopoulou, Michalis Vafeiadis, Eleni Loula
 */
public class ExamDate implements Serializable{

	private static final long serialVersionUID = 3L;

	String day;

	private Course[] zones = new Course[6];
	
	
	/**
	 * Constructs a new ExamDate object.
	 * 
	 * @param d date string
	 */
	public ExamDate(String d) {
		day=d;
	}
	
	/**
	 * Method for adding a course
	 * Sets the c course object to the index position in the zones array
	 * 
	 * @param c course object
	 * @param index zones array index
	 */
	public void addCourse(Course c,int index) { 
		zones[index] = c;
	}
	
	/**
	 * Method for getting the (String)exam start hour (first 2 chars of s) and converting to int
	 * 
	 * @param s selected "##:## - ##:##" string
	 * @return index for the zones array, depending on the hour
	 */
	public int calcRow(String s) { 

		String temp=s.substring(0,2);
		int start = Integer.parseInt(temp,10);

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
	
	
	/**
	 * Method for checking Availability in the day's zones array. If available, c gets inserted in the array at the calculated index
	 * 
	 * @param c course
	 * @param s selected "##:## - ##:##" string
	 * 
	 * @return true or false depending on availability
	 */
	public boolean checkAvailibility(Course c, String s) { 
		int num = calcRow(s);
		if (zones[num]!= null) {
			return false;
		}else addCourse(c,num);
		return true;
	}

	public Course[] getZone() {
		return zones;
	}

	public String getDay() {
		return day;
	}
	
}
