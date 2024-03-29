package dkeep.gui;

import dkeep.logic.*;
import dkeep.logic.Guard.Personality;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
//import java.awt.BorderLayout;
//import javax.swing.BoxLayout;
import javax.swing.JPanel;
//import java.awt.GridLayout;
import javax.swing.JTextField;
//import java.awt.GridBagLayout;
//import java.awt.GridBagConstraints;
//import java.awt.Insets;
import javax.swing.SwingConstants;
//import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpringLayout;
import java.awt.Font;
//import javax.swing.JProgressBar;

import java.util.concurrent.ThreadLocalRandom;
//import java.util.concurrent.TimeUnit;

public class graphic extends Thread{

	private JFrame frame;
	private JTextField ogreNumber;
	private JTextArea textArea = new JTextArea();
	private JLabel lblstatus = new JLabel("Please start you game with the variables you choose :)");
	private JButton btnUp = new JButton("Up");
	private JButton btnRight = new JButton("Right");
	private JButton btnDown = new JButton("Down");
	private JButton btnLeft = new JButton("Left");	
	
	private static GameMap game = null;
	private int level = 1;
	private int finalLevel = 3;
	private int nOgres = 0;
	
	
	public GameMap getGame() {return game;}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					graphic window = new graphic();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}
		});
	}
	
	public void moveText() {
		int i = ThreadLocalRandom.current().nextInt(0, 25);
		switch (i) {
		case 0:
			lblstatus.setText("Whoa! Nice move there!");
			break;
		case 1:
			lblstatus.setText("Ehehehe! I knew you were going to do that! ;)");
			break;
		case 2:
			lblstatus.setText("You're going to get caught!");
			break;
		case 3:
			lblstatus.setText("Guard: 'I see you!'");
			break;
		case 4:
			lblstatus.setText("Tip: Follow the rabit!");
			break;
		case 5:
			lblstatus.setText("Are you crazy?!");
			break;
		case 6:
			lblstatus.setText("What is it like to be the hero?");
			break;
		case 7:
			lblstatus.setText("Tic Toc Tic Toc, time is ticking!");
			break;
		case 8:
			lblstatus.setText("These messages are cringy!");
			break;
			
		}
	}
	
	public void updateGraphics(){
		game.update();
		textArea.setText(game.getMap());
		moveText();
		if (game.isEndOfGame()) {
			if (game.isVictory()) {
				level++;
				if(level > finalLevel) { //acabou o jogo e ganhou
					textArea.setFont(new Font(textArea.getFont().getName(), Font.BOLD, 90));
					textArea.setText("VICTORY!!!");
					game = null;
					btnUp.setEnabled(false);
					btnDown.setEnabled(false);
					btnLeft.setEnabled(false);
					btnRight.setEnabled(false);
				} else { //proximo nivel
					char [][] tempMap = Maps.getMap(level);
					game = new GameMap(tempMap, Maps.hasMultipleOgre(level), nOgres, Maps.instantaneousDoorOpen(level));
					game.readMap();
					game.restartVariables();//restaurar variaveis static!!!!!
					textArea.setText(game.getMap());
				}
			} else { //perdeu o jogo
				textArea.setFont(new Font(textArea.getFont().getName(), Font.BOLD, 140));
				textArea.setText("GAME\nOVER");
				game = null;
				btnUp.setEnabled(false);
				btnDown.setEnabled(false);
				btnLeft.setEnabled(false);
				btnRight.setEnabled(false);
			}
		}
				
	}

	/**
	 * Create the application.
	 */
	public graphic() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 660, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel Window = new JPanel();
		Window.setBounds(0, 0, 642, 433);
		frame.getContentPane().add(Window);
		
		JPanel Variables = new JPanel();
		
		JLabel lblNumberOfOgres = new JLabel("Number of Ogres");
		lblNumberOfOgres.setHorizontalAlignment(SwingConstants.LEFT);
		
		ogreNumber = new JTextField();
		ogreNumber.setHorizontalAlignment(SwingConstants.CENTER);
		ogreNumber.setColumns(10);
		
		JLabel lblGuardPersonality = new JLabel("Guard personality");
		
		JComboBox personalityBox = new JComboBox();
		personalityBox.setModel(new DefaultComboBoxModel(new String[] {"Rookie", "Drunken", "Suspicious", "Obedient"}));
		personalityBox.setMaximumRowCount(4);
		GroupLayout gl_Variables = new GroupLayout(Variables);
		gl_Variables.setHorizontalGroup(
			gl_Variables.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Variables.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_Variables.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNumberOfOgres, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblGuardPersonality))
					.addGap(18)
					.addGroup(gl_Variables.createParallelGroup(Alignment.LEADING)
						.addComponent(ogreNumber, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addComponent(personalityBox, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(389, Short.MAX_VALUE))
		);
		gl_Variables.setVerticalGroup(
			gl_Variables.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_Variables.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_Variables.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNumberOfOgres, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
						.addComponent(ogreNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_Variables.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGuardPersonality)
						.addComponent(personalityBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		);
		
		Variables.setLayout(gl_Variables);
		
		JPanel Buttons = new JPanel();
		
		JPanel Movement = new JPanel();
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ogreNumber.getText().equals(""))
					nOgres = 0;
				else{
					if (Integer.parseInt(ogreNumber.getText()) > 5)
						nOgres = 5;
					else if (Integer.parseInt(ogreNumber.getText()) < 0)
						nOgres = 0;
					else 
						nOgres = Integer.parseInt(ogreNumber.getText());
				}
				int personality = personalityBox.getSelectedIndex(); // 0 - Rookie; 1 - Drunken; 2 - Suspicious; 3 - Obedient
				level = 1;
				char [][] tempMap = Maps.getMap(level);
				game = new GameMap(tempMap, Maps.hasMultipleOgre(level), nOgres, Maps.instantaneousDoorOpen(level));
				game.readMap();
				for (int i = 0; i < game.getCharacters().size(); i++) {//percorrer as personagens
					if (game.getCharacters().get(0) instanceof Guard) {//alterar a personalidade do guarda
						Guard g = (Guard) game.getCharacters().get(0);
						g.setPersonality(Personality.values()[personality]);
					}
				}
				textArea.setFont(new Font(textArea.getFont().getName(), Font.BOLD, 24));
				textArea.setText(game.getMap());
				lblstatus.setText("Game started!!! :D");
				btnUp.setEnabled(true);
				btnDown.setEnabled(true);
				btnLeft.setEnabled(true);
				btnRight.setEnabled(true);
			}
		});
		btnNewGame.setVerticalAlignment(SwingConstants.TOP);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		
		GroupLayout gl_Buttons = new GroupLayout(Buttons);
		gl_Buttons.setHorizontalGroup(
			gl_Buttons.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Buttons.createSequentialGroup()
					.addGroup(gl_Buttons.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_Buttons.createSequentialGroup()
							.addGap(45)
							.addComponent(btnNewGame, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_Buttons.createSequentialGroup()
							.addContainerGap()
							.addComponent(Movement, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_Buttons.createSequentialGroup()
							.addGap(44)
							.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(11, Short.MAX_VALUE))
		);
		gl_Buttons.setVerticalGroup(
			gl_Buttons.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Buttons.createSequentialGroup()
					.addGap(17)
					.addComponent(btnNewGame)
					.addGap(89)
					.addComponent(Movement, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
					.addGap(58)
					.addComponent(btnExit)
					.addGap(36))
		);
		SpringLayout sl_Movement = new SpringLayout();
		Movement.setLayout(sl_Movement);
		btnUp.setEnabled(false);
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game.startGame('w'))
					updateGraphics();
			}
		});
		sl_Movement.putConstraint(SpringLayout.NORTH, btnUp, 0, SpringLayout.NORTH, Movement);
		sl_Movement.putConstraint(SpringLayout.WEST, btnUp, 40, SpringLayout.WEST, Movement);
		sl_Movement.putConstraint(SpringLayout.EAST, btnUp, -40, SpringLayout.EAST, Movement);
		Movement.add(btnUp);
		
		btnLeft.setEnabled(false);
		sl_Movement.putConstraint(SpringLayout.NORTH, btnLeft, 15, SpringLayout.SOUTH, btnUp);
		sl_Movement.putConstraint(SpringLayout.WEST, btnLeft, 0, SpringLayout.WEST, Movement);
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if (game.startGame('a'))
						updateGraphics();
			}
		});
		Movement.add(btnLeft);
		
		btnRight.setEnabled(false);
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game.startGame('d'))
					updateGraphics();
			}
		});
		sl_Movement.putConstraint(SpringLayout.SOUTH, btnLeft, 0, SpringLayout.SOUTH, btnRight);
		sl_Movement.putConstraint(SpringLayout.EAST, btnLeft, -6, SpringLayout.WEST, btnRight);
		sl_Movement.putConstraint(SpringLayout.NORTH, btnRight, 15, SpringLayout.SOUTH, btnUp);
		sl_Movement.putConstraint(SpringLayout.WEST, btnRight, 84, SpringLayout.WEST, Movement);
		sl_Movement.putConstraint(SpringLayout.EAST, btnRight, 162, SpringLayout.WEST, Movement);
		Movement.add(btnRight);
		
		
		btnDown.setEnabled(false);
		sl_Movement.putConstraint(SpringLayout.WEST, btnDown, 40, SpringLayout.WEST, Movement);
		sl_Movement.putConstraint(SpringLayout.EAST, btnDown, -40, SpringLayout.EAST, Movement);
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if (game.startGame('s'))
						updateGraphics();
			}
		});
		sl_Movement.putConstraint(SpringLayout.SOUTH, btnRight, -13, SpringLayout.NORTH, btnDown);
		sl_Movement.putConstraint(SpringLayout.SOUTH, btnDown, 0, SpringLayout.SOUTH, Movement);
		Movement.add(btnDown);
		Buttons.setLayout(gl_Buttons);
		
		JPanel panel = new JPanel();
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 431, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblstatus, GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE))
					.addContainerGap())
		);
		textArea.setEditable(false);
		textArea.setFont(new Font("Courier New", Font.BOLD, 20));
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 306, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
					.addComponent(lblstatus)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		GroupLayout gl_Window = new GroupLayout(Window);
		gl_Window.setHorizontalGroup(
			gl_Window.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Window.createSequentialGroup()
					.addGroup(gl_Window.createParallelGroup(Alignment.LEADING, false)
						.addComponent(Variables, GroupLayout.PREFERRED_SIZE, 642, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_Window.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 451, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(Buttons, 0, 0, Short.MAX_VALUE)))
					.addGap(34))
		);
		gl_Window.setVerticalGroup(
			gl_Window.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Window.createSequentialGroup()
					.addComponent(Variables, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_Window.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(Buttons, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		Window.setLayout(gl_Window);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 642, 433);
		frame.getContentPane().add(panel_1);
	}
}
