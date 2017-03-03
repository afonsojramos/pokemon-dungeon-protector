package dkeep.logic;
import java.util.*;

//import dkeep.logic.Guard.Personality;

public class GameMap {

	Random rand = new Random();
	char map0[][] = new char[][] {{' '}};
	char map1[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ 'X', 'H', ' ', ' ', 'I', ' ', 'X', ' ', 'G', 'X' }, { 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
			{ 'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X' }, { 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
			{ 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', ' ', 'X' }, { 'X', ' ', 'I', ' ', 'I', ' ', 'X', 'k', ' ', 'X' },
			{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } }; // mapa do
																	// nivel 1
	char map2[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ 'I', ' ', ' ', ' ', 'O', '*', ' ', 'k', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', 'H', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } }; // mapa do nivel 2
																												
	char map3[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ 'I', ' ', ' ', ' ', ' ', ' ', ' ', 'k', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', 'A', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } }; // mapa do nivel 3
																												
	//private ArrayList<char[][]> listOfMaps;
	private int state; // nivel do jogo
	private MapLevel currentMap; //mapa de jogo
	private Person hero;
	private Vector<Vector<Person>> characters;
	private boolean endOfGame;	
	private boolean victory;
	
	public GameMap() {
		/**
		 * inicializar variaveis
		 */
		state = 0;
		endOfGame = false;
		victory = false;
		currentMap = null;
		hero = new Hero("hero", 1, 1);
		/**
		 * inicializar personagens do tabuleiro de jogo
		 */
		characters = new Vector<Vector<Person>>();//vetor do index 0 fica vazio
		Vector<Person> level0 = new Vector<Person>();Vector<Person> level1 = new Vector<Person>();
		Vector<Person> level2 = new Vector<Person>();Vector<Person> level3 = new Vector<Person>();
		characters.add(level0); characters.add(level1);
		characters.add(level2); characters.add(level3);

		int randomNum = rand.nextInt(5) + 1;
		System.out.print("\n\nNumero de ogres no nivel 3: " + randomNum + "\n\n");
		while(randomNum > 0){
			this.addOgreToLevel(3);
			randomNum--;
		}
	}

	public void addOgreToLevel(int level) {
		Ogre ogre = new Ogre();
		Vector<Person> auxOgres = characters.get(level);
		auxOgres.add(ogre);
	}
	
	public void addGuardToLevel(int level) {
		Guard guard = new Guard();
		Vector<Person> auxGuards = characters.get(level);
		auxGuards.add(guard);
	}

	public int getState() { return state; }

	public boolean isEndOfGame() {
		return endOfGame;
	}
	
	public boolean isVictorry () {
		return victory;
	}
	
	public String getMap() {
		return currentMap.getStringMap();
	}
	
	public void moveHero(char input) {

		int x = hero.getX(), y = hero.getY();
		boolean update = false;
		
		if (input == 'w' && ((currentMap.isCharAtPos(x, y - 1, ' ')) || (currentMap.isCharAtPos(x, y - 1, 'I') && currentMap.isKeyFound()) || currentMap.isCharAtPos(x, y - 1, 'S') || currentMap.isCharAtPos(x, y - 1, 'k'))) {
			y--;
			update = true;
		} else if (input == 's' && ((currentMap.isCharAtPos(x, y + 1, ' ')) || (currentMap.isCharAtPos(x, y + 1, 'I') && currentMap.isKeyFound()) || currentMap.isCharAtPos(x, y + 1, 'S') || currentMap.isCharAtPos(x, y + 1, 'k'))) {
			y++;
			update = true;
		} else if (input == 'd' && ((currentMap.isCharAtPos(x + 1, y, ' ')) || (currentMap.isCharAtPos(x + 1, y, 'I') && currentMap.isKeyFound()) || currentMap.isCharAtPos(x + 1, y, 'S') || currentMap.isCharAtPos(x + 1, y, 'k'))) {
			x++;
			update = true;
		} else if (input == 'a' && ((currentMap.isCharAtPos(x - 1, y, ' ')) || (currentMap.isCharAtPos(x - 1, y, 'I') && currentMap.isKeyFound()) || currentMap.isCharAtPos(x - 1, y, 'S') || currentMap.isCharAtPos(x - 1, y, 'k'))) {
			x--;
			update = true;
		}

		if (update) {
			this.update(x, y);
			}
	}
	
	public void update (int new_x, int new_y) {
		if(((Hero)hero).doStep(currentMap, new_x, new_y) == 1) { //mudar de nivel
			this.changeState();
		} else {
			Vector<Person> v = characters.get(state);
			int size = v.size();
			size--;
			while (size >= 0) {
				Person levelCharacter = v.get(size);
				if (levelCharacter instanceof Guard) {
					if (((Guard) levelCharacter).doStep(currentMap, new_x, new_y) == 1) {
						endOfGame = true;
						break;
					}
				} else if (levelCharacter instanceof Ogre) {
					if (((Ogre) levelCharacter).doStep(currentMap, new_x, new_y) == 1) {
						endOfGame = true;
						break;
					}
				}
				size--;
			}
			currentMap.clearArrayOverlap();
		}
	}

	public void changeState() {
		state++;	
		ArrayList<char[][]> listOfMaps = new ArrayList<char[][]>(4);
		listOfMaps.add(map0);
		listOfMaps.add(map1);
		listOfMaps.add(map2);
		listOfMaps.add(map3);
		if(state >= listOfMaps.size()) {
			victory = true;
			endOfGame = true;
			return;
		}
		Vector<Person> v = characters.get(state);
		currentMap = new MapLevel(listOfMaps.get(state), state, v);
		((Hero)hero).editHero(currentMap);
		
		int size = v.size();
		size--;
		while (size >= 0) {
			Person levelCharacters = v.get(size);
			if (levelCharacters instanceof Guard) {
			((Guard)levelCharacters).printElement(listOfMaps.get(state));
			} else if (levelCharacters instanceof Ogre){
				((Ogre)levelCharacters).printElement(listOfMaps.get(state));
			}
			size--;
		}
	}
	public Person getHero() {
		return hero;
	}
	
	public void editMap (char [][] currentMapArray) {
		Vector<Person> v = new Vector<Person>();
		currentMap = new MapLevel(currentMapArray, 0, v);
	}
		
}
