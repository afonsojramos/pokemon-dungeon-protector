package dkeep.logic;

import java.util.Vector;

public class MapLevel {
	private char currentMap [][];
	private char overlapedPos [][];
	private Vector<Integer> doorsPosX;
	private Vector<Integer> doorsPosY;
	private int width, height; //largura e altura do mapa de jogo
	private int state;
	private Key key;
	private int beginningXOfHero, beginningYOfHero;
	boolean heroArmed;
	
	public MapLevel (char [][] currentMap, int state, Vector<Person> characters) {
		doorsPosX = new Vector<Integer>();doorsPosY = new Vector<Integer>();
		this.currentMap = currentMap;
		this.state = state;
		width = currentMap[0].length;
		height = currentMap.length;
		overlapedPos = new char [height][width];
		boolean keyFound = false;
		for (int i = 0; i < height; i++){
			for (int j = 0; j < width; j++) {
				overlapedPos[i][j] = ' ';
				if(currentMap[i][j] == 'k') {
					overlapedPos[i][j] = 'k';
					key = new Key(j, i);
					keyFound = true;;
				} else if(currentMap[i][j] == 'I') {
					doorsPosX.add(j);
					doorsPosY.add(i);
				} else if(currentMap[i][j] == 'H') {
					beginningXOfHero = j;
					beginningYOfHero = i;
					heroArmed = false;
				} else if(currentMap[i][j] == 'A') {
					beginningXOfHero = j;
					beginningYOfHero = i;
					heroArmed = true;
				} else if(currentMap[i][j] == 'G') {
					Person g = new Guard (j, i);
					characters.add(g);
				} else if(currentMap[i][j] == 'O') {
					Person o = new Ogre (j, i);
					characters.add(o);
				}
			}
		}
		if(!keyFound) {
			key = null;
		}
	}
	
	public boolean isUnderKey (int x, int y) {
		return (key.getX() == x && key.getY() == y && !key.isFound());
	}
	
	public boolean isKeyFound () {
		return key.isFound();
	}
	
	public boolean isHeroArmed () {
		return heroArmed;
	}

	public int getState () {
		return state;
	}
	
	public int getBeginningXOfHero () {
		return beginningXOfHero;
	}
	
	public int getBeginningYOfHero () {
		return beginningYOfHero;
	}
	
	public String getStringMap () {
		StringBuilder tmp = new StringBuilder();
		for (int i = 0; i < height; i++){
			for (int j = 0; j < width; j++){
				tmp.append(currentMap[i][j]);
				tmp.append(" ");
			}
			tmp.append('\n');
		}
		return tmp.toString();
	}
	
	public void openDoors () {
		for (int i = 0; i < doorsPosX.size(); i++) {
			currentMap[doorsPosY.get(i)][doorsPosX.get(i)] = 'S';
		}
	}
	
	public void setKeyFound () {
		if (state == 1) {
			this.openDoors();
		}
		key.setFound();
	}
	
	public void setValuePos (int x, int y, char ch) {
		currentMap [y][x] = ch;
	}
	
	public boolean isCharAtPos (int x, int y, char ch) {
		return currentMap[y][x] == ch;
	}
	
	public char getOverlapChar (int x, int y) {
		return overlapedPos[y][x];
	}
	
	public void setOverlapedChar (int x, int y, char ch) {
		overlapedPos[y][x] = ch;
	}
	
	public void clearElement (int x, int y) {
		currentMap[y][x] = overlapedPos[y][x];
	}
	
	public void clearArrayOverlap() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				overlapedPos[i][j] = ' ';
			}
		}
		if(!key.isFound()) {
			overlapedPos[key.getY()][key.getX()] = 'k';
		}
	}
}
