package player;

import static org.junit.Assert.*;

import org.junit.Test;

public class ItemTest {

	@Test
	public void testIsEquipable() {
		Item item = new Item(5, true, false, false);
		assertSame(true, item.isEquipable());
	}

	@Test
	public void testIsConsummable() {
		Item item = new Item(5, false, true, false);
		assertSame(true, item.isConsummable());
	}

	@Test
	public void testIsThrowable() {
		Item item = new Item(5, false, false, true);
		assertSame(true, item.isThrowable());
	}

	@Test
	public void testGetName() {
		Item item = new Item(5, false, false, true);
		item.name = "sword";
		assertSame("sword", item.getName());
		
	}

}
