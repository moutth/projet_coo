package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.Controller;
import model.Model;
import model.MsgUser;
import model.User;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
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
	JScrollPane messagesScroll;
	JList<String> messages_list;
	DefaultListModel<String> messages_string;
	private User activeuser;
	JList<String> connected_list;
	DefaultListModel<String> connected_string;
	private JTextField newPseudo;

	/**
	 * Create the frame.
	 */
	public Principal(Controller controller, Model model, Connexion connexion) {
		this.setTitle(model.getCurrentUser().getPseudo());
		this.controller = controller;
		this.model = model;
		this.connexion = connexion;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 695, 546);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		connected_string = new DefaultListModel<>();
		InitializeConnected();
		connected_list = new JList<>(connected_string);
		messages_string = new DefaultListModel<>();
		messages_list = new JList<>(messages_string);
		messages_list.setBounds(12, 40, 463, 296);
		chatPanel = new JPanel();
		chatPanel.setBounds(12, 45, 487, 405);
		contentPane.add(chatPanel);
		chatPanel.setLayout(null);

		chatPanel.add(messages_list);
		messagesScroll = new JScrollPane();
		messagesScroll.setBounds(0, 39, 475, 299);
		chatPanel.add(messagesScroll);

		activeUser = new JLabel("lol");
		activeUser.setBounds(12, 12, 70, 15);
		chatPanel.add(activeUser);

		messageField = new JTextField();
		messageField.setBounds(10, 350, 400, 43);
		chatPanel.add(messageField);
		messageField.setColumns(10);
		connected_list.addListSelectionListener((ListSelectionListener) new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting()) {
					JList source = (JList) event.getSource();
					List<MsgUser> allMessages = null;
					if (source.getSelectedValue() != null) {
						String selected = source.getSelectedValue().toString();
						// DEBUG
						System.out.println(selected);
						ArrayList<User> connectedUserList = model.getConnectedUserList();
						for (int i = 0; i < connectedUserList.size(); i++) {
							if (connectedUserList.get(i).getPseudo().equals(selected)) {
								activeuser = connectedUserList.get(i);
								controller.comSystem.EstablishConnexion(activeuser);
								allMessages = controller.database.GetHistory(activeuser, model.getCurrentUser());
								InitMessages(allMessages);
								break;
							}
						}

						// set pseudo in title of the chat pannel
						activeUser.setText(selected);
						chatPanel.setVisible(true);
						InitMessages(allMessages);
						// activeUser.setText(selected)
						// Envoyer le message Via TCP
						// MsgUser msg = new MsgUser("oui et toi ?");
						// controller.database.SaveMsg(userSender, userReceiver, msg);
						source.clearSelection();
					}
				}

			}
		});

		JScrollPane scrollPane = new JScrollPane(connected_list);
		scrollPane.setBounds(511, 45, 170, 407);
		contentPane.add(scrollPane);

		JButton reduceButon = new JButton("Reduce");
		reduceButon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		reduceButon.setBounds(389, 1, 86, 37);
		chatPanel.add(reduceButon);

		JButton endButton = new JButton("End");
		endButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chatPanel.setVisible(false);
				controller.comSystem.EndConnexion(activeuser.getUserID());
				// DEBUG
				activeUser.setText("lol");
				messages_string.removeAllElements();
			}
		});
		endButton.setBounds(303, 1, 86, 37);
		chatPanel.add(endButton);

		JButton sendmessageButton = new JButton(">");
		sendmessageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				Date date = new Date();
				String input = messageField.getText();
				String msg = input;
				messages_string.addElement("moi: " + msg + " ( " + formatter.format(date) + " ) ");
				getController().comSystem.ServChatTo(activeuser.getUserID()).send(input);
				MsgUser msgTosave = new MsgUser(msg);
				controller.database.SaveMsg(model.getCurrentUser(), activeuser, msgTosave);
				messageField.setText("");

			}
		});
		sendmessageButton.setBounds(422, 350, 53, 43);
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

				String Pseudo = newPseudo.getText();
				model.getCurrentUser().setPseudo(Pseudo);
				controller.comSystem.SendSystemChangePseudo(Pseudo);
				getinstancePrincipal().setTitle(Pseudo);
				newPseudo.setText("");

			}
		});
		changepseudoButton.setBounds(407, 0, 145, 35);
		contentPane.add(changepseudoButton);

		newPseudo = new JTextField();
		newPseudo.setBounds(564, 6, 117, 23);
		contentPane.add(newPseudo);
		newPseudo.setColumns(10);
		chatPanel.setVisible(false);

	}

	public Connexion getConnexion() {
		return connexion;
	}

	private void InitializeConnected() {
		ArrayList<User> List = model.getConnectedUserList();
		for (int i = 0; i < List.size(); i++) {
			connected_string.addElement(List.get(i).getPseudo());
		}
	}

	private void InitMessages(List<MsgUser> allMessages) {
		String currentString;
		messages_string.removeAllElements();
		for (int i = 0; i < allMessages.size(); i++) {
			MsgUser msg = allMessages.get(i);
			if (msg.getIdSender() == model.getCurrentUser().getUserID()) {
				currentString = "moi: " + msg.getContent() + " ( " + msg.getDate() + " )";
			} else {
				currentString = model.userFromID(msg.getIdSender()).getPseudo() + ": " + msg.getContent() + " ( "
						+ msg.getDate() + " )";

			}
			messages_string.addElement(currentString);
		}
	}

	public void SomeoneChangedPseudo(String OldPseudo, String NewPseudo) {
		for (int i = 0; i < connected_string.size(); i++) {
			if (connected_string.get(i).equals(OldPseudo)) {
				if (connected_string.get(i).equals(activeUser.getText())) {
					activeUser.setText(NewPseudo);
					List<MsgUser> allMessages = controller.database.GetHistory(activeuser, model.getCurrentUser());
					InitMessages(allMessages);
				}
				connected_string.setElementAt(NewPseudo, i);
			}
		}
	}

	public void updateMessageHistory() {
		List<MsgUser> allMessages = controller.database.GetHistory(activeuser, model.getCurrentUser());
		InitMessages(allMessages);
	}

	public void addConnecteduser(User user) {
		connected_string.addElement(user.getPseudo());
	}

	public void removeConnecteduser(User user) {
		if (user.getPseudo().equals(activeUser.getText())) {
			chatPanel.setVisible(false);
			controller.comSystem.EndConnexion(activeuser.getUserID());
			// DEBUG
			activeUser.setText("lol");
			messages_string.removeAllElements();
			connected_string.removeElement(user.getPseudo());
		} else {
			connected_string.removeElement(user.getPseudo());
		}
	}

	private void FermerFenetre() {
		this.setVisible(false);
	}

	public Controller getController() {
		return controller;
	}

	public Principal getinstancePrincipal() {
		return this;
	}
}
