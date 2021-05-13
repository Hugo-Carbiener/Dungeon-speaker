package player;

import java.util.ArrayList;
import java.util.List;

import dungeon.Map;
import dungeon.Room;
import gui.GuiGameWindow;

public class Hero extends Character{
	private String username;
	private Room position;
	private List<Room> visitedRooms;
	private int level;
	int xp;
	private int balance = 0;
	
	public Hero(String username, Map map) {
		//Default settings for a new player
		super(20, 20, 1, 10);
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
		if (item.isEquipable()|| item.isThrowable()) {
			Item temp = this.equippedItem;
			
			this.equippedItem = item;
			item.equipped = true;
			this.inventory.remove(item);
			
			if (temp != null) { //if their was an item previously equipped 
				temp.equipped = false;
				this.inventory.add(temp);
			}
		}
	}
	
	public void throwItem(Item item) {
		if (this.inventory.contains(item)) { //if the designated item is in inventory we get rid of it
			this.inventory.remove(item);
		}
		else if (this.equippedItem == item) { //if the designated item is equipped we either get rid of it or use it if throwable
			this.equippedItem = null;
			if (item.isThrowable()) {
				//do item action
			}
		}
		else {
			System.out.println("The item does not exist");
		}
	}
	
	public void consume() {
		//Consume item if consumable
	}
	
	
	public <T> void take(T item) {
		//Take an item from the room to put it in the player's inventory
		if (this.position.getItems().contains(item) && item != null) {
			if (item instanceof Coins) {		//if player takes coins add an amount to the balance
				this.balance  += ((Coins) item).getAmount();
				this.position.getItems().remove(item);
				GuiGameWindow.GuiDisplay("You pick up the coins.");
			} else if (item instanceof Item){
			//else adds a miscellaneous item to the inventory
				if (this.inventory.size() < this.inventorySize) {
					this.inventory.add((Item) item);
					this.position.getItems().remove(item);
					GuiGameWindow.GuiDisplay("You pick up the " + ((Item) item).getName());
				} else {
					GuiGameWindow.GuiDisplay("Your inventory is full! Throw away something first.");
				}
			} else {
				System.err.println("Parameter is not an Item");
			}
		}
	}
	
	public void checkInventory() {
		//Print a description of the inventory's content
		GuiGameWindow.GuiDisplay("You open your bag and take a peek inside.\n");
		GuiGameWindow.GuiDisplay("You see");
		if (this.inventory.size() == 0) {
			GuiGameWindow.GuiDisplay(" nothing. It is empty.");
		} else {
			for (int i = 0; i < this.inventory.size(); i++) {
				GuiGameWindow.GuiDisplay(" a " + this.inventory.get(i).getName());
				if (i == this.inventory.size() - 1) {
					GuiGameWindow.GuiDisplay(".");
				} else {
					GuiGameWindow.GuiDisplay(",");
				}
			}
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
	
	public void observe() {
		//Give a description of the room
		String[] adjectives = {"dark", "pestilent", "dank", "moist", "foul", "nasty", "rugged", "decayed", "old", "hideous", "surprisingly bright", "humid", "large", "small", "oppressing"};
		String[] goodAdjectives = {"shiny", "bright", "trusty", "well manufactured", "trustworthy", "perfectly balanced", "large", "old", "long"};
		String[] places = {"there", "on the ground", "on a shelve", "in an open chest", "in a little cabinet", "among rubles", "in the dark", "among crates", "in the hand of an unlucky adventurer"};
		String[] adverbs = {"menacingly", "aggressively", "wrathfully", "calmy", "curiously", "oodly", "nefariously"};
		
		String place;
		String adj = adjectives[(int) (Math.random() * adjectives.length)];
		String obs = "You look around you.\nIt is a " + adj + " room.";
		
		obs += "\n\n";
		//Item
		if (! this.getPosition().getItems().isEmpty()) {//Room is not empty item wise
			if (this.getPosition().getItems().size() == 1) {//Room has one item
				place = places[(int) (Math.random() * places.length)];
				String goodAdj = goodAdjectives[(int) (Math.random() * goodAdjectives.length)];
				obs += "There is an item laying " + place + ". It is a " + goodAdj+ " " + this.getPosition().getItems().get(0).getName() + ".";
				
			} else {//Room has more than one item
				place = places[(int) (Math.random() * places.length)];
				obs += "There are items laying " + place + ". You can see ";
				for (int i = 0; i < this.getPosition().getItems().size(); i++) {
					obs += "a " + this.getPosition().getItems().get(i).getName();
					if (i < this.getPosition().getItems().size() - 1) {
						obs += ",";
					} else {
						obs += ".";
					}
				}
			}
		}
		obs += "\n\n";
		
		if (! (this.getPosition().getMonster() == null)) {//Room is not empty monster wise
			adj = adjectives[(int) (Math.random() * adjectives.length)];
			String adv = adverbs[(int) (Math.random() * adverbs.length)];
			obs += "There is a " + adj + " monster looking at you " + adv + ": a " + this.getPosition().getMonster().getName() + "!";
		}
		GuiGameWindow.GuiDisplay(obs);
	}
}












