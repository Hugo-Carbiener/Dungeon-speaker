package player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.javatuples.Pair;


public class Monster extends Character{

	private String name;
	private boolean isAlive = true;		//Is used when entering a room : if the monster is alive then a combat has to start  with the player
	

	
	public Monster(String name) {
		super(10, 0, 0.5, 2);
		
		this.name = name;
		
		
	}
	
	public String getName() {return this.name;}
	
	public void monsterGeneration() {
		Pair<String, Integer> pair = Pair.with("blabla", 1);
		List<Pair<String, Integer>> monsters = new ArrayList<>(Arrays.asList(
		
		("dragon", 10)
				));
			
		};
		
	}

}
