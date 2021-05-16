package player;
import java.awt.Color;
import java.util.Scanner; //simplifies reading data on standard input (keyboard) or in a file.

import game.Game;
import gui.GuiGameWindow;


public class Combat { 
	private static Hero hero;
	private static Monster monster;
	Scanner scanner = new Scanner(System.in); 
	
	public static Hero getPlayer() {return hero;}
	public static Monster getMonster() {return monster;}
	
	public Combat(Hero hero, Monster monster) {
		Combat.hero = hero;
		Combat.monster = monster;
		Combat.start();
	}
	
	public static void start() { 
		
		while(getPlayer().health != 0 || monster.health != 0) { //the fight ends when the monster or the hero has no health anymore
			playerTurn();
			monsterTurn();
		}
		if(monster.health == 0) {
			GuiGameWindow.GuiGameDisplay("You have slain the" + monster.getName() + "!", Color.WHITE, false);
			GuiGameWindow.GuiGameDisplay("You could probably habve looted some equipment on the corpse if that was implemented in the game", Color.WHITE, true);
		} else if(getPlayer().health == 0) {
			GuiGameWindow.GuiGameDisplay("Out of energy, you begin to sumble as your sight darkens. You know that these are the last breaths you take...", Color.WHITE, false);
			GuiGameWindow.GuiGameDisplay("Your lifeless body hits the ground heavily.", Color.WHITE, false);
			if (getPlayer().equippedItem != null) {
				GuiGameWindow.GuiGameDisplay("You send your " + getPlayer().equippedItem.getName() + "flying away", Color.WHITE, false);
				GuiGameWindow.GuiGameDisplay("These are the last words of the story of" + getPlayer().getUsername() +", the adventurer", Color.WHITE, true);
			}
		}
	}
	
	private static void monsterTurn() {
		//Monster can either attack of defend
		double r = Math.random();
		if (r < 0.5) {
			monster.defend();
		} else {
			monster.basicAttack(getPlayer());
		}
	}
	
	private static void playerTurn() {
		//Monster can either attack of defend
				double r = Math.random();
				if (r < 0.5) {
					hero.defend();
				} else {
					hero.basicAttack(getPlayer());
				}
	}
	
	
	public void doDamage(int attack){ //calculates the damage done by the hero
        switch(attack){
            case 1:
            	System.out.println("Chose an item from your inventory"); //in case the player uses an item
            	String itemName = scanner.next();
            	Item usedItem = new Item(0,false,false,false);
            	for(Item item : getPlayer().inventory) {
            		if(itemName == item.name) {
            			usedItem = item;
            		}
            	}
            	int totalDamage =  getPlayer().baseDamage+ usedItem.itemDamage;
                System.out.println(getPlayer().getUsername() +  "uses "+ itemName +" and inflicts "+ totalDamage);
                getPlayer().throwItem(usedItem);
                monster.health = monster.health - totalDamage;
                break;
                
            case 2:
                System.out.println(getPlayer().getUsername() +"inflige "+ getPlayer().baseDamage); //in case he doesn't use anything
                monster.health = monster.health - getPlayer().baseDamage;
                break;
        }
    }
// might add the use of a potion to get health and mana back 
// might put the mana, xp and level features in use
}
