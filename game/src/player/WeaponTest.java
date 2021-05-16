package player;

import static org.junit.Assert.*;

import org.junit.Test;

public class WeaponTest {

	@Test
	public void testGetName() {
		Weapon weapon = new Weapon();
		String name = weapon.name;
		assertSame(name, weapon.getName());
		
	}

	@Test
	public void testGetDamage() {
		Weapon weapon = new Weapon();
		int damage = weapon.damage;
		assertSame(damage, weapon.getDamage());
		
		
	}
	
	//can't test the other functions because they give random numbers 

}
