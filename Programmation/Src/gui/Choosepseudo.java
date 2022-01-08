package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import model.Model;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.TextField;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Choosepseudo extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private Controller controller;
	private Model model;
	private Principal principal;
	private Connexion connexion;
	/**
	 * Create the frame.
	 */
	public Choosepseudo(Controller controller, Model model, Connexion connexion) {
		this.controller = controller;
		this.model = model;
		this.connexion = connexion;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(12, 26, 169, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblChoseYour = new JLabel("Chose your pseudo");
		lblChoseYour.setBounds(12, 7, 163, 15);
		contentPane.add(lblChoseYour);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//vérifcation grace à l'UDP
				principal = new Principal (controller, model,getConnexion());
				principal.setVisible(true);                                                            
				FermerFenetre();
				
			}
		});
		btnConfirm.setBounds(41, 63, 117, 25);
		contentPane.add(btnConfirm);
	}
	
	void FermerFenetre() {
		this.setVisible(false);
	}

	public Connexion getConnexion() {
		return connexion;
	}
	
	
}
