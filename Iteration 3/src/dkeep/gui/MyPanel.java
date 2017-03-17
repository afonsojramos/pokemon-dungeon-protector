package dkeep.gui;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.*; 
import javax.swing.JPanel;

import dkeep.logic.Guard;
import dkeep.logic.Hero;
import dkeep.logic.MapLevel;
import dkeep.logic.Ogre;
import dkeep.logic.Person;

public class MyPanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener{

	 private int x1 = 0, y1 = 0, x2 = 0, y2 = 0; 
	 private Vector<Person> characters = new Vector<Person>();
	 private MapLevel currentMap = null;
	 private Hero characterH = null;//heroi
	 private BufferedImage background = null, wall = null, hero = null, guard = null, ogre = null, clube = null, key = null, doorClosed = null, doorOpened = null;
	 
	/**
	 * Create the panel.
	 */
	public MyPanel() {
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this); 
		this.currentMap = null;
		this.characters = null;
		characterH = null;
		try {
		    background = ImageIO.read(new File("tree.png"));
		    wall = ImageIO.read(new File("tree.png"));
		    hero = ImageIO.read(new File("tree.png"));
		    guard = ImageIO.read(new File("tree.png"));
		    ogre = ImageIO.read(new File("tree.png"));
		    clube = ImageIO.read(new File("tree.png"));
		    key = ImageIO.read(new File("tree.png"));
		    doorClosed = ImageIO.read(new File("tree.png"));
		    doorOpened = ImageIO.read(new File("tree.png"));
		} catch (IOException e) {
		}
	}

	public void setMap(MapLevel currentMap) {
		this.currentMap = currentMap;
	}
	public void setCharacters (Vector<Person> characters) {
		this.characters = characters;
	}
	public void setHero (Hero h) {
		characterH = h;
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // limpa fundo …
		g.drawImage(background, 0, 0, null);
		char mapArray[][] = currentMap.getMap();
		int width = mapArray[0].length;
		int height = mapArray.length;
		for (int i = 0; i < height; i++) { // desenhar objetos fixos do mapa
			for (int j = 0; j < width; j++) {
				if (mapArray[i][j] == 'k') {
					g.drawImage(key, j, i, null);
				} else if (mapArray[i][j] == 'X') {
					g.drawImage(wall, j, i, null);
				} else if (mapArray[i][j] == 'I') {
					g.drawImage(doorClosed, j, i, null);
				} else if (mapArray[i][j] == 'S') {
					g.drawImage(doorOpened, j, i, null);
				}
			}
		}

		int size = characters.size();
		for (int i = 0; i < size; i++) {//desenhar personagens adversarias do jogo
			if (characters.get(i) instanceof Guard) {
				Guard characterG = (Guard) characters.get(i);
				int xg = characterG.getX(), yg = characterG.getY();
				g.drawImage(guard, xg, yg, null);
			} else if (characters.get(i) instanceof Ogre) {
				Ogre characterO = (Ogre) characters.get(i);
				int xo = characterO.getX(), yo = characterO.getY();
				g.drawImage(ogre, xo, yo, null);
			}
		}
		g.drawImage(hero, characterH.getX(), characterH.getY(), null);
	}

	// Handling keyboard and mouse events
	public void mousePressed(MouseEvent e) {
	 x2 = x1 = e.getX();
	 y2 = y1 = e.getY();
	 repaint(); // notifies SWING that it needs repainting
	}
	public void mouseDragged(MouseEvent e) {
	 x2 = e.getX();
	 y2 = e.getY();
	 repaint();
	}
	public void keyPressed(KeyEvent e) {
	 switch(e.getKeyCode()){
	 case KeyEvent.VK_LEFT: x1--; x2--; repaint(); break;
	 case KeyEvent.VK_RIGHT: x1++; x2++; repaint(); break;
	 case KeyEvent.VK_UP: y1--; y2--; repaint(); break;
	 case KeyEvent.VK_DOWN: y1++; y2++; repaint(); break;
	 }
	}
	// Remaining listeners (not used)
	public void mouseReleased(MouseEvent e) {}
	public void mouseMoved(MouseEvent arg0) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {} 
}
