package dungeon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Room {
	
	private List<Room> nextRooms = new ArrayList<Room>();
    private Room previousRoom;
    private boolean visited = false;
    private String id; 
    
    private int level;
    
    public Room(String id, int level) {
    	this.id = id;
    	this.level = level;
    	this.previousRoom = this;
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
    
    public List<Boolean> lastChildIndicator() {//PLEASE IGNORE. Returns a list of booleans telling for each previous room to this whether or not the room is the last child of its level
    	Room cur = this;
    	Room prev = this.previousRoom;
    	int level = this.getLevel();
    	List<Boolean> indicator = new ArrayList<Boolean>(Arrays.asList(new Boolean[level]));
    	Collections.fill(indicator, Boolean.FALSE);

    	
    	for (int i = level - 1; i >= 0; i--) {
    		List<Room> neighborRooms = prev.nextRooms;
    		
    		if (neighborRooms.indexOf(cur) == neighborRooms.size()-1) { //then it is the last child of the level
    			indicator.set(i, true);
    		}
    		cur = cur.previousRoom;
    		prev = prev.previousRoom;
    	}
    	return indicator;
    }
    
    
    
    public String getId() {return this.id;}
    public void setId(String id) {this.id = id;}
    public int getLevel() {return this.level;}
    public void setLevel(int level) {this.level = level;}
    
    public boolean wasVisited() {return visited;}
    public void setAsVisited() {this.visited = true;}
    	
}
