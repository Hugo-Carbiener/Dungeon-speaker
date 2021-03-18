package main;


import dungeon.Map;
import dungeon.Room;
import player.Hero;
import player.Monster;

public class Main {
	
	public static void main(String[] args) {
		Map map = new Map(4,4,0.25);
		while (map.getRoom("011", map.getStartingRoom()) == null) {
			map = new Map(4,4,0.25);
		}
		Room root = map.getStartingRoom();
		Hero hero = new Hero("Kohuro", map);
		
		System.out.println(hero.getPosition().getId());

		map.displayFromRoom(root);
		
		Monster monster1 = Monster.generateMonster();
		System.out.println(monster1.getName());
		System.out.println(monster1.baseDamage);
		System.out.println(monster1.health);
		
		Monster monster2 = Monster.generateMonster();
		System.out.println(monster2.getName());
		System.out.println(monster2.baseDamage);
		System.out.println(monster2.health);
	}
}
	
