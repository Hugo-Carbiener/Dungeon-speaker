package dungeon;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		
		Hero hero = new Hero("Kohuro");
		int health = hero.getHealth();
		int mana = hero.getMana();
		System.out.println("Le héros s'appelle " + hero.username);
		System.out.print("Il a " + health + " points de vie");
		System.out.println(" et " + mana + " points de mana");
		System.out.println("Il tient " + hero.equipedItem);
		System.out.println("Son inventaire contient ");
		for (Item temp : hero.inventory) {
			System.out.println(temp + ",");
		}
		
		Monster monster1 = generateMonster();
		System.out.println("Au secours un monstre est apparu");
		System.out.println("C'est un " + monster1.getName());
		System.out.println("Il a " + monster1.getHealth() + " points de vie et " + monster1.getAttack() + " points d'attaque");
		
	}
	
	
	
	public static Monster generateMonster() {
		String[] monsters = {"slime", "rat", "goblin", "wisp", "thief", "ghost", "specter", "drake", "dragon", "wolf", "Filipo"};
		Random r = new Random();
		Monster monster1 = new Monster(monsters[r.nextInt(monsters.length)]);
		return monster1;
	}
}
