package player;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import dungeon.Map;
import dungeon.Room;
import gui.GuiGameWindow;
import javafx.util.Pair;

public class Hero extends Character{
	private String username;
	private Room position;
	private List<Room> visitedRooms;
	private int level;
	int xp;
	private int balance = 0;
	
	public Hero(String username, Map map) {
		//Default settings for a new player
		super(100, 20, 1, 20);
		Room root = map.getStartingRoom();
		
		this.username = username;
		this.position = root;
		this.visitedRooms = new ArrayList<>();
		this.xp = 0;
		
		//Hero appears in the starting room so it is visited
		this.position.setAsVisited();
		this.visitedRooms.add(root);
	}
	
	public String getUsername() {return this.username;}
	public int getLevel() {return this.level;}
	public Room getPosition() {return this.position;}
	public int getBalance() {return this.balance;}
	
	public void equip(Item item) {
		if (item.isEquipable()) {
			Item temp = this.equippedItem;
			
			this.equippedItem = item;
			item.equipped = true;
			this.inventory.remove(item);
			String message = "You equip your " + item.getName();
			if (temp != null) { //if their was an item previously equipped 
				temp.equipped = false;
				this.inventory.add(temp);
				message += "and put your" + temp.getName() + " back into your inventory";
			}
			message += ".";
			GuiGameWindow.GuiGameDisplay(message, Color.WHITE, true);
		} else {
			GuiGameWindow.GuiGameDisplay("This item is not equipable.", Color.WHITE, true);
		}
	}
	
	public void throwItem(Item item) {
		if (this.inventory.contains(item)) { //if the designated item is in inventory we get rid of it
			this.inventory.remove(item);
			GuiGameWindow.GuiGameDisplay("You throw your " + item.getName() + ".", Color.WHITE, true);
		} else if (this.equippedItem == item) { //if the designated item is equipped we either get rid of it or use it if throwable
			this.equippedItem = null;
			if (item.isThrowable()) {
				//do item action
			}
			GuiGameWindow.GuiGameDisplay("You throw your equipped " + item.getName() + ".", Color.WHITE, true);
		}
		else {
			System.out.println("The item does not exist");
		}
	}
	
	public void consume() {
		//Consume item if consummable
	}
	
	
	public <T> void take(T item) {
		//Take an item from the room to put it in the player's inventory
		if (this.position.getItems().contains(item) && item != null) {
			if (item instanceof Coins) {		//if player takes coins add an amount to the balance
				this.balance  += ((Coins) item).getAmount();
				this.position.getItems().remove(item);
				GuiGameWindow.GuiGameDisplay("You pick up the coins.", Color.WHITE, true);
			} else if (item instanceof Item){
			//else adds a miscellaneous item to the inventory
				if (this.inventory.size() < this.inventorySize) {
					this.inventory.add((Item) item);
					this.position.getItems().remove(item);
					GuiGameWindow.GuiGameDisplay("You pick up the " + ((Item) item).getName() + " and put it in your inventory.", Color.WHITE, true);
				} else {
					GuiGameWindow.GuiGameDisplay("Your inventory is full! Throw away something first.", Color.WHITE, true);
				}
			} else {
				System.err.println("Parameter is not an Item");
			}
		}
	}
	
	public void checkInventory() {
		//Print a description of the inventory's content
		GuiGameWindow.GuiGameDisplay("You open your bag and take a peek inside.", Color.WHITE, true);
		String output = "You see";
		if (this.inventory.size() == 0) {
			output += " nothing. It is empty.";
		} else {
			for (int i = 0; i < this.inventory.size(); i++) {
				output += " a " + this.inventory.get(i).getName();
				if (i == this.inventory.size() - 1) {
					output += ".";
				} else {
					output += ",";
				}
			}
		}
		GuiGameWindow.GuiGameDisplay(output, Color.WHITE, false);
		if (this.equippedItem != null) {
			String output2 = "You have equipped a " + this.equippedItem.getName() + ".";
			GuiGameWindow.GuiGameDisplay(output2, Color.WHITE, true);
		}
	}
	
	public void moveTo(Room room) {
		//Move player to the chosen room if it is accessible
		Room currentRoom = this.position;
		if (currentRoom.getAccessibleRooms().contains(room)) {
			this.position = room;
			this.visitedRooms.add(room);
			room.setAsVisited();
			//HERE ADD EVENTS THAT OCCUR WHEN ENTERING A ROOM
		} else {
			System.out.println("Room is not accessible");
		}
	}
	
	public void moveForward() {
		//Move the hero to the first child room
		Room nextRoom = position.getNextRooms().get(0);
		this.moveTo(nextRoom);
	}
	
	public void moveBackwards() {
		//Move the hero to the previous room
		Room previousRoom = position.getPreviousRoom();
		this.moveTo(previousRoom);
	}
	
	public void backtrack() {
		//Move the hero to the room previously visited
		visitedRooms.remove(visitedRooms.size()-1);
		Room previousRoom = visitedRooms.remove(visitedRooms.size()-1);
		this.moveTo(previousRoom);
	}
	
	public void moveLeft(Map map) {
		List<Pair<String, Boolean>> neighbors = this.getPosition().getNeighbor(map);
		if (neighbors.get(1).getValue() == false) {//Left neighbor does not exist
			GuiGameWindow.GuiGameDisplay("The room you are in does not have a left neighbor.", Color.WHITE, true);
		} else if(! (this.getPosition().getAccessibleRooms().contains(map.getRoom(neighbors.get(1).getKey(), map.getStartingRoom())))) {//if the list of accessible rooms does not contain the neighbor
			GuiGameWindow.GuiGameDisplay("There is no corridor leading to the left room. You may try to find another path.", Color.WHITE, true);
		} else {//The left room exists and is accessible
			Room targetRoom = map.getRoom(neighbors.get(1).getKey(), map.getStartingRoom());
			this.moveTo(targetRoom);
			GuiGameWindow.GuiGameDisplay("You move to the room on your left.", Color.WHITE, true);
		}
	}
	
	public void moveRight(Map map) {
		List<Pair<String, Boolean>> neighbors = this.getPosition().getNeighbor(map);
		if (neighbors.get(0).getValue() == false) {//Left neighbor does not exist
			GuiGameWindow.GuiGameDisplay("The room you are in does not have a right neighbor.", Color.WHITE, true);
		} else if(! (this.getPosition().getAccessibleRooms().contains(map.getRoom(neighbors.get(0).getKey(), map.getStartingRoom())))) {//if the list of accessible rooms does not contain the neighbor
			GuiGameWindow.GuiGameDisplay("There is no corridor leading to the right room. You may try to find another path.", Color.WHITE, true);
		} else {//The left room exists and is accessible
			Room targetRoom = map.getRoom(neighbors.get(0).getKey(), map.getStartingRoom());
			this.moveTo(targetRoom);
			GuiGameWindow.GuiGameDisplay("You move to the room on your left.", Color.WHITE, true);
		}
	}
	
	public void observe() {
		//Give a description of the room
		String[] adjectives = {"dark", "pestilent", "dank", "moist", "foul", "nasty", "rugged", "decayed", "old", "hideous", "surprisingly bright", "humid", "large", "small", "oppressing"};
		String[] goodAdjectives = {"shiny", "bright", "trusty", "well manufactured", "trustworthy", "perfectly balanced", "large", "old", "long"};
		String[] places = {"there", "on the ground", "on a shelve", "in an open chest", "in a little cabinet", "among rubles", "in the dark", "among crates", "in the hand of an unlucky adventurer"};
		String[] adverbs = {"menacingly", "aggressively", "wrathfully", "calmy", "curiously", "oodly", "nefariously"};
		
		String place;
		String adj = adjectives[(int) (Math.random() * adjectives.length)];
		String obs = "You look around you. It is a " + adj + " room.";
		GuiGameWindow.GuiGameDisplay(obs, Color.WHITE, false);
		
		//Item
		if (! this.getPosition().getItems().isEmpty()) {//Room is not empty item wise
			if (this.getPosition().getItems().size() == 1) {//Room has one item
				place = places[(int) (Math.random() * places.length)];
				String goodAdj = goodAdjectives[(int) (Math.random() * goodAdjectives.length)];
				obs = "There is an item laying " + place + ". It is a " + goodAdj+ " " + this.getPosition().getItems().get(0).getName() + ".";
				GuiGameWindow.GuiGameDisplay(obs, Color.WHITE, false);
				
			} else {//Room has more than one item
				place = places[(int) (Math.random() * places.length)];
				obs = "There are items laying " + place + ". You can see ";
				for (int i = 0; i < this.getPosition().getItems().size(); i++) {
					obs += "a " + this.getPosition().getItems().get(i).getName();
					if (i < this.getPosition().getItems().size() - 1) {
						obs += ",";
					} else {
						obs += ".";
					}
				}
				GuiGameWindow.GuiGameDisplay(obs, Color.WHITE, false);
			}
		}

		if (! (this.getPosition().getMonster() == null)) {//Room is not empty monster wise
			adj = adjectives[(int) (Math.random() * adjectives.length)];
			String adv = adverbs[(int) (Math.random() * adverbs.length)];
			obs = "There is a " + adj + " monster looking at you " + adv + ": a " + this.getPosition().getMonster().getName() + "!";
			GuiGameWindow.GuiGameDisplay(obs, Color.WHITE, true);
		}		
	}
}












