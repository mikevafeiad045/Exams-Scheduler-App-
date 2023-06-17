import java.io.Serializable;
/**
 * Room class representing each room object that gets created
 * 
 * @version 17 Jun 2023
 * @author Michalis Vafeiadis
 */
public class Room implements Serializable{

	private static final long serialVersionUID = 5L;
	private String roomName; //room code name, either AMP### or AUD###
	private int type; //0 for auditorium, 1 for amphitheater
	

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
