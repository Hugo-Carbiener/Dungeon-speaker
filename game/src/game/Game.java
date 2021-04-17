package game;

import dungeon.Map;
import player.Hero;

public class Game {
	
	private Hero player;
	private Map map;
	private String gameState;				//defines the occuring event within the game(roaming when the player is moving, combat when in combat)
	
	
	public Hero getHero() {return this.player;}
	public Map getMap() {return this.map;}
	public String getGameState() {return this.gameState;}
	public void setGameState(String str) {this.gameState = str;}
	
	public void init() {
		//init GUI with title screen 
		
		//after pressing "start" button
		//prompt playerName
		String playerName = "";
				
		//GENERATE MAP
		Map map = Map.generateMap(4,4,0.5, 0.5, 20, 30);
		//GENERATE PLAYER 
		Hero player = new Hero(playerName, map);
		//Create game with a roaming state
		this.player = player;
		this.map = map;
		this.gameState = "roaming";
	}
	
	
	public void loop() {
		
		//MAIN LOOP
		while (player.health != 0 && !(player.getPosition() == map.getEndingRoom())) {
			this.event();
		}
		
		
		if (player.health == 0) {
			//lose condition
		} else if (player.getPosition() == map.getEndingRoom()) {
			//win condition
		}
	}
	
	public void event() {
		switch (this.gameState) {
		
		case "roaming":
			//player can move or use objects or check the map or manage their inventory
			break;
			
		case "combat":
			//player is stuck in combat 
			break;
			
		}
	}
}
