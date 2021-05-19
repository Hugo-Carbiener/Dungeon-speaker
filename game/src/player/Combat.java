package player;
import java.awt.Color;
import java.util.Scanner; //simplifies reading data on standard input (keyboard) or in a file.

import game.Game;
import gui.GuiGameWindow;


public class Combat { 
	private Hero hero;
	private Monster monster;
	Scanner scanner = new Scanner(System.in); 
	
	public Hero getPlayer() {return hero;}
	public Monster getMonster() {return monster;}
	
	public Combat(Hero hero, Monster monster) {
		this.hero = hero;
		this.monster = monster;
		Game.setGameState("Combat");
	}
	
	public void start() { 
		
		while(getPlayer().health > 0 && monster.health > 0) { //the fight ends when the monster or the hero has no health anymore
			playerTurn();
			monsterTurn();
		}
		if(getPlayer().health <= 0) {
			GuiGameWindow.GuiGameDisplay("Out of energy, you begin to sumble as your sight darkens. You know that these are the last breaths you take...", Color.RED, false);
			GuiGameWindow.GuiGameDisplay("Your lifeless body hits the ground heavily.", Color.RED, false);
			if (getPlayer().equippedItem != null) {
				GuiGameWindow.GuiGameDisplay("You send your " + getPlayer().equippedItem.getName() + "flying away.", Color.WHITE, false);
			}
			GuiGameWindow.GuiGameDisplay("These are the last words of the story of " + getPlayer().getUsername() +", the adventurer.", Color.RED, true);
		} else if(monster.health <= 0) {
			GuiGameWindow.GuiGameDisplay("You have slain the " + monster.getName() + "!", Color.WHITE, false);
			GuiGameWindow.GuiGameDisplay("You could probably habve looted some equipment on the corpse if that was implemented in the game.", Color.WHITE, true);
			//Remove the dead monster from the room
			hero.getPosition().setMonster(null);
			//Tell the game to listen to roaming actions
			Game.setGameState("roaming");
		}
	}
	
	private void monsterTurn() {
		//Monster can either attack of defend
		double r = Math.random();
		if (r <= 0.2) {
			monster.defend();
		} else {
			monster.basicAttack(getPlayer());
		}
	}
	
	private void playerTurn() {
		//Monster can either attack of defend
		double r = Math.random();
		if (r <= 0.2) {
			hero.defend();
		} else {
			hero.basicAttack(getMonster());
		}
	}
// might add the use of a potion to get health and mana back 
// might put the mana, xp and level features in use
}
