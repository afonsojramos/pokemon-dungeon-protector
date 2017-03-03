package dkeep.logic;

import java.util.Random;

public class Hero extends Person{

	public Hero(String name, int x, int y, char Ch){
		super(name,x,y, Ch);
	}
	
	public Hero(String name, int x, int y) {//construtor default
		this(name, x, y, 'H');
	}
	
	public void printElement (char currentMap [][]) {
		currentMap[y][x] = Ch;
	}
	
	public void editHero (MapLevel currentMap) {
		if(currentMap.isHeroArmed()) {
			Ch = 'A';
		} else {
			Ch = 'H';
		}
		x = currentMap.getBeginningXOfHero();
		y = currentMap.getBeginningYOfHero();
	}
	
	public int doStep (MapLevel currentMap, int xOgre, int yOgre) {
		prevX = x; prevY = y;
		x = xOgre; y = yOgre;
		if(currentMap.isCharAtPos(x, y, 'S')) {
			return 1; //mudar de nivel
		}
		if(currentMap.isCharAtPos(x, y, 'I')) {
			currentMap.openDoors();
			x = prevX; y = prevY;
		}
		currentMap.setValuePos(prevX, prevY, ' ');
		currentMap.setValuePos(x, y, Ch);
		if(currentMap.isUnderKey(x, y)) {
			currentMap.setKeyFound();
			if(currentMap.getState() != 1) {
				Ch = 'K';
				currentMap.setValuePos(x, y, Ch);
			}
		}
		return 0;
	}
}

