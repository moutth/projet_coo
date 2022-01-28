package controller;

import java.io.*;
import java.net.*;

import model.*;

public class ServChatOut extends Thread {
	
	private ComSystem comSystem;
	private User currentUser;
	public User distantUser;
	private Model model;
	
	private Socket sendSocket;
	private DataOutputStream writer;
	
	private String toSend;
	private boolean running;

	public boolean sendWaiting;
	
	ServChatOut(ComSystem comSystem, User distant, Socket sendSocket){
		this.comSystem = comSystem;
		this.model = comSystem.controller.model;
		this.currentUser = comSystem.controller.model.getCurrentUser();
		this.distantUser = distant;
		sendWaiting = false;
		running = true;
		
		this.sendSocket = sendSocket;
		
		try {
			writer = new DataOutputStream(sendSocket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		start();
	}
	
	synchronized public void run() {
		while(running) {
			try {
				wait();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
				System.out.println(sendWaiting);
				try {
					writer.writeUTF(toSend);
					System.out.println(toSend);
					writer.flush();
				} catch (IOException e) {
					// On arrive ici si notre destinataire ferme sans deconnexion
					// Fin du thread d'écoute et fermeture des sockets
					running = false;
					comSystem.EndConnexion(distantUser.getUserID());
					comSystem.controller.model.RemoveUser(distantUser.getUserID());
				}
				sendWaiting = false;
		}
	}
	
	synchronized public void send(String msg) {
		toSend = msg;
		notify();
	}
	
	public void end() {
		running = false;
	}
	
	public boolean isRunning() {
		return running;
	}
}