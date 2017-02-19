import java.util.Scanner;
import java.util.Random;

public class DungeonKeep {
	char board[][] = { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ 'X', 'H', ' ', ' ', 'I', ' ', 'X', ' ', 'G', 'X' }, { 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
			{ 'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X' }, { 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
			{ 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', ' ', 'X' }, { 'X', ' ', 'I', ' ', 'I', ' ', 'X', 'k', ' ', 'X' },
			{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };
	char board2[][] = { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ 'I', ' ', ' ', ' ', 'O', ' ', ' ', 'k', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', 'H', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };
	char guard[] = { 'a', 's', 's', 's', 's', 'a', 'a', 'a', 'a', 'a', 'a', 's', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'w',
			'w', 'w', 'w', 'w' };
	int x = 1, y = 1; // canto superior esquerdo e a origem do referencial;
						// posicao inicial do heroi
	int xg = 8, yg = 1; // posicao do guarda
	int xo = 4, yo = 1; // posicao do ogre
	int it = 0; // iterador do array
	Random rand = new Random();
	boolean possibleMove = true;
	boolean keyFound = false;
	
	public void update(int new_x, int new_y) {
		board[y][x] = ' ';
		x = new_x;
		y = new_y;
		board[y][x] = 'H';
		if (new_y == 8 && new_x == 7) {
			board[1][4] = 'S';
			board[3][4] = 'S';
			board[3][2] = 'S';
			board[5][0] = 'S';
			board[6][0] = 'S';
			board[8][2] = 'S';
			board[8][4] = 'S';
		}
		board[yg][xg] = ' ';
		if (guard[it] == 'a') {
			xg--;
		} else if (guard[it] == 'd') {
			xg++;
		} else if (guard[it] == 'w') {
			yg--;
		} else if (guard[it] == 's') {
			yg++;
		}
		board[yg][xg] = 'G';
		it++;
		it = it % 24;

	}

	public void update2(int new_x, int new_y) {
		board2[y][x] = ' ';
		x = new_x;
		y = new_y;
		if((x == 7) && (y == 1)) //heroi apanhou a chave
			keyFound = true;

		if((x == 0) && (y == 1) && (board2[y][x] == 'I')){
			board2[1][0] = 'S';
			x++; //permanece na posicao anterior
		}

		if(keyFound)
			board2[y][x] = 'K';
		else
			board2[y][x] = 'H';
		
		do {
			int randomNum = rand.nextInt(4); // random entre [min, max] : int randomNum = rand.nextInt((max - min) + 1) + min;
			switch (randomNum) {
			case 0: // Ogre anda para cima
				if ((board2[yo - 1][xo] == 'I') || (board2[yo - 1][xo] == 'X')) {
					possibleMove = false;
				} else {
					board2[yo][xo] = ' ';
					yo--;
					if((xo == 7) && (yo == 1))
						board2[yo][xo] = '$';
					else
						board2[yo][xo] = 'O';
					possibleMove = true;
				}
				break;
			case 1: // Ogre anda para baixo
				if ((board2[yo + 1][xo] == 'I') || (board2[yo + 1][xo] == 'X')) {
					possibleMove = false;
				} else {
					board2[yo][xo] = (board2[yo][xo] == '$') ? 'k' : ' ';
					yo++;
					board2[yo][xo] = 'O';
					possibleMove = true;
				}
				break;
			case 2: // Ogre anda para a esquerda
				if ((board2[yo][xo - 1] == 'I') || (board2[yo][xo - 1] == 'X')) {
					possibleMove = false;
				} else {
					board2[yo][xo] = (board2[yo][xo] == '$') ? 'k' : ' ';
					xo--;
					board2[yo][xo] = 'O';
					possibleMove = true;
				}
				break;
			case 3: // Ogre anda para a direita
				if ((board2[yo][xo + 1] == 'I') || (board2[yo][xo + 1] == 'X')) {
					possibleMove = false;
				} else {
					board2[yo][xo] = ' ';
					xo++;
					if((xo == 7) && (yo == 1))
						board2[yo][xo] = '$';
					else
						board2[yo][xo] = 'O';
					possibleMove = true;
				}
				break;
			}
		} while (!possibleMove);
	}

	public void display() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(board[i][j]);
				System.out.print(' ');
			}
			System.out.println();
		}
	}

	public void display2() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(board2[i][j]);
				System.out.print(' ');
			}
			System.out.println();
		}
	}

	public void move() {
		Scanner input = new Scanner(System.in);
		char letter = input.next().charAt(0);
		if (letter == 'w' && ((board[y - 1][x] != 'X') && (board[y - 1][x] != 'I'))) {
			update(x, y - 1);
		} else if (letter == 'a' && ((board[y][x - 1] != 'X') && (board[y][x - 1] != 'I'))) {
			update(x - 1, y);
		} else if (letter == 's' && ((board[y + 1][x] != 'X') && (board[y + 1][x] != 'I'))) {
			update(x, y + 1);
		} else if (letter == 'd' && ((board[y][x + 1] != 'X') && (board[y][x + 1] != 'I'))) {
			update(x + 1, y);
		}

	}
	
	public void move2() {
		Scanner input = new Scanner(System.in);
		char letter = input.next().charAt(0);
		if (letter == 'w' && ((board2[y - 1][x] != 'X') && (board2[y - 1][x] != 'I'))) {
			update2(x, y - 1);
		} else if (letter == 'a' && ((board2[y][x - 1] != 'X') && (!((board2[y][x - 1] == 'I') && !keyFound)))) {
			update2(x - 1, y);
		} else if (letter == 's' && ((board2[y + 1][x] != 'X') && (board2[y + 1][x] != 'I'))) {
			update2(x, y + 1);
		} else if (letter == 'd' && ((board2[y][x + 1] != 'X') && (board2[y][x + 1] != 'I'))) {
			update2(x + 1, y);
		}

	}

	private void run() {
		boolean endOfGame = false;
		while (!((x == 0) && ((y == 5) || (y == 6)))) {// nivel 1
			if (((y == yg) || (y == (yg - 1)) || (y == (yg + 1)))
					&& ((x == xg) || (x == (xg - 1)) || (x == (xg + 1)))) {
				System.out.println("\n\nGAME OVER\n\n");
				endOfGame = true;
				break;
			}
			move();
			display();
		}

		if (!endOfGame) {// proximo nivel (nivel 2)
			x = 1; // atualizar coordenadas do guarda no tabuleiro
			y = 7;
			System.out.print("\n\n");
			display2();
			while(!((x == 0) && (y == 1))){
				if (((y == yo) || (y == (yo - 1)) || (y == (yo + 1)))
						&& ((x == xo) || (x == (xo - 1)) || (x == (xo + 1)))) {
					System.out.println("\n\nGAME OVER\n\n");
					endOfGame = true;
					break;
				}
				move2();
				display2();
			}
		}
	}

	public static void main(String[] args) {
		DungeonKeep d = new DungeonKeep();
		d.display();
		d.run();
	}

}
