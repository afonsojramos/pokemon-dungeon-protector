package dkeep.test;

import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.*;
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
		game.readMap();
		assertEquals(1, game.getNewHeroX()); assertEquals(1, game.getNewHeroY()); 
		game.startGame('s');
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
		assertEquals(1, game.getNewHeroX()); assertEquals(1, game.getNewHeroY()); 
		game.startGame('s');
		game.startGame('s');
		//Chave não está a ser apanhada
		game.startGame('a');
		assertTrue(game.isEndOfGame());	
	}
	/*
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
