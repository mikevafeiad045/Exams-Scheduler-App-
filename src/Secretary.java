import java.util.ArrayList;

public class Secretary {
	protected ArrayList<Course> courseList;
	protected ArrayList<Room> roomList;
	protected int period;
	protected int capacityAud;
	protected int capacityAmph;
	protected int numberOfAud;
	protected int numberOfAmph;
	
	
	public Secretary(int period, int capacityAud,int capacityAmph, int numberOfAud, int numberOfAmph) {
	
		this.courseList = new ArrayList<Course>();
		this.roomList = new ArrayList<Room>();
		this.period = period;
		this.capacityAud = capacityAud;
		this.capacityAmph = capacityAmph;
		this.numberOfAud = numberOfAud;
		this.numberOfAmph = numberOfAmph;
	}


	public ArrayList<Course> getCourseList() {
		return courseList;
	}


	public ArrayList<Room> getRoomList() {
		return roomList;
	}


	public int getPeriod() {
		return period;
	}


	public int getCapacityAud() {
		return capacityAud;
	}


	public int getCapacityAmph() {
		return capacityAmph;
	}


	public int getNumberOfAud() {
		return numberOfAud;
	}


	public int getNumberOfAmph() {
		return numberOfAmph;
	}
	
	
	
	
	
}
