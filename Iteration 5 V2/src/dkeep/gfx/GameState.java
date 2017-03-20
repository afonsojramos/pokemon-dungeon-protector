package dkeep.gfx;

import java.awt.Graphics;

import dkeep.logic.GameMap;
import dkeep.logic.Guard;
import dkeep.logic.Maps;
import dkeep.logic.Ogre;

public class GameState extends State {

	private int f = 0;
	private int x = 0;
	private GameMap gameMap = null;
	private int xDimension, yDimension;
	private int level = 1;
	private int nOgres = 1;
	public GameState(Game game){
		super(game);
		
	}
	
	@Override
	public void update() {
		char[][] tempMap = Maps.getMap(level);
		gameMap = new GameMap(tempMap, Maps.hasMultipleOgre(level), nOgres, Maps.instantaneousDoorOpen(level));
		gameMap.readMap();
		gameMap.restartVariables();// restaurar variaveis static!!!!!
		setGame(gameMap);
	}

	public void setGame(GameMap gameMap) {
		this.gameMap = gameMap;
		changeImagesDimension();
	}
	
	@Override
	public void render(Graphics g) {
		char mapArray[][] = gameMap.getCurrentMap().getMap();
		int width = mapArray[0].length;
		int height = mapArray.length;
		//DESENHAR OBJETOS FIXOS DO MAPA
		g.drawImage(Assets.grass, 0, 0, null);
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (mapArray[i][j] == 'k') {
					g.drawImage(Assets.key, j*xDimension+xDimension/6, i*yDimension+yDimension/6, null);
				} else if (mapArray[i][j] == 'X') {
					g.drawImage(Assets.tree1, j*xDimension, i*yDimension, xDimension, yDimension,null);
				} else if (mapArray[i][j] == 'I') {
					g.drawImage(Assets.door, j*xDimension, i*yDimension, xDimension, yDimension,null);
				} else if (mapArray[i][j] == 'S') {
					g.drawImage(Assets.door, j*xDimension, i*yDimension, xDimension, yDimension,null);
				}
			}
		}
			
		int size = gameMap.getCharacters().size();
		//DESENHAR PERSONAGENS ADVERSARIAS DO JOGO
		for (int i = 0; i < size; i++) {
			if (gameMap.getCharacters().get(i) instanceof Guard) {
				Guard characterG = (Guard) gameMap.getCharacters().get(i);
				int xg = characterG.getX(), yg = characterG.getY();
				g.drawImage(Assets.guardFrontStop, xg*xDimension, yg*yDimension, null);
			} else if (gameMap.getCharacters().get(i) instanceof Ogre) {
				Ogre characterO = (Ogre) gameMap.getCharacters().get(i);
				int xo = characterO.getX(), yo = characterO.getY();
				int xc = characterO.getClubX(), yc = characterO.getClubY();
				g.drawImage(Assets.ogreBackStop, xo*xDimension, yo*yDimension, xDimension,yDimension, null);
				g.drawImage(Assets.clubFront, xc*xDimension, yc*yDimension, xDimension,yDimension, null);
			}
		}
		//DESENHAR HEROI
		g.drawImage(Assets.heroFrontStop, gameMap.getHero().getX()*xDimension, gameMap.getHero().getY()*yDimension, null);
		
	}
	
	public void changeImagesDimension() {
		char mapArray[][] = gameMap.getCurrentMap().getMap();
		int width = mapArray[0].length;// largura do mapa
		int height = mapArray.length;// altura do mapa
		xDimension = 500 / width;
		yDimension = 500 / height;
	}

}
