package main;


import dungeon.Map;
import dungeon.Room;

public class Main {

	public static void main(String[] args) {
		Map map = new Map(4,4);
		while (map.getRoom("011", map.getStartingRoom()) == null) {
			map = new Map(4,4);
		}
		Room root = map.getStartingRoom();
		
		
		map.addNeighborLink(root, 0.5);
		map.displayFromRoom(root);		
		System.out.println();System.out.println();
		
		Room testRoom = map.getRoom("011", root);
		System.out.println("Test room 011");
		System.out.println();
		System.out.print("Previous room is : ");
		System.out.println(testRoom.getPreviousRoom().getId());
		System.out.println();
		System.out.println("Next rooms are : ");
		for (Room each : testRoom.getNextRooms()) {
			System.out.println(each.getId());
		}
		System.out.println();
		System.out.println("Accessible rooms are : ");
		for (Room each : testRoom.getAccessibleRooms()) {
			System.out.println(each.getId());
		}
		System.out.println();
		System.out.println("Room has neighbors ?");
		System.out.println(testRoom.getNeighbor(map));
				
	}
}
	
