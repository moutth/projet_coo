package gui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.Controller;
import model.Model;
import model.User;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Principal extends JFrame {

	private JPanel contentPane;
	private Controller controller;
	private Model model;
	private JLabel activeUser;
	private JPanel chatPanel;
	private Connexion connexion;
	private JTextField messageField;
	private User activeuser;
	JList connected_list;
	String[] connected_string;
	/**
	 * Create the frame.
	 */
	public Principal(Controller controller, Model model, Connexion connexion) {
		this.controller = controller;
		this.model = model;
		this.connexion = connexion;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 528, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

//		String[] test_user = { "test1", "test2", "test3", "test4", "test5", "test6", "test7", "test2", "test3", "test4",
//				"test5", "test6", "test7", "test2", "test3", "test4", "test5", "test6", "test7", "test2", "test3",
//				"test4", "test5", "test6", "test7", "test2", "test3", "test4", "test5", "test6", "test7", "test2",
//				"test3", "test4", "test5", "test6", "test7", "test2", "test3", "test4", "test5", "test6", "test7",
//				"test2", "test3", "test4", "test5", "test6", "test7", "test2", "test3", "test4", "test5", "test6",
//				"test7" };
		connected_string = pseudoConnecter();
		connected_list = new JList(connected_string);
		connected_list.addListSelectionListener((ListSelectionListener) new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting()) {
					JList source = (JList) event.getSource();
					String selected = source.getSelectedValue().toString();
					//DEBUG
					System.out.println(selected);
					ArrayList<User> connectedUserList = model.getConnectedUserList();
					for (int i = 0; i < connectedUserList.size(); i++) {
						if (connectedUserList.get(i).getPseudo().equals(selected)) {
							activeuser = connectedUserList.get(i);
						}
					}
					// set pseudo in title of the chat pannel
					activeUser.setText(selected);
					chatPanel.setVisible(true);
					// activeUser.setText(selected);
					
					controller.comSystem.EstablishConnexion(activeuser);

				}
			}
		});
		JScrollPane scrollPane = new JScrollPane(connected_list);
		scrollPane.setBounds(344, 47, 170, 360);
		contentPane.add(scrollPane);

		chatPanel = new JPanel();
		chatPanel.setBounds(12, 47, 332, 405);
		contentPane.add(chatPanel);
		chatPanel.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 39, 320, 299);
		chatPanel.add(scrollPane_1);

		activeUser = new JLabel("lol");
		activeUser.setBounds(12, 12, 70, 15);
		chatPanel.add(activeUser);

		JButton reduceButon = new JButton("Reduce");
		reduceButon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		reduceButon.setBounds(234, 1, 86, 37);
		chatPanel.add(reduceButon);
		
		JButton endButton = new JButton("End");
		endButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chatPanel.setVisible(false);
				controller.comSystem.EndConnexion(activeuser);
				//DEBUG
				activeUser.setText("lol");
			}
		});
		endButton.setBounds(152, 1, 86, 37);
		chatPanel.add(endButton);

		messageField = new JTextField();
		messageField.setBounds(10, 350, 248, 43);
		chatPanel.add(messageField);
		messageField.setColumns(10);

		JButton sendmessageButton = new JButton(">");
		sendmessageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Envoyer le message Via TCP
				User u = model.getCurrentUser();
			}
		});
		sendmessageButton.setBounds(267, 350, 53, 43);
		chatPanel.add(sendmessageButton);

		JButton disconnectButton = new JButton("Disconnect");
		disconnectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FermerFenetre();
				getConnexion().comebackPrincipal();
				controller.comSystem.SendSystemDeconnexion();
			}
		});
		disconnectButton.setBounds(12, 0, 117, 35);
		contentPane.add(disconnectButton);

		JButton changepseudoButton = new JButton("Change Pseudo");
		changepseudoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// create new panel
				//model.ChangePseudo(model.getCurrentUser(), getName());
			}
		});
		changepseudoButton.setBounds(134, 0, 145, 35);
		contentPane.add(changepseudoButton);
		chatPanel.setVisible(false);
		
	}

	
	public Connexion getConnexion() {
		return connexion;
	}

	private String[] pseudoConnecter() {
		ArrayList<User> List = model.connectedUserList;
		String[] pseudoList = new String[List.size()];
		for (int i = 0; i < List.size(); i++) {
			pseudoList[i] = List.get(i).getPseudo();
		}
		return pseudoList;
	}
	
	private void updatConnecter() {
		ArrayList<User> List = model.connectedUserList;
		String[] pseudoList = new String[List.size()];
		for (int i = 0; i < List.size(); i++) {
			pseudoList[i] = List.get(i).getPseudo();
		}
		
	}
	private void FermerFenetre() {
		this.setVisible(false);
	}
}
