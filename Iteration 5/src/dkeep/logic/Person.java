package dkeep.logic;

public class Person implements java.io.Serializable{
	
	private String name;
	
	protected char Ch;
	
	protected int x, y; //posicao da personagem
	
	protected int prevX, prevY; //posicao anterior da personagem
	/**
	 * construtor de Person (classe mae)
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
	 * retorna o nome de Person
	 * @return
	 */
	public String getName() {return name;}
	/**
	 * retorna o x de Person
	 * @return
	 */
	public int getX() {return x;}
	/**
	 * retorna o y de Person
	 * @return
	 */
	public int getY() {return y;}
	/**
	 * retorna o x anterior ao movimento de Person
	 * @return
	 */
	public int getPrevX() {return prevX;}
	/**
	 * retorna o y anterior ao movimento de Person
	 * @return
	 */
	public int getPrevY() {return prevY;}
	/**
	 * retorna o Ch de Person
	 * @return
	 */
	public char getCh() {return Ch;}
	/**
	 * altera o nome de Person
	 * @param name
	 */
	public void setName(String name) { this.name = name;}
	/**
	 * altera o x de Person
	 * @param x
	 */
	public void setX(int x) { this.x = x;}
	/**
	 * altera o y de Person
	 * @param y
	 */
	public void setY(int y) { this.y = y;}
	/**
	 * altera o Ch de Person
	 * @param Ch
	 */
	public void setCh (char Ch) { this.Ch = Ch; }
	/**
	 * trata do movimento de Person (funcao virtual)
	 * @param currentMap
	 * @param xHero
	 * @param yHero
	 */
	public void doStep (MapLevel currentMap, int xHero, int yHero) {}
	/**
	 * verifica se (x1, y1) e adjacente a (x2, y2)
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
