import java.io.Serializable;

public class Room implements Serializable{
	private String roomName;
	private int type;//0 for auditorium
					 //1 for amphitheatre
	
	public Room(String roomName, int type) {
		this.roomName = roomName;
		this.type = type;
	}
	
//Getters
	public String getRoomName() {
		return roomName;
	}

	public int getType() {
		return type;
	}
	
	
	
}
