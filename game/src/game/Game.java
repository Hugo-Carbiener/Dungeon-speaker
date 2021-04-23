package game;

import dungeon.Map;
import gui.GuiGameMenu;
import player.Hero;

public class Game {
	
	private static Hero player;
	private static Map map;
	private static String gameState;				//defines the occuring event within the game(roaming when the player is moving, combat when in combat)
	
	
	public static Hero getHero() {return Game.player;}
	public static Map getMap() {return Game.map;}
	public static String getGameState() {return Game.gameState;}
	public static void setGameState(String str) {Game.gameState = str;}
	
	public static void init() {
		//init GUI with title screen 
		
		//after pressing "start" button
		//prompt playerName
		String playerName = GuiGameMenu.username;
		//GENERATE MAP
		Map map = Map.generateMap(4,4,0.5, 0.5, 20, 30);
		//GENERATE PLAYER 
		Hero player = new Hero(playerName, map);
		//Create game with a roaming state
		Game.player = player;
		Game.map = map;
		Game.gameState = "roaming";
	}
	
	
	public static void loop() {
		
		//MAIN LOOP
		while (player.health != 0 && !(player.getPosition() == map.getEndingRoom())) {
			Game.event();
		}
		
		
		if (player.health == 0) {
			//lose condition
		} else if (player.getPosition() == map.getEndingRoom()) {
			//win condition
		}
	}
	
	public static void event() {
		switch (Game.gameState) {
		
		case "roaming":
			//player can move or use objects or check the map or manage their inventory
			break;
			
		case "combat":
			//player is stuck in combat 
			break;
			
		}
	}
}
