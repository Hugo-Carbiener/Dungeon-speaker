package player;


public class Monster extends Character{

	private String name;
	private int attack;

	
	public Monster(String name) {
		super(10, 0, 0.5);
		this.name = name;
		attack = 5;
		
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getAttack() {
		return this.attack;
	}
	

}
