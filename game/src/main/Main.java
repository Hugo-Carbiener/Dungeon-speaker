package main;


import java.util.List;

import dungeon.Map;
import dungeon.Room;

public class Main {

	public static void main(String[] args) {
		Map map = new Map(4,4);
		while (map.getRoom("011", map.getStartingRoom()) == null) {
			map = new Map(4,4);
		}
		
		Room root = map.getStartingRoom();
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
		
		System.out.println();
		System.out.println("before adding");
		List<Room> accessibleRooms = testRoom.getAccessibleRooms();
		for (Room each : accessibleRooms) {
			System.out.println(each.getId());
		}
		testRoom.addNeighborLink(map, 0.5);
		accessibleRooms = testRoom.getAccessibleRooms();
		System.out.println("after adding");
		for (Room each : accessibleRooms) {
			System.out.println(each.getId());
		}
		
		/*Room room1 = map.getRoom("010", root);
		Room room2 = map.getRoom("011", root);
		Room room3 = map.getRoom("020", root);
		Room room4 = map.getRoom("0101", root);
		Room room5 = map.getRoom("01", root);
		List<Room> accessibleRooms1 = room1.getAccessibleRooms();
		List<Room> accessibleRooms2 = room2.getAccessibleRooms();
		List<Room> accessibleRooms3 = room3.getAccessibleRooms();
		List<Room> accessibleRooms4 = room4.getAccessibleRooms();
		List<Room> accessibleRooms5 = room5.getAccessibleRooms();
		
		System.out.print("Room 1 accessible rooms : ");
		System.out.println(accessibleRooms1);
		System.out.print("Room 2 accessible rooms : ");
		System.out.println(accessibleRooms2);
		System.out.print("Room 3 accessible rooms : ");
		System.out.println(accessibleRooms3);
		System.out.print("Room 4 accessible rooms : ");
		System.out.println(accessibleRooms4);
		System.out.print("Room 5 accessible rooms : ");
		System.out.println(accessibleRooms5);*/
		
	}
}
	
