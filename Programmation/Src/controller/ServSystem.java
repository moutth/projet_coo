package controller;

import java.io.IOException;
import model.*;
import java.net.*;

public class ServSystem extends Thread {

	private static final int BUFFSIZE = 512;

	private ComSystem comSystem;
	private User currentUser;
	private Model model;
	
	private boolean running = true;

	ServSystem(ComSystem in) {
		comSystem = in;
		model = comSystem.controller.model;
		currentUser = comSystem.controller.model.getCurrentUser();
		start(); // runs the thread once instantiated
	}

	public void run() {
		DatagramSocket socket = null;
		try {
			socket = new DatagramSocket(ComSystem.SERVSYST);
		} catch (SocketException e) {
			e.printStackTrace();
		}

		byte[] buffer = new byte[BUFFSIZE];
		
		while (running) {
			DatagramPacket inPacket = new DatagramPacket(buffer, buffer.length);

			try {
				socket.receive(inPacket);
			} catch (IOException e) {
				e.printStackTrace();
			}

			InetAddress clientAddress = inPacket.getAddress();
			if (!clientAddress.toString().equals("/" + currentUser.getIp())) {
				
				MsgSystem msg = new MsgSystem(model, new String(inPacket.getData(), 0, inPacket.getLength()));
				// Debug print
				System.out.println("received : " + msg);

				switch (msg.getType()) {

				case "Init":
					if (!(model.getCurrentUser().getPseudo().equals("!"))) {
						comSystem.SendSystemInitAnswer();
						// Debug print
						System.out.println("sent : " + new MsgSystem(model, "InitAnswer"));
					}
					break;
					
				case "InitAnswer":
					comSystem.controller.model.AddUser(new User(Integer.valueOf(msg.getArg(1)), msg.getArg(2), clientAddress.toString()));
					break;
					
				case "Connexion" :
					comSystem.controller.model.AddUser(new User(Integer.valueOf(msg.getArg(1)), msg.getArg(2), clientAddress.toString()));
					break;
					
				case "ChangePseudo" :
					comSystem.controller.model.ChangePseudo(Integer.valueOf(msg.getArg(1)), msg.getArg(2));
					break;
					
				case "Deconnexion" :
					int userDeconnectedID = Integer.valueOf(msg.getArg(1));
					comSystem.controller.model.RemoveUser(userDeconnectedID);
					comSystem.EndConnexion(userDeconnectedID);
					break;
					
				default:
					System.out.println("default");
				}
			}
		}
	}
	
	
	// Getters & setters

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

}
