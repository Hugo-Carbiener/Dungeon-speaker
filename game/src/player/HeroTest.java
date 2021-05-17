package player;

import static org.junit.Assert.*;

import org.junit.Test;

import dungeon.Map;
import dungeon.Room;

public class HeroTest {

	@Test
	public void testGetUsername() {
		Map map = new Map(1,1,1,1);
		Hero hero = new Hero("username",map);
		assertSame("username",hero.getUsername());
	}

	@Test
	public void testGetLevel() {
		Map map = new Map(1,1,1,1);
		Hero hero = new Hero("username",map);
		hero.level = 3;
		assertSame(hero.level,hero.getLevel());
	}

	@Test
	public void testGetPosition() {
		Map map = new Map(1,1,1,1);
		Hero hero = new Hero("username",map);
		Room room = new Room("00",1,1);
		hero.position = room;
		assertSame(hero.position,hero.getPosition());
	}

	@Test
	public void testGetBalance() {
		Map map = new Map(1,1,1,1);
		Hero hero = new Hero("username",map);
		assertSame(hero.balance,hero.getBalance());
	}

	@Test
	public void testEquip() {
		Map map = new Map(1,1,1,1);
		Hero hero = new Hero("username",map);
		Item item = new Item(5, true, false, false);
		hero.equip(item);
		assertSame(item,hero.equippedItem);
		
	}

	@Test
	public void testThrowItem() {
		Map map = new Map(1,1,1,1);
		Hero hero = new Hero("username",map);
		Item item = new Item(5, true, false, false);
		hero.equip(item);
		hero.throwItem(item);
		assertFalse(hero.inventory.contains(item));
		
	}


	@Test
	public void testTake() {
		Map map = new Map(1,1,1,1);
		Hero hero = new Hero("username",map);
		Item item = new Item(5, true, false, false);
		Room room = new Room("1", 1,1);
		room.items.add(item);
		hero.position = room;
		assertTrue(hero.inventory.contains(item));
		
	}


	@Test
	public void testMoveTo() {
		Map map = new Map(3,3,3,3);
		Hero hero = new Hero("username",map);
		Room room = new Room("1", 1,1);
		Room nextroom = new Room("2", 1, 1);
		room.nextRooms.add(nextroom);
		hero.position = room;
		hero.moveTo(nextroom);
		assertSame(nextroom,hero.getPosition());
	}

	@Test
	public void testMoveForward() {
		Map map = new Map(3,3,3,3);
		Hero hero = new Hero("username",map);
		Room room = new Room("1", 1,1);
		Room nextroom = new Room("2", 1, 1);
		room.nextRooms.add(nextroom);
		hero.position = room;
		hero.moveForward();
		assertSame(nextroom,hero.getPosition());
	}

	@Test
	public void testMoveBackwards() {
		Map map = new Map(3,3,3,3);
		Hero hero = new Hero("username",map);
		Room room = new Room("1", 1,1);
		Room previousroom = new Room("2", 1, 1);
		room.previousRoom = previousroom;
		hero.position = room;
		hero.moveBackwards();
		assertSame(previousroom,hero.getPosition());
	}

	@Test
	public void testBacktrack() {
		Map map = new Map(3,3,3,3);
		Hero hero = new Hero("username",map);
		Room room = new Room("1", 1,1);
		Room previousroom = new Room("2", 1, 1);
		room.previousRoom = previousroom;
		hero.position = room;
		hero.backtrack();
		assertFalse(hero.visitedRooms.contains(room));
	}
}

//the other functions can be tested directly on GUI