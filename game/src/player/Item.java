package player;

public class Item {

	String name;
	int weight = 10;						//Set to 10 for now
	boolean equipable;
	boolean consummable;
	boolean throwable;
	boolean equipped = false;
	
	public Item(boolean equipable, boolean consummable, boolean throwable) {
		this.equipable = equipable;
		this.consummable = consummable;
		this.throwable = throwable;
	}
	
	public boolean isEquipable() {return this.equipable;}
	public boolean isConsummable() {return this.consummable;}
	public boolean isThrowable() {return this.throwable;}
	
	
}
