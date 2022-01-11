package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import controller.Controller;
import model.Model;


import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Connexion extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JTextField passwordField;
	private Controller controller;
	private Inscription inscription;
	private Model model;
	private Choosepseudo choosepseudo;
	private boolean sign_up;
	private JLabel can_login;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Connexion(Controller controller, Model model) {
		this.controller = controller;
		this.model = model;
		this.sign_up = false;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(25, 12, 120, 15);
		contentPane.add(lblUsername);

		usernameField = new JTextField();
		usernameField.setBounds(25, 31, 196, 25);
		contentPane.add(usernameField);
		usernameField.setColumns(10);

		passwordField = new JTextField();
		passwordField.setColumns(10);
		passwordField.setBounds(25, 88, 196, 25);
		contentPane.add(passwordField);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(25, 69, 120, 15);
		contentPane.add(lblPassword);

		JButton btnLoging = new JButton("Login");
		btnLoging.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username = usernameField.getText();
				String password = passwordField.getText();
				boolean login = controller.database.Login(username, password);
				if (login)
				{
					// Demande d'envoi des pseudos à toutes les instances connectées
					controller.comSystem.SendSystemInit();
					// Instanciation de la prochaine fenêtre d'interface graphique
					choosepseudo = new Choosepseudo(controller, model,getConnexion());
					choosepseudo.setVisible(true);
					FermerFenetre();
				}
				else
				{
					can_login.setText("Username or Password are wrong !");
					can_login.setVisible(true);
				}
			}
		});
		btnLoging.setBounds(35, 125, 171, 25);
		contentPane.add(btnLoging);

		JButton btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				inscription = new Inscription(controller, getConnexion(), model);
				inscription.setVisible(true);
				FermerFenetre();
			}
		});

		btnNewButton.setBounds(35, 162, 171, 25);
		contentPane.add(btnNewButton);

		can_login = new JLabel("You have been register. You can now login");
		can_login.setForeground(Color.RED);
		can_login.setBounds(25, 215, 350, 15);
		can_login.setVisible(false);
		contentPane.add(can_login);
	}

	public Connexion getConnexion() {
		return this;
	}

	public boolean isSign_up() {
		return sign_up;
	}

	public void setSign_up(boolean sign_up) {
		this.sign_up = sign_up;
	}

	public JLabel getCan_login() {
		return can_login;
	}
	
	

	public JTextField getUsernameField() {
		return usernameField;
	}

	public JTextField getPasswordField() {
		return passwordField;
	}

	public void comebackPrincipal() {
		usernameField.setText("");
		passwordField.setText("");
		can_login.setVisible(false);
		this.setVisible(true);
	}
	
	
	private void FermerFenetre() {
		this.setVisible(false);
	}
}
