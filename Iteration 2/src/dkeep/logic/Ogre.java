package dkeep.logic;

public class Ogre extends Person{
	
	private Club club;
	private static int nOgres = 1;
	private static int pos = -1; //posicao dos ogre criados com o construtor sem parametros

	public Ogre(String name, int x, int y, char Ch){
		super(name,x,y, Ch);
		club = new Club(x+1,y);
		nOgres++;
		pos += 2;
	}
	
	public Ogre(String name, int x, int y) {//construtor default
		this(name, x, y, 'O');
	}
	
	public Ogre(){
		this("ogre" + nOgres, (pos % 6), (pos/6) + 1, 'O');
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
