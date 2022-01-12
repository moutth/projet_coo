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
	// liste des sockets TCP pour chatter
	public List<ServChat> servChatList;
	// liste des id de chaque destinataire des servChat
	public List<Integer> servChatListID;

	public String localIP;

	ComSystem(Controller in) {
		controller = in;
		servSystem = new ServSystem(this);
		servAccept = new ServAccept(this);
		servChatList= new ArrayList<ServChat>();
		servChatListID = new ArrayList<Integer>();
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
		//DEBUG
		System.out.println(localIP);
		controller.model.getCurrentUser().setIp(localIP.toString());

		try {
			BROADCAST = InetAddress.getByName("255.255.255.255");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void SendTCP(String ip, int port, MsgUser msg) throws UnknownHostException, IOException {
		OutputStream outputStream;
		OutputStreamWriter outputStreamWriter;

		// etablissement de la connexion
		Socket sendSocket = new Socket(ip, port);

		// Instantiation pour écrire des données
		outputStream = sendSocket.getOutputStream();
		outputStreamWriter = new OutputStreamWriter(outputStream);

		// Envoi de la donnée
		outputStreamWriter.write(msg.toString()); // à modifier plus tard
		outputStreamWriter.flush();

		sendSocket.close();
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

	public void EstablishConnexion(User dest) {
		try {
			servChatList.add(new ServChat(this, dest, new Socket(dest.getIp(), SERVACCEPT)));
			servChatListID.add(dest.getUserID());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void EndConnexion(User Dest) {
		int index;
		if ((index = servChatListID.indexOf(Dest.getUserID())) != -1) {
			servChatList.get(index).end();
			servChatList.remove(index);
			servChatListID.remove(index);
		}
	}
	public void EndConnexion(int DestID) {
		int index;
		if ((index = servChatListID.indexOf(DestID)) != -1) {
			servChatList.get(index).end();
			servChatList.remove(index);
			servChatListID.remove(index);
		}
	}
	
	public ServChat servChatWith (User Dest) {
		int index;
		if ((index = servChatListID.indexOf(Dest.getUserID())) != -1) {
			return servChatList.get(index);
		}
		else {
			return null;
		}
	}
	public ServChat servChatWith (int DestID) {
		int index;
		if ((index = servChatListID.indexOf(DestID)) != -1) {
			return servChatList.get(index);
		}
		else {
			return null;
		}
	}
}
