package dkeep.logic;

public class Key implements java.io.Serializable{
	private boolean found;
	private int x, y;
	/**
	 * construtor da chave
	 * @param x
	 * @param y
	 */
	public Key (int x, int y) {
		found = false;
		this.x = x;
		this.y = y;
	}
	/**
	 * retorna x da chave
	 * @return
	 */
	public int getX () {
		return x;
	}
	/**
	 * retorna y da chave
	 * @return
	 */
	public int getY () {
		return y;
	}
	/**
	 * verifica se a chave ja foi encontrada pelo heroi
	 * @return
	 */
	public boolean isFound () {
		return found;
	}
	/**
	 * altera a condicao da chave para chave encontrada
	 */
	public void setFound () {
		found = true;
	}
}
