package dkeep.logic;

import java.util.Random;
/**
 * Club.java - Class that deals with the club
 */
public class Club implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	Random rand = new Random();
	private int x, y, prevX, prevY;
	private char Ch;
	/**
	 * Club constructor
	 * @param x
	 * @param y
	 * @param Ch
	 */
	public Club (int x, int y, char Ch){
		this.x = x;
		this.y = y;
		prevX = x;
		prevY = y;
		this.Ch = Ch;
		
	}
	/**
	 * Club constructor
	 * @param x
	 * @param y
	 */
	public Club (int x, int y){ //construtor default
		this(x, y, '*');
	}
	/**
	 * Gets club's X
	 * @return x
	 */
	public int getX() {return x;}
	/**
	 * Gets club's y
	 * @return y
	 */
	public int getY() {return y;}
	/**
	 * Gets club's char
	 * @return Char
	 */
	public char getCh() {return Ch;}
	/**
	 * Deals with clubs position relative to the ogre's position
	 * @param currentMap
	 * @param xOgre
	 * @param yOgre
	 */
	public void move (MapLevel currentMap, int xOgre, int yOgre) {
		boolean possibleMove = false;
		do {int randomNum2 = rand.nextInt(4); // random entre [min, max] : int randomNum = rand.nextInt((max - min) + 1) + min;
			switch (randomNum2) {
			case 0: possibleMove = clubUp(currentMap, xOgre, yOgre);			break;
			case 1: possibleMove = clubDown(currentMap, xOgre, yOgre);			break;
			case 2: possibleMove = clubLeft(currentMap, xOgre, yOgre);			break;
			case 3: possibleMove = clubRight(currentMap, xOgre, yOgre);			break;
			default:	break;} } while (!possibleMove);
	}
	/**
	 * Places club under ogre
	 * @param currentMap
	 * @param xOgre
	 * @param yOgre
	 * @return boolean
	 */
	public boolean clubDown(MapLevel currentMap, int xOgre, int yOgre){
		if ((currentMap.isAboveWall(xOgre, yOgre + 1)) || (currentMap.isOnTheDoor(xOgre, yOgre + 1))) {
			return false;
		} else {
			prevX = x; prevY = y;
			x = xOgre; y = yOgre + 1;
			Ch = currentMap.isAboveKey(x, y) ? '$' : '*';
			return true;
		}
	}
	/**
	 * Places club left to ogre
	 * @param currentMap
	 * @param xOgre
	 * @param yOgre
	 * @return boolean
	 */
	public boolean clubLeft(MapLevel currentMap, int xOgre, int yOgre){
		if ((currentMap.isAboveWall(xOgre - 1, yOgre)) || (currentMap.isOnTheDoor(xOgre - 1, yOgre))) {
			return false;
		} else {
			prevX = x; prevY = y;
			x = xOgre - 1; y = yOgre;
			Ch = currentMap.isAboveKey(x, y) ? '$' : '*';
			return true;
		}
	}
	/**
	 * Places club right to ogre
	 * @param currentMap
	 * @param xOgre
	 * @param yOgre
	 * @return boolean
	 */
	public boolean clubRight(MapLevel currentMap, int xOgre, int yOgre){
		if ((currentMap.isAboveWall(xOgre + 1, yOgre)) || (currentMap.isOnTheDoor(xOgre + 1, yOgre))) {
			return false;
		}else {
			prevX = x; prevY = y;
			x = xOgre + 1; y = yOgre;
			Ch = currentMap.isAboveKey(x, y) ? '$' : '*';
			return true;
		}
	}
	/**
	 * Places club above ogre
	 * @param currentMap
	 * @param xOgre
	 * @param yOgre
	 * @return boolean
	 */
	public boolean clubUp(MapLevel currentMap, int xOgre, int yOgre){
		if ((currentMap.isAboveWall(xOgre, yOgre - 1)) || (currentMap.isOnTheDoor(xOgre, yOgre - 1))) {
			return false;
		}else {
			prevX = x; prevY = y;
			x = xOgre; y = yOgre - 1;
			Ch = currentMap.isAboveKey(x, y) ? '$' : '*';
			return true;
		}
	}
}