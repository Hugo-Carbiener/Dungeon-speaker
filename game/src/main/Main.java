package main;


import dungeon.Map;

public class Main {

	public static void main(String[] args) {
		Map map = new Map(5,3);
		map.displayFromRoom(map.getStartingRoom());
	}
}
	
