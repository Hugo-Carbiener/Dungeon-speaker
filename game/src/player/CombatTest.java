package player;

import static org.junit.Assert.assertSame;

import org.junit.Test;

import dungeon.Map;

public class CombatTest {

	@Test
	public void testGetPlayer() {
		Map map = new Map(1,1,1,1);
		Hero hero = new Hero("username",map);
		Monster monster = new Monster("monster", 5,5); 
		Combat combat = new Combat(hero, monster);
		assertSame(combat.hero, combat.getPlayer());
	}

	@Test
	public void testGetMonster() {
		Map map = new Map(1,1,1,1);
		Hero hero = new Hero("username",map);
		Monster monster = new Monster("monster", 5,5); 
		Combat combat = new Combat(hero, monster);
		assertSame(combat.monster, combat.getMonster());
	}
}
//the other functions can be tested directly on GUI