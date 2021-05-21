package player;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import org.junit.Test;

public class MonsterTest {

	@Test
	public void testGetName() {
		Monster monster = new Monster("Loup", 6, 5);
		assertSame("Loup", monster.getName());
	}

	@Test
	public void testGenerateMonster() {
		fail("Not yet implemented");
	}

}
