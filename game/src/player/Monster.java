package player;


public class Monster extends Character{

	private String name;
	

	
	public Monster(String name) {
		super(10, 0, 0.5, 2);
		this.name = name;
		
		
	}
	
	public String getName() {
		return this.name;
	}
	
	

}
