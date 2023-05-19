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
	

//Getters
	public ArrayList<String> getProfessorsList() {
		return professorsList;
	}

	public ArrayList<Room> getSelectedRoomsList() {
		return selectedRoomsList;
	}

	public String getClassName() {
		return className;
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
