package controller;
import java.io.IOException;
import java.net.*;

public class ServSystem extends Thread {

	private static final int BUFFSIZE = 256;
	
	private ComSystem comSystem;
	
	ServSystem(ComSystem in){
		comSystem = in;
		start();	//runs the thread once instanciated
	}

	public void run(){
		DatagramSocket socket = null;
		try {
			socket = new DatagramSocket(ComSystem.SERVSYST);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
		byte[] buffer = new byte[BUFFSIZE];
		
		DatagramPacket inPacket = new DatagramPacket(buffer, buffer.length);
		
		try {
			socket.receive(inPacket);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		InetAddress clientAddress = inPacket.getAddress();
		
		String message = new String(inPacket.getData(), 0, inPacket.getLength());
		
		switch (message) {
			case "newConnexion":
				String response = Integer.toString(comSystem.controller.model.currentUser.userID) + comSystem.controller.model.currentUser.pseudo;
				DatagramPacket outPacket = new DatagramPacket(response.getBytes(), response.length(), clientAddress, ComSystem.SERVSYST);
				try {
					socket.send(outPacket);
				} catch (IOException e) {
					e.printStackTrace();
				}
			
				
		}

	}

}
