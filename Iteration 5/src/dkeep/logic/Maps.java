package dkeep.logic;

import java.util.ArrayList;

public class Maps {
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
	
	static boolean hasMultipleOgres[] = new boolean[] {false, false, false, true};// se true, criar random ogres
	static boolean instantaneousDoorOpens[] = new boolean[] {false, true, false, false};// se true, portas abrem-se mal se apanha a chave
	 			
			
	public static char[][] getMap(int x){
		
		char [][] tmpArray = new char [listOfMaps.get(x).length][listOfMaps.get(x)[0].length];
		/*COPIAR O MAPA*/
		for (int i = 0; i < listOfMaps.get(x).length; i++) {
			for (int j = 0; j < listOfMaps.get(x)[0].length; j++) {
				tmpArray[i][j] = listOfMaps.get(x)[i][j];
			}
		}
		return tmpArray;
	}
	
	public static boolean hasMultipleOgre(int x){
		if (x > 3)
			return false;
		return hasMultipleOgres[x];
	}
	
	public static boolean instantaneousDoorOpen(int x){
		if (x > 3)
			return false;
		return instantaneousDoorOpens[x]; 
	}
	

	
	
}
