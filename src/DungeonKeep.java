import java.util.Scanner;

public class DungeonKeep {
	char board[][] = {{'X','X','X','X','X','X','X','X','X','X'},{'X','H',' ',' ','I',' ','X',' ','G','X'},{'X','X','X',' ','X','X','X',' ',' ','X'},{'X',' ','I',' ','I',' ','X',' ',' ','X'},{'X','X','X',' ','X','X','X',' ',' ','X'},{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},{'X','X','X',' ','X','X','X','X',' ','X'},{'X',' ','I',' ','I',' ','X','k',' ','X'},{'X','X','X','X','X','X','X','X','X','X'}};
	int x = 1, y = 1; //canto superior esquerdo e a origem do referencial
	public void update(int new_x, int new_y){
		board[y][x] = ' ';
		x = new_x;
		y = new_y;
		board[y][x] = 'H';
		if(new_y == 8 && new_x == 7){
			board[1][4]= 'S';
			board[3][4]= 'S';
			board[3][2]= 'S';
			board[5][0]= 'S';
			board[6][0]= 'S';
			board[8][2]= 'S';
			board[8][4]= 'S';
		}
		
	}
	public void display(){
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				System.out.print(board[i][j]);
				System.out.print(' ');
			}
			System.out.println();
		}
	}
	public void move(){
		Scanner input = new Scanner(System.in);
		char letter = input.next().charAt(0);
		if(letter == 'w' && ((board[y-1][x] != 'X') && (board[y-1][x] != 'I'))){
			update(x, y-1);
		}
		else if (letter == 'a' && ((board[y][x-1] != 'X') && (board[y][x-1] != 'I'))){
			update(x-1,y);	
		}
		else if (letter == 's' && ((board[y+1][x] != 'X') && (board[y+1][x] != 'I'))){
			update(x, y+1);
		}
		else if (letter == 'd' && ((board[y][x+1] != 'X') && (board[y][x+1] != 'I'))){
			update(x+1,y);
		}
		
	}
	public static void main(String[] args){
		DungeonKeep d = new DungeonKeep();
		d.display();
		d.run();
	}
	private void run() {
		while(x != 0 && (y != 5 || y != 6)){
			move();
			display();
		}
	}
}
