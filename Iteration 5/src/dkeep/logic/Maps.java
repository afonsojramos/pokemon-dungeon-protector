package dkeep.logic;

import java.util.ArrayList;

public class Maps implements java.io.Serializable{
	static char map0[][] = new char[][] {{' '}};
	static char map1[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ 'X', 'H', ' ', ' ', 'I', ' ', 'X', ' ', 'G', 'X' }, { 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
			{ 'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X' }, { 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
			{ 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', ' ', 'X' }, { 'X', ' ', 'I', ' ', 'I', ' ', 'X', 'k', ' ', 'X' },
			{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } }; // mapa do
																	// nivel 1
	static char map2[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ 'I', ' ', ' ', ' ', 'O', '*', ' ', 'k', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', 'H', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } }; // mapa
																												// do
																												// nivel
																												// 2

	static char map3[][] = new char[][] { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ 'I', ' ', ' ', ' ', ' ', ' ', ' ', 'k', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', 'A', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } }; // mapa
																												// do
	static 	ArrayList<char[][]> listOfMaps = new ArrayList<char[][]> () {{
		add(map0);
		add(map1);
		add(map2);
		add(map3);
	}};																									// nivel
																												// 3
	static int currentLevel = 1;
	static int finalLevel = listOfMaps.size() -1;
	
	static boolean hasMultipleOgres[] = new boolean[] {false, false, false, true};// se true, criar random ogres
	static boolean instantaneousDoorOpens[] = new boolean[] {false, true, false, false};// se true, portas abrem-se mal se apanha a chave
	/**
	 * retorna o nivel atual do mapa a ser usado			
	 * @return
	 */
	public static int getCurrentLevel() { return currentLevel; }
	/**
	 * retorna o nivel final do jogo
	 * @return
	 */
	public static int getFinalLevel() {return finalLevel;}
	/**
	 * altera o nivel atual do jogo
	 * @param l
	 */
	public static void setCurrentLevel(int l) {currentLevel = l;}
	/**
	 * altera o nivel final do jogo
	 * @param l
	 */
	public static void setFinalLevel(int l) {finalLevel = l;}
	/**
	 * retorna uma copia do mapa de indice x da lista de mapas
	 * @param x
	 * @return
	 */
	public static char[][] getMap(int x){
		
		char [][] tmpArray = new char [listOfMaps.get(x).length][listOfMaps.get(x)[0].length];
		/*COPIAR O MAPA*/
		for (int i = 0; i < listOfMaps.get(x).length; i++) {
			for (int j = 0; j < listOfMaps.get(x)[0].length; j++) {
				tmpArray[i][j] = listOfMaps.get(x)[i][j];
			}
		}
		currentLevel = x;
		return tmpArray;
	}
	/**
	 * verifica se o mapa de indice x da lista de mapas precisa de criar ogres aquando da criacao do nivel de jogo que usa este mesmo mapa
	 * @param x
	 * @return
	 */
	public static boolean hasMultipleOgre(int x){
		if (x > 3)
			return false;
		return hasMultipleOgres[x];
	}
	/**
	 * verifica se o mapa de indice x da lista de mapas abre as portas mal o heroi apanha a chave e nao apenas quando apanha a chave e depois se aproxima da porta
	 * @param x
	 * @return
	 */
	public static boolean instantaneousDoorOpen(int x){
		if (x > 3)
			return false;
		return instantaneousDoorOpens[x]; 
	}
	/**
	 * cria array de um novo mapa de largura x e altura y e guarda-o na lista de mapas de jogo
	 * @param x
	 * @param y
	 */
	public static void createNewMap(int x, int y) {
		finalLevel = listOfMaps.size();currentLevel = finalLevel;
		char creationMap[][] = new char[y][x];
		//PREENCHER BORDAS DO MAPA
		for(int j = 0; j < x; j++) {
			creationMap[0][j] = 'X';
			creationMap[y-1][j] = 'X';}
		for(int i = 0; i < y; i++) {
			creationMap[i][0] = 'X';
			creationMap[i][x-1] = 'X';}
		for(int i = 0; i < y; i++) {
			for(int j = 0; j < x; j++) {
				if(creationMap[i][j] != 'X') {
					creationMap[i][j] = ' ';
		}}}
		listOfMaps.add(creationMap);
	}
	/**
	 * altera elementos do mapa de jogo que esta a ser criado pelo utilizador 
	 * @param x
	 * @param y
	 * @param Ch
	 * @return
	 */
	public static boolean changeNewMap(int x, int y, char Ch) {
		char[][] mapArray = listOfMaps.get(listOfMaps.size() - 1);
		if (Ch != ' ') {
			if (Ch == 'H' || Ch == 'A' || Ch == 'k' || Ch == 'O') {
				return validateElement(x, y, Ch);
			} else if (Ch == 'I') {
				mapArray[y][x] = mapArray[y][x] == 'X' ? Ch : mapArray[y][x];
				return true;
			} else {
				mapArray[y][x] = mapArray[y][x] == ' ' ? Ch : mapArray[y][x];
				return true;
		}}	
		return false;
	}
	/**
	 * verifica se um determinado elemento pode ser colocado no mapa que esta a ser criado
	 * @param x
	 * @param y
	 * @param Ch
	 * @return
	 */
	public static boolean validateElement(int x, int y, char Ch) {
		char[][] mapArray = listOfMaps.get(listOfMaps.size() - 1);
		int width = mapArray[0].length; 	int height = mapArray.length;
		if (mapArray[y][x] != ' ' && mapArray[y][x] != 'X') {return false;}
		if (x == 0 || x == (width - 1) || y == 0 || y == (height - 1)) {return false;}
		if (mapArray[y][x - 1] == 'X' && mapArray[y][x + 1] == 'X' && mapArray[y - 1][x] == 'X' && mapArray[y + 1][x] == 'X') {	return false;}
		if (Ch == 'H' || Ch == 'A') {
			for (int i = 0; i < mapArray.length; i++) { // apagar o heroi que ja existia no mapa
				for (int j = 0; j < mapArray[0].length; j++) {
					if (mapArray[i][j] == 'A' || mapArray[i][j] == 'H') {
						mapArray[i][j] = ' ';}}}}
		mapArray[y][x] = Ch;
		System.out.println(mapArray[y][x]);
		return true;
	}
	

	
	
}
