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
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;

import javax.swing.JLabel;
import java.awt.Component;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
//import java.util.Map;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSlider;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.GridLayout;
import java.awt.BorderLayout;

public class PlayGame {

	private JFrame frame;
	private PrintMap printPanel;
	private JMenuBar menuBar;
	private JLabel lblNumberOfOgres;
	private JLabel lblGuardPersonality;
	private JLabel lblCreateMap;
	private JLabel lblSaveGame;
	private JLabel lblLoadGame;
	private JLabel lblGameState;
	private JPanel buttonsPanel;
	private JButton btnStartGame;
	private JButton btnWall;
	private JButton btnDoor;
	private JButton btnKey;
	private JButton btnOgre;
	private JButton btnHero;
	private JButton btnHeroArmed;
	
	private static GameMap game = null;
	private int level = 1;
	private int finalLevel = 3;
	private int nOgres = 1;
	private Personality guardPersonality = Personality.valueOf("Rookie");
	private boolean gameStarted = false;
	private boolean creationMode = false;
	private int MapDimension = 10;
	private char currentElement = ' ';
	private int xMouseMap, yMouseMap; //coodenadas do rato na tabela de jogo
	private boolean keyUsed = false, doorUsed = false;
	private static PlayMusic play;
	private JPanel buttonsContrlPanel;
	private JButton btnLeft;
	private JButton btnRight;
	private JButton btnUp;
	private JButton btnDown;
	
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
				} catch (Exception e) {
					e.printStackTrace();
			}}});
	}

	/**
	 * Create the application.
	 */
	public PlayGame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Dungeon Protector");
		frame.setBounds(100, 100, 850, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		try {frame.setIconImage(ImageIO.read(new File("Utils/POKEBALL.png")));
		} catch (IOException e) {e.printStackTrace();}
		frameKeyboardListener();
		frameMouseListener();
		setMusic();
		createPainelPrint();
		createMenu();
		createButtons();
		buttonsListeners();
		labelListeners();
	}

	public void updateGraphics() {
		game.update();lblGameState.setText("");
		printPanel.repaint();
		if (game.isEndOfGame()) {
			if (game.isVictory()) {
				level++;
				if (level > finalLevel) { // acabou o jogo e ganhou
					game = null;gameStarted = false;lblGameState.setText("GREAT JOB! YOU WON!");
				} else { // proximo nivel
					char[][] tempMap = Maps.getMap(level);
					game = new GameMap(tempMap, Maps.hasMultipleOgre(level), nOgres, Maps.instantaneousDoorOpen(level));
					game.readMap(false);
					printPanel.setGame(game);
					printPanel.repaint();
					lblGameState.setText("Next level here we go!");}
			} else { // perdeu o jogo
				game = null;gameStarted = false; lblGameState.setText("You lost... Bummer :(");		deathSound();}}
	}

	public void convertCoordinates(int x, int y) {
		//coordenadas origem do mapa/painel: x = 12 ; y = 68
		char mapArray[][] = game.getCurrentMap().getMap();
		int width = mapArray[0].length;
		int height = mapArray.length;
		
		xMouseMap = (x-21)/(500/width);
		yMouseMap = (y-146)/(500/height);
	}
	
	public void setMusic() {
		@SuppressWarnings("unused")
		JFXPanel fxPanel = new JFXPanel();
		Media hit = new Media(new File("Utils/Music.mp3").toURI().toString());
		play = new PlayMusic(hit);
		play.playContinuous();
	}
	
	public void deathSound(){
		@SuppressWarnings("unused")
		JFXPanel fxPanel = new JFXPanel();
		Media hit = new Media(new File("Utils/death.mp3").toURI().toString());
		play = new PlayMusic(hit);
		play.playSound();
	}
	
	public void createMenu() {
		createMenu1();
		createMenu2();
		createMenu3();	
	}
	public void createMenu1() {
		lblGameState = new JLabel("");
		lblGameState.setBounds(12, 634, 300, 16);
		frame.getContentPane().add(lblGameState);
	}
	public void createMenu2() {
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		lblNumberOfOgres = new JLabel("   Number of Ogres");
		lblNumberOfOgres.setFont(new Font("AR DARLING", Font.PLAIN, 23));
		menuBar.add(lblNumberOfOgres);
		Component horizontalStrut = Box.createHorizontalStrut(20);
		menuBar.add(horizontalStrut);
		lblGuardPersonality = new JLabel("Guard Personality");
		lblGuardPersonality.setFont(new Font("AR DARLING", Font.PLAIN, 23));
		menuBar.add(lblGuardPersonality);
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		menuBar.add(horizontalStrut_1);
	}
	public void createMenu3() {
		lblCreateMap = new JLabel("Create Map");
		lblCreateMap.setFont(new Font("AR DARLING", Font.PLAIN, 23));
		menuBar.add(lblCreateMap);
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		menuBar.add(horizontalStrut_2);
		lblSaveGame = new JLabel("Save Game");
		lblSaveGame.setFont(new Font("AR DARLING", Font.PLAIN, 23));
		menuBar.add(lblSaveGame);
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		menuBar.add(horizontalStrut_3);
		lblLoadGame = new JLabel("Load Game");
		lblLoadGame.setFont(new Font("AR DARLING", Font.PLAIN, 23));
		menuBar.add(lblLoadGame);
	}
	public void createButtons() {
		createButtons1();
		createButtons2();
		createButtons3();
	}
	public void createButtons1() {
		btnStartGame = new JButton("Start Game");
		btnStartGame.setBounds(635, 656, 135, 43);
		frame.getContentPane().add(btnStartGame);
	
		buttonsPanel = new JPanel();
		buttonsPanel.setBounds(558, 68, 212, 500);
		frame.getContentPane().add(buttonsPanel);
		buttonsPanel.setLayout(new GridLayout(3, 2));
		buttonsPanel.setVisible(false);
		
		btnWall = new JButton();
		btnWall.setIcon(new ImageIcon(Assets.tree1));
		buttonsPanel.add(btnWall);
		frame.validate();
	}
	
	public void createButtons2() {
		btnDoor = new JButton(); btnOgre = new JButton(); btnKey = new JButton(); btnHero = new JButton(); btnHeroArmed = new JButton();
		btnDoor.setIcon(new ImageIcon(Assets.door));
		buttonsPanel.add(btnDoor);
		btnKey.setIcon(new ImageIcon(Assets.key));
		buttonsPanel.add(btnKey);	
		btnOgre.setIcon(new ImageIcon(Assets.ogreFrontStop));
		buttonsPanel.add(btnOgre);	
		btnHero.setIcon(new ImageIcon(Assets.heroFrontStop));
		buttonsPanel.add(btnHero);	
		btnHeroArmed.setIcon(new ImageIcon(Assets.heroFrontArmed));
		buttonsPanel.add(btnHeroArmed);
	}
	
	public void createButtons3() {
		buttonsContrlPanel = new JPanel();
		buttonsContrlPanel.setBounds(558, 215, 212, 200);
		frame.getContentPane().add(buttonsContrlPanel);
		buttonsContrlPanel.setLayout(new BorderLayout(0, 0));
		buttonsContrlPanel.setVisible(false);
		btnLeft = new JButton("LEFT");
		buttonsContrlPanel.add(btnLeft, BorderLayout.WEST);
		btnRight = new JButton("RIGHT");
		buttonsContrlPanel.add(btnRight, BorderLayout.EAST);
		btnUp = new JButton("UP");
		buttonsContrlPanel.add(btnUp, BorderLayout.NORTH);
		btnDown = new JButton("DOWN");
		buttonsContrlPanel.add(btnDown, BorderLayout.SOUTH);
		
	}
	
	public void frameKeyboardListener() {
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (gameStarted) {	switch (e.getKeyCode()) {
									case KeyEvent.VK_LEFT:	if (game.startGame('a')) {
															updateGraphics();} break;
									case KeyEvent.VK_RIGHT:	if (game.startGame('d')) {
															updateGraphics();} break;
									case KeyEvent.VK_UP:	if (game.startGame('w')) {
															updateGraphics();} break;
									case KeyEvent.VK_DOWN:	if (game.startGame('s')) {
															updateGraphics();} break;
					}}}});
	}
	
	public void frameMouseListener() {
		frame.addMouseListener(new MouseAdapter() {
			@Override public void mouseClicked(MouseEvent arg0) {
				if(creationMode) {
					if(arg0.getX() >= 21 && arg0.getX() <= 521 && arg0.getY() >= 146 && arg0.getY() <= 646) { 
						convertCoordinates(arg0.getX(), arg0.getY());
						boolean result = Maps.changeNewMap(xMouseMap, yMouseMap, currentElement);
						if(currentElement == 'k' && result) {keyUsed = true;currentElement = ' ';
						} else if (currentElement == 'I' && result) {	doorUsed = true;}
						game.changeMapArray(Maps.getMap(level));
						game.readMap(true);
						printPanel.repaint();
					}}}});
	}

	public void numberOfOgresListener() {
		lblNumberOfOgres.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String[] possibilities = { "1", "2", "3", "4", "5" };
				String s = (String) JOptionPane.showInputDialog(frame, "Number of Ogres:", "Options",
						JOptionPane.PLAIN_MESSAGE, null, possibilities, null);
				nOgres = s == null ? 1 : Integer.parseInt(s);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNumberOfOgres.setForeground(new Color(102, 205, 170));}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNumberOfOgres.setForeground(new Color(0, 0, 0));	}
		});
	}
	public void guardPersonalityListener() {
		lblGuardPersonality.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String[] possibilities = { "Rookie", "Drunken", "Suspicious", "Obedient" };
				String s = (String) JOptionPane.showInputDialog(frame, "Guard personality", "Options",
						JOptionPane.PLAIN_MESSAGE, null, possibilities, "Rookie");
				guardPersonality = ((s != null) && (s.length() > 0)) ? Personality.valueOf(s) : Personality.valueOf("Rookie");}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblGuardPersonality.setForeground(new Color(32, 178, 170));}
			@Override
			public void mouseExited(MouseEvent e) {
				lblGuardPersonality.setForeground(new Color(0, 0, 0));}
		});
	}
	public void creatMapListener() {
		lblCreateMap.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JFrame parent = new JFrame();
			    JOptionPane optionPane = new JOptionPane();
			    optionPane.setIcon(Assets.icon);
			    JSlider slider = getSlider(optionPane);
			    optionPane.setMessage(new Object[] { "Select a dimension for the map:", slider });
			    optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
			    optionPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
			    JDialog dialog = optionPane.createDialog(parent, "Settings");
			    dialog.setVisible(true);
			    MapDimension = slider.getValue();	
				gameStarted = false;
				creationMode = true;
				buttonsPanel.setVisible(true);
				Maps.createNewMap(MapDimension, MapDimension);
				finalLevel++;
				level = finalLevel;
				char [][] tempMap = Maps.getMap(level);
				game = new GameMap(tempMap, false, 0, false);
				game.readMap(true);
				printPanel.setGame(game);
				printPanel.setVisible(true);
				printPanel.repaint();
				lblGameState.setText("In Creation Mode...");
			}

			public JSlider getSlider(final JOptionPane optionPane) {
				JSlider slider = new JSlider();
				slider.setMinimum(5); slider.setMaximum(20);
				slider.setMinorTickSpacing(1); slider.setMajorTickSpacing(5);
				slider.setValue(7);
				slider.setPaintTicks(true);	slider.setPaintLabels(true);
				return slider;
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
	}
	
	public void saver(String s){
		if(s == null) return;
		try {FileOutputStream fileOut = new FileOutputStream("Utils/"+s+".txt");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(game);
	         out.close();
	         fileOut.close();
	         FileOutputStream fileOut2 = new FileOutputStream("Utils/"+s+"MAPSLevel.txt");
	         ObjectOutputStream out2 = new ObjectOutputStream(fileOut2);
	         out2.writeObject(Maps.getCurrentLevel());
	         out2.close();
	         fileOut2.close();
	         FileOutputStream fileOut3 = new FileOutputStream("Utils/"+s+"MAPSFinalLevel.txt");
	         ObjectOutputStream out3 = new ObjectOutputStream(fileOut3);
	         out3.writeObject(Maps.getFinalLevel());
	         out3.close();
	         fileOut3.close();
	         System.out.printf("Serialized data is saved!");lblGameState.setText("Your current game is saved!"); } catch(IOException i) { i.printStackTrace();  }
	}
	
	public void saveGameListener() {
		lblSaveGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String s = (String) JOptionPane.showInputDialog(frame, "Name of the file:", "Options", JOptionPane.PLAIN_MESSAGE, null, null, null);
				saver(s);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblSaveGame.setForeground(new Color(135, 215, 128));}
			@Override
			public void mouseExited(MouseEvent e) {
				lblSaveGame.setForeground(new Color(0, 0, 0));		}
		});
	}
	
	public boolean loader(String s){
		if (s == null) return false;
		try {
	         FileInputStream fileIn = new FileInputStream("Utils/"+s+".txt");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         game = (GameMap) in.readObject();
	         in.close();
	         fileIn.close();
	         FileInputStream fileIn2 = new FileInputStream("Utils/"+s+"MAPSLevel.txt");
	         ObjectInputStream in2 = new ObjectInputStream(fileIn2);
	         level = (int) in2.readObject();
	         in2.close();
	         fileIn2.close();
	         FileInputStream fileIn3 = new FileInputStream("Utils/"+s+"MAPSFinalLevel.txt");
	         ObjectInputStream in3 = new ObjectInputStream(fileIn3);
	         finalLevel = (int) in3.readObject();
	         in3.close();
	         fileIn3.close();
	         Maps.setCurrentLevel(level); Maps.setFinalLevel(finalLevel);
	      }catch(IOException i) {
	         i.printStackTrace();
	         return false;
	      }catch(ClassNotFoundException c) {
	         System.out.println("GameMap class not found");
	         c.printStackTrace();
	         return false;
	      }
		return true;
	}
	
	public void loadGameListener() {
		lblLoadGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String s = (String) JOptionPane.showInputDialog(frame, "Name of the file:", "Options",
						JOptionPane.PLAIN_MESSAGE, null, null, null);
				if(!loader(s)) return;
				setupGame();
				gameStarted = true; lblGameState.setText("Game is starting"); }
			@Override
			public void mouseEntered(MouseEvent e) {
				lblLoadGame.setForeground(new Color(141, 180, 90));}
			@Override
			public void mouseExited(MouseEvent e) {
				lblLoadGame.setForeground(new Color(0, 0, 0));	}
		});
	}
	
	public void labelListeners() {
		numberOfOgresListener();
		guardPersonalityListener();
		creatMapListener();
		saveGameListener();
		loadGameListener();
	}

	public void startGameListener() {
		btnStartGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!creationMode) {
				level = 1;
				} else {
					if (!keyUsed || !doorUsed) { return;
				}}
				gameStarted = true; lblGameState.setText("Game is starting");
				char [][] tempMap = Maps.getMap(level);
				game = new GameMap(tempMap, Maps.hasMultipleOgre(level), nOgres, Maps.instantaneousDoorOpen(level));
				setupGame();
				buttonsContrlPanel.setVisible(true);
		}});
	}
	
	public void setupGame() {
		game.readMap(false);
		game.restartVariables();//restaurar variaveis static!!!!!
		for (int i = 0; i < game.getCharacters().size(); i++) {//percorrer as personagens
			if (game.getCharacters().get(0) instanceof Guard) {//alterar a personalidade do guarda
				Guard g = (Guard) game.getCharacters().get(0);
				g.setPersonality(guardPersonality);			}}
		printPanel.setGame(game);
		printPanel.setVisible(true);
		frame.getContentPane().add(printPanel);
		frame.requestFocusInWindow();
		printPanel.repaint();
		buttonsPanel.setVisible(false);
		keyUsed = false; doorUsed = false;	creationMode = false;
	}
	
	public void buttonsListeners1() {		
		btnWall.addMouseListener(new MouseAdapter() {// WALL BUTTON
			@Override
			public void mouseClicked(MouseEvent e) {
				currentElement = 'X';lblGameState.setText("Tree");
			}
		});	
		btnDoor.addMouseListener(new MouseAdapter() {// DOOR BUTTON
			@Override
			public void mouseClicked(MouseEvent e) {
				currentElement = 'I';lblGameState.setText("Door");
		}});
		btnKey.addMouseListener(new MouseAdapter() {// KEY BUTTON
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!keyUsed) {
					currentElement = 'k';lblGameState.setText("Key");
		}}});
	}

	public void buttonsListeners2() {
		// OGRE BUTTON
		btnOgre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				currentElement = 'O';lblGameState.setText("Ogre");
			}
		});

		// HERO BUTTON
		btnHero.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				currentElement = 'H';lblGameState.setText("Hero");
			}
		});

		// HERO ARMED BUTTON
		btnHeroArmed.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				currentElement = 'A';lblGameState.setText("Hero Armed");
			}
		});
	}
	public void buttonsListeners3() {
		btnLeft.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {if(gameStarted){if (game.startGame('a')) {updateGraphics();}} frame.requestFocus();}
		});
		btnRight.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {if(gameStarted){if (game.startGame('d')) {updateGraphics();}} frame.requestFocus();}
		});
		btnUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {if(gameStarted){if (game.startGame('w')) {updateGraphics();}}frame.requestFocus();}
		});
		btnDown.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {if(gameStarted){if (game.startGame('s')) {updateGraphics();}} frame.requestFocus();}
		});
	}
	public void buttonsListeners() {
		startGameListener();
		buttonsListeners1();
		buttonsListeners2();
		buttonsListeners3();
	}
	
	public void createPainelPrint() {
		printPanel = new PrintMap();
		printPanel.setBounds(12, 68, 500, 500);
		printPanel.setVisible(true);
		frame.getContentPane().add(printPanel);
		frame.requestFocusInWindow();
		}
}
