package player;

public class Hero extends Character{
	String username;
	//Room position;
	int level;
	int xp;
	//int xpCap;
	
	public Hero(String username) {
		//Default settings for a new player
		super(20, 20, 1, 2);
		this.username = username;
		xp = 0;
		//xpCap = getxpCap(int level);
	}
	
	public String getUsername() {return this.username;}
	public int getLevel() {return this.level;}
	
	public void equipItem(Item item) {
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
	
	
	
}












