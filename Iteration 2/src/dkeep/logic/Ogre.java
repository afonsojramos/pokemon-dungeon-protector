package dkeep.logic;

import java.util.Random;

public class Ogre extends Person{
	
	private Club club;
	private static int nOgres = 0;

	public Ogre(String name, int x, int y, char Ch){
		super(name,x,y, Ch);
		club = new Club(x+1,y);
		nOgres++;
	}
	
	public Ogre(String name, int x, int y) {//construtor default
		this(name, x, y, 'O');
	}
	
	public Ogre(){
		this("ogre" + nOgres, (nOgres % 7), (nOgres/7), 'O');
		nOgres++;
	}
	
	public void doStep (int x, int y) {
		prevX = this.x;
		prevY = this.y;
		this.x = x;
		this.y = y;
	}
	
	public int getClubX () { return club.getX(); }
	
	public int getClubY () { return club.getY(); }
	
	public char getClubCh () { return club.getCh(); }
	
	public void setClubX (int x) { club.setX(x); }
	
	public void setClubY (int y) { club.setY(y); }
	
	public void setClubCh (char ch) { club.setCh(ch); }
	
}
