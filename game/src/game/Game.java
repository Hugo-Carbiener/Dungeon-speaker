package game;

import java.awt.FontFormatException;
import java.io.IOException;
import java.util.List;

import dungeon.Map;
import gui.GuiGameMenu;
import gui.GuiGameWindow;
import gui.GuiTitleScreen;
import player.Hero;
import player.Item;

public class Game {
	
	private static Hero player;
	private static Map map;
	private static String gameState;				//defines the occuring event within the game(roaming when the player is moving, combat when in combat)
	public static Thread loopThread;
	public static Thread GuiThread;
	
	public static Hero getHero() {return Game.player;}
	public static Map getMap() {return Game.map;}
	public static String getGameState() {return Game.gameState;}
	public static void setGameState(String str) {Game.gameState = str;}
	
	public static void start() {
		//Start the game by launching the Titlescreen interface in a separate thread
		GuiThread = new Thread() {
			public void run(){
				try {
					new GuiTitleScreen();
				} catch (FontFormatException | IOException e) {
					e.printStackTrace();
				}
			}
		};
		GuiThread.start();
	}
	
	public static void init() {
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
		Game.loop();
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
		//Required to wait for the user's input
		//---------------------------------------------------------------
		if (! GuiGameWindow.getInputUpdateState()) {
			GuiGameWindow.GuiGameDisplay("waiting...");
		
			//Pause the game loop to wait for the user's input 
			try {
				synchronized(loopThread) {
					loopThread.wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		String currentInput = GuiGameWindow.getCurrentInput();
		GuiGameWindow.GuiGameDisplay("Went through");
		GuiGameWindow.GuiGameDisplay(currentInput);
		GuiGameWindow.setInputState(false);
		//-----------------------------------------------------------------
		
		switch (Game.gameState) {
		
		case "roaming":
			switch (currentInput) {
			
			case "take":
			//Take an item
			player.take(player.getPosition().getItems().get(0));
			/*
			List<Item> roomItems = player.getPosition().getItems();
			for (Item each : roomItems) {			//for each item in the room's item pool
				if (each.getName() == argument) {
					player.take(each);
					break;
				} else if (roomItems.indexOf(each) == roomItems.size()-1 && each.getName() != argument) {  //If we reached the last item of the list and the name is still not correct
					GuiGameWindow.GuiDisplay("You do not have such an item in your inventory...");
				}
			}*/
			
				break;
				
			case "check":
			//Either check the map or check the inventory
				//Check map
				GuiGameWindow.GuiDefaultDisplay("______________________________________");
				map.displayOnGuiFromRoom(map.getStartingRoom());
				GuiGameWindow.GuiDefaultDisplay("______________________________________");
				
				//Check inventory
				player.checkInventory();
				break;
				
			case "throw":
			//Either uses a throwable weapon (not implemented) or throw away items to clear space in the inventory
				
				break;
			
			case "equip":
			//Equip an item from the inventory
				break;
			
			case "look":
			//Look at your surroundings 
				player.observe();
				break;
			
			case "move":
				player.moveForward();
				break;
			
				
			}
			break;
			
		case "combat":
			//player is stuck in combat 
			break;
			
		}
	}
}
