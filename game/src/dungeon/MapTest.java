package dungeon;

import static org.junit.Assert.*;

import org.junit.Test;

public class MapTest {

	@Test
	public void testGetStartingRoom() {
		Map map = new Map(1,1,1,1);
		Room room = new Room("0", 0,0);
		assertEquals(room, map.getStartingRoom());
	}

	@Test
	public void testGetEndingRoom() {
		Map map = new Map(1,1,1,1);
		Room room = new Room("1", 1,1);
		map.endingRoom = room;
		assertSame(room, map.getEndingRoom());
	}

	@Test
	public void testGetEndLevel() {
		Map map = new Map(1,1,1,1);
		map.endLevel = 3;
		assertEquals(3,map.getEndLevel());
	}

	@Test
	public void testGetMaxExitNumber() {
		Map map = new Map(1,1,1,1);
		assertEquals(1,map.getMaxExitNumber());
	}

	@Test
	public void testGetNeighborLinkProbability() {
		Map map = new Map(1,1,1,1);
		assertSame(1,map.getNeighborLinkProbability());
	}

	@Test
	public void testGetFillProbability() {
		Map map = new Map(1,1,1,1);
		assertSame(1,map.getFillProbability());
	}

	@Test
	public void testGetRoomNumber() {
		Map map = new Map(1,1,1,1);
		map.roomAmount = 1;
		assertEquals(1,map.getRoomNumber());
	}

	@Test
	public void testReachesMaxLevel() {
		Map map = new Map(1,1,1,1);
		Room room = new Room("1", 1, 1);
		assertTrue(map.reachesMaxLevel(room));
	}


}

//the other functions have random variables in them, can't predict the outcome
