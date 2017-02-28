package dkeep.logic;
import java.util.concurrent.ThreadLocalRandom;

public class Guard extends Person{
	
	public enum Personality {Rookie, Drunken, Suspicious} 
	private static int numberOfGuards = 0;
	private int timeSleep, timeAwake;
	private boolean direction;
	private char path[] = { 'a', 's', 's', 's', 's', 'a', 'a', 'a', 'a', 'a', 'a', 's', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'w',
			'w', 'w', 'w', 'w' };
	
	private Personality personality;
	private int it;//iterador que percorre path
	
	
	public Guard(String name, int x, int y, char Ch, Personality personality) {
		super(name,x,y, Ch);
		timeSleep=ThreadLocalRandom.current().nextInt(2, 7);
		timeAwake=ThreadLocalRandom.current().nextInt(1, 7);
		this.personality = personality;
		numberOfGuards++;
		it = 0;
		direction = true;
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
			if (timeSleep != 0 || timeAwake != 0){
				if (timeAwake > 0){
					if (path[it] == 'a') {
						if (direction)
							this.x--;
						else
							this.x++;
					} else if (path[it] == 'd') {
						if (direction)
							this.x++;
						else
							this.x--;
					} else if (path[it] == 'w') {
						if (direction)
							this.y--;
						else
							this.y++;
					} else if (path[it] == 's') {
						if (direction)
							this.y++;
						else
							this.y--;
					}
					timeAwake--;
					if (timeAwake != 0){
						if (direction)
							it++;
						else {
							it--;
							if (it == 0)
								it = 24;
							}
					}
					it = it % 24;
					
				}
				else if (timeSleep > 0){
					this.setCh('g');
					timeSleep--;
				}
			}
			else {
				direction = (ThreadLocalRandom.current().nextInt(0, 2)==0) ? false : true;
				this.setCh('G');
				timeSleep=ThreadLocalRandom.current().nextInt(2, 7);
				timeAwake=ThreadLocalRandom.current().nextInt(1, 7);
			}
					
			break;
		case Suspicious:
			if (timeSleep != 0 || timeAwake != 0){
					if (path[it] == 'a') {
						if (direction)
							this.x--;
						else
							this.x++;
					} else if (path[it] == 'd') {
						if (direction)
							this.x++;
						else
							this.x--;
					} else if (path[it] == 'w') {
						if (direction)
							this.y--;
						else
							this.y++;
					} else if (path[it] == 's') {
						if (direction)
							this.y++;
						else
							this.y--;
					}
					if (timeAwake > 0)
						timeAwake--;
					else 
						timeSleep--;
					if (timeAwake != 0){
						if (direction)
							it++;
						else {
							it--;
							if (it == 0)
								it = 24;
							}
					}
					it = it % 24;
					if (timeAwake==0)
						direction = false;
			}
			else {		
				timeSleep=ThreadLocalRandom.current().nextInt(1, 7);
				timeAwake=ThreadLocalRandom.current().nextInt(1, 7);
			}
			break;
		}
	}
}
