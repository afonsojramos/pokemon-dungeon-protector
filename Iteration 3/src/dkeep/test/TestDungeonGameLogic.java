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
		assertEquals(2, game.getNewHeroX()); assertEquals(1, game.getNewHeroY());  
		assertTrue(game.isEndOfGame());	 
	}
	
	@Test
	public void testHeroLosesByGuardAbove(){
		char currentMap[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X'}, { 'X', 'H', ' ', 'G', 'X'},	
			{ 'I', ' ', ' ', ' ', 'X'}, { 'I', 'k', ' ', ' ', 'X'} , { 'X', 'X', 'X', 'X', 'X'} }; //mapa de testes
		GameMap game = new GameMap(currentMap, false, false);
		game.readMap();
		Person hero = game.getHero();
		assertEquals(1, hero.getX()); assertEquals(1, hero.getY()); 
		game.startGame('s');
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		assertEquals(1, game.getNewHeroX()); assertEquals(2, game.getNewHeroY());  
		game.startGame('d');
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		assertEquals(2, game.getNewHeroX()); assertEquals(2, game.getNewHeroY());  
		game.startGame('d');
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		assertEquals(3, game.getNewHeroX()); assertEquals(2, game.getNewHeroY());  
		assertTrue(game.isEndOfGame());	 
	}
	
	@Test
	public void testHeroTriesToExitWithDoorsClosed() {
		char currentMap[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X'}, { 'X', 'H', ' ', 'G', 'X'},	
			{ 'I', ' ', ' ', ' ', 'X'}, { 'I', 'k', ' ', ' ', 'X'} , { 'X', 'X', 'X', 'X', 'X'} }; //mapa de testes
		GameMap game = new GameMap(currentMap, false, false);
		Person hero = game.getHero();
		game.readMap();
		assertEquals(1, game.getNewHeroX()); assertEquals(1, game.getNewHeroY()); 
		game.startGame('s');
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		assertEquals(1, game.getNewHeroX()); assertEquals(2, game.getNewHeroY());  
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
		assertEquals(1, game.getNewHeroX()); assertEquals(2, game.getNewHeroY());  
		game.startGame('s');
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		assertEquals(1, game.getNewHeroX()); assertEquals(3, game.getNewHeroY());  
		assertFalse(game.getCurrentMap().isElementAtPos(game.getCurrentMap().getKey().getX(),game.getCurrentMap().getKey().getY()));
		game.startGame('a');
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		assertEquals(0, game.getNewHeroX()); assertEquals(3, game.getNewHeroY()); 
		assertEquals('S',game.getMapPos(2,0));	
		assertEquals('S',game.getMapPos(3,0));
		assertTrue(game.isEndOfGame());	
		assertTrue(game.isVictory());	
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
		assertEquals(1, game.getNewHeroX()); assertEquals(2, game.getNewHeroY());  
		game.startGame('s');
		assertFalse(game.getCurrentMap().isElementAtPos(game.getCurrentMap().getKey().getX(),game.getCurrentMap().getKey().getY()));
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		assertEquals(1, game.getNewHeroX()); assertEquals(3, game.getNewHeroY());  
		assertEquals('S',game.getMapPos(2,0));	
		assertEquals('S',game.getMapPos(3,0));	
		assertFalse(game.isEndOfGame());
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
		assertEquals('I',game.getMapPos(2,0));	
		assertEquals('I',game.getMapPos(3,0));
		assertTrue(game.getCurrentMap().isAboveKey(1, 3));
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
		assertEquals('I',game.getMapPos(2,0));	
		assertEquals('I',game.getMapPos(3,0));
		assertTrue(game.getCurrentMap().isAboveKey(1, 3));
	}
	
	@Test
	public void testPreviousPositionOfGuard(){
		char currentMap[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X'}, { 'X', 'H', ' ', 'G', 'X'},	
			{ 'I', ' ', ' ', ' ', 'X'}, { 'I', 'k', ' ', ' ', 'X'} , { 'X', 'X', 'X', 'X', 'X'} }; //mapa de testes
		GameMap game = new GameMap(currentMap, false, false);
		game.readMap();
		Person hero = game.getHero();
		assertEquals(1, game.getNewHeroX()); assertEquals(1, game.getNewHeroY()); 
		game.startGame('s');
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		assertEquals(3, game.getCharacters().get(0).getPrevX()); assertEquals(1, game.getCharacters().get(0).getPrevY()); 
		game.startGame('f');
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		assertEquals(3, game.getCharacters().get(0).getPrevX()); assertEquals(1, game.getCharacters().get(0).getPrevY());
	}
	
	@Test
	public void testHeroAdjacentToGuard(){
		char currentMap[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X'}, { 'X', 'H', ' ', 'G', 'X'},	
			{ 'I', ' ', ' ', ' ', 'X'}, { 'I', 'k', ' ', ' ', 'X'} , { 'X', 'X', 'X', 'X', 'X'} }; //mapa de testes
		GameMap game = new GameMap(currentMap, false, false);
		game.readMap();
		Person hero = game.getHero();
		assertFalse(game.getCharacters().get(0).isAdjacent(hero.getX(), hero.getY(), game.getCharacters().get(0).getPrevX(), game.getCharacters().get(0).getPrevY()));
		assertEquals(1, hero.getX()); assertEquals(1, hero.getY()); 
		game.startGame('d');
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		assertTrue(game.getCharacters().get(0).isAdjacent(hero.getX(), hero.getY(), game.getCharacters().get(0).getPrevX(), game.getCharacters().get(0).getPrevY()));
		assertTrue(game.getCharacters().get(0).isAdjacent(game.getCharacters().get(0).getPrevX(), game.getCharacters().get(0).getPrevY(), hero.getX(), hero.getY()));
		assertTrue(game.getCharacters().get(0).isAdjacent(hero.getX(), hero.getY(), hero.getX(), hero.getY()));
	}
	
	@Test
	public void testNameChanging(){
		char currentMap[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X'}, { 'X', 'H', ' ', 'G', 'X'},	
			{ 'I', ' ', ' ', ' ', 'X'}, { 'I', 'k', ' ', ' ', 'X'} , { 'X', 'X', 'X', 'X', 'X'} }; //mapa de testes
		GameMap game = new GameMap(currentMap, false, false);
		game.readMap();
		Person hero = game.getHero();
		game.getGuard().setName("wow");
		assertEquals("wow",game.getGuard().getName());
		hero.setName("wow");
		assertEquals("wow",hero.getName());
		game.getGuard().setName("guard");
		assertEquals("guard",game.getGuard().getName());
		hero.setName("hero");
		assertEquals("hero",hero.getName());
		hero.setCh('M');
		assertEquals('M',hero.getCh());
		game.getGuard().setCh('M');
		assertEquals('M',game.getGuard().getCh());
		hero.setCh('H');
		assertEquals('H',hero.getCh());
		game.getGuard().setCh('G');
		assertEquals('G',game.getGuard().getCh());
	}
	
	@Test
	public void testHeroLoseToMovingGuard(){
		char currentMap[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X'}, { 'X', 'A', ' ', 'G', 'X'},	
			{ 'I', ' ', ' ', ' ', 'X'}, { 'I', 'k', ' ', ' ', 'X'} , { 'X', 'X', 'X', 'X', 'X'} }; //mapa de testes
		GameMap game = new GameMap(currentMap, false, false);
		game.readMap();
		Person hero = game.getHero();
		assertEquals(1, game.getNewHeroX()); assertEquals(1, game.getNewHeroY()); 
		game.startGame('s');
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		if (game.getCharacters().get(0) instanceof Guard){
			Guard g = (Guard) game.getCharacters().get(0);
			assertFalse(g.isInInvalidPos(currentMap));
			g.setX(2);
			g.setY(1);
			assertFalse(g.isInInvalidPos(currentMap));
			assertFalse(game.isEndOfGame());
			g.setX(2);
			g.setY(2);
			g.setPersonality(Personality.Obedient);
			assertFalse(g.isInInvalidPos(currentMap));
			assertTrue(game.isEndOfGame());
			hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
		}
	}
	
	@Test
	public void testGuardFollowingPath(){
		char currentMap[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ 'X', 'H', ' ', ' ', 'I', ' ', 'X', ' ', 'G', 'X' }, { 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
			{ 'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X' }, { 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
			{ 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', ' ', 'X' }, { 'X', ' ', 'I', ' ', 'I', ' ', 'X', 'k', ' ', 'X' },
			{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } }; // mapa do
		GameMap game = new GameMap(currentMap, false, false);
		game.readMap();
		Person hero = game.getHero();
		if (game.getCharacters().get(0) instanceof Guard){
			Guard g = (Guard) game.getCharacters().get(0);
			assertFalse(g.isInInvalidPos(currentMap));		
			g.setPersonality(Personality.Rookie);
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			g.setPersonality(Personality.Drunken);
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.setPersonality(Personality.Suspicious);
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
			g.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
			game.update();
			assertFalse(g.isInInvalidPos(currentMap));
		}			
	}																
}
