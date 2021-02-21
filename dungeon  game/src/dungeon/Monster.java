package dungeon;

import java.util.Random;

public class Monster extends Character{
//the most common danger in the dungeon. Monster are meant to have a given chance of appaearing in the dungeon. This percentage will be linked to the name and attack value of the monster.
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
