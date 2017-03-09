package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
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

public class graphic {

	private JFrame frame;
	private JTextField textField;

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
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblGuardPersonality = new JLabel("Guard personality");
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Rookie", "Drunken", "Suspicious", "Obedient"}));
		comboBox.setMaximumRowCount(4);
		GroupLayout gl_Variables = new GroupLayout(Variables);
		gl_Variables.setHorizontalGroup(
			gl_Variables.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_Variables.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_Variables.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNumberOfOgres, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblGuardPersonality))
					.addGap(18)
					.addGroup(gl_Variables.createParallelGroup(Alignment.LEADING)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(387, Short.MAX_VALUE))
		);
		gl_Variables.setVerticalGroup(
			gl_Variables.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_Variables.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_Variables.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNumberOfOgres, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_Variables.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGuardPersonality)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		);
		Variables.setLayout(gl_Variables);
		
		JPanel Buttons = new JPanel();
		
		JPanel Movement = new JPanel();
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.setVerticalAlignment(SwingConstants.TOP);
		
		JButton btnNewButton = new JButton("Exit");
		btnNewButton.addActionListener(new ActionListener() {
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
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		gl_Buttons.setVerticalGroup(
			gl_Buttons.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Buttons.createSequentialGroup()
					.addGap(17)
					.addComponent(btnNewGame)
					.addGap(89)
					.addComponent(Movement, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
					.addGap(58)
					.addComponent(btnNewButton)
					.addGap(36))
		);
		SpringLayout sl_Movement = new SpringLayout();
		Movement.setLayout(sl_Movement);
		JButton btnNewButton_1 = new JButton("Up");
		sl_Movement.putConstraint(SpringLayout.NORTH, btnNewButton_1, 0, SpringLayout.NORTH, Movement);
		sl_Movement.putConstraint(SpringLayout.WEST, btnNewButton_1, 40, SpringLayout.WEST, Movement);
		sl_Movement.putConstraint(SpringLayout.EAST, btnNewButton_1, -40, SpringLayout.EAST, Movement);
		Movement.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Left");
		sl_Movement.putConstraint(SpringLayout.NORTH, btnNewButton_2, 15, SpringLayout.SOUTH, btnNewButton_1);
		sl_Movement.putConstraint(SpringLayout.WEST, btnNewButton_2, 0, SpringLayout.WEST, Movement);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Movement.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Right");
		sl_Movement.putConstraint(SpringLayout.SOUTH, btnNewButton_2, 0, SpringLayout.SOUTH, btnNewButton_3);
		sl_Movement.putConstraint(SpringLayout.EAST, btnNewButton_2, -6, SpringLayout.WEST, btnNewButton_3);
		sl_Movement.putConstraint(SpringLayout.NORTH, btnNewButton_3, 15, SpringLayout.SOUTH, btnNewButton_1);
		sl_Movement.putConstraint(SpringLayout.WEST, btnNewButton_3, 84, SpringLayout.WEST, Movement);
		sl_Movement.putConstraint(SpringLayout.EAST, btnNewButton_3, 162, SpringLayout.WEST, Movement);
		Movement.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Down");
		sl_Movement.putConstraint(SpringLayout.WEST, btnNewButton_4, 40, SpringLayout.WEST, Movement);
		sl_Movement.putConstraint(SpringLayout.EAST, btnNewButton_4, -40, SpringLayout.EAST, Movement);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		sl_Movement.putConstraint(SpringLayout.SOUTH, btnNewButton_3, -13, SpringLayout.NORTH, btnNewButton_4);
		sl_Movement.putConstraint(SpringLayout.SOUTH, btnNewButton_4, 0, SpringLayout.SOUTH, Movement);
		Movement.add(btnNewButton_4);
		Buttons.setLayout(gl_Buttons);
		
		JPanel panel = new JPanel();
		
		JTextArea textArea = new JTextArea();
		
		JLabel lblNewLabel = new JLabel(" ");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 431, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 306, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
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
	}
}
