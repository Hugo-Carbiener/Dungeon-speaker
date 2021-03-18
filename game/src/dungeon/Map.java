package dungeon;

import java.util.List;

public class Map {
	private Room startingRoom = new Room("0", 0);		//map.startingRoom is the root node 
	private Room endingRoom;							//Room to reach to end the game 
	private int endLevel; 								//determines how many "floors" the dungeon will have
	private int maxExitNumber; 							//determines how many exits a room can have. 
	private double neighborLinkProbability;				//determines the probability of generating a link between neighbor rooms 
	private int roomAmount;								//tells how many rooms the dungeon contains, starting room included.
	
	
	
	public Map(int endLevel,int maxExitNumber, double neighborLinkProbability) { 
		//this constructor generates a map as well as a basic tree architecture displayable using displayFromRoom(map.getStartingRoom())
		this.endLevel = endLevel;
		this.maxExitNumber = maxExitNumber;
		this.neighborLinkProbability = neighborLinkProbability;
		this.roomAmount = 1;
		
		//GENERATION OF THE TREE
		this.generateBasicTree(0, this.startingRoom);
		//GENERATION OF NEIGHBOR LINKS
		this.addNeighborLink(this.startingRoom, neighborLinkProbability);
		//SET THE ENDING ROOM
		this.setEndingRoom();
	}
	
	public Room getStartingRoom() {return this.startingRoom;}
	public Room getEndingRoom() {return this.endingRoom;}
	public void setEndingRoom() {}
	public int getEndLevel() {return this.endLevel;}
	public int getMaxExitNumber() {return this.maxExitNumber;}
	public double getNeighborLinkProbability() {return this.neighborLinkProbability;}
	public int getRoomNumber() {return this.roomAmount;}
	
	public void generateBasicTree(int level, Room root) {
		//add x rooms as children of the room passed as root. x is between 0 and maxExitNumber
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
						
						//NON LAST LEVEL NEIGHBOR LINKS____________________________________________________________________
						Room previousRoom = each.getPreviousRoom();
						String previousRoomNeihghborId = previousRoom.getNeighbor(this).get(0).getKey(); //get the id of the first(left) neighbor of the previous room
						if (previousRoom.getAccessibleRooms().contains(this.getRoom(previousRoomNeihghborId, this.startingRoom))) {
							string += (" ");
							string += (crossSection);
							string += (horizontalLine);
							/*     ║     ╟─01121
   								   ║     ║   ║
   								   ║     ╟─01122
   								   ╙─012
      								  ╙─0120
							BOTTOM CORNER WITH NEIGHBOR LINK*/
						} else {
							string += (" ");
							string += (bottomCorner);
							string += (horizontalLine);
							/*	   ║     ╟─01121
   								   ║     ║   ║
   								   ║     ╙─01122
   							BOTTOM CORNER WITHOUT NEIGHBOR LINK*/
							
						}
						//_____________________________________________________________________________________________
						
					}
					else {							
						string += (" ");
						string += (crossSection);
						string += (horizontalLine);
						//  ╟─01100 GENERATES THIS CHARACTER AT THE END OF A LINE 
					}
				}
				else {									
					if (indicator) {
						string += (" ");
						string += ("  ");
						//GENERATE EMPTY COLUMNS IN THE TREE
					}
					else {
						string += (" ");
						string += (verticalLine);
						string += (" "); 
						//   ║  ║  ╟─01100 GENERATES THE VERTICAL LINES
					}
				}
			}
			System.out.print(string.substring(1)); //substring to remove first space
			System.out.println(each.getId());
			
			//LAST LEVEL NEIGHBOR LINK_____________________________________________________________________
			if (each.getLevel() == this.endLevel) {
				String neighborId = (each.getNeighbor(this).get(0).getKey());
				if (each.getAccessibleRooms().contains(this.getRoom(neighborId, this.startingRoom))) {	
					//check if the neighbor room is an accessible room (if it is, this link was made by Map.addNeighborLink)
					string = string.substring(1, string.length()-2) + verticalLine; //remove first space, remove cross section, add vertical line
					for (int i = 0; i < each.getLevel() + 1; i += 2) {string += " ";}
					string += verticalLine;
					System.out.println(string);
					/*   ║     ╟─01120
   						 ║     ║   ║
   						 ║     ╟─01121
   						 ║     ║   ║
   						 ║     ╙─01122
   					GENERATES NEIGHBOR LINKS AT LAST LEVEL*/
				}
			}
			//_____________________________________________________________________________________________
			
			//OLD VERSION__________________________________________________________________________________
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
			}
			System.out.println(each.getId());*/
			
			//_____________________________________________________________________________________________
			
					
			displayFromRoom(each);
		}
	}	
	
	
	public Room getRoom(String id, Room root) { 
		//returns the room that is defined by id. root must be set at startingRoom
		
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
		//Applies Room.addNeighborLink to every room of the dungeon
		
		for (Room each : root.getNextRooms()) { 
			each.addNeighborLink(this, probability);
			this.addNeighborLink(each, probability);
		}
	}
}
