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
	public void start() { 
		
		while(hero.health != 0 || monster.health != 0) { //the fight ends when the monster or the hero has no health anymore
			this.playerTurn();
			this.monsterTurn();
		}
		if(hero.health == 0) {
			System.out.println("you won");
		} else if(monster.health == 0) {
			System.out.println("you lost");
		}
	}
	
	private void monsterTurn() {
		//For now the monster only attacks
		this.monster.basicAttack(this.hero);
	}
	
	private void playerTurn() {
		System.out.println(hero.getUsername() +" ("+ hero.health+") choose an action (1 : use weapon , 2 : don't use a weapon)");
        int attack = scanner.nextInt();
        doDamage(attack);//the hero attacks
	}
	
	
	public void doDamage(int attack){ //calculates the damage done by the hero
        switch(attack){
            case 1:
            	System.out.println("Chose an item from your inventory"); //in case the player uses an item
            	String itemName = scanner.next();
            	Item usedItem = new Item(0,false,false,false);
            	for(Item item : hero.inventory) {
            		if(itemName == item.name) {
            			usedItem = item;
            		}
            	}
            	int totalDamage =  hero.baseDamage+ usedItem.itemDamage;
                System.out.println(hero.getUsername() +  "uses "+ itemName +" and inflicts "+ totalDamage);
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
// might put the mana, xp and level features in use

}
