package dkeep.logic;

import java.io.File;
import java.util.Vector;

import dkeep.gui.PlayMusic;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;

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
	 * construtor do mapa atual do jogo
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
	 * retorna a altura do mapa de jogo
	 * @return
	 */
	public int getMapHeight(){
		return height;
	}
	/**
	 * retorna a largura do mapa de jogo
	 * @return
	 */
	public int getMapWidth(){
		return width;
	}
	/**
	 * retorna o array do mapa atual de jogo
	 * @return
	 */
	public char [][] getMap() {
		return currentMap;
	}
	/**
	 * verifica se neste nivel as portas abrem-se logo mal se apanhe a chave
	 * @return
	 */
	public boolean isInstantaneousDoorOpen() {
		return instantaneousDoorOpen;
	}
	/**
	 * verifica se um elemento na posicao (x, y) esta em cima da chave no mapa
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isAboveKey (int x, int y) {
		return ((key.getX() == x) && (key.getY() == y) && !key.isFound());
	}
	/**
	 * retorna a chave do jogo
	 * @return
	 */
	public Key getKey() {return key;}
	/**
	 * verifica se um elemento na posicao (x, y) esta em cima de uma parede no mapa
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isAboveWall (int x, int y) {
		return currentMap[y][x] == 'X';
	}
	/**
	 * verifica se a chave ja foi encontrada pelo heroi
	 * @return
	 */
	public boolean isKeyFound () {
		return key.isFound();
	}
	/**
	 * verifica se um elemento na posicao (x, y) esta em cima de uma porta no mapa
	 * @param x
	 * @param y
	 * @return
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
	 * verifica se as portas estao abertas
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isDoorOpen(int x, int y) {
		return doorsOpen[y][x];
	}
	/**
	 * verifica se e uma porta nas bordas do mapa
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isEdgeDoor(int x, int y) {
		if (!doorsOpen[y][x])	return false;
		else if (x == doorsOpen.length-1 || y == doorsOpen[0].length-1 || x == 0 || y == 0)	return true;
		else	return false;
	}
	/**
	 * metodo que abre as portas do mapa
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
	 * alterao o estado da chave para chave encontrada
	 */
	public void setKeyFound () {
		if (instantaneousDoorOpen) { //abre logo as portas
			this.openDoors(-1,-1);
		}
		key.setFound();
		currentMap[key.getY()][key.getX()] = ' '; //apagar chave do mapa
	}
	/**
	 * verifica se ja existe um elemento no mapa na posicao (x, y) - usada para verificar sobreposicao de elemento do jogo -
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isElementAtPos (int x, int y) {
		return posUsed[y][x];
	}
	/**
	 * guardar a informacao de que a posicao (x,y) do mapa esta agora ocupada por um elemento qualquer do jogo
	 * @param x
	 * @param y
	 */
	public void setPosUsed (int x, int y) {
		posUsed[y][x] = true;
	}
	/**
	 * desocupa todas as posicoes do mapa que anteriormente estavam a ser ocupadas por elementos do jogo
	 */
	public void clearPosUsed () {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				posUsed[i][j] = false;
			}
		}
	}
}
