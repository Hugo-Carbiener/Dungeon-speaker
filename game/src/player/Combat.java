package player;
import java.util.Scanner; //cf le fichier que j'ai envoyé sur la conv sur cette classe

//je n'ai pas du tout pris en compte le mana, xp et level for now 

public class Combat {
	private Hero hero;
	private Monster monster;
	Scanner scanner = new Scanner(System.in); 
	
	public Combat(Hero hero, Monster monster) {
		this.hero = hero;
		this.monster = monster;
	}
	public void start(Hero hero, Monster monster) {
		
		while(hero.health != 0 || monster.health != 0) {
			System.out.println(hero.username +" ("+ hero.health+") veuillez choisir votre action (1 : Utiliser une arme , 2 : Ne pas utiliser d'arme)");
            int attack = scanner.nextInt();
            doDamage(attack);
            hero.health = hero.health - monster.baseDamage; //c'est le monstre qui attaque 
		}
		
		if(hero.health == 0) {
			System.out.println("vous avez gagné");
			
		} else if(monster.health == 0) {
			System.out.println("vous avez perdu");
		}
	}
	public void doDamage(int attack){
        switch(attack){
            case 1:
            	System.out.println("Veuillez choisir un objet de votre inventaire");
            	String itemName = scanner.next();
            	Item usedItem = new Item();
            	for(Item item : hero.inventory) {
            		if(itemName == item.name) {
            			usedItem = item;
            		}
            	}
            	int totalDamage =  hero.baseDamage+ usedItem.itemDamage;
                System.out.println(hero.username +  "utilise "+ itemName +" et inflige "+ totalDamage);
                hero.throwItem(usedItem);
                monster.health = monster.health - totalDamage;
                break;
                
            case 2:
                System.out.println(hero.username +"inflige "+ hero.baseDamage);
                monster.health = monster.health - hero.baseDamage;
                break;
        }
    }
	

}
