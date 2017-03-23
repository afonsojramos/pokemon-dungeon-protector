package dkeep.logic;

import java.util.Random;

public class Club implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	Random rand = new Random();
	private int x, y, prevX, prevY;
	private char Ch;
	
	public Club (int x, int y, char Ch){
		this.x = x;
		this.y = y;
		prevX = x;
		prevY = y;
		this.Ch = Ch;
		
	}
	
	public Club (int x, int y){ //construtor default
		this(x, y, '*');
	}
	
	public int getX() {return x;}
	
	public int getY() {return y;}
	
	public char getCh() {return Ch;}
	
	public void move (MapLevel currentMap, int xOgre, int yOgre) {
		boolean possibleMove = false;
		do {
			int randomNum2 = rand.nextInt(4); // random entre [min, max] : int randomNum = rand.nextInt((max - min) + 1) + min;
			switch (randomNum2) {
			case 0: // Taco apontado para cima
				possibleMove = clubUp(currentMap, xOgre, yOgre);
				break;
			case 1: // Taco apontado para baixo
				possibleMove = clubDown(currentMap, xOgre, yOgre);
				break;
			case 2: // Taco apontado para a esquerda
				possibleMove = clubLeft(currentMap, xOgre, yOgre);
				break;
			case 3: // Taco apontado para a direita
				possibleMove = clubRight(currentMap, xOgre, yOgre);
				break;
			default:
					break;
			}
		} while (!possibleMove);
	}
	
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