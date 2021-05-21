package player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.javatuples.Pair;

public class Weapon extends Item{
	String name;
	int damage;
	
	
	public Weapon() {
		super(10, true, false, false);	//set weapon weight to 10
		String name;					//Name of the weapon generated
		int rarityIndicator;			//Determines the rarity of the weapon which is used to calculated damages and probability of being generated
		int r;
		
		Pair<String,Integer> weaponMarker;
		List<Pair<String, Integer>> weapons = new ArrayList<>(Arrays.asList(
				Pair.with("club", 1),
				Pair.with("knife", 2),
				Pair.with("dagger", 3),
				Pair.with("bow", 4),
				Pair.with("sword", 5),
				Pair.with("axe", 6),
				Pair.with("whip", 7),
				Pair.with("claymore", 8),
				Pair.with("scythe", 9),
				Pair.with("katana", 10)));	
		
		r = (int) (Math.random() * weapons.size());
		weaponMarker = weapons.get(r);
		name = weaponMarker.getValue0();
		rarityIndicator = weaponMarker.getValue1();
		double keepLimit = (rarityIndicator / 10) - 0.1;       //Used to determine if we keep the weapon when doing the probability check
		
		double keepTest = Math.random();					   //Random number compared to the probability limit
		
		//PROBABILITY CHECK
		//_______________________________________________________
		while (keepTest < keepLimit) { //redraw a weapon
			r = (int) (Math.random() * (weapons.size() - 1));
			weaponMarker = weapons.get(r);
			name = weaponMarker.getValue0();
			rarityIndicator = weaponMarker.getValue1();
			keepLimit = (rarityIndicator / 10) - 0.1;
		}
		//_______________________________________________________
		
		
		this.name = name;
		this.damage = calculateDamage(rarityIndicator);
	}
	
	public String getName() {return this.name;}
	public int getDamage() {return this.damage;}

		int calculateDamage(int rarityIndicator) {
		//Base damage is 5 per rarity level
		int DMG = 5 * rarityIndicator;
		//Add a number between +10% of baseDamage and -10% of baseDamage to randomize baseDamage amount
		//int DMG10 = DMG / 10;
		//int r = (int) (Math.random() *(2 * DMG10) - DMG10);
		//DMG += r * DMG10;
		return DMG;
	}
}
