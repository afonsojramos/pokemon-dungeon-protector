package dkeep.logic;

public class Person implements java.io.Serializable{
	
	private String name;
	
	protected char Ch;
	
	protected int x, y; //posicao da personagem
	
	protected int prevX, prevY; //posicao anterior da personagem
	
	public Person(String name, int x, int y, char Ch) {
		this.name = name;
		this.x = x;
		this.y = y;
		prevX = x;
		prevY = y;
		this.Ch = Ch;
	}
	
	public String getName() {return name;}
	
	public int getX() {return x;}
	
	public int getY() {return y;}
	
	public int getPrevX() {return prevX;}
	
	public int getPrevY() {return prevY;}
	
	public char getCh() {return Ch;}
	
	public void setName(String name) { this.name = name;}
	
	public void setX(int x) { this.x = x;}
	
	public void setY(int y) { this.y = y;}
	
	public void setCh (char Ch) { this.Ch = Ch; }
	
	public void doStep (MapLevel currentMap, int xHero, int yHero) {}
	
	public boolean isAdjacent(int x1, int y1, int x2, int y2) {
		return false;
	}

}
