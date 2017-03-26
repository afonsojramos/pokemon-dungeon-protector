package dkeep.logic;

import java.util.Random;

public class Club implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	Random rand = new Random();
	private int x, y, prevX, prevY;
	private char Ch;
	/**
	 * construtor do club
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
	 * construtor do club
	 * @param x
	 * @param y
	 */
	public Club (int x, int y){ //construtor default
		this(x, y, '*');
	}
	/**
	 * retorna x do club
	 * @return
	 */
	public int getX() {return x;}
	/**
	 * retorna y do club
	 * @return
	 */
	public int getY() {return y;}
	/**
	 * retorna Ch do Club
	 * @return
	 */
	public char getCh() {return Ch;}
	/**
	 * trata da posicao em que o club vai estar aposto o movimento do seu respetivo ogre
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
	 * metodo para colocar  o club numa posicao abaixo do seu respetivo ogre
	 * @param currentMap
	 * @param xOgre
	 * @param yOgre
	 * @return
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
	 * metodo para colocar  o club numa posicao a esquerda do seu respetivo ogre
	 * @param currentMap
	 * @param xOgre
	 * @param yOgre
	 * @return
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
	 * metodo para colocar  o club numa posicao a direita do seu respetivo ogre
	 * @param currentMap
	 * @param xOgre
	 * @param yOgre
	 * @return
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
	 * metodo para colocar  o club numa posicao a cima do seu respetivo ogre
	 * @param currentMap
	 * @param xOgre
	 * @param yOgre
	 * @return
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