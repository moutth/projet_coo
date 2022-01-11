package gui;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import model.Model;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Choosepseudo extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private Controller controller;
	private Model model;
	private Principal principal;
	private Connexion connexion;
	private JLabel notavailable;
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
				String pseudo = textField.getText();
				if (model.IsAvailable(pseudo)) {
					principal = new Principal (controller, model,getConnexion());
					principal.setVisible(true);                                                            
					FermerFenetre();
					
					model.getCurrentUser().setPseudo(pseudo);
					controller.comSystem.SendSystemConnexion();
				}
				else {
					
					notavailable.setVisible(true);					
				}
			}
		});
		btnConfirm.setBounds(41, 63, 117, 25);
		contentPane.add(btnConfirm);
		
		notavailable = new JLabel("Your pseudo is not available, please choose another one!");
		notavailable.setForeground(Color.RED);
		notavailable.setBounds(12, 100, 424, 25);
		notavailable.setVisible(false);
		contentPane.add(notavailable);
	}
	
	void FermerFenetre() {
		this.setVisible(false);
	}

	public Connexion getConnexion() {
		return connexion;
	}
}
