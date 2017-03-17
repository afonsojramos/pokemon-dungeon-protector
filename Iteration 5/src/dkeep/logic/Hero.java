package dkeep.logic;

public class Hero extends Person{
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
		if (currentMap.isOnTheDoor(xHero, yHero)) {
			if (currentMap.isDoorOpen()) {
				prevX = x;
				prevY = y;
				x = xHero;
				y = yHero;
				currentMap.setPosUsed(x, y);
				return;
			} else {// nao alteramos as coordenadas e abrir as portas
				currentMap.openDoors();
				return;
			}
		}
		
		if (currentMap.isAboveKey(xHero, yHero) && !currentMap.isKeyFound()) {// acabou de encontrar a chave
			currentMap.setKeyFound();
			Ch = 'K';
			prevX = x;
			prevY = y;
			x = xHero;
			y = yHero;
			return;
		}
		prevX = x;
		prevY = y;
		x = xHero;
		y = yHero;
		return;
	}

	public void setArmed() {
		armed = true;
		Ch = 'A';
	}
	
	public boolean isArmed() {
		return armed;
	}
}

