package dkeep.cli;
import java.util.ArrayList;
import java.util.Scanner;

import dkeep.logic.GameMap;

public class Play {

	static char map0[][] = new char[][] {{' '}};
	static char map1[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ 'X', 'H', ' ', ' ', 'I', ' ', 'X', ' ', 'G', 'X' }, { 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
			{ 'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X' }, { 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
			{ 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', ' ', 'X' }, { 'X', ' ', 'I', ' ', 'I', ' ', 'X', 'k', ' ', 'X' },
			{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } }; // mapa do
																	// nivel 1
	static char map2[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ 'I', ' ', ' ', ' ', 'O', '*', ' ', 'k', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', 'H', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } }; // mapa
																												// do
																												// nivel
																												// 2

	static char map3[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ 'I', ' ', ' ', ' ', ' ', ' ', ' ', 'k', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', 'A', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } }; // mapa
																												// do
																												// nivel
																												// 3

	private static int currentLevel = 3;
	private static int finalLevel = 3;
	private static boolean endOfGame = false;
	private static GameMap game = null;
	private static char askForInput() {
		Scanner input = new Scanner(System.in);
		char letter = input.next().charAt(0);
		return letter;
	}

	public static void main(String[] args) {
		/*GUARDAR MAPAS DE JOGO*/
		ArrayList<char[][]> listOfMaps = new ArrayList<char[][]>(4);
		boolean hasMultipleOgres[] = new boolean[] {false, false, false, true};// se true, criar random ogres
		boolean instantaneousDoorOpen[] = new boolean[] {false, true, false, false};// se true, portas abrem-se mal se apanha a chave
 		listOfMaps.add(map0);
		listOfMaps.add(map1);
		listOfMaps.add(map2);
		listOfMaps.add(map3);
		/*COMECAR O JOGO*/
		while ((currentLevel <= finalLevel) && !endOfGame) {
			game = new GameMap(listOfMaps.get(currentLevel), hasMultipleOgres[currentLevel], instantaneousDoorOpen[currentLevel]);
			game.readMap();
			System.out.print(game.getMap());
			endOfGame = false;
			while (!endOfGame) {
				boolean validInput = false;
				do {
					char letter = askForInput();
					validInput = game.startGame(letter);
				} while(!validInput);//enquanto o input nao for valido
				game.update();
				System.out.print(game.getMap());
				endOfGame = game.isEndOfGame();
			}
			if(game.isVictory()) {//passar para proximo nivel
				endOfGame = false;
				currentLevel++;
			} 
		}
		if(game.isVictory()) {
			System.out.print("\n\nVICTORY!!!\n\n");
		} else {
			System.out.print("\n\nGAME OVER...\n\n");
		}
		
	}
}
