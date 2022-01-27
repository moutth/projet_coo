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

public class Inscription extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel username_aleardy_used;
	private Controller controller;
	private Connexion connexion;
	private Model model;


	/**
	 * Create the frame.
	 */
	public Inscription(Controller controller, Connexion connexion, Model model) {
		this.setTitle("ChatSystem: Register");
		this.connexion = connexion;
		this.controller = controller;
		this.model = model;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(12, 12, 120, 15);
		contentPane.add(lblUsername);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(12, 31, 196, 25);
		contentPane.add(textField);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(12, 69, 120, 15);
		contentPane.add(lblPassword);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(12, 88, 196, 25);
		contentPane.add(textField_1);

		JButton btnNewButton = new JButton("Send");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textField.getText();
				String password = textField_1.getText();
				boolean inscription = controller.database.NewUser(username, password);
				if (inscription) {
					FermerFenetre();
					getInscription().connexion.getUsernameField().setText("");
					getInscription().connexion.getPasswordField().setText("");
					getInscription().connexion.getCan_login().setVisible(true);
					getInscription().connexion.getCan_login().setText("You have been register. You can now login");
					getInscription().connexion.setSign_up(true);
					getInscription().connexion.setVisible(true);
				}
				else
				{
					username_aleardy_used.setVisible(true);

				}
			}
		});
		
		btnNewButton.setBounds(22, 125, 166, 25);
		contentPane.add(btnNewButton);
		username_aleardy_used = new JLabel("Username aleardy used! please retry");
		username_aleardy_used.setForeground(Color.RED);
		username_aleardy_used.setBounds(12, 162, 277, 32);
		username_aleardy_used.setVisible(false);
		contentPane.add(username_aleardy_used);
		
		
	}
	public Inscription getInscription()
	{
		return this;
	}
	void FermerFenetre() {
		this.setVisible(false);
	}
}
