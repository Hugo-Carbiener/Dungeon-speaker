package main;


import dungeon.Map;

public class Main {

	public static void main(String[] args) {
		Map map = new Map(15,4);
		map.displayFromRoom(map.getStartingRoom());
	}
}
	
