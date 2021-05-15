package player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.javatuples.Pair;


public class Monster extends Character{

	private String name;
	@SuppressWarnings("unused")
	private boolean isAlive = true;		//Is used when entering a room : if the monster is alive then a combat has to start  with the player
	
	
	public Monster(String name, int health, int baseDamage) {
		super(health, 0, 0.5, baseDamage);		//For now we neglect speed and mana for monsters
		this.name = name;
	}
	
	public String getName() {return this.name;}
	
	public static Monster generateMonster() {
		//Generate a monster (name, health, baseDamage) picked randomly from a list
		
		String name;				//Name of the monster generated
		int rarityIndicator;		//Determines the rarity of the monster which is used to calculated health, damages and probability of being generated
		int health;
		int baseDamage;
		
		Pair<String,Integer> monsterMarker;
		//List of monster markers (name, rarityIndicator)
		List<Pair<String, Integer>> monsters = new ArrayList<>(Arrays.asList(
				Pair.with("Zombie", 1),
				Pair.with("Skeleton", 2),
				Pair.with("Ghost", 3),
				Pair.with("Undead", 4),
				Pair.with("Wolf", 5),
				Pair.with("Giant spider", 6),
				Pair.with("Slime", 7),
				Pair.with("Ghoul", 8),
				Pair.with("Necromancer", 9),
				Pair.with("dragon", 10)));	
		
		//Randomly pick a monster
		int r = (int) (Math.random() * monsters.size());
		monsterMarker = monsters.get(r);
		name = monsterMarker.getValue0();
		rarityIndicator = monsterMarker.getValue1();
		
		double keepLimit = (rarityIndicator / 10) - 0.1;       //Used to determine if we keep the weapon when doing the probability check
		
		double keepTest = Math.random();					   //Random number compared to the probability limit
		
		//PROBABILITY CHECK
		//_______________________________________________________
		while (keepTest < keepLimit) { //redraw a weapon
			r = (int) (Math.random() * (monsters.size() - 1));
			monsterMarker = monsters.get(r);
			name = monsterMarker.getValue0();
			rarityIndicator = monsterMarker.getValue1();
			keepLimit = (rarityIndicator / 10) - 0.1;
		}
		//_______________________________________________________
		
		health = calculateHealth(rarityIndicator);
		baseDamage = calculateDamage(rarityIndicator);
		
		Monster monster = new Monster(name, health, baseDamage);
		return monster;
	}

	private static int calculateHealth(int rarityIndicator) {
		//Base health is 10 per rarity level
		int health = 10 * rarityIndicator;
		//Add a number between +10% of health and -10% of health to randomize health amount
		int health10 = health / 10;
		int r = (int) (Math.random() *(2 * health10) - health10);
		health += r * health10;
		
		return health;
	}
	
	private static int calculateDamage(int rarityIndicator) {
		//Base damage is 5 per rarity level
		int baseDMG = 5 * rarityIndicator;
		//Add a number between +10% of baseDamage and -10% of baseDamage to randomize baseDamage amount
		int baseDMG10 = baseDMG / 10;
		int r = (int) (Math.random() *(2 * baseDMG10) - baseDMG10);
		baseDMG += r * baseDMG10;
		
		return baseDMG;
	}

	public void defend() {
		
	}
}

