package game;

import java.awt.Color;
import java.awt.FontFormatException;
import java.awt.Image;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import dungeon.Map;
import dungeon.Room;
import gui.GuiDefeatScreen;
import gui.GuiGameMenu;
import gui.GuiGameWindow;
import gui.GuiTitleScreen;
import gui.GuiVictoryScreen;
import player.Combat;
import player.Hero;
import player.Item;



public class Game {
	
	private static Hero player;
	private static Map map;
	private static Combat combat;
	private static String gameState;				//defines the occuring event within the game(roaming when the player is moving, combat when in combat)
	public static Thread loopThread;
	public static Thread GuiThread;
	
	public static Hero getPlayer() {return Game.player;}
	public static Map getMap() {return Game.map;}
	public static Combat getCombat() {return Game.combat;}
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
		Map map = Map.generateMap(4, 4, 0.5, 1, 20, 30);
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
		while (player.health > 0 && !(player.getPosition() == map.getEndingRoom())) {
			Game.event();
		}
		if (player.health <= 0) {
			//lose condition
			gameState = "defeat";
			GuiGameWindow.GuiGameDisplay("<Press Enter to continue>", Color.WHITE, true);
			Game.event();
		} else if (player.getPosition() == map.getEndingRoom()) {
			//win condition
			gameState = "victory";
			GuiGameWindow.GuiGameDisplay("You reached the exit! A bright light shines on the path in front of you! It is now written: you will come out as a Hero! Congratulations!", Color.WHITE, true);
			GuiGameWindow.GuiGameDisplay("<Press Enter to continue>", Color.WHITE, true);
			Game.event();
		}
	}
	
	
	public static void event() {
		//Required to wait for the user's input
		//---------------------------------------------------------------
		if (! GuiGameWindow.getInputUpdateState()) {
			GuiGameWindow.GuiGameDisplay("Waiting for instructions...", Color.WHITE, false);
			
			//Pause the game loop to wait for the user's input 
			try {
				synchronized(loopThread) {
					loopThread.wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		String[] currentInput = GuiGameWindow.getCurrentInput();
		List<String> argList = Arrays.asList(currentInput);
		String action = currentInput[0];
		
		GuiGameWindow.setInputState(false);
		//-----------------------------------------------------------------
		
		switch (Game.gameState) {
		
		case "roaming":
			//Case where input is empty
			if (currentInput[0].equals("RR")) {
				GuiGameWindow.GuiGameDisplay("You did not write a command or the game could not interprete it. Please try another phrasing.", Color.RED, true);
			}
			
			switch (action) {
			case "take"://Take an item in the current room
				List<Item> roomItem = player.getPosition().getItems();
				if (roomItem.isEmpty()) {//There is no item to pick up
					GuiGameWindow.GuiGameDisplay("There is no item in this room", Color.WHITE, true);
				} else {
					player.take(roomItem.get(0));
					
				}
				break;
				
			case "check"://Either check the map or check the inventory
					if (argList.contains("map")) {
						//Check map
						GuiGameWindow.GuiRawDisplay("______________________________________", Color.WHITE);
						map.displayOnGuiFromRoom(map.getStartingRoom());
						GuiGameWindow.GuiRawDisplay("______________________________________", Color.WHITE);
					} else if (argList.contains("inventory")) {
						//Check inventory
						player.checkInventory();
					} else if (argList.contains("health")) {
						//Check health
						player.checkHealth();
					} else {//send error message if the nlp script did not output an argument
						GuiGameWindow.GuiGameDisplay("Your instruction was unclear. What did you want to check ?", Color.WHITE, true);
						}
				break;
				
			case "throw"://Either uses a throwable weapon (not implemented) or throw away items to clear space in the inventory				
				List<Item> playerItems = player.inventory;
				if (currentInput.length > 1) {//get the argument if it exists
					String arg = currentInput[1];
					for (Item each : playerItems) {//for each item in the player's inventory
						if (arg.equals(each.getName())) {
							player.throwItem(each);
							break;
						} else if (playerItems.indexOf(each) == playerItems.size()-1 && each.getName() != arg) {  //If we reached the last item of the list and the name is still not correct
							GuiGameWindow.GuiGameDisplay("You do not have such an item in your inventory...", Color.WHITE, true);
						}
					}
				} else {//send error message if the nlp script did not output an argument
					GuiGameWindow.GuiGameDisplay("Your instruction was unclear. What did you want to take ?", Color.WHITE, true);
				}
				break;
			
			case "equip"://Equip an item from the inventory
				List<Item> playerInventory = player.inventory;
				if (currentInput.length > 1) {//get the argument if it exists
					String arg = currentInput[1];
					for (Item each : playerInventory) {//for each item in the player's inventory;
						if (arg.equals(each.getName())) {
							player.equip(each);
							break;
						} else if ((playerInventory.indexOf(each) == playerInventory.size()-1) && (each.getName() != arg)) {  //If we reached the last item of the list and the name is still not correct
							GuiGameWindow.GuiGameDisplay("You do not have such an item in your inventory...",Color.WHITE, true);
						}
					}
				} else {//send error message if the nlp script did not output an argument
					GuiGameWindow.GuiGameDisplay("Your instruction was unclear. What did you want to take ?", Color.WHITE, true);
				}
				break;
			
			case "look"://Look at your surroundings 
				player.observe();
				break;
				
			case "attack"://Start the combat
				//Cheeck if there is a monster in the room
				if (player.getPosition().getMonster() == null) {//There is no monster
					GuiGameWindow.GuiGameDisplay("There is nothing to attack here...", Color.WHITE, true);
				} else {
					GuiGameWindow.GuiGameDisplay("You engage the " + player.getPosition().getMonster().getName() + "! Get ready!", Color.WHITE, true);
					combat = new Combat(player, player.getPosition().getMonster());
					combat.start();
				}
				break;
			
			case "move"://Move the player to another room
				if (currentInput.length > 1) {//get the argument if it exists
					//String[] arg = Arrays.copyOfRange(currentInput, 1, currentInput.length-1);
					if (argList.contains("first") || argList.contains("forward")) {//player wants to go to the first room
						if(monsterIsPresent()) {
							GuiGameWindow.GuiGameDisplay("You cannot go to the next room. A " + Game.player.getPosition().getMonster().getName() + " is blocking the way!", Color.WHITE, true);
							break;
						} else {
							//Check if there is a 'first room'
							String targetRoomId = player.getPosition().getId() + "0";
							Room targetRoom = map.getRoom(targetRoomId, map.getStartingRoom());
							if (targetRoom == null) {
								GuiGameWindow.GuiGameDisplay("That room does not exist.. You might want to check your map.", Color.WHITE, true);
							} else {
								GuiGameWindow.GuiGameDisplay("You move to the first room in front of you.", Color.WHITE, true);
								player.moveForward();
							}
							break;
						}
					} else if (argList.contains("second")) {//player wants to go to the second room
						if(monsterIsPresent()) {
							GuiGameWindow.GuiGameDisplay("You cannot go to the next room. A " + Game.player.getPosition().getMonster().getName() + " is blocking the way!", Color.WHITE, true);
							break;
						} else {
							//Check if there is a 'second room'
							String targetRoomId = player.getPosition().getId() + "1";
							Room targetRoom = map.getRoom(targetRoomId, map.getStartingRoom());
							if (targetRoom == null) {
								GuiGameWindow.GuiGameDisplay("That room does not exist.. You might want to check your map.", Color.WHITE, true);
							} else {
								GuiGameWindow.GuiGameDisplay("You move to the second room in front of you.", Color.WHITE, true);
								player.moveTo(targetRoom);
							}
							break;
						}
					} else if (argList.contains("third")) {//player wants to go the third room
						if(monsterIsPresent()) {
							GuiGameWindow.GuiGameDisplay("You cannot go to the next room. A " + Game.player.getPosition().getMonster().getName() + " is blocking the way!", Color.WHITE, true);
							break;
						} else {
							//Check if there is a 'third room'
							String targetRoomId = player.getPosition().getId() + "2";
							Room targetRoom = map.getRoom(targetRoomId, map.getStartingRoom());
							if (targetRoom == null) {
								GuiGameWindow.GuiGameDisplay("That room does not exist.. You might want to check your map.", Color.WHITE, true);
							} else {
								GuiGameWindow.GuiGameDisplay("You move to the third room in front of you.", Color.WHITE, true);
								player.moveTo(targetRoom);
							}
							break;
						}
					} else if (argList.contains("fourth")) {//player wants to go to the fourth room
						if(monsterIsPresent()) {
							GuiGameWindow.GuiGameDisplay("You cannot go to the next room. A " + Game.player.getPosition().getMonster().getName() + " is blocking the way!", Color.WHITE, true);
							break;
						} else {
							//Check if there is a 'fourth room'
							String targetRoomId = player.getPosition().getId() + "3";
							Room targetRoom = map.getRoom(targetRoomId, map.getStartingRoom());
							if (targetRoom == null) {
								GuiGameWindow.GuiGameDisplay("That room does not exist.. You might want to check your map.", Color.WHITE, true);
							} else {
								GuiGameWindow.GuiGameDisplay("You move to the fourth room in front of you.", Color.WHITE, true);
								player.moveTo(targetRoom);
							}
							break;
						}
					} else if (argList.contains("right")) {//player wants to go to the right neighboring room
						if(monsterIsPresent()) {
							GuiGameWindow.GuiGameDisplay("You cannot go to the next room. A " + Game.player.getPosition().getMonster().getName() + " is blocking the way!", Color.WHITE, true);
							break;
						} else {
							player.moveRight(map);
						}
					} else if (argList.contains("left")) {//player wants to go to the left neighboring room
						if(monsterIsPresent()) {
							GuiGameWindow.GuiGameDisplay("You cannot go to the next room. A " + Game.player.getPosition().getMonster().getName() + " is blocking the way!", Color.WHITE, true);
							break;
						} else {
							player.moveLeft(map);
						}
					} else if (argList.contains("back")) {//player wants to go back the their previous position
						GuiGameWindow.GuiGameDisplay("You follow your steps back and find yourself in the room previously visited.", Color.WHITE, true);
						player.backtrack();
						break;
					} else if (argList.contains("behind")){//player wants to go to the room behind them
						GuiGameWindow.GuiGameDisplay("You turn around and head towards the room that was behind you.", Color.WHITE, true);
						player.moveBackwards();
						break;
					} else {//We generate the dungeon with max four child rooms so there cannot be a fifth room
						GuiGameWindow.GuiGameDisplay("Your instruction was unclear. Such room does not exist...", Color.WHITE, true);
					}
				} else {//send error message if the nlp script did not output an argument
					GuiGameWindow.GuiGameDisplay("Your instruction was unclear. Please precise which room you are looking for.", Color.WHITE, true);
				}
				break;
			}
			break;
			
		case "combat":
			//player is in Combat. For now, due to the limited amount of possible actions during battles, we have chosen to let them run automatically
			break;
			
		case "defeat":
			try {
				new GuiDefeatScreen();
			} catch (FontFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "victory":
			try {
				new GuiVictoryScreen();
			} catch (FontFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}
	public static boolean monsterIsPresent() {
		//returns true if there is a monster on the player's current position
		if (Game.player.getPosition().getMonster() == null) {
			return false;
		} else {
			return true;
		}
	}
}



