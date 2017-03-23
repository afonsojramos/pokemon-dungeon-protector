package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.*;
public class TestOgreRandomness {
	@Test
	public void TestOgreRandomMove(){
		char currentMap[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X'}, { 'X', 'H', ' ', 'O', 'X'},	
			{ 'I', ' ', ' ', ' ', 'X'}, { 'I', 'k', ' ', ' ', 'X'} , { 'X', 'X', 'X', 'X', 'X'} }; //mapa de testes
		int x, y;
		GameMap game = new GameMap(currentMap, false,0, false);
		game.readMap(false);
		int possibleX1 = 2, possibleY1 = 1, possibleX2 = 3, possibleY2 = 2;
		MapLevel map = game.getCurrentMap();
		Ogre ogre = (Ogre)game.getCharacters().get(0);
		ogre.doStep(map, 0, 0);
		x = ogre.getX(); y = ogre.getY();
		boolean result = ((possibleX1 == x && possibleY1 == y) || (possibleX2 == x && possibleY2 == y));
		assertTrue(result);
		assertFalse(ogre.isInInvalidPos(currentMap));
		game.update();	
		ogre.doStep(map, 0, 0);
		x = ogre.getX(); y = ogre.getY();
		assertTrue(result);
		assertFalse(ogre.isInInvalidPos(currentMap));
		game.update();	
		ogre.doStep(map, 0, 0);
		x = ogre.getX(); y = ogre.getY();
		assertTrue(result);
		assertFalse(ogre.isInInvalidPos(currentMap));
		game.update();	
		ogre.doStep(map, 0, 0);
		x = ogre.getX(); y = ogre.getY();
		assertTrue(result);
		assertFalse(ogre.isInInvalidPos(currentMap));
		game.update();	
		ogre.doStep(map, 0, 0);
		x = ogre.getX(); y = ogre.getY();
		assertTrue(result);
		assertFalse(ogre.isInInvalidPos(currentMap));
		game.update();	
		ogre.doStep(map, 0, 0);
		x = ogre.getX(); y = ogre.getY();
		assertTrue(result);
		assertFalse(ogre.isInInvalidPos(currentMap));
		game.update();	
		ogre.doStep(map, 0, 0);
		x = ogre.getX(); y = ogre.getY();
		assertTrue(result);
		assertFalse(ogre.isInInvalidPos(currentMap));
		game.update();	
		ogre.doStep(map, 0, 0);
		x = ogre.getX(); y = ogre.getY();
		assertTrue(result);
		assertFalse(ogre.isInInvalidPos(currentMap));
		game.update();	
		ogre.doStep(map, 0, 0);
		x = ogre.getX(); y = ogre.getY();
		assertTrue(result);
		assertFalse(ogre.isInInvalidPos(currentMap));
		game.update();	
		ogre.doStep(map, 0, 0);
		x = ogre.getX(); y = ogre.getY();
		assertTrue(result);
		assertFalse(ogre.isInInvalidPos(currentMap));
		game.update();	
		ogre.doStep(map, 0, 0);
		x = ogre.getX(); y = ogre.getY();
		assertTrue(result);
		assertFalse(ogre.isInInvalidPos(currentMap));
		game.update();	
		ogre.doStep(map, 0, 0);
		x = ogre.getX(); y = ogre.getY();
		assertTrue(result);
		assertFalse(ogre.isInInvalidPos(currentMap));
		game.update();	
		ogre.stun();
		assertTrue(ogre.isStuned());
		x = ogre.getX(); y = ogre.getY();
		assertTrue(result);
		ogre.lessStuned();
		ogre.lessStuned();
		assertFalse(ogre.isStuned());
	}
	
	@Test
	public void testClubPositions(){
		char currentMap0[][] = new char[][] { { 'X', 'O', 'X'}, { 'X', ' ', 'X'},	
			{ 'X', 'X', 'X'}}; //mapa de testes
		GameMap game = new GameMap(currentMap0, false,0, false);
		game.readMap(false);
		Ogre ogre = (Ogre)game.getCharacters().get(0);
		assertEquals(ogre.getClubCh(),'*');
		assertTrue(ogre.isClubAdjacent(ogre.getX(), ogre.getY()));
		assertEquals(ogre.isClubVisible(game.getCurrentMap()), true);
		char currentMap01[][] = new char[][] { { 'X', 'O', 'X'}, { 'X', ' ', 'X'},	
			{ 'X', 'X', 'X'}}; //mapa de testes
		GameMap game01 = new GameMap(currentMap01, false,0, false);
		game01.readMap(false);
		Ogre ogre01 = (Ogre)game01.getCharacters().get(0);
		assertEquals(ogre01.getClubCh(),'*');
		assertTrue(ogre01.isClubAdjacent(ogre01.getX(), ogre01.getY()));
		assertEquals(ogre01.isClubVisible(game01.getCurrentMap()), true);
		char currentMap02[][] = new char[][] { { 'X', 'O', 'X'}, { 'X', ' ', 'X'},	
			{ 'X', 'X', 'X'}}; //mapa de testes
		GameMap game02 = new GameMap(currentMap02, false,0, false);
		game02.readMap(false);
		Ogre ogre02 = (Ogre)game02.getCharacters().get(0);
		assertEquals(ogre02.getClubCh(),'*');
		assertTrue(ogre02.isClubAdjacent(ogre02.getX(), ogre02.getY()));
		assertEquals(ogre02.isClubVisible(game02.getCurrentMap()), true);
		
		char currentMap1[][] = new char[][] { { 'X', ' ', 'X'}, { 'X', 'O', 'X'},	
			{ 'X', 'X', 'X'}}; //mapa de testes
		GameMap game1 = new GameMap(currentMap1, false,0, false);
		game1.readMap(false);
		Ogre ogre1 = (Ogre)game1.getCharacters().get(0);
		assertEquals(ogre1.getClubCh(),'*');
		assertTrue(ogre1.isClubAdjacent(ogre1.getX(), ogre1.getY()));
		assertEquals(ogre1.isClubVisible(game1.getCurrentMap()), true);
		char currentMap11[][] = new char[][] { { 'X', ' ', 'X'}, { 'X', 'O', 'X'},	
			{ 'X', 'X', 'X'}}; //mapa de testes
		GameMap game11 = new GameMap(currentMap11, false,0, false);
		game11.readMap(false);
		Ogre ogre11 = (Ogre)game11.getCharacters().get(0);
		assertEquals(ogre11.getClubCh(),'*');
		assertTrue(ogre11.isClubAdjacent(ogre11.getX(), ogre11.getY()));
		assertEquals(ogre11.isClubVisible(game11.getCurrentMap()), true);
		char currentMap12[][] = new char[][] { { 'X', ' ', 'X'}, { 'X', 'O', 'X'},	
			{ 'X', 'X', 'X'}}; //mapa de testes
		GameMap game12 = new GameMap(currentMap12, false,0, false);
		game12.readMap(false);
		Ogre ogre12 = (Ogre)game12.getCharacters().get(0);
		assertEquals(ogre12.getClubCh(),'*');
		assertTrue(ogre12.isClubAdjacent(ogre12.getX(), ogre12.getY()));
		assertEquals(ogre12.isClubVisible(game12.getCurrentMap()), true);

		char currentMap2[][] = new char[][] { { 'X', 'X', 'X'}, { 'O', ' ', 'X'},	
			{ 'X', 'X', 'X'}}; //mapa de testes
		GameMap game2 = new GameMap(currentMap2, false,0, false);
		game2.readMap(false);
		Ogre ogre2 = (Ogre)game2.getCharacters().get(0);
		game2.StunOgres();
		assertEquals(ogre2.getClubCh(),'*');
		assertTrue(ogre2.isClubAdjacent(ogre2.getX(), ogre2.getY()));
		assertEquals(ogre2.isClubVisible(game2.getCurrentMap()), true);
		char currentMap21[][] = new char[][] { { 'X', 'X', 'X'}, { 'O', ' ', 'X'},	
			{ 'X', 'X', 'X'}}; //mapa de testes
		GameMap game21 = new GameMap(currentMap21, false,0, false);
		game21.readMap(false);
		Ogre ogre21 = (Ogre)game21.getCharacters().get(0);
		game21.StunOgres();
		assertEquals(ogre21.getClubCh(),'*');
		assertTrue(ogre21.isClubAdjacent(ogre21.getX(), ogre21.getY()));
		assertEquals(ogre21.isClubVisible(game21.getCurrentMap()), true);
		char currentMap22[][] = new char[][] { { 'X', 'X', 'X'}, { 'O', ' ', 'X'},	
			{ 'X', 'X', 'X'}}; //mapa de testes
		GameMap game22 = new GameMap(currentMap22, false,0, false);
		game22.readMap(false);
		Ogre ogre22 = (Ogre)game22.getCharacters().get(0);
		game22.StunOgres();
		assertEquals(ogre22.getClubCh(),'*');
		assertTrue(ogre22.isClubAdjacent(ogre22.getX(), ogre22.getY()));
		assertEquals(ogre22.isClubVisible(game22.getCurrentMap()), true);
		
		char currentMap3[][] = new char[][] { { 'X', 'X', 'X'}, { ' ', 'O', 'X'},	
			{ 'X', 'X', 'X'}}; //mapa de testes
		GameMap game3 = new GameMap(currentMap3, false,0, false);
		game3.readMap(false);
		Ogre ogre3 = (Ogre)game3.getCharacters().get(0);
		game3.StunOgres();
		assertEquals(ogre3.getClubCh(),'*');
		assertTrue(ogre3.isClubAdjacent(ogre3.getX(), ogre3.getY()));
		assertEquals(ogre3.isClubVisible(game3.getCurrentMap()), true);
		char currentMap31[][] = new char[][] { { 'X', 'X', 'X'}, { ' ', 'O', 'X'},	
			{ 'X', 'X', 'X'}}; //mapa de testes
		GameMap game31 = new GameMap(currentMap31, false,0, false);
		game31.readMap(false);
		Ogre ogre31 = (Ogre)game31.getCharacters().get(0);
		game31.StunOgres();
		assertEquals(ogre31.getClubCh(),'*');
		assertTrue(ogre31.isClubAdjacent(ogre31.getX(), ogre31.getY()));
		assertEquals(ogre31.isClubVisible(game31.getCurrentMap()), true);
		char currentMap32[][] = new char[][] { { 'X', 'X', 'X'}, { ' ', 'O', 'X'},	
			{ 'X', 'X', 'X'}}; //mapa de testes
		GameMap game32 = new GameMap(currentMap32, false,0, false);
		game32.readMap(false);
		Ogre ogre32 = (Ogre)game32.getCharacters().get(0);
		game32.StunOgres();
		assertEquals(ogre32.getClubCh(),'*');
		assertTrue(ogre32.isClubAdjacent(ogre32.getX(), ogre32.getY()));
		assertTrue(ogre32.isClubAdjacent(ogre32.getClubX(), ogre32.getClubY()));
		assertEquals(ogre32.isClubVisible(game32.getCurrentMap()), true);
		
		char currentMap4[][] = new char[][] { { 'X', 'X', 'X'}, { 'X', 'O', 'X'},	
			{ 'X', 'X', 'X'}}; //mapa de testes
		GameMap game4 = new GameMap(currentMap4, false,0, false);
		game4.readMap(false);
		game4.StunOgres();
	}
}
