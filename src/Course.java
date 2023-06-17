import java.io.Serializable;
import java.util.ArrayList;


/**
 * Class that represents each course object
 * Contains a professors list, a selected rooms list, a course name(title) and the number of enrolled students.
 * 
 * @author Michalis Vafeiadis
 */

public class Course implements Serializable{

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
	

	/**
	 * Method for splitting the input string of multiple professors and adding each one to the professorsList
	 * 
	 * @param profsInput
	 */
	public void addProfs(String profsInput) {
		String[] names = profsInput.split(",");
		
		for(String s : names) {
			s.trim();
			professorsList.add(s);
		}
	}
	
	public void addSelectedRoom(Room R) {
		selectedRoomsList.add(R);
	}


}

	