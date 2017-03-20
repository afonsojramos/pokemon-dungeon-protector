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

import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PlayGame {

	private JFrame frame;
	private PrintMap printPanel;
	private JMenuBar menuBar;
	private JLabel lblNumberOfOgres;
	private JLabel lblGuardPersonality;
	private JLabel lblCreateMap;
	
	private static GameMap game = null;
	private int level = 1;
	private int finalLevel = 3;
	private int nOgres = 1;
	private Personality guardPersonality = Personality.valueOf("Rookie");
	private boolean gameStarted = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayGame window = new PlayGame();
					window.frame.setVisible(true);
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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
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
		lblCreateMap.setFont(new Font("AR DARLING", Font.PLAIN, 30));
		menuBar.add(lblCreateMap);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 68, 500, 500);
		frame.getContentPane().add(panel);
		panel.setVisible(true);
		
		JButton btnStartGame = new JButton("Start Game");
		btnStartGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				gameStarted = true;
				level = 1;
				char [][] tempMap = Maps.getMap(level);
				game = new GameMap(tempMap, Maps.hasMultipleOgre(level), nOgres, Maps.instantaneousDoorOpen(level));
				game.readMap();
				for (int i = 0; i < game.getCharacters().size(); i++) {//percorrer as personagens
					if (game.getCharacters().get(0) instanceof Guard) {//alterar a personalidade do guarda
						Guard g = (Guard) game.getCharacters().get(0);
						g.setPersonality(guardPersonality);
					}
				}
				printPanel = new PrintMap();
				printPanel.setBounds(12, 68, 500, 500);
				printPanel.setGame(game);
				printPanel.setVisible(true);
				frame.add(printPanel);
				frame.requestFocusInWindow();
				panel.setVisible(false);
				printPanel.repaint();
				
			}
		});
		btnStartGame.setBounds(635, 656, 135, 43);
		frame.getContentPane().add(btnStartGame);
		
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
					game.readMap();
					game.restartVariables();// restaurar variaveis static!!!!!
					printPanel.setGame(game);
					printPanel.repaint();
				}
			} else { // perdeu o jogo
				game = null;
				gameStarted = false;
			}	
		}
		}
}
