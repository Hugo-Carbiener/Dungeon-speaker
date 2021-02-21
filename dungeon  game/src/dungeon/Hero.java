package dungeon;

public class Hero extends Character{
	String username;
	//Room position;
	int level;
	int xp;
	//int xpCap;
	
	public Hero(String username) {
		//Default settings for a new player
		super(20, 20, 1);
		this.username = username;
		xp = 0;
		//xpCap = getxpCap(int level);
	}
}
