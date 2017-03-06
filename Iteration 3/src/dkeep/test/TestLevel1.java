package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.*;
public class TestLevel1 {
	
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
		game.startGame('s');
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		assertEquals('K',hero.getCh());	
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
		game.startGame('s');
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		game.startGame('a');
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
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
		game.startGame('s');
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		game.startGame('a');
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		game.startGame('a');
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
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