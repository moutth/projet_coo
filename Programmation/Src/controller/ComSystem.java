package controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;

import model.MsgSystem;
import model.MsgUser;
import model.User;

public class ComSystem {

	public final static int SERVSYST = 1200;
	public final static int SERVACCEPT = 1201;
	public final static int UDPMAXSIZE = 256;

	public InetAddress BROADCAST;

	public Controller controller;

	// socket qui gère les datagrammes systemes
	public ServSystem servSystem;
	// socket d'acceptation des demandes de connexion TCP
	public ServAccept servAccept;
	// liste des sockets TCP de réception de chat
	public List<ServChatIn> servChatInList;
	// liste des sockets TCP d'envoi de chat
	public List<ServChatOut> servChatOutList;
	// liste des id de chaque destinataire des servChat
	public List<Integer> servChatInListID;
	public List<Integer> servChatOutListID;
	
	public List<ChatThread> chatThreads;

	public String localIP;

	ComSystem(Controller in) {
		controller = in;
		servSystem = new ServSystem(this);
		servAccept = new ServAccept(this);
		
		servChatInList = new ArrayList<ServChatIn>();
		servChatOutList = new ArrayList<ServChatOut>();
		servChatOutListID = new ArrayList<Integer>();
		servChatInListID = new ArrayList<Integer>();
		
		chatThreads = new ArrayList<ChatThread>();
		
		String ip = null;
		try (final DatagramSocket socket = new DatagramSocket()) {
			// create a socket only to get the local ip adress from it
			socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
			ip = socket.getLocalAddress().getHostAddress();
		} catch (SocketException e4) {
			e4.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		localIP = ip;
		// DEBUG
		System.out.println(localIP);
		controller.model.getCurrentUser().setIp(localIP.toString());

		try {
			BROADCAST = InetAddress.getByName("255.255.255.255");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void SendUdp(InetAddress ip, int port, MsgSystem msg) throws IOException {
		Scanner sc = new Scanner(System.in);

		// Création de la socket avec un numéro de port aléatoire
		DatagramSocket ds = new DatagramSocket();

		// Créer le Datagramme pour envoyer les données
		DatagramPacket DpSend = new DatagramPacket(msg.toString().getBytes(), msg.toString().length(), ip, port);

		// Invoquer la méthode send pour envoyer les données
		ds.send(DpSend);

		// Fermeture des sockets
		sc.close();
		ds.close();

	}

	public void SendSystemInit() {
		MsgSystem msg = new MsgSystem(controller.model, "Init", "");
		try {
			controller.comSystem.SendUdp(BROADCAST, ComSystem.SERVSYST, msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void SendSystemInitAnswer() {
		MsgSystem msg = new MsgSystem(controller.model, "InitAnswer", "");
		try {
			controller.comSystem.SendUdp(BROADCAST, ComSystem.SERVSYST, msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void SendSystemConnexion() {
		MsgSystem msg = new MsgSystem(controller.model, "Connexion", "");
		try {
			controller.comSystem.SendUdp(BROADCAST, ComSystem.SERVSYST, msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void SendSystemDeconnexion() {
		MsgSystem msg = new MsgSystem(controller.model, "Deconnexion", "");
		try {
			controller.comSystem.SendUdp(BROADCAST, ComSystem.SERVSYST, msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void SendSystemChangePseudo(String newPseudo) {
		MsgSystem msg = new MsgSystem(controller.model, "ChangePseudo", "newPseudo");
		try {
			controller.comSystem.SendUdp(BROADCAST, ComSystem.SERVSYST, msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void EstablishConnexion(User dest) {
		try {
			servChatOutList.add(new ServChatOut(this, dest, new Socket(dest.getIp(), SERVACCEPT)));
			servChatOutListID.add(dest.getUserID());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void SendTo(int destID, String msg) {
		ServChatOut servToDest;
		if ((servToDest = ServChatTo(destID)) != null) {
			servToDest.send(msg);
		}
	}

	public void EndConnexion(int destID) {
		int index;
		if ((index = servChatInListID.indexOf(destID)) != -1) {
			servChatInList.get(index).end();
			servChatInList.remove(index);
			servChatInListID.remove(index);
		}
		if ((index = servChatOutListID.indexOf(destID)) != -1) {
			servChatOutList.get(index).end();
			servChatOutList.remove(index);
			servChatOutListID.remove(index);
		}
	}

	public ServChatIn ServChatFrom(int destID) {
		int index;
		if ((index = servChatInListID.indexOf(destID)) != -1) {
			return servChatInList.get(index);
		} else {
			return null;
		}
	}
	
	public ServChatOut ServChatTo(int destID) {
		int index;
		if ((index = servChatOutListID.indexOf(destID)) != -1) {
			return servChatOutList.get(index);
		} else {
			return null;
		}
	}
}
