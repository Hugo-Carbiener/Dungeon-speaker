package player;

import static org.junit.Assert.*;

import org.junit.Test;

public class CoinsTest {

	@Test
	public void testGetAmount() {
		Coins coins = new Coins(10);
		assertSame(10, coins.getAmount());
	}

}
