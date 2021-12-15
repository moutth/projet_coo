package controller;

import java.io.IOException;
import model.*;
import java.net.*;

public class ServSystem extends Thread {

	private static final int BUFFSIZE = 256;

	private ComSystem comSystem;
	private User currentUser;
	private Model model;

	ServSystem(ComSystem in) {
		comSystem = in;
		model = comSystem.controller.model;
		currentUser = comSystem.controller.model.currentUser;
		start(); // runs the thread once instanciated
	}

	public void run() {
		DatagramSocket socket = null;
		try {
			socket = new DatagramSocket(ComSystem.SERVSYST);
		} catch (SocketException e) {
			e.printStackTrace();
		}

		byte[] buffer = new byte[BUFFSIZE];

		while (true) {
			DatagramPacket inPacket = new DatagramPacket(buffer, buffer.length);

			try {
				socket.receive(inPacket);
			} catch (IOException e) {
				e.printStackTrace();
			}

			InetAddress clientAddress = inPacket.getAddress();
			if (clientAddress.toString().equals("/" + currentUser.ip)) {
				
				MsgSystem msg = new MsgSystem(model, new String(inPacket.getData(), 0, inPacket.getLength()));
				System.out.println("received : " + msg);

				switch (msg.getType()) {

				case "newConnexion":
					try {
						comSystem.SendUdp(clientAddress, ComSystem.SERVSYST, new MsgSystem(model, "ConnexionAnswer", ""));
						System.out.println("sent : " + new MsgSystem(model, "ConnexionAnswer"));
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case "ConnexionAnswer":
					comSystem.controller.model.AddUser(new User(Integer.valueOf(msg.getArg(1)), msg.getArg(2), clientAddress.toString()));
				default:
					System.out.println("default");
				}
			}
		}

	}

}
