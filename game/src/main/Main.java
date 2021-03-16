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
		Hero hero = new Hero("Kohuro", map);
		System.out.println(hero.getPosition().getId());
		
		System.out.println(map.getRoom("0", map.getStartingRoom()).wasVisited());
		System.out.println(map.getRoom("00", map.getStartingRoom()).wasVisited());
		System.out.println(map.getRoom("01", map.getStartingRoom()).wasVisited());
		System.out.println(map.getRoom("010", map.getStartingRoom()).wasVisited());
		
		hero.moveTo(map.getRoom("01", map.getStartingRoom()));
		System.out.println(hero.getPosition().getId());
		
		System.out.println(map.getRoom("0", map.getStartingRoom()).wasVisited());
		System.out.println(map.getRoom("00", map.getStartingRoom()).wasVisited());
		System.out.println(map.getRoom("01", map.getStartingRoom()).wasVisited());
		System.out.println(map.getRoom("010", map.getStartingRoom()).wasVisited());
		
		hero.moveForward();
		System.out.println(hero.getPosition().getId());
		
		System.out.println(map.getRoom("0", map.getStartingRoom()).wasVisited());
		System.out.println(map.getRoom("00", map.getStartingRoom()).wasVisited());
		System.out.println(map.getRoom("01", map.getStartingRoom()).wasVisited());
		System.out.println(map.getRoom("010", map.getStartingRoom()).wasVisited());
		
		Room root = map.getStartingRoom();
		
		map.displayFromRoom(root);		
		
		
	}
}
	
