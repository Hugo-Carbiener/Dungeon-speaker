package dungeon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Room {
	
	private List<Room> nextRooms = new ArrayList<Room>();		//List of rooms that are considered "child" of the current room
    private Room previousRoom;									//Room considered "parent"of the current room
    private List<Room> accessibleRooms = new ArrayList<Room>();
    private boolean visited = false;							//determines if the player as already been through this room
    private String id; 											//id of the room. Generated as follows : id  = id of previous room + number of the room in the order of generation(starting at 0). e.g. the third room of room 010 will have id = 0102 
    private int level;											//Number of the floor the room belongs to. Is equal to the number of parents(including starting room)
    
    
    public Room(String id, int level) {
    	this.id = id;
    	this.level = level;
    	this.previousRoom = this;
    	
    }
    
    
    public List<Room> getNextRooms() {return nextRooms;}
    public Room getPreviousRoom() {return this.previousRoom;}
    public void setPreviousRoom(Room previousRoom) {this.previousRoom = previousRoom;}
    public List<Room> getAccessibleRooms(){return this.accessibleRooms;}
    public String getId() {return this.id;}
    public int getLevel() {return this.level;}
    public boolean wasVisited() {return visited;}
    public void setAsVisited() {this.visited = true;}
    
    public List<Room> getRoomFamily() {// returns a list of the rooms coming from the same previous room, i.e. the family of rooms this is part of
    	Room prev = this.previousRoom;
    	if (prev == null) {
    		List<Room> temp = new ArrayList<Room>();
    		temp.add(this);
    		return temp;
    	}
    	else {
    	return prev.nextRooms;
    	}
    }
    
    public void addRoom(String id, int level) {
    	Room nextRoom = new Room(id, level);
    	nextRoom.previousRoom = this;
    	nextRoom.accessibleRooms.add(nextRoom.previousRoom);	//Add the previous room as an accessible room
    	this.accessibleRooms.add(nextRoom);						//Add the new room as an accessible room to the previous room
    	this.nextRooms.add(nextRoom);							//Add the new room to the list of children of the previous room
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
}
