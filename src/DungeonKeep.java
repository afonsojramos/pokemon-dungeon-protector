import java.util.Scanner;

public class DungeonKeep {
	char board[][] = {{'X','X','X','X','X','X','X','X','X','X'},{'X','H',' ',' ','I',' ','X',' ','G','X'},{'X','X','X',' ','X','X','X',' ',' ','X'},{'X',' ','I',' ','I',' ','X',' ',' ','X'},{'X','X','X',' ','X','X','X',' ',' ','X'},{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},{'X','X','X',' ','X','X','X','X',' ','X'},{'X',' ','I',' ','I',' ','X','k',' ','X'},{'X','X','X','X','X','X','X','X','X','X'}};
	char guard[] = {'a','s','s','s','s','a','a','a','a','a','a','s','d','d','d','d','d','d','d','w','w','w','w','w'};
	int x = 1, y = 1; //canto superior esquerdo e a origem do referencial
	int xg = 8, yg = 1; //posicao do guarda
	int it = 0; //iterador do array
	public void update(int new_x, int new_y){
		if(it == 24){
			it = 0;
		}
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
		board[yg][xg]=' ';
		if(guard[it] == 'a'){
			xg--;
		}
		else if(guard[it] == 'd'){
			xg++;
		}
		else if(guard[it] == 'w'){
			yg--;
		}
		else if(guard[it] == 's'){
			yg++;
		}
		board[yg][xg]='G';
		it++;
		
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
		while((x != 0 && (y != 5 || y != 6)) && ((y != yg || (y != yg-1) || (y != yg+1)) && (x != xg || x != (xg-1) || x != (xg+1)))){
			move();
			display();
		}
	}
	
}
