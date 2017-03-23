package dkeep.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import dkeep.logic.GameMap;
import dkeep.logic.Maps;
import dkeep.logic.Person;

public class TestOgreLevel {
	@Test
	public void testMoveHeroToFreeCell() {
		char currentMap[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X'}, { 'X', 'H', ' ', 'G', 'X'},	
			{ 'I', ' ', ' ', ' ', 'X'}, { 'I', 'k', ' ', ' ', 'X'} , { 'X', 'X', 'X', 'X', 'X'} }; //mapa de testes
		GameMap game = new GameMap(currentMap, false,0, false);
		game.readMap(false);
		Person hero = game.getHero();
		assertEquals(1, hero.getX()); assertEquals(1, hero.getY()); 
		game.startGame('s');
		assertEquals(1, game.getNewHeroX()); assertEquals(2, game.getNewHeroY()); 		
	}
	
	@Test
	public void testHeroBlockedByWall(){
		char currentMap[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X'}, { 'X', 'H', ' ', 'G', 'X'},	
			{ 'I', ' ', ' ', ' ', 'X'}, { 'I', 'k', ' ', ' ', 'X'} , { 'X', 'X', 'X', 'X', 'X'} }; //mapa de testes
		GameMap game = new GameMap(currentMap, false,0, false);
		game.readMap(false);
		Person hero = game.getHero();
		assertEquals(1, hero.getX()); assertEquals(1, hero.getY()); 
		game.startGame('w');
		assertEquals(1, game.getNewHeroX()); assertEquals(1, game.getNewHeroY());  
	}
	
	@Test
	public void testMoveHeroToOgre() {
		char currentMap[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X'}, { 'X', 'H', ' ', 'O', 'X'},	
			{ 'I', ' ', ' ', ' ', 'X'}, { 'I', 'k', ' ', ' ', 'X'} , { 'X', 'X', 'X', 'X', 'X'} }; //mapa de testes
		GameMap game = new GameMap(currentMap, false,0, false);
		game.readMap(false);
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
		GameMap game = new GameMap(currentMap, false,0, false);
		game.readMap(false);
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
		GameMap game = new GameMap(currentMap, false,0, false);
		game.readMap(false);
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
		GameMap game = new GameMap(currentMap, false,0, false);
		game.readMap(false);
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
		GameMap game = new GameMap(currentMap, false,0, false);
		game.readMap(false);
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
	
	@Test
	public void testMaps() {
		char map0[][] = new char[][] {{' '}};
		char map1[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
				{ 'X', 'H', ' ', ' ', 'I', ' ', 'X', ' ', 'G', 'X' }, { 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
				{ 'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X' }, { 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
				{ 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', ' ', 'X' }, { 'X', ' ', 'I', ' ', 'I', ' ', 'X', 'k', ' ', 'X' },
				{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } }; // mapa do
																		// nivel 1
		char map2[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
				{ 'I', ' ', ' ', ' ', 'O', '*', ' ', 'k', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', 'H', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } }; // mapa
																													// do
																													// nivel
																													// 2

		char map3[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
				{ 'I', ' ', ' ', ' ', ' ', ' ', ' ', 'k', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', 'A', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } }; // mapa
																													// do
		AssertEquals(Maps.getMap(0), map0);
		AssertEquals(Maps.getMap(1), map1);
		AssertEquals(Maps.getMap(2), map2);
		AssertEquals(Maps.getMap(3), map3);
		Maps.createNewMap(6, 6);
		assertFalse(Maps.validateElement(0, 0, 'X'));
		assertTrue(Maps.validateElement(1, 1, 'X'));
		assertFalse(Maps.validateElement(0, 0, 'H'));
		assertFalse(Maps.validateElement(0, 0, 'G'));
		assertFalse(Maps.validateElement(0, 0, 'A'));
		assertFalse(Maps.validateElement(0, 0, ' '));
		assertFalse(Maps.validateElement(0, 0, 'O'));
		assertFalse(Maps.validateElement(0, 0, 'I'));
		assertFalse(Maps.validateElement(1, 0, 'I'));
		assertTrue(Maps.validateElement(1, 1, 'H'));
		assertFalse(Maps.changeNewMap(1, 1, 'H'));
		assertTrue(Maps.changeNewMap(1, 4, 'H'));
		assertTrue(Maps.changeNewMap(1, 0, 'I'));
		assertTrue(Maps.changeNewMap(0, 1, 'I'));
		assertFalse(Maps.instantaneousDoorOpen(0));
		assertTrue(Maps.instantaneousDoorOpen(1));
		assertFalse(Maps.hasMultipleOgre(0));
		assertFalse(Maps.hasMultipleOgre(1));
		assertFalse(Maps.hasMultipleOgre(4));
		assertFalse(Maps.instantaneousDoorOpen(4));
	}

	private boolean AssertEquals(char[][] map, char[][] map0) {
		for (int i = 0 ; i < map.length ; i++){
			for (int j = 0 ; j < map[0].length ; j++){
				if (map[i][j] != map0[i][j])
					return false;
			}
		}
		return true;
	}
}
