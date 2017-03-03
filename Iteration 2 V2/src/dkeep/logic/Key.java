package dkeep.logic;

public class Key {
	private char Ch;
	private boolean found;
	private int x, y;
	
	public Key (int x, int y) {
		this.Ch = Ch;
		found = false;
		this.x = x;
		this.y = y;
	}
	
	public int getX () {
		return x;
	}
	
	public int getY () {
		return y;
	}
	
	public void setX (int x) {
		this.x = x;
	}
	
	public void setY (int y) {
		this.y = y;
	}
	
	public boolean isFound () {
		return found;
	}
	
	public void setFound () {
		found = true;
	}
}
