package dkeep.logic;
import java.util.concurrent.ThreadLocalRandom;

public class Guard extends Person implements java.io.Serializable{
	
	public enum Personality {Rookie, Drunken, Suspicious, Obedient} 
	private static int numberOfGuards = 0;
	private int timeSleep, timeAwake;
	private boolean direction;
	
	
	int pathX[] = {8,7,7,7,7,7,6,5,4,3,2,1,1,2,3,4,5,6,7,8,8,8,8,8};
	int pathY[] = {1,1,2,3,4,5,5,5,5,5,5,5,6,6,6,6,6,6,6,6,5,4,3,2};
	
	private Personality personality;
	private int it;//iterador que percorre path
	
	
	public Guard(String name, int x, int y, char Ch, Personality personality) {
		super(name,x,y, Ch);
		timeSleep=ThreadLocalRandom.current().nextInt(2, 7);
		timeAwake=ThreadLocalRandom.current().nextInt(1, 7);
		this.personality = personality;
		numberOfGuards++;
		it = 1;
		direction = true;
	}
	/*
	public Guard(String name, int x, int y, Personality personality){//construtor default
		this(name, x, y, 'G', personality);
	}*/
	
	public Guard(int x, int y) {
		this("guard" + numberOfGuards, x, y, 'G', Personality.values()[ThreadLocalRandom.current().nextInt(0, 3)]);
	}
	
	public int getX() { return this.x; }
	
	public int getY() { return this.y; }
	
	public void setX(int x) { this.x = x; }
	
	public void setY(int y) { this.y = y; }
	
	public void doStep(MapLevel currentMap, int xHero, int yHero) {
		prevX = this.x; //guardar coordenadas antigas para poder apagar a personagem no mapa
		prevY = this.y;
		switch (personality) {
		case Rookie:	rookiePath();
			break;
		case Drunken:	drunkenPath();
			break;
		case Suspicious:suspiciousPath();
			break;
		case Obedient:break;
		}
		currentMap.setPosUsed(x, y);
	}
	
	public void rookiePath(){
		this.x = pathX[it];
		this.y = pathY[it];
		it++;
		it = it % 24;
	}
	
	public void drunkenPath(){
		if (timeAwake > 0) { timeAwake--;
			if (direction) { it++;
			} else {it = (it == 0) ? 23 : (it-1);}
			it = it % 24;
			this.x = pathX[it];	this.y = pathY[it];
		} else { this.setCh('g');
			if (timeSleep == 0) {
				direction = (ThreadLocalRandom.current().nextInt(0, 2) == 0) ? false : true;
				this.setCh('G');
				timeSleep = ThreadLocalRandom.current().nextInt(2, 7);
				timeAwake = ThreadLocalRandom.current().nextInt(1, 7);
			}timeSleep--;}
	}
	
	public void suspiciousPath(){ // timeAwake = sentido certo ; timeSleep = sentido contrario
		if (timeAwake > 0) {
			timeAwake--;
			it++;
		} else {timeSleep--;
			it = (it == 0) ? 23 : (it-1);
			if (timeSleep == 0) {
				timeSleep = ThreadLocalRandom.current().nextInt(2, 7);
				timeAwake = ThreadLocalRandom.current().nextInt(1, 7);}
		}
		it = it % 24;
		this.x = pathX[it]; this.y = pathY[it];
	}

	public void setPersonality (Personality p) {
		personality = p;
	}
	
	public boolean isAdjacent(int x1, int y1, int x2, int y2) {
		if (x1 == x2 && (y2 == (y1 - 1) || y2 == (y1 + 1))) {return true;}
		if (y1 == y2 && (x2 == (x1 - 1) || x2 == (x1 + 1))) {return true;}
		if (y1 == y2 && x1 == x2) {return true;}
		return false;
	}
	
	public boolean isInInvalidPos (char [][] mapArray) {
		return (mapArray[y][x] != ' ');
	}
	
	public void restartVariables() {
		numberOfGuards = 0;
	}
}

