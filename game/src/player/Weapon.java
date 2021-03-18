package player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.javatuples.Pair;

public class Weapon extends Item{
	private String name;
	private int damage;
	
	
	public Weapon() {
		super(true, false, false);
		String name;				//Name of the weapon generated
		int rarityIndicator;		//Determines the rarity of the weapon which is used to calculated damages and probability of being generated
		
		Pair<String,Integer> weaponMarker;
		List<Pair<String, Integer>> weapons = new ArrayList<>(Arrays.asList(
				Pair.with("Club", 1),
				Pair.with("Knife", 2),
				Pair.with("Dagger", 3),
				Pair.with("Bow", 4),
				Pair.with("Sword", 5),
				Pair.with("Axe", 6),
				Pair.with("Whip", 7),
				Pair.with("Claymore", 8),
				Pair.with("Scythe", 9),
				Pair.with("Magic AK47", 10)));	
		
		int r = (int) (Math.random() * (weapons.size() - 1));
		weaponMarker = weapons.get(r);
		name = weaponMarker.getValue0();
		rarityIndicator = weaponMarker.getValue1();
		
		this.name = name;
		this.damage = calculateDamage(rarityIndicator);
	}
	
	public String getName() {return this.name;}
	public int getDamage() {return this.damage;}

	private int calculateDamage(int rarityIndicator) {
		//Base damage is 5 per rarity level
		int DMG = 5 * rarityIndicator;
		//Add a number between +10% of baseDamage and -10% of baseDamage to randomize baseDamage amount
		int DMG10 = DMG / 10;
		int r = (int) (Math.random() *(2 * DMG10) - DMG10);
		DMG += r * DMG10;
		return DMG;
	}
}
