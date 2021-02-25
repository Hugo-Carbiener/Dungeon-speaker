package main;


import dungeon.Map;
import dungeon.Room;

public class Main {

	public static void main(String[] args) {
		Map map = new Map(4,4);
		Room root = map.getStartingRoom();
		map.displayFromRoom(root);		
	}
}
	
