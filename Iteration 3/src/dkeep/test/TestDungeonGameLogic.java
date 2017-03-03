package dkeep.test;

import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.*;
public class TestDungeonGameLogic {
	
	Vector <Person> v = new Vector <Person>();
	char[][] currentMap = new char[][] { { 'X', 'X', 'X', 'X', 'X'}, { 'X', 'H', ' ', 'G', 'X'},
		{ 'I', ' ', ' ', ' ', 'X'}, { 'I', 'k', ' ', ' ', 'X'} , { 'X', 'X', 'X', 'X', 'X'} }; //mapa de testes
	MapLevel Test = new MapLevel(currentMap,0,v);
	
	
	@Test
	public void testMoveHeroToFreeCell() {
		Person hero;
		GameMap Game = new GameMap();
		Game.editMap(currentMap);
		hero = Game.getHero();
		assertEquals(1, hero.getX()); assertEquals(1, hero.getY()); 
		Game.moveHero('s');
		hero = Game.getHero();
		assertEquals(1, hero.getX()); assertEquals(2, hero.getY()); 		
	}
	
	@Test
	public void testHeroBlockedByWall(){
		Person hero;
		GameMap Game = new GameMap();
		Game.editMap(currentMap);
		hero = Game.getHero();
		assertEquals(1, hero.getX()); assertEquals(1, hero.getY()); 
		Game.moveHero('w');
		hero = Game.getHero();
		assertEquals(1, hero.getX()); assertEquals(1, hero.getY()); 
	}
	/*
	@Test
	public void testHeroLosesByGuard(){
		GameMap Game = new GameMap();
		Game.changeState(0);
		assertEquals(1, Game.getHeroX()); assertEquals(1, Game.getHeroY()); 
		Game.moveHero('d');
		assertTrue(Game.isEndOfGame()); 
	}
	
	@Test
	public void testHeroTriesToExit() {
		
		GameMap Game = new GameMap();
		Game.changeState(0);
		assertEquals(1, Game.getHeroX()); assertEquals(1, Game.getHeroY()); 
		Game.moveHero('s');
		Game.moveHero('a');
		assertEquals(1, Game.getHeroX()); assertEquals(2, Game.getHeroY());
		assertFalse(Game.isEndOfGame());
	}
	
	@Test
	public void testHeroTriesToExitAndPasses() {
		
		GameMap Game = new GameMap();
		Game.changeState(0);
		assertEquals(1, Game.getHeroX()); assertEquals(1, Game.getHeroY()); 
		Game.moveHero('s');
		Game.moveHero('s');
		Game.moveHero('a');
		assertTrue(Game.isEndOfGame());	
	}
	
	@Test
	public void testDoorsOpen() {
		
		GameMap Game = new GameMap();
		Game.changeState(0);
		assertEquals(1, Game.getHeroX()); assertEquals(1, Game.getHeroY()); 
		Game.moveHero('s');
		Game.moveHero('s');
		assertEquals('S',Game.getMapPos(2,0));	
		assertEquals('S',Game.getMapPos(3,0));	
	}*/
}
