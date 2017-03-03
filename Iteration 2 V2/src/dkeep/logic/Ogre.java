package dkeep.logic;

import java.util.Random;

public class Ogre extends Person{
	Random rand = new Random();
	private Club club;
	private static int nOgres = 0;
	private static int pos = 1; //posicao dos ogre criados com o construtor sem parametros
	private int stuned;
	
	public Ogre(String name, int x, int y, char Ch){
		super(name,x,y, Ch);
		club = new Club(x+1,y);
		nOgres++;
		pos += 2;
		stuned = 0;
	}

	public Ogre(String name, int x, int y) {// construtor default
		this(name, x, y, 'O');
	}

	public Ogre() {
		this("ogre" + nOgres, (pos % 6), (pos / 6) + 1, 'O');
	}
	
	public Ogre(int x, int y) {
		this("ogre" + nOgres, x, y, 'O');
	}

	public int doStep(MapLevel currentMap, int xHero, int yHero) {
		if (!this.isStuned()) { // is not stuned
			boolean possibleMove = false;
			currentMap.clearElement(x, y);
			do {
				int randomNum = rand.nextInt(4); // random entre [min, max] :
													// int
													// randomNum =
													// rand.nextInt((max
													// - min) + 1) + min;
				switch (randomNum) {
				case 0: // Ogre anda para cima
					if ((currentMap.isCharAtPos(x, y - 1, 'I')) || (currentMap.isCharAtPos(x, y - 1, 'X'))) {
						possibleMove = false;
					} else {
						prevY = y;
						y--;
						if (currentMap.isUnderKey(x, y)) {
							currentMap.setOverlapedChar(x, y, '$');
							currentMap.setValuePos(x, y, '$');
						} else {
							currentMap.setOverlapedChar(x, y, 'O');
							currentMap.setValuePos(x, y, 'O');
						}
						possibleMove = true;
					}
					break;
				case 1: // Ogre anda para baixo
					if ((currentMap.isCharAtPos(x, y + 1, 'I')) || (currentMap.isCharAtPos(x, y + 1, 'X'))) {
						possibleMove = false;
					} else {
						prevY = y;
						y++;
						currentMap.setOverlapedChar(x, y, 'O');
						currentMap.setValuePos(x, y, 'O');
						possibleMove = true;
					}
					break;
				case 2: // Ogre anda para a esquerda
					if ((currentMap.isCharAtPos(x - 1, y, 'I')) || (currentMap.isCharAtPos(x - 1, y, 'X'))) {
						possibleMove = false;
					} else {
						prevX = x;
						x--;
						currentMap.setOverlapedChar(x, y, 'O');
						currentMap.setValuePos(x, y, 'O');
						possibleMove = true;
					}
					break;
				case 3: // Ogre anda para a direita
					if ((currentMap.isCharAtPos(x + 1, y, 'I')) || (currentMap.isCharAtPos(x + 1, y, 'X'))) {
						possibleMove = false;
					} else {
						prevX = x;
						x++;
						;
						if (currentMap.isUnderKey(x, y)) {
							currentMap.setOverlapedChar(x, y, '$');
							currentMap.setValuePos(x, y, '$');
						} else {
							currentMap.setOverlapedChar(x, y, 'O');
							currentMap.setValuePos(x, y, 'O');
						}
						possibleMove = true;
					}
					break;
				}
			} while (!possibleMove);
		} else {// is stuned; don't move
			this.lessStuned();
		}

		club.move(currentMap, x, y);
		if (!currentMap.isHeroArmed()) {
			if (this.isAdjacent(xHero, yHero, x, y) || this.isAdjacent(xHero, yHero, club.getX(), club.getY())) {
				return 1;
			}
		} else {
			if (this.isAdjacent(xHero, yHero, x, y) && !this.isAdjacent(xHero, yHero, club.getX(), club.getY())) {
				this.stun();
				return 0;
			} else if (this.isAdjacent(xHero, yHero, club.getX(), club.getY())) {
				return 1;
			}
		}
		return 0;
	}

	public boolean isAdjacent(int x1, int y1, int x2, int y2) {
		if (x1 == x2 && (y2 == (y1 - 1) || y2 == (y1 + 1))) {
			return true;
		}
		if (y1 == y2 && (x2 == (x1 - 1) || x2 == (x1 + 1))) {
			return true;
		}
		if (y1 == y2 && x1 == x2) {
			return true;
		}
		return false;
	}
	
	public void printElement (char currentMap [][]) {
		currentMap[y][x] = Ch;
		currentMap[club.getY()][club.getX()] = club.getCh();
	}
	
	public boolean isStuned() {
		return stuned > 0;
	}
	
	public void stun() {
		stuned = 2;
		this.setCh('8');
	}
	
	public void lessStuned() {
		stuned--;
		if(stuned == 0){
			this.setCh('O');
		}
	}
	
	public int getClubX () { return club.getX(); }
	
	public int getClubY () { return club.getY(); }
	
	public char getClubCh () { return club.getCh(); }
	
	public void setClubX (int x) { club.setX(x); }
	
	public void setClubY (int y) { club.setY(y); }
	
	public void setClubCh (char ch) { club.setCh(ch); }
	
}
