package main;


import NLP.NLPManager;

import dungeon.Map;
import dungeon.Room;
import game.Game;
import player.Hero;
import player.Item;
import player.Weapon;
import gui.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		//Game.start();
		Map map = Map.generateMap(4, 4, 0.5, 0.5, 20, 30);
		map.displayFromRoom(map.getStartingRoom());
		System.out.println(map.getEndingRoom().getId());
		System.out.println(map.getRoom(map.getEndingRoom().getId(), map.getStartingRoom()).isEndingRoom());
	}
}
	