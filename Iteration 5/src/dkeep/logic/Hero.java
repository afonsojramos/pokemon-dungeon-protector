package dkeep.logic;

/**  
* Hero.java - Class regarding the hero  
* @see Person
*/ 
public class Hero extends Person implements java.io.Serializable{
	private boolean armed = false;
	/**
	 * Hero's constructor
	 * @param name
	 * @param x
	 * @param y
	 * @param Ch
	 */
	public Hero(String name, int x, int y, char Ch){
		super(name,x,y, Ch);
	}
	/**
	 * Hero's constructor
	 * @param name
	 * @param x
	 * @param y
	 */
	public Hero(String name, int x, int y) {//construtor default
		this(name, x, y, 'H');
	}
	/**
	 * Draws hero's element
	 * @param currentMap[yHero][xHero]
	 */
	public void printElement (char currentMap [][]) {
		currentMap[y][x] = Ch;
	}
	/**
	 * Deals with hero's movement
	 * @param currentMap
	 * @param xHero
	 * @param yHero
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
	 * Switches between armed and unarmed state for the hero
	 * @param change
	 */
	public void setArmed(boolean change) {
		armed = change;
		Ch = change ? 'A' : 'H';
	}
	/**
	 * Verifies if hero is armed
	 * @return boolean
	 */
	public boolean isArmed() {
		return armed;
	}
}

