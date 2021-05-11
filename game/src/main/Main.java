package main;


import java.awt.FontFormatException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import dungeon.Map;
import dungeon.Room;
import game.Game;
import player.Hero;
import player.Item;
import player.Weapon;
import gui.*;

public class Main {
	
	public static void main(String[] args) throws FontFormatException, IOException {
		
		//Game.start();
		Map map = new Map(6,6,1,1);
		Hero player = new Hero("Kohuro", map);
		
		map.displayFromRoom(map.getStartingRoom());
		player.moveForward();
		System.out.println(player.getPosition().getItems().get(0).getName());
		System.out.println(player.getPosition().getMonsters().get(0).getName());
		player.observe();
		player.moveForward();
		System.out.println(player.getPosition().getItems().get(0).getName());
		System.out.println(player.getPosition().getMonsters().get(0).getName());
		player.observe();
		player.moveForward();
		System.out.println(player.getPosition().getItems().get(0).getName());
		System.out.println(player.getPosition().getMonsters().get(0).getName());
		player.observe();
		
		
	}
}
	
