package dkeep.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import dkeep.logic.GameMap;
import dkeep.logic.Maps;
import dkeep.logic.Ogre;
import dkeep.logic.Person;

public class TestOgreLevel {
	/**
	 * testa movimento do heroi para celula livre
	 */
	@Test
	public void testMoveHeroToFreeCell() {
		char currentMap[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X'}, { 'X', 'H', ' ', 'G', 'X'},	
			{ 'I', ' ', ' ', ' ', 'X'}, { 'I', 'k', ' ', ' ', 'X'} , { 'X', 'X', 'X', 'X', 'X'} }; //mapa de testes
		GameMap game = new GameMap(currentMap, false,0, false);
		game.readMap(false);
		Person hero = game.getHero();
		assertEquals(1, hero.getX()); assertEquals(1, hero.getY()); 
		assertTrue(game.startGame('s'));
		assertEquals(1, game.getNewHeroX()); assertEquals(2, game.getNewHeroY()); 		
	}
	/**
	 * testa se o heroi nao se pode mexer devido a existencia de uma parede
	 */
	@Test
	public void testHeroBlockedByWall(){
		char currentMap[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X'}, { 'X', 'H', ' ', 'G', 'X'},	
			{ 'I', ' ', ' ', ' ', 'X'}, { 'I', 'k', ' ', ' ', 'X'} , { 'X', 'X', 'X', 'X', 'X'} }; //mapa de testes
		GameMap game = new GameMap(currentMap, false,0, false);
		game.readMap(false);
		Person hero = game.getHero();
		assertEquals(1, hero.getX()); assertEquals(1, hero.getY()); 
		assertFalse(game.startGame('w'));
		assertEquals(1, game.getNewHeroX()); assertEquals(1, game.getNewHeroY());  
	}
	/**
	 * testa se heroi perde por causa do ogre
	 */
	@Test
	public void testMoveHeroToOgre() {
		char currentMap[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X'}, { 'X', 'H', ' ', 'O', 'X'},	
			{ 'I', ' ', ' ', ' ', 'X'}, { 'I', 'k', ' ', ' ', 'X'} , { 'X', 'X', 'X', 'X', 'X'} }; //mapa de testes
		GameMap game = new GameMap(currentMap, false,0, false);
		game.readMap(false);
		Person hero = game.getHero();
		assertEquals(1, game.getNewHeroX()); assertEquals(1, game.getNewHeroY()); 
		assertTrue(game.startGame('d'));
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		assertEquals(2, game.getNewHeroX()); assertEquals(1, game.getNewHeroY());
		assertTrue(game.isEndOfGame());	 		
	}
	/**
	 * testa se heroi altera a sua condicao de chave apanhada
	 */
	@Test
	public void testHeroChangeToK() {
		char currentMap[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X'}, { 'X', 'H', ' ', 'O', 'X'},	
			{ 'I', ' ', ' ', ' ', 'X'}, { 'I', 'k', ' ', ' ', 'X'} , { 'X', 'X', 'X', 'X', 'X'} }; //mapa de testes
		GameMap game = new GameMap(currentMap, false,0, false);
		game.readMap(false);
		Person hero = game.getHero();
		assertEquals(1, game.getNewHeroX()); assertEquals(1, game.getNewHeroY()); 
		assertTrue(game.startGame('s'));
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		assertEquals(1, game.getNewHeroX()); assertEquals(2, game.getNewHeroY()); 
		assertTrue(game.startGame('s'));
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		assertEquals(1, game.getNewHeroX()); assertEquals(3, game.getNewHeroY()); 
		assertEquals('K',hero.getCh());	
		assertFalse(game.isEndOfGame());
	}
	/**
	 * testa se a tentativa de o heroi acabar o nivel e falhada
	 */
	@Test
	public void testHeroFailToExit(){
		char currentMap[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X'}, { 'X', 'H', ' ', 'O', 'X'},	
			{ 'I', ' ', ' ', ' ', 'X'}, { 'I', 'k', ' ', ' ', 'X'} , { 'X', 'X', 'X', 'X', 'X'} }; //mapa de testes
		GameMap game = new GameMap(currentMap, false,0, false);
		game.readMap(false);
		Person hero = game.getHero();
		assertEquals(1, game.getNewHeroX()); assertEquals(1, game.getNewHeroY()); 
		assertTrue(game.startGame('s'));
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		assertFalse(game.startGame('a'));
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		assertFalse(game.isEndOfGame());		
	}
	/**
	 * testa se o heroi abre as portas
	 */
	@Test
	public void testHeroSucceedsToOpenDoor(){
		char currentMap[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X'}, { 'X', 'H', ' ', 'O', 'X'},	
			{ 'I', ' ', ' ', ' ', 'X'}, { 'I', 'k', ' ', ' ', 'X'} , { 'X', 'X', 'X', 'X', 'X'} }; //mapa de testes
		GameMap game = new GameMap(currentMap, false,0, false);
		game.readMap(false);
		Person hero = game.getHero();
		assertEquals(1, game.getNewHeroX()); assertEquals(1, game.getNewHeroY()); 
		assertTrue(game.startGame('s'));
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		assertEquals(1, game.getNewHeroX()); assertEquals(2, game.getNewHeroY()); 
		assertTrue(game.startGame('s'));
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		assertEquals(1, game.getNewHeroX()); assertEquals(3, game.getNewHeroY()); 
		assertEquals('K',hero.getCh());	
		assertTrue(game.startGame('a'));
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		assertEquals(0, game.getNewHeroX()); assertEquals(3, game.getNewHeroY()); 
		assertEquals('S',game.getMapPos(2,0));
		assertEquals('S',game.getMapPos(3,0));		
	}
	/**
	 * testa se o heroi consegue abrir as porta e depois sair/acabar o nivel
	 */
	@Test
	public void testHeroSucceedsToOpenDoorAndExits() {
		char currentMap[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X'}, { 'X', 'H', ' ', 'O', 'X'},	
			{ 'I', ' ', ' ', 'O', 'X'}, { 'I', 'k', ' ', ' ', 'X'} , { 'X', 'X', 'X', 'X', 'X'} }; //mapa de testes
		GameMap game = new GameMap(currentMap, false,0, false);
		game.readMap(false);
		Person hero = game.getHero();
		assertEquals(1, game.getNewHeroX()); assertEquals(1, game.getNewHeroY()); 
		assertTrue(game.startGame('s'));
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		assertEquals(1, game.getNewHeroX()); assertEquals(2, game.getNewHeroY()); 
		assertTrue(game.startGame('s'));
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		assertEquals(1, game.getNewHeroX()); assertEquals(3, game.getNewHeroY());
		assertEquals('K',hero.getCh());	
		assertTrue(game.startGame('a'));
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		assertEquals('S',game.getMapPos(2,0));
		assertEquals('S',game.getMapPos(3,0));
		assertEquals(0, game.getNewHeroX()); assertEquals(3, game.getNewHeroY());
		assertEquals('K',hero.getCh());	
		assertTrue(game.startGame('a'));
		hero.doStep(game.getCurrentMap(), game.getNewHeroX(), game.getNewHeroY());
		assertEquals(0, game.getNewHeroX()); assertEquals(3, game.getNewHeroY());
		assertTrue(game.isEndOfGame());	
	}
	/**
	 * testa se o heroi da stun ao ogre
	 */
	@Test
	public void testStunOgresAndClub(){
		char currentMap[][] = new char[][] { { ' ', 'A', ' '}, { ' ', 'O', ' '},	
			{ ' ', ' ', ' '}}; //mapa de testes
		char currentMap1[][] = new char[][] { { ' ', 'A', ' '}, { ' ', 'O', ' '},	
			{ ' ', ' ', ' '}}; //mapa de testes
		GameMap game = new GameMap(currentMap, false,0, false);
		game.readMap(false);
		game.StunOgres();
		game.restartVariables();
		game.changeMapArray(currentMap1);
		Person hero = game.getHero();
		Ogre ogre = (Ogre)game.getCharacters().get(0);
		ogre.lessStuned();
		ogre.doStep(game.getCurrentMap(), 0, 1);
		assertFalse(hero.isAdjacent(0, 0, 0, 0));
		assertFalse(game.startGame('s'));
		hero.setX(1);
		hero.setY(1);
		assertTrue(ogre.isAdjacent(ogre.getX(), ogre.getY(), hero.getX(), hero.getY()));
	}
	/**
	 * testa se sao criados varios ogres
	 */
	@Test
	public void testMultipleOgres(){
		char map3[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ 'I', ' ', ' ', ' ', ' ', ' ', ' ', 'k', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', 'A', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };
		GameMap game = new GameMap(map3, false,0, false);
		game.readMap(false);
		game.addOgreToLevel();
		game.addOgreToLevel();
		char map4[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ 'I', ' ', ' ', ' ', ' ', ' ', ' ', 'k', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', 'A', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };
		GameMap game1 = new GameMap(map4, false,0, false);
		game1.readMap(true);
	}
	/**
	 * testa o mapa de jogo do nivel 3
	 */
	@Test
	public void testLevel3MultipleOgres(){
		char map3[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ 'I', ' ', ' ', ' ', ' ', ' ', ' ', 'k', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', 'A', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };
		GameMap game = new GameMap(map3, true,0, false);
		game.readMap(false);
	}
	/**
	 * testa os mapas de jogo guardados
	 */
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
				{ 'X', 'A', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } }; 	
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
		assertFalse(Maps.changeNewMap(0, 1, ' '));
		assertTrue(Maps.changeNewMap(0, 1, 'S'));
		assertFalse(Maps.instantaneousDoorOpen(0));
		assertTrue(Maps.instantaneousDoorOpen(1));
		assertFalse(Maps.hasMultipleOgre(0));
		assertFalse(Maps.hasMultipleOgre(1));
		assertFalse(Maps.hasMultipleOgre(4));
		assertFalse(Maps.instantaneousDoorOpen(4));
		Maps.setCurrentLevel(1);
		Maps.setFinalLevel(2);
		assertEquals(Maps.getCurrentLevel(),1);
		assertEquals(Maps.getFinalLevel(),2);
		Maps.setCurrentLevel(3);
		Maps.setFinalLevel(10);
		assertEquals(Maps.getCurrentLevel(),3);
		assertEquals(Maps.getFinalLevel(),10);
	}

	private boolean AssertEquals(char[][] map, char[][] map0) {
		for (int i = 0 ; i < map.length ; i++){
			for (int j = 0 ; j < map[0].length ; j++){
				assertEquals(map[i][j], map0[i][j]);
			}
		}
		return true;
	}
}
