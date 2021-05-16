package player;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import game.Game;
import gui.GuiGameWindow;

public class Character {

	public int health;
	public int maxHealth;
	//Defines if the player is currently defendeing himself from an incomming attack
	public boolean isDefending = false;
	public int mana;
	public int maxMana;
	//speed may be used to determine who attacks first during an encounter
	public double speed;
	public int baseDamage;
	//the character can equip items such as weapons, spells, torches...
	public Item equippedItem;
	public List<Item> inventory;
	int inventorySize;
	
	//default settings of a new character
	public Character(int health, int mana, double speed, int baseDamage) {
		this.health = health;
		this.mana = mana;
		this.speed = speed;
		this.baseDamage = baseDamage;
		equippedItem = null;
		inventorySize = 10;
		inventory = new ArrayList<Item>();
	}
	
	
	public void basicAttack(Character target) {
		//Damage = base damages
		int totalDamage = this.baseDamage;
		// + weapon damages
		if (this.equippedItem != null) {
			totalDamage += this.equippedItem.itemDamage;
		}
		
		// +/- 10%
		int r = (int) ((Math.random() * (2 * totalDamage / 10)) - (totalDamage/10));
		totalDamage += r;
		
		// - defense
		if (target.isDefending) {
			//Defense divides the damages in two 
			totalDamage = totalDamage / 2;
			target.isDefending = false;
		}
	
		target.health -= totalDamage;
		
		//Create the message to explain the action
		String message = "";
		if (target == Game.getCombat().getMonster()) {
			message += "You deal " + totalDamage + " points of damages to the " + Game.getCombat().getMonster().getName() + ".";
		} else {
			message += "The " + Game.getCombat().getMonster().getName() + " inflicted you " + totalDamage + " points of damages ! Hold Strong !";
		}
		GuiGameWindow.GuiGameDisplay(message, Color.WHITE, true);
	}
	
	public void defend() {
		this.isDefending = true;
		
		String message = "";
		if (this == Game.getCombat().getPlayer()) {
			message += "You defend yourself from the incomming attack.";
		} else {
			message += "The " + Game.getCombat().getMonster().getName() + " is preparing the block your next move.";
		}
		GuiGameWindow.GuiGameDisplay(message, Color.WHITE, true);
	}
}
