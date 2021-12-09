package controller;

import java.io.IOException;
import model.*;
import java.net.*;

public class ServSystem extends Thread {

	private static final int BUFFSIZE = 256;

	private ComSystem comSystem;

	ServSystem(ComSystem in) {
		comSystem = in;
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

			if (clientAddress.toString() != "/" + comSystem.controller.model.currentUser.ip) {

				String message = new String(inPacket.getData(), 0, inPacket.getLength());
				System.out.println(message);

				switch (message) {

				case "newConnexion":
					System.out.println(clientAddress);
					String response = Integer.toString(comSystem.controller.model.currentUser.userID) + " "
							+ comSystem.controller.model.currentUser.pseudo;

					try {
						comSystem.SendUdp(clientAddress, ComSystem.SERVSYST, new MsgSystem(response));
						System.out.println(response);
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;

				default:
					System.out.println("default");
				}
			}
		}

	}

}
