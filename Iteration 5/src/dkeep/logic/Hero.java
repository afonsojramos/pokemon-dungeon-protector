package dkeep.logic;

public class Hero extends Person implements java.io.Serializable{
	private boolean armed = false;
	/**
	 * construtor do heroi
	 * @param name
	 * @param x
	 * @param y
	 * @param Ch
	 */
	public Hero(String name, int x, int y, char Ch){
		super(name,x,y, Ch);
	}
	/**
	 * construtor do heroi
	 * @param name
	 * @param x
	 * @param y
	 */
	public Hero(String name, int x, int y) {//construtor default
		this(name, x, y, 'H');
	}
	/**
	 * desenha o Ch do heroi no array do mapa atual
	 * @param currentMap
	 */
	public void printElement (char currentMap [][]) {
		currentMap[y][x] = Ch;
	}
	/**
	 * trata do movimento do heroi no mapa de jogo
	 */
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
	/**
	 * altera o estado armed do heroi. poe-lo ou nao armado
	 * @param change
	 */
	public void setArmed(boolean change) {
		armed = change;
		Ch = change ? 'A' : 'H';
	}
	/**
	 * verifica se o heroi esta armado
	 * @return
	 */
	public boolean isArmed() {
		return armed;
	}
}

