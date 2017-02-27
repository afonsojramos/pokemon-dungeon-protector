package dkeep.logic;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Random;
import java.util.*;

public class GameMap {

	Random rand = new Random();

	private int state; // nivel do jogo
	private int width, height; //largura e altura do mapa de jogo

	private char currentMap[][];

	private Person hero;

	private Club c;

	private HashMap<Integer, HashMap<String, Person>> levels;

	private boolean possibleMove;
	private boolean keyFound;
	private boolean endOfGame;	
	
	public GameMap() {
		state = 1;
		possibleMove = true;
		keyFound = false;
		endOfGame = false;

		hero = new Hero("hero", 1, 1);
		c = new Club(5, 1);

		currentMap = new char[][] { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
				{ 'X', 'H', ' ', ' ', 'I', ' ', 'X', ' ', 'G', 'X' },
				{ 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
				{ 'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X' },
				{ 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
				{ 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', ' ', 'X' },
				{ 'X', ' ', 'I', ' ', 'I', ' ', 'X', 'k', ' ', 'X' },
				{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } }; // mapa
																		// do
																		// nivel
																		// 1
		width = 10;
		height = 10;
		Person guardState1 = new Guard("guard1", 8, 1);
		Person crazyOgre = new Ogre("ogre1", 4, 1);
		HashMap<String, Person> characters1 = new HashMap<String, Person>();
		characters1.put(guardState1.getName(), guardState1);
		HashMap<String, Person> characters2 = new HashMap<String, Person>();
		characters2.put(crazyOgre.getName(), crazyOgre);
		levels.put(new Integer(1), characters1);
		levels.put(new Integer(2), characters2);
	}

	public void addCharacterToLevel(int level, Person character) {
		String name = character.getName();
		levels.get(level).put(name, character);
	}

	public int getState() {
		return state;
	}

	public boolean isEndOfGame() {
		int x = hero.getX(), y = hero.getY();
		switch(state){
		case 1:
			int xg = levels.get(1).get("guard1").getX(), yg = levels.get(1).get("guard1").getY();
			if(((y == yg) || (y == (yg - 1)) || (y == (yg + 1)))
					&& ((x == xg) || (x == (xg - 1)) || (x == (xg + 1)))){
				endOfGame = true;
			}
			break;
		case 2:
			int xo = levels.get(2).get("ogre1").getX(), yo = levels.get(2).get("ogre1").getY();
			if(((y == yo) && ((x == (xo - 1)) || (x == (xo + 1)))) || ((x == xo) && ((y == (yo - 1)) || (y == (yo + 1))))){
				endOfGame = true;
				System.out.println("\n\nGAME OVER\n\n");
			}
			int xc = c.getX(), yc = c.getY();
			if(((y == yc) && ((x == (xc - 1)) || (x == (xc + 1)))) || ((x == xc) && ((y == (yc - 1)) || (y == (yc + 1))))){
				endOfGame = true;
				System.out.println("\n\nGAME OVER\n\n");
			}
			if((x == 0) && (y == 1)){
				endOfGame = true;
				System.out.println("\n\nVICTORY\n\n");
			}
			break;
		}
		return endOfGame;
	}
	
	public String getMap() {
		StringBuilder tmp = new StringBuilder();
		for (int i = 0; i < height; i++){
			for (int j = 0; j < width; j++){
				tmp.append(currentMap[i][j]);
				tmp.append(" ");
			}
			tmp.append('\n');
		}
		return tmp.toString();
	}
	
	public void moveHero(char input) {

		int x = hero.getX(), y = hero.getY();

		if (input == 'w' && ((currentMap[y - 1][x] != 'X') && (currentMap[y - 1][x] != 'I'))) {
			y--;
		} else if (input == 's' && ((currentMap[y + 1][x] != 'X') && (currentMap[y + 1][x] != 'I'))) {
			y++;
		} else if (input == 'd' && ((currentMap[y][x + 1] != 'X') && (currentMap[y][x + 1] != 'I'))) {
			x++;
		} else if ((state == 1) && (input == 'a' && ((currentMap[y][x - 1] != 'X') && (currentMap[y][x - 1] != 'I')))) {
			x--;
		} else if ((state == 2) && (input == 'a' && ((currentMap[y][x - 1] != 'X') && (!((currentMap[y][x - 1] == 'I') && !keyFound))))) {
			x--;
		}

		switch (state) {
		case 1:
			update1(x, y);
			break;
		case 2:
			update2(x, y);
			break;
		}
	}

	public void update1(int new_x, int new_y) {
		hero.doStep(new_x, new_y);
		currentMap[hero.getPrevY()][hero.getPrevX()] = ' ';
		currentMap[hero.getY()][hero.getX()] = 'H';
		if (new_y == 8 && new_x == 7) {
			currentMap[1][4] = 'S';
			currentMap[3][4] = 'S';
			currentMap[3][2] = 'S';
			currentMap[5][0] = 'S';
			currentMap[6][0] = 'S';
			currentMap[8][2] = 'S';
			currentMap[8][4] = 'S';
		}

		int xg = levels.get(1).get("guarda1").getX(), yg = levels.get(1).get("guarda1").getY();
		int prevXg = levels.get(1).get("guarda1").getPrevX(), prevYg = levels.get(1).get("guarda1").getPrevY();

		currentMap[prevYg][prevXg] = ' ';
		currentMap[yg][xg] = levels.get(1).get("guarda1").getCh();
		
		if((hero.getX() == 0) && ((hero.getY() == 5) || (hero.getY() == 6))){
			this.changeState(2);
		}

	}

	public void update2(int new_x, int new_y) {
		hero.doStep(new_x, new_y);
		int x = hero.getX(), y = hero.getY();
		currentMap[y][x] = ' ';
		if ((x == 7) && (y == 1)) // heroi apanhou a chave
			keyFound = true;

		if ((x == 0) && (y == 1) && (currentMap[y][x] == 'I')) {
			currentMap[1][0] = 'S';
			x++; // permanece na posicao anterior
			hero.setX(x);
		}

		if (keyFound)
			currentMap[y][x] = 'K';
		else
			currentMap[y][x] = 'H';

		int xo = levels.get(2).get("ogre1").getX(), yo = levels.get(2).get("ogre1").getY();
		int xc = c.getX(), yc = c.getY();

		/**
		 * posicionar o Ogre
		 */
		do {
			int randomNum = rand.nextInt(4); // random entre [min, max] : int
												// randomNum = rand.nextInt((max
												// - min) + 1) + min;
			switch (randomNum) {
			case 0: // Ogre anda para cima
				if ((currentMap[yo - 1][xo] == 'I') || (currentMap[yo - 1][xo] == 'X')) {
					possibleMove = false;
				} else {
					currentMap[yo][xo] = ' ';
					currentMap[yc][xc] = (currentMap[yc][xc] == '$') ? 'k' : ' ';
					yo--;
					currentMap[yo][xo] = ((xo == 7) && (yo == 1) && !keyFound) ? '$' : 'O';
					possibleMove = true;
				}
				break;
			case 1: // Ogre anda para baixo
				if ((currentMap[yo + 1][xo] == 'I') || (currentMap[yo + 1][xo] == 'X')) {
					possibleMove = false;
				} else {
					currentMap[yo][xo] = (currentMap[yo][xo] == '$') ? 'k' : ' ';
					currentMap[yc][xc] = (currentMap[yc][xc] == '$') ? 'k' : ' ';
					yo++;
					currentMap[yo][xo] = 'O';
					possibleMove = true;
				}
				break;
			case 2: // Ogre anda para a esquerda
				if ((currentMap[yo][xo - 1] == 'I') || (currentMap[yo][xo - 1] == 'X')) {
					possibleMove = false;
				} else {
					currentMap[yo][xo] = (currentMap[yo][xo] == '$') ? 'k' : ' ';
					currentMap[yc][xc] = (currentMap[yc][xc] == '$') ? 'k' : ' ';
					xo--;
					currentMap[yo][xo] = 'O';
					possibleMove = true;
				}
				break;
			case 3: // Ogre anda para a direita
				if ((currentMap[yo][xo + 1] == 'I') || (currentMap[yo][xo + 1] == 'X')) {
					possibleMove = false;
				} else {
					currentMap[yo][xo] = ' ';
					currentMap[yc][xc] = (currentMap[yc][xc] == '$') ? 'k' : ' ';
					xo++;
					currentMap[yo][xo] = ((xo == 7) && (yo == 1) && !keyFound) ? '$' : 'O';
					possibleMove = true;
				}
				break;
			}
		} while (!possibleMove);
		levels.get(2).get("ogre1").doStep(xo, yo); // atualizar as coordenadas
													// do objeto Ogre

		possibleMove = false;

		/**
		 * posicionar o Club
		 */
		do {
			int randomNum2 = rand.nextInt(4); // random entre [min, max] : int
												// randomNum = rand.nextInt((max
												// - min) + 1) + min;
			switch (randomNum2) {
			case 0: // Taco apontado para cima
				if ((currentMap[yo - 1][xo] == 'I') || (currentMap[yo - 1][xo] == 'X')) {
					possibleMove = false;
				} else {
					xc = xo;
					yc = yo - 1;
					currentMap[yc][xc] = ((xc == 7) && (yc == 1) && !keyFound) ? '$' : '*';
					possibleMove = true;
				}
				break;
			case 1: // Taco apontado para baixo
				if ((currentMap[yo + 1][xo] == 'I') || (currentMap[yo + 1][xo] == 'X')) {
					possibleMove = false;
				} else {
					xc = xo;
					yc = yo + 1;
					currentMap[yc][xc] = '*';
					possibleMove = true;
				}
				break;
			case 2: // Taco apontado para a esquerda
				if ((currentMap[yo][xo - 1] == 'I') || (currentMap[yo][xo - 1] == 'X')) {
					possibleMove = false;
				} else {
					xc = xo - 1;
					yc = yo;
					currentMap[yc][xc] = '*';
					possibleMove = true;
				}
				break;
			case 3: // Taco apontado para a direita
				if ((currentMap[yo][xo + 1] == 'I') || (currentMap[yo][xo + 1] == 'X')) {
					possibleMove = false;
				} else {
					xc = xo + 1;
					yc = yo;
					currentMap[yc][xc] = ((xc == 7) && (yc == 1) && !keyFound) ? '$' : '*';
					possibleMove = true;
				}
				break;
			}
		} while (!possibleMove);
		c.changePos(xc, yc); // atualizar coordenadas do Club
	}

	public void changeState(int nState){
		
		switch(nState){
		case 1:
			currentMap = new char[][] { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
				{ 'X', 'H', ' ', ' ', 'I', ' ', 'X', ' ', 'G', 'X' }, { 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
				{ 'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X' }, { 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
				{ 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', ' ', 'X' }, { 'X', ' ', 'I', ' ', 'I', ' ', 'X', 'k', ' ', 'X' },
				{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } }; //mapa do nivel 1
				width = 10;
				height = 10;
				hero.setX(1);hero.setY(1);hero.setPrevX(1);hero.setPrevY(1);
				break;
		case 2:
			currentMap = new char[][] { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' }, { 'I', ' ', ' ', ' ', 'O', '*', ' ', 'k', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', 'H', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } }; //mapa do nivel 2
				width = 9;
				height = 9;
				hero.setX(1);hero.setY(7);hero.setPrevX(1);hero.setPrevY(7);
				break;
		}
	}
	
	
}
