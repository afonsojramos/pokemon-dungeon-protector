package dkeep.cli;
import java.util.Scanner;

import dkeep.logic.GameMap;
import dkeep.logic.Maps;

public class Play {

	private static int currentLevel = 1;
	private static int finalLevel = 3;
	private static boolean endOfGame = false;
	private static GameMap game = null;
	
	private static char askForInput() {
		Scanner input = new Scanner(System.in);
		char letter = input.next().charAt(0);
		return letter;
	}

	public static void main(String[] args) {
		/*COMECAR O JOGO*/
		while ((currentLevel <= finalLevel) && !endOfGame) {
			game = new GameMap(Maps.getMap(currentLevel), Maps.hasMultipleOgre(currentLevel), 0, Maps.instantaneousDoorOpen(currentLevel));
			game.readMap(false);
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
