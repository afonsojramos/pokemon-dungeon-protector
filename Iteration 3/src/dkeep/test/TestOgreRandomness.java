/*package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.*;
public class TestOgreRandomness {
	
	public void TestOgreRandomMove(){
		char currentMap[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X'}, { 'X', 'H', ' ', 'O', 'X'},	
			{ 'I', ' ', ' ', ' ', 'X'}, { 'I', 'k', ' ', ' ', 'X'} , { 'X', 'X', 'X', 'X', 'X'} }; //mapa de testes
		GameMap game = new GameMap(currentMap, false, false);
		game.readMap();
		boolean out1 = false, out2 = false, out3 = false, out4 = false;
		while (!out1 || !out2 || !out3 || !out4){
			assertTrue(game.isEndOfGame());
		}
	}
}
*/