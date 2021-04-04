package dungeon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javafx.util.Pair;
import player.Item;
import player.Monster;
import player.Weapon;

public class Room {
	
	private List<Room> nextRooms = new ArrayList<Room>();		//List of rooms that are considered "child" of the current room
    private Room previousRoom;									//Room considered "parent"of the current room
    private List<Room> accessibleRooms = new ArrayList<Room>();	//List of rooms the player can go to 
    private boolean visited = false;							//determines if the player as already been through this room
    private String id; 											//id of the room. Generated as follows : id  = id of previous room + number of the room in the order of generation(starting at 0). e.g. the third room of room 010 will have id = 0102 
    private int level;											//Number of the floor the room belongs to. Is equal to the number of parents(including starting room)
    
    private List<Item> items = new ArrayList<>();						//List of items in the room
    private List<Monster> monsters = new ArrayList<>();					//List of monsters guarding the room
    
    public Room(String id, int level, double fillProbability) {
    	this.id = id;
    	this.level = level;
    	this.previousRoom = this;
    	this.fill(fillProbability);
    }
    
    public List<Room> getNextRooms() {return nextRooms;}
    public Room getPreviousRoom() {return this.previousRoom;}
    public void setPreviousRoom(Room previousRoom) {this.previousRoom = previousRoom;}
    public List<Room> getAccessibleRooms(){return this.accessibleRooms;}
    public String getId() {return this.id;}
    public int getLevel() {return this.level;}
    public boolean wasVisited() {return visited;}
    public void setAsVisited() {this.visited = true;}
    public List<Item> getItems() {return this.items;}
    public List<Monster> getMonsters() {return this.monsters;}
    
    public List<Room> getRoomFamily() {
    	// returns a list of the rooms coming from the same previous room, i.e. the family of rooms this is part of
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
    
    public void addRoom(String id, int level, double fillProbability) {
    	//Add a room. It is considered a child of this 
    	Room nextRoom = new Room(id, level, fillProbability);
    	nextRoom.previousRoom = this;
    	nextRoom.accessibleRooms.add(nextRoom.previousRoom);	//Add the previous room as an accessible room
    	this.accessibleRooms.add(nextRoom);						//Add the new room as an accessible room to the previous room
    	this.nextRooms.add(nextRoom);							//Add the new room to the list of children of the previous room
    }
 

	public List<Pair<String, Boolean>> getNeighbor(Map map) {	
    	//Returns a list of 2 pairs (id, boolean) that respectively indicates if room has a left neighbor and a right neighbor. 
    	//id for the right neighbor (second pair) may be nonsense but will not be used afterwards as long as it's boolean is at false
    	
    	
    	List<Pair<String, Boolean>> neighbors = new ArrayList<>();
    	String roomId = this.getId();
    	char curLastChar = roomId.charAt(roomId.length()-1);
    	String leftLastChar;
    	String rightLastChar;
    	String tempId = roomId.substring(0, roomId.length() - 1);
    	String leftId;
    	String rightId;
    	Pair<String, Boolean> leftNeighbor;
    	Pair<String, Boolean> rightNeighbor;
    	
    	leftLastChar = Integer.toString((Character.getNumericValue(curLastChar) + 1));
    	rightLastChar = Integer.toString((Character.getNumericValue(curLastChar) - 1));
    	leftId = tempId + leftLastChar;
    	rightId = tempId + rightLastChar;
    	
    	if (map.getRoom(leftId, map.getStartingRoom()) != null){							//left neighbor exists
    		leftNeighbor = new Pair<>(leftId, true);
    	} else {
    		leftNeighbor = new Pair<>(leftId, false);
    	}
    	
    	if (map.getRoom(rightId, map.getStartingRoom()) != null) {							//right neighbor exists
    		rightNeighbor = new Pair<>(rightId, true);
    	} else { 
    		rightNeighbor = new Pair<>(rightId, false);
    	}
    	
    	neighbors.add(leftNeighbor);
    	neighbors.add(rightNeighbor);
    	return neighbors;
    }
    
    
    public List<Boolean> lastChildIndicator() {
    	//PLEASE IGNORE. Returns a list of booleans telling for each previous room to this whether or not the room is the last child of its level
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
    
    
    public void addNeighborLink(Map map, double probability) {
    	//Check each room to see if it has left and right neighbors. If so it generate a links between the room and its neighbor with a probability 
    	
    	if (probability < 0) {
    		probability = 0;
    	} else if (probability > 1) {
    		probability = 1;
    	}
    	
    	List<Pair<String, Boolean>> neighbors = this.getNeighbor(map);
    	Room root = map.getStartingRoom();
    	Room currentNeighbor;
    	String id;
    	
    	for (Pair<String, Boolean> eachNeighbor : neighbors) {
    		id = eachNeighbor.getKey();
  
    		if (eachNeighbor.getValue() == true) {									//check if the neighbor exists
    			currentNeighbor = map.getRoom(id, root);
	    		if (!this.accessibleRooms.contains(currentNeighbor)) {				//check if the neighbor is already somehow accessible
	    			double r = Math.random();
	    			if (r <= probability) {											//we add the link with a given probability (allows to generate more or less opened dungeons)
	    				//Add room
	    				currentNeighbor.accessibleRooms.add(this);
	    				this.accessibleRooms.add(currentNeighbor);
	    			}
	    		}
    		}
    	}
    }
    
    public void fill(double probability) {
    	//Fill the room with a monster and an item
    	Monster monster = Monster.generateMonster();
    	Weapon weapon = new Weapon();
    	if (Math.random() < probability) {
    		this.monsters.add(monster);
    	}
    	if (Math.random() < probability) {
    		this.items.add(weapon);
    	}
    }
}
