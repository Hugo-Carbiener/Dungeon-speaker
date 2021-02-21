package dungeon;

public class Item {

	String name;
	int weight;
	boolean equipable;
	boolean consummable;
	boolean throwable;
	
	
	public boolean isEquipable() {
		return this.equipable;
	}
	public boolean isConsummable() {
		return this.consummable;
	}
	public boolean isthrowable() {
		return this.throwable;
	}
	
	
}
