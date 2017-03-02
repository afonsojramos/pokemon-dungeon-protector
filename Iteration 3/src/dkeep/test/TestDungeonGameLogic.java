package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.*;
public class TestDungeonGameLogic {
	
	@Test
	public void testMoveHeroToFreeCell() {
		
		GameMap Game = new GameMap();
		assertEquals(1, Game.getHeroX()); assertEquals(1, Game.getHeroY()); 
		Game.moveHero('s');
		assertEquals(1, Game.getHeroX()); assertEquals(2, Game.getHeroY()); 		
	}
	
	@Test
	public void testHeroBlockedByWall(){
		GameMap Game = new GameMap();
		assertEquals(1, Game.getHeroX()); assertEquals(1, Game.getHeroY()); 
		Game.moveHero('w');
		assertEquals(1, Game.getHeroX()); assertEquals(1, Game.getHeroY()); 
	}
	
	@Test
	public void testHeroLosesByGuard(){
		GameMap Game = new GameMap();
		assertEquals(1, Game.getHeroX()); assertEquals(1, Game.getHeroY()); 
		Game.moveHero('d');
		assertTrue(Game.isEndOfGame()); 
	}
}
