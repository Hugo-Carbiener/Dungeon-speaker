package dungeon;

public class Item {

	String name;
	int weight;
	boolean equipable;
	boolean consummable;
	boolean throwable;
	boolean equipped = false;
	
	
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
