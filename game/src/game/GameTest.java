package game;

import static org.junit.Assert.*;

import org.junit.Test;

import dungeon.Map;
import player.Combat;
import player.Hero;
import player.Monster;

public class GameTest {

	@Test
	public void testGetPlayer() {
		Map map = new Map(1,1, 1,1);
		Hero hero = new Hero("username",map);
		Game.setPlayer(hero);
		assertSame(hero, Game.getPlayer());
		
	}

	@Test
	public void testGetMap() {
		Map map = new Map(1,1, 1,1);
		Game.setMap(map);
		assertSame(map, Game.getMap());
	}

	@Test
	public void testGetCombat() {
		Map map = new Map(1,1, 1,1);
		Hero hero = new Hero("username",map);
		Monster monster = new Monster("monster", 5, 5);
		Combat combat = new Combat(hero, monster);
		Game.setCombat(combat);
		assertSame(combat, Game.getCombat());
	}

	@Test
	public void testGetGameState() {
		String gamestate = "gamestate";
		Game.setGameState(gamestate);
		assertSame(gamestate, Game.getGameState());
	}

	@Test
	public void testSetGameState() {
		String gamestate = "gamestate";
		Game.setGameState(gamestate);
		assertSame(gamestate, Game.getGameState());
		
	}
//we can test the other functions directly via GUI
}
