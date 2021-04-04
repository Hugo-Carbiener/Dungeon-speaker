package main;


import java.util.List;

import dungeon.Map;
import dungeon.Room;
import player.Hero;
import player.Item;
import player.Monster;
import player.Weapon;

public class Main {
	
	public static void main(String[] args) {
		Map map = new Map(4,4,0.25,0.5);
		while (map.getRoom("011", map.getStartingRoom()) == null) {
			map = new Map(4,4,0.25,0.5);
		}
		Room root = map.getStartingRoom();
		Hero hero = new Hero("Kohuro", map);

		map.displayFromRoom(root);
		
		hero.moveForward();
		if (hero.getPosition().getItems().size()!=0) {
		hero.take(hero.getPosition().getItems().get(0));
		}
		hero.moveBackwards();
		hero.moveTo(map.getRoom("01", root));
		if (hero.getPosition().getItems().size()!=0) {
			hero.take(hero.getPosition().getItems().get(0));
			}
		hero.moveForward();
		if (hero.getPosition().getItems().size()!=0) {
			hero.take(hero.getPosition().getItems().get(0));
			}
		List<Item> inventory = hero.inventory;
		for (Item each : inventory) {
			System.out.println(((Weapon) each).getName());
		}
		
		
	}
}
	
