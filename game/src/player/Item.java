package player;

public class Item {

	String name;
	int weight;
	boolean equipable;
	boolean consummable;
	boolean throwable;
	boolean equipped = false;
	int itemDamage;
	
	public boolean isEquipable() {
		return this.equipable;
	}
	public boolean isConsummable() {
		return this.consummable;
	}
	public boolean isThrowable() {
		return this.throwable;
	}
	
	
}