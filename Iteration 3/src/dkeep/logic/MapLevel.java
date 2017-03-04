package dkeep.logic;

import java.util.Vector;

public class MapLevel {
	private char currentMap [][];
	private boolean posUsed [][];//posicoes que foram ocupadas numa jogada
	private Vector<Integer> doorsPosX;
	private Vector<Integer> doorsPosY;
	private boolean doorsOpened = false;
	private int width, height; //largura e altura do mapa de jogo
	private Key key;
	private boolean instantaneousDoorOpen;// se true, portas abrem-se mal se apanha a chave
	
	public MapLevel (char [][] currentMap, Key key, Vector<Integer> doorsX, Vector<Integer> doorsY, boolean instantaneousDoorOpen) {
		doorsPosX = doorsX;
		doorsPosY = doorsY;
		this.currentMap = currentMap;
		this.instantaneousDoorOpen = instantaneousDoorOpen;
		width = currentMap[0].length;
		height = currentMap.length;
		posUsed = new boolean [height][width];
		boolean keyFound = false;
		for (int i = 0; i < height; i++){
			for (int j = 0; j < width; j++) {
				posUsed[i][j] = false;
			}
		}
		this.key = key;
	}
	
	public boolean isAboveKey (int x, int y) {
		return ((key.getX() == x) && (key.getY() == y) && !key.isFound());
	}
	
	public Key getKey() {return key;}
	
	public boolean isAboveWall (int x, int y) {
		return currentMap[y][x] == 'X';
	}
	
	public boolean isKeyFound () {
		return key.isFound();
	}
	
	public boolean isOnTheDoor (int x, int y) {
		for (int i = 0; i < doorsPosX.size(); i++) {
			if (x == doorsPosX.get(i) && y == doorsPosY.get(i)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isDoorOpen() {
		return doorsOpened;
	}
	
	public void openDoors () {
		for (int i = 0; i < doorsPosX.size(); i++) {
			currentMap[doorsPosY.get(i)][doorsPosX.get(i)] = 'S';
		}
		doorsOpened = true;
	}
	
	public void setKeyFound () {
		if (instantaneousDoorOpen) { //abre logo as portas
			this.openDoors();
		}
		key.setFound();
		currentMap[key.getY()][key.getX()] = ' '; //apagar chave do mapa
	}
	
	public boolean isElementAtPos (int x, int y) {
		return posUsed[y][x];
	}
	
	public void setPosUsed (int x, int y) {
		posUsed[y][x] = true;
	}
	
	public void clearPosUsed () {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				posUsed[i][j] = false;
			}
		}
	}
}
