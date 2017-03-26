package dkeep.logic;

/**
 * Person.java - Class that deals with person
 */
public class Person implements java.io.Serializable{
	
	private String name;
	
	protected char Ch;
	
	protected int x, y; //posicao da personagem
	
	protected int prevX, prevY; //posicao anterior da personagem
	/**
	 * Person's constructor
	 * @param name
	 * @param x
	 * @param y
	 * @param Ch
	 */
	public Person(String name, int x, int y, char Ch) {
		this.name = name;
		this.x = x;
		this.y = y;
		prevX = x;
		prevY = y;
		this.Ch = Ch;
	}
	/**
	 * Gets person name
	 * @return name
	 */
	public String getName() {return name;}
	/**
	 * Gets person's x
	 * @return x
	 */
	public int getX() {return x;}
	/**
	 * Gets person's y
	 * @return y
	 */
	public int getY() {return y;}
	/**
	 * Gets person's previous x
	 * @return Prevx
	 */
	public int getPrevX() {return prevX;}
	/**
	 * Gets person's previous y
	 * @return Prevy
	 */
	public int getPrevY() {return prevY;}
	/**
	 * Gets person's char
	 * @return char
	 */
	public char getCh() {return Ch;}
	/**
	 * Sets person's name
	 */
	public void setName(String name) { this.name = name;}
	/**
	 * Sets person's X
	 * @param x
	 */
	public void setX(int x) { this.x = x;}
	/**
	 * Sets person's Y
	 * @param y
	 */
	public void setY(int y) { this.y = y;}
	/**
	 * Changes person's char
	 * @param char
	 */
	public void setCh (char Ch) { this.Ch = Ch; }
	/**
	 * Virtual function that does the person's movement
	 * @param currentMap
	 * @param xHero
	 * @param yHero
	 */
	public void doStep (MapLevel currentMap, int xHero, int yHero) {}
	/**
	 * checks if something is adjacent to another
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	public boolean isAdjacent(int x1, int y1, int x2, int y2) {
		return false;
	}

}
