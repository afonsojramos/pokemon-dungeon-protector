package dkeep.gui;

import java.awt.Graphics;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JPanel;

import dkeep.logic.GameMap;
/*import dkeep.logic.Guard;
import dkeep.logic.Ogre;
import dkeep.logic.Person;*/

public class PrintMap extends JPanel {

	private GameMap game = null;
	private int xDimension, yDimension;
	private int[][] Trees = new int[12][12];
	
	/**
	 * Create the panel.
	 */
	public PrintMap() {
		super();
		Assets.init();
		for (int i = 0 ; i < Trees.length ; i++){
			for (int j = 0 ; j < Trees[0].length ; j++){
				Trees[i][j] = ThreadLocalRandom.current().nextInt(1, 8);
			}
		}
	}
	
	public void setGame(GameMap game) {
		this.game = game;
		changeImagesDimension();
		repaint();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // limpa fundo …
		//DESENHAR FUNDO
		g.drawImage(Assets.grass, 0, 0, 500, 500, null);
		String stringMap = game.getMap();
		int j = 0, i = 0;
		for (int index = 0; index < stringMap.length(); index++) {
			char aChar = stringMap.charAt(index);
			System.out.print(aChar);
			if (aChar == 'k') {
				g.drawImage(Assets.key, j*xDimension+14, i*yDimension+14, xDimension/2, yDimension/2, null);
			} else if (aChar == 'X') {
				g.drawImage(Assets.getTree(Trees[i][j]), j*xDimension, i*yDimension, xDimension, yDimension,null);
			} else if (aChar == 'I') {
				g.drawImage(Assets.door, j*xDimension, i*yDimension, xDimension, yDimension,null);
			} else if (aChar == 'S') {
				g.drawImage(null, j*xDimension, i*yDimension, xDimension, yDimension,null);
			} else if (aChar == 'O') {
				g.drawImage(Assets.ogreFrontStop, j*xDimension, i*yDimension, xDimension, yDimension,null);
			} else if (aChar == '*') {
				g.drawImage(Assets.clubFront, j*xDimension, i*yDimension, xDimension, yDimension,null);
			} else if (aChar == 'G') {
				g.drawImage(Assets.guardFrontStop, j*xDimension, i*yDimension, xDimension, yDimension,null);
			} else if (aChar == 'g') {
				g.drawImage(Assets.guardSleep, j*xDimension, i*yDimension, xDimension, yDimension,null);
			} else if (aChar == 'A') {
				g.drawImage(Assets.heroFrontArmed, j*xDimension, i*yDimension, xDimension, yDimension,null);
			} else if (aChar == 'H') {
				g.drawImage(Assets.heroFrontStop, j*xDimension, i*yDimension, xDimension, yDimension,null);
			} else if (aChar == 'K') {
				g.drawImage(Assets.heroFrontKey, j*xDimension, i*yDimension, xDimension, yDimension,null);
			} else if (aChar == '$') {
				g.drawImage(Assets.ogreFrontKey, j*xDimension, i*yDimension, xDimension, yDimension,null);
			} else if (aChar == '8') {
				g.drawImage(Assets.ogres, j*xDimension, i*yDimension, xDimension, yDimension,null);
			} else if (aChar == '\n') {
				i++;
				j = -1;
			}
			j++;
		}
	}
	public void changeImagesDimension() {
		char mapArray[][] = game.getCurrentMap().getMap();
		int width = mapArray[0].length;// largura do mapa
		int height = mapArray.length;// altura do mapa
		xDimension = 500 / width;
		yDimension = 500 / height;
	}

}
