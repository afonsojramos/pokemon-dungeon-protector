package dkeep.logic;
import java.io.File;
import java.util.*;

import dkeep.gui.PlayMusic;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;

/**  
 * GameMap.java - Class regarding the game logic  
 */ 
public class GameMap implements java.io.Serializable{

	Random rand = new Random();
	private char [][] mapArray = null;
	private MapLevel currentMap; //mapa de jogo
	private Hero hero;
	private Vector<Person> characters;
	private boolean instantaneousDoorOpen;
	private int new_x, new_y;
	private PlayMusic play;
	
	/**
	 * Constructor for a Game level
	 * Map array is the matrix for the game map
	 * MultipleOgres is a boolean that informs the constructor if it has to randomly generate the multiple ogres
	 * While nOgres is self explanatory being the number of ogres
	 * InstantaneousDoorOpen is a boolean that informs the constructor if it has to open door instantaneously, for example, with a lever
	 * @param mapArray
	 * @param multipleOgres
	 * @param nOgres
	 * @param instantaneousDoorOpen
	 */
	public GameMap(char [][] mapArray, boolean multipleOgres, int nOgres, boolean instantaneousDoorOpen) {
		/**
		 * inicializar variaveis
		 */
		new_x = 1; new_y = 1;
		this.instantaneousDoorOpen = instantaneousDoorOpen;
		this.mapArray = mapArray;
		currentMap = null;
		hero = new Hero("hero", 1, 1);
		/**
		 * inicializar personagens do tabuleiro de jogo
		 */
		characters = new Vector<Person>();//vetor do index 0 fica vazio
		if (multipleOgres) {
			if (nOgres == 0) {//gerar numero aleatorio de ogres
				int randomNum = rand.nextInt(5) + 1;
				System.out.print("\n\nNumero de ogres no nivel 3: " + randomNum + "\n\n");
				while (randomNum > 0) {	this.addOgreToLevel(); 	randomNum--; }
			} else {
				while (nOgres > 0) {	this.addOgreToLevel();	nOgres--;
	}}}}
	/**
	 * Returns a vector with all the game characters
	 * @return characters
	 */
	public Vector<Person> getCharacters() { return characters; }
	 /**
	  * Goes through the matrix to extract elements information
	  * @param isCreationMode
	  */
	public void readMap (boolean isCreationMode) {
		if(isCreationMode) { 	characters.clear();	}
		int width = mapArray[0].length, height = mapArray.length;
		char[][] tmpMapArray = mapArray.clone();
		Key key = null;
		Vector<Integer> doorX = new Vector<Integer>(), doorY = new Vector<Integer>();
		for (int i = 0; i < height; i++){
			for (int j = 0; j < width; j++) {
				 if(mapArray[i][j] == 'H') {
					hero.setX(j); hero.setY(i);
					hero.setArmed(false);
					mapArray[i][j] = ' ';
				} else if(mapArray[i][j] == 'k') {	key = new Key(j, i);
				} else if(mapArray[i][j] == 'I') {	doorX.add(j); doorY.add(i);
				} else if(mapArray[i][j] == 'A') {
					hero.setX(j); hero.setY(i);
					hero.setArmed(true);
					mapArray[i][j] = ' ';
				} else if(mapArray[i][j] == 'G') {
					Person g = new Guard (j, i);
					characters.add(g);
					mapArray[i][j] = ' ';
				} else if(mapArray[i][j] == 'O') {
					Person o = new Ogre (j, i, tmpMapArray);
					characters.add(o);
					mapArray[i][j] = ' ';
				} else if(mapArray[i][j] == '*') {	mapArray[i][j] = ' ';
		}}}
		currentMap = new MapLevel(mapArray, key, doorX, doorY, instantaneousDoorOpen);
	}
	/**
	 * Method that adds ogre to level
	 */
	public void addOgreToLevel() {
		Ogre ogre = null;
		do {
			ogre = new Ogre(null);
		}while(ogre.isInInvalidPos(mapArray));
		characters.add(ogre);
	}
	
	/**
	 * Verifies if hero loses the game or beats the last level
	 * @return boolean
	 */
	public boolean isEndOfGame() {
		int x = hero.getX(), y = hero.getY();
		int size = characters.size();
		for (int i = 0; i < size; i++) {
			if (characters.get(i) instanceof Guard) {
				Guard g = (Guard)characters.get(i);
				int xg = g.getX(), yg = g.getY();
				if(g.isAdjacent(x, y, xg, yg)) { return true;}
			} else if (characters.get(i) instanceof Ogre) {
				Ogre o = (Ogre)characters.get(i);
				int xo = o.getX(), yo = o.getY();
				if ((o.isAdjacent(x, y, xo, yo) && !hero.isArmed()) || (o.isClubAdjacent(x, y))) {	return true;	}}}
		if(currentMap.isOnTheDoor(x, y) && currentMap.isDoorOpen(x,y) && currentMap.isEdgeDoor(x, y)) {	return true;}
		return false;
	}
	/**
	 * Stuns ogres (if the hero is armed in that level which is checked in update)
	 */
	public void StunOgres() {
		int size = characters.size();
		if (hero.isArmed()) {
			for (int i = 0; i < size; i++) {
				if (characters.get(i) instanceof Ogre) {
					Ogre o = (Ogre) characters.get(i);
					if (o.isAdjacent(hero.getX(), hero.getY(), o.getX(), o.getY())) {
						o.stun();
						@SuppressWarnings("unused")
						JFXPanel fxPanel = new JFXPanel();
						Media hit = new Media(new File("Utils/sword.wav").toURI().toString());
						play = new PlayMusic(hit);
						play.playSound();
	}}}}}

	/**
	 * Verifies if the hero has beaten the current level
	 * @return true
	 */
	public boolean isVictory () {
		int x = hero.getX(), y = hero.getY();
		if(currentMap.isOnTheDoor(x, y) && currentMap.isDoorOpen(x,y) && currentMap.isEdgeDoor(x, y)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Gets the char present in a position of the matrix
	 * @param x
	 * @param y
	 * @return mapArray[x][y]
	 */
	public char getMapPos(int x, int y){
		return mapArray[x][y];
	}
		
	/**
	 * Draws characters in their respective positions
	 * @return String
	 */
	public String getMap() {
		char [][] tmpArray = new char [mapArray.length][mapArray[0].length];
		for (int i = 0; i < mapArray.length; i++) { //COPIAR O MAPA
			for (int j = 0; j < mapArray[0].length; j++) {
				tmpArray[i][j] = mapArray[i][j];}}
		int size = characters.size(), x, y;	
		for (int i = 0; i < size; i++) { //PREENCHER O MAPA
			if (characters.get(i) instanceof Guard) {// desenhar o guarda
				x = ((Guard) characters.get(i)).getX();			y = ((Guard) characters.get(i)).getY();
				tmpArray[y][x] = ((Guard) characters.get(i)).getCh();
			} else if (characters.get(i) instanceof Ogre) {// desenhar o ogre
				x = ((Ogre) characters.get(i)).getX();			y = ((Ogre) characters.get(i)).getY();
				tmpArray[y][x] = ((Ogre) characters.get(i)).getCh();
				if (((Ogre) characters.get(i)).isClubVisible(currentMap)) {// desenhar o club
					x = ((Ogre) characters.get(i)).getClubX(); 			y = ((Ogre) characters.get(i)).getClubY();
					tmpArray[y][x] = ((Ogre) characters.get(i)).getClubCh(); }}}
		tmpArray[hero.getY()][hero.getX()] = hero.getCh();// desenhar o heroi	
		return mapConverter(tmpArray);
	}
	
	/**
	 * Converts the matrix into a string
	 * @param char[][]
	 * @return String
	 */
	
	public String mapConverter(char[][] tmpArray){
		StringBuilder tmp = new StringBuilder();
		for (int i = 0; i < mapArray.length; i++){
			for (int j = 0; j < mapArray[0].length; j++){
				tmp.append(tmpArray[i][j]);
			}tmp.append('\n');
		}
		currentMap.clearPosUsed();
		return tmp.toString();
	}
	
	/**
	 * Verifies if user input is valid or not while extracting new hero coordinates if it is.
	 * @param input
	 * @return boolean
	 */
	public boolean startGame(char input) {
		int x = hero.getX(), y = hero.getY();
		switch(input) {
		case 'w':	y--;	break;
		case 's':	y++;	break;
		case 'd':	x++;	break;
		case 'a':	x--;	break;
		default:
			return false;/*input letra invalido*/}
		if ((mapArray[y][x] == ' ') || (mapArray[y][x] == 'I' && currentMap.isKeyFound()) || mapArray[y][x] == 'S' || mapArray[y][x] == 'k') {
			new_x = x;
			new_y = y;
			return true;}
		return false;
	}
	
	/**
	 * Returns game hero
	 * @return hero
	 */
	public Person getHero(){
		return hero;
	}
	/**
	 * Returns hero new Y
	 * @return y
	 */
	public int getNewHeroY(){
		return new_y;
	}
	/**
	 * Returns hero new X
	 * @return x
	 */
	public int getNewHeroX(){
		return new_x;
	}
	/**
	 * Returns current level's map
	 * @return currentMap
	 */
	public MapLevel getCurrentMap(){
		return currentMap;
	}
	/**
	 * Returns the first guard form the game
	 * @return Person
	 */
	public Person getGuard(){
		return characters.get(0);
	}
	/**
	 * Where the characters positions updaters are called
	 */
	public void update () {
		hero.doStep(currentMap, new_x, new_y);
		int size = characters.size();
		for (int i = 0; i < size; i++) {
			if (characters.get(i) instanceof Guard) {
				((Guard)characters.get(i)).doStep(currentMap, 0, 0);
			} else if(characters.get(i) instanceof Ogre) {
				((Ogre)characters.get(i)).doStep(currentMap, 0, 0);
			}
		}
		if (hero.isArmed()) {
			this.StunOgres();
		}
	}
	/**
	 * Restore ogre and guard static variables
	 */
	public void restartVariables() {
		int size = characters.size();
		for (int i = 0; i < size; i++) {
			if (characters.get(i) instanceof Guard) {
				((Guard)characters.get(i)).restartVariables();
			} else if(characters.get(i) instanceof Ogre) {
				((Ogre)characters.get(i)).restartVariables();
			}
		}
	}
	/**
	 * Changes the current map array
	 * @param newMapArray
	 */
	public void changeMapArray(char[][] tmpMapArray) {
		for (int i = 0; i < mapArray.length; i++) {
			for (int j = 0; j < mapArray[0].length; j++) {
				mapArray[i][j] = tmpMapArray[i][j];
			}
		}
	}
}
