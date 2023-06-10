import java.io.Serializable;
import java.util.ArrayList;


public class Course implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private ArrayList<String> professorsList;
	private ArrayList<Room> selectedRoomsList;
	private String courseName;
	private int numberOfStudents;
	
	public Course(String className,int numberOfStudents) {

		this.professorsList = new ArrayList<String>();
		this.selectedRoomsList = new ArrayList<Room>();
		this.courseName = className;
		this.numberOfStudents = numberOfStudents;
	}
	

//Getters
	public ArrayList<String> getProfessorsList() {
		return professorsList;
	}

	public ArrayList<Room> getSelectedRoomsList() {
		return selectedRoomsList;
	}

	public String getCourseName() {
		return courseName;
	}

	public int getNumberOfStudents() {
		return numberOfStudents;
	}
	
//Add objects to array lists methods
	public void addProf(String name) {
		professorsList.add(name);
	}
	
	public void addSelectedRoom(Room R) {
		selectedRoomsList.add(R);
	}


}
