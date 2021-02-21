package dungeon;

public class Character {

	private int health;
	private int mana;
	//speed may be used to determine who attacks first during an ecounter
	public double speed;
	//the character can equip items such as weapons, spells, torches...
	public Item equipedItem;
	public Item[] inventory;
	int inventorySize;
	
	//default settings of a new character
	public Character(int health, int mana, double speed) {
		this.health = health;
		this.mana = mana;
		this.speed = speed;
		equipedItem = null;
		inventorySize = 10;
		inventory = new Item[this.inventorySize];
	}
	
	
	public int getHealth() {
		return this.health;
	}
	
	public int getMana() {
		return this.mana;
	}
	
	public int modifyHealth(int damageOrRegen) {
		return (this.health + damageOrRegen);
	}
	
	public int modifyMana(int consoOrRegen) {
		return (this.mana + consoOrRegen);
	}
	
	
}
