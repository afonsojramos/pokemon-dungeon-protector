package dkeep.logic;

public class Ogre extends Person{

	public Ogre(String name, int x, int y, char Ch){
		super(name,x,y, Ch);
	}
	
	public Ogre(String name, int x, int y) {//construtor default
		this(name, x, y, 'O');
	}
	
	public void doStep (int x, int y) {
		prevX = this.x;
		prevY = this.y;
		this.x = x;
		this.y = y;
	}
}
