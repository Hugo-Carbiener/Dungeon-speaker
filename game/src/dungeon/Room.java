package dungeon;

import java.util.ArrayList;
import java.util.List;

public class Room {
	private List<Room> nextRooms = new ArrayList<Room>();
    private Room previousRoom = null;
    private boolean visited = false;
    
    
    public Room getPreviousRoom() {
    	return this.previousRoom;
    }
    
    public void setPreviousRoom(Room previousRoom) {
    	this.previousRoom = previousRoom;
    }
    
    public List<Room> getNextRooms() {
        return nextRooms;
    }
    
    public void addRoom() {
    	Room nextRoom = new Room();
    	nextRoom.previousRoom = this;
    	this.nextRooms.add(nextRoom);
    }
    
    public boolean wasVisited() {
    	return visited;
    }
    
    public void setAsVisited() {
    	this.visited = true;
    }
    
}
