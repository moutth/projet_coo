package controller;

import java.io.*;
import java.net.*;

import model.User;

public class ChatThread extends Thread {
	Socket clientSock = null;
	private DataInputStream reader;
	private ComSystem comSystem;
	private int destID;
	private boolean running = true;
	
	public ChatThread (ComSystem comSystem, Socket receiveSocket, int destID) {
		this.comSystem = comSystem;
		this.clientSock = receiveSocket;
		this.destID = destID;
		try {
			reader = new DataInputStream(new BufferedInputStream(receiveSocket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		start();
	}
	
	public void run() {
		String line = "";
		while(running) {
			try {
				line = reader.readUTF();
				System.out.println(line);
				comSystem.controller.model.getPrincipal().ReceiveMessage(line, destID);

			} catch (IOException e) {
				// On arrive ici si notre destinataire ferme sans deconnexion
				// Fin du thread d'écoute et fermeture des sockets
				running = false;
				comSystem.EndConnexion(destID);
			}
		}
	}
	
	public void end() {
		running = false;
	}
	
	public boolean isRunning() {
		return running;
	}
}
