package dkeep.test;

import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.*;
import dkeep.logic.Guard.Personality;
public class TestDungeonGameLogic {
	
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
	public void testHeroLosesByGuard(){
		char currentMap[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X'}, { 'X', 'H', ' ', 'G', 'X'},	
			{ 'I', ' ', ' ', ' ', 'X'}, { 'I', 'k', ' ', ' ', 'X'} , { 'X', 'X', 'X', 'X', 'X'} }; //mapa de testes
		GameMap game = new GameMap(currentMap, false, false);
		game.readMap();
		Person hero = game.getHero();
		assertEquals(1, hero.getX()); assertEquals(1, hero.getY()); 
		game.startGame('d');
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		assertTrue(game.isEndOfGame());	 
	}
	
	@Test
	public void testHeroTriesToExit() {
		char currentMap[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X'}, { 'X', 'H', ' ', 'G', 'X'},	
			{ 'I', ' ', ' ', ' ', 'X'}, { 'I', 'k', ' ', ' ', 'X'} , { 'X', 'X', 'X', 'X', 'X'} }; //mapa de testes
		GameMap game = new GameMap(currentMap, false, false);
		Person hero = game.getHero();
		game.readMap();
		assertEquals(1, game.getNewHeroX()); assertEquals(1, game.getNewHeroY()); 
		game.startGame('s');
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		game.startGame('a');
		assertEquals(1, game.getNewHeroX()); assertEquals(2, game.getNewHeroY());
		assertFalse(game.isEndOfGame());
	}
	
	
	@Test
	public void testHeroTriesToExitAndPasses() {
		char currentMap[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X'}, { 'X', 'H', ' ', 'G', 'X'},	
			{ 'I', ' ', ' ', ' ', 'X'}, { 'I', 'k', ' ', ' ', 'X'} , { 'X', 'X', 'X', 'X', 'X'} }; //mapa de testes
		GameMap game = new GameMap(currentMap, false, true);
		game.readMap();
		Person hero = game.getHero();
		assertEquals(1, game.getNewHeroX()); assertEquals(1, game.getNewHeroY()); 
		game.startGame('s');
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		game.startGame('s');
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		game.startGame('a');
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		assertTrue(game.isEndOfGame());	
	}
	
	@Test
	public void testDoorsOpen() {
		char currentMap[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X'}, { 'X', 'H', ' ', 'G', 'X'},	
			{ 'I', ' ', ' ', ' ', 'X'}, { 'I', 'k', ' ', ' ', 'X'} , { 'X', 'X', 'X', 'X', 'X'} }; //mapa de testes
		GameMap game = new GameMap(currentMap, false, true);
		game.readMap();
		Person hero = game.getHero();
		assertEquals(1, game.getNewHeroX()); assertEquals(1, game.getNewHeroY()); 
		game.startGame('s');
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		game.startGame('s');
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		assertEquals('S',game.getMapPos(2,0));	
		assertEquals('S',game.getMapPos(3,0));	
	}
	
	@Test
	public void testPositioningSave() {
		char currentMap[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X'}, { 'X', 'H', ' ', 'G', 'X'},	
			{ 'I', ' ', ' ', ' ', 'X'}, { 'I', 'k', ' ', ' ', 'X'} , { 'X', 'X', 'X', 'X', 'X'} }; //mapa de testes
		GameMap game = new GameMap(currentMap, false, false);
		game.readMap();
		Person hero = game.getHero();
		assertEquals(1, hero.getX()); assertEquals(1, hero.getY()); 
		game.startGame('s');
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		assertEquals(1, hero.getX()); assertEquals(2, hero.getY());
		assertEquals('G',game.getGuard().getCh());
		game.getCurrentMap().setPosUsed(game.getCurrentMap().getKey().getX(),game.getCurrentMap().getKey().getY());
		assertTrue(game.getCurrentMap().isElementAtPos(game.getCurrentMap().getKey().getX(),game.getCurrentMap().getKey().getY()));
		game.getCurrentMap().clearPosUsed();
		for (int i = 0; i < game.getCurrentMap().getMapHeight(); i++) {
			for (int j = 0; j < game.getCurrentMap().getMapWidth(); j++) {
				assertFalse(game.getCurrentMap().isElementAtPos(i,j));
			}
		}
	}
	
	@Test
	public void testWalls() {
		char currentMap[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X'}, { 'X', 'H', ' ', 'G', 'X'},	
			{ 'I', ' ', ' ', ' ', 'X'}, { 'I', 'k', ' ', ' ', 'X'} , { 'X', 'X', 'X', 'X', 'X'} }; //mapa de testes
		GameMap game = new GameMap(currentMap, false, false);
		game.readMap();
		assertTrue(game.getCurrentMap().isAboveWall(0,0));	
		assertTrue(game.getCurrentMap().isAboveWall(1,0));	
		assertTrue(game.getCurrentMap().isAboveWall(2,0));	
		assertTrue(game.getCurrentMap().isAboveWall(3,0));	
		assertTrue(game.getCurrentMap().isAboveWall(4,0));	
		assertTrue(game.getCurrentMap().isAboveWall(0,1));	
		assertTrue(game.getCurrentMap().isAboveWall(0,4));	
		assertTrue(game.getCurrentMap().isAboveWall(1,4));	
		assertTrue(game.getCurrentMap().isAboveWall(2,4));	
		assertTrue(game.getCurrentMap().isAboveWall(3,4));	
		assertTrue(game.getCurrentMap().isAboveWall(4,4));	
		assertTrue(game.getCurrentMap().isAboveWall(4,3));	
		assertTrue(game.getCurrentMap().isAboveWall(4,2));	
		assertTrue(game.getCurrentMap().isAboveWall(4,1));	
	}
}
