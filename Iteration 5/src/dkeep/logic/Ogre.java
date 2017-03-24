package dkeep.logic;

import java.util.Random;

public class Ogre extends Person implements java.io.Serializable{
	Random rand = new Random();
	private Club club;
	private static int nOgres = 0;
	private static int pos = 1; //posicao dos ogre criados com o construtor sem parametros
	private int stuned;
	
	public Ogre(String name, int x, int y, char Ch, char [][] mapArray){
		super(name,x,y, Ch);
		if (mapArray != null) {
			if (mapArray[y][x+1] == ' ') {		club = new Club(x+1,y);
			} else if (mapArray[y][x-1] == ' ') {	club = new Club(x-1,y);
			} else if (mapArray[y+1][x] == ' ') {	club = new Club(x,y+1);
			} else if (mapArray[y-1][x] == ' ') {	club = new Club(x,y-1);			}
		} else { club = new Club(x+1,y);}
		nOgres++;
		pos += 2;
		stuned = 0;
	}

	public Ogre(char [][] mapArray) {
		this("ogre" + nOgres, (pos % 6), (pos / 6) + 1, 'O', mapArray);
	}
	
	public Ogre(int x, int y, char [][] mapArray) {
		this("ogre" + nOgres, x, y, 'O', mapArray);
	}

	public int getClubX () { return club.getX(); }
	
	public int getClubY () { return club.getY(); }
	
	public char getClubCh () { return club.getCh(); }
	
	public int getX () { return x; }
	
	public int getY () { return y; }
	
	public char getCh () { return Ch; }
	
	public void setCh (char ch) { this.Ch = ch; }
	
	public void doStep(MapLevel currentMap, int xHero, int yHero) {
		if (!this.isStuned()) { boolean possibleMove = false;
			do {int randomNum = rand.nextInt(4); // random entre [min, max] : int randomNum = rand.nextInt((max - min) + 1) + min;
				switch (randomNum) {
				case 0: possibleMove = ogreUp(currentMap, xHero, yHero);
					break;
				case 1: possibleMove = ogreDown(currentMap, xHero, yHero);
					break;
				case 2: possibleMove = ogreLeft(currentMap, xHero, yHero);
					break;
				case 3: possibleMove = ogreRight(currentMap, xHero, yHero);
					break; } } while (!possibleMove);
		} else {currentMap.setPosUsed(x, y);
				this.lessStuned(); 		return;		}
		club.move(currentMap, x, y);
	}
	
	public boolean ogreUp(MapLevel currentMap, int xHero, int yHero){
		if ((currentMap.isAboveWall(x, y - 1)) || (currentMap.isOnTheDoor(x, y - 1))) {
			return false;
		} else {
			prevY = y;
			y--;
			currentMap.setPosUsed(x, y);
			Ch = currentMap.isAboveKey(x, y) ? '$' : 'O';
			return true;
		}
	}
	
	public boolean ogreDown(MapLevel currentMap, int xHero, int yHero){
		if ((currentMap.isAboveWall(x, y + 1)) || (currentMap.isOnTheDoor(x, y + 1))) {
			return false;
		} else {
			prevY = y;
			y++;
			currentMap.setPosUsed(x, y);
			Ch = currentMap.isAboveKey(x, y) ? '$' : 'O';
			return true;
		}
	}
	
	public boolean ogreRight(MapLevel currentMap, int xHero, int yHero){
		if ((currentMap.isAboveWall(x + 1, y)) || (currentMap.isOnTheDoor(x + 1, y))) {
			return false;
		} else {
			prevX = x;
			x++;
			currentMap.setPosUsed(x, y);
			Ch = currentMap.isAboveKey(x, y) ? '$' : 'O';
			return true;
		}
	}
	
	public boolean ogreLeft(MapLevel currentMap, int xHero, int yHero){
		if ((currentMap.isAboveWall(x - 1, y)) || (currentMap.isOnTheDoor(x - 1, y))) {
			return false;
		} else {
			prevX = x;
			x--;
			currentMap.setPosUsed(x, y);
			Ch = currentMap.isAboveKey(x, y) ? '$' : 'O';
			return true;
		}
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
	
	public void restartVariables() {
		nOgres = 0;
		pos = 1;
	}
}
