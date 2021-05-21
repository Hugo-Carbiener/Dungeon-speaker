package player;

import java.util.ArrayList;
import java.util.List;

public class Character {

	public int health;
	public int maxHealth;
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
		int totalDamage = this.baseDamage;
		if (this.equippedItem != null) {
			totalDamage += this.equippedItem.itemDamage;
		}
		
		// +/- 10%
		//int r = (int) ((Math.random() * (2 * totalDamage / 10)) - (totalDamage/10));
		//totalDamage += r;
		
		target.health -= totalDamage;
	}
	
}
