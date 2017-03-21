package dkeep.gui;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import dkeep.logic.GameMap;
import dkeep.logic.Guard;
import dkeep.logic.Maps;
import dkeep.logic.Guard.Personality;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Map;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;

public class PlayGame {

	private JFrame frame;
	private PrintMap printPanel;
	private JMenuBar menuBar;
	private JLabel lblNumberOfOgres;
	private JLabel lblGuardPersonality;
	private JLabel lblCreateMap;
	private JButton btnWall;
	private JButton btnDoor;
	private JButton btnKey;
	private JButton btnOgre;
	private JButton btnHero;
	private JPanel buttonsPanel;
	
	private static GameMap game = null;
	private int level = 1;
	private int finalLevel = 3;
	private int nOgres = 1;
	private Personality guardPersonality = Personality.valueOf("Rookie");
	private boolean gameStarted = false;
	private boolean creationMode = false;
	private int xMapDimension = 10, yMapDimension = 10;
	private char currentElement = ' ';
	private int xMouseMap, yMouseMap; //coodenadas do rato na tabela de jogo
	private boolean keyUsed = false, doorUsed = false;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayGame window = new PlayGame();
					window.frame.setVisible(true);
					window.frame.setLocationRelativeTo(null);
					/*String bip = "Utils/Music.mp3";
					Media hit = new Media(new File(bip).toURI().toString());
					MediaPlayer mediaPlayer = new MediaPlayer(hit);
					mediaPlayer.play();*/
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PlayGame() {
		initialize();
	}
	
	/*public void playMusic(){
		String bip = "Utils/Music.mp3";
		Media hit = new Media(new File(bip).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(hit);
		mediaPlayer.play();
	}*/

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		printPanel = new PrintMap();
		printPanel.setBounds(12, 68, 500, 500);
		printPanel.setVisible(false);
		frame.getContentPane().add(printPanel);
		frame.requestFocusInWindow();
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (gameStarted) {
					switch (e.getKeyCode()) {
					case KeyEvent.VK_LEFT:
						if (game.startGame('a')) {
							updateGraphics();
						}
						break;
					case KeyEvent.VK_RIGHT:
						if (game.startGame('d')) {
							updateGraphics();
						}
						break;
					case KeyEvent.VK_UP:
						if (game.startGame('w')) {
							updateGraphics();
						}
						break;
					case KeyEvent.VK_DOWN:
						if (game.startGame('s')) {
							updateGraphics();
						}
						break;
					case KeyEvent.VK_A:
						if (game.startGame('a')) {
							updateGraphics();
						}
						break;
					case KeyEvent.VK_D:
						if (game.startGame('d')) {
							updateGraphics();
						}
						break;
					case KeyEvent.VK_W:
						if (game.startGame('w')) {
							updateGraphics();
						}
						break;
					case KeyEvent.VK_S:
						if (game.startGame('s')) {
							updateGraphics();
						}
						break;
					}
				}
			}
		});
		frame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(creationMode) {
					System.out.println("entrou!");
					System.out.println("x: "+ xMouseMap+" y: "+yMouseMap);
					System.out.println("xScreen: "+ arg0.getX()+" yScreen: "+arg0.getY());
					System.out.println(currentElement);
					if(arg0.getX() >= 21 && arg0.getX() <= 521 && arg0.getY() >= 146 && arg0.getY() <= 646) { 
					convertCoordinates(arg0.getX(), arg0.getY());
					boolean result = Maps.changeNewMap(xMouseMap, yMouseMap, currentElement);
					if(currentElement == 'k' && result) {
						keyUsed = true;
					} else if (currentElement == 'I' && result) {
						doorUsed = true;
					}
					game.changeMapArray(Maps.getMap(level));
					game.readMap(true);
					printPanel.repaint();
					System.out.println("cagou");
					}
					
				}
			}
		});
		
		frame.setBounds(100, 100, 800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		lblNumberOfOgres = new JLabel("Number of Ogres");
		lblNumberOfOgres.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String[] possibilities = {"1","2","3","4","5"};
				String s = (String)JOptionPane.showInputDialog(frame, "Number of Ogres:", "Options", JOptionPane.PLAIN_MESSAGE,null, possibilities,null);
				if (s==null) {
					nOgres = 1;
				}
				else{
					nOgres = Integer.parseInt(s);
				}
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNumberOfOgres.setForeground(new Color(102, 205, 170));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNumberOfOgres.setForeground(new Color(0, 0, 0));
			}
		});
		lblNumberOfOgres.setFont(new Font("AR DARLING", Font.PLAIN, 30));
		menuBar.add(lblNumberOfOgres);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		menuBar.add(horizontalStrut);
		
		lblGuardPersonality = new JLabel("Guard Personality");
		lblGuardPersonality.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String[] possibilities = {"Rookie", "Drunken", "Suspicious", "Obedient"};
				String s = (String)JOptionPane.showInputDialog(frame, "Guard personality", "Options", JOptionPane.PLAIN_MESSAGE,null, possibilities, "Rookie");
				if((s != null) && (s.length() > 0)) {
					guardPersonality = Personality.valueOf(s);
				} else {
					guardPersonality = Personality.valueOf("Rookie");
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblGuardPersonality.setForeground(new Color(32, 178, 170));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblGuardPersonality.setForeground(new Color(0, 0, 0));
			}
		});
		lblGuardPersonality.setFont(new Font("AR DARLING", Font.PLAIN, 30));
		menuBar.add(lblGuardPersonality);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		menuBar.add(horizontalStrut_1);
		
		lblCreateMap = new JLabel("Create Map");
		lblCreateMap.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String[] possibilitiesWidth = {"5","6", "7", "8", "9", "10", "11", "12"};
				String s = (String)JOptionPane.showInputDialog(frame, "Width:", "Options", JOptionPane.PLAIN_MESSAGE,null, possibilitiesWidth,null);
				xMapDimension = s==null ? 5 : Integer.parseInt(s);
				
				String[] possibilitiesHeight = {"5","6", "7", "8", "9", "10", "11", "12"};
				String s2 = (String)JOptionPane.showInputDialog(frame, "Height:", "Options", JOptionPane.PLAIN_MESSAGE,null, possibilitiesHeight,null);
				yMapDimension = s2==null ? 5 : Integer.parseInt(s2);
				
				gameStarted = false;
				creationMode = true;
				buttonsPanel.setVisible(true);
				Maps.createNewMap(xMapDimension, yMapDimension);
				finalLevel++;
				level = finalLevel;
				char [][] tempMap = Maps.getMap(level);
				game = new GameMap(tempMap, false, 0, false);
				game.readMap(true);
				
				printPanel.setGame(game);
				printPanel.setVisible(true);
				printPanel.repaint();
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblCreateMap.setForeground(new Color(46, 139, 87));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblCreateMap.setForeground(new Color(0, 0, 0));
			}
		});
		lblCreateMap.setFont(new Font("AR DARLING", Font.PLAIN, 30));
		menuBar.add(lblCreateMap);
		frame.getContentPane().setLayout(null);
		
		JButton btnStartGame = new JButton("Start Game");
		btnStartGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				gameStarted = true;
				if(!creationMode) {
				level = 1;
				} else {
					if (!keyUsed || !doorUsed) {
						return;
					}
				}
				char [][] tempMap = Maps.getMap(level);
				game = new GameMap(tempMap, Maps.hasMultipleOgre(level), nOgres, Maps.instantaneousDoorOpen(level));
				game.readMap(false);
				game.restartVariables();//restaurar variaveis static!!!!!
				for (int i = 0; i < game.getCharacters().size(); i++) {//percorrer as personagens
					if (game.getCharacters().get(0) instanceof Guard) {//alterar a personalidade do guarda
						Guard g = (Guard) game.getCharacters().get(0);
						g.setPersonality(guardPersonality);
					}
				}
				printPanel.setGame(game);
				printPanel.setVisible(true);
				frame.getContentPane().add(printPanel);
				frame.requestFocusInWindow();
				printPanel.repaint();
				buttonsPanel.setVisible(false);
				keyUsed = false; doorUsed = false;
				creationMode = false;
				
			}
		});
		btnStartGame.setBounds(635, 656, 135, 43);
		frame.getContentPane().add(btnStartGame);
		
		buttonsPanel = new JPanel();
		buttonsPanel.setBounds(558, 68, 212, 500);
		frame.getContentPane().add(buttonsPanel);
		buttonsPanel.setLayout(new GridLayout(3, 2));
		buttonsPanel.setVisible(false);
		
		btnWall = new JButton();
		btnWall.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				currentElement = 'X';
			}
		});
		btnWall.setIcon(new ImageIcon(Assets.tree1));
		buttonsPanel.add(btnWall);
		frame.validate();
		
		btnDoor = new JButton();
		btnDoor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				currentElement = 'I';
			}
		});
		btnDoor.setIcon(new ImageIcon(Assets.door));
		buttonsPanel.add(btnDoor);
		
		btnKey = new JButton();
		btnKey.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				currentElement = 'k';
			}
		});
		btnKey.setIcon(new ImageIcon(Assets.key));
		buttonsPanel.add(btnKey);
		
		btnOgre = new JButton();
		btnOgre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				currentElement = 'O';
			}
		});
		btnOgre.setIcon(new ImageIcon(Assets.ogreFrontStop));
		buttonsPanel.add(btnOgre);
		
		btnHero = new JButton();
		btnHero.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				currentElement = 'H';
			}
		});
		btnHero.setIcon(new ImageIcon(Assets.heroFrontStop));
		buttonsPanel.add(btnHero);
		
		JButton btnHeroArmed = new JButton();
		btnHeroArmed.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				currentElement = 'A';
			}
		});
		buttonsPanel.add(btnHeroArmed);
	
		
	}

	public void updateGraphics() {
		game.update();
		printPanel.repaint();
		if (game.isEndOfGame()) {
			if (game.isVictory()) {
				level++;
				if (level > finalLevel) { // acabou o jogo e ganhou
					game = null;
					gameStarted = false;
				} else { // proximo nivel
					char[][] tempMap = Maps.getMap(level);
					game = new GameMap(tempMap, Maps.hasMultipleOgre(level), nOgres, Maps.instantaneousDoorOpen(level));
					game.readMap(false);
					printPanel.setGame(game);
					printPanel.repaint();
				}
			} else { // perdeu o jogo
				game = null;
				gameStarted = false;
			}	
		}
		}
	public void convertCoordinates(int x, int y) {
		//coordenadas origem do mapa/painel: x = 12 ; y = 68
		char mapArray[][] = game.getCurrentMap().getMap();
		int width = mapArray[0].length;
		int height = mapArray.length;
		
		xMouseMap = (x-21)/(500/width);
		yMouseMap = (y-146)/(500/height);
	}
}
