package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.*;
public class TestOgreRandomness {
	@Test
	public void TestOgreRandomMove(){
		char currentMap[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X'}, { 'X', 'H', ' ', 'O', 'X'},	
			{ 'I', ' ', ' ', ' ', 'X'}, { 'I', 'k', ' ', ' ', 'X'} , { 'X', 'X', 'X', 'X', 'X'} }; //mapa de testes
		GameMap game = new GameMap(currentMap, false,0, false);
		game.readMap(false);
		int possibleX1 = 2, possibleY1 = 1, possibleX2 = 3, possibleY2 = 2;
		MapLevel map = game.getCurrentMap();
		Ogre ogre = (Ogre)game.getCharacters().get(0);
		ogre.doStep(map, 0, 0);
		int x, y;
		x = ogre.getX(); y = ogre.getY();
		boolean result = ((possibleX1 == x && possibleY1 == y) || (possibleX2 == x && possibleY2 == y));
		assertTrue(result);
	}
	
}
