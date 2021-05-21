package dungeon;

import static org.junit.Assert.*;

import org.junit.Test;

import player.Item;
import player.Monster;

public class RoomTest {

	@Test
	public void testGetNextRooms() {
		Room room = new Room("1",1,1);
		Room nextroom = new Room("2", 1,1);
		room.nextRooms.add(nextroom);
		assertTrue(room.getNextRooms().contains(nextroom));
	}

	@Test
	public void testGetPreviousRoom() {
		Room room = new Room("1",1,1);
		Room previousroom = new Room("2", 1,1);
		room.previousRoom = previousroom;    //set previousRoom as public as not use SetPreviousRoom() to test each function individually
		assertSame(previousroom,room.getPreviousRoom());
	}

	@Test
	public void testSetPreviousRoom() {
		Room room = new Room("1",1,1);
		Room previousroom = new Room("2", 1,1);
		room.setPreviousRoom(previousroom);
		assertSame(previousroom, room.previousRoom);
	}

	@Test
	public void testGetAccessibleRooms() {
		Room room = new Room("1",1,1);
		Room nextroom = new Room("2", 1,1);
		room.accessibleRooms.add(nextroom);
		assertTrue(room.getAccessibleRooms().contains(nextroom));
	}

	@Test
	public void testGetId() {
		Room room = new Room("1",1,1);
		assertSame("1",room.getId());
	}

	@Test
	public void testGetLevel() {
		Room room = new Room("1",1,1);
		assertSame(1,room.getLevel());
	}

	@Test
	public void testWasVisited() {
		Room room = new Room("1",1,1);
		room.visited = true;         
		assertTrue(room.wasVisited());
	}

	@Test
	public void testSetAsVisited() {
		Room room = new Room("1",1,1);
		room.setAsVisited();
		assertTrue(room.visited);
	}

	@Test
	public void testIsEndingRoom() {
		Room room = new Room("1",1,1);
		room.isEndingRoom = true;
		assertTrue(room.isEndingRoom());
	}

	@Test
	public void testSetAsEndingRoom() {
		Room room = new Room("1",1,1);
		room.setAsEndingRoom();
		assertTrue(room.isEndingRoom);
	}

	@Test
	public void testGetItems() {
		Item item = new Item(5, true, false, false);
		Room room = new Room("1",1,1);
		room.items.add(item);
		assertTrue(room.getItems().contains(item));
	}

	@Test
	public void testGetMonster() {
		Monster monster = new Monster("monster", 5, 5);
		Room room = new Room("1",1,1);
		room.monster = monster;
		assertSame(monster, room.getMonster());
		
	}

	@Test
	public void testGetRoomFamily() {
		Room room = new Room("1",1,1);
		Room previousroom = new Room("2",1,1);
		room.setPreviousRoom(previousroom);
		Room secondroom = new Room("3",1,1);
		secondroom.setPreviousRoom(previousroom);
		assertTrue(room.getRoomFamily().contains(secondroom));
	}

	@Test
	public void testAddRoom() {
		Room room = new Room("1",1,1);
		room.addRoom("2", 1, 1);
		Room nextroom = new Room("2", 1, 1);
		assertEquals(nextroom,room.getPreviousRoom());
		assertTrue(nextroom.getAccessibleRooms().contains(room));
		assertTrue(room.getAccessibleRooms().contains(nextroom));
		assertTrue(room.getNextRooms().contains(nextroom));
	}


}
//can't test the other functions because they depend on a random variable
