package main;


import dungeon.Map;
import dungeon.Room;
import player.Hero;

public class Main {
	
	public static void main(String[] args) {
		Map map = new Map(4,4,1);
		while (map.getRoom("011", map.getStartingRoom()) == null) {
			map = new Map(4,4,1);
		}
		Room root = map.getStartingRoom();
		Hero hero = new Hero("Kohuro", map);
		
		System.out.println(hero.getPosition().getId());

		map.displayFromRoom(root);
	}
}
	
