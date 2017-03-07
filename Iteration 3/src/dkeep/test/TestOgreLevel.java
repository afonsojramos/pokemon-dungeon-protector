package dkeep.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import dkeep.logic.GameMap;
import dkeep.logic.Person;

public class TestOgreLevel {
	@Test
	public void testMoveHeroToFreeCell() {
		char currentMap[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X'}, { 'X', 'H', ' ', 'G', 'X'},	
			{ 'I', ' ', ' ', ' ', 'X'}, { 'I', 'k', ' ', ' ', 'X'} , { 'X', 'X', 'X', 'X', 'X'} }; //mapa de testes
		GameMap game = new GameMap(currentMap, false, false);
		game.readMap();
		Person hero = game.getHero();
		assertEquals(1, hero.getX()); assertEquals(1, hero.getY()); 
		game.startGame('s');
		assertEquals(1, game.getNewHeroX()); assertEquals(2, game.getNewHeroY()); 		
	}
	
	@Test
	public void testHeroBlockedByWall(){
		char currentMap[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X'}, { 'X', 'H', ' ', 'G', 'X'},	
			{ 'I', ' ', ' ', ' ', 'X'}, { 'I', 'k', ' ', ' ', 'X'} , { 'X', 'X', 'X', 'X', 'X'} }; //mapa de testes
		GameMap game = new GameMap(currentMap, false, false);
		game.readMap();
		Person hero = game.getHero();
		assertEquals(1, hero.getX()); assertEquals(1, hero.getY()); 
		game.startGame('w');
		assertEquals(1, game.getNewHeroX()); assertEquals(1, game.getNewHeroY());  
	}
	
	@Test
	public void testMoveHeroToOgre() {
		char currentMap[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X'}, { 'X', 'H', ' ', 'O', 'X'},	
			{ 'I', ' ', ' ', ' ', 'X'}, { 'I', 'k', ' ', ' ', 'X'} , { 'X', 'X', 'X', 'X', 'X'} }; //mapa de testes
		GameMap game = new GameMap(currentMap, false, false);
		game.readMap();
		Person hero = game.getHero();
		assertEquals(1, game.getNewHeroX()); assertEquals(1, game.getNewHeroY()); 
		game.startGame('d');
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		assertEquals(2, game.getNewHeroX()); assertEquals(1, game.getNewHeroY());
		assertTrue(game.isEndOfGame());	 		
	}
	
	@Test
	public void testHeroChangeToK() {
		char currentMap[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X'}, { 'X', 'H', ' ', 'O', 'X'},	
			{ 'I', ' ', ' ', ' ', 'X'}, { 'I', 'k', ' ', ' ', 'X'} , { 'X', 'X', 'X', 'X', 'X'} }; //mapa de testes
		GameMap game = new GameMap(currentMap, false, false);
		game.readMap();
		Person hero = game.getHero();
		assertEquals(1, game.getNewHeroX()); assertEquals(1, game.getNewHeroY()); 
		game.startGame('s');
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		assertEquals(1, game.getNewHeroX()); assertEquals(2, game.getNewHeroY()); 
		game.startGame('s');
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		assertEquals(1, game.getNewHeroX()); assertEquals(3, game.getNewHeroY()); 
		assertEquals('K',hero.getCh());	
		assertFalse(game.isEndOfGame());
	}
	
	@Test
	public void testHeroFailToExit(){
		char currentMap[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X'}, { 'X', 'H', ' ', 'O', 'X'},	
			{ 'I', ' ', ' ', ' ', 'X'}, { 'I', 'k', ' ', ' ', 'X'} , { 'X', 'X', 'X', 'X', 'X'} }; //mapa de testes
		GameMap game = new GameMap(currentMap, false, false);
		game.readMap();
		Person hero = game.getHero();
		assertEquals(1, game.getNewHeroX()); assertEquals(1, game.getNewHeroY()); 
		game.startGame('s');
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		game.startGame('a');
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		assertFalse(game.isEndOfGame());		
	}
	
	@Test
	public void testHeroSucceedsToOpenDoor(){
		char currentMap[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X'}, { 'X', 'H', ' ', 'O', 'X'},	
			{ 'I', ' ', ' ', ' ', 'X'}, { 'I', 'k', ' ', ' ', 'X'} , { 'X', 'X', 'X', 'X', 'X'} }; //mapa de testes
		GameMap game = new GameMap(currentMap, false, false);
		game.readMap();
		Person hero = game.getHero();
		assertEquals(1, game.getNewHeroX()); assertEquals(1, game.getNewHeroY()); 
		game.startGame('s');
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		assertEquals(1, game.getNewHeroX()); assertEquals(2, game.getNewHeroY()); 
		game.startGame('s');
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		assertEquals(1, game.getNewHeroX()); assertEquals(3, game.getNewHeroY()); 
		assertEquals('K',hero.getCh());	
		game.startGame('a');
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		assertEquals(0, game.getNewHeroX()); assertEquals(3, game.getNewHeroY()); 
		assertEquals('S',game.getMapPos(2,0));
		assertEquals('S',game.getMapPos(3,0));		
	}
	
	@Test
	public void testHeroSucceedsToOpenDoorAndExits() {
		char currentMap[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X'}, { 'X', 'H', ' ', 'O', 'X'},	
			{ 'I', ' ', ' ', 'O', 'X'}, { 'I', 'k', ' ', ' ', 'X'} , { 'X', 'X', 'X', 'X', 'X'} }; //mapa de testes
		GameMap game = new GameMap(currentMap, false, false);
		game.readMap();
		Person hero = game.getHero();
		assertEquals(1, game.getNewHeroX()); assertEquals(1, game.getNewHeroY()); 
		game.startGame('s');
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		assertEquals(1, game.getNewHeroX()); assertEquals(2, game.getNewHeroY()); 
		game.startGame('s');
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		assertEquals(1, game.getNewHeroX()); assertEquals(3, game.getNewHeroY());
		assertEquals('K',hero.getCh());	
		game.startGame('a');
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		assertEquals('S',game.getMapPos(2,0));
		assertEquals('S',game.getMapPos(3,0));
		assertEquals(0, game.getNewHeroX()); assertEquals(3, game.getNewHeroY());
		assertEquals('K',hero.getCh());	
		game.startGame('a');
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		assertEquals(0, game.getNewHeroX()); assertEquals(3, game.getNewHeroY());
		assertTrue(game.isEndOfGame());	
	}
	
	/*@Test
	public void testMultipleOgres() {
		char currentMap[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X'}, { 'X', 'H', ' ', ' ', 'X'},	
			{ 'I', ' ', ' ', ' ', 'X'}, { 'I', 'k', ' ', ' ', 'X'} , { 'X', 'X', 'X', 'X', 'X'} }; //mapa de testes
		GameMap game = new GameMap(currentMap, true, true);
		game.readMap();
		Person hero = game.getHero();
		assertEquals(1, game.getNewHeroX()); assertEquals(1, game.getNewHeroY()); 
		game.startGame('s');
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		game.startGame('s');
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		game.startGame('a');
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		game.startGame('a');
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		assertTrue(game.isEndOfGame());	
	}
	*/
}
