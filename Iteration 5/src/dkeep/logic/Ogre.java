package dkeep.logic;

import java.util.Random;
/**  
* Ogre.java - Class regarding the ogre  
* @see Person
*/ 
public class Ogre extends Person implements java.io.Serializable{
	Random rand = new Random();
	private Club club;
	private static int nOgres = 0;
	private static int pos = 1; //posicao dos ogre criados com o construtor sem parametros
	private int stuned;
	/**
	 * Ogre's constructor
	 * @param name
	 * @param x
	 * @param y
	 * @param Ch
	 * @param mapArray
	 */
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
	/**
	 * Ogre's constructor
	 * @param mapArray
	 */
	public Ogre(char [][] mapArray) {
		this("ogre" + nOgres, (pos % 6), (pos / 6) + 1, 'O', mapArray);
	}
	/**
	 * Ogre's constructor
	 * @param x
	 * @param y
	 * @param mapArray
	 */
	public Ogre(int x, int y, char [][] mapArray) {
		this("ogre" + nOgres, x, y, 'O', mapArray);
	}
	/**
	 * Gets club's X
	 * @return x
	 */
	public int getClubX () { return club.getX(); }
	/**
	 * Gets club's y
	 * @return y
	 */
	public int getClubY () { return club.getY(); }
	/**
	 * Gets club's char
	 * @return char
	 */
	public char getClubCh () { return club.getCh(); }
	/**
	 * Gets ogre's X
	 * @return x
	 */
	public int getX () { return x; }
	/**
	 * Gets ogre's y
	 * @return y
	 */
	public int getY () { return y; }
	/**
	 * Gets ogre's char
	 * @return char
	 */
	public char getCh () { return Ch; }
	/**
	 * Set ogre's char
	 * @param char
	 */
	public void setCh (char ch) { this.Ch = ch; }
	/**
	 * Deals with Ogre's movement
	 * @param currentMap
	 * @param xHero
	 * @param yHero
	 */
	public void doStep(MapLevel currentMap, int xHero, int yHero) {
		if (!this.isStuned()) { boolean possibleMove = false;
			do {int randomNum = rand.nextInt(4); // random entre [min, max] : int randomNum = rand.nextInt((max - min) + 1) + min;
				switch (randomNum) {
				case 0: possibleMove = ogreUp(currentMap, xHero, yHero);	break;
				case 1: possibleMove = ogreDown(currentMap, xHero, yHero);	break;
				case 2: possibleMove = ogreLeft(currentMap, xHero, yHero);	break;
				case 3: possibleMove = ogreRight(currentMap, xHero, yHero);
					break; } } while (!possibleMove);
		} else {currentMap.setPosUsed(x, y);
				this.lessStuned(); 	
				return;	}	
		club.move(currentMap, x, y);
	}
	/**
	 * Ogre moves up
	 * @param currentMap
	 * @param xHero
	 * @param yHero
	 * @return boolean
	 */
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
	/**
	 * Ogre moves down
	 * @param currentMap
	 * @param xHero
	 * @param yHero
	 * @return boolean
	 */
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
	/**
	 * Ogre moves right
	 * @param currentMap
	 * @param xHero
	 * @param yHero
	 * @return boolean
	 */
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
	/**
	 * Ogre moves left
	 * @param currentMap
	 * @param xHero
	 * @param yHero
	 * @return boolean
	 */
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
	/**
	 * Checks if ogre is adjacent to something
	 * @return boolean
	 */
	public boolean isAdjacent(int x1, int y1, int x2, int y2) {
		if (x1 == x2 && (y2 == (y1 - 1) || y2 == (y1 + 1))) {return true;}
		if (y1 == y2 && (x2 == (x1 - 1) || x2 == (x1 + 1))) {return true;}
		if (y1 == y2 && x1 == x2) {return true;}
		return false;
	}
	/**
	 * Checks if ogre is stuned
	 * @return boolean
	 */
	public boolean isStuned() {
		return stuned > 0;
	}
	/**
	 * Changes the ogre's state to stuned
	 */
	public void stun() {
		stuned = 2;
		Ch = '8';
	}
	/**
	 * Decrements one to the stuned state
	 */
	public void lessStuned() {
		stuned--;
		if(stuned == 0){
			this.setCh('O');
		}
	}
	/**
	 * Checks if club is visible
	 * @param currentMap
	 * @return boolean
	 */
	public boolean isClubVisible (MapLevel currentMap) {
		return !currentMap.isElementAtPos(club.getX(), club.getY());
	}
	/**
	 * Checks if ogre is in invalid position
	 * @param mapArray
	 * @return boolean
	 */
	public boolean isInInvalidPos (char [][] mapArray) {
		return (mapArray[y][x] != ' ');
	}
	/**
	 * Checks if club is adjacent to something
	 * @param x
	 * @param y
	 * @return boolean
	 */
	public boolean isClubAdjacent(int x, int y) {
		int xClub = club.getX(), yClub = club.getY();
		if (x == xClub && (yClub == (y - 1) || yClub == (y + 1))) {return true;}
		if (y == yClub && (xClub == (x - 1) || xClub == (x + 1))) {return true;}
		if (y == yClub && x == xClub) {return true;}
		return false;
	}
	/**
	 * Restarts static variables
	 */
	public void restartVariables() {
		nOgres = 0;
		pos = 1;
	}
}
