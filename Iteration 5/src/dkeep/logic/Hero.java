package dkeep.logic;

public class Hero extends Person implements java.io.Serializable{
	private boolean armed = false;

	public Hero(String name, int x, int y, char Ch){
		super(name,x,y, Ch);
	}
	
	public Hero(String name, int x, int y) {//construtor default
		this(name, x, y, 'H');
	}
	
	public void printElement (char currentMap [][]) {
		currentMap[y][x] = Ch;
	}
	
	public void doStep(MapLevel currentMap, int xHero, int yHero) {
		if(currentMap.isOnTheDoor(xHero, yHero) && !(currentMap.isDoorOpen(xHero,yHero))) {currentMap.openDoors(xHero,yHero);return;}
		if(currentMap.isAboveKey(xHero, yHero) && !currentMap.isKeyFound()) {
			currentMap.setKeyFound();
			if(!currentMap.isInstantaneousDoorOpen()) {Ch = 'K';}
		}
		prevX = x;
		prevY = y;
		x = xHero;
		y = yHero;
		if (currentMap.isOnTheDoor(xHero, yHero) && currentMap.isDoorOpen(xHero,yHero)) { currentMap.setPosUsed(x, y); }
	}
	
	public void setArmed(boolean change) {
		armed = change;
		Ch = change ? 'A' : 'H';
	}
	
	public boolean isArmed() {
		return armed;
	}
}

