package dkeep.logic;
import java.util.concurrent.ThreadLocalRandom;

public class Guard extends Person{
	
	public enum Personality {Rookie, Drunken, Suspicious} 
	private static int numberOfGuards = 0;
	
	private char path[] = { 'a', 's', 's', 's', 's', 'a', 'a', 'a', 'a', 'a', 'a', 's', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'w',
			'w', 'w', 'w', 'w' };
	
	private Personality personality;
	private int it;//iterador que percorre path
	
	
	public Guard(String name, int x, int y, char Ch, Personality personality) {
		super(name,x,y, Ch);
		this.personality = personality;
		numberOfGuards++;
		it = 0;
	}
	
	public Guard(String name, int x, int y, Personality personality){//construtor default
		this(name, x, y, 'G', personality);
	}
	
	public Guard() {
		this("guard" + numberOfGuards, (numberOfGuards % 7), (numberOfGuards/7), 'G', Personality.values()[ThreadLocalRandom.current().nextInt(0, 3)]);
	}
	
	public static int getNumberOfGuards(){
		return numberOfGuards;
	}
	
	public void doStep(int x, int y) {
		prevX = this.x; //guardar coordenadas antigas para poder apagar a personagem no mapa
		prevY = this.y;
		
		switch(personality){
		case Rookie:
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
		break;
		case Drunken:
			break;
		case Suspicious:
			break;
		}
	}
}
