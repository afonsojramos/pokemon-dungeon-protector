package dkeep.logic;

public class Guard extends Person{
	
	private static int numberOfGuards = 0;
	
	private char path[] = { 'a', 's', 's', 's', 's', 'a', 'a', 'a', 'a', 'a', 'a', 's', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'w',
			'w', 'w', 'w', 'w' };
	
	private int it;//iterador que percorre path
	
	
	public Guard(String name, int x, int y, char Ch) {
		super(name,x,y, Ch);
		numberOfGuards++;
		it = 0;
	}
	
	public Guard(String name, int x, int y){//construtor default
		this(name, x, y, 'G');
	}
	
	public static int getNumberOfGuards(){
		return numberOfGuards;
	}
	
	public void doStep(int x, int y) {
		prevX = this.x; //guardar coordenadas antigas para poder apagar a personagem no mapa
		prevY = this.y;
		if (path[it] == 'a') {
			this.x--;
		} else if (path[it] == 'd') {
			this.x++;
		} else if (path[it] == 'w') {
			this.y--;
		} else if (path[it] == 's') {
			this.y++;
		}
		it++;
		it = it % 24;
	}
}
