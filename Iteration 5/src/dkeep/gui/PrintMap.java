package dkeep.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import dkeep.logic.GameMap;
import dkeep.logic.Guard;
import dkeep.logic.Ogre;

public class PrintMap extends JPanel {

	private BufferedImage background, wall, door, key, clube, guard, ogre, hero;
	private GameMap game = null;
	private int xDimension, yDimension;
	
	/**
	 * Create the panel.
	 */
	public PrintMap() {
		super();
		try {
			background = ImageIO.read(new File("Utils/cena.jpg"));
			wall = ImageIO.read(new File("Utils/tree.png"));
			door = ImageIO.read(new File("Utils/tree.png"));
			key = ImageIO.read(new File("Utils/tree.png"));
			clube = ImageIO.read(new File("Utils/tree.png"));
			guard = ImageIO.read(new File("Utils/guardFrontWalk.png"));
			ogre = ImageIO.read(new File("Utils/tree.png"));
			hero = ImageIO.read(new File("Utils/heroFrontWalk1.png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("\nLoad images error.\n");
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
		g.drawImage(background, 0, 0, 500, 500, null);
		char mapArray[][] = game.getCurrentMap().getMap();
		int width = mapArray[0].length;
		int height = mapArray.length;
		//DESENHAR OBJETOS FIXOS DO MAPA
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (mapArray[i][j] == 'k') {
					g.drawImage(key, j*xDimension, i*yDimension, xDimension, yDimension, null);
				} else if (mapArray[i][j] == 'X') {
					g.drawImage(wall, j*xDimension, i*yDimension, xDimension, yDimension,null);
				} else if (mapArray[i][j] == 'I') {
					g.drawImage(door, j*xDimension, i*yDimension, xDimension, yDimension,null);
				} else if (mapArray[i][j] == 'S') {
					g.drawImage(door, j*xDimension, i*yDimension, xDimension, yDimension,null);
				}
			}
		}
			
		int size = game.getCharacters().size();
		//DESENHAR PERSONAGENS ADVERSARIAS DO JOGO
		for (int i = 0; i < size; i++) {
			if (game.getCharacters().get(i) instanceof Guard) {
				Guard characterG = (Guard) game.getCharacters().get(i);
				int xg = characterG.getX(), yg = characterG.getY();
				g.drawImage(guard, xg*xDimension, yg*yDimension, xDimension,yDimension, null);
			} else if (game.getCharacters().get(i) instanceof Ogre) {
				Ogre characterO = (Ogre) game.getCharacters().get(i);
				int xo = characterO.getX(), yo = characterO.getY();
				int xc = characterO.getClubX(), yc = characterO.getClubY();
				g.drawImage(ogre, xo*xDimension, yo*yDimension, xDimension,yDimension, null);
				g.drawImage(clube, xc*xDimension, yc*yDimension, xDimension,yDimension, null);
			}
		}
		//DESENHAR HEROI
		g.drawImage(hero, game.getHero().getX()*xDimension, game.getHero().getY()*yDimension, xDimension,yDimension, null);
	}
	public void changeImagesDimension() {
		char mapArray[][] = game.getCurrentMap().getMap();
		int width = mapArray[0].length;// largura do mapa
		int height = mapArray.length;// altura do mapa
		xDimension = 500 / width;
		yDimension = 500 / height;
	}

}
