package game;

import dungeon.Map;
import player.Hero;

public class Engine {
	
	public static void init() {
		//init gamestate
		GameState gameState = GameState.init();
		
		//init GUI with title screen 
		
		//after pressing "start" button
		//prompt playerName
		String playerName = "";
		
		//GENERATE MAP
		Map map = Map.generateMap(4,4,0.5, 0.5, 20, 30);
		//GENERATE PLAYER 
		Hero player = new Hero(playerName, map);
		//Set GameState to playing phase
		gameState.setGamestate("Roaming");
		
		
	}
}
