package dungeon;

import java.util.List;

public class Map {
	private Room startingRoom = new Room("0", 0);		//map.startingRoom is the root node 
	private int endLevel; 								//determines how many "floors" the dungeon will have
	private int maxExitNumber; 							//determines how many exits a room can have. 
	private int roomAmount;								//tells how many rooms the dungeon contains, starting room included.
	
	
	public Map(int endLevel,int maxExitNumber) { 
		//this constructor generates a map as well as a basic tree architecture displayable using displayFromRoom(map.getStartingRoom())
		this.endLevel = endLevel;
		this.maxExitNumber = maxExitNumber;
		this.roomAmount = 1;
		
		//GENERATION OF THE TREE
		generateBasicTree(0, this.startingRoom);
	}
	
	
	public Room getStartingRoom() {return this.startingRoom;}
	public int getEndLevel() {return this.endLevel;}
	public int getMaxExitNumber() {return this.maxExitNumber;}
	public int getRoomNumber() {return this.roomAmount;}
	
	public void generateBasicTree(int level, Room root) {
		if (level < this.endLevel) {
			int nbOfRoom = (int) (Math.random() * this.maxExitNumber);
			for (int i = 0; i < nbOfRoom; i++) { 					//generate the x rooms, children of root
				String newId = root.getId() + String.valueOf(i);	//generate the id of the room about to be created
				//System.out.println(newId);
				
				root.addRoom(newId, level + 1);
				this.roomAmount +=1;
			}
			//System.out.println("Room generated");
			for (Room each : root.getNextRooms()) { 	//generate basic trees for each child
				generateBasicTree(level + 1, each);
			}
		}
	}
	
	
	public void displayFromRoom(Room room) {
		//Displays the map of the dungeon like a tree. It starts from the room passed as argument. 
		char bottomCorner = '\u2559';
		char crossSection = '\u255F';
		char verticalLine = '\u2551';
		char horizontalLine = '\u2500';
		
		if (room == this.getStartingRoom()) {System.out.println("0");}
		for (Room each : room.getNextRooms()) {
			
			//NEW VERSION
			//--------------------------------------------------
			List<Boolean> indicatorList = each.lastChildIndicator();
			for (int i = 0; i < indicatorList.size(); i++) {
				Boolean indicator = indicatorList.get(i);
				if(i == indicatorList.size()-1) {		//Last character
					if (indicator) {
						System.out.print(" ");
						System.out.print(bottomCorner);
						System.out.print(horizontalLine);
					}
					else {
						System.out.print(" ");
						System.out.print(crossSection);
						System.out.print(horizontalLine);
					}
				}
				else {									//previous characters
					if (indicator) {
						System.out.print(" ");
						System.out.print("  ");
					}
					else {
						System.out.print(" ");
						System.out.print(verticalLine);
						System.out.print(" "); 
					}
				}
			}
			//--------------------------------------------------
			
			
			//OLD VERSION 
			//--------------------------------------------------
			/*int level = each.getLevel();
			for (int i = 0; i < level - 1; i++) {
				System.out.print(verticalLine);
				System.out.print(" "); 
			}
			if (room.getNextRooms().indexOf(each) == room.getNextRooms().size()-1) {
				System.out.print(bottomCorner);
				System.out.print(horizontalLine);
			}
			else {
			System.out.print(crossSection);
			System.out.print(horizontalLine);
			}*/
			//--------------------------------------------------
			
			System.out.println(each.getId());		
			displayFromRoom(each);
		}
	}	
	
	
	public Room getRoom(String id, Room root) { //returns the room that is defined by id. root must be set at startingRoom
		if (id.equals(root.getId())) {
			return root;
		} else {
			for (Room each : root.getNextRooms()) {
				Room foundRoom = getRoom(id, each);
				if (foundRoom != null) {
					//System.out.println("found");
					return foundRoom;
				}
			}
		return null;
		}
	}
	
	
	public void addNeighborLink(Room root, double probability) {
		for (Room each : root.getNextRooms()) { 
			each.addNeighborLink(this, probability);
			this.addNeighborLink(each, probability);
		}
	}
	
	
}
