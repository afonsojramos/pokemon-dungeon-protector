package dkeep.logic;

import java.io.File;
import java.util.Vector;

import dkeep.gui.PlayMusic;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
/**  
 * MapLevel.java - Class regarding the maps structure
 */ 
public class MapLevel implements java.io.Serializable{
	private char currentMap [][];
	private boolean posUsed [][];//posicoes que foram ocupadas numa jogada
	private Vector<Integer> doorsPosX;
	private Vector<Integer> doorsPosY;
	private boolean doorsOpen [][];
	private int width, height; //largura e altura do mapa de jogo
	private Key key;
	private boolean instantaneousDoorOpen;// se true, portas abrem-se mal se apanha a chave
	private PlayMusic play;
	/**
	 * Current map's constructor
	 * @param currentMap
	 * @param key
	 * @param doorsX
	 * @param doorsY
	 * @param instantaneousDoorOpen
	 */
	public MapLevel (char [][] currentMap, Key key, Vector<Integer> doorsX, Vector<Integer> doorsY, boolean instantaneousDoorOpen) {
		doorsPosX = doorsX;
		doorsPosY = doorsY;
		this.currentMap = currentMap;
		this.instantaneousDoorOpen = instantaneousDoorOpen;
		width = currentMap[0].length;
		height = currentMap.length;
		posUsed = new boolean [height][width];
		doorsOpen = new boolean [height][width];
		for (int i = 0; i < height; i++){
			for (int j = 0; j < width; j++) {
				posUsed[i][j] = false;
				doorsOpen[i][j] = false;
			}
		}
		this.key = key;
	}
	/**
	 * Gets map height
	 * @return height
	 */
	public int getMapHeight(){
		return height;
	}
	/**
	 * Gets map width
	 * @return width
	 */
	public int getMapWidth(){
		return width;
	}
	/**
	 * Gets current map array
	 * @return currentMap
	 */
	public char [][] getMap() {
		return currentMap;
	}
	/**
	 * Verifies if this level instantaneously opens doors
	 * @return instantaneousDoorOpen
	 */
	public boolean isInstantaneousDoorOpen() {
		return instantaneousDoorOpen;
	}
	/**
	 * Verifies if a certain element is above key
	 * @param x
	 * @param y
	 * @return boolean
	 */
	public boolean isAboveKey (int x, int y) {
		return ((key.getX() == x) && (key.getY() == y) && !key.isFound());
	}
	/**
	 * Returns key
	 * @return Key
	 */
	public Key getKey() {return key;}
	/**
	 * Returns if a certain element is above a wall
	 * @param x
	 * @param y
	 * @return boolean
	 */
	public boolean isAboveWall (int x, int y) {
		return currentMap[y][x] == 'X';
	}
	/**
	 * Checks if key has been found
	 * @return boolean
	 */
	public boolean isKeyFound () {
		return key.isFound();
	}
	/**
	 * Checks if a certain element is above a door
	 * @param x
	 * @param y
	 * @return boolean
	 */
	public boolean isOnTheDoor (int x, int y) {
		for (int i = 0; i < doorsPosX.size(); i++) {
			if (x == doorsPosX.get(i) && y == doorsPosY.get(i)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * Checks if a certain door is opened
	 * @param x
	 * @param y
	 * @return boolean
	 */
	public boolean isDoorOpen(int x, int y) {
		return doorsOpen[y][x];
	}
	/**
	 * Checks if a certain door belong to the edge
	 * @param x
	 * @param y
	 * @return boolean
	 */
	public boolean isEdgeDoor(int x, int y) {
		if (!doorsOpen[y][x])	return false;
		else if (x == doorsOpen.length-1 || y == doorsOpen[0].length-1 || x == 0 || y == 0)	return true;
		else	return false;
	}
	/**
	 * Method that opens edge doors and certain door
	 * @param x
	 * @param y
	 */
	public void openDoors (int x, int y) {
		@SuppressWarnings("unused")
		JFXPanel fxPanel = new JFXPanel();
		Media hit = new Media(new File("Utils/door.wav").toURI().toString());
		play = new PlayMusic(hit);	play.playSound();
		for (int i = 0; i < doorsPosX.size(); i++) {
			if (doorsPosX.get(i) == width - 1){				currentMap[doorsPosY.get(i)][width-1] = 'S';	doorsOpen[doorsPosY.get(i)][width - 1] = true;
			} else if (doorsPosX.get(i) == 0){				currentMap[doorsPosY.get(i)][0] = 'S';			doorsOpen[doorsPosY.get(i)][0] = true;
			} else if (doorsPosY.get(i) == height - 1){		currentMap[height-1][doorsPosX.get(i)] = 'S';	doorsOpen[height - 1][doorsPosX.get(i)] = true;	
			} else if (doorsPosY.get(i) == 0){				currentMap[0][doorsPosX.get(i)] = 'S';			doorsOpen[0][doorsPosX.get(i)] = true;
			}else if (doorsPosX.get(i) == x){if (doorsPosY.get(i) == y){ currentMap[y][x] = 'S';			doorsOpen[y][x] = true;}}}
	}
	/**
	 * Sets the key as found
	 */
	public void setKeyFound () {
		if (instantaneousDoorOpen) { //abre logo as portas
			this.openDoors(-1,-1);
		}
		key.setFound();
		currentMap[key.getY()][key.getX()] = ' '; //apagar chave do mapa
	}
	/**
	 * Checks if an element is at a certain element
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isElementAtPos (int x, int y) {
		return posUsed[y][x];
	}
	/**
	 * Set the position as used
	 * @param x
	 * @param y
	 */
	public void setPosUsed (int x, int y) {
		posUsed[y][x] = true;
	}
	/**
	 * Clear positions used
	 */
	public void clearPosUsed () {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				posUsed[i][j] = false;
			}
		}
	}
}
