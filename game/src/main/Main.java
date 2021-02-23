package main;


import player.Hero;

public class Main {

	public static void main(String[] args) {
		
		Hero hero = new Hero("Kohuro");
		int health = hero.getHealth();
		int mana = hero.getMana();
		System.out.println("Le héros s'appelle " + hero.getUsername());
		System.out.print("Il a " + health + " points de vie");
		System.out.println(" et " + mana + " points de mana");
		System.out.println("Il tient " + hero.equippedItem);
	}
}
	
