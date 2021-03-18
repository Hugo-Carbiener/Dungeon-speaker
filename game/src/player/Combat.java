package player;
import java.util.Scanner; //simplifies reading data on standard input (keyboard) or in a file.


public class Combat { 
	private Hero hero;
	private Monster monster;
	Scanner scanner = new Scanner(System.in); 
	
	public Combat(Hero hero, Monster monster) {
		this.hero = hero;
		this.monster = monster;
	}
	public void start(Hero hero, Monster monster) { //used to start a fight
		
		while(hero.health != 0 || monster.health != 0) { //the fight ends when the monster or the hero has no health anymore
			System.out.println(hero.getUsername() +" ("+ hero.health+") veuillez choisir votre action (1 : Utiliser une arme , 2 : Ne pas utiliser d'arme)");
            int attack = scanner.nextInt();
            doDamage(attack);//the hero attacks
            hero.health = hero.health - monster.baseDamage; //the monster attacks
		}
		
		if(hero.health == 0) {
			System.out.println("vous avez gagné");
			
		} else if(monster.health == 0) {
			System.out.println("vous avez perdu");
		}
	}
	
	public void doDamage(int attack){ //calculates the damage done by the hero
        switch(attack){
            case 1:
            	System.out.println("Veuillez choisir un objet de votre inventaire"); //in case the player uses an item
            	String itemName = scanner.next();
            	Item usedItem = new Item();
            	for(Item item : hero.inventory) {
            		if(itemName == item.name) {
            			usedItem = item;
            		}
            	}
            	int totalDamage =  hero.baseDamage+ usedItem.itemDamage;
                System.out.println(hero.getUsername() +  "utilise "+ itemName +" et inflige "+ totalDamage);
                hero.throwItem(usedItem);
                monster.health = monster.health - totalDamage;
                break;
                
            case 2:
                System.out.println(hero.getUsername() +"inflige "+ hero.baseDamage); //in case he doesn't use anything
                monster.health = monster.health - hero.baseDamage;
                break;
        }
    }
// might add the use of a potion to get health and mana back 
// might put the mana, xp and level features in place 

}
