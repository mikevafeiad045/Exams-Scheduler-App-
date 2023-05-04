import java.util.ArrayList;

public class Course {
	private ArrayList<String> professorsList;
	private ArrayList<Room> selectedRoomsList;
	private String className;
	private int numberOfStudents;
	public Course(String className,int numberOfStudents) {

		this.professorsList = new ArrayList<String>();
		this.selectedRoomsList = new ArrayList<Room>();
		this.className = className;
		this.numberOfStudents = numberOfStudents;
	}
	
	

}
