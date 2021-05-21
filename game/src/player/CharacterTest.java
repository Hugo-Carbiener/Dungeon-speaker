package player;

import static org.junit.Assert.assertSame;

import org.junit.Test;

public class CharacterTest {

	@Test
	public void testBasicAttack() {
		Character hero = new Character(5,5,5,2);
		Character target = new Character(5,5,5,2);
		hero.basicAttack(target);
		assertSame(3, target.health);
	}
}
