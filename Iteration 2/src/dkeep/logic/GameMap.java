package dkeep.logic;
import java.util.*;

import dkeep.logic.Guard.Personality;

public class GameMap {

	Random rand = new Random();
	
	private char overlapedPos [][];
	private int state; // nivel do jogo
	private int width, height; //largura e altura do mapa de jogo

	private char currentMap[][];

	private Person hero;
	
	private Vector<Vector<Ogre>> ogres;
	private Vector<Vector<Guard>> guards;

	private boolean possibleMove;
	private boolean keyFound;
	private boolean endOfGame;	
	
	public GameMap() {
		state = 1;
		possibleMove = true;
		keyFound = false;
		endOfGame = false;
		overlapedPos = new char [10][10];
		
		hero = new Hero("hero", 1, 1);
		
		width = 10;
		height = 10;
		ogres = new Vector<Vector<Ogre>>(4);//vetor do index 0 fica vazio
		guards = new Vector<Vector<Guard>>(4);
		Vector<Guard> glevel0 = new Vector<Guard>();Vector<Guard> glevel1 = new Vector<Guard>();
		Vector<Guard> glevel2 = new Vector<Guard>();Vector<Guard> glevel3 = new Vector<Guard>();
		Vector<Ogre> olevel0 = new Vector<Ogre>();Vector<Ogre> olevel1 = new Vector<Ogre>();
		Vector<Ogre> olevel2 = new Vector<Ogre>();Vector<Ogre> olevel3 = new Vector<Ogre>();
		Guard guardState1 = new Guard("guard1", 8, 1, 'G', Personality.Rookie);
		Ogre crazyOgre = new Ogre("ogre1", 4, 1);
		glevel1.add(guardState1);
		olevel2.add(crazyOgre);
		ogres.add(olevel0);ogres.add(olevel1);ogres.add(olevel2);ogres.add(olevel3);
		guards.add(glevel0);guards.add(glevel1);guards.add(glevel2);guards.add(glevel3);
		int randomNum = rand.nextInt(5) + 1;
		System.out.print("\n\nNumero de ogres no nivel 3: " + randomNum + "\n\n");
		while(randomNum > 0){
			this.addOgreToLevel(3);
			randomNum--;
		}
		this.changeState(state);
	}

	public void addOgreToLevel(int level) {
		Ogre ogre = new Ogre();
		Vector<Ogre> auxOgres = ogres.get(level);
		auxOgres.add(ogre);
	}
	
	public void addGuardToLevel(int level) {
		Guard guard = new Guard();
		Vector<Guard> auxGuards = guards.get(level);
		auxGuards.add(guard);
	}

	public int getState() { return state; }

	public boolean isEndOfGame() {
		int x = hero.getX(), y = hero.getY();
		switch(state){
		case 1:
			int xg = guards.get(1).get(0).getX(), yg = guards.get(1).get(0).getY();
			if((((y == yg) && ((x == (xg - 1)) || (x == (xg + 1)))) || ((x == xg) && ((y == (yg - 1)) || (y == (yg + 1)))) || ((x == xg) && (y == yg))) && (guards.get(1).get(0).getCh() == 'G')){
				endOfGame = true;
			}
			break;
		case 2:
			int xo = ogres.get(2).get(0).getX(), yo = ogres.get(2).get(0).getY(), xc = ogres.get(2).get(0).getClubX(), yc = ogres.get(2).get(0).getClubY();
			if(((y == yo) && ((x == (xo - 1)) || (x == (xo + 1)))) || ((x == xo) && ((y == (yo - 1)) || (y == (yo + 1))))){
				endOfGame = true;
				System.out.println("\n\nGAME OVER\n\n");
			}
			if(((y == yc) && ((x == (xc - 1)) || (x == (xc + 1)))) || ((x == xc) && ((y == (yc - 1)) || (y == (yc + 1))))){
				endOfGame = true;
				System.out.println("\n\nGAME OVER\n\n");
			}
			
			break;
		case 3:
			Vector<Ogre> v = ogres.get(state);
			int size = v.size();
			size--;
			while(size >= 0){
				Ogre ogre = v.get(size);
				int xo3 = ogre.getX(), yo3 = ogre.getY(), xc3 = ogre.getClubX(), yc3 = ogre.getClubY();
				/*if(((y == yo3) && ((x == (xo3 - 1)) || (x == (xo3 + 1)))) || ((x == xo3) && ((y == (yo3 - 1)) || (y == (yo3 + 1))))){
					endOfGame = true;
					System.out.println("\n\nGAME OVER\n\n");
					break;
				}*/
				if(((y == yc3) && ((x == (xc3 - 1)) || (x == (xc3 + 1)))) || ((x == xc3) && ((y == (yc3 - 1)) || (y == (yc3 + 1))))){
					endOfGame = true;
					System.out.println("\n\nGAME OVER\n\n");
					break;
				}
				size--;
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
		boolean update = false;

		if (input == 'w' && ((currentMap[y - 1][x] != 'X') && (currentMap[y - 1][x] != 'I'))) {
			y--;
			update = true;
		} else if (input == 's' && ((currentMap[y + 1][x] != 'X') && (currentMap[y + 1][x] != 'I'))) {
			y++;
			update = true;
		} else if (input == 'd' && ((currentMap[y][x + 1] != 'X') && (currentMap[y][x + 1] != 'I'))) {
			x++;
			update = true;
		} else if ((state == 1) && (input == 'a' && ((currentMap[y][x - 1] != 'X') && (currentMap[y][x - 1] != 'I')))) {
			x--;
			update = true;
		} else if ((state == 2 || state == 3) && (input == 'a' && ((currentMap[y][x - 1] != 'X') && (!((currentMap[y][x - 1] == 'I') && !keyFound))))) {
			x--;
			update = true;
		}

		if (update) {
			switch (state) {
			case 1:
				update1(x, y);
				break;
			case 2:
				update2(x, y);
				break;
			case 3:
				update3(x, y);
				break;
			}
		}
	}

	public void moveOgre(Ogre ogre) {
		
		int xo = ogre.getX(), yo = ogre.getY(), xc = ogre.getClubX(), yc = ogre.getClubY();
		currentMap[yo][xo] = overlapedPos[yo][xo];
		do {
			int randomNum = rand.nextInt(4); // random entre [min, max] : int
												// randomNum = rand.nextInt((max
												// - min) + 1) + min;
			switch (randomNum) {
			case 0: // Ogre anda para cima
				if ((currentMap[yo - 1][xo] == 'I') || (currentMap[yo - 1][xo] == 'X')) {
					possibleMove = false;
				} else {					
					yo--;
					overlapedPos[yo][xo] = ((xo == 7) && (yo == 1) && !keyFound) ? '$' : 'O';
					currentMap[yo][xo] = ((xo == 7) && (yo == 1) && !keyFound) ? '$' : 'O';
					possibleMove = true;
				}
				break;
			case 1: // Ogre anda para baixo
				if ((currentMap[yo + 1][xo] == 'I') || (currentMap[yo + 1][xo] == 'X')) {
					possibleMove = false;
				} else {
					yo++;
					overlapedPos[yo][xo] = 'O';
					currentMap[yo][xo] = 'O';
					possibleMove = true;
				}
				break;
			case 2: // Ogre anda para a esquerda
				if ((currentMap[yo][xo - 1] == 'I') || (currentMap[yo][xo - 1] == 'X')) {
					possibleMove = false;
				} else {
					xo--;
					overlapedPos[yo][xo] = 'O';
					currentMap[yo][xo] = 'O';
					possibleMove = true;
				}
				break;
			case 3: // Ogre anda para a direita
				if ((currentMap[yo][xo + 1] == 'I') || (currentMap[yo][xo + 1] == 'X')) {
					possibleMove = false;
				} else {
					xo++;
					overlapedPos[yo][xo] = ((xo == 7) && (yo == 1) && !keyFound) ? '$' : 'O';
					currentMap[yo][xo] = ((xo == 7) && (yo == 1) && !keyFound) ? '$' : 'O';
					possibleMove = true;
				}
				break;
			}
		} while (!possibleMove);
		ogre.doStep(xo, yo); // atualizar as coordenadas do objeto Ogre
		int x = hero.getX(), y = hero.getY();
		if(((y == yo) && ((x == (xo - 1)) || (x == (xo + 1)))) || ((x == xo) && ((y == (yo - 1)) || (y == (yo + 1)))) && (state == 3)) {
			ogre.stun();
			currentMap[yo][xo] = '8';
		}
	}
	
	public void moveClub(Ogre ogre) {
		int xo = ogre.getX(), yo = ogre.getY(), xc = ogre.getClubX(), yc = ogre.getClubY();
		currentMap[yc][xc] = overlapedPos[yc][xc];//apagar club antigo
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
					if(overlapedPos[yc][xc] != 'O') {
					overlapedPos[yc][xc] = ((xc == 7) && (yc == 1) && !keyFound) ? '$' : '*';
					currentMap[yc][xc] = ((xc == 7) && (yc == 1) && !keyFound) ? '$' : '*';
					}
					possibleMove = true;
				}
				break;
			case 1: // Taco apontado para baixo
				if ((currentMap[yo + 1][xo] == 'I') || (currentMap[yo + 1][xo] == 'X')) {
					possibleMove = false;
				} else {
					xc = xo;
					yc = yo + 1;
					if(overlapedPos[yc][xc] != 'O') {
					overlapedPos[yc][xc] = '*';
					currentMap[yc][xc] = '*';
					}
					possibleMove = true;
				}
				break;
			case 2: // Taco apontado para a esquerda
				if ((currentMap[yo][xo - 1] == 'I') || (currentMap[yo][xo - 1] == 'X')) {
					possibleMove = false;
				} else {
					xc = xo - 1;
					yc = yo;
					if(overlapedPos[yc][xc] != 'O') {
					overlapedPos[yc][xc] = '*';
					currentMap[yc][xc] = '*';
					}
					possibleMove = true;
				}
				break;
			case 3: // Taco apontado para a direita
				if ((currentMap[yo][xo + 1] == 'I') || (currentMap[yo][xo + 1] == 'X')) {
					possibleMove = false;
				} else {
					xc = xo + 1;
					yc = yo;
					if(overlapedPos[yc][xc] != 'O'){
					overlapedPos[yc][xc] = ((xc == 7) && (yc == 1) && !keyFound) ? '$' : '*';
					currentMap[yc][xc] = ((xc == 7) && (yc == 1) && !keyFound) ? '$' : '*';
					}
					possibleMove = true;
				}
				break;
			}
		} while (!possibleMove);
		
		ogre.setClubX(xc);
		ogre.setClubY(yc);
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
		
		guards.get(1).get(0).doStep(0, 0);
		int xg = guards.get(1).get(0).getX(), yg = guards.get(1).get(0).getY();
		int prevXg = guards.get(1).get(0).getPrevX(), prevYg = guards.get(1).get(0).getPrevY();
		currentMap[prevYg][prevXg] = ' ';
		currentMap[yg][xg] = guards.get(1).get(0).getCh();
		
		if((hero.getX() == 0) && ((hero.getY() == 5) || (hero.getY() == 6))){
			this.changeState(2);
		}

	}

	public void update2(int new_x, int new_y) {
		hero.doStep(new_x, new_y);
		int x = hero.getX(), y = hero.getY();
		currentMap[hero.getPrevY()][hero.getPrevX()] = ' ';
		if ((x == 7) && (y == 1)){ // heroi apanhou a chave
			keyFound = true;
			hero.setCh('K');
		}

		if ((x == 0) && (y == 1) && (currentMap[y][x] == 'I')) {//abrir a porta
			currentMap[1][0] = 'S';
			x++; // permanece na posicao anterior
			hero.setX(x);
		}
		currentMap[y][x] = hero.getCh();

		overlapedPos = new char[][] { { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'k', ' ', ' ' },
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' } };
		Ogre ogre = ogres.get(2).get(0);
		this.moveOgre(ogre);
		this.moveClub(ogre);
		overlapedPos = null;
		if((x == 0) && (y == 1)){
			this.changeState(3);
		}
	}
	
	public void update3(int new_x, int new_y){
		hero.doStep(new_x, new_y);
		int x = hero.getX(), y = hero.getY();
		currentMap[hero.getPrevY()][hero.getPrevX()] = ' ';
		if ((x == 7) && (y == 1)){ // heroi apanhou a chave
			keyFound = true;
			hero.setCh('K');
		}

		if ((x == 0) && (y == 1) && (currentMap[y][x] == 'I')) {//abrir a porta
			currentMap[1][0] = 'S';
			x++; // permanece na posicao anterior
			hero.setX(x);
		}
		currentMap[y][x] = hero.getCh();
		
		overlapedPos = new char[][] { { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' } };
		if (!keyFound) {
			overlapedPos[1][7] = 'k';
		}
		Vector<Ogre> v = ogres.get(state);
		int size = v.size();
		size--;
		while(size >= 0){
			Ogre ogre = v.get(size);
			if(ogre.isStuned()){
				this.moveClub(ogre);
				ogre.lessStuned();
			}else {
			this.moveOgre(ogre);
			this.moveClub(ogre);
			}
			size--;
		}
		overlapedPos = null;
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
				state = 1;
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
				state = 2;
				break;
		case 3:
			currentMap = new char[][] { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' }, { 'I', ' ', ' ', ' ', ' ', ' ', ' ', 'k', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', 'A', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } }; //mapa do nivel 3
				width = 9;
				height = 9;
				hero.setX(1);hero.setY(7);hero.setPrevX(1);hero.setPrevY(7);
				hero.setCh('A');
				state = 3;
				keyFound = false;
				Vector<Ogre> v = ogres.get(state);
				int size = v.size();
				size--;
				while(size >= 0){
					Ogre ogre = v.get(size);
					System.out.print("\n\nx do ogre: "+ogre.getX()+" y do ogre: "+ogre.getY()+"  x do club: "+ogre.getClubX()+" y do club: "+ogre.getClubY()+"\n\n");
					currentMap[ogre.getY()][ogre.getX()] = ogre.getCh();
					currentMap[ogre.getClubY()][ogre.getClubX()] = ogre.getClubCh();
					size--;
				}
				break;
		}
	}
	
	
	
	
}
