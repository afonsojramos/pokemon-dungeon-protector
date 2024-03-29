package dkeep.logic;

import java.util.Random;

public class Ogre extends Person{
	Random rand = new Random();
	private Club club;
	private static int nOgres = 0;
	private static int pos = 1; //posicao dos ogre criados com o construtor sem parametros
	private int stuned;
	
	public Ogre(String name, int x, int y, char Ch){
		super(name,x,y, Ch);
		club = new Club(x+1,y);
		nOgres++;
		pos += 2;
		stuned = 0;
	}

	public Ogre(String name, int x, int y) {// construtor default
		this(name, x, y, 'O');
	}

	public Ogre() {
		this("ogre" + nOgres, (pos % 6), (pos / 6) + 1, 'O');
	}
	
	public Ogre(int x, int y) {
		this("ogre" + nOgres, x, y, 'O');
	}

	public int getClubX () { return club.getX(); }
	
	public int getClubY () { return club.getY(); }
	
	public char getClubCh () { return club.getCh(); }
	
	public int getX () { return x; }
	
	public int getY () { return y; }
	
	public char getCh () { return Ch; }
	
	public void setClubX (int x) { club.setX(x); }
	
	public void setClubY (int y) { club.setY(y); }
	
	public void setClubCh (char ch) { club.setCh(ch); }
	
    public void setX (int x) { this.x = x; }
	
	public void setY (int y) { this.y = y; }
	
	public void setCh (char ch) { this.Ch = ch; }
	
	public void doStep(MapLevel currentMap, int xHero, int yHero) {
		if (!this.isStuned()) { // is not stuned
			boolean possibleMove = false;
			do {
				int randomNum = rand.nextInt(4); // random entre [min, max] : int randomNum = rand.nextInt((max - min) + 1) + min;
				switch (randomNum) {
				case 0: // Ogre anda para cima
					
					if ((currentMap.isAboveWall(x, y - 1)) || (currentMap.isOnTheDoor(x, y - 1))) {
						possibleMove = false;
					} else {
						prevY = y;
						y--;
						currentMap.setPosUsed(x, y);
						Ch = currentMap.isAboveKey(x, y) ? '$' : 'O';
						possibleMove = true;
					}
					break;
				case 1: // Ogre anda para baixo
					if ((currentMap.isAboveWall(x, y + 1)) || (currentMap.isOnTheDoor(x, y + 1))) {
						possibleMove = false;
					} else {
						prevY = y;
						y++;
						currentMap.setPosUsed(x, y);
						Ch = currentMap.isAboveKey(x, y) ? '$' : 'O';
						possibleMove = true;
					}
					break;
				case 2: // Ogre anda para a esquerda
					if ((currentMap.isAboveWall(x - 1, y)) || (currentMap.isOnTheDoor(x - 1, y))) {
						possibleMove = false;
					} else {
						prevX = x;
						x--;
						currentMap.setPosUsed(x, y);
						Ch = currentMap.isAboveKey(x, y) ? '$' : 'O';
						possibleMove = true;
					}
					break;
				case 3: // Ogre anda para a direita
					if ((currentMap.isAboveWall(x + 1, y)) || (currentMap.isOnTheDoor(x + 1, y))) {
						possibleMove = false;
					} else {
						prevX = x;
						x++;
						currentMap.setPosUsed(x, y);
						Ch = currentMap.isAboveKey(x, y) ? '$' : 'O';
						possibleMove = true;
					}
					break;
				}
			} while (!possibleMove);
		} else {// is stuned; don't move
			currentMap.setPosUsed(x, y);
			this.lessStuned();
		}
		club.move(currentMap, x, y);
	}

	public boolean isAdjacent(int x1, int y1, int x2, int y2) {
		if (x1 == x2 && (y2 == (y1 - 1) || y2 == (y1 + 1))) {
			return true;
		}
		if (y1 == y2 && (x2 == (x1 - 1) || x2 == (x1 + 1))) {
			return true;
		}
		if (y1 == y2 && x1 == x2) {
			return true;
		}
		return false;
	}
	
	public boolean isStuned() {
		return stuned > 0;
	}
	
	public void stun() {
		stuned = 2;
		Ch = '8';
	}
	
	public void lessStuned() {
		stuned--;
		if(stuned == 0){
			this.setCh('O');
		}
	}
	
	public boolean isClubVisible (MapLevel currentMap) {
		return !currentMap.isElementAtPos(club.getX(), club.getY());
	}
	
	public boolean isInInvalidPos (char [][] mapArray) {
		return (mapArray[y][x] != ' ');
	}
	
	public boolean isClubAdjacent(int x, int y) {
		int xClub = club.getX(), yClub = club.getY();
				if (x == xClub && (yClub == (y - 1) || yClub == (y + 1))) {
					return true;
				}
				if (y == yClub && (xClub == (x - 1) || xClub == (x + 1))) {
					return true;
				}
				if (y == yClub && x == xClub) {
					return true;
				}
				return false;
	}
	
}
