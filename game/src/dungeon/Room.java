package dungeon;

import java.util.ArrayList;
import java.util.List;

public class Room {
	private List<Room> nextRooms = new ArrayList<Room>();
    private Room previousRoom = null;
    private boolean visited = false;
    private String id; 
    
    private int level;
    
    public Room(String id, int level) {
    	this.id = id;
    	this.level = level;
    }
    
    public Room getPreviousRoom() {
    	return this.previousRoom;
    }
    
    public void setPreviousRoom(Room previousRoom) {
    	this.previousRoom = previousRoom;
    }
    
    public List<Room> getNextRooms() {
        return nextRooms;
    }
    
    public void addRoom(String id, int level) {
    	Room nextRoom = new Room(id, level);
    	nextRoom.previousRoom = this;
    	this.nextRooms.add(nextRoom);
    }
    
    public String getId() {return this.id;}
    public void setId(String id) {this.id = id;}
    public int getLevel() {return this.level;}
    public void setLevel(int level) {this.level = level;}
    
    public boolean wasVisited() {return visited;}
    public void setAsVisited() {this.visited = true;}
    	
}
