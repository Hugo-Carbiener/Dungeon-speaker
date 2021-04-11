package main;


import java.util.List;

import dungeon.Map;
import dungeon.Room;
import player.Hero;
import player.Item;
import player.Weapon;

public class Main {
	
	public static void main(String[] args) {
		Map map = Map.generateMap(4,4,0.25,0.5, 20, 30);
		
		Room root = map.getStartingRoom();
		Hero hero = new Hero("Kohuro", map);

		map.displayFromRoom(root);
		System.out.println(map.getRoomNumber());
		
	}
}
	
