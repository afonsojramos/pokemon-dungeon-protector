package dkeep.logic;

public class Person{
	
	private String name;
	
	private char Ch;
	
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
	
	public void setPrevX(int x) { this.prevX = x;}
	
	public void setPrevY(int y) { this.prevY = y;}
	
	public void setCh (char Ch) { this.Ch = Ch; }
	
	public void doStep (int x, int y) {}
}
