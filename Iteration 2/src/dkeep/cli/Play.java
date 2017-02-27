package dkeep.cli;
import java.util.Scanner;

import dkeep.logic.GameMap;

public class Play {
	
	public static void main(String[] args) {
		GameMap game = new GameMap();
		System.out.print(game.getMap());
		while(!game.isEndOfGame()){
			Scanner input = new Scanner(System.in);
			char letter = input.next().charAt(0);
			game.moveHero(letter);
			System.out.print(game.getMap());
		}
	}
}
