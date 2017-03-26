package dkeep.gui;

import java.awt.Graphics;
import javax.swing.JPanel;

import dkeep.logic.GameMap;
import dkeep.logic.Ogre;
/*import dkeep.logic.Guard;
import dkeep.logic.Ogre;
import dkeep.logic.Person;*/
import dkeep.logic.Person;
/**
 * PrintMap.java - class that deals with map printing
 */
public class PrintMap extends JPanel {

	private static final long serialVersionUID = 1L;
	private GameMap game = null;
	private int xDimension, yDimension;
	
	/**
	 * Create the panel
	 */
	public PrintMap() {
		super();
		Assets.init();
	}
	/**
	 * Attributes game to panel
	 * @param game
	 */
	public void setGame(GameMap game) {
		this.game = game;
		changeImagesDimension();
		repaint();
	}
	/**
	 * Draws the map
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // limpa fundo
		if(game == null) { g.drawImage(Assets.pokemon, 12, 80, 450, 250, null);}
		else {
		g.drawImage(Assets.grass, 0, 0, 500, 500, null);//DESENHAR FUNDO
		String stringMap = game.getMap();
		int j = 0, i = 0;
		for (int index = 0; index < stringMap.length(); index++) {
			char aChar = stringMap.charAt(index);
			System.out.print(aChar);
			if (aChar == 'k') {			g.drawImage(Assets.key, j*xDimension+14, i*yDimension+14, xDimension/2, yDimension/2, null);
			} else if (aChar == 'X') {	g.drawImage(Assets.getTree(j, i), j*xDimension, i*yDimension, xDimension, yDimension,null);
			} else if (aChar == 'I') {	g.drawImage(Assets.door, j*xDimension, i*yDimension, xDimension, yDimension,null);
			} else if (aChar == 'S') {	g.drawImage(null, j*xDimension, i*yDimension, xDimension, yDimension,null);
			} else if (aChar == 'O') {	g.drawImage(Assets.ogreFrontStop, j*xDimension, i*yDimension, xDimension, yDimension,null);
			} else if (aChar == '*') {	g.drawImage(Assets.clubFront, j*xDimension, i*yDimension, xDimension, yDimension,null);
			} else if (aChar == 'G') {	g.drawImage(Assets.guardFrontStop, j*xDimension, i*yDimension, xDimension, yDimension,null);
			} else if (aChar == 'g') {	g.drawImage(Assets.guardSleep, j*xDimension, i*yDimension, xDimension, yDimension,null);
			} else if (aChar == 'A') {	g.drawImage(Assets.heroFrontArmed, j*xDimension, i*yDimension, xDimension, yDimension,null);
			} else if (aChar == 'H') {	g.drawImage(Assets.heroFrontStop, j * xDimension, i * yDimension, xDimension, yDimension, null);
			} else if (aChar == 'K') {	g.drawImage(Assets.heroFrontKey, j * xDimension, i * yDimension, xDimension, yDimension, null);
			} else if (aChar == '$') {	if (isOgreAboveKey()) {g.drawImage(Assets.ogreFrontKey, j * xDimension, i * yDimension, xDimension, yDimension, null);
									}	else {g.drawImage(Assets.clubFrontKey, j * xDimension, i * yDimension, xDimension, yDimension, null);}
			} else if (aChar == '8') {	g.drawImage(Assets.ogreStun, j * xDimension, i * yDimension, xDimension, yDimension, null);
			} else if (aChar == '\n') {
				i++; j = -1;}
			j++;}}
	}
	/**
	 * Check if ogre is above key
	 * @return
	 */
	public boolean isOgreAboveKey() {
		boolean result = false;
		for (Person x : game.getCharacters()) {
			if (x instanceof Ogre) {
				if(game.getCurrentMap().isAboveKey(((Ogre)x).getX(), ((Ogre)x).getY())) {
					result = true;
					break;
				}
			}
		}
		return result; 
	}
	/**
	 * Changes images dimension to fit the map
	 */
	public void changeImagesDimension() {
		char mapArray[][] = game.getCurrentMap().getMap();
		int width = mapArray[0].length;// largura do mapa
		int height = mapArray.length;// altura do mapa
		xDimension = 500 / width;
		yDimension = 500 / height;
	}

}
