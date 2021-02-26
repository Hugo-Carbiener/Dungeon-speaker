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
			
			
			//DISPLAY ONE LINE WITH THE ID AT THE END
			List<Boolean> indicatorList = each.lastChildIndicator();
			String string = "";
			for (int i = 0; i < indicatorList.size(); i++) {
				Boolean indicator = indicatorList.get(i);
				
				if(i == indicatorList.size()-1) {		//Last character
					if (indicator) {
						string += (" ");
						string += (bottomCorner);
						string += (horizontalLine);
					}
					else {
						string += (" ");
						string += (crossSection);
						string += (horizontalLine);
					}
				}
				else {									//previous characters
					if (indicator) {
						string += (" ");
						string += ("  ");
					}
					else {
						string += (" ");
						string += (verticalLine);
						string += (" "); 
					}
				}
			}
			System.out.print(string.substring(1)); //substring to remove first space
			System.out.println(each.getId());
			
			//--------------------------------------------------
			//DISPLAY NEIGHBOR LINK FOR LAST LEVEL ROOMS 
			//We check if the room under each has a neighbor link with each and if so print a custom string underneath
			
			if (each.getLevel() == this.endLevel) {
				String neighborId = (each.getNeighbor(this).get(0).getKey());
				if (each.getAccessibleRooms().contains(this.getRoom(neighborId, this.startingRoom))) {	
					//check if the neighbor room is an accessible room (if it is, this link was made by Map.addNeighborLink)
					string = string.substring(1, string.length()-2) + verticalLine; //remove first space, remove cross section, add vertical line
					for (int i = 0; i < each.getLevel() + 1; i += 2) {string += " ";}
					string += verticalLine;
					System.out.println(string);
				}
			}
			
			
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
