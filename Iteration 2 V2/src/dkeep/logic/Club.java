package dkeep.logic;


public class Club {

	private int x, y, prevX, prevY;
	
	private char Ch;
	
	public Club (int x, int y, char Ch){
		this.x = x;
		this.y = y;
		prevX = x;
		prevY = y;
		this.Ch = Ch;
	}
	
	public Club (int x, int y){ //construtor default
		this(x, y, '*');
	}
	
	public int getX() {return x;}
	
	public int getY() {return y;}
	
	public int getPrevX() {return prevX;}
	
	public int getPrevY() {return prevY;}
	
	public char getCh() {return Ch;}
	
	public void setX(int x) { this.x = x;}
	
	public void setY(int y) { this.y = y;}
	
	public void setPrevX(int x) { this.prevX = x;}
	
	public void setPrevY(int y) { this.prevY = y;}
	
	public void setCh (char Ch) { this.Ch = Ch; }
	
	public void changePos (int x, int y) {
		prevX = this.x;
		prevY = this.y;
		this.x = x;
		this.y = y;
	}
}