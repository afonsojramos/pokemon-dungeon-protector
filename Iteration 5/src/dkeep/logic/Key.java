package dkeep.logic;
/**  
 * Key.java - Class regarding the key
 */ 
public class Key implements java.io.Serializable{
	private boolean found;
	private int x, y;
	/**
	 * Key constructor
	 * @param x
	 * @param y
	 */
	public Key (int x, int y) {
		found = false;
		this.x = x;
		this.y = y;
	}
	/**
	 * Gets key's X
	 * @return x
	 */
	public int getX () {
		return x;
	}
	/**
	 * Gets key's Y
	 * @return y
	 */
	public int getY () {
		return y;
	}
	/**
	 * Checks if key has already been found
	 * @return boolean
	 */
	public boolean isFound () {
		return found;
	}
	/**
	 * Sets the key as found
	 */
	public void setFound () {
		found = true;
	}
}
