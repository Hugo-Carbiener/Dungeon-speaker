package dungeon;

public class Map {
	private Room startingRoom = new Room("0", 0);		//map.startingRoom is the root node 
	private int endLevel; 			//determines how many "floors" the dungeon will have
	private int maxExitNumber; 		//determines how many exits a room can have. 
	
	
	public Map(int endLevel,int maxExitNumber) { 
		//this constructor generates a map as well as a basic tree architecture displayable using displayFromRoom(map.getStartingRoom())
		this.endLevel = endLevel;
		this.maxExitNumber = maxExitNumber;
		
		//GENERATION OF THE TREE
		generateBasicTree(0, this.startingRoom);
	}
	
	public void generateBasicTree(int level, Room root) {
		if (level < this.endLevel) {
			int nbOfRoom = (int) (Math.random() * this.maxExitNumber);
			for (int i = 0; i < nbOfRoom; i++) { 					//generate the x rooms, children of root
				String newId = root.getId() + String.valueOf(i);	//generate the id of the room about to be created
				//System.out.println(newId);
				
				root.addRoom(newId, level + 1);
			}
			//System.out.println("Room generated");
			for (Room each : root.getNextRooms()) { 	//generate basic trees for each child
				generateBasicTree(level + 1, each);
			}
		}
	}
	
	
	public void displayFromRoom(Room room) {		
		if (room == this.getStartingRoom()) {System.out.println("0");}
		for (Room each : room.getNextRooms()) {
			for (int i = 0; i < each.getLevel(); i++) {
			System.out.print("----"); //print tabulation to arrange display
			}
			System.out.println(each.getId());
			displayFromRoom(each);
		}
	}
	
	
	public Room getStartingRoom() {return this.startingRoom;}
	public int getEndLevel() {return this.endLevel;}
	public int getMaxExitNumber() {return this.maxExitNumber;}
}
